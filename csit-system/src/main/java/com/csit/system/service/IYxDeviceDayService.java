package com.csit.system.service;

import com.csit.system.domain.YxDeviceDay;
import com.csit.system.domain.YxDeviceMonth;
import com.csit.system.domain.YxDeviceYear;

import java.util.List;
import java.util.Map;

/**
 * 设备信息采集Service接口
 * 
 * @author csit
 * @date 2019-11-26
 */
public interface IYxDeviceDayService 
{
    /**
     * 查询设备信息采集
     * 
     * @param id 设备信息采集ID
     * @return 设备信息采集
     */

    public YxDeviceDay selectYxDeviceDayById(String id);

    public  int batchInset(List<YxDeviceDay> list);

    /**
     * 查询设备信息采集列表
     * 
     * @param yxDeviceDay 设备信息采集
     * @return 设备信息采集集合
     */
    public List<YxDeviceDay> selectYxDeviceDayList(YxDeviceDay yxDeviceDay);

    /**
     * 新增设备信息采集
     * 
     * @param yxDeviceDay 设备信息采集
     * @return 结果
     */
    public int insertYxDeviceDay(YxDeviceDay yxDeviceDay);

    /**
     * 修改设备信息采集
     * 
     * @param yxDeviceDay 设备信息采集
     * @return 结果
     */
    public int updateYxDeviceDay(YxDeviceDay yxDeviceDay);

    /**
     * 批量删除设备信息采集
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteYxDeviceDayByIds(String ids);

    /**
     * 删除设备信息采集信息
     * 
     * @param id 设备信息采集ID
     * @return 结果
     */
    public int deleteYxDeviceDayById(String id);
    /**
     * 根据设备id获取最新的一条记录
     *
     * @param map 设备ID
     * @return 结果
     */
    public YxDeviceDay selectNewestDataByDeviceId(Map map);
    /**
     * 根据openid获取当天站立次数
     *
     * @param openid
     * @return 记录数
     */
    public int selectDayStandUpTimesByOpenIdAndDeviceId(String openid);
    /**
     * 根据openid获取当天坐下次数
     *
     * @param openid
     * @return 记录数
     */
    public int selectDaySitDownTimesByOpenIdAndDeviceId(String openid);
    /**
     * 获取日数据
     * @param map
     * @return 结果
     */
    public List<YxDeviceDay> selectDayDataByDeviceId(Map map);
    /**
     * 获取月数据
     * @param map
     * @return 结果
     */
    public List<YxDeviceDay> selectMonthDataByDeviceId(Map map);
    /**
     * 获取年数据
     * @param map
     * @return 结果
     */
    public List<YxDeviceDay> selectYearDataByDeviceId(Map map);
    /**
     * 获取累计站(坐)时长
     * @param map
     * @return 结果
     */
    public List<YxDeviceDay>  selectUsedTimeByDeviceId(Map map);
    /**
     * 查询时间段内久坐次数
     * @param map
     * @return 结果
     */
    public int selectSitNumByDeviceId(Map map);
    /**
     * 查询时间段内最大久坐时长
     * @param map
     * @return 结果
     */
    public int selectMaxSitTimeByDeviceId(Map map);
    /**
     * 查询时间段内最大连续站立时长
     * @param map
     * @return 结果
     */
    public int selectMaxStandTimeByDeviceId(Map map);
    /**
     * 查询时间段内站的次数
     * @param map
     * @return 结果
     */
    public int selectStandNumByDeviceId(Map map);
    /**
     * 查询时间段内站平均每次站立时长
     * @param map
     * @return 结果
     */
    public int selectAvgStandTimeByDeviceId(Map map);
}
