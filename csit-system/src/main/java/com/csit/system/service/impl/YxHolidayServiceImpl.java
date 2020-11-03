package com.csit.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.csit.system.mapper.YxHolidayMapper;
import com.csit.system.domain.YxHoliday;
import com.csit.system.service.IYxHolidayService;
import com.csit.common.core.text.Convert;

/**
 * 节假日Service业务层处理
 * 
 * @author csit
 * @date 2020-05-01
 */
@Service
public class YxHolidayServiceImpl implements IYxHolidayService 
{
    @Autowired
    private YxHolidayMapper yxHolidayMapper;

    /**
     * 查询节假日
     * 
     * @param id 节假日ID
     * @return 节假日
     */
    @Override
    public YxHoliday selectYxHolidayById(Long id)
    {
        return yxHolidayMapper.selectYxHolidayById(id);
    }

    /**
     * 查询节假日列表
     * 
     * @param yxHoliday 节假日
     * @return 节假日
     */
    @Override
    public List<YxHoliday> selectYxHolidayList(YxHoliday yxHoliday)
    {
        return yxHolidayMapper.selectYxHolidayList(yxHoliday);
    }

    /**
     * 新增节假日
     * 
     * @param yxHoliday 节假日
     * @return 结果
     */
    @Override
    public int insertYxHoliday(YxHoliday yxHoliday)
    {
        return yxHolidayMapper.insertYxHoliday(yxHoliday);
    }



    public int insertHBatch(List<YxHoliday> yxHoliday)
    {
        return yxHolidayMapper.insertHBatch(yxHoliday);
    }

    /**
     * 修改节假日
     * 
     * @param yxHoliday 节假日
     * @return 结果
     */
    @Override
    public int updateYxHoliday(YxHoliday yxHoliday)
    {
        return yxHolidayMapper.updateYxHoliday(yxHoliday);
    }

    /**
     * 删除节假日对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteYxHolidayByIds(String ids)
    {
        return yxHolidayMapper.deleteYxHolidayByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除节假日信息
     * 
     * @param id 节假日ID
     * @return 结果
     */
    @Override
    public int deleteYxHolidayById(Long id)
    {
        return yxHolidayMapper.deleteYxHolidayById(id);
    }
}
