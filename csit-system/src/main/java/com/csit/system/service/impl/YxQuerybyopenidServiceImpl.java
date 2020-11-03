package com.csit.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.csit.system.mapper.YxQuerybyopenidMapper;
import com.csit.system.domain.YxQuerybyopenid;
import com.csit.system.service.IYxQuerybyopenidService;
import com.csit.common.core.text.Convert;

/**
 * zaixianService业务层处理
 * 
 * @author csit
 * @date 2020-03-28
 */
@Service
public class YxQuerybyopenidServiceImpl implements IYxQuerybyopenidService 
{
    @Autowired
    private YxQuerybyopenidMapper yxQuerybyopenidMapper;

    /**
     * 查询zaixian
     * 
     * @param id zaixianID
     * @return zaixian
     */
    @Override
    public YxQuerybyopenid selectYxQuerybyopenidById(String  openid)
    {
        return yxQuerybyopenidMapper.selectYxQuerybyopenidById(openid);
    }

    /**
     * 查询zaixian列表
     * 
     * @param yxQuerybyopenid zaixian
     * @return zaixian
     */
    @Override
    public List<YxQuerybyopenid> selectYxQuerybyopenidList(YxQuerybyopenid yxQuerybyopenid)
    {
        return yxQuerybyopenidMapper.selectYxQuerybyopenidList(yxQuerybyopenid);
    }

    /**
     * 新增zaixian
     * 
     * @param yxQuerybyopenid zaixian
     * @return 结果
     */
    @Override
    public int insertYxQuerybyopenid(YxQuerybyopenid yxQuerybyopenid)
    {
        return yxQuerybyopenidMapper.insertYxQuerybyopenid(yxQuerybyopenid);
    }

    /**
     * 修改zaixian
     * 
     * @param yxQuerybyopenid zaixian
     * @return 结果
     */
    @Override
    public int updateYxQuerybyopenid(YxQuerybyopenid yxQuerybyopenid)
    {
        return yxQuerybyopenidMapper.updateYxQuerybyopenid(yxQuerybyopenid);
    }

    /**
     * 删除zaixian对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteYxQuerybyopenidByIds(String ids)
    {
        return yxQuerybyopenidMapper.deleteYxQuerybyopenidByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除zaixian信息
     * 
     * @param id zaixianID
     * @return 结果
     */
    @Override
    public int deleteYxQuerybyopenidById(String  id)
    {
        return yxQuerybyopenidMapper.deleteYxQuerybyopenidById(id);
    }
}
