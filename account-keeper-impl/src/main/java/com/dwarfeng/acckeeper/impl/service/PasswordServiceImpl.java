package com.dwarfeng.acckeeper.impl.service;

import com.dwarfeng.acckeeper.sdk.util.AccountUtil;
import com.dwarfeng.acckeeper.sdk.util.ServiceExceptionCodes;
import com.dwarfeng.acckeeper.stack.bean.entity.Account;
import com.dwarfeng.acckeeper.stack.service.AccountMaintainService;
import com.dwarfeng.acckeeper.stack.service.PasswordService;
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

@Service
public class PasswordServiceImpl implements PasswordService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PasswordServiceImpl.class);

    @Autowired
    private AccountMaintainService accountMaintainService;
    @Autowired
    private ServiceExceptionMapper sem;

    @Value("${acckeeper.password.salt_log_rounds}")
    private int logRounds;

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public Account updatePassword(StringIdKey accountKey, String oldPassword, String newPassword) throws ServiceException {
        try {
            LOGGER.info("账户 " + accountKey + " 请求更改密码...");
            /*
             * 1. 验证账户是否存在。
             * 2. 验证账户的旧密码是否正确。
             * 3. 将账户的密码设置为新密码，并更新账户。
             * 4. 返回更新后的账户。
             */
            //1. 验证账户是否存在。
            if (!accountMaintainService.exists(accountKey)) {
                LOGGER.warn("指定的账户 " + accountKey + " 不存在，更改密码失败，将抛出异常...");
                throw new ServiceException(ServiceExceptionCodes.ACCOUNT_NOT_EXISTS);
            }
            //2. 验证账户的旧密码是否正确。
            //获取账户的详细信息。
            Account account = accountMaintainService.get(accountKey);
            if (internalCheckPassword(accountKey, oldPassword)) {
                LOGGER.info("账户 " + account.getKey() + " 密码验证成功，将更新密码...");
            } else {
                LOGGER.warn("账户 " + account.getKey() + " 密码验证失败，将会抛出异常...");
                throw new ServiceException(ServiceExceptionCodes.WRONG_PASSWORD);
            }
            //3. 将账户的密码设置为新密码，并更新账户。
            account.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt(logRounds)));
            accountMaintainService.update(account);
            //4. 返回更新后的账户。
            return account;
        } catch (Exception e) {
            throw sem.map(e);
        }
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public Account resetPassword(StringIdKey accountKey, String newPassword) throws ServiceException {
        try {
            LOGGER.info("账户 " + accountKey + " 请求强制性更改密码...");
            /*
             * 1. 验证账户是否存在。
             * 2. 将账户的密码设置为新密码，并更新账户。
             * 3. 返回更新后的账户。
             */
            //1. 验证账户是否存在。
            if (!accountMaintainService.exists(accountKey)) {
                LOGGER.warn("指定的账户 " + accountKey + " 不存在，更改密码失败，将抛出异常...");
                throw new ServiceException(ServiceExceptionCodes.ACCOUNT_NOT_EXISTS);
            }
            //2. 将账户的密码设置为新密码，并更新账户。
            //获取账户的详细信息。
            Account account = accountMaintainService.get(accountKey);
            account.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt(logRounds)));
            AccountUtil.increaseSerial(account);
            accountMaintainService.update(account);
            //3. 返回更新后的账户。
            return account;
        } catch (Exception e) {
            throw sem.map(e);
        }
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public boolean checkPassword(StringIdKey accountKey, String password) throws ServiceException {
        try {
            return internalCheckPassword(accountKey, password);
        } catch (Exception e) {
            throw sem.map(e);
        }
    }

    private boolean internalCheckPassword(StringIdKey accountKey, String password) throws ServiceException {
        LOGGER.info("账户 " + accountKey + " 查询密码是否正确...");
        /*
         * 1. 验证账户是否存在。
         * 2. 验证账户的密码是否正确，并返回结果。
         */
        //1. 验证账户是否存在。
        if (!accountMaintainService.exists(accountKey)) {
            LOGGER.warn("指定的账户 " + accountKey + " 不存在，密码验证失败，将抛出异常...");
            throw new ServiceException(ServiceExceptionCodes.ACCOUNT_NOT_EXISTS);
        }
        //2. 验证账户的密码是否正确，并返回结果。
        //获取账户的详细信息。
        Account account = accountMaintainService.get(accountKey);
        if (BCrypt.checkpw(password, account.getPassword())) {
            LOGGER.info("账户 ID=" + account.getKey().getStringId() + " 密码验证成功...");
            return true;
        } else {
            LOGGER.warn("账户 ID=" + account.getKey().getStringId() + " 密码验证失败...");
            return false;
        }
    }
}
