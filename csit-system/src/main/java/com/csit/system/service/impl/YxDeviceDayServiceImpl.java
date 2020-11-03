package com.csit.system.service.impl;

import java.util.List;
import java.util.Map;

import com.csit.system.domain.YxDeviceMonth;
import com.csit.system.domain.YxDeviceYear;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.csit.system.mapper.YxDeviceDayMapper;
import com.csit.system.domain.YxDeviceDay;
import com.csit.system.service.IYxDeviceDayService;
import com.csit.common.core.text.Convert;

/**
 * 设备信息采集Service业务层处理
 * 
 * @author csit
 * @date 2019-11-26
 */
@Service
public class YxDeviceDayServiceImpl implements IYxDeviceDayService 
{
    @Autowired
    private YxDeviceDayMapper yxDeviceDayMapper;

    /**
     * 查询设备信息采集
     * 
     * @param id 设备信息采集ID
     * @return 设备信息采集
     */
    @Override
    public YxDeviceDay selectYxDeviceDayById(String id)
    {
        return yxDeviceDayMapper.selectYxDeviceDayById(id);
    }

    /**
     * 查询设备信息采集列表
     * 
     * @param yxDeviceDay 设备信息采集
     * @return 设备信息采集
     */
    @Override
    public List<YxDeviceDay> selectYxDeviceDayList(YxDeviceDay yxDeviceDay)
    {
        return yxDeviceDayMapper.selectYxDeviceDayList(yxDeviceDay);
    }

    /**
     * 新增设备信息采集
     * 
     * @param yxDeviceDay 设备信息采集
     * @return 结果
     */
    @Override
    public int insertYxDeviceDay(YxDeviceDay yxDeviceDay)
    {

        return yxDeviceDayMapper.insertYxDeviceDay(yxDeviceDay);
    }


    public  int batchInset(List<YxDeviceDay> list)
    {
        return yxDeviceDayMapper.insertDeviceDayBatch(list);
    }
    /**
     * 修改设备信息采集
     * 
     * @param yxDeviceDay 设备信息采集
     * @return 结果
     */
    @Override
    public int updateYxDeviceDay(YxDeviceDay yxDeviceDay)
    {
        return yxDeviceDayMapper.updateYxDeviceDay(yxDeviceDay);
    }

    /**
     * 删除设备信息采集对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteYxDeviceDayByIds(String ids)
    {
        return yxDeviceDayMapper.deleteYxDeviceDayByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除设备信息采集信息
     * 
     * @param id 设备信息采集ID
     * @return 结果
     */
    @Override
    public int deleteYxDeviceDayById(String id)
    {
        return yxDeviceDayMapper.deleteYxDeviceDayById(id);
    }

    /**
     * 根据设备id获取最新的一条记录
     *
     * @param map 设备ID
     * @return 结果
     */
    public YxDeviceDay selectNewestDataByDeviceId(Map map) {
        return yxDeviceDayMapper.selectNewestDataByDeviceId(map);
    }
    /**
     * 根据openid获取当天站立次数
     *
     * @param openid
     * @return 记录数
     */
    public int selectDayStandUpTimesByOpenIdAndDeviceId(String openid){
        return yxDeviceDayMapper.selectDayStandUpTimesByOpenIdAndDeviceId(openid);
    }
    /**
     * 根据openid获取当天坐下次数
     *
     * @param openid
     * @return 记录数
     */
    public int selectDaySitDownTimesByOpenIdAndDeviceId(String openid){
        return yxDeviceDayMapper.selectDaySitDownTimesByOpenIdAndDeviceId(openid);
    }
    /**
     * 获取日数据
     * @param map
     * @return 结果
     */
    public List<YxDeviceDay> selectDayDataByDeviceId(Map map){
        return yxDeviceDayMapper.selectDayDataByDeviceId(map);
    }
    /**
     * 获取月数据
     * @param map
     * @return 结果
     */
    public List<YxDeviceDay> selectMonthDataByDeviceId(Map map){
        return yxDeviceDayMapper.selectMonthDataByDeviceId(map);
    }
    /**
     * 获取年数据
     * @param map
     * @return 结果
     */
    public List<YxDeviceDay> selectYearDataByDeviceId(Map map){
        return yxDeviceDayMapper.selectYearDataByDeviceId(map);
    }
    /**
     * 获取累计站(坐)时长
     * @param map
     * @return 结果
     */
    public  List<YxDeviceDay>  selectUsedTimeByDeviceId(Map map){
        return yxDeviceDayMapper.selectUsedTimeByDeviceId(map);
    }
    /**
     * 查询时间段内久坐次数
     * @param map
     * @return 结果
     */
    public int selectSitNumByDeviceId(Map map){
        return yxDeviceDayMapper.selectSitNumByDeviceId(map);
    }
    /**
     * 查询时间段内最大久坐时长
     * @param map
     * @return 结果
     */
    public int selectMaxSitTimeByDeviceId(Map map){
        return yxDeviceDayMapper.selectMaxSitTimeByDeviceId(map);
    }
    /**
     * 查询时间段内最大连续站立时长
     * @param map
     * @return 结果
     */
    public int selectMaxStandTimeByDeviceId(Map map){
        return yxDeviceDayMapper.selectMaxStandTimeByDeviceId(map);
    }
    /**
     * 查询时间段内站的次数
     * @param map
     * @return 结果
     */
    public int selectStandNumByDeviceId(Map map){
        return yxDeviceDayMapper.selectStandNumByDeviceId(map);
    }
    /**
     * 查询时间段内站平均每次站立时长
     * @param map
     * @return 结果
     */
    public int selectAvgStandTimeByDeviceId(Map map){
        return yxDeviceDayMapper.selectAvgStandTimeByDeviceId(map);
    }
}
