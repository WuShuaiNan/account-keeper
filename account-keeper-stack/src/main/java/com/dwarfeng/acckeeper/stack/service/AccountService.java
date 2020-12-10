package com.dwarfeng.acckeeper.stack.service;

import com.dwarfeng.acckeeper.stack.bean.entity.dto.AccountInfo;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 账户服务。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public interface AccountService extends Service {

    /**
     * 注册账户。
     *
     * @param accountInfo 账户信息。
     * @param password    账户密码。
     * @throws ServiceException 服务异常。
     */
    void register(AccountInfo accountInfo, String password) throws ServiceException;

    /**
     * 更新账户。
     * <p>该操作会使用户之前的登录信息失效。
     *
     * @param accountInfo 账户信息。
     * @throws ServiceException 服务异常。
     */
    void update(AccountInfo accountInfo) throws ServiceException;

    /**
     * 使账户之前的登录信息无效。
     *
     * @param key 指定的账户主键。
     * @throws ServiceException 服务异常。
     */
    void invalid(StringIdKey key) throws ServiceException;
}
