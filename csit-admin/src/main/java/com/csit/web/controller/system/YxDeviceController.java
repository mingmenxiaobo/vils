package com.csit.web.controller.system;

import java.util.List;

import com.csit.framework.util.ShiroUtils;
import com.csit.system.domain.YxAssetinfoList;
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
import com.csit.system.domain.YxDevice;
import com.csit.system.service.IYxDeviceService;
import com.csit.common.core.controller.BaseController;
import com.csit.common.core.domain.AjaxResult;
import com.csit.common.utils.poi.ExcelUtil;
import com.csit.common.utils.StringUtils;
import com.csit.common.core.domain.Ztree;
import org.springframework.web.multipart.MultipartFile;

/**
 * 设备信息Controller
 *
 * @author csit
 * @date 2019-12-14
 */
@Controller
@RequestMapping("/system/device")
public class YxDeviceController extends BaseController
{
    private String prefix = "system/device";

    @Autowired
    private IYxDeviceService yxDeviceService;

    @RequiresPermissions("system:device:view")
    @GetMapping()
    public String device()
    {
        return prefix + "/device";
    }

    /**
     * 查询设备信息树列表
     */
    @RequiresPermissions("system:device:list")
    @PostMapping("/list")
    @ResponseBody
    public List<YxDevice> list(YxDevice yxDevice)
        {
        List<YxDevice> list = yxDeviceService.selectYxDeviceList(yxDevice);
        return list;
    }

    /**
     * 导出设备信息列表
     */
    @RequiresPermissions("system:device:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(YxDevice yxDevice)
    {
        List<YxDevice> list = yxDeviceService.selectYxDeviceList(yxDevice);
        ExcelUtil<YxDevice> util = new ExcelUtil<YxDevice>(YxDevice.class);
        return util.exportExcel(list, "device");
    }

    /**
     * 新增设备信息
     */
    @GetMapping(value = { "/add/{deviceId}", "/add/" })
    public String add(@PathVariable(value = "deviceId", required = false) Long deviceId, ModelMap mmap)
    {
        if (StringUtils.isNotNull(deviceId))
        {
            mmap.put("yxDevice", yxDeviceService.selectYxDeviceById(deviceId));
        }
        return prefix + "/add";
    }

    /**
     * 新增保存设备信息
     */
    @RequiresPermissions("system:device:add")
    @Log(title = "设备信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(YxDevice yxDevice)
    {
        return toAjax(yxDeviceService.insertYxDevice(yxDevice));
    }

    /**
     * 修改设备信息
     */
    @GetMapping("/edit/{deviceId}")
    public String edit(@PathVariable("deviceId") Long deviceId, ModelMap mmap)
    {
        YxDevice yxDevice = yxDeviceService.selectYxDeviceById(deviceId);
        mmap.put("yxDevice", yxDevice);
        return prefix + "/edit";
    }

    /**
     * 修改保存设备信息
     */
    @RequiresPermissions("system:device:edit")
    @Log(title = "设备信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(YxDevice yxDevice)
    {
        return toAjax(yxDeviceService.updateYxDevice(yxDevice));
    }

    /**
     * 删除
     */
    @RequiresPermissions("system:device:remove")
    @Log(title = "设备信息", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{deviceId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("deviceId") Long deviceId)
    {
        return toAjax(yxDeviceService.deleteYxDeviceById(deviceId));
    }

    /**
     * 选择设备信息树
     */
    @GetMapping(value = { "/selectDeviceTree/{deviceId}", "/selectDeviceTree/" })
    public String selectDeviceTree(@PathVariable(value = "deviceId", required = false) Long deviceId, ModelMap mmap)
    {
        if (StringUtils.isNotNull(deviceId))
        {
            mmap.put("yxDevice", yxDeviceService.selectYxDeviceById(deviceId));
        }
        return prefix + "/tree";
    }

    /**
     * 加载设备信息树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = yxDeviceService.selectYxDeviceTree();
        return ztrees;
    }
    @Log(title = "设备管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("system:device:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<YxDevice> util = new ExcelUtil<YxDevice>(YxDevice.class);
        List<YxDevice> devList = util.importExcel(file.getInputStream());
        String operName = ShiroUtils.getSysUser().getLoginName();
        String message=yxDeviceService.importDevice(devList, updateSupport, operName);
        return AjaxResult.success(message);
    }

    @RequiresPermissions("system:device:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<YxDevice> util = new ExcelUtil<YxDevice>(YxDevice.class);
        return util.importTemplateExcel("网关传感器");
    }
}