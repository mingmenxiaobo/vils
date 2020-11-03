package com.csit.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.csit.common.annotation.Excel;
import com.csit.common.core.domain.BaseEntity;

/**
 * 微信月对象 yx_wechart_device_month
 * 
 * @author csit
 * @date 2020-04-23
 */
public class YxWechartDeviceMonth extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 小程序openid */
    @Excel(name = "小程序openid")
    private String openid;

    /** 设备ID */
    @Excel(name = "设备ID")
    private String deviceid;

    /** 高度 */
    @Excel(name = "高度")
    private Long high;

    /** 是否有人：1：有人0：没人2：有人站立 */
    @Excel(name = "是否有人：1：有人0：没人2：有人站立")
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

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOpenid(String openid) 
    {
        this.openid = openid;
    }

    public String getOpenid() 
    {
        return openid;
    }
    public void setDeviceid(String deviceid) 
    {
        this.deviceid = deviceid;
    }

    public String getDeviceid() 
    {
        return deviceid;
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
    public void setDuration(Long duration) 
    {
        this.duration = duration;
    }

    public void setCreatetime(String createtime)
    {
        this.createtime = createtime;
    }

    public String getCreatetime()
    {
        return createtime;
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
            .append("openid", getOpenid())
            .append("deviceid", getDeviceid())
            .append("high", getHigh())
            .append("seatstatus", getSeatstatus())
            .append("createtime", getCreatetime())
            .append("duration", getDuration())
            .append("starttime", getStarttime())
            .toString();
    }
}
