package com.csit.web.controller.system;


import com.csit.common.annotation.Log;
import com.csit.common.core.controller.BaseController;
import com.csit.common.core.domain.AjaxResult;
import com.csit.common.core.page.TableDataInfo;
import com.csit.common.enums.BusinessType;
import com.csit.common.utils.poi.ExcelUtil;
import com.csit.framework.util.ShiroUtils;
import com.csit.system.domain.SysClass;
import com.csit.system.domain.SysClassTrend;
import com.csit.system.domain.SysTrend;
import com.csit.system.service.ISysCountlistService;
import net.sf.json.JSONArray;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/system/classTrend")
public class SysClassTrendController extends BaseController {

    private String prefix = "system/classTrend";
    @Autowired
    private ISysCountlistService countlistService;



    @RequiresPermissions("system:classTrend:view")
    @GetMapping()
    public String classTrendlist()
    {
        return prefix + "/list";
    }

    @RequiresPermissions("system:classTrend:list")
    @PostMapping("/lists")
    @ResponseBody
    public TableDataInfo list(SysClassTrend sysClassTrend)
    {
        startPage();
        if(sysClassTrend.getUserId() != "" && !sysClassTrend.getUserId().equals("")){
            List<SysClassTrend> list = countlistService.selectClassVisionTrendByUserId(sysClassTrend);
            return getDataTable(list);
        }else {
            sysClassTrend.setUserId(ShiroUtils.getSysUser().getDeptId().toString());
            List<SysClassTrend> list = countlistService.selectClassVisionTrendByUserId(sysClassTrend);
            return getDataTable(list);
        }


    }

    //班级报表
    @RequiresPermissions("system:classTrend:list")
    @GetMapping("/details/{userId}")
    public String schoolExcel(@PathVariable("userId") String  userId, ModelMap mmap)
    {
        if(userId.equals("0")){
            mmap.put("userId",ShiroUtils.getSysUser().getDeptId());
        }else{
            mmap.put("userId",userId);
        }
        return prefix + "/classTrendExcel";
    }



    @GetMapping("/getClassName")
    @ResponseBody
    public void getClassName(HttpServletRequest request,
                              HttpServletResponse response) throws IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> list = new HashMap<String, Object>();
        String userId=request.getParameter("userId");
        SysClassTrend sysClassTrend=new SysClassTrend();
        sysClassTrend.setUserId(userId);
        List<SysClassTrend> tempList = countlistService.selectClassName(sysClassTrend);
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


    @GetMapping("/getClassTrendData")
    @ResponseBody
    public void getClassTrendData(HttpServletRequest request,
                               HttpServletResponse response) throws IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> list = new HashMap<String, Object>();
        String userId=request.getParameter("userId");
        SysClassTrend sysClassTrend=new SysClassTrend();
        sysClassTrend.setUserId(userId);
        List<SysClassTrend> tempList = countlistService.selectClassVisionTrendByUserId(sysClassTrend);
        list.put("Count", 0);
        list.put("Code", 200);
        list.put("data", tempList);
        list.put("msg", "获取成功！");
        JSONArray jo = JSONArray.fromObject(list);
        out.print(jo);
    }


    @Log(title = "班级报表", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:classTrend:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysClassTrend sysClassTrend)
    {
        List<SysClassTrend> list = countlistService.selectClassVisionTrendByUserId(sysClassTrend);
        ExcelUtil<SysClassTrend> util = new ExcelUtil<SysClassTrend>(SysClassTrend.class);
        return util.exportExcel(list, "班级数据");
    }

}
