package com.csit.system.mapper;

import com.csit.system.domain.YxDevice;
import com.csit.system.domain.YxDeviceCode;

import java.util.List;

/**
 * 设备信息Mapper接口
 * 
 * @author csit
 * @date 2019-12-14
 */
public interface YxDeviceMapper 
{
    /**
     *
     * 查询设备信息
     * 
     * @param deviceId 设备信息ID
     * @return 设备信息
     */
    public YxDevice selectYxDeviceById(Long deviceId);

    /**
     * 查询设备信息列表
     * 
     * @param yxDevice 设备信息
     * @return 设备信息集合
     */
    public List<YxDevice> selectYxDeviceList(YxDevice yxDevice);




    public List<YxDeviceCode> selectYxDeviceByCode(YxDeviceCode yxDevice);

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
     * 删除设备信息
     * 
     * @param deviceId 设备信息ID
     * @return 结果
     */
    public int deleteYxDeviceById(Long deviceId);

    /**
     * 批量删除设备信息
     * 
     * @param deviceIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteYxDeviceByIds(String[] deviceIds);

    public YxDevice selectDeviceByCode(YxDevice dev);


}
