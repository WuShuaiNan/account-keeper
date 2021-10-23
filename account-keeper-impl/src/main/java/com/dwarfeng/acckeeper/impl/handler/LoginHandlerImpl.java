package com.dwarfeng.acckeeper.impl.handler;

import com.dwarfeng.acckeeper.stack.bean.dto.LoginInfo;
import com.dwarfeng.acckeeper.stack.bean.entity.Account;
import com.dwarfeng.acckeeper.stack.bean.entity.LoginState;
import com.dwarfeng.acckeeper.stack.cache.LoginStateCache;
import com.dwarfeng.acckeeper.stack.exception.*;
import com.dwarfeng.acckeeper.stack.handler.LoginHandler;
import com.dwarfeng.acckeeper.stack.service.AccountMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.KeyFetcher;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.CacheException;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LoginHandlerImpl implements LoginHandler {

    private final AccountMaintainService accountMaintainService;

    private final LoginStateCache loginStateCache;

    private final KeyFetcher<LongIdKey> keyFetcher;

    @Value("${acckeeper.login.expire}")
    private long expireTimeout;
    @Value("${acckeeper.login.timeout_factor}")
    private double expireTimeoutFactor;

    public LoginHandlerImpl(
            AccountMaintainService accountMaintainService, LoginStateCache loginStateCache,
            KeyFetcher<LongIdKey> keyFetcher
    ) {
        this.accountMaintainService = accountMaintainService;
        this.loginStateCache = loginStateCache;
        this.keyFetcher = keyFetcher;
    }

    @Override
    public LoginState login(LoginInfo loginInfo) throws HandlerException {
        try {
            // 1. 获取主键。
            StringIdKey accountKey = loginInfo.getAccountKey();

            // 2. 确定主键对应的账户存在。
            makeSureAccountExists(accountKey);

            // 3. 确定账户未被禁用。
            makeSureAccountNotDisabled(accountKey);

            // 4. 确认密码正确。
            makeSurePasswordCorrect(accountKey, loginInfo.getPassword());

            // 5. 获取账户实体，并根据账户实体构造登录状态实体。
            Account account = accountMaintainService.get(accountKey);
            LoginState loginState = new LoginState(
                    keyFetcher.fetchKey(), accountKey, System.currentTimeMillis() + expireTimeout,
                    account.getSerialVersion()
            );
            long timeout = (long) (expireTimeout * expireTimeoutFactor);

            // 6. 向登录状态缓存中推送实体，并返回结果。
            loginStateCache.push(loginState, timeout);
            return loginState;
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void logout(LongIdKey loginStateKey) throws HandlerException {
        try {
            // 1. 如果登录状态缓存中有实体，则清除实体，如果不存在，也不做特别的动作。
            if (loginStateCache.exists(loginStateKey)) {
                loginStateCache.delete(loginStateKey);
            }
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public boolean isLogin(LongIdKey loginStateKey) throws HandlerException {
        try {
            // 1. 判断登录状态缓存中是否有登录状态实体，如果没有，则返回 false。
            if (!loginStateCache.exists(loginStateKey)) {
                return false;
            }

            // 2. 获取登录状态实体。
            LoginState loginState = loginStateCache.get(loginStateKey);

            // 3. 如果过了登录超时期，则返回 false。
            if (loginState.getExpireDate() < System.currentTimeMillis()) {
                return false;
            }

            // 4. 根据登录状态判断账户维护服务中是否有对应的账户，如果没有，则返回 false。
            if (!accountMaintainService.exists(loginState.getAccountKey())) {
                return false;
            }

            // 5. 获取账户实体。
            Account account = accountMaintainService.get(loginState.getAccountKey());

            // 6. 如果账户实体被禁用，则返回 false。
            if (!account.isEnabled()) {
                return false;
            }

            // 7. 如果账户实体的序列版本等于登录状态的序列版本，则返回 true，否则返回 false。
            return account.getSerialVersion() == loginState.getSerialVersion();
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public LoginState postpone(LongIdKey loginStateKey) throws HandlerException {
        try {
            // 1. 确认登录状态存在。
            makeSureLoginStateExists(loginStateKey);

            // 2. 确认登录状态没有过期。
            makeSureLoginStateNotExpired(loginStateKey);

            // 3. 获取登录状态，并获取其中的账户主键。
            LoginState loginState = loginStateCache.get(loginStateKey);
            StringIdKey accountKey = loginState.getAccountKey();

            // 5. 确认账户存在。
            makeSureAccountExists(accountKey);

            // 6. 确认用户没有被禁用。
            makeSureAccountNotDisabled(accountKey);

            // 7. 确认用户的序列编码与登录状态编码一致。
            makeSureSerialNumberConsistent(loginStateKey);

            // 8. 更新登录状态实体，设置新的超时日期。
            loginState.setExpireDate(System.currentTimeMillis() + expireTimeout);

            // 9. 将新的实体推送到缓存中，并更新缓存的超时时间，最后返回结果。
            long timeout = (long) (expireTimeout * expireTimeoutFactor);
            loginStateCache.push(loginState, timeout);
            return loginState;
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    private void makeSureAccountExists(StringIdKey accountKey) throws HandlerException {
        try {
            if (!accountMaintainService.exists(accountKey)) {
                throw new AccountNotExistsException(accountKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    private void makeSureAccountNotDisabled(StringIdKey accountKey) throws HandlerException {
        try {
            Account account = accountMaintainService.get(accountKey);
            if (!account.isEnabled()) {
                throw new AccountDisabledException(accountKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    private void makeSurePasswordCorrect(StringIdKey accountKey, String password) throws HandlerException {
        try {
            Account account = accountMaintainService.get(accountKey);
            if (!BCrypt.checkpw(password, account.getPassword())) {
                throw new PasswordIncorrectException(accountKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    private void makeSureLoginStateExists(LongIdKey loginStateKey) throws HandlerException {
        try {
            if (!loginStateCache.exists(loginStateKey)) {
                throw new LoginStateNotExistsException(loginStateKey);
            }
        } catch (CacheException e) {
            throw new HandlerException(e);
        }
    }

    private void makeSureLoginStateNotExpired(LongIdKey loginStateKey) throws HandlerException {
        try {
            LoginState loginState = loginStateCache.get(loginStateKey);
            if (loginState.getExpireDate() < System.currentTimeMillis()) {
                throw new LoginStateExpiredException(loginStateKey);
            }
        } catch (CacheException e) {
            throw new HandlerException(e);
        }
    }

    private void makeSureSerialNumberConsistent(LongIdKey loginStateKey) throws HandlerException {
        try {
            LoginState loginState = loginStateCache.get(loginStateKey);
            Account account = accountMaintainService.get(loginState.getAccountKey());
            if (loginState.getSerialVersion() != account.getSerialVersion()) {
                throw new SerialVersionInconsistentException(loginStateKey);
            }
        } catch (CacheException | ServiceException e) {
            throw new HandlerException(e);
        }
    }
}
