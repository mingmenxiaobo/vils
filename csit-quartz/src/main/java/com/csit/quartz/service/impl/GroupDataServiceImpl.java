package com.csit.quartz.service.impl;

import com.csit.common.constant.ScheduleConstants;
import com.csit.common.core.text.Convert;
import com.csit.common.exception.job.TaskException;
import com.csit.quartz.domain.SysJob;
import com.csit.quartz.mapper.GroupDataMapper;
import com.csit.quartz.mapper.SysJobMapper;
import com.csit.quartz.service.IGroupDataService;
import com.csit.quartz.service.ISysJobService;
import com.csit.quartz.util.CronUtils;
import com.csit.quartz.util.ScheduleUtils;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 定时任务调度信息 服务层
 * 
 * @author csit
 */
@Service
public class GroupDataServiceImpl implements IGroupDataService
{
    @Autowired
    private GroupDataMapper gdata;




    /**
     * 天数据汇总到月表
     *
     * @param
     * @return 结果
     */
    @Override
    @Transactional
    public int InsertData_month()
    {
        gdata.delete_device_month();

        int rows = gdata.InsertData_month();
        return rows;
    }

    /**
     * 月数据汇总到年
     *
     * @param
     * @return 结果
     */
    @Override
    @Transactional
    public int InsertData_year()
    {
        gdata.delete_device_year();

         int  rows = gdata.InsertData_year();

        return rows;
    }


    public int InsertData_month_wechart()
    {
        gdata.delete_wechart_device_month();

        int  rows = gdata.InsertData_month_wechart();

        return rows;
    }

    /**
     * 月数据汇总到年
     *
     * @param
     * @return 结果
     */
    public int InsertData_Year_wechart()
    {
        gdata.delete_wechart_device_year();

        int  rows = gdata.InsertData_Year_wechart();

        return rows;
    }

}