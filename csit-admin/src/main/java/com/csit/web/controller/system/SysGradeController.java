package com.csit.web.controller.system;

import com.csit.common.annotation.Log;
import com.csit.common.constant.UserConstants;
import com.csit.common.core.controller.BaseController;
import com.csit.common.core.domain.AjaxResult;
import com.csit.common.core.page.TableDataInfo;
import com.csit.common.enums.BusinessType;
import com.csit.common.utils.poi.ExcelUtil;
import com.csit.framework.util.ShiroUtils;
import com.csit.system.domain.SysGrade;
import com.csit.system.domain.SysRole;
import com.csit.system.domain.SysStudent;
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
@RequestMapping("/system/grade")
public class SysGradeController extends BaseController {

    private String prefix = "system/grade";
    @Autowired
    private ISysCountlistService countlistService;

    @RequiresPermissions("system:grade:view")
    @GetMapping()
    public String countlist()
    {
        return prefix + "/list";
    }

    @RequiresPermissions("system:grade:list")
    @PostMapping("/lists")
    @ResponseBody
    public TableDataInfo list(SysGrade sysGrade)
    {
        startPage();
        if(sysGrade.getUserId() != "" && !sysGrade.getUserId().equals("")){
            List<SysGrade> list = countlistService.selectGradeVision(sysGrade);
            return getDataTable(list);
        }else {
            sysGrade.setUserId(ShiroUtils.getSysUser().getDeptId().toString());
            List<SysGrade> list = countlistService.selectGradeVision(sysGrade);
            return getDataTable(list);
        }
    }


    @PostMapping("/checkSchool")
    @ResponseBody
    public void getSchoolVision(HttpServletRequest request,
                                HttpServletResponse response) throws IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> list = new HashMap<String, Object>();
        String userId=request.getParameter("userId").toString();
        SysGrade sysGrade=new SysGrade();
        sysGrade.setUserId(userId);
        int tempList = countlistService.checkSchool(sysGrade);
        list.put("Count", 0);
        list.put("Code", 200);
        list.put("data", tempList);
        list.put("msg", "获取成功！");
        JSONArray jo = JSONArray.fromObject(list);
        out.print(jo);
    }



    @RequiresPermissions("system:grade:list")
    @GetMapping("/details/{userId}")
    public String schoolExcel(@PathVariable("userId") String  userId, ModelMap mmap)
    {
        mmap.put("userId",userId);
        return prefix + "/gradeExcel";
    }


    @PostMapping("/getGradeVision")
    @ResponseBody
    public void getGradeVision(HttpServletRequest request,
                                HttpServletResponse response) throws IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> list = new HashMap<String, Object>();
        String userId=request.getParameter("userId");
        SysGrade sysGrade=new SysGrade();
        sysGrade.setUserId(userId);
        List<SysGrade> tempList = countlistService.selectGradeVision(sysGrade);
        list.put("Count", 0);
        list.put("Code", 200);
        list.put("data", tempList);
        list.put("msg", "获取成功！");
        JSONArray jo = JSONArray.fromObject(list);
        out.print(jo);
    }



    @Log(title = "年级报表", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:grade:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysGrade sysGrade)
    {
        List<SysGrade> list = countlistService.selectGradeVision(sysGrade);
        ExcelUtil<SysGrade> util = new ExcelUtil<SysGrade>(SysGrade.class);
        return util.exportExcel(list, "年级数据");
    }


    @GetMapping("/edit")
    public String edit(ModelMap mmap)
    {
        SysGrade list = countlistService.selectVisionStandard();
        mmap.put("list", list);
        return prefix + "/edit";
    }


    @RequiresPermissions("system:grade:edit")
    @Log(title = "年级报表", businessType = BusinessType.INSERT)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysGrade sysGrade)
    {
        ShiroUtils.clearCachedAuthorizationInfo();
        return toAjax(countlistService.updateVisionStandard(sysGrade));
    }

}
