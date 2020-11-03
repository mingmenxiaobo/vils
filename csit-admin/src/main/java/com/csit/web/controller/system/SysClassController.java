package com.csit.web.controller.system;

import com.csit.common.annotation.Log;
import com.csit.common.core.controller.BaseController;
import com.csit.common.core.domain.AjaxResult;
import com.csit.common.core.page.TableDataInfo;
import com.csit.common.enums.BusinessType;
import com.csit.common.utils.poi.ExcelUtil;
import com.csit.framework.util.ShiroUtils;
import com.csit.system.domain.SysClass;
import com.csit.system.domain.SysGrade;
import com.csit.system.service.ISysCountlistService;
import net.sf.json.JSONArray;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/system/class")
public class SysClassController extends BaseController {

    private String prefix = "system/class";
    @Autowired
    private ISysCountlistService countlistService;

    @RequiresPermissions("system:class:view")
    @GetMapping()
    public String countlist()
    {
        return prefix + "/list";
    }

    @RequiresPermissions("system:class:list")
    @PostMapping("/lists")
    @ResponseBody
    public TableDataInfo list(SysClass sysClass)
    {
        startPage();
        if(sysClass.getUserId() != "" && !sysClass.getUserId().equals("")){
            List<SysClass> list = countlistService.selectClassVision(sysClass);
            return getDataTable(list);
        }else {
            sysClass.setUserId(ShiroUtils.getSysUser().getDeptId().toString());
            List<SysClass> list = countlistService.selectClassVision(sysClass);
            return getDataTable(list);
        }
    }


    @PostMapping("/checkGrade")
    @ResponseBody
    public void getSchoolVision(HttpServletRequest request,
                                HttpServletResponse response) throws IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> list = new HashMap<String, Object>();
        String userId=request.getParameter("userId").toString();
        SysClass sysClass=new SysClass();
        sysClass.setUserId(userId);
        int tempList = countlistService.checkGrade(sysClass);
        list.put("Count", 0);
        list.put("Code", 200);
        list.put("data", tempList);
        list.put("msg", "获取成功！");
        JSONArray jo = JSONArray.fromObject(list);
        out.print(jo);
    }


    //班级报表
    @RequiresPermissions("system:class:list")
    @GetMapping("/details/{userId}")
    public String schoolExcel(@PathVariable("userId") String  userId, ModelMap mmap)
    {
        if(userId.equals("0")){
            mmap.put("userId",ShiroUtils.getSysUser().getDeptId());
        }else{
            mmap.put("userId",userId);
        }
        return prefix + "/classExcel";
    }


    @PostMapping("/getClassVision")
    @ResponseBody
    public void getGradeVision(HttpServletRequest request,
                                HttpServletResponse response) throws IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> list = new HashMap<String, Object>();
        String userId=request.getParameter("userId");
        SysClass sysClass=new SysClass();
        sysClass.setUserId(userId);
        List<SysClass> tempList = countlistService.selectClassVision(sysClass);
        list.put("Count", 0);
        list.put("Code", 200);
        list.put("data", tempList);
        list.put("msg", "获取成功！");
        JSONArray jo = JSONArray.fromObject(list);
        out.print(jo);
    }



    @Log(title = "班级报表", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:class:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysClass sysClass)
    {
        List<SysClass> list = countlistService.selectClassVision(sysClass);
        ExcelUtil<SysClass> util = new ExcelUtil<SysClass>(SysClass.class);
        return util.exportExcel(list, "班级数据");
    }


    @GetMapping("/edit")
    public String edit(ModelMap mmap)
    {
        SysGrade list = countlistService.selectVisionStandard();
        mmap.put("list", list);
        return prefix + "/edit";
    }


    @RequiresPermissions("system:class:edit")
    @Log(title = "班级报表", businessType = BusinessType.INSERT)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysGrade sysGrade)
    {
        ShiroUtils.clearCachedAuthorizationInfo();
        return toAjax(countlistService.updateVisionStandard(sysGrade));
    }

}
