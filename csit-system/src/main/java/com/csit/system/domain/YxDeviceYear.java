package com.csit.system.domain;

import com.csit.common.annotation.Excel;
import com.csit.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 设备信息采集对象 yx_device_day
 * 
 * @author csit
 * @date 2019-11-26
 */
public class YxDeviceYear extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private String id;

    /** 设备ID */
    @Excel(name = "设备ID")
    private String deviceid;

    /** 网关ID */
    @Excel(name = "网关ID")
    private String gatewayid;

    /** 高度 */
    @Excel(name = "高度")
    private Long high;

    /** 状态 */
    @Excel(name = "状态")
    private Long seatstatus;

    /** 时间 */
    @Excel(name = "时间")
    private String createtime;


    /** 持续时长 */
    @Excel(name = "持续时长")
    private Long duration;

    /** 开始时间戳 */
    @Excel(name = "开始时间戳")
    private String starttime;


    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setDeviceid(String deviceid)
    {
        this.deviceid = deviceid;
    }

    public String getDeviceid()
    {
        return deviceid;
    }
    public void setGatewayid(String gatewayid)
    {
        this.gatewayid = gatewayid;
    }

    public String getGatewayid()
    {
        return gatewayid;
    }

    public void setHigh(Long high)
    {
        this.high = high;
    }

    public Long getHigh()
    {
        return high;
    }
    public void setSeatstatus(Long seatstatus)
    {
        this.seatstatus = seatstatus;
    }
    public Long getSeatstatus()
    {
        return seatstatus;
    }


    public void setCreatetime(String createtime)
    {
        this.createtime = createtime;
    }

    public String getCreatetime()
    {
        return createtime;
    }
    public void setDuration(Long duration)
    {
        this.duration = duration;
    }

    public Long getDuration()
    {
        return duration;
    }
    public void setStarttime(String starttime)
    {
        this.starttime = starttime;
    }

    public String getStarttime()
    {
        return starttime;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("deviceid", getDeviceid())
            .append("gatewayid", getGatewayid())
            .append("high", getHigh())
            .append("seatstatus", getSeatstatus())
            .append("createtime", getCreatetime())
                .append("duration", getDuration())
                .append("starttime", getStarttime())
            .toString();
    }
}
