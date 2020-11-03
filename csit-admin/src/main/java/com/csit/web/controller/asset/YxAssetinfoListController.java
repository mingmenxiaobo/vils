package com.csit.web.controller.asset;

import java.util.List;
import java.util.UUID;

import com.csit.framework.util.ShiroUtils;
import com.csit.system.domain.SysUser;
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
import com.csit.system.domain.YxAssetinfoList;
import com.csit.system.service.IYxAssetinfoListService;
import com.csit.common.core.controller.BaseController;
import com.csit.common.core.domain.AjaxResult;
import com.csit.common.utils.poi.ExcelUtil;
import com.csit.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 产品信息Controller
 * 
 * @author csit
 * @date 2019-12-14
 */
@Controller
@RequestMapping("/system/list")
public class YxAssetinfoListController extends BaseController
{
    private String prefix = "system/list";

    @Autowired
    private IYxAssetinfoListService yxAssetinfoListService;

    @RequiresPermissions("system:list:view")
    @GetMapping()
    public String list()
    {
        return prefix + "/list";
    }

    /**
     * 查询产品信息列表
     */
    @RequiresPermissions("system:list:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(YxAssetinfoList yxAssetinfoList)
    {
        startPage();
        List<YxAssetinfoList> list = yxAssetinfoListService.selectYxAssetinfoListList(yxAssetinfoList);
        return getDataTable(list);
    }

    /**
     * 导出产品信息列表
     */
    @RequiresPermissions("system:list:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(YxAssetinfoList yxAssetinfoList)
    {
        List<YxAssetinfoList> list = yxAssetinfoListService.selectYxAssetinfoListList(yxAssetinfoList);
        ExcelUtil<YxAssetinfoList> util = new ExcelUtil<YxAssetinfoList>(YxAssetinfoList.class);
        return util.exportExcel(list, "list");
    }

    /**
     * 新增产品信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存产品信息
     */
    @RequiresPermissions("system:list:add")
    @Log(title = "产品信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(YxAssetinfoList yxAssetinfoList)
    {
        String  qrcode=  UUID.randomUUID().toString().trim().replaceAll("-", "");
        yxAssetinfoList.setQrcode(qrcode);
        return toAjax(yxAssetinfoListService.insertYxAssetinfoList(yxAssetinfoList));
    }

    /**
     * 修改产品信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        YxAssetinfoList yxAssetinfoList = yxAssetinfoListService.selectYxAssetinfoListById(id);
        mmap.put("yxAssetinfoList", yxAssetinfoList);
        return prefix + "/edit";
    }

    /**
     * 修改保存产品信息
     */
    @RequiresPermissions("system:list:edit")
    @Log(title = "产品信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(YxAssetinfoList yxAssetinfoList)
    {
        return toAjax(yxAssetinfoListService.updateYxAssetinfoList(yxAssetinfoList));
    }

    /**
     * 删除产品信息
     */
    @RequiresPermissions("system:list:remove")
    @Log(title = "产品信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(yxAssetinfoListService.deleteYxAssetinfoListByIds(ids));
    }

    @Log(title = "升降桌管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("system:list:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<YxAssetinfoList> util = new ExcelUtil<YxAssetinfoList>(YxAssetinfoList.class);
        List<YxAssetinfoList> assList = util.importExcel(file.getInputStream());
        String operName = ShiroUtils.getSysUser().getLoginName();
        String message=yxAssetinfoListService.importAsset(assList, updateSupport, operName);
        return AjaxResult.success(message);
    }

    @RequiresPermissions("system:list:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<YxAssetinfoList> util = new ExcelUtil<YxAssetinfoList>(YxAssetinfoList.class);
        return util.importTemplateExcel("升降桌");
    }
}
