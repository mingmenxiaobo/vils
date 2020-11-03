package com.csit.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.csit.common.annotation.Excel;
import com.csit.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 微信信息对象 yx_wechart_info
 * 
 * @author csit
 * @date 2020-04-23
 */
public class YxWechartInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long id;

    /** null */
    @Excel(name = "null")
    private String openid;

    /** null */
    @Excel(name = "null")
    private String nickname;

    private Date  updatetime;
    /** null */
    @Excel(name = "null")
    private String avatarurl;

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
    public void setNickname(String nickname) 
    {
        this.nickname = nickname;
    }

    public String getNickname() 
    {
        return nickname;
    }
    public void setAvatarurl(String avatarurl) 
    {
        this.avatarurl = avatarurl;
    }

    public String getAvatarurl() 
    {
        return avatarurl;
    }

    public Date getUpdateTime()
    {
        return updatetime;
    }

    public void setUpdateTime(Date loginDate)
    {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("openid", getOpenid())
            .append("nickname", getNickname())
            .append("avatarurl", getAvatarurl())
                .append("updatetime", getUpdateTime())
            .toString();
    }
}
