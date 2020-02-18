package com.dwarfeng.acckeeper.impl.service;

import com.dwarfeng.acckeeper.sdk.util.ServiceExceptionCodes;
import com.dwarfeng.acckeeper.stack.bean.entity.Account;
import com.dwarfeng.acckeeper.stack.bean.entity.dto.AccountInfo;
import com.dwarfeng.acckeeper.stack.bean.entity.dto.ForcePasswordInfo;
import com.dwarfeng.acckeeper.stack.bean.entity.dto.PasswordInfo;
import com.dwarfeng.acckeeper.stack.bean.entity.dto.RegisterInfo;
import com.dwarfeng.acckeeper.stack.service.AccountMaintainService;
import com.dwarfeng.acckeeper.stack.service.RegisterService;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@Service
public class RegisterServiceImpl implements RegisterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterServiceImpl.class);

    @Autowired
    private AccountMaintainService accountMaintainService;
    @Autowired
    private ServiceExceptionMapper sem;

    @Value("${acckeeper.password.salt_log_rounds}")
    private int logRounds;

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public Account register(@NotNull RegisterInfo registerInfo) throws ServiceException {
        LOGGER.info("注册新账户 ID=" + registerInfo.getId() + " ...");
        StringIdKey stringIdKey = new StringIdKey(registerInfo.getId());
        try {
            if (accountMaintainService.exists(stringIdKey)) {
                LOGGER.warn("指定的id " + registerInfo.getId() + " 已经存在，注册失败，将抛出异常...");
                throw new ServiceException(ServiceExceptionCodes.ACCOUNT_ALREADY_EXISTED);
            }
            String password = BCrypt.hashpw(registerInfo.getPassword(), BCrypt.gensalt(logRounds));
            Account account = new Account(
                    stringIdKey,
                    password,
                    true,
                    registerInfo.getRemark(),
                    0
            );
            accountMaintainService.insert(account);
            LOGGER.info("账户已成功注册: " + account.toString() + " ...");
            return account;
        } catch (Exception e) {
            throw sem.map(e);
        }
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public Account updatePassword(PasswordInfo passwordInfo) throws ServiceException {
        StringIdKey stringIdKey = new StringIdKey(passwordInfo.getId());
        try {
            LOGGER.info("账户 " + passwordInfo.getId() + " 请求更改密码...");
            /*
             * 1. 验证账户是否存在。
             * 2. 验证账户的旧密码是否正确。
             * 3. 将账户的密码设置为新密码，并更新账户。
             * 4. 返回更新后的账户。
             */
            //1. 验证账户是否存在。
            if (!accountMaintainService.exists(stringIdKey)) {
                LOGGER.warn("指定的id " + passwordInfo.getId() + " 不存在，更改密码失败，将抛出异常...");
                throw new ServiceException(ServiceExceptionCodes.ACCOUNT_NOT_EXISTS);
            }
            //2. 验证账户的旧密码是否正确。
            //获取账户的详细信息。
            Account account = accountMaintainService.get(stringIdKey);
            if (BCrypt.checkpw(passwordInfo.getOldPassword(), account.getPassword())) {
                LOGGER.info("账户 ID=" + account.getKey().getStringId() + " 密码验证成功，将更新密码...");
            } else {
                LOGGER.warn("账户 ID=" + account.getKey().getStringId() + " 密码验证失败，将会抛出异常...");
                throw new ServiceException(ServiceExceptionCodes.WRONG_PASSWORD);
            }
            //3. 将账户的密码设置为新密码，并更新账户。
            account.setPassword(BCrypt.hashpw(passwordInfo.getNewPassword(), BCrypt.gensalt(logRounds)));
            accountMaintainService.update(account);
            //4. 返回更新后的账户。
            return account;
        } catch (Exception e) {
            throw sem.map(e);
        }
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public Account forceUpdatePassword(ForcePasswordInfo forcePasswordInfo) throws ServiceException {
        StringIdKey stringIdKey = new StringIdKey(forcePasswordInfo.getId());
        try {
            LOGGER.info("账户 " + forcePasswordInfo.getId() + " 请求强制性更改密码...");
            /*
             * 1. 验证账户是否存在。
             * 2. 将账户的密码设置为新密码，并更新账户。
             * 3. 返回更新后的账户。
             */
            //1. 验证账户是否存在。
            if (!accountMaintainService.exists(stringIdKey)) {
                LOGGER.warn("指定的id " + forcePasswordInfo.getId() + " 不存在存在，更改密码失败，将抛出异常...");
                throw new ServiceException(ServiceExceptionCodes.ACCOUNT_NOT_EXISTS);
            }
            //2. 将账户的密码设置为新密码，并更新账户。
            //获取账户的详细信息。
            Account account = accountMaintainService.get(stringIdKey);
            account.setPassword(BCrypt.hashpw(forcePasswordInfo.getNewPassword(), BCrypt.gensalt(logRounds)));
            accountMaintainService.update(account);
            //3. 返回更新后的账户。
            return account;
        } catch (Exception e) {
            throw sem.map(e);
        }
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public Account updateAccountInfo(AccountInfo accountInfo) throws ServiceException {
        StringIdKey stringIdKey = new StringIdKey(accountInfo.getId());
        try {
            LOGGER.info("账户 " + accountInfo.getId() + " 请求强制性更改密码...");
            /*
             * 1. 验证账户是否存在。
             * 2. 设置账户信息为指定的信息，并更新账户。
             * 3. 返回更新后的账户。
             */
            //1. 验证账户是否存在。
            if (!accountMaintainService.exists(stringIdKey)) {
                LOGGER.warn("指定的id " + accountInfo.getId() + " 不存在存在，更改密码失败，将抛出异常...");
                throw new ServiceException(ServiceExceptionCodes.ACCOUNT_NOT_EXISTS);
            }
            //2. 设置账户信息为指定的信息，并更新账户。
            //获取账户的详细信息。
            Account account = accountMaintainService.get(stringIdKey);
            account.setEnabled(account.isEnabled());
            account.setRemark(accountInfo.getRemark());
            accountMaintainService.update(account);
            //3. 返回更新后的账户。
            return account;
        } catch (Exception e) {
            throw sem.map(e);
        }
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public boolean checkPassword(String accountId, String password) throws ServiceException {
        StringIdKey stringIdKey = new StringIdKey(accountId);
        try {
            LOGGER.info("账户 " + accountId + " 查询密码是否正确...");
            /*
             * 1. 验证账户是否存在。
             * 2. 验证账户的密码是否正确，并返回结果。
             */
            //1. 验证账户是否存在。
            if (!accountMaintainService.exists(stringIdKey)) {
                LOGGER.warn("指定的id " + accountId + " 不存在，密码验证失败，将抛出异常...");
                throw new ServiceException(ServiceExceptionCodes.ACCOUNT_NOT_EXISTS);
            }
            //2. 验证账户的密码是否正确，并返回结果。
            //获取账户的详细信息。
            Account account = accountMaintainService.get(stringIdKey);
            if (BCrypt.checkpw(password, account.getPassword())) {
                LOGGER.info("账户 ID=" + account.getKey().getStringId() + " 密码验证成功...");
                return true;
            } else {
                LOGGER.warn("账户 ID=" + account.getKey().getStringId() + " 密码验证失败...");
                return false;
            }
        } catch (Exception e) {
            throw sem.map(e);
        }
    }
}
