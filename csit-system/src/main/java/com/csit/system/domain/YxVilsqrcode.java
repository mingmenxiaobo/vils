package com.csit.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.csit.common.annotation.Excel;
import com.csit.common.core.domain.BaseEntity;

/**
 * 编码生成对象 yx_vilsqrcode
 * 
 * @author csit
 * @date 2020-06-27
 */
public class YxVilsqrcode extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编码索引 */
    private Long codeindex;

    /** 编码 */
    @Excel(name = "编码")
    private String qrcode;

    public void setCodeindex(Long codeindex) 
    {
        this.codeindex = codeindex;
    }

    public Long getCodeindex() 
    {
        return codeindex;
    }
    public void setQrcode(String qrcode) 
    {
        this.qrcode = qrcode;
    }

    public String getQrcode() 
    {
        return qrcode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("codeindex", getCodeindex())
            .append("qrcode", getQrcode())
            .toString();
    }
}
