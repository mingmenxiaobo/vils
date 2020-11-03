package com.csit.system.mapper;

import com.csit.system.domain.YxQuerybyopenid;
import java.util.List;

/**
 * zaixianMapper接口
 * 
 * @author csit
 * @date 2020-03-28
 */
public interface YxQuerybyopenidMapper 
{
    /**
     * 查询zaixian
     * 
     * @param id zaixianID
     * @return zaixian
     */
    public YxQuerybyopenid selectYxQuerybyopenidById(String id);

    /**
     * 查询zaixian列表
     * 
     * @param yxQuerybyopenid zaixian
     * @return zaixian集合
     */
    public List<YxQuerybyopenid> selectYxQuerybyopenidList(YxQuerybyopenid yxQuerybyopenid);

    /**
     * 新增zaixian
     * 
     * @param yxQuerybyopenid zaixian
     * @return 结果
     */
    public int insertYxQuerybyopenid(YxQuerybyopenid yxQuerybyopenid);

    /**
     * 修改zaixian
     * 
     * @param yxQuerybyopenid zaixian
     * @return 结果
     */
    public int updateYxQuerybyopenid(YxQuerybyopenid yxQuerybyopenid);

    /**
     * 删除zaixian
     * 
     * @param id zaixianID
     * @return 结果
     */
    public int deleteYxQuerybyopenidById(String id);

    /**
     * 批量删除zaixian
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteYxQuerybyopenidByIds(String[] ids);
}
