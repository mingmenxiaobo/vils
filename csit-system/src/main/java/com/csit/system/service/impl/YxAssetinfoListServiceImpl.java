package com.csit.system.service.impl;

import java.util.List;

import com.csit.common.exception.BusinessException;
import com.csit.common.utils.StringUtils;
import com.csit.common.utils.security.Md5Utils;
import com.csit.system.domain.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.csit.system.mapper.YxAssetinfoListMapper;
import com.csit.system.domain.YxAssetinfoList;
import com.csit.system.service.IYxAssetinfoListService;
import com.csit.common.core.text.Convert;

/**
 * 产品信息Service业务层处理
 * 
 * @author csit
 * @date 2019-12-14
 */
@Service
public class YxAssetinfoListServiceImpl implements IYxAssetinfoListService 
{
    private static final Logger log = LoggerFactory.getLogger(YxAssetinfoListServiceImpl.class);

    @Autowired
    private YxAssetinfoListMapper yxAssetinfoListMapper;

    /**
     * 查询产品信息
     * 
     * @param id 产品信息ID
     * @return 产品信息
     */
    @Override
    public YxAssetinfoList selectYxAssetinfoListById(Long id)
    {
        return yxAssetinfoListMapper.selectYxAssetinfoListById(id);
    }

    public YxAssetinfoList selectYxAssetinfoListByQrcode(String qrcode)
    {
        return yxAssetinfoListMapper.selectYxAssetinfoListByQrcode(qrcode);
    }

    /**
     * 查询产品信息列表
     * 
     * @param yxAssetinfoList 产品信息
     * @return 产品信息
     */
    @Override
    public List<YxAssetinfoList> selectYxAssetinfoListList(YxAssetinfoList yxAssetinfoList)
    {
        return yxAssetinfoListMapper.selectYxAssetinfoListList(yxAssetinfoList);
    }

    /**
     * 新增产品信息
     * 
     * @param yxAssetinfoList 产品信息
     * @return 结果
     */
    @Override
    public int insertYxAssetinfoList(YxAssetinfoList yxAssetinfoList)
    {
        return yxAssetinfoListMapper.insertYxAssetinfoList(yxAssetinfoList);
    }

    /**
     * 修改产品信息
     * 
     * @param yxAssetinfoList 产品信息
     * @return 结果
     */
    @Override
    public int updateYxAssetinfoList(YxAssetinfoList yxAssetinfoList)
    {
        return yxAssetinfoListMapper.updateYxAssetinfoList(yxAssetinfoList);
    }

    /**
     * 删除产品信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteYxAssetinfoListByIds(String ids)
    {
        return yxAssetinfoListMapper.deleteYxAssetinfoListByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除产品信息信息
     * 
     * @param id 产品信息ID
     * @return 结果
     */
    @Override
    public int deleteYxAssetinfoListById(Long id)
    {
        return yxAssetinfoListMapper.deleteYxAssetinfoListById(id);
    }

    /**
     * 导入数据
     *
     * @param AssetList 数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    public String importAsset(List<YxAssetinfoList> AssetList, Boolean isUpdateSupport, String operName)
    {
        if (StringUtils.isNull(AssetList) || AssetList.size() == 0)
        {
            throw new BusinessException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();

        for (YxAssetinfoList asset : AssetList)
        {
            try
            {
                // 验证是否存在这个用户
                YxAssetinfoList yxasset = yxAssetinfoListMapper.selectYxAssetinfoListByQrcode(asset.getQrcode());
                if (StringUtils.isNull(yxasset))
                {

                    this.insertYxAssetinfoList(asset);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、升降桌CODE: " + asset.getQrcode() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    asset.setUpdateBy(operName);
                    this.updateYxAssetinfoList(asset);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、升降桌CODE: " + asset.getQrcode()+ " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、升降桌CODE: " + asset.getQrcode() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、升降桌CODE:" + asset.getQrcode()+ " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }
}
