package com.csit.system.service;

import com.csit.system.domain.YxWechartInfo;
import java.util.List;

/**
 * 微信信息Service接口
 * 
 * @author csit
 * @date 2020-04-23
 */
public interface IYxWechartInfoService 
{
    /**
     * 查询微信信息
     * 
     * @param id 微信信息ID
     * @return 微信信息
     */
    public YxWechartInfo selectYxWechartInfoById(String openid);

    /**
     * 查询微信信息列表
     * 
     * @param yxWechartInfo 微信信息
     * @return 微信信息集合
     */
    public List<YxWechartInfo> selectYxWechartInfoList(YxWechartInfo yxWechartInfo);

    /**
     * 新增微信信息
     * 
     * @param yxWechartInfo 微信信息
     * @return 结果
     */
    public int insertYxWechartInfo(YxWechartInfo yxWechartInfo);

    /**
     * 修改微信信息
     * 
     * @param yxWechartInfo 微信信息
     * @return 结果
     */
    public int updateYxWechartInfo(YxWechartInfo yxWechartInfo);

    /**
     * 批量删除微信信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteYxWechartInfoByIds(String ids);

    /**
     * 删除微信信息信息
     * 
     * @param id 微信信息ID
     * @return 结果
     */
    public int deleteYxWechartInfoById(Long id);
}
