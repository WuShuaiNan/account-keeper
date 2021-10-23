package com.dwarfeng.acckeeper.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 账户不存在异常。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public class AccountNotExistsException extends HandlerException {

    private static final long serialVersionUID = -3484693229747023645L;

    private final StringIdKey accountKey;

    public AccountNotExistsException(StringIdKey accountKey) {
        this.accountKey = accountKey;
    }

    public AccountNotExistsException(Throwable cause, StringIdKey accountKey) {
        super(cause);
        this.accountKey = accountKey;
    }

    @Override
    public String getMessage() {
        return "账户 " + accountKey + " 不存在";
    }
}
