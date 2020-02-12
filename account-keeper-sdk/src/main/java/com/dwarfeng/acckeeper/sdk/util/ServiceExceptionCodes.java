package com.dwarfeng.acckeeper.sdk.util;

import com.dwarfeng.subgrade.stack.exception.ServiceException;

import static com.dwarfeng.acckeeper.sdk.util.Constants.EXCEPTION_CODE_OFFSET;

/**
 * 服务异常代码。
 *
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public final class ServiceExceptionCodes {

    /**
     * 账户已经存在。
     */
    public static final ServiceException.Code ACCOUNT_ALREADY_EXISTED = new ServiceException.Code(EXCEPTION_CODE_OFFSET, "user already existed");
    /**
     * 账户不存在。
     */
    public static final ServiceException.Code ACCOUNT_NOT_EXISTS = new ServiceException.Code(EXCEPTION_CODE_OFFSET + 10, "user not exists");
    /**
     * 密码错误。
     */
    public static final ServiceException.Code WRONG_PASSWORD = new ServiceException.Code(520, "wrong password");
    /**
     * 登录超时。
     */
    public static final ServiceException.Code LOGIN_EXPIRED = new ServiceException.Code(EXCEPTION_CODE_OFFSET + 20, "login expired");
    /**
     * 登录过期。
     */
    public static final ServiceException.Code LOGIN_OUTDATED = new ServiceException.Code(EXCEPTION_CODE_OFFSET + 30, "login outdated");

    private ServiceExceptionCodes() {
        throw new IllegalStateException("禁止实例化");
    }
}
