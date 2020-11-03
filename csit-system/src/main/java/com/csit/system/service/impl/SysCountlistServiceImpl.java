package com.csit.system.service.impl;

import com.csit.common.core.text.Convert;
import com.csit.common.exception.BusinessException;
import com.csit.common.utils.StringUtils;
import com.csit.common.utils.security.Md5Utils;
import com.csit.system.domain.*;
import com.csit.system.mapper.SysCountlistMapper;
import com.csit.system.mapper.SysDeptMapper;
import com.csit.system.service.ISysCountlistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysCountlistServiceImpl implements ISysCountlistService {

    private static final Logger log = LoggerFactory.getLogger(SysCountlistServiceImpl.class);
    @Autowired
    private SysCountlistMapper sysCountlistMapper;
    @Override
    public List<SysStudent> selectStudentList(SysStudent sysStudent) {
        return sysCountlistMapper.selectStudentList(sysStudent);
    }

    @Override
    public SysStudent selectStudentById(String studentid) {
        return sysCountlistMapper.selectStudentById(studentid);
    }

    @Override
    public int insertStudentData(SysStudent sysStudent) {
        return sysCountlistMapper.insertStudentData(sysStudent);
    }

    @Override
    public List<SysStudentDetail> selectStudentDataList(SysStudentDetail sysStudentDetail) {
        return sysCountlistMapper.selectStudentDataList(sysStudentDetail);
    }


    /**
     * 导入用户数据
     *
     * @param studentList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param UserId 操作用户ID
     * @param updateBy 操作用户
     * @return 结果
     */
    @Override
    public String importStudent(List<SysStudent> studentList, Boolean isUpdateSupport, String UserId,String updateBy)
    {
        if (StringUtils.isNull(studentList) || studentList.size() == 0)
        {
            throw new BusinessException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (SysStudent student : studentList)
        {
            try
            {
                // 验证是否存在这个学生
                SysStudent u = sysCountlistMapper.selectStudentByCode(student.getStudentCode());
                List<Map> d=sysCountlistMapper.selectStudentDept(UserId);
                if (StringUtils.isNull(u))
                {
                    student.setUserId(UserId);
                    student.setCreateBy(updateBy);
                    student.setStudentCity(d.get(0).get("dept_id").toString());
                    student.setStudentArea(d.get(1).get("dept_id").toString());
                    student.setStudentSchool(d.get(2).get("dept_id").toString());
                    student.setStudentGrade(d.get(3).get("dept_id").toString());
                    student.setStudentClass(UserId);
                    this.insertStudentData(student);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、学生 " + student.getStudentName() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    student.setUserId(UserId);
                    student.setCreateBy(updateBy);
                    student.setStudentid(u.getStudentid());
                    this.updateStudent(student);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、学生 " + student.getStudentName() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、学生 " + student.getStudentName() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、学生 " + student.getStudentName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    @Override
    public SysStudent selectStudentByCode(String studentCode) {
        return sysCountlistMapper.selectStudentByCode(studentCode);
    }

    @Override
    public int updateStudent(SysStudent sysStudent) {
        return sysCountlistMapper.updateStudent(sysStudent);
    }

    @Override
    public int insertStudentDetails(SysStudentDetail sysStudentDetail) {
        return sysCountlistMapper.insertStudentDetails(sysStudentDetail);
    }

    @Override
    public List<Map> selectStudentDept(String userId) {
        return sysCountlistMapper.selectStudentDept(userId);
    }


    @Override
    public String importStudent2(List<SysStudentDetail> studentDetail, Boolean isUpdateSupport2, String UserId,String updateBy)
    {
        if (StringUtils.isNull(studentDetail) || studentDetail.size() == 0)
        {
            throw new BusinessException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (SysStudentDetail student : studentDetail)
        {
            try
            {
                student.setCreateBy(updateBy);
                this.insertStudentDetails(student);
                successNum++;
                successMsg.append("<br/>" + successNum + "、学生 " + student.getStudentName() + " 导入成功");
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、学生 " + student.getStudentName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    @Override
    public int deleteStudentByIds(String id) throws BusinessException {

        String[] ids = Convert.toStrArray(id);
        return sysCountlistMapper.deleteStudentByIds(ids);
    }

    @Override
    public List<Map> selectStudentVision(String studentCode) {
        return sysCountlistMapper.selectStudentVision(studentCode);
    }

    @Override
    public List<SysSchool> selectSchoolVision() {
        return sysCountlistMapper.selectSchoolVision();
    }

    @Override
    public List<SysGrade> selectGradeVision(SysGrade sysGrade) {
        return sysCountlistMapper.selectGradeVision(sysGrade);
    }

    @Override
    public int checkSchool(SysGrade sysGrade) {
        return sysCountlistMapper.checkSchool(sysGrade);
    }

    @Override
    public SysGrade selectVisionStandard() {
        return sysCountlistMapper.selectVisionStandard();
    }

    @Override
    public int updateVisionStandard(SysGrade sysGrade) {
        return sysCountlistMapper.updateVisionStandard(sysGrade);
    }

    @Override
    public List<SysClass> selectClassVision(SysClass sysClass) {
        return sysCountlistMapper.selectClassVision(sysClass);
    }

    @Override
    public int checkGrade(SysClass sysClass) {
        return sysCountlistMapper.checkGrade(sysClass);
    }

    @Override
    public List<SysTrend> selectSchoolVisionTrend() {
        return sysCountlistMapper.selectSchoolVisionTrend();
    }

    @Override
    public List<SysTrend> selectSchoolName() {
        return sysCountlistMapper.selectSchoolName();
    }

    @Override
    public List<SysTrend> selectSchoolVisionTrendBystudentSchool(String studentSchool) {
        return sysCountlistMapper.selectSchoolVisionTrendBystudentSchool(studentSchool);
    }

    @Override
    public List<SysClassTrend> selectClassVisionTrendByUserId(SysClassTrend sysClassTrend) {
        return sysCountlistMapper.selectClassVisionTrendByUserId(sysClassTrend);
    }

    @Override
    public List<SysClassTrend> selectClassName(SysClassTrend sysClassTrend) {
        return sysCountlistMapper.selectClassName(sysClassTrend);
    }

    @Override
    public void insertmonthdetails() {
        sysCountlistMapper.insertmonthdetails();
    }

    @Override
    public List<Map> selectStudentClass() {
        return sysCountlistMapper.selectStudentClass();
    }

    @Override
    public void insertClassMonthDetails(int studentClass) {
        sysCountlistMapper.insertClassMonthDetails(studentClass);
    }


}
