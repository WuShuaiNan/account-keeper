package com.dwarfeng.acckeeper.sdk.util;

import com.dwarfeng.acckeeper.stack.bean.entity.Account;

/**
 * 账户工具类。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public final class AccountUtil {

    /**
     * 增加指定账户的序列号。
     *
     * @param account 指定的账户。
     */
    public static void increaseSerial(Account account) {
        account.setSerialVersion(account.getSerialVersion() + 1);
    }

    private AccountUtil() {
        throw new IllegalStateException("禁止实例化");
    }
}
