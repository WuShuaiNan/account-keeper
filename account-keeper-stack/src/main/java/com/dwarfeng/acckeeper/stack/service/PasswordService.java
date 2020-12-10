package com.dwarfeng.acckeeper.stack.service;

import com.dwarfeng.acckeeper.stack.bean.entity.Account;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 密码服务。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public interface PasswordService extends Service {

    /**
     * 更新账户密码。
     *
     * @param accountKey  账户的主键。
     * @param oldPassword 旧密码。
     * @param newPassword 新密码。
     * @return 更改密码成功后返回的账户。
     * @throws ServiceException 服务异常。
     */
    Account updatePassword(StringIdKey accountKey, String oldPassword, String newPassword) throws ServiceException;

    /**
     * 重置账户密码。
     *
     * <p> 该操作会导致原先的登录状态无效。
     *
     * @param accountKey  账户的主键。
     * @param newPassword 新密码。
     * @return 更改密码成功后返回的账户。
     * @throws ServiceException 服务异常。
     */
    Account resetPassword(StringIdKey accountKey, String newPassword) throws ServiceException;

    /**
     * 判断指定的账户密码是否正确。
     *
     * @param accountKey 账户的主键。
     * @param password   账户的密码。
     * @return 账户的密码是否正确。
     * @throws ServiceException 服务异常。
     */
    boolean checkPassword(StringIdKey accountKey, String password) throws ServiceException;
}
