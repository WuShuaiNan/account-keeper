package com.dwarfeng.acckeeper.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 账户已经存在异常。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public class AccountAlreadyExistedException extends HandlerException {

    private static final long serialVersionUID = -207519617395485601L;

    private final StringIdKey accountKey;

    public AccountAlreadyExistedException(StringIdKey accountKey) {
        this.accountKey = accountKey;
    }

    public AccountAlreadyExistedException(Throwable cause, StringIdKey accountKey) {
        super(cause);
        this.accountKey = accountKey;
    }

    @Override
    public String getMessage() {
        return "账户 " + accountKey + " 已经存在";
    }
}
