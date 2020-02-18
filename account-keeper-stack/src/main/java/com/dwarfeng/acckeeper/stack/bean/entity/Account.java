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

    private static final long serialVersionUID = -7166808835407200393L;

    private StringIdKey key;
    private String password;
    private boolean enabled;
    private String remark;
    private long serialVersion;

    public Account() {
    }

    public Account(StringIdKey key, String password, boolean enabled, String remark, long serialVersion) {
        this.key = key;
        this.password = password;
        this.enabled = enabled;
        this.remark = remark;
        this.serialVersion = serialVersion;
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

    @Override
    public String toString() {
        return "Account{" +
                "key=" + key +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", remark='" + remark + '\'' +
                ", serialVersion=" + serialVersion +
                '}';
    }
}
