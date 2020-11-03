package com.csit.web.controller.system;


import com.csit.common.annotation.Log;
import com.csit.common.core.controller.BaseController;
import com.csit.common.core.domain.AjaxResult;
import com.csit.common.core.page.TableDataInfo;
import com.csit.common.enums.BusinessType;
import com.csit.common.utils.poi.ExcelUtil;
import com.csit.framework.util.ShiroUtils;
import com.csit.system.domain.SysSchool;
import com.csit.system.domain.SysStudent;
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
@RequestMapping("/system/school")
public class SysSchoolController extends BaseController {

    private String prefix = "system/school";
    @Autowired
    private ISysCountlistService countlistService;



    @RequiresPermissions("system:school:view")
    @GetMapping()
    public String schoollist()
    {
        return prefix + "/list";
    }

    @RequiresPermissions("system:school:list")
    @PostMapping("/lists")
    @ResponseBody
    public TableDataInfo list()
    {
        startPage();
        List<SysSchool> list = countlistService.selectSchoolVision();
        return getDataTable(list);

    }




    //学校报表
    @RequiresPermissions("system:school:list")
    @GetMapping("/details")
    public String schoolExcel()
    {
        return prefix + "/schoolExcel";
    }


    @PostMapping("/getSchoolVision")
    @ResponseBody
    public void getSchoolVision(HttpServletRequest request,
                                              HttpServletResponse response) throws IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> list = new HashMap<String, Object>();
        List<SysSchool> tempList = countlistService.selectSchoolVision();
        list.put("Count", 0);
        list.put("Code", 200);
        list.put("data", tempList);
        list.put("msg", "获取成功！");
        JSONArray jo = JSONArray.fromObject(list);
        out.print(jo);
    }



    @Log(title = "学校报表", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:school:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export()
    {
        List<SysSchool> list = countlistService.selectSchoolVision();
        ExcelUtil<SysSchool> util = new ExcelUtil<SysSchool>(SysSchool.class);
        return util.exportExcel(list, "学校数据");
    }



}
