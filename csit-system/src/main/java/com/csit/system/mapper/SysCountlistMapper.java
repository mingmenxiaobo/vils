package com.csit.system.mapper;

import com.csit.system.domain.*;

import java.util.List;
import java.util.Map;

public interface SysCountlistMapper<selectSchoolVisionTrend> {

    public List<SysStudent> selectStudentList(SysStudent sysStudent);

    public SysStudent selectStudentById(String studentid);

    public int insertStudentData(SysStudent sysStudent);

    public List<SysStudentDetail> selectStudentDataList(SysStudentDetail sysStudentDetail);

    public SysStudent selectStudentByCode(String studentCode);

    public int updateStudent(SysStudent sysStudent);

    public int insertStudentDetails(SysStudentDetail sysStudentDetail);

    public List<Map> selectStudentDept(String userId);

    public int deleteStudentByIds(String[] id);

    public List<Map> selectStudentVision(String studentCode);

    public List<SysSchool> selectSchoolVision();

    public List<SysGrade> selectGradeVision(SysGrade sysGrade);

    public int checkSchool(SysGrade sysGrade);

    public SysGrade selectVisionStandard();

    public int updateVisionStandard(SysGrade sysGrade);


    public List<SysClass> selectClassVision(SysClass sysClass);

    public int checkGrade(SysClass sysClass);

    public  List<SysTrend> selectSchoolVisionTrend();

    public  List<SysTrend> selectSchoolName();

    public List<SysTrend> selectSchoolVisionTrendBystudentSchool(String studentSchool);

    public List<SysClassTrend> selectClassVisionTrendByUserId(SysClassTrend sysClassTrend);

    public  List<SysClassTrend> selectClassName(SysClassTrend sysClassTrend);

    public void insertmonthdetails();

    public List<Map> selectStudentClass();

    public void insertClassMonthDetails(int studentClass);

}
