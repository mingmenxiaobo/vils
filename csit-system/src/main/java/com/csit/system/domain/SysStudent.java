package com.csit.system.domain;

import com.csit.common.annotation.Excel;
import com.csit.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

public class SysStudent extends BaseEntity {
    private String studentCity;
    private String studentArea;
    @Excel(name = "学校",type = Excel.Type.EXPORT)
    private String studentSchool;
    @Excel(name = "年级",type = Excel.Type.EXPORT)
    private String studentGrade;
    @Excel(name = "班级",type = Excel.Type.EXPORT)
    private String studentClass;
    @Excel(name = "姓名")
    private String studentName;
    private int studentid;
    @Excel(name = "学号")
    private String studentCode;
    @Excel(name = "性别")
    private String sex;
    @Excel(name = "出生日期,格式为：yyyy.mm.dd",prompt = "格式为：yyyy.mm.dd",width=30)
    private String birthday;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
    @Excel(name = "入校时间,格式为：yyyy.mm.dd",prompt = "格式为：yyyy.mm.dd",width=30)
    private String schoolTime;

    private String userId;



    @Excel(name = "视力正常人数",type = Excel.Type.EXPORT)
    private String counta;
    @Excel(name = "视力异常人数",type = Excel.Type.EXPORT)
    private String countb;
    @Excel(name = "学校",type = Excel.Type.EXPORT)
    private String school;
    @Excel(name = "视力正常人数",type = Excel.Type.EXPORT)
    private String countaa;
    @Excel(name = "每天使用电子设备超4小时人数",type = Excel.Type.EXPORT)
    private String countbb;
    @Excel(name = "年级",type = Excel.Type.EXPORT)
    private String grade;
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("studentid", getStudentid())
                .append("studentCode", getStudentCode())
                .append("studentName", getStudentName())
                .append("birthday", getBirthday())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("schoolTime", getSchoolTime())
                .append("sex", getSex())
                .append("studentCity", getStudentCity())
                .append("studentArea", getStudentArea())
                .append("studentSchool", getStudentSchool())
                .append("studentGrade", getStudentGrade())
                .append("studentClass", getStudentClass())
                .append("counta", getCounta())
                .append("countb", getCountb())
                .append("school", getSchool())
                .append("countaa", getCountaa())
                .append("countbb", getCountbb())
                .append("grade", getGrade())
                .toString();
    }

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getSchoolTime() {
        return schoolTime;
    }

    public void setSchoolTime(String schoolTime) {
        this.schoolTime = schoolTime;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
}
