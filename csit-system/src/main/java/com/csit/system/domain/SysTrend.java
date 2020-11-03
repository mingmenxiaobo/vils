package com.csit.system.domain;

import com.csit.common.annotation.Excel;
import com.csit.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

public class SysTrend extends BaseEntity {
    private int id;
    private String studentCity;
    private String studentArea;
    private String studentSchool;
    private String studentGrade;
    private String studentClass;
    private String studentName;
    private String studentCode;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
    private String userId;
    public String checkdate;
    public String lefteyeVision;
    public String righteyeVision;
    public String time;
    @Excel(name = "月份",type = Excel.Type.EXPORT)
    public String month;
    @Excel(name = "视力异常人数(任意眼视力低于视力标准)",type = Excel.Type.EXPORT,width = 40)
    public String number;
    @Excel(name = "学校",type = Excel.Type.EXPORT)
    public String deptName;

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

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    @Override
    public String getCreateBy() {
        return createBy;
    }

    @Override
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String getUpdateBy() {
        return updateBy;
    }

    @Override
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCheckdate() {
        return checkdate;
    }

    public void setCheckdate(String checkdate) {
        this.checkdate = checkdate;
    }

    public String getLefteyeVision() {
        return lefteyeVision;
    }

    public void setLefteyeVision(String lefteyeVision) {
        this.lefteyeVision = lefteyeVision;
    }

    public String getRighteyeVision() {
        return righteyeVision;
    }

    public void setRighteyeVision(String righteyeVision) {
        this.righteyeVision = righteyeVision;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("studentCode", getStudentCode())
                .append("studentName", getStudentName())
                .append("checkdate", getCheckdate())
                .append("lefteyeVision", getLefteyeVision())
                .append("righteyeVision", getRighteyeVision())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("time", getTime())
                .append("month", getMonth())
                .append("studentCity", getStudentCity())
                .append("studentArea", getStudentArea())
                .append("studentSchool", getStudentSchool())
                .append("studentGrade", getStudentGrade())
                .append("studentClass", getStudentClass())
                .append("number", getNumber())
                .append("deptName", getDeptName())
                .toString();
    }
}
