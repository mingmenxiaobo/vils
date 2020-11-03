package com.csit.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.csit.common.annotation.Excel;
import com.csit.common.core.domain.BaseEntity;

/**
 * 维修记录对象 yx_assetfaultlog
 * 
 * @author csit
 * @date 2020-02-07
 */
public class YxAssetfaultlog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 设备编码 */
    private String qrcode;

    /** 故障描述 */
    private String fault;

    /** 维修方案 */
    private String repair;
    /** 创建时间 */
    private String createby;
    /** 创建人 */
    private String createtime;



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
    public void setFault(String fault) 
    {
        this.fault = fault;
    }

    public String getFault() 
    {
        return fault;
    }
    public void setRepair(String repair) 
    {
        this.repair = repair;
    }

    public String getRepair() 
    {
        return repair;
    }


    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("qrcode", getQrcode())
            .append("fault", getFault())
            .append("repair", getRepair())
            .append("createby", getCreateby())
            .append("createtime", getCreatetime())
            .toString();
    }
}
