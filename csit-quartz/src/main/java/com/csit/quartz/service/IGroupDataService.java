package com.csit.quartz.service;

import com.csit.common.exception.job.TaskException;
import com.csit.quartz.domain.SysJob;
import org.quartz.SchedulerException;

import java.util.List;

/**
 * 定时任务调度信息信息 服务层
 * 
 * @author csit
 */
public interface IGroupDataService
{


    /**
     * 天数据汇总到月表
     *
     * @param
     * @return 结果
     */
    public int InsertData_month();

    /**
     * 月数据汇总到年
     *
     * @param
     * @return 结果
     */
    public int InsertData_year();


    public int InsertData_month_wechart();

    /**
     * 月数据汇总到年
     *
     * @param
     * @return 结果
     */
    public int InsertData_Year_wechart();
}