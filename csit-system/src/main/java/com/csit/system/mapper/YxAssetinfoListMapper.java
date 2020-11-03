package com.csit.system.mapper;

import com.csit.common.exception.BusinessException;
import com.csit.common.utils.StringUtils;
import com.csit.common.utils.security.Md5Utils;
import com.csit.system.domain.SysUser;
import com.csit.system.domain.YxAssetinfoList;
import java.util.List;

/**
 * 产品信息Mapper接口
 * 
 * @author csit
 * @date 2019-12-14
 */
public interface YxAssetinfoListMapper 
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
     * 删除产品信息
     * 
     * @param id 产品信息ID
     * @return 结果
     */
    public int deleteYxAssetinfoListById(Long id);

    /**
     * 批量删除产品信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteYxAssetinfoListByIds(String[] ids);



}
