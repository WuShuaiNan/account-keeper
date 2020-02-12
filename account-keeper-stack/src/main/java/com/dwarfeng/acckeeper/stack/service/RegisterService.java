package com.dwarfeng.acckeeper.stack.service;

import com.dwarfeng.acckeeper.stack.bean.entity.Account;
import com.dwarfeng.acckeeper.stack.bean.entity.dto.AccountInfo;
import com.dwarfeng.acckeeper.stack.bean.entity.dto.ForcePasswordInfo;
import com.dwarfeng.acckeeper.stack.bean.entity.dto.PasswordInfo;
import com.dwarfeng.acckeeper.stack.bean.entity.dto.RegisterInfo;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 注册服务。
 *
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public interface RegisterService extends Service {

    /**
     * 注册账户。
     *
     * @param registerInfo 注册信息。
     * @return 注册成功后返回的账户。
     * @throws ServiceException 服务异常。
     */
    Account register(RegisterInfo registerInfo) throws ServiceException;

    /**
     * 更新账户密码。
     *
     * @param passwordInfo 账户的密码信息。
     * @return 更改密码成功后返回的账户。
     * @throws ServiceException 服务异常。
     */
    Account updatePassword(PasswordInfo passwordInfo) throws ServiceException;

    /**
     * 强制更新账户密码。
     *
     * @param forcePasswordInfo 账户的强制更换密码信息。
     * @return 更改密码成功后返回的账户。
     * @throws ServiceException 服务异常。
     */
    Account forceUpdatePassword(ForcePasswordInfo forcePasswordInfo) throws ServiceException;

    /**
     * 更新账户信息。
     *
     * @param userInfo 指定的信息。
     * @return 更新账户信息之后返回的账户。
     * @throws ServiceException 服务异常。
     */
    Account updateAccountInfo(AccountInfo userInfo) throws ServiceException;

    /**
     * 判断指定的账户密码是否正确。
     *
     * @param userId   指定的账户id。
     * @param password 账户的密码。
     * @return 账户的密码是否正确。
     * @throws ServiceException 服务异常。
     */
    boolean checkPassword(String userId, String password) throws ServiceException;
}
