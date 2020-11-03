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
import com.csit.system.domain.YxDeviceConfig;
import com.csit.system.service.IYxDeviceConfigService;
import com.csit.common.core.controller.BaseController;
import com.csit.common.core.domain.AjaxResult;
import com.csit.common.utils.poi.ExcelUtil;
import com.csit.common.core.page.TableDataInfo;

/**
 * 绑定设备Controller
 * 
 * @author csit
 * @date 2020-02-06
 */
@Controller
@RequestMapping("/system/deviceLink")
public class YxDeviceConfigController extends BaseController
{
    private String prefix = "system/deviceLink";

    @Autowired
    private IYxDeviceConfigService yxDeviceConfigService;

    @RequiresPermissions("system:deviceLink:view")
    @GetMapping()
    public String deviceLink()
    {
        return prefix + "/deviceLink";
    }

    /**
     * 查询绑定设备列表
     */
    @RequiresPermissions("system:deviceLink:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(YxDeviceConfig yxDeviceConfig)
    {
        startPage();
        List<YxDeviceConfig> list = yxDeviceConfigService.selectYxDeviceConfigList(yxDeviceConfig);
        return getDataTable(list);
    }

    /**
     * 导出绑定设备列表
     */
    @RequiresPermissions("system:deviceLink:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(YxDeviceConfig yxDeviceConfig)
    {
        List<YxDeviceConfig> list = yxDeviceConfigService.selectYxDeviceConfigList(yxDeviceConfig);
        ExcelUtil<YxDeviceConfig> util = new ExcelUtil<YxDeviceConfig>(YxDeviceConfig.class);
        return util.exportExcel(list, "deviceLink");
    }

    /**
     * 新增绑定设备
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存绑定设备
     */
    @RequiresPermissions("system:deviceLink:add")
    @Log(title = "绑定设备", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(YxDeviceConfig yxDeviceConfig)
    {
        return toAjax(yxDeviceConfigService.insertYxDeviceConfig(yxDeviceConfig));
    }

    /**
     * 修改绑定设备
     */
    @GetMapping("/edit/{deviceConfigId}")
    public String edit(@PathVariable("deviceConfigId") Long deviceConfigId, ModelMap mmap)
    {
        YxDeviceConfig yxDeviceConfig = yxDeviceConfigService.selectYxDeviceConfigById(deviceConfigId);
        mmap.put("yxDeviceConfig", yxDeviceConfig);
        return prefix + "/edit";
    }

    /**
     * 修改保存绑定设备
     */
    @RequiresPermissions("system:deviceLink:edit")
    @Log(title = "绑定设备", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(YxDeviceConfig yxDeviceConfig)
    {
        return toAjax(yxDeviceConfigService.updateYxDeviceConfig(yxDeviceConfig));
    }

    /**
     * 删除绑定设备
     */
    @RequiresPermissions("system:deviceLink:remove")
    @Log(title = "绑定设备", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(yxDeviceConfigService.deleteYxDeviceConfigByIds(ids));
    }
}
