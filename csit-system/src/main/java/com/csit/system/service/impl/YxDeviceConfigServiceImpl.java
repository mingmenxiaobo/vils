package com.csit.system.service.impl;

import java.util.List;
import com.csit.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.csit.system.mapper.YxDeviceConfigMapper;
import com.csit.system.domain.YxDeviceConfig;
import com.csit.system.service.IYxDeviceConfigService;
import com.csit.common.core.text.Convert;

/**
 * 绑定设备Service业务层处理
 * 
 * @author csit
 * @date 2020-02-06
 */
@Service
public class YxDeviceConfigServiceImpl implements IYxDeviceConfigService 
{
    @Autowired
    private YxDeviceConfigMapper yxDeviceConfigMapper;

    /**
     * 查询绑定设备
     * 
     * @param deviceConfigId 绑定设备ID
     * @return 绑定设备
     */
    @Override
    public YxDeviceConfig selectYxDeviceConfigById(Long deviceConfigId)
    {
        return yxDeviceConfigMapper.selectYxDeviceConfigById(deviceConfigId);
    }

    /**
     * 查询绑定设备列表
     * 
     * @param yxDeviceConfig 绑定设备
     * @return 绑定设备
     */
    @Override
    public List<YxDeviceConfig> selectYxDeviceConfigList(YxDeviceConfig yxDeviceConfig)
    {
        return yxDeviceConfigMapper.selectYxDeviceConfigList(yxDeviceConfig);
    }

    /**
     * 新增绑定设备
     * 
     * @param yxDeviceConfig 绑定设备
     * @return 结果
     */
    @Override
    public int insertYxDeviceConfig(YxDeviceConfig yxDeviceConfig)
    {
        yxDeviceConfig.setCreateTime(DateUtils.getNowDate());
        return yxDeviceConfigMapper.insertYxDeviceConfig(yxDeviceConfig);
    }

    /**
     * 修改绑定设备
     * 
     * @param yxDeviceConfig 绑定设备
     * @return 结果
     */
    @Override
    public int updateYxDeviceConfig(YxDeviceConfig yxDeviceConfig)
    {
        yxDeviceConfig.setUpdateTime(DateUtils.getNowDate());
        return yxDeviceConfigMapper.updateYxDeviceConfig(yxDeviceConfig);
    }

    /**
     * 删除绑定设备对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteYxDeviceConfigByIds(String ids)
    {
        return yxDeviceConfigMapper.deleteYxDeviceConfigByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除绑定设备信息
     * 
     * @param deviceConfigId 绑定设备ID
     * @return 结果
     */
    @Override
    public int deleteYxDeviceConfigById(Long deviceConfigId)
    {
        return yxDeviceConfigMapper.deleteYxDeviceConfigById(deviceConfigId);
    }
    /**
     * @param openid
     * @return 查询openid是否存在
     */
    public YxDeviceConfig selectDeviceConfigByOpenId(String openid){
        return yxDeviceConfigMapper.selectDeviceConfigByOpenId(openid);
    }
}
