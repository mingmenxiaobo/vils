package com.csit.system.service;

import com.csit.system.domain.SysUser;
import com.csit.system.domain.YxAssetinfoList;
import java.util.List;

/**
 * 产品信息Service接口
 * 
 * @author csit
 * @date 2019-12-14
 */
public interface IYxAssetinfoListService 
{
    /**
     * 查询产品信息
     * 
     * @param id 产品信息ID
     * @return 产品信息
     */
    public YxAssetinfoList selectYxAssetinfoListById(Long id);

    public YxAssetinfoList selectYxAssetinfoListByQrcode(String qrcode);

    /**
     * 查询产品信息列表
     * 
     * @param yxAssetinfoList 产品信息
     * @return 产品信息集合
     */
    public List<YxAssetinfoList> selectYxAssetinfoListList(YxAssetinfoList yxAssetinfoList);

    /**
     * 新增产品信息
     * 
     * @param yxAssetinfoList 产品信息
     * @return 结果
     */
    public int insertYxAssetinfoList(YxAssetinfoList yxAssetinfoList);

    /**
     * 修改产品信息
     * 
     * @param yxAssetinfoList 产品信息
     * @return 结果
     */
    public int updateYxAssetinfoList(YxAssetinfoList yxAssetinfoList);

    /**
     * 批量删除产品信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteYxAssetinfoListByIds(String ids);

    /**
     * 删除产品信息信息
     * 
     * @param id 产品信息ID
     * @return 结果
     */
    public int deleteYxAssetinfoListById(Long id);

    /**
     * 导入数据
     *
     * @param AssetList 数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    public String importAsset(List<YxAssetinfoList> AssetList, Boolean isUpdateSupport, String operName);

}
