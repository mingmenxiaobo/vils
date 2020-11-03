package com.csit.web.controller.system;

import java.util.List;
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
import com.csit.system.domain.YxAssetfaultlog;
import com.csit.system.service.IYxAssetfaultlogService;
import com.csit.common.core.controller.BaseController;
import com.csit.common.core.domain.AjaxResult;
import com.csit.common.utils.poi.ExcelUtil;
import com.csit.common.core.page.TableDataInfo;

/**
 * 维修记录Controller
 * 
 * @author csit
 * @date 2020-02-07
 */
@Controller
@RequestMapping("/system/assetfaultlog")
public class YxAssetfaultlogController extends BaseController
{
    private String prefix = "system/assetfaultlog";

    @Autowired
    private IYxAssetfaultlogService yxAssetfaultlogService;

    @RequiresPermissions("system:assetfaultlog:view")
    @GetMapping()
    public String assetfaultlog()
    {
        return prefix + "/assetfaultlog";
    }

    /**
     * 查询维修记录列表
     */
    @RequiresPermissions("system:assetfaultlog:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(YxAssetfaultlog yxAssetfaultlog)
    {
        startPage();
        List<YxAssetfaultlog> list = yxAssetfaultlogService.selectYxAssetfaultlogList(yxAssetfaultlog);
        return getDataTable(list);
    }

    /**
     * 导出维修记录列表
     */
    @RequiresPermissions("system:assetfaultlog:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(YxAssetfaultlog yxAssetfaultlog)
    {
        List<YxAssetfaultlog> list = yxAssetfaultlogService.selectYxAssetfaultlogList(yxAssetfaultlog);
        ExcelUtil<YxAssetfaultlog> util = new ExcelUtil<YxAssetfaultlog>(YxAssetfaultlog.class);
        return util.exportExcel(list, "assetfaultlog");
    }

    /**
     * 新增维修记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存维修记录
     */
    @RequiresPermissions("system:assetfaultlog:add")
    @Log(title = "维修记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(YxAssetfaultlog yxAssetfaultlog)
    {
        return toAjax(yxAssetfaultlogService.insertYxAssetfaultlog(yxAssetfaultlog));
    }

    /**
     * 修改维修记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        YxAssetfaultlog yxAssetfaultlog = yxAssetfaultlogService.selectYxAssetfaultlogById(id);
        mmap.put("yxAssetfaultlog", yxAssetfaultlog);
        return prefix + "/edit";
    }

    /**
     * 修改保存维修记录
     */
    @RequiresPermissions("system:assetfaultlog:edit")
    @Log(title = "维修记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(YxAssetfaultlog yxAssetfaultlog)
    {
        return toAjax(yxAssetfaultlogService.updateYxAssetfaultlog(yxAssetfaultlog));
    }

    /**
     * 删除维修记录
     */
    @RequiresPermissions("system:assetfaultlog:remove")
    @Log(title = "维修记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(yxAssetfaultlogService.deleteYxAssetfaultlogByIds(ids));
    }
}
