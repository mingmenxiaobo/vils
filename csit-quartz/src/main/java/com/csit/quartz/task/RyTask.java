package com.csit.quartz.task;

import com.csit.quartz.service.IGroupDataService;
import com.csit.quartz.service.ISysJobService;
import com.csit.system.service.ISysCountlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.csit.common.utils.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 定时任务调度测试
 * 
 * @author csit
 */
@Component("ryTask")
public class RyTask
{
    @Autowired
    private IGroupDataService groupService;

    @Autowired
    ISysCountlistService sysCountlistService;

    public void ryMultipleParams(String s, Boolean b, Long l, Double d, Integer i)
    {
        System.out.println(StringUtils.format("执行多参方法： 字符串类型{}，布尔类型{}，长整型{}，浮点型{}，整形{}", s, b, l, d, i));
    }

    public void ryParams(String params)
    {
        System.out.println("执行有参方法：" + params);
    }

    public void ryNoParams()
    {
        System.out.println("执行无参方法");
    }

    public void runEveryDay()
    {
            System.out.println("每天执行一次，数据汇总");
            groupService.InsertData_month();
            groupService.InsertData_year();
            System.out.println("每天执行一次，微信小程序账号数据汇总");
            groupService.InsertData_month_wechart();
    }

    public void runEveryMonth()
    {
            System.out.println("每天执行一次，微信小程序账号数据汇总");
            groupService.InsertData_Year_wechart();
    }

    public void runEveryMinute()
    {
        System.out.println("每分执行一次，查询*****************");
    }

    public void runEveryMonthSchool()
    {
       System.out.println(" 0 59 23 L * ? ");
       System.out.println("每月最后一天执行一次，插入学校学生数据***************");
       sysCountlistService.insertmonthdetails();
    }

    public void runEveryMonthClass()
    {
        System.out.println(" 0 59 23 L * ? ");
        System.out.println("每月最后一天执行一次，插入班级总体数据****************");
        List<Map> mapList=sysCountlistService.selectStudentClass();
        for(int i=0;i<mapList.size();i++){
            int studentClass=Integer.parseInt(mapList.get(i).get("student_class").toString());
            sysCountlistService.insertClassMonthDetails(studentClass);
        }
    }

}
