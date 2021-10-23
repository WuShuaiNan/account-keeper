package com.dwarfeng.acckeeper.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 密码错误异常。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public class PasswordIncorrectException extends HandlerException {

    private static final long serialVersionUID = 4213452404348843126L;

    private final StringIdKey accountKey;

    public PasswordIncorrectException(StringIdKey accountKey) {
        this.accountKey = accountKey;
    }

    public PasswordIncorrectException(Throwable cause, StringIdKey accountKey) {
        super(cause);
        this.accountKey = accountKey;
    }

    @Override
    public String getMessage() {
        return "账户 " + accountKey + " 提供了错误的密码";
    }
}
