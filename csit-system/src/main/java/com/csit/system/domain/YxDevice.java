package com.csit.system.domain;

import com.csit.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.csit.common.annotation.Excel;
import com.csit.common.core.domain.TreeEntity;

/**
 * 设备信息对象 yx_device
 * 
 * @author csit
 * @date 2019-12-14
 */
public class YxDevice extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 设备ID */
    private Long deviceId;


    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

    @Excel(name = "设备类型")
    private int deviceType;

    /** 网关ID */
    @Excel(name = "网关ID")
    private Long parentId;

    /** 目录 */
    @Excel(name = "目录")
    private String ancestors;

    /** 所属部门 */
    @Excel(name = "所属部门")
    private Long deptId;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String deviceName;

    /** 设备安装位置 */
    @Excel(name = "设备安装位置")
    private String deviceSite;

    /** 设备编码 */
    @Excel(name = "设备编码")
    private String deviceCode;

    /** 设备卡号 */
    @Excel(name = "设备卡号")
    private String deviceMsisdn;

    /** 显示顺序 */
    @Excel(name = "显示顺序")
    private Integer orderNum;

    /** 负责人 */
    @Excel(name = "负责人")
    private String leader;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String phone;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public SysDept getDept() {
        if (dept == null)
        {
            dept = new SysDept();
        }
        return dept;
    }

    public void setDept(SysDept dept) {
        this.dept = dept;
    }

    private SysDept dept;


    public void setDeviceId(Long deviceId) 
    {
        this.deviceId = deviceId;
    }

    public Long getDeviceId() 
    {
        return deviceId;
    }
    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
    }
    public void setAncestors(String ancestors) 
    {
        this.ancestors = ancestors;
    }

    public String getAncestors() 
    {
        return ancestors;
    }
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setDeviceName(String deviceName) 
    {
        this.deviceName = deviceName;
    }

    public String getDeviceName() 
    {
        return deviceName;
    }
    public void setDeviceSite(String deviceSite) 
    {
        this.deviceSite = deviceSite;
    }

    public String getDeviceSite() 
    {
        return deviceSite;
    }
    public void setDeviceCode(String deviceCode) 
    {
        this.deviceCode = deviceCode;
    }

    public String getDeviceCode() 
    {
        return deviceCode;
    }
    public void setDeviceMsisdn(String deviceMsisdn) 
    {
        this.deviceMsisdn = deviceMsisdn;
    }

    public String getDeviceMsisdn() 
    {
        return deviceMsisdn;
    }
    public void setOrderNum(Integer orderNum) 
    {
        this.orderNum = orderNum;
    }

    public Integer getOrderNum() 
    {
        return orderNum;
    }
    public void setLeader(String leader) 
    {
        this.leader = leader;
    }

    public String getLeader() 
    {
        return leader;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("deviceId", getDeviceId())
                .append("deviceType", getDeviceType())
            .append("parentId", getParentId())
            .append("ancestors", getAncestors())
            .append("deptId", getDeptId())
            .append("deviceName", getDeviceName())
            .append("deviceSite", getDeviceSite())
            .append("deviceCode", getDeviceCode())
            .append("deviceMsisdn", getDeviceMsisdn())
            .append("orderNum", getOrderNum())
            .append("leader", getLeader())
            .append("phone", getPhone())
            .append("email", getEmail())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime()).append("dept", getDept())
            .toString();
    }
}

