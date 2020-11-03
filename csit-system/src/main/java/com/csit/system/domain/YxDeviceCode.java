package com.csit.system.domain;

import com.csit.common.annotation.Excel;
import com.csit.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 设备信息对象 yx_device
 * 
 * @author csit
 * @date 2019-12-14
 */
public class YxDeviceCode
{
    private static final long serialVersionUID = 1L;


    /** 设备编码 */
    @Excel(name = "设备编码")
    private String deviceCode;


    public void setDeviceCode(String deviceCode)
    {
        this.deviceCode = deviceCode;
    }

    public String getDeviceCode() 
    {
        return deviceCode;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
             .append("deviceCode", getDeviceCode())
             .toString();
    }
}

