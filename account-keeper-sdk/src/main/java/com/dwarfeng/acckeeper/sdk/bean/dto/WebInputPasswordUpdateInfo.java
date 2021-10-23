package com.dwarfeng.acckeeper.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.acckeeper.stack.bean.dto.PasswordUpdateInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 密码更新信息。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public class WebInputPasswordUpdateInfo implements Dto {

    private static final long serialVersionUID = -4892211741936759439L;

    public static PasswordUpdateInfo toStackBean(WebInputPasswordUpdateInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new PasswordUpdateInfo(
                    WebInputStringIdKey.toStackBean(webInput.getAccountKey()), webInput.getOldPassword(),
                    webInput.getNewPassword()
            );
        }
    }

    @JSONField(name = "account_key")
    @Valid
    private WebInputStringIdKey accountKey;

    @JSONField(name = "old_password")
    @NotEmpty
    @NotNull
    private String oldPassword;

    @JSONField(name = "new_password")
    @NotEmpty
    @NotNull
    private String newPassword;

    public WebInputPasswordUpdateInfo() {
    }

    public WebInputStringIdKey getAccountKey() {
        return accountKey;
    }

    public void setAccountKey(WebInputStringIdKey accountKey) {
        this.accountKey = accountKey;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "WebInputPasswordUpdateInfo{" +
                "accountKey=" + accountKey +
                ", oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
