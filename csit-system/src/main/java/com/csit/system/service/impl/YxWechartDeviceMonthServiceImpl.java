package com.csit.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.csit.system.mapper.YxWechartDeviceMonthMapper;
import com.csit.system.domain.YxWechartDeviceMonth;
import com.csit.system.service.IYxWechartDeviceMonthService;
import com.csit.common.core.text.Convert;

/**
 * 微信月Service业务层处理
 * 
 * @author csit
 * @date 2020-04-23
 */
@Service
public class YxWechartDeviceMonthServiceImpl implements IYxWechartDeviceMonthService 
{
    @Autowired
    private YxWechartDeviceMonthMapper yxWechartDeviceMonthMapper;

    /**
     * 查询微信月
     * 
     * @param id 微信月ID
     * @return 微信月
     */
    @Override
    public YxWechartDeviceMonth selectYxWechartDeviceMonthById(Long id)
    {
        return yxWechartDeviceMonthMapper.selectYxWechartDeviceMonthById(id);
    }



    /**
     * 查询微信月列表
     * 
     * @param yxWechartDeviceMonth 微信月
     * @return 微信月
     */
    @Override
    public List<YxWechartDeviceMonth> selectYxWechartDeviceMonthList(Map map)
    {
        return yxWechartDeviceMonthMapper.selectYxWechartDeviceMonthList(map);
    }

    public List<YxWechartDeviceMonth> selectYxWechartDeviceWeekList(Map map)
    {
        return yxWechartDeviceMonthMapper.selectYxWechartDeviceWeekList(map);
    }



    @Override
    public List<YxWechartDeviceMonth> selectYxWechartDeviceWeekTotal(String openid)
    {
        return yxWechartDeviceMonthMapper.selectYxWechartDeviceWeekTotal(openid);
    }

    @Override
    public List<YxWechartDeviceMonth> selectYxWechartDeviceMonthTotal(String openid)
    {
        return yxWechartDeviceMonthMapper.selectYxWechartDeviceMonthTotal(openid);
    }

    /**
     * 新增微信月
     * 
     * @param yxWechartDeviceMonth 微信月
     * @return 结果
     */
    @Override
    public int insertYxWechartDeviceMonth(YxWechartDeviceMonth yxWechartDeviceMonth)
    {
        return yxWechartDeviceMonthMapper.insertYxWechartDeviceMonth(yxWechartDeviceMonth);
    }

    /**
     * 修改微信月
     * 
     * @param yxWechartDeviceMonth 微信月
     * @return 结果
     */
    @Override
    public int updateYxWechartDeviceMonth(YxWechartDeviceMonth yxWechartDeviceMonth)
    {
        return yxWechartDeviceMonthMapper.updateYxWechartDeviceMonth(yxWechartDeviceMonth);
    }

    /**
     * 删除微信月对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteYxWechartDeviceMonthByIds(String ids)
    {
        return yxWechartDeviceMonthMapper.deleteYxWechartDeviceMonthByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除微信月信息
     * 
     * @param id 微信月ID
     * @return 结果
     */
    @Override
    public int deleteYxWechartDeviceMonthById(Long id)
    {
        return yxWechartDeviceMonthMapper.deleteYxWechartDeviceMonthById(id);
    }
}
