package com.csit.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class SysTemplate_layout {
    private int template_id;
    private String template_name;
    private String template_type;
    private int width;
    private int height;
    private String base_map;
    private int map_width;
    private int map_height;
    private String createTime;
    private String modifyDate;
    private String createUser;
    private String modifyUser;
    private String status;
    private Long deptId;
    private List<SysTemplate_detail> detailList;
    private List<YxDevice> detailName;

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public List<YxDevice> getDetailName() {
        return detailName;
    }

    public void setDetailName(List<YxDevice> detailName) {
        this.detailName = detailName;
    }

    public int getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(int template_id) {
        this.template_id = template_id;
    }

    public String getTemplate_name() {
        return template_name;
    }

    public void setTemplate_name(String template_name) {
        this.template_name = template_name;
    }

    public String getTemplate_type() {
        return template_type;
    }

    public void setTemplate_type(String template_type) {
        this.template_type = template_type;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getBase_map() {
        return base_map;
    }

    public void setBase_map(String base_map) {
        this.base_map = base_map;
    }

    public int getMap_width() {
        return map_width;
    }

    public void setMap_width(int map_width) {
        this.map_width = map_width;
    }

    public int getMap_height() {
        return map_height;
    }

    public void setMap_height(int map_height) {
        this.map_height = map_height;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SysTemplate_detail> getDetailList()
    {
        return detailList;
    }

    public void setDetailList(List<SysTemplate_detail> detailList)
    {
        this.detailList = detailList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("template_id", getTemplate_id())
                .append("template_name", getTemplate_name())
                .append("template_type", getTemplate_type())
                .append("width", getWidth())
                .append("height", getHeight())
                .append("base_map", getBase_map())
                .append("map_width", getMap_width())
                .append("map_height", getMap_height())
                .append("createTime", getCreateTime())
                .append("modifyDate", getModifyDate())
                .append("createUser", getCreateUser())
                .append("modifyUser", getModifyUser())
                .append("status", getStatus())
                .append("deptId", getDeptId())
                .toString();
    }

}
