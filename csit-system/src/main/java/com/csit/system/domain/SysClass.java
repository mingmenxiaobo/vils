package com.csit.system.domain;

import com.csit.common.annotation.Excel;
import com.csit.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SysClass extends BaseEntity {

    private String userId;
    @Excel(name = "视力正常人数",type = Excel.Type.EXPORT)
    private String countaaa;
    @Excel(name = "每天使用电子设备超4小时人数",type = Excel.Type.EXPORT)
    private String countbbb;
    @Excel(name = "班级",type = Excel.Type.EXPORT)
    private String studentClass;

    private String visionStandard;
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("userId", getUserId())
                .append("countaaa", getCountaaa())
                .append("countbbb", getCountbbb())
                .append("studentClass", getStudentClass())
                .append("visionStandard", getVisionStandard())
                .toString();
    }



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCountaaa() {
        return countaaa;
    }

    public void setCountaaa(String countaaa) {
        this.countaaa = countaaa;
    }

    public String getCountbbb() {
        return countbbb;
    }

    public void setCountbbb(String countbbb) {
        this.countbbb = countbbb;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public String getVisionStandard() {
        return visionStandard;
    }

    public void setVisionStandard(String visionStandard) {
        this.visionStandard = visionStandard;
    }
}
