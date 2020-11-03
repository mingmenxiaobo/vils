package com.csit.system.domain;

import com.csit.common.annotation.Excel;
import com.csit.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

public class SysGrade extends BaseEntity {

    private String userId;
    @Excel(name = "视力正常人数",type = Excel.Type.EXPORT)
    private String countaa;
    @Excel(name = "每天使用电子设备超4小时人数",type = Excel.Type.EXPORT)
    private String countbb;
    @Excel(name = "年级",type = Excel.Type.EXPORT)
    private String grade;

    private String visionStandard;
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("userId", getUserId())
                .append("countaa", getCountaa())
                .append("countbb", getCountbb())
                .append("grade", getGrade())
                .append("visionStandard", getVisionStandard())
                .toString();
    }



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCountaa() {
        return countaa;
    }

    public void setCountaa(String countaa) {
        this.countaa = countaa;
    }

    public String getCountbb() {
        return countbb;
    }

    public void setCountbb(String countbb) {
        this.countbb = countbb;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getVisionStandard() {
        return visionStandard;
    }

    public void setVisionStandard(String visionStandard) {
        this.visionStandard = visionStandard;
    }
}
