package com.dwarfeng.acckeeper.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

public class FastJsonLoginState implements Bean {

    private static final long serialVersionUID = 6905371931123253056L;

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "account_key", ordinal = 2)
    private FastJsonStringIdKey accountKey;

    @JSONField(name = "expire_date", ordinal = 3)
    private long expireDate;

    @JSONField(name = "serial_version", ordinal = 4)
    private long serialVersion;

    public FastJsonLoginState() {
    }

    public FastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonLongIdKey key) {
        this.key = key;
    }

    public FastJsonStringIdKey getAccountKey() {
        return accountKey;
    }

    public void setAccountKey(FastJsonStringIdKey accountKey) {
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
        return "FastJsonLoginState{" +
                "key=" + key +
                ", accountKey=" + accountKey +
                ", expireDate=" + expireDate +
                ", serialVersion=" + serialVersion +
                '}';
    }
}
