package com.dwarfeng.acckeeper.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 账户被禁用异常。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public class AccountDisabledException extends HandlerException {

    private static final long serialVersionUID = -2564079630363901960L;

    private final StringIdKey accountKey;

    public AccountDisabledException(StringIdKey accountKey) {
        this.accountKey = accountKey;
    }

    public AccountDisabledException(Throwable cause, StringIdKey accountKey) {
        super(cause);
        this.accountKey = accountKey;
    }

    @Override
    public String getMessage() {
        return "账户 " + accountKey + " 被禁用";
    }
}
