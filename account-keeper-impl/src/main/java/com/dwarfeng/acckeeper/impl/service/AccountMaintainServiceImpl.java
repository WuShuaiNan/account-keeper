package com.dwarfeng.acckeeper.impl.service;

import com.dwarfeng.acckeeper.stack.bean.entity.Account;
import com.dwarfeng.acckeeper.stack.cache.AccountCache;
import com.dwarfeng.acckeeper.stack.dao.AccountDao;
import com.dwarfeng.acckeeper.stack.service.AccountMaintainService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class AccountMaintainServiceImpl implements AccountMaintainService {

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private AccountCache accountCache;
    @Autowired
    private ServiceExceptionMapper sem;
    @Value("${cache.timeout.entity.account}")
    private long accountTimeout;

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public boolean exists(StringIdKey key) throws ServiceException {
        try {
            return internalExists(key);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("判断实体是否存在时发生异常", LogLevel.WARN, sem, e);
        }
    }

    private boolean internalExists(StringIdKey key) throws Exception {
        return accountCache.exists(key) || accountDao.exists(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public Account get(StringIdKey key) throws ServiceException {
        try {
            return internalGet(key);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("获取实体时发生异常", LogLevel.WARN, sem, e);
        }
    }

    private Account internalGet(StringIdKey key) throws com.dwarfeng.subgrade.stack.exception.CacheException, com.dwarfeng.subgrade.stack.exception.DaoException, ServiceException {
        if (accountCache.exists(key)) {
            return accountCache.get(key);
        } else {
            if (!accountDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            Account account = accountDao.get(key);
            accountCache.push(account, accountTimeout);
            return account;
        }
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public StringIdKey insert(Account account) throws ServiceException {
        try {
            return internalInsert(account);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("插入实体时发生异常", LogLevel.WARN, sem, e);
        }
    }

    private StringIdKey internalInsert(Account account) throws Exception {
        if (Objects.nonNull(account.getKey()) && internalExists(account.getKey())) {
            throw new ServiceException(ServiceExceptionCodes.ENTITY_EXISTED);
        }

        accountDao.insert(account);
        accountCache.push(account, accountTimeout);
        return account.getKey();
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void update(Account account) throws ServiceException {
        try {
            internalUpdate(account);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("更新实体时发生异常", LogLevel.WARN, sem, e);
        }
    }

    private void internalUpdate(Account account) throws Exception {
        if (Objects.nonNull(account.getKey()) && !internalExists(account.getKey())) {
            throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
        }
        Account oldAccount = internalGet(account.getKey());
        account.setSerialVersion(oldAccount.getSerialVersion() + 1);
        accountCache.push(account, accountTimeout);
        accountDao.update(account);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void delete(StringIdKey key) throws ServiceException {
        try {
            internalDelete(key);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("删除实体时发生异常", LogLevel.WARN, sem, e);
        }
    }

    private void internalDelete(StringIdKey key) throws Exception {
        if (!internalExists(key)) {
            throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
        }

        accountDao.delete(key);
        accountCache.delete(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public Account getIfExists(StringIdKey key) throws ServiceException {
        try {
            return internalExists(key) ? internalGet(key) : null;
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("获取实体时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public StringIdKey insertIfNotExists(Account account) throws ServiceException {
        try {
            if (Objects.isNull(account.getKey()) || !internalExists(account.getKey())) {
                return internalInsert(account);
            }
            return null;
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("插入实体时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void updateIfExists(Account account) throws ServiceException {
        try {
            if (internalExists(account.getKey())) {
                internalUpdate(account);
            }
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("更新实体时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void deleteIfExists(StringIdKey key) throws ServiceException {
        try {
            if (internalExists(key)) {
                internalDelete(key);
            }
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("删除实体时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public StringIdKey insertOrUpdate(Account account) throws ServiceException {
        try {
            if (internalExists(account.getKey())) {
                internalUpdate(account);
                return null;
            } else {
                return internalInsert(account);
            }
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("插入或更新实体时发生异常", LogLevel.WARN, sem, e);
        }
    }
}
