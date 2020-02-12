package com.dwarfeng.acckeeper.sdk.util;

import com.dwarfeng.subgrade.stack.exception.ServiceException;

/**
 * 服务异常代码。
 *
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public final class ServiceExceptionCodes {

    private static int EXCEPTION_CODE_OFFSET = 1000;

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
    public static final ServiceException.Code WRONG_PASSWORD = new ServiceException.Code(EXCEPTION_CODE_OFFSET + 20, "wrong password");
    /**
     * 登录超时。
     */
    public static final ServiceException.Code LOGIN_EXPIRED = new ServiceException.Code(EXCEPTION_CODE_OFFSET + 30, "login expired");
    /**
     * 登录过期。
     */
    public static final ServiceException.Code LOGIN_OUTDATED = new ServiceException.Code(EXCEPTION_CODE_OFFSET + 40, "login outdated");

    /**
     * 获取异常代号的偏移量。
     *
     * @return 异常代号的偏移量。
     */
    public static int getExceptionCodeOffset() {
        return EXCEPTION_CODE_OFFSET;
    }

    /**
     * 设置异常代号的偏移量。
     *
     * @param exceptionCodeOffset 指定的异常代号的偏移量。
     */
    public static void setExceptionCodeOffset(int exceptionCodeOffset) {
        EXCEPTION_CODE_OFFSET = exceptionCodeOffset;
    }

    private ServiceExceptionCodes() {
        throw new IllegalStateException("禁止实例化");
    }
}
