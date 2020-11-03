package com.csit.system.service.impl;

import java.util.List;
import com.csit.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.csit.system.mapper.YxQrcodeConfigMapper;
import com.csit.system.domain.YxQrcodeConfig;
import com.csit.system.service.IYxQrcodeConfigService;
import com.csit.common.core.text.Convert;

/**
 * 绑定升降桌Service业务层处理
 * 
 * @author csit
 * @date 2020-04-30
 */
@Service
public class YxQrcodeConfigServiceImpl implements IYxQrcodeConfigService 
{
    @Autowired
    private YxQrcodeConfigMapper yxQrcodeConfigMapper;

    /**
     * 查询绑定升降桌
     * 
     * @param qrcodeConfigId 绑定升降桌ID
     * @return 绑定升降桌
     */
    @Override
    public YxQrcodeConfig selectYxQrcodeConfigById(Long qrcodeConfigId)
    {
        return yxQrcodeConfigMapper.selectYxQrcodeConfigById(qrcodeConfigId);
    }

    /**
     * 查询绑定升降桌列表
     * 
     * @param yxQrcodeConfig 绑定升降桌
     * @return 绑定升降桌
     */
    @Override
    public List<YxQrcodeConfig> selectYxQrcodeConfigList(YxQrcodeConfig yxQrcodeConfig)
    {
        return yxQrcodeConfigMapper.selectYxQrcodeConfigList(yxQrcodeConfig);
    }

    /**
     * 新增绑定升降桌
     * 
     * @param yxQrcodeConfig 绑定升降桌
     * @return 结果
     */
    @Override
    public int insertYxQrcodeConfig(YxQrcodeConfig yxQrcodeConfig)
    {
        yxQrcodeConfig.setCreateTime(DateUtils.getNowDate());
        return yxQrcodeConfigMapper.insertYxQrcodeConfig(yxQrcodeConfig);
    }

    /**
     * 修改绑定升降桌
     * 
     * @param yxQrcodeConfig 绑定升降桌
     * @return 结果
     */
    @Override
    public int updateYxQrcodeConfig(YxQrcodeConfig yxQrcodeConfig)
    {
        yxQrcodeConfig.setUpdateTime(DateUtils.getNowDate());
        return yxQrcodeConfigMapper.updateYxQrcodeConfig(yxQrcodeConfig);
    }

    /**
     * 删除绑定升降桌对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteYxQrcodeConfigByIds(String ids)
    {
        return yxQrcodeConfigMapper.deleteYxQrcodeConfigByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除绑定升降桌信息
     * 
     * @param qrcodeConfigId 绑定升降桌ID
     * @return 结果
     */
    @Override
    public int deleteYxQrcodeConfigById(Long qrcodeConfigId)
    {
        return yxQrcodeConfigMapper.deleteYxQrcodeConfigById(qrcodeConfigId);
    }
}
