package com.csit.web.controller.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csit.system.domain.YxDeviceDay;
import com.csit.web.controller.tool.Ten2ThirtySix;
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
import com.csit.system.domain.YxVilsqrcode;
import com.csit.system.service.IYxVilsqrcodeService;
import com.csit.common.core.controller.BaseController;
import com.csit.common.core.domain.AjaxResult;
import com.csit.common.utils.poi.ExcelUtil;
import com.csit.common.core.page.TableDataInfo;

/**
 * 编码生成Controller
 * 
 * @author csit
 * @date 2020-06-27
 */
@Controller
@RequestMapping("/system/vilsqrcode")
public class YxVilsqrcodeController extends BaseController
{
    private String prefix = "system/vilsqrcode";

    @Autowired
    private IYxVilsqrcodeService yxVilsqrcodeService;

    @RequiresPermissions("system:vilsqrcode:view")
    @GetMapping()
    public String vilsqrcode()
    {
        return prefix + "/vilsqrcode";
    }

    /**
     * 查询编码生成列表
     */
    @RequiresPermissions("system:vilsqrcode:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(YxVilsqrcode yxVilsqrcode)
    {
        startPage();
        List<YxVilsqrcode> list = yxVilsqrcodeService.selectYxVilsqrcodeList(yxVilsqrcode);
        return getDataTable(list);
    }

    /**
     * 导出编码生成列表
     */
    @RequiresPermissions("system:vilsqrcode:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(YxVilsqrcode yxVilsqrcode)
    {
        List<YxVilsqrcode> list = yxVilsqrcodeService.selectYxVilsqrcodeList(yxVilsqrcode);
        ExcelUtil<YxVilsqrcode> util = new ExcelUtil<YxVilsqrcode>(YxVilsqrcode.class);
        return util.exportExcel(list, "vilsqrcode");
    }

    /**
     * 新增编码生成
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存编码生成
     */
    @RequiresPermissions("system:vilsqrcode:add")
    @Log(title = "编码生成", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(YxVilsqrcode yxVilsqrcode)
    {
        return toAjax(yxVilsqrcodeService.insertYxVilsqrcode(yxVilsqrcode));
    }


    /**
     * 批量新增保存编码生成
     */

    @Log(title = "编码生成", businessType = BusinessType.INSERT)
    @GetMapping("/addTest")
    @ResponseBody
    public String addTest() {
        long start_Time = 0, end_Time = 0;
        Ten2ThirtySix Six= new Ten2ThirtySix();
        //计算循环使用的时间
        start_Time = System.currentTimeMillis(); //从1970-1-1到现在的毫秒

        String str[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};
        List<YxVilsqrcode> list = new ArrayList<>();

        for (int i =10000001; i <= 10100000; i++) {

            YxVilsqrcode qrcode = new YxVilsqrcode();
            String s = Six.DeciamlToThirtySix(i);
            System.out.println(s);
            String Str="0000000"+s;
            String Code=Str.substring(Str.length()-7,Str.length());

            qrcode.setCodeindex((long)i);
            qrcode.setQrcode(Code);
            list.add(qrcode);

            if (i % 10000 == 0) {
                yxVilsqrcodeService.insertYxVilsqrcodeBatch(list);
                list = new ArrayList<>();
                System.out.println("每十万输出一次：" + i + " - " + Code);
            }

        }
        end_Time = System.currentTimeMillis();
        System.out.println("for循环结束,");
        System.out.printf("循环使用时间是: %d 毫秒\n", (end_Time - start_Time));
      return "循环使用时间是: %d 毫秒\n"+(end_Time - start_Time);
    }

    /**
     * 修改编码生成
     */
    @GetMapping("/edit/{codeindex}")
    public String edit(@PathVariable("codeindex") Long codeindex, ModelMap mmap)
    {
        YxVilsqrcode yxVilsqrcode = yxVilsqrcodeService.selectYxVilsqrcodeById(codeindex);
        mmap.put("yxVilsqrcode", yxVilsqrcode);
        return prefix + "/edit";
    }

    /**
     * 修改保存编码生成
     */
    @RequiresPermissions("system:vilsqrcode:edit")
    @Log(title = "编码生成", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(YxVilsqrcode yxVilsqrcode)
    {
        return toAjax(yxVilsqrcodeService.updateYxVilsqrcode(yxVilsqrcode));
    }

    /**
     * 删除编码生成
     */
    @RequiresPermissions("system:vilsqrcode:remove")
    @Log(title = "编码生成", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(yxVilsqrcodeService.deleteYxVilsqrcodeByIds(ids));
    }
}
