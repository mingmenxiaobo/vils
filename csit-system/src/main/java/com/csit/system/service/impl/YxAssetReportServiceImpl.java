package com.csit.system.service.impl;

import com.csit.system.domain.YxAssetinforeport;
import com.csit.system.mapper.YxAssetReportMapper;
import com.csit.system.service.IYxAssetReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 产品信息Service业务层处理
 * 
 * @author csit
 * @date 2019-12-14
 */
@Service
public class YxAssetReportServiceImpl implements IYxAssetReportService
{
    @Autowired
    private YxAssetReportMapper yxReportMapper;


    @Override
    public List<YxAssetinforeport> AssetUseInfoReport(YxAssetinforeport Assetreport)
    {
        return yxReportMapper.AssetUseInfoReport(Assetreport);
    }

    @Override
    public List<YxAssetinforeport> getViewCode(YxAssetinforeport YxAssetreport) {
        return yxReportMapper.getViewCode(YxAssetreport);
    }

    @Override
    public List<YxAssetinforeport> getCreateTime(YxAssetinforeport YxAssetreport) {
        return yxReportMapper.getCreateTime(YxAssetreport);
    }

    @Override
    public YxAssetinforeport getAvgUsep(String RepDate) {
        return yxReportMapper.getAvgUsep(RepDate);
    }


}
