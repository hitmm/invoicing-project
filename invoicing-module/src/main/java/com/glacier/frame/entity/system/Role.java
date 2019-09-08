package com.glacier.frame.entity.system;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Role {
    private String roleId;

    private String roleEnName;

    private String roleCnName;

    private String builtin;

    private String remark;

    private String creater;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String companyId;

    private String companyCnName;

    private boolean checked;

    private String createrDisplay;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getRoleEnName() {
        return roleEnName;
    }

    public void setRoleEnName(String roleEnName) {
        this.roleEnName = roleEnName == null ? null : roleEnName.trim();
    }

    public String getRoleCnName() {
        return roleCnName;
    }

    public void setRoleCnName(String roleCnName) {
        this.roleCnName = roleCnName == null ? null : roleCnName.trim();
    }

    public String getBuiltin() {
        return builtin;
    }

    public void setBuiltin(String builtin) {
        this.builtin = builtin == null ? null : builtin.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getCompanyCnName() {
        return companyCnName;
    }

    public void setCompanyCnName(String companyCnName) {
        this.companyCnName = companyCnName;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getCreaterDisplay() {
        return createrDisplay;
    }

    public void setCreaterDisplay(String createrDisplay) {
        this.createrDisplay = createrDisplay;
    }
}