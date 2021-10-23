package com.dwarfeng.acckeeper.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 登录状态不存在异常。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public class LoginStateNotExistsException extends HandlerException {

    private static final long serialVersionUID = 7482543190444498519L;

    private final LongIdKey loginStateKey;

    public LoginStateNotExistsException(LongIdKey loginStateKey) {
        this.loginStateKey = loginStateKey;
    }

    public LoginStateNotExistsException(Throwable cause, LongIdKey loginStateKey) {
        super(cause);
        this.loginStateKey = loginStateKey;
    }

    @Override
    public String getMessage() {
        return "登录状态 " + loginStateKey + " 不存在";
    }
}
