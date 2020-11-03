package com.csit.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.csit.system.mapper.YxWechartInfoMapper;
import com.csit.system.domain.YxWechartInfo;
import com.csit.system.service.IYxWechartInfoService;
import com.csit.common.core.text.Convert;

/**
 * 微信信息Service业务层处理
 * 
 * @author csit
 * @date 2020-04-23
 */
@Service
public class YxWechartInfoServiceImpl implements IYxWechartInfoService 
{
    @Autowired
    private YxWechartInfoMapper yxWechartInfoMapper;

    /**
     * 查询微信信息
     * 
     * @param id 微信信息ID
     * @return 微信信息
     */
    @Override
    public YxWechartInfo selectYxWechartInfoById(String openid)
    {

        return yxWechartInfoMapper.selectYxWechartInfoById(openid);
    }

    /**
     * 查询微信信息列表
     * 
     * @param yxWechartInfo 微信信息
     * @return 微信信息
     */
    @Override
    public List<YxWechartInfo> selectYxWechartInfoList(YxWechartInfo yxWechartInfo)
    {
        return yxWechartInfoMapper.selectYxWechartInfoList(yxWechartInfo);
    }

    /**
     * 新增微信信息
     * 
     * @param yxWechartInfo 微信信息
     * @return 结果
     */
    @Override
    public int insertYxWechartInfo(YxWechartInfo yxWechartInfo)
    {
        return yxWechartInfoMapper.insertYxWechartInfo(yxWechartInfo);
    }

    /**
     * 修改微信信息
     * 
     * @param yxWechartInfo 微信信息
     * @return 结果
     */
    @Override
    public int updateYxWechartInfo(YxWechartInfo yxWechartInfo)
    {
        return yxWechartInfoMapper.updateYxWechartInfo(yxWechartInfo);
    }

    /**
     * 删除微信信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteYxWechartInfoByIds(String ids)
    {
        return yxWechartInfoMapper.deleteYxWechartInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除微信信息信息
     * 
     * @param id 微信信息ID
     * @return 结果
     */
    @Override
    public int deleteYxWechartInfoById(Long id)
    {
        return yxWechartInfoMapper.deleteYxWechartInfoById(id);
    }
}
