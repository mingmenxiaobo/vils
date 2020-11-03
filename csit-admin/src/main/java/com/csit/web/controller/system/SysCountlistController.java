package com.csit.web.controller.system;

import com.csit.common.annotation.Log;
import com.csit.common.constant.UserConstants;
import com.csit.common.core.controller.BaseController;
import com.csit.common.core.domain.AjaxResult;
import com.csit.common.core.page.TableDataInfo;
import com.csit.common.enums.BusinessType;
import com.csit.common.utils.poi.ExcelUtil;
import com.csit.framework.shiro.service.SysPasswordService;
import com.csit.framework.util.ShiroUtils;
import com.csit.system.domain.*;
import com.csit.system.service.ISysCountlistService;
import com.csit.system.service.ISysPostService;
import com.csit.system.service.ISysRoleService;
import com.csit.system.service.ISysUserService;
import net.sf.json.JSONArray;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 用户信息
 *
 * @author csit
 */
@Controller
@RequestMapping("/system/countlist")
public class SysCountlistController extends BaseController
{
    private String prefix = "system/countlist";

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysPostService postService;

    @Autowired
    private ISysCountlistService countlistService;

    @Autowired
    private SysPasswordService passwordService;

    @RequiresPermissions("system:countlist:view")
    @GetMapping()
    public String countlist()
    {
        return prefix + "/list";
    }

    @RequiresPermissions("system:countlist:list")
    @PostMapping("/lists")
    @ResponseBody
    public TableDataInfo list(SysStudent sysStudent)
    {

        startPage();
        if(sysStudent.getUserId() != "" && !sysStudent.getUserId().equals("")){
            List<SysStudent> list = countlistService.selectStudentList(sysStudent);
            return getDataTable(list);
        }else {
            sysStudent.setUserId(ShiroUtils.getSysUser().getDeptId().toString());
            List<SysStudent> list = countlistService.selectStudentList(sysStudent);
            return getDataTable(list);
        }
    }


