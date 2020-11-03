package com.csit.system.service.impl;

import java.util.List;
import java.util.ArrayList;
import com.csit.common.core.domain.Ztree;
import com.csit.common.exception.BusinessException;
import com.csit.common.utils.DateUtils;
import com.csit.common.utils.StringUtils;
import com.csit.system.domain.YxAssetinfoList;
import com.csit.system.domain.YxDeviceCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.csit.system.mapper.YxDeviceMapper;
import com.csit.system.domain.YxDevice;
import com.csit.system.service.IYxDeviceService;
import com.csit.common.core.text.Convert;

/**
 * 设备信息Service业务层处理
 * 
 * @author csit
 * @date 2019-12-14
 */
@Service
public class YxDeviceServiceImpl implements IYxDeviceService 
{
    private static final Logger log = LoggerFactory.getLogger(YxDeviceServiceImpl.class);

    @Autowired
    private YxDeviceMapper yxDeviceMapper;

    /**
     * 查询设备信息
     * 
     * @param deviceId 设备信息ID
     * @return 设备信息
     */
    @Override
    public YxDevice selectYxDeviceById(Long deviceId)
    {
        return yxDeviceMapper.selectYxDeviceById(deviceId);
    }


    public List<YxDeviceCode> selectYxDeviceByCode(YxDeviceCode yxDevice)
    {
        return yxDeviceMapper.selectYxDeviceByCode(yxDevice);
    }
    /**
     * 查询设备信息列表
     * 
     * @param yxDevice 设备信息
     * @return 设备信息
     */
    @Override
    public List<YxDevice> selectYxDeviceList(YxDevice yxDevice)
    {
        return yxDeviceMapper.selectYxDeviceList(yxDevice);
    }

    /**
     * 新增设备信息
     * 
     * @param yxDevice 设备信息
     * @return 结果
     */
    @Override
    public int insertYxDevice(YxDevice yxDevice)
    {
        YxDevice dev = yxDeviceMapper.selectYxDeviceById(yxDevice.getParentId());
        // 如果父节点不为"正常"状态,则不允许新增子节点
        if (!"0".equals(dev.getStatus()))
        {
            throw new BusinessException("网关停用，不允许新增设备");
        }
        yxDevice.setAncestors(dev.getAncestors() + "," + yxDevice.getParentId());
        yxDevice.setCreateTime(DateUtils.getNowDate());
        return yxDeviceMapper.insertYxDevice(yxDevice);
    }

    /**
     * 修改设备信息
     * 
     * @param yxDevice 设备信息
     * @return 结果
     */
    @Override
    public int updateYxDevice(YxDevice yxDevice)
    {
        yxDevice.setUpdateTime(DateUtils.getNowDate());
        return yxDeviceMapper.updateYxDevice(yxDevice);
    }

    /**
     * 删除设备信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteYxDeviceByIds(String ids)
    {
        return yxDeviceMapper.deleteYxDeviceByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除设备信息信息
     * 
     * @param deviceId 设备信息ID
     * @return 结果
     */
    @Override
    public int deleteYxDeviceById(Long deviceId)
    {
        return yxDeviceMapper.deleteYxDeviceById(deviceId);
    }

    /**
     * 查询设备信息树列表
     * 
     * @return 所有设备信息信息
     */
    @Override
    public List<Ztree> selectYxDeviceTree()
    {
        List<YxDevice> yxDeviceList = yxDeviceMapper.selectYxDeviceList(new YxDevice());
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (YxDevice yxDevice : yxDeviceList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(yxDevice.getDeviceId());
            ztree.setpId(yxDevice.getParentId());
            ztree.setName(yxDevice.getDeviceName());
            ztree.setTitle(yxDevice.getDeviceName());
            ztrees.add(ztree);
        }
        return ztrees;
    }


    /**
     * 导入数据
     *
     * @param YxDevice 数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    public String importDevice(List<YxDevice> devList, Boolean isUpdateSupport, String operName)
    {
        if (StringUtils.isNull(devList) || devList.size() == 0)
        {
            throw new BusinessException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();

        for (YxDevice dev : devList)
        {
            try
            {
                // 验证是否存在这个用户
                YxDevice yxasset = yxDeviceMapper.selectDeviceByCode(dev);
                if (StringUtils.isNull(yxasset))
                {

                    this.insertYxDevice(dev);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、设备: " + dev.getDeviceCode() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    dev.setUpdateBy(operName);
                    this.updateYxDevice(dev);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、设备: " + dev.getDeviceCode()+ " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、设备: " + dev.getDeviceCode() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、设备:" + dev.getDeviceCode()+ " 导入失败：";
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
