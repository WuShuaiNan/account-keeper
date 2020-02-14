package com.dwarfeng.acckeeper.stack.bean.entity.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

public class RegisterInfo implements Dto {

    private static final long serialVersionUID = 3823420735812131351L;

    private String id;
    private String password;
    private String remark;

    public RegisterInfo() {
    }

    public RegisterInfo(String id, String password, String remark) {
        this.id = id;
        this.password = password;
        this.remark = remark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "RegisterInfo{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
