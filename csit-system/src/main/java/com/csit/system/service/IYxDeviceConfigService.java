package com.csit.system.service;

import com.csit.system.domain.YxDeviceConfig;
import java.util.List;

/**
 * 绑定设备Service接口
 * 
 * @author csit
 * @date 2020-02-06
 */
public interface IYxDeviceConfigService 
{
    /**
     * 查询绑定设备
     * 
     * @param deviceConfigId 绑定设备ID
     * @return 绑定设备
     */
    public YxDeviceConfig selectYxDeviceConfigById(Long deviceConfigId);

    /**
     * 查询绑定设备列表
     * 
     * @param yxDeviceConfig 绑定设备
     * @return 绑定设备集合
     */
    public List<YxDeviceConfig> selectYxDeviceConfigList(YxDeviceConfig yxDeviceConfig);

    /**
     * 新增绑定设备
     * 
     * @param yxDeviceConfig 绑定设备
     * @return 结果
     */
    public int insertYxDeviceConfig(YxDeviceConfig yxDeviceConfig);

    /**
     * 修改绑定设备
     * 
     * @param yxDeviceConfig 绑定设备
     * @return 结果
     */
    public int updateYxDeviceConfig(YxDeviceConfig yxDeviceConfig);

    /**
     * 批量删除绑定设备
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteYxDeviceConfigByIds(String ids);

    /**
     * 删除绑定设备信息
     * 
     * @param deviceConfigId 绑定设备ID
     * @return 结果
     */
    public int deleteYxDeviceConfigById(Long deviceConfigId);
    /**
     * @param openid
     * @return 查询openid是否存在
     */
    public YxDeviceConfig selectDeviceConfigByOpenId(String openid);
}
