package com.csit.system.mapper;

import com.csit.system.domain.YxWechartDeviceYear;
import java.util.List;
import java.util.Map;

/**
 * 微信绑定年Mapper接口
 * 
 * @author csit
 * @date 2020-04-23
 */
public interface YxWechartDeviceYearMapper 
{
    /**
     * 查询微信绑定年
     * 
     * @param id 微信绑定年ID
     * @return 微信绑定年
     */
    public YxWechartDeviceYear selectYxWechartDeviceYearById(Long id);


    /**
     * 查询微信绑定年列表
     * 
     * @param yxWechartDeviceYear 微信绑定年
     * @return 微信绑定年集合
     */
    public List<YxWechartDeviceYear> selectYxWechartDeviceYearList(Map map );


    public List<YxWechartDeviceYear>   selectYxWechartDeviceYearTotal(String openid);

    /**
     * 新增微信绑定年
     * 
     * @param yxWechartDeviceYear 微信绑定年
     * @return 结果
     */
    public int insertYxWechartDeviceYear(YxWechartDeviceYear yxWechartDeviceYear);

    /**
     * 修改微信绑定年
     * 
     * @param yxWechartDeviceYear 微信绑定年
     * @return 结果
     */
    public int updateYxWechartDeviceYear(YxWechartDeviceYear yxWechartDeviceYear);

    /**
     * 删除微信绑定年
     * 
     * @param id 微信绑定年ID
     * @return 结果
     */
    public int deleteYxWechartDeviceYearById(Long id);

    /**
     * 批量删除微信绑定年
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteYxWechartDeviceYearByIds(String[] ids);
}
