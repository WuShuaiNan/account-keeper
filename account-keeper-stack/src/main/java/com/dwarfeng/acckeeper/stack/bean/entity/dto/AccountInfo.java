package com.dwarfeng.acckeeper.stack.bean.entity.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

public class AccountInfo implements Dto {

    private static final long serialVersionUID = -6684230566247375168L;

    private StringIdKey key;
    private boolean enabled;
    private String remark;
    /**
     * @since 1.3.1
     */
    private String displayName;

    public AccountInfo() {
    }

    public AccountInfo(StringIdKey key, boolean enabled, String remark, String displayName) {
        this.key = key;
        this.enabled = enabled;
        this.remark = remark;
        this.displayName = displayName;
    }

    public StringIdKey getKey() {
        return key;
    }

    public void setKey(StringIdKey key) {
        this.key = key;
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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return "AccountInfo{" +
                "key=" + key +
                ", enabled=" + enabled +
                ", remark='" + remark + '\'' +
                ", displayName='" + displayName + '\'' +
                '}';
    }
}
