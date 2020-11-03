package com.csit.system.domain;

import com.csit.common.annotation.Excel;
import com.csit.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

public class SysStudentDetail extends BaseEntity {
    public int id;
    @Excel(name = "学号",type = Excel.Type.EXPORT)
    public String studentCode;
    @Excel(name = "学生姓名",type = Excel.Type.EXPORT)
    public String studentName;
    public String birthday;
    @Excel(name = "检查日期",type = Excel.Type.EXPORT)
    public String checkdate;
    @Excel(name = "左眼视力",type = Excel.Type.EXPORT)
    public String lefteyeVision;
    @Excel(name = "右眼视力",type = Excel.Type.EXPORT)
    public String righteyeVision;
    public String create_by;
    public Date create_time;
    public String update_by;
    public Date update_time;
    @Excel(name = "每天使用电子设备时间",type = Excel.Type.EXPORT,width = 20)
    public String time;
    public String month;

    public SysStudent student;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("studentCode", getStudentCode())
                .append("studentName", getStudentName())
                .append("birthday", getBirthday())
                .append("checkdate", getCheckdate())
                .append("lefteyeVision", getLefteyeVision())
                .append("righteyeVision", getRighteyeVision())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("time", getTime())
                .append("month", getMonth())
                .append("student", getStudent())
                .toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public SysStudent getStudent() {
        return student;
    }

    public void setStudent(SysStudent student) {
        this.student = student;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
