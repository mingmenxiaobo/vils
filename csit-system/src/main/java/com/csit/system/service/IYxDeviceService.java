package com.csit.system.service;

import com.csit.common.utils.StringUtils;
import com.csit.system.domain.YxAssetinfoList;
import com.csit.system.domain.YxDevice;
import java.util.List;
import com.csit.common.core.domain.Ztree;
import com.csit.system.domain.YxDeviceCode;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 设备信息Service接口
 * 
 * @author csit
 * @date 2019-12-14
 */
public interface IYxDeviceService 
{
    /**
     * 查询设备信息
     * 
     * @param deviceId 设备信息ID
     * @return 设备信息
     */
    public YxDevice selectYxDeviceById(Long deviceId);

    public List<YxDeviceCode> selectYxDeviceByCode(YxDeviceCode yxDevice);
    /**
     * 查询设备信息列表
     * 
     * @param yxDevice 设备信息
     * @return 设备信息集合
     */
    public List<YxDevice> selectYxDeviceList(YxDevice yxDevice);

    /**
     * 新增设备信息
     * 
     * @param yxDevice 设备信息
     * @return 结果
     */
    public int insertYxDevice(YxDevice yxDevice);

    /**
     * 修改设备信息
     * 
     * @param yxDevice 设备信息
     * @return 结果
     */
    public int updateYxDevice(YxDevice yxDevice);



    /**
     * 批量删除设备信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteYxDeviceByIds(String ids);

    /**
     * 删除设备信息信息
     * 
     * @param deviceId 设备信息ID
     * @return 结果
     */
    public int deleteYxDeviceById(Long deviceId);

    /**
     * 查询设备信息树列表
     * 
     * @return 所有设备信息信息
     */
    public List<Ztree> selectYxDeviceTree();


    /**
     * 导入数据
     *
     * @param YxDevice 数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    public String importDevice(List<YxDevice> devList, Boolean isUpdateSupport, String operName);

}
