package com.glacier.frame.dto.query.system;

import com.glacier.frame.entity.system.Company;
import com.glacier.frame.entity.system.CompanyExample;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.Date;

/**
 * @ClassName: CompanyQueryDTO
 * @Description: TODO(公司查询DTO)
 * @author zhenfei.zhang
 * @email 289556866@qq.com
 * @date 2014-2-20 下午2:26:17
 */
public class CompanyQueryDTO extends Company {

    private Date createStartTime;

    private Date createEndTime;

    public Date getCreateStartTime() {
        return createStartTime;
    }

    public void setCreateStartTime(Date createStartTime) {
        this.createStartTime = createStartTime;
    }

    public Date getCreateEndTime() {
        return createEndTime;
    }

    public void setCreateEndTime(Date createEndTime) {
        this.createEndTime = createEndTime;
    }

    public void setQueryCondition(CompanyExample.Criteria queryCriteria) {
        if (null != this.getCeoId() && StringUtils.isNotBlank(this.getCeoId())) {//公司CEO ID查询
            queryCriteria.andCeoIdEqualTo(this.getCeoId());
        }
        if (null != this.getCompanyCnName() && StringUtils.isNotBlank(this.getCompanyCnName())){//公司中文名Like查询
            queryCriteria.andCompanyCnNameLike("%" + this.getCompanyCnName() + "%");
        }
        if (null != createStartTime && null != createEndTime) {//创建时间段查询
            queryCriteria.andCreateTimeBetween(createStartTime, createEndTime);
        } else {
            if (null != createStartTime) {
                queryCriteria.andCreateTimeGreaterThanOrEqualTo(createStartTime);
            }
            if (null != createEndTime) {
                queryCriteria.andCreateTimeLessThanOrEqualTo(createEndTime);
            }

        }
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
