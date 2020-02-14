package com.dwarfeng.acckeeper.impl.service;

import com.dwarfeng.acckeeper.sdk.util.ServiceExceptionCodes;
import com.dwarfeng.acckeeper.stack.bean.entity.LoginState;
import com.dwarfeng.acckeeper.stack.cache.LoginStateCache;
import com.dwarfeng.acckeeper.stack.service.AccountMaintainService;
import com.dwarfeng.acckeeper.stack.service.LoginService;
import com.dwarfeng.acckeeper.stack.service.RegisterService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.KeyFetcher;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes.ENTITY_NOT_EXIST;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private RegisterService registerService;
    @Autowired
    private LoginStateCache loginStateCache;
    @Autowired
    private AccountMaintainService accountMaintainService;
    @Autowired
    private KeyFetcher<LongIdKey> keyFetcher;

    @Autowired
    private ServiceExceptionMapper sem;

    @Value("${acckeeper.login.expire}")
    private long expireTimeout;
    @Value("${acckeeper.login.timeout_factor}")
    private double expireTimeoutFactor;

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public LoginState login(StringIdKey accountKey, String password) throws ServiceException {
        try {
            if (!registerService.checkPassword(accountKey.getStringId(), password)) {
                throw new ServiceException(ServiceExceptionCodes.WRONG_PASSWORD);
            }
            long serialVersion = accountMaintainService.get(accountKey).getSerialVersion();
            LoginState loginState = new LoginState(
                    keyFetcher.fetchKey(),
                    accountKey,
                    System.currentTimeMillis() + expireTimeout,
                    serialVersion
            );
            long timeout = (long) (expireTimeout * expireTimeoutFactor);
            loginStateCache.push(loginState, timeout);
            return loginState;
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("登录时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void logout(LongIdKey idKey) throws ServiceException {
        try {
            if (loginStateCache.exists(idKey)) {
                loginStateCache.delete(idKey);
            }
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("登出时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public boolean isLogin(LongIdKey idKey) throws ServiceException {
        try {
            if (!loginStateCache.exists(idKey)) return false;
            LoginState loginState = loginStateCache.get(idKey);
            if (loginState.getExpireDate() < System.currentTimeMillis()) {
                return false;
            }
            if (!accountMaintainService.exists(loginState.getAccountKey())) {
                return false;
            }
            long accountSerialVersion = accountMaintainService.get(loginState.getAccountKey()).getSerialVersion();
            return accountSerialVersion == loginState.getSerialVersion();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("判断是否登录时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public LoginState getLoginState(LongIdKey idKey) throws ServiceException {
        try {
            if (!loginStateCache.exists(idKey)) throw new ServiceException(ENTITY_NOT_EXIST);
            return loginStateCache.get(idKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("判断是否登录时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public LoginState postpone(LongIdKey idKey) throws ServiceException {
        try {
            if (!loginStateCache.exists(idKey)) {
                throw new ServiceException(ENTITY_NOT_EXIST);
            }
            LoginState loginState = loginStateCache.get(idKey);
            if (loginState.getExpireDate() < System.currentTimeMillis()) {
                throw new ServiceException(ServiceExceptionCodes.LOGIN_EXPIRED);
            }
            if (!accountMaintainService.exists(loginState.getAccountKey())) {
                throw new ServiceException(ServiceExceptionCodes.ACCOUNT_NOT_EXISTS);
            }
            long accountSerialVersion = accountMaintainService.get(loginState.getAccountKey()).getSerialVersion();
            if (accountSerialVersion != loginState.getSerialVersion()) {
                throw new ServiceException(ServiceExceptionCodes.LOGIN_OUTDATED);
            }
            loginState.setExpireDate(System.currentTimeMillis() + expireTimeout);
            long timeout = (long) (expireTimeout * expireTimeoutFactor);
            loginStateCache.push(loginState, timeout);
            return loginState;
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("延长超时时间时发生异常", LogLevel.WARN, sem, e);
        }
    }
}
