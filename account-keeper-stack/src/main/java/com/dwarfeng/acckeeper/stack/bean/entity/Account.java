package com.dwarfeng.acckeeper.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 账户。
 *
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public class Account implements Entity<StringIdKey> {

    private static final long serialVersionUID = -7041696490265336859L;

    private StringIdKey key;
    private String password;
    private boolean enabled;
    private String remark;
    private long serialVersion;
    /**
     * @since 1.3.1
     */
    private String displayName;

    public Account() {
    }

    public Account(
            StringIdKey key, String password, boolean enabled, String remark, long serialVersion, String displayName
    ) {
        this.key = key;
        this.password = password;
        this.enabled = enabled;
        this.remark = remark;
        this.serialVersion = serialVersion;
        this.displayName = displayName;
    }

    @Override
    public StringIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(StringIdKey key) {
        this.key = key;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public long getSerialVersion() {
        return serialVersion;
    }

    public void setSerialVersion(long serialVersion) {
        this.serialVersion = serialVersion;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return "Account{" +
                "key=" + key +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", remark='" + remark + '\'' +
                ", serialVersion=" + serialVersion +
                ", displayName='" + displayName + '\'' +
                '}';
    }
}
