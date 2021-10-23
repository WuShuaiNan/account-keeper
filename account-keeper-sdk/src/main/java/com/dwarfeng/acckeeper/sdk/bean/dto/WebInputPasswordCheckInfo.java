package com.dwarfeng.acckeeper.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.acckeeper.stack.bean.dto.PasswordCheckInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 密码检查信息。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
public class WebInputPasswordCheckInfo implements Dto {

    private static final long serialVersionUID = -2748930309157915673L;

    public static PasswordCheckInfo toStackBean(WebInputPasswordCheckInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new PasswordCheckInfo(
                    WebInputStringIdKey.toStackBean(webInput.getAccountKey()), webInput.getPassword()
            );
        }
    }

    @JSONField(name = "account_key")
    @Valid
    private WebInputStringIdKey accountKey;

    @JSONField(name = "password")
    @NotEmpty
    @NotNull
    private String password;

    public WebInputPasswordCheckInfo() {
    }

    public WebInputStringIdKey getAccountKey() {
        return accountKey;
    }

    public void setAccountKey(WebInputStringIdKey accountKey) {
        this.accountKey = accountKey;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "WebInputPasswordCheckInfo{" +
                "accountKey=" + accountKey +
                ", password='" + password + '\'' +
                '}';
    }
}
