package com.csit.quartz.mapper;

import com.csit.quartz.domain.SysJob;

import java.util.List;

/**
 * 调度任务信息 数据层
 * 
 * @author csit
 */
public interface GroupDataMapper
{


    /**
     * 批量删除当月数据
     * 
     * @param
     * @return 结果
     */
    public int delete_device_year();

    public int  delete_device_month();
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


    /**
     * 批量删除当月数据
     *
     * @param
     * @return 结果
     */
    public int delete_wechart_device_year();

    public int delete_wechart_device_month();
    /**
     * 天数据汇总到月表
     *
     * @param
     * @return 结果
     */
    public int InsertData_month_wechart();

    /**
     * 月数据汇总到年
     *
     * @param
     * @return 结果
     */
    public int InsertData_Year_wechart();
}
