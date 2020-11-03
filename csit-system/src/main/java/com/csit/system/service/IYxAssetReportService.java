package com.csit.system.service;

import com.csit.system.domain.YxAssetinforeport;

import java.util.List;
import java.util.Map;

/**
 * 产品信息Service接口
 * 
 * @author csit
 * @date 2019-12-14
 */
public interface IYxAssetReportService
{
    public List<YxAssetinforeport> AssetUseInfoReport(YxAssetinforeport YxAssetreport);

    //获取设备名称
    public List<YxAssetinforeport> getViewCode(YxAssetinforeport YxAssetreport);

    //获取日期去除重复日期
    public List<YxAssetinforeport> getCreateTime(YxAssetinforeport YxAssetreport);

    //获取某个日期所有设备使用率的平均值
    public YxAssetinforeport getAvgUsep(String RepDate);


}
