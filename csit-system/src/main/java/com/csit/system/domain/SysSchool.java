package com.csit.system.domain;

import com.csit.common.annotation.Excel;
import com.csit.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

public class SysSchool extends BaseEntity {


    private String userId;
    @Excel(name = "视力正常人数",type = Excel.Type.EXPORT)
    private String counta;
    @Excel(name = "视力异常人数",type = Excel.Type.EXPORT)
    private String countb;
    @Excel(name = "学校",type = Excel.Type.EXPORT)
    private String school;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("userId", getUserId())
                .append("counta", getCounta())
                .append("countb", getCountb())
                .append("school", getSchool())
                .toString();
    }



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCounta() {
        return counta;
    }

    public void setCounta(String counta) {
        this.counta = counta;
    }

    public String getCountb() {
        return countb;
    }

    public void setCountb(String countb) {
        this.countb = countb;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }


}
