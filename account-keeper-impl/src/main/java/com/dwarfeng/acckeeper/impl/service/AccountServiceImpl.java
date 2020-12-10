package com.dwarfeng.acckeeper.impl.service;

import com.dwarfeng.acckeeper.sdk.util.AccountUtil;
import com.dwarfeng.acckeeper.sdk.util.ServiceExceptionCodes;
import com.dwarfeng.acckeeper.stack.bean.entity.Account;
import com.dwarfeng.acckeeper.stack.bean.entity.dto.AccountInfo;
import com.dwarfeng.acckeeper.stack.service.AccountMaintainService;
import com.dwarfeng.acckeeper.stack.service.AccountService;
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
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountMaintainService accountMaintainService;
    @Autowired
    private ServiceExceptionMapper sem;

    @Value("${acckeeper.password.salt_log_rounds}")
    private int logRounds;

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void register(AccountInfo accountInfo, String password) throws ServiceException {
        StringIdKey accountKey = accountInfo.getKey();
        LOGGER.info("注册新账户 " + accountKey + " ...");
        try {
            if (accountMaintainService.exists(accountKey)) {
                LOGGER.warn("指定的账户 " + accountKey + " 已经存在，注册失败，将抛出异常...");
                throw new ServiceException(ServiceExceptionCodes.ACCOUNT_ALREADY_EXISTED);
            }
            String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt(logRounds));
            Account account = new Account(
                    accountKey,
                    encryptedPassword,
                    accountInfo.isEnabled(),
                    accountInfo.getRemark(),
                    0
            );
            accountMaintainService.insert(account);
            LOGGER.info("账户已成功注册: " + account + " ...");
        } catch (Exception e) {
            throw sem.map(e);
        }
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void update(AccountInfo accountInfo) throws ServiceException {
        StringIdKey accountKey = accountInfo.getKey();
        LOGGER.info("更新账户 " + accountKey + " ...");
        try {
            if (!accountMaintainService.exists(accountKey)) {
                LOGGER.warn("指定的账户 " + accountKey + " 不存在，更新失败，将抛出异常...");
                throw new ServiceException(ServiceExceptionCodes.ACCOUNT_NOT_EXISTS);
            }
            Account account = accountMaintainService.get(accountKey);
            account.setEnabled(accountInfo.isEnabled());
            account.setRemark(accountInfo.getRemark());
            AccountUtil.increaseSerial(account);
            accountMaintainService.update(account);
            LOGGER.info("账户已成功更新: " + account + " ...");
        } catch (Exception e) {
            throw sem.map(e);
        }
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void invalid(StringIdKey key) throws ServiceException {
        LOGGER.info("使账户 " + key + " 的登录信息无效...");
        try {
            if (!accountMaintainService.exists(key)) {
                LOGGER.warn("指定的账户 " + key + " 不存在，登录无效失败，将抛出异常...");
                throw new ServiceException(ServiceExceptionCodes.ACCOUNT_NOT_EXISTS);
            }
            Account account = accountMaintainService.get(key);
            AccountUtil.increaseSerial(account);
            accountMaintainService.update(account);
            LOGGER.info("账户: " + account + " 的登录信息已经无效...");
        } catch (Exception e) {
            throw sem.map(e);
        }
    }
}
