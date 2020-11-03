package com.csit.system.service;

import com.csit.system.domain.YxWechartDeviceMonth;
import java.util.List;
import java.util.Map;

/**
 * 微信月Service接口
 * 
 * @author csit
 * @date 2020-04-23
 */
public interface IYxWechartDeviceMonthService 
{
    /**
     * 查询微信月
     * 
     * @param id 微信月ID
     * @return 微信月
     */
    public YxWechartDeviceMonth selectYxWechartDeviceMonthById(Long id);



    /**
     * 查询微信月列表
     * 
     * @param yxWechartDeviceMonth 微信月
     * @return 微信月集合
     */
    public List<YxWechartDeviceMonth> selectYxWechartDeviceMonthList(Map map);

    public List<YxWechartDeviceMonth> selectYxWechartDeviceWeekList(Map map);

    public List<YxWechartDeviceMonth> selectYxWechartDeviceWeekTotal(String openid);
    public List<YxWechartDeviceMonth> selectYxWechartDeviceMonthTotal(String openid);

    /**
     * 新增微信月
     * 
     * @param yxWechartDeviceMonth 微信月
     * @return 结果
     */
    public int insertYxWechartDeviceMonth(YxWechartDeviceMonth yxWechartDeviceMonth);

    /**
     * 修改微信月
     * 
     * @param yxWechartDeviceMonth 微信月
     * @return 结果
     */
    public int updateYxWechartDeviceMonth(YxWechartDeviceMonth yxWechartDeviceMonth);

    /**
     * 批量删除微信月
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteYxWechartDeviceMonthByIds(String ids);

    /**
     * 删除微信月信息
     * 
     * @param id 微信月ID
     * @return 结果
     */
    public int deleteYxWechartDeviceMonthById(Long id);
}
