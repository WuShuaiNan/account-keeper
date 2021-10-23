package com.dwarfeng.acckeeper.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 账户注册信息。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public class AccountRegisterInfo implements Dto {

    private static final long serialVersionUID = 5502749975362049705L;

    private StringIdKey accountKey;
    private String displayName;
    private boolean enabled;
    private String remark;
    private String password;

    public AccountRegisterInfo() {
    }

    public AccountRegisterInfo(
            StringIdKey accountKey, String displayName, boolean enabled, String remark, String password
    ) {
        this.accountKey = accountKey;
        this.displayName = displayName;
        this.enabled = enabled;
        this.remark = remark;
        this.password = password;
    }

    public StringIdKey getAccountKey() {
        return accountKey;
    }

    public void setAccountKey(StringIdKey accountKey) {
        this.accountKey = accountKey;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AccountRegisterInfo{" +
                "accountKey=" + accountKey +
                ", displayName='" + displayName + '\'' +
                ", enabled=" + enabled +
                ", remark='" + remark + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
