package com.dwarfeng.acckeeper.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 序列版本不一致异常。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public class SerialVersionInconsistentException extends HandlerException {

    private static final long serialVersionUID = 6438358351946185749L;

    private final LongIdKey loginStateKey;

    public SerialVersionInconsistentException(LongIdKey loginStateKey) {
        this.loginStateKey = loginStateKey;
    }

    public SerialVersionInconsistentException(Throwable cause, LongIdKey loginStateKey) {
        super(cause);
        this.loginStateKey = loginStateKey;
    }

    @Override
    public String getMessage() {
        return "登录状态 " + loginStateKey + " 的序列版本与账户中的序列版本不一致";
    }
}
