package com.csit.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.csit.common.annotation.Excel;
import com.csit.common.core.domain.BaseEntity;

/**
 * zaixian对象 yx_querybyopenid
 * 
 * @author csit
 * @date 2020-03-28
 */
public class YxQuerybyopenid extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long id;

    /** null */
    @Excel(name = "null")
    private String openid;

    /** null */
    @Excel(name = "null")
    private String qrcode;

    /** 时间 */
    @Excel(name = "时间")
    private String createtime;


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
    public void setQrcode(String qrcode) 
    {
        this.qrcode = qrcode;
    }

    public String getQrcode() 
    {
        return qrcode;
    }

    public void setCreatetime(String createtime)
    {
        this.createtime = createtime;
    }

    public String getCreatetime()
    {
        return createtime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("openid", getOpenid())
            .append("qrcode", getQrcode())
                .append("createtime", getCreatetime())
            .toString();
    }
}
