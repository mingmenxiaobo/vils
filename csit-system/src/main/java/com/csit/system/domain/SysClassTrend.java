package com.csit.system.domain;

import com.csit.common.annotation.Excel;
import com.csit.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

public class SysClassTrend extends BaseEntity {
    private int id;
    private String studentCity;
    private String studentArea;
    @Excel(name = "学校",type = Excel.Type.EXPORT)
    private String studentSchool;
    @Excel(name = "年级",type = Excel.Type.EXPORT)
    private String studentGrade;
    @Excel(name = "班级",type = Excel.Type.EXPORT)
    private String studentClass;
    private String userId;
    @Excel(name = "月份",type = Excel.Type.EXPORT)
    public String month;
    @Excel(name = "视力异常人数(任意眼视力低于视力标准)",type = Excel.Type.EXPORT,width = 40)
    public String number;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentCity() {
        return studentCity;
    }

    public void setStudentCity(String studentCity) {
        this.studentCity = studentCity;
    }

    public String getStudentArea() {
        return studentArea;
    }

    public void setStudentArea(String studentArea) {
        this.studentArea = studentArea;
    }

    public String getStudentSchool() {
        return studentSchool;
    }

    public void setStudentSchool(String studentSchool) {
        this.studentSchool = studentSchool;
    }

    public String getStudentGrade() {
        return studentGrade;
    }

    public void setStudentGrade(String studentGrade) {
        this.studentGrade = studentGrade;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("month", getMonth())
                .append("studentCity", getStudentCity())
                .append("studentArea", getStudentArea())
                .append("studentSchool", getStudentSchool())
                .append("studentGrade", getStudentGrade())
                .append("studentClass", getStudentClass())
                .append("number", getNumber())
                .append("userId", getUserId())
                .toString();
    }
}
