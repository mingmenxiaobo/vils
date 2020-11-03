package com.csit.web.controller.system;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.csit.common.json.JSONParam;
import com.csit.system.domain.SysDept;
import com.csit.system.domain.YxDeviceDay;
import com.csit.system.service.ISysDeptService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.csit.common.annotation.Log;
import com.csit.common.enums.BusinessType;
import com.csit.system.domain.YxHoliday;
import com.csit.system.service.IYxHolidayService;
import com.csit.common.core.controller.BaseController;
import com.csit.common.core.domain.AjaxResult;
import com.csit.common.utils.poi.ExcelUtil;
import com.csit.common.core.page.TableDataInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 节假日Controller
 * 
 * @author csit
 * @date 2020-05-01
 */
@Controller
@RequestMapping("/system/holiday")
public class YxHolidayController extends BaseController
{
    private String prefix = "system/holiday";

    @Autowired
    private IYxHolidayService yxHolidayService;

    @Autowired
    private ISysDeptService deptService;

    @RequiresPermissions("system:holiday:view")
    @GetMapping()
    public String holiday()
    {
        return prefix + "/holiday";
    }

    @RequiresPermissions("system:holiday:view")
    @GetMapping("holidayList")
    public String holidayList()
    {
        return prefix + "/holidaylist";
    }

    /**
     * 查询节假日列表
     */
    @RequiresPermissions("system:holiday:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(YxHoliday yxHoliday)
    {
        startPage();
        List<YxHoliday> list = yxHolidayService.selectYxHolidayList(yxHoliday);
        return getDataTable(list);
    }

    /**
     * 导出节假日列表
     */
    @RequiresPermissions("system:holiday:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(YxHoliday yxHoliday)
    {
        List<YxHoliday> list = yxHolidayService.selectYxHolidayList(yxHoliday);
        ExcelUtil<YxHoliday> util = new ExcelUtil<YxHoliday>(YxHoliday.class);
        return util.exportExcel(list, "holiday");
    }

    /**
     * 新增节假日
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存节假日
     */
    @RequiresPermissions("system:holiday:add")
    @Log(title = "节假日", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(YxHoliday yxHoliday)
    {
        return toAjax(yxHolidayService.insertYxHoliday(yxHoliday));
    }

    /**
     * 修改节假日
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        YxHoliday yxHoliday = yxHolidayService.selectYxHolidayById(id);
        mmap.put("yxHoliday", yxHoliday);
        return prefix + "/edit";
    }

    /**
     * 修改保存节假日
     */
    @RequiresPermissions("system:holiday:edit")
    @Log(title = "节假日", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(YxHoliday yxHoliday)
    {
        return toAjax(yxHolidayService.updateYxHoliday(yxHoliday));
    }

    /**
     * 删除节假日
     */
    @RequiresPermissions("system:holiday:remove")
    @Log(title = "节假日", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(yxHolidayService.deleteYxHolidayByIds(ids));
    }

    @RequiresPermissions("system:holiday:add")
    @Log(title = "节假日", businessType = BusinessType.INSERT)
    @PostMapping("/saveBatch")
    @ResponseBody
    public AjaxResult batchSave(String deptId,String calendarData)
    {

        System.out.println(deptId+"   "+calendarData);

        List<SysDept> dept= deptService.findAllDeptid(Long.parseLong(deptId));

        try{
            JSONArray holiday = JSONArray.parseArray(calendarData);
            List<YxHoliday> Daylist=new ArrayList<YxHoliday>();

            for(int i=0; i<dept.size();i++) {

               String  dept_Id= dept.get(i).getDeptId().toString();
                for (Object obj : holiday) {

                    YxHoliday hDay = new YxHoliday();

                    JSONObject dataObj = JSONObject.parseObject(obj.toString());

                    hDay.setDept(dept_Id);

                    hDay.setRepdate(dataObj.get("ymd").toString());
                    String ymd = dataObj.get("ymd").toString();
                    String[] yymmdd = ymd.split("-");

                    hDay.setRepyear(Long.parseLong(yymmdd[0].toString()));
                    hDay.setRepmonth(Long.parseLong(yymmdd[1].toString()));
                    hDay.setRepday(Long.parseLong(yymmdd[2].toString()));

                    hDay.setStatus(dataObj.get("isWorkDay").toString());

                    Daylist.add(hDay);
                    // yxHolidayService.insertYxHoliday(hDay);

                }
            }

            yxHolidayService.insertHBatch(Daylist);
            return success("保存成功！");
        } catch (Exception e) {
            return error("系统错误！");
        }
             //yxDeviceDayService.batchInset(devlist);

    }


}
