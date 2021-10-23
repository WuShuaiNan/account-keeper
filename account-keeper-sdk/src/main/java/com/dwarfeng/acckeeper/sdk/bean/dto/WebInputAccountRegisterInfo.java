package com.dwarfeng.acckeeper.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.acckeeper.sdk.util.Constraints;
import com.dwarfeng.acckeeper.stack.bean.dto.AccountRegisterInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 账户注册信息。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public class WebInputAccountRegisterInfo implements Dto {

    private static final long serialVersionUID = 5728271245612502177L;

    public static AccountRegisterInfo toStackBean(WebInputAccountRegisterInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new AccountRegisterInfo(
                    WebInputStringIdKey.toStackBean(webInput.getAccountKey()), webInput.getDisplayName(),
                    webInput.isEnabled(), webInput.getRemark(), webInput.getPassword()
            );
        }
    }

    @JSONField(name = "account_key")
    @Valid
    private WebInputStringIdKey accountKey;

    @JSONField(name = "display_name")
    @Length(max = Constraints.LENGTH_LABEL)
    private String displayName;

    @JSONField(name = "enabled")
    private boolean enabled;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    @JSONField(name = "password")
    @NotNull
    @NotEmpty
    private String password;

    public WebInputAccountRegisterInfo() {
    }

    public WebInputStringIdKey getAccountKey() {
        return accountKey;
    }

    public void setAccountKey(WebInputStringIdKey accountKey) {
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
        return "WebInputAccountRegisterInfo{" +
                "accountKey=" + accountKey +
                ", displayName='" + displayName + '\'' +
                ", enabled=" + enabled +
                ", remark='" + remark + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
