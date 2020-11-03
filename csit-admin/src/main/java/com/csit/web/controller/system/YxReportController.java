package com.csit.web.controller.system;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.csit.common.annotation.Log;
import com.csit.common.core.controller.BaseController;
import com.csit.common.core.domain.AjaxResult;
import com.csit.common.core.page.TableDataInfo;
import com.csit.common.enums.BusinessType;
import com.csit.common.utils.poi.ExcelUtil;
import com.csit.system.domain.SysDept;
import com.csit.system.domain.YxAssetinforeport;
import com.csit.system.domain.YxHoliday;
import com.csit.system.service.ISysDeptService;
import com.csit.system.service.IYxAssetReportService;
import com.csit.system.service.IYxHolidayService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 节假日Controller
 * 
 * @author csit
 * @date 2020-05-01
 */
@Controller
@RequestMapping("/system/report")
public class YxReportController extends BaseController
{
    private String prefix = "system/report";

    @Autowired
    private IYxAssetReportService reportService;

    @Autowired
    private ISysDeptService deptService;

    @RequiresPermissions("system:report:view")
    @GetMapping()
    public String report()
    {
        return prefix + "/report";
    }


    /**
     * 查询节假日列表
     */
    @RequiresPermissions("system:report:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(YxAssetinforeport report)
    {
        startPage();
        List<YxAssetinforeport> list = reportService.AssetUseInfoReport(report );
        return getDataTable(list);
    }

    /**
     * 导出节假日列表
     */
    @RequiresPermissions("system:report:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(YxAssetinforeport report)
    {
        List<YxAssetinforeport> list = reportService.AssetUseInfoReport(report );
        ExcelUtil<YxAssetinforeport> util = new ExcelUtil<YxAssetinforeport>(YxAssetinforeport.class);
        return util.exportExcel(list, "report");
    }


    @PostMapping("/getcountViewCode")
    @ResponseBody
    public AjaxResult getcountViewCode(@Validated YxAssetinforeport report)
    {
        List<YxAssetinforeport> countViewCode=reportService.getViewCode(report);
        try
        {
            return AjaxResult.success("查询成功",countViewCode.size());
        }
        catch (Exception e)
        {
            return error(e.getMessage());
        }

    }

    @PostMapping("/getUsep")
    @ResponseBody
    public AjaxResult getUsep(@Validated YxAssetinforeport report)
    {

        String templateFilePath = "D:/csit/chartTemplate.xls"; //模板地址
        String fileName = "myExcel_"+ExcelExportUtil.getRandomFileName() + ".xls";
        String filePath = "D:\\csit\\uploadPath\\download\\"
                + fileName;//导出保存的地址
        File file = new File(filePath);
        try
        {
            OutputStream os = new FileOutputStream(file);
            ExcelExportUtil excel = new ExcelExportUtil();

            Map<String, Object> dataMap = new HashMap<String, Object>();

            List<YxAssetinforeport> count=reportService.AssetUseInfoReport(report);  //总共有多少条数据，取出日期、使用率
            dataMap.put("A1" , "日期");//A1
            dataMap.put("B1" , "使用率");//B1
                for (int j = 0; j <count.size(); j++) {
                    String Date=count.get(j).getRepDate().toString(); //循环获取时间
                    dataMap.put("A" + (j+2), Date);// 日期 An
                    dataMap.put("B"+(j+2),count.get(j).getUsep());  //使用率 //Bn
                }
            ExcelUtil<YxAssetinforeport> util = new ExcelUtil<YxAssetinforeport>(YxAssetinforeport.class);
            excel.writeData(templateFilePath, dataMap, 1);
            // 写到输出流并移除资源
            excel.writeAndClose(templateFilePath, os);
            os.flush();
            os.close();


            return AjaxResult.success(fileName);

        }
        catch (Exception e)
        {
            return error(e.getMessage());
        }

    }


    @PostMapping("/getAvgUsep")
    @ResponseBody
    public AjaxResult getAvgUsep(@Validated YxAssetinforeport report)
    {

        String templateFilePath = "D:/csit/chartTemplate.xls"; //模板地址
        String fileName = "myExcel_"+ExcelExportUtil.getRandomFileName() + ".xls";
        String filePath = "D:\\csit\\uploadPath\\download\\"
                + fileName;
        File file = new File(filePath);//导出保存的地址
        try
        {

            OutputStream os = new FileOutputStream(file);
            ExcelExportUtil excel = new ExcelExportUtil();
            Map<String, Object> dataMap = new HashMap<String, Object>();

            List<YxAssetinforeport> getCount=reportService.AssetUseInfoReport(report);  //总共有多少条数据
            List<YxAssetinforeport> getCreateTime=reportService.getCreateTime(report);  //获取日期
            dataMap.put("A1" , "日期");//A1
            dataMap.put("B1" , "使用率");//B1
                    for (int j = 0; j < getCreateTime.size(); j++) {
                        String getDate = getCreateTime.get(j).getRepDate().toString(); //循环获取时间
                        dataMap.put("A" + (j + 2), getDate);// 日期 An
                        YxAssetinforeport avgUsep = reportService.getAvgUsep(getDate);
                        dataMap.put("B"+(j + 2), avgUsep.getUsep());  //平均使用率
                    }
                    excel.writeData(templateFilePath, dataMap, 1);
                    // 写到输出流并移除资源
                    excel.writeAndClose(templateFilePath, os);
                    os.flush();
                    os.close();
                return AjaxResult.success(fileName);
            }
            catch (Exception e)
            {
                return error(e.getMessage());
            }
    }

}
