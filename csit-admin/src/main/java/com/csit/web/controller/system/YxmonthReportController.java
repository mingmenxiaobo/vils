package com.csit.web.controller.system;

import com.csit.common.core.controller.BaseController;
import com.csit.common.core.domain.AjaxResult;
import com.csit.common.core.page.TableDataInfo;
import com.csit.common.utils.poi.ExcelUtil;
import com.csit.system.domain.YxAssetMothReport;
import com.csit.system.service.ISysDeptService;
import com.csit.system.service.IYxAssetMothReportService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 节假日Controller
 * 
 * @author csit
 * @date 2020-05-01
 */
@Controller
@RequestMapping("/system/monthReport")
public class YxmonthReportController extends BaseController
{
    private String prefix = "system/report";

    @Autowired
    private IYxAssetMothReportService reportMonthService;

    @Autowired
    private ISysDeptService deptService;

    @RequiresPermissions("system:report:view")
    @GetMapping()
    public String monthReport()
    {
        return prefix + "/monthReport";
    }


    /**
     * 查询节假日列表
     */
    @RequiresPermissions("system:report:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(YxAssetMothReport report)
    {
        startPage();
        List<YxAssetMothReport> list = reportMonthService.AssetMothReport(report);
        return getDataTable(list);
    }

    /**
     * 导出节假日列表
     */
    @RequiresPermissions("system:report:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(YxAssetMothReport report)
    {
        List<YxAssetMothReport> list = reportMonthService.AssetMothReport(report );
        ExcelUtil<YxAssetMothReport> util = new ExcelUtil<YxAssetMothReport>(YxAssetMothReport.class);
        return util.exportExcel(list, "report");
    }





}
