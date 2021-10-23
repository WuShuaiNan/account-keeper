package com.dwarfeng.acckeeper.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 密码重置信息。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public class PasswordResetInfo implements Dto {

    private static final long serialVersionUID = -7397545741501745824L;

    private StringIdKey accountKey;
    private String newPassword;

    public PasswordResetInfo() {
    }

    public PasswordResetInfo(StringIdKey accountKey, String newPassword) {
        this.accountKey = accountKey;
        this.newPassword = newPassword;
    }

    public StringIdKey getAccountKey() {
        return accountKey;
    }

    public void setAccountKey(StringIdKey accountKey) {
        this.accountKey = accountKey;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "PasswordResetInfo{" +
                "accountKey=" + accountKey +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
