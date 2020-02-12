package com.dwarfeng.acckeeper.impl.bean.entity;

import com.dwarfeng.acckeeper.sdk.util.Constraints;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Optional;

@Entity
@IdClass(HibernateStringIdKey.class)
@Table(name = "tbl_account")
public class HibernateAccount implements Bean {

    private static final long serialVersionUID = 6069180993822366758L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", length = Constraints.LENGTH_ID, nullable = false, unique = true)
    private String stringId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "password", columnDefinition = "CHAR(" + Constraints.LENGTH_PASSWORD + ")", nullable = false)
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    @Column(name = "serial_version", nullable = false)
    private long serialVersion;

    public HibernateAccount() {
    }

    public HibernateStringIdKey getKey() {
        return Optional.ofNullable(stringId).map(HibernateStringIdKey::new).orElse(null);
    }

    public void setKey(HibernateStringIdKey uuidKey) {
        this.stringId = Optional.ofNullable(uuidKey).map(HibernateStringIdKey::getStringId).orElse(null);
    }

    public String getStringId() {
        return stringId;
    }

    public void setStringId(String stringId) {
        this.stringId = stringId;
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
        return "HibernateAccount{" +
                "stringId='" + stringId + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", remark='" + remark + '\'' +
                ", serialVersion=" + serialVersion +
                '}';
    }
}
