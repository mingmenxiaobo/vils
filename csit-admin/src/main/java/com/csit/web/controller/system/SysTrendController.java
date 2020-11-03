package com.csit.web.controller.system;


import com.csit.common.annotation.Log;
import com.csit.common.core.controller.BaseController;
import com.csit.common.core.domain.AjaxResult;
import com.csit.common.core.page.TableDataInfo;
import com.csit.common.enums.BusinessType;
import com.csit.common.utils.poi.ExcelUtil;
import com.csit.system.domain.SysSchool;
import com.csit.system.domain.SysTrend;
import com.csit.system.service.ISysCountlistService;
import net.sf.json.JSONArray;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/system/trend")
public class SysTrendController extends BaseController {

    private String prefix = "system/trend";
    @Autowired
    private ISysCountlistService countlistService;



    @RequiresPermissions("system:trend:view")
    @GetMapping()
    public String trendlist()
    {
        return prefix + "/list";
    }

    @RequiresPermissions("system:trend:list")
    @PostMapping("/lists")
    @ResponseBody
    public TableDataInfo list()
    {
        startPage();
        List<SysTrend> list = countlistService.selectSchoolVisionTrend();
        return getDataTable(list);

    }


    @RequiresPermissions("system:trend:list")
    @GetMapping("/details")
    public String schoolExcel()
    {
        return prefix + "/trendExcel";
    }


    @GetMapping("/getSchoolName")
    @ResponseBody
    public void getSchoolName(HttpServletRequest request,
                                HttpServletResponse response) throws IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> list = new HashMap<String, Object>();
        List<SysTrend> tempList = countlistService.selectSchoolName();
        if(tempList != null){
            list.put("Count", 0);
            list.put("Code", 200);
            list.put("data", tempList);
            list.put("msg", "获取成功！");
            JSONArray jo = JSONArray.fromObject(list);
            out.print(jo);
        }else{
            list.put("Count", 0);
            list.put("Code", 201);
            list.put("data", "");
            list.put("msg", "获取失败！");
            JSONArray jo = JSONArray.fromObject(list);
            out.print(jo);
        }
    }


    @GetMapping("/selectSchoolVisionTrendBystudentSchool")
    @ResponseBody
    public void selectSchoolVisionTrendBystudentSchool(HttpServletRequest request,
                                HttpServletResponse response) throws IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> list = new HashMap<String, Object>();
        String studentSchool=request.getParameter("studentSchool");
        if(studentSchool !="" && !studentSchool.equals("")){
            List<SysTrend> tempList = countlistService.selectSchoolVisionTrendBystudentSchool(studentSchool);
            list.put("Count", 0);
            list.put("Code", 200);
            list.put("data", tempList);
            list.put("msg", "获取成功！");
            JSONArray jo = JSONArray.fromObject(list);
            out.print(jo);
        }else{
            list.put("Count", 0);
            list.put("Code", 201);
            list.put("data", "");
            list.put("msg", "获取失败，请传入studentSchool！");
            JSONArray jo = JSONArray.fromObject(list);
            out.print(jo);
        }

    }


    @Log(title = "学校趋势报表", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:trend:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export()
    {
        List<SysTrend> list = countlistService.selectSchoolVisionTrend();
        ExcelUtil<SysTrend> util = new ExcelUtil<SysTrend>(SysTrend.class);
        return util.exportExcel(list, "学校趋势数据");
    }


}
