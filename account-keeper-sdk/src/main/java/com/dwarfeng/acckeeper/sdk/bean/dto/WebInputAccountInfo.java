package com.dwarfeng.acckeeper.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.acckeeper.sdk.util.Constraints;
import com.dwarfeng.acckeeper.stack.bean.entity.dto.AccountInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import java.util.Objects;

/**
 * WebInput 账户信息。
 *
 * @author DwArFeng
 * @since 1.3.1
 */
public class WebInputAccountInfo implements Dto {

    private static final long serialVersionUID = -4061133244039354247L;

    public static AccountInfo toStackBean(WebInputAccountInfo webInputAccountInfo) {
        if (Objects.isNull(webInputAccountInfo)) {
            return null;
        } else {
            return new AccountInfo(
                    WebInputStringIdKey.toStackBean(webInputAccountInfo.getKey()), webInputAccountInfo.isEnabled(),
                    webInputAccountInfo.getRemark(), webInputAccountInfo.getDisplayName()
            );
        }
    }

    @JSONField(name = "key")
    @Valid
    private WebInputStringIdKey key;

    @JSONField(name = "enabled")
    private boolean enabled;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    @JSONField(name = "display_name")
    @Length(max = Constraints.LENGTH_LABEL)
    private String displayName;

    public WebInputAccountInfo() {
    }

    public WebInputStringIdKey getKey() {
        return key;
    }

    public void setKey(WebInputStringIdKey key) {
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
        return "WebInputAccountInfo{" +
                "key=" + key +
                ", enabled=" + enabled +
                ", remark='" + remark + '\'' +
                ", displayName='" + displayName + '\'' +
                '}';
    }
}
