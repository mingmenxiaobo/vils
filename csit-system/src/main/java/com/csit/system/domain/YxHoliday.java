package com.csit.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.csit.common.annotation.Excel;
import com.csit.common.core.domain.BaseEntity;

/**
 * 节假日对象 yx_holiday
 * 
 * @author csit
 * @date 2020-05-01
 */
public class YxHoliday extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long id;

    /** 部门 */
    @Excel(name = "部门")
    private String dept;

    /** 天 */
    @Excel(name = "天")
    private String repdate;

    /** 年 */
    @Excel(name = "年")
    private Long repyear;

    /** 月 */
    @Excel(name = "月")
    private Long repmonth;

    /** 天 */
    @Excel(name = "天")
    private Long repday;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setDept(String dept) 
    {
        this.dept = dept;
    }

    public String getDept() 
    {
        return dept;
    }
    public void setRepdate(String repdate) 
    {
        this.repdate = repdate;
    }

    public String getRepdate() 
    {
        return repdate;
    }
    public void setRepyear(Long repyear) 
    {
        this.repyear = repyear;
    }

    public Long getRepyear() 
    {
        return repyear;
    }
    public void setRepmonth(Long repmonth) 
    {
        this.repmonth = repmonth;
    }

    public Long getRepmonth() 
    {
        return repmonth;
    }
    public void setRepday(Long repday) 
    {
        this.repday = repday;
    }

    public Long getRepday() 
    {
        return repday;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("dept", getDept())
            .append("repdate", getRepdate())
            .append("repyear", getRepyear())
            .append("repmonth", getRepmonth())
            .append("repday", getRepday())
            .append("status", getStatus())
            .toString();
    }
}
