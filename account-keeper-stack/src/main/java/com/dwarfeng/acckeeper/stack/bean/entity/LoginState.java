package com.dwarfeng.acckeeper.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 登录状态。
 *
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public class LoginState implements Entity<LongIdKey> {

    private static final long serialVersionUID = 1978695890873432131L;

    private LongIdKey key;
    private StringIdKey accountKey;
    private long expireDate;
    private long serialVersion;

    public LoginState() {
    }

    public LoginState(LongIdKey key, StringIdKey accountKey, long expireDate, long serialVersion) {
        this.key = key;
        this.accountKey = accountKey;
        this.expireDate = expireDate;
        this.serialVersion = serialVersion;
    }

    @Override
    public LongIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(LongIdKey key) {
        this.key = key;
    }

    public StringIdKey getAccountKey() {
        return accountKey;
    }

    public void setAccountKey(StringIdKey accountKey) {
        this.accountKey = accountKey;
    }

    public long getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(long expireDate) {
        this.expireDate = expireDate;
    }

    public long getSerialVersion() {
        return serialVersion;
    }

    public void setSerialVersion(long serialVersion) {
        this.serialVersion = serialVersion;
    }

    @Override
    public String toString() {
        return "LoginState{" +
                "key=" + key +
                ", accountKey=" + accountKey +
                ", expireDate=" + expireDate +
                ", serialVersion=" + serialVersion +
                '}';
    }
}
