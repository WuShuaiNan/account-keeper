package com.dwarfeng.acckeeper.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.acckeeper.stack.bean.dto.PasswordResetInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * 密码重置信息。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public class WebInputPasswordResetInfo implements Dto {

    private static final long serialVersionUID = -5883447679487771489L;

    public static PasswordResetInfo toStackBean(WebInputPasswordResetInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new PasswordResetInfo(
                    WebInputStringIdKey.toStackBean(webInput.getAccountKey()), webInput.getNewPassword()
            );
        }
    }

    @JSONField(name = "account_key")
    @Valid
    private WebInputStringIdKey accountKey;

    @JSONField(name = "new_password")
    @NotEmpty
    @NotNull
    private String newPassword;

    public WebInputPasswordResetInfo() {
    }

    public WebInputStringIdKey getAccountKey() {
        return accountKey;
    }

    public void setAccountKey(WebInputStringIdKey accountKey) {
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
        return "WebInputPasswordResetInfo{" +
                "accountKey=" + accountKey +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
