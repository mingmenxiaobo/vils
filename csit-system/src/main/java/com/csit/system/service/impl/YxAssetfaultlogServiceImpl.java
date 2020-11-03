package com.csit.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.csit.system.mapper.YxAssetfaultlogMapper;
import com.csit.system.domain.YxAssetfaultlog;
import com.csit.system.service.IYxAssetfaultlogService;
import com.csit.common.core.text.Convert;

/**
 * 维修记录Service业务层处理
 * 
 * @author csit
 * @date 2020-02-07
 */
@Service
public class YxAssetfaultlogServiceImpl implements IYxAssetfaultlogService 
{
    @Autowired
    private YxAssetfaultlogMapper yxAssetfaultlogMapper;

    /**
     * 查询维修记录
     * 
     * @param id 维修记录ID
     * @return 维修记录
     */
    @Override
    public YxAssetfaultlog selectYxAssetfaultlogById(Long id)
    {
        return yxAssetfaultlogMapper.selectYxAssetfaultlogById(id);
    }

    /**
     * 查询维修记录列表
     * 
     * @param yxAssetfaultlog 维修记录
     * @return 维修记录
     */
    @Override
    public List<YxAssetfaultlog> selectYxAssetfaultlogList(YxAssetfaultlog yxAssetfaultlog)
    {
        return yxAssetfaultlogMapper.selectYxAssetfaultlogList(yxAssetfaultlog);
    }

    /**
     * 新增维修记录
     * 
     * @param yxAssetfaultlog 维修记录
     * @return 结果
     */
    @Override
    public int insertYxAssetfaultlog(YxAssetfaultlog yxAssetfaultlog)
    {
        return yxAssetfaultlogMapper.insertYxAssetfaultlog(yxAssetfaultlog);
    }

    /**
     * 修改维修记录
     * 
     * @param yxAssetfaultlog 维修记录
     * @return 结果
     */
    @Override
    public int updateYxAssetfaultlog(YxAssetfaultlog yxAssetfaultlog)
    {
        return yxAssetfaultlogMapper.updateYxAssetfaultlog(yxAssetfaultlog);
    }

    /**
     * 删除维修记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteYxAssetfaultlogByIds(String ids)
    {
        return yxAssetfaultlogMapper.deleteYxAssetfaultlogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除维修记录信息
     * 
     * @param id 维修记录ID
     * @return 结果
     */
    @Override
    public int deleteYxAssetfaultlogById(Long id)
    {
        return yxAssetfaultlogMapper.deleteYxAssetfaultlogById(id);
    }
}
