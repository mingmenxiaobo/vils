package com.csit.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.csit.common.annotation.Excel;
import com.csit.common.core.domain.BaseEntity;

/**
 * 绑定升降桌对象 yx_qrcode_config
 * 
 * @author csit
 * @date 2020-04-30
 */
public class YxQrcodeConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long qrcodeConfigId;

    /** 二维码 */
    @Excel(name = "二维码")
    private String qrcode;

    /** 小程序openid */
    @Excel(name = "小程序openid")
    private String openid;

    public void setQrcodeConfigId(Long qrcodeConfigId) 
    {
        this.qrcodeConfigId = qrcodeConfigId;
    }

    public Long getQrcodeConfigId() 
    {
        return qrcodeConfigId;
    }
    public void setQrcode(String qrcode) 
    {
        this.qrcode = qrcode;
    }

    public String getQrcode() 
    {
        return qrcode;
    }
    public void setOpenid(String openid) 
    {
        this.openid = openid;
    }

    public String getOpenid() 
    {
        return openid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("qrcodeConfigId", getQrcodeConfigId())
            .append("qrcode", getQrcode())
            .append("openid", getOpenid())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
