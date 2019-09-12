package com.glacier.frame.entity.basicdatas;

import java.util.Date;

public class ParComDeliverType {
    private String deliverTypeId;

    private String code;

    private String deliverTypeName;

    private Integer sequenced;

    private String enabled;

    private String creater;

    private Date createTime;

    private String updater;

    private Date updateTime;

    public String getDeliverTypeId() {
        return deliverTypeId;
    }

    public void setDeliverTypeId(String deliverTypeId) {
        this.deliverTypeId = deliverTypeId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDeliverTypeName() {
        return deliverTypeName;
    }

    public void setDeliverTypeName(String deliverTypeName) {
        this.deliverTypeName = deliverTypeName;
    }

    public Integer getSequenced() {
        return sequenced;
    }

    public void setSequenced(Integer sequenced) {
        this.sequenced = sequenced;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ParComDeliverType other = (ParComDeliverType) that;
        return (this.getDeliverTypeId() == null ? other.getDeliverTypeId() == null : this.getDeliverTypeId().equals(other.getDeliverTypeId()))
            && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getDeliverTypeName() == null ? other.getDeliverTypeName() == null : this.getDeliverTypeName().equals(other.getDeliverTypeName()))
            && (this.getSequenced() == null ? other.getSequenced() == null : this.getSequenced().equals(other.getSequenced()))
            && (this.getEnabled() == null ? other.getEnabled() == null : this.getEnabled().equals(other.getEnabled()))
            && (this.getCreater() == null ? other.getCreater() == null : this.getCreater().equals(other.getCreater()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdater() == null ? other.getUpdater() == null : this.getUpdater().equals(other.getUpdater()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDeliverTypeId() == null) ? 0 : getDeliverTypeId().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getDeliverTypeName() == null) ? 0 : getDeliverTypeName().hashCode());
        result = prime * result + ((getSequenced() == null) ? 0 : getSequenced().hashCode());
        result = prime * result + ((getEnabled() == null) ? 0 : getEnabled().hashCode());
        result = prime * result + ((getCreater() == null) ? 0 : getCreater().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdater() == null) ? 0 : getUpdater().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}