package com.csit.system.service;

import com.csit.system.domain.YxAssetfaultlog;
import java.util.List;

/**
 * 维修记录Service接口
 * 
 * @author csit
 * @date 2020-02-07
 */
public interface IYxAssetfaultlogService 
{
    /**
     * 查询维修记录
     * 
     * @param id 维修记录ID
     * @return 维修记录
     */
    public YxAssetfaultlog selectYxAssetfaultlogById(Long id);

    /**
     * 查询维修记录列表
     * 
     * @param yxAssetfaultlog 维修记录
     * @return 维修记录集合
     */
    public List<YxAssetfaultlog> selectYxAssetfaultlogList(YxAssetfaultlog yxAssetfaultlog);

    /**
     * 新增维修记录
     * 
     * @param yxAssetfaultlog 维修记录
     * @return 结果
     */
    public int insertYxAssetfaultlog(YxAssetfaultlog yxAssetfaultlog);

    /**
     * 修改维修记录
     * 
     * @param yxAssetfaultlog 维修记录
     * @return 结果
     */
    public int updateYxAssetfaultlog(YxAssetfaultlog yxAssetfaultlog);

    /**
     * 批量删除维修记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteYxAssetfaultlogByIds(String ids);

    /**
     * 删除维修记录信息
     * 
     * @param id 维修记录ID
     * @return 结果
     */
    public int deleteYxAssetfaultlogById(Long id);
}