    @Log(title = "学生管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:countlist:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysStudent sysStudent)
    {
        List<SysStudent> list = countlistService.selectStudentList(sysStudent);
        ExcelUtil<SysStudent> util = new ExcelUtil<SysStudent>(SysStudent.class);
        return util.exportExcel(list, "学生数据");
    }


    @Log(title = "学生管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:countlist:export")
    @PostMapping("/exports")
    @ResponseBody
    public AjaxResult exports(SysStudentDetail sysStudentDetail)
    {
        List<SysStudentDetail> list = countlistService.selectStudentDataList(sysStudentDetail);
        ExcelUtil<SysStudentDetail> util = new ExcelUtil<SysStudentDetail>(SysStudentDetail.class);
        return util.exportExcel(list, "学生详细数据");
    }


    /**
     * 新增用户
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }


    /**
     * 新增保存
     */
    @Log(title = "新增学生", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:countlist:add")
    @PostMapping("/adds")
    @ResponseBody
    public AjaxResult addSave(@Validated SysStudent sysStudent)
    {
        String userId=ShiroUtils.getSysUser().getDeptId().toString();
        List<Map> d=countlistService.selectStudentDept(userId);
        sysStudent.setStudentCity(d.get(0).get("dept_id").toString());
        sysStudent.setStudentArea(d.get(1).get("dept_id").toString());
        sysStudent.setStudentSchool(d.get(2).get("dept_id").toString());
        sysStudent.setStudentGrade(d.get(3).get("dept_id").toString());
        sysStudent.setStudentClass(ShiroUtils.getSysUser().getDeptId().toString());
        sysStudent.setUserId(ShiroUtils.getSysUser().getDeptId().toString());
        return toAjax(countlistService.insertStudentData(sysStudent));
    }


    @RequiresPermissions("system:countlist:student")
    @GetMapping("/student/{studentName}/{studentCode}/{birthday}")
    public String student( @PathVariable("studentCode") String studentCode,ModelMap mmap)
    {
        mmap.put("studentCode", studentCode);
        return prefix + "/student";
    }

    @RequiresPermissions("system:countlist:student")
@PostMapping("/studentlist")
@ResponseBody
public TableDataInfo studentlist(SysStudentDetail sysStudentDetail)
{
    startPage();
    List<SysStudentDetail> list = countlistService.selectStudentDataList(sysStudentDetail);
    return getDataTable(list);
}



    @Log(title = "学生管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("system:countlist:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<SysStudent> util = new ExcelUtil<SysStudent>(SysStudent.class);
        List<SysStudent> studentList = util.importExcel(file.getInputStream());
        String UserId = ShiroUtils.getSysUser().getDeptId().toString();
        String updateBy = ShiroUtils.getSysUser().getLoginName();
        String message = countlistService.importStudent(studentList, updateSupport, UserId,updateBy);
        return AjaxResult.success(message);
    }

    @RequiresPermissions("system:countlist:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<SysStudent> util = new ExcelUtil<SysStudent>(SysStudent.class);
        return util.importTemplateExcel("学生数据");
    }






    @RequiresPermissions("system:countlist:details")
    @GetMapping("/details/{studentid}")
    public String studentDetails( @PathVariable("studentid") String studentid,ModelMap mmap)
    {
        SysStudent list = countlistService.selectStudentById(studentid);
        mmap.put("list", list);
        return prefix + "/details";
    }


    @Log(title = "新增学生", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:countlist:details")
    @PostMapping("/detailslist")
    @ResponseBody
    public AjaxResult addstudentDetails(@Validated SysStudentDetail sysStudentDetail)
    {
        sysStudentDetail.setCreate_by(ShiroUtils.getSysUser().getLoginName());
        return toAjax(countlistService.insertStudentDetails(sysStudentDetail));
    }



    @Log(title = "学生管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("system:countlist:import2")
    @PostMapping("/importData2")
    @ResponseBody
    public AjaxResult importData2(MultipartFile file, boolean updateSupport2) throws Exception
    {
        ExcelUtil<SysStudentDetail> util = new ExcelUtil<SysStudentDetail>(SysStudentDetail.class);
        List<SysStudentDetail> studentDetail = util.importExcel(file.getInputStream());
        String UserId = ShiroUtils.getSysUser().getDeptId().toString();
        String updateBy = ShiroUtils.getSysUser().getLoginName();
        String message = countlistService.importStudent2(studentDetail, updateSupport2, UserId,updateBy);
        return AjaxResult.success(message);
    }

    @RequiresPermissions("system:countlist:view")
    @GetMapping("/importTemplate2")
    @ResponseBody
    public AjaxResult importTemplate2()
    {
        ExcelUtil<SysStudentDetail> util = new ExcelUtil<SysStudentDetail>(SysStudentDetail.class);
        return util.importTemplateExcel("学生数据");
    }


    @RequiresPermissions("system:countlist:remove")
    @Log(title = "学生管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        try
        {
            return toAjax(countlistService.deleteStudentByIds(ids));
        }
        catch (Exception e)
        {
            return error(e.getMessage());
        }
    }

    //个人报表
    @RequiresPermissions("system:countlist:list")
    @GetMapping("/studentExcel/{studentName}/{studentCode}/{birthday}")
    public String goExcel( @PathVariable("studentCode") String studentCode,@PathVariable("studentName") String studentName,ModelMap mmap)
    {
        mmap.put("studentCode", studentCode);
        mmap.put("studentName", studentName);
        return prefix + "/goStudentExcel";
    }


    @PostMapping("/getStudentVisionByStudentCode")
    @ResponseBody
    public void getStudentVisionByStudentCode(HttpServletRequest request,
                                             HttpServletResponse response) throws IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> list = new HashMap<String, Object>();

        if (request.getParameter("StudentCode") != null)
        {
            if (!"".equals(request.getParameter("StudentCode").trim()))
            {
                String studentCode=request.getParameter("StudentCode");
                List<Map> tempList = countlistService.selectStudentVision(studentCode);
                list.put("Count", 0);
                list.put("Code", 200);
                list.put("data", tempList);
                list.put("msg", "获取成功！");
                JSONArray jo = JSONArray.fromObject(list);
                out.print(jo);
            }
            else
            {
                list.put("Count", 0);
                list.put("Code", 201);
                list.put("data", null);
                list.put("msg", "获取失败,请传入StudentCode！");
                JSONArray jo = JSONArray.fromObject(list);
                out.print(jo);
            }
        }
        else
        {
            list.put("Count", 0);
            list.put("Code", 201);
            list.put("data", null);
            list.put("msg", "获取失败,请传入StudentCode！");
            JSONArray jo = JSONArray.fromObject(list);
            out.print(jo);
        }
    }


}