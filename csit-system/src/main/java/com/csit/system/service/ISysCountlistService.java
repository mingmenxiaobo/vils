package com.csit.system.service;

import com.csit.system.domain.*;

import java.util.List;
import java.util.Map;

public interface ISysCountlistService {

    public List<SysStudent> selectStudentList(SysStudent sysStudent);

    public SysStudent selectStudentById(String studentid);

    public int insertStudentData(SysStudent sysStudent);

    public List<SysStudentDetail> selectStudentDataList(SysStudentDetail sysStudentDetail);


    /**
     * 导入用户数据
     *
     * @param studentList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param UserId 操作用户ID
     * @param updateBy 操作用户
     * @return 结果
     */
    public String importStudent(List<SysStudent> studentList, Boolean isUpdateSupport, String UserId,String updateBy);

    public SysStudent selectStudentByCode(String studentCode);

    public int updateStudent(SysStudent sysStudent);

    public int insertStudentDetails(SysStudentDetail sysStudentDetail);

    public List<Map> selectStudentDept(String userId);

    public String importStudent2(List<SysStudentDetail> studentDetail, Boolean isUpdateSupport2, String UserId,String updateBy);


    public int deleteStudentByIds(String id) throws Exception;

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
