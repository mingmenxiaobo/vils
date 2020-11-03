package com.csit.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.csit.system.mapper.YxWechartDeviceYearMapper;
import com.csit.system.domain.YxWechartDeviceYear;
import com.csit.system.service.IYxWechartDeviceYearService;
import com.csit.common.core.text.Convert;

/**
 * 微信绑定年Service业务层处理
 * 
 * @author csit
 * @date 2020-04-23
 */
@Service
public class YxWechartDeviceYearServiceImpl implements IYxWechartDeviceYearService 
{
    @Autowired
    private YxWechartDeviceYearMapper yxWechartDeviceYearMapper;

    /**
     * 查询微信绑定年
     * 
     * @param id 微信绑定年ID
     * @return 微信绑定年
     */
    @Override
    public YxWechartDeviceYear selectYxWechartDeviceYearById(Long id)
    {
        return yxWechartDeviceYearMapper.selectYxWechartDeviceYearById(id);
    }

    @Override
    public List<YxWechartDeviceYear>  selectYxWechartDeviceYearTotal(String  openid)
    {
        return yxWechartDeviceYearMapper.selectYxWechartDeviceYearTotal(openid);
    }

    /**
     * 查询微信绑定年列表
     * 
     * @param yxWechartDeviceYear 微信绑定年
     * @return 微信绑定年
     */
    @Override
    public List<YxWechartDeviceYear> selectYxWechartDeviceYearList(Map map)
    {
        return yxWechartDeviceYearMapper.selectYxWechartDeviceYearList(map);
    }

    /**
     * 新增微信绑定年
     * 
     * @param yxWechartDeviceYear 微信绑定年
     * @return 结果
     */
    @Override
    public int insertYxWechartDeviceYear(YxWechartDeviceYear yxWechartDeviceYear)
    {
        return yxWechartDeviceYearMapper.insertYxWechartDeviceYear(yxWechartDeviceYear);
    }

    /**
     * 修改微信绑定年
     * 
     * @param yxWechartDeviceYear 微信绑定年
     * @return 结果
     */
    @Override
    public int updateYxWechartDeviceYear(YxWechartDeviceYear yxWechartDeviceYear)
    {
        return yxWechartDeviceYearMapper.updateYxWechartDeviceYear(yxWechartDeviceYear);
    }

    /**
     * 删除微信绑定年对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteYxWechartDeviceYearByIds(String ids)
    {
        return yxWechartDeviceYearMapper.deleteYxWechartDeviceYearByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除微信绑定年信息
     * 
     * @param id 微信绑定年ID
     * @return 结果
     */
    @Override
    public int deleteYxWechartDeviceYearById(Long id)
    {
        return yxWechartDeviceYearMapper.deleteYxWechartDeviceYearById(id);
    }
}
