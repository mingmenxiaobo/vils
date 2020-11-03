package com.csit.web.controller.system;


import com.csit.common.annotation.Log;
import com.csit.common.config.Global;
import com.csit.common.core.controller.BaseController;
import com.csit.common.core.domain.AjaxResult;
import com.csit.common.core.domain.BaseEntity;
import com.csit.common.core.domain.Ztree;
import com.csit.common.core.page.TableDataInfo;
import com.csit.common.enums.BusinessType;
import com.csit.common.utils.file.FileUploadUtils;
import com.csit.framework.util.ShiroUtils;
import com.csit.system.domain.*;
import com.csit.system.mapper.SysTemplateMapper;
import com.csit.system.service.ISysDeptService;
import com.csit.system.service.ISysTemplateService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/system/template")
public class SysTemplateController extends BaseController {

    private String prefix = "system/template";

    @Autowired
    private ISysDeptService deptService;

    @Autowired
    private ISysTemplateService templateService;


    @RequiresPermissions("system:template:view")
    @GetMapping()
    public String templateList()
    {
        return prefix + "/template";
    }

    @RequiresPermissions("system:template:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysTemplate_layout SysTemplate_layout)
    {
        startPage();
        List<SysTemplate_layout> list = templateService.selectTemplateList(SysTemplate_layout);
        return getDataTable(list);
    }


    /**
     * 选择部门树
     */
    @GetMapping("/selectDeptTree/{deptId}")
    public String selectDeptTree(@PathVariable("deptId") Long deptId, ModelMap mmap)
    {
        mmap.put("dept", deptService.selectDeptById(deptId));
        return prefix + "/tree";
    }

    /**
     * 加载部门列表树
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = deptService.selectDeptTree(new SysDept());
        return ztrees;
    }

    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存公告
     */
    @RequiresPermissions("system:template:add")
    @Log(title = "新增模板", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Map map)
    {
        return null;
    }

    @GetMapping("/addTemplateControls")
    public String addTemplateControls()
    {
        return prefix + "/addTemplateControls";
    }

    @GetMapping("/addSetUpTemplate")
    public String addSetUpTemplate()
    {
        return prefix + "/addSetUpTemplate";
    }

    @GetMapping("/templateLayoutDetail")
    public String templateLayoutDetail()
    {
        return prefix + "/templateLayoutDetail";
    }

    //上传图片
    @PostMapping("/uploadTemplateLayoutImage")
    @ResponseBody
    public void uploadTemplateLayoutImage(HttpServletRequest request,
                                          HttpServletResponse response) throws Exception
    {
        // 设置响应编码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Map<String, Object> list = new HashMap<String, Object>();
        String rootpath = request.getSession().getServletContext()
                .getRealPath("")+ "public//images//jigui//";
        PrintWriter out = response.getWriter();
        // 使用MultipartHttpServletRequest包装文件数据
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        String SUFFIX = "";
        // 页面控件的文件流
        MultipartFile multipartFile = multipartRequest.getFile("FILE_PATH");
        // 获取文件名/
        String filename = multipartFile.getOriginalFilename();
        String imagePath = "";
        // 构建图片保存的目录
        String logoPathDir = "public//images//jigui//";
        // 得到图片保存目录的真实路径
        String logoRealPathDir = request.getSession().getServletContext()
                .getRealPath(logoPathDir);
        File logoSaveFile = new File(logoRealPathDir);
        if (!logoSaveFile.exists())
        {
            logoSaveFile.mkdirs();
        }
        if (!"".equals(filename))
        {
            // 获取文件名的后缀
            SUFFIX = filename.substring(filename.lastIndexOf(".") + 1);
            if (SUFFIX.equalsIgnoreCase("png")
                    || SUFFIX.equalsIgnoreCase("jpg")
                    || SUFFIX.equalsIgnoreCase("jpeg")
                    || SUFFIX.equalsIgnoreCase("jpe")
                    || SUFFIX.equalsIgnoreCase("gif")
                    || SUFFIX.equalsIgnoreCase("ico"))
            {
                // 将文件名改为用时间生成
                String imageName = new SimpleDateFormat("yyyyMMddHHmmss")
                        .format(new Date()) + "." + SUFFIX;
                // 存入数据库路径
                imagePath = logoPathDir + imageName;
                String fileName = "";
                // 如果multipartFile控件值不为空就将图片保存进文件夹
                // 上传路径
                fileName = logoRealPathDir + File.separator + imageName;
                System.out.println(fileName);
                File file = new File(fileName);
//                if (!file.exists()){
//                    file.mkdir();
//                }
                try
                {
                    // 保存上传文件
                    multipartFile.transferTo(file);
                }
                catch (Exception e)
                {
                    // TODO: handle exception
                }

                list.put("Count", 0);
                list.put("Code", 200);
                list.put("msg", "上传成功！");
                list.put("data", imagePath);
                JSONArray jo = JSONArray.fromObject(list);
                out.print(jo);
            }
            else
            {
                list.put("Count", 0);
                list.put("Code", 201);
                list.put("msg", "上传失败！文件格式不正确！");
                list.put("data", null);
                JSONArray jo = JSONArray.fromObject(list);
                out.print(jo);
            }
        }
    }

    //新增模板
    @PostMapping("/AddTemplate_layout")
    @ResponseBody
    public void AddTemplate_layout(HttpServletRequest request,
                                   HttpServletResponse response) throws IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> list = new HashMap<String, Object>();

        String templateName=request.getParameter("template_name").toString();
        String template_type=request.getParameter("template_type").toString();
        String status=request.getParameter("status").toString();
        String width=request.getParameter("width").toString();
        String height=request.getParameter("height").toString();
        String base_map=request.getParameter("base_map").toString();
        String map_width=request.getParameter("map_width").toString();
        String map_height=request.getParameter("map_height").toString();
        Map map=new HashMap();
        map.put("template_name",templateName);
        map.put("template_type",template_type);
        map.put("status",status);
        map.put("width",width);
        map.put("height",height);
        map.put("base_map",base_map);
        map.put("map_width",map_width);
        map.put("map_height",map_height);
        if (templateName != null)
        {
            int num = templateService.SaveTemplateLayout(map);

            List<Map> data2 = templateService.SelectTemplateName(templateName);

            if (num > 0)
            {
                list.put("Count", 0);
                list.put("Code", 200);
                list.put("data", data2);
                JSONArray jo = JSONArray.fromObject(list);
                out.print(jo);
            }
            else
            {

                list.put("Count", 0);
                list.put("Code", 201);
                list.put("data", "保存失败");
                JSONArray jo = JSONArray.fromObject(list);
                out.print(jo);
            }

        }
        else
        {
            list.put("Count", 0);
            list.put("Code", 202);
            list.put("data", "请传入模板名称等参数！");
            JSONArray jo = JSONArray.fromObject(list);
            out.print(jo);
        }
    }

