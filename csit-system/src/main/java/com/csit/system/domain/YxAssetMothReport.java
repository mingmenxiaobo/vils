package com.csit.system.domain;

import com.csit.common.annotation.Excel;
import com.csit.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 产品信息对象 yx_assetinfo_list
 * 
 * @author csit
 * @date 2019-12-14
 */
public class YxAssetMothReport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
   // @Excel(name = "ID")
    private Long id;

    /** 二维码 */
    private String qrcode;

    /** 显示码 */
    @Excel(name = "显示码")
    private String viewcode;

    /** 制造商 */
    @Excel(name = "制造商")
    private String manufacturer;

    ///** 出厂日期 */
   // @Excel(name = "出厂日期")
    private String productionDate;

    /** 安装日期 */
    //@Excel(name = "安装日期")
    private String installDate;

    /** 保用期限 */
    //@Excel(name = "保用期限")
    private String maintenancePeriod;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String customerName;

    /** 合同号 */
    //@Excel(name = "合同号")
    private String contractNumber;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 单价 */
    //@Excel(name = "单价")
    private Double unitPrice;

    /** 备注1 */
    @Excel(name = "备注1")
    private String remark1;

    /** 备注2 */
    @Excel(name = "备注2")
    private String remark2;

    //@Excel(name = "部门")
    private String dept;


    @Excel(name = "日期")
    private String endDay;

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }


    public Long getUseDay() {
        return useDay;
    }

    public void setUseDay(Long useDay) {
        this.useDay = useDay;
    }

    @Excel(name = "使用天数")
    private Long  useDay;


    public Long getFreeDay() {
        return freeDay;
    }

    public void setFreeDay(Long freeDay) {
        this.freeDay = freeDay;
    }

    @Excel(name = "空闲天数")
    private Long  freeDay;

    @Excel(name = "使用时长(分钟)")
    private String duration;

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }


    @Excel(name = "空闲时长 (分钟)")
    private String free;

    public String getFree() {
        return free;
    }
    public void setFree(String free) {
        this.free = free;
    }



    @Excel(name = "使用率 %")
    private String usep;

    public String getUsep() {
        return usep;
    }

    public void setUsep(String usep) {
        this.usep = usep;
    }

    @Excel(name = "空闲率 %")
    private String unusep;
    public String getUnusep() {
        return unusep;
    }

    public void setUnusep(String unusep) {
        this.unusep = unusep;
    }


    private String deptId;
    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }


    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setQrcode(String qrcode) 
    {
        this.qrcode = qrcode;
    }

    public String getQrcode() 
    {
        return qrcode;
    }
    public void setViewcode(String viewcode) 
    {
        this.viewcode = viewcode;
    }

    public String getViewcode() 
    {
        return viewcode;
    }
    public void setManufacturer(String manufacturer) 
    {
        this.manufacturer = manufacturer;
    }

    public String getManufacturer() 
    {
        return manufacturer;
    }
    public void setProductionDate(String productionDate) 
    {
        this.productionDate = productionDate;
    }

    public String getProductionDate() 
    {
        return productionDate;
    }
    public void setInstallDate(String installDate)
    {
        this.installDate = installDate;
    }

    public String getInstallDate()
    {
        return installDate;
    }
    public void setMaintenancePeriod(String maintenancePeriod) 
    {
        this.maintenancePeriod = maintenancePeriod;
    }

    public String getMaintenancePeriod() 
    {
        return maintenancePeriod;
    }
    public void setCustomerName(String customerName) 
    {
        this.customerName = customerName;
    }

    public String getCustomerName() 
    {
        return customerName;
    }
    public void setContractNumber(String contractNumber) 
    {
        this.contractNumber = contractNumber;
    }

    public String getContractNumber() 
    {
        return contractNumber;
    }
    public void setProductName(String productName) 
    {
        this.productName = productName;
    }

    public String getProductName() 
    {
        return productName;
    }
    public void setUnitPrice(Double unitPrice) 
    {
        this.unitPrice = unitPrice;
    }

    public Double getUnitPrice() 
    {
        return unitPrice;
    }
    public void setRemark1(String remark1) 
    {
        this.remark1 = remark1;
    }

    public String getRemark1() 
    {
        return remark1;
    }
    public void setRemark2(String remark2) 
    {
        this.remark2 = remark2;
    }

    public String getRemark2() 
    {
        return remark2;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("qrcode", getQrcode())
            .append("viewcode", getViewcode())
            .append("manufacturer", getManufacturer())
            .append("productionDate", getProductionDate())
            .append("installDateinstall date", getInstallDate())
            .append("maintenancePeriod", getMaintenancePeriod())
            .append("customerName", getCustomerName())
            .append("contractNumber", getContractNumber())
            .append("productName", getProductName())
            .append("unitPrice", getUnitPrice())
            .append("remark1", getRemark1())
            .append("remark2", getRemark2())
            .append("free", getFree())
            .append("duration", getDuration())
            .append("usep", getUsep())
            .append("unusep", getUnusep())
            .append("endDay", getEndDay())
            .append("deptId", getDeptId())
            .append("useDay", getUseDay())
            .append("freeDay", getFreeDay())
            .toString();
    }
}
