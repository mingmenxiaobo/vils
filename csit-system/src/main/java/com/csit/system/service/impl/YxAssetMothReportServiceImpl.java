package com.csit.system.service.impl;

import com.csit.system.domain.YxAssetMothReport;
import com.csit.system.domain.YxAssetinforeport;
import com.csit.system.mapper.YxAssetMonthReportMapper;
import com.csit.system.service.IYxAssetMothReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 产品信息Service业务层处理
 * 
 * @author csit
 * @date 2019-12-14
 */
@Service
public class YxAssetMothReportServiceImpl implements  IYxAssetMothReportService
{
    @Autowired
    private YxAssetMonthReportMapper yxmonthReportMapper;


    @Override
    public List<YxAssetMothReport> AssetMothReport(YxAssetMothReport Assetreport)
    {
        return yxmonthReportMapper.AssetMothReport(Assetreport);
    }


}