    //查询模板返回数据
    @GetMapping("/getTemplateDetailById")
    @ResponseBody
    public void getTemplateDetailById(HttpServletRequest request,
                                      HttpServletResponse response) throws IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> list = new HashMap<String, Object>();
        String template_id=request.getParameter("template_id");
        if (template_id != null)
        {
            if (!template_id.equals(""))
            {
                SysTemplate_layout tempList = this.templateService
                        .getTemplateDetailById(template_id);
                if (tempList == null)
                {
                    tempList = new SysTemplate_layout();
                }
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
                list.put("msg", "获取失败,请传入template_id！");
                JSONArray jo = JSONArray.fromObject(list);
                out.print(jo);
            }
        }
        else
        {
            list.put("Count", 0);
            list.put("Code", 202);
            list.put("data", null);
            list.put("msg", "获取失败,请传入template_id！");
            JSONArray jo = JSONArray.fromObject(list);
            out.print(jo);
        }
    }


    //删除模板控件
    @PostMapping("/delTempletControl")
    @ResponseBody
    public void delTempletControl(HttpServletRequest request,
                                  HttpServletResponse response) throws IOException
    {
        Map<String, Object> list = new HashMap<String, Object>();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try
        {
            String template_id = request.getParameter("template_id");
            String control_name = request.getParameter("control_name");
            Map map=new HashMap();
            map.put("template_id",template_id);
            map.put("control_name",control_name);
            if (template_id != "" && control_name != "")
            {
                int num = templateService.Deletetemplate_control(map);
                if (num > 0)
                {
                    list.put("Count", num);
                    list.put("Code", 200);
                    list.put("data", "删除成功！");
                }
                else
                {
                    list.put("Count", 0);
                    list.put("Code", 203);
                    list.put("data", "控件不存在,或已经删除！");
                }
            }
            else
            {
                list.put("Count", 0);
                list.put("Code", 202);
                list.put("data", "缺少必要参数！");
            }
        }
        catch (Exception e)
        {
            list.put("Count", 0);
            list.put("Code", 201);
            list.put("data", null);
            list.put("Msg", "删除失败！");
            e.printStackTrace();
        }
        JSONArray jo = JSONArray.fromObject(list);
        out.print(jo);
    }

    //保存控件位置
    @PostMapping("/AddTemplateDetail")
    @ResponseBody
    public void AddTemplateDetail(HttpServletRequest request,
                                  HttpServletResponse response) throws IOException
    {
        Map<String, Object> list = new HashMap<String, Object>();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try
        {
            // JSONArray holiday = JSONArray.parseArray(jsonString);
            JSONArray objectArray = JSONArray.fromObject(request.getParameter("jsonString"));
            System.out.println(objectArray);
            String template_id = request.getParameter("template_id");
            String status = request.getParameter("status");
            List<Map> tempList = new ArrayList<Map>();
            if (objectArray != null && objectArray.size() > 0)
            {
                for (int i = 0; i < objectArray.size(); i++)
                {
                    JSONObject obj = JSONObject.fromObject(objectArray.get(i));
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("template_id", template_id);
                    map.put("control_name", obj.get("control_name").toString());
                    map.put("location_x", obj.get("location_x").toString());
                    map.put("location_y", obj.get("location_y").toString());
                    map.put("control_type", obj.get("control_type").toString());
                    map.put("control_shape", obj.get("control_shape")
                            .toString());
                    tempList.add(map);
                }
            }
            Map map = new HashMap();
            map.put("template_id", template_id);
            map.put("status", status);
            int count = templateService.updateTemplateLayoutStatus(map);
            int deleteControl=templateService.deleteControlById(template_id);
            int num = templateService.Inserttemplate_detail_Pach(tempList);
            list.put("Count", 0);
            list.put("Code", 200);
            list.put("data", "保存成功！");
        }
        catch (Exception e)
        {
            list.put("Count", 0);
            list.put("Code", 201);
            list.put("data", null);
            list.put("Msg", "保存失败！");
            e.printStackTrace();
        }

        JSONArray jo = JSONArray.fromObject(list);
        out.print(jo);
    }


    // 模板名称检测
    @PostMapping("/checkTempletName")
    @ResponseBody
    public void checkTempletName(HttpServletRequest request,
                                 HttpServletResponse response) throws IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> list = new HashMap<String, Object>();

        if (request.getParameter("template_name") != null)
        {
            String name = request.getParameter("template_name");

            int num = this.templateService.SelectTemplateByName(name);
            if (num > 0)
            {
                list.put("Count", 0);
                list.put("Code", 202);
                list.put("data", "模板名称已经存在！");
                JSONArray jo = JSONArray.fromObject(list);
                out.print(jo);
            }
            else
            {

                list.put("Count", 0);
                list.put("Code", 200);
                list.put("data", "可以使用该名称");
                JSONArray jo = JSONArray.fromObject(list);
                out.print(jo);
            }

        }
        else
        {
            list.put("Count", 0);
            list.put("Code", 201);
            list.put("data", "请传入模板名称");
            JSONArray jo = JSONArray.fromObject(list);
            out.print(jo);
        }
    }

    //根据旧模板数据，新模板名称和状态生成一条新的数据并修改详情表的template_id
    @PostMapping("/insertDataFromOldTemplateLayout")
    @ResponseBody
    public void insertDataFromOldTemplateLayout(HttpServletRequest request,
                                                HttpServletResponse response) throws IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> list = new HashMap<String, Object>();
        String template_id = "";
        if (request.getParameter("template_id") != null
                && !request.getParameter("template_id").equals("")) {
            template_id = request.getParameter("template_id");
        }
        String status = "";
        if (request.getParameter("status") != null
                && !request.getParameter("status").equals("")) {
            status = request.getParameter("status");
        }
        String template_name = "";
        if (request.getParameter("template_name") != null
                && !request.getParameter("template_name").equals("")) {
            template_name = request.getParameter("template_name");
        }
        Map map = new HashMap();
        if(!"".equals(template_id.trim()) && !"".equals(status.trim()) && !"".equals(template_name.trim())){
            map.put("template_id", template_id);
            map.put("status", status);
            map.put("template_name", template_name);
            int insertCount = templateService.insertDataFromOldTemplateLayout(map);
            if(insertCount > 0){
                int templateId = templateService.getMaxIdFromTemplateLayout();
                map.put("newTemplateId", templateId);
                int updateCount = templateService.updateTemplateDetailById(map);//应是新增操作
                SysTemplate_layout tempList = templateService
                        .getTemplateDetailById(String.valueOf(templateId));
                map.put("tempList", tempList);
                list.put("Count",1);
                list.put("Code",200);
                list.put("data",map);
                list.put("msg", "修改成功！");
            }else{
                list.put("Count",0);
                list.put("Code",201);
                list.put("data", null);
                list.put("msg", "修改失败！");
            }
        } else
        {
            list.put("Count", 0);
            list.put("Code", 202);
            list.put("data", map);
            list.put("msg", "缺少必要参数！");
        }
        JSONObject jo = JSONObject.fromObject(list);
        out.print(jo);
    }


    // 查询模板列表
    @PostMapping("/getAllTemplateList")
    @ResponseBody
    public void getAllTemplateList(HttpServletRequest request,
                                   HttpServletResponse response) throws IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> list = new HashMap<String, Object>();
        List<Map> tempList = this.templateService.selectAllTemplateList();
        if (tempList.size() > 0)
        {
            list.put("Count", 0);
            list.put("Code", 200);
            list.put("tempList", tempList);
            list.put("data", "获取成功！");
            JSONArray jo = JSONArray.fromObject(list);
            out.print(jo);
        }
        else
        {

            list.put("Count", 0);
            list.put("Code", 201);
            list.put("data", "获取失败");
            JSONArray jo = JSONArray.fromObject(list);
            out.print(jo);
        }
    }


    // 查询所属部门
    @PostMapping("/getAllSystemList")
    @ResponseBody
    public void getAllSystemList(HttpServletRequest request,
                                 HttpServletResponse response) throws IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> list = new HashMap<String, Object>();
        List<Map> tempList = templateService.selectAllSystem();
        if (tempList.size() > 0)
        {
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
            list.put("msg", "获取失败");
            JSONArray jo = JSONArray.fromObject(list);
            out.print(jo);
        }
    }

    // 查询设备类型
    @PostMapping("/getAllAssetTypeInfoList")
    @ResponseBody
    public void getAllAssetTypeInfoList(HttpServletRequest request,
                                 HttpServletResponse response) throws IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> list = new HashMap<String, Object>();
        List<Map> tempList = templateService.getAllAssetTypeInfoList();
        if (tempList.size() > 0)
        {
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
            list.put("msg", "获取失败");
            JSONArray jo = JSONArray.fromObject(list);
            out.print(jo);
        }
    }


    //关联单个控件
    @PostMapping("/AssociateControl")
    @ResponseBody
    public void AssociateControl(HttpServletRequest request,
                                 HttpServletResponse response) throws IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> list = new HashMap<String, Object>();
        int template_id=0;
        if(request.getParameter("template_id") != null
                && !request.getParameter("template_id").equals("")){
            template_id=Integer.parseInt(request.getParameter("template_id"));
        }
        String qrcode="";
        if(request.getParameter("qrcode") != null
                && !request.getParameter("qrcode").equals("")){
            qrcode=request.getParameter("qrcode");
        }
        String control_name="";
        if(request.getParameter("control_name") != null
                && !request.getParameter("control_name").equals("")){
            control_name=request.getParameter("control_name");
        }
        if (qrcode != null)
        {
            Map map = new HashMap();
            map.put("template_id",template_id);
            map.put("qrcode", qrcode);
            map.put("control_name", control_name);
            int count = templateService.updateTempContentByControlName(map);
            if(count > 0){
                list.put("Count",0);
                list.put("Code", 200);
                list.put("data", "保存更新成功");
                JSONArray jo = JSONArray.fromObject(list);
                out.print(jo);
            }else {
                list.put("Count", 0);
                list.put("Code", 201);
                list.put("data", "保存更新失败");
                JSONArray jo = JSONArray.fromObject(list);
                out.print(jo);
            }
        }
        else
        {
            list.put("Count", 0);
            list.put("Code", 202);
            list.put("data", "请传入参数！");
            JSONArray jo = JSONArray.fromObject(list);
            out.print(jo);
        }
    }

    //关联查询qrcode
    @PostMapping("/getQrcodeCheckList")
    @ResponseBody
    public void getQrcodeCheckList(HttpServletRequest request,
                                   HttpServletResponse response) throws IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> list = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();

        if (request.getParameter("newNameID") != null)
        {
            if (!"".equals(request.getParameter("newNameID")))
            {

                if (request.getParameter("newNameID") != null)
                {
                    if (!"".equals(request.getParameter("newNameID")))
                    {
                        map.put("device_name", request.getParameter("newNameID"));
                    }
                }
                if (request.getParameter("newTypeID") != null)
                {
                    if (!"".equals(request.getParameter("newTypeID")))
                    {
                        map.put("dept_id", request.getParameter("newTypeID"));
                    }
                }
                List<Map> tempList = this.templateService
                        .SelectQrcodeCheckList(map);
                list.put("Count", 0);
                list.put("Code", 200);
                list.put("msg", "获取成功");
                list.put("data", tempList);
                JSONArray jo = JSONArray.fromObject(list);
                out.print(jo);
            }
            else
            {
                list.put("Count", 0);
                list.put("Code", 201);
                list.put("msg", "获取失败");
                list.put("data", null);
                JSONArray jo = JSONArray.fromObject(list);
                out.print(jo);
            }
        }
        else
        {
            list.put("Count", 0);
            list.put("Code", 202);
            list.put("msg", "请传入newNameID");
            list.put("data", null);
            JSONArray jo = JSONArray.fromObject(list);
            out.print(jo);
        }
    }


    // 数据展示（模板、控件）
    @PostMapping("/getTemplateDetailByMouldCode")
    @ResponseBody
    public void getTemplateDetailByMouldCode(HttpServletRequest request,
                                             HttpServletResponse response) throws IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> list = new HashMap<String, Object>();

        if (request.getParameter("template_id") != null)
        {
            if (!"".equals(request.getParameter("template_id").trim()))
            {
                SysTemplate_layout tempList = templateService
                        .getTemplateDetailByMouldCode(request.getParameter("template_id"));
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
                list.put("msg", "获取失败,请传入template_id！");
                JSONArray jo = JSONArray.fromObject(list);
                out.print(jo);
            }
        }
        else
        {
            list.put("Count", 0);
            list.put("Code", 201);
            list.put("data", null);
            list.put("msg", "获取失败,请传入template_id！");
            JSONArray jo = JSONArray.fromObject(list);
            out.print(jo);
        }
    }

    //查询设备的使用状态
    @PostMapping("/getDeviceSeatStatusBy")
    @ResponseBody
    public void getDeviceSeatStatusBy(HttpServletRequest request,
                                             HttpServletResponse response) throws IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> list = new HashMap<String, Object>();

        try{
            int num = templateService.getDeviceSeatStatusBy();
            if(num>0){
                list.put("Count", 0);
                list.put("Code", 200);
                list.put("data", "1");
                list.put("msg", "获取成功！");
                JSONArray jo = JSONArray.fromObject(list);
                out.print(jo);
            }else {
                list.put("Count", 0);
                list.put("Code", 200);
                list.put("data", "0");
                list.put("msg", "获取成功！");
                JSONArray jo = JSONArray.fromObject(list);
                out.print(jo);
            }
        }catch (Exception e){
            list.put("Count", 0);
            list.put("Code", 201);
            list.put("data", null);
            list.put("Msg", "获取失败！");
            e.printStackTrace();
        }
    }


    //校验绑定的编码是否已绑定
    @PostMapping("/setUpQrCodeByTemplateId")
    @ResponseBody
    public void setUpQrCodeByTemplateId(HttpServletRequest request,
                                      HttpServletResponse response) throws IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> list = new HashMap<String, Object>();
        try{
            Map map=new HashMap();
            map.put("template_id",request.getParameter("template_id"));
            map.put("qrcode",request.getParameter("qrcode"));
            int num = templateService.setUpQrCodeByTemplateId(map);
            if(num>0){
                list.put("Count", 0);
                list.put("Code", 200);
                list.put("data", 1);
                list.put("msg", "获取成功！");
                JSONArray jo = JSONArray.fromObject(list);
                out.print(jo);
            }else {
                list.put("Count", 0);
                list.put("Code", 200);
                list.put("data", 0);
                list.put("msg", "获取成功！");
                JSONArray jo = JSONArray.fromObject(list);
                out.print(jo);
            }
        }catch (Exception e){
            list.put("Count", 0);
            list.put("Code", 201);
            list.put("data", null);
            list.put("Msg", "获取失败！");
            e.printStackTrace();
        }
    }

}
