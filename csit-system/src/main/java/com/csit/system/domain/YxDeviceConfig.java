package com.csit.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.csit.common.annotation.Excel;
import com.csit.common.core.domain.BaseEntity;

/**
 * 绑定设备对象 yx_device_config
 * 
 * @author csit
 * @date 2020-02-06
 */
public class YxDeviceConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long deviceConfigId;

    /** 二维码 */
    @Excel(name = "二维码")
    private String qrcode;

    /** 设备id */
    @Excel(name = "设备id")
    private String deviceId;

    public void setDeviceConfigId(Long deviceConfigId) 
    {
        this.deviceConfigId = deviceConfigId;
    }

    public Long getDeviceConfigId() 
    {
        return deviceConfigId;
    }
    public void setQrcode(String qrcode) 
    {
        this.qrcode = qrcode;
    }

    public String getQrcode() 
    {
        return qrcode;
    }
    public void setDeviceId(String deviceId) 
    {
        this.deviceId = deviceId;
    }

    public String getDeviceId() 
    {
        return deviceId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("deviceConfigId", getDeviceConfigId())
            .append("qrcode", getQrcode())
            .append("deviceId", getDeviceId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
