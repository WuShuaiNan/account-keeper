package com.dwarfeng.acckeeper.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 登录状态不存在异常。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public class LoginStateExpiredException extends HandlerException {

    private static final long serialVersionUID = -405183225726353967L;

    private final LongIdKey loginStateKey;

    public LoginStateExpiredException(LongIdKey loginStateKey) {
        this.loginStateKey = loginStateKey;
    }

    public LoginStateExpiredException(Throwable cause, LongIdKey loginStateKey) {
        super(cause);
        this.loginStateKey = loginStateKey;
    }

    @Override
    public String getMessage() {
        return "登录状态 " + loginStateKey + " 已过期";
    }
}
