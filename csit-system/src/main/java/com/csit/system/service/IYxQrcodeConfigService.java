package com.csit.system.service;

import com.csit.system.domain.YxQrcodeConfig;
import java.util.List;

/**
 * 绑定升降桌Service接口
 * 
 * @author csit
 * @date 2020-04-30
 */
public interface IYxQrcodeConfigService 
{
    /**
     * 查询绑定升降桌
     * 
     * @param qrcodeConfigId 绑定升降桌ID
     * @return 绑定升降桌
     */
    public YxQrcodeConfig selectYxQrcodeConfigById(Long qrcodeConfigId);

    /**
     * 查询绑定升降桌列表
     * 
     * @param yxQrcodeConfig 绑定升降桌
     * @return 绑定升降桌集合
     */
    public List<YxQrcodeConfig> selectYxQrcodeConfigList(YxQrcodeConfig yxQrcodeConfig);

    /**
     * 新增绑定升降桌
     * 
     * @param yxQrcodeConfig 绑定升降桌
     * @return 结果
     */
    public int insertYxQrcodeConfig(YxQrcodeConfig yxQrcodeConfig);

    /**
     * 修改绑定升降桌
     * 
     * @param yxQrcodeConfig 绑定升降桌
     * @return 结果
     */
    public int updateYxQrcodeConfig(YxQrcodeConfig yxQrcodeConfig);

    /**
     * 批量删除绑定升降桌
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteYxQrcodeConfigByIds(String ids);

    /**
     * 删除绑定升降桌信息
     * 
     * @param qrcodeConfigId 绑定升降桌ID
     * @return 结果
     */
    public int deleteYxQrcodeConfigById(Long qrcodeConfigId);
}
