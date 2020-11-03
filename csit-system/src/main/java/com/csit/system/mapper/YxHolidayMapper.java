package com.csit.system.mapper;

import com.csit.system.domain.YxHoliday;
import java.util.List;

/**
 * 节假日Mapper接口
 * 
 * @author csit
 * @date 2020-05-01
 */
public interface YxHolidayMapper 
{
    /**
     * 查询节假日
     * 
     * @param id 节假日ID
     * @return 节假日
     */
    public YxHoliday selectYxHolidayById(Long id);

    /**
     * 查询节假日列表
     * 
     * @param yxHoliday 节假日
     * @return 节假日集合
     */
    public List<YxHoliday> selectYxHolidayList(YxHoliday yxHoliday);

    /**
     * 新增节假日
     * 
     * @param yxHoliday 节假日
     * @return 结果
     */
    public int insertYxHoliday(YxHoliday yxHoliday);

    public int insertHBatch(List<YxHoliday> yxHoliday);



    /**
     * 修改节假日
     * 
     * @param yxHoliday 节假日
     * @return 结果
     */
    public int updateYxHoliday(YxHoliday yxHoliday);

    /**
     * 删除节假日
     * 
     * @param id 节假日ID
     * @return 结果
     */
    public int deleteYxHolidayById(Long id);

    /**
     * 批量删除节假日
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteYxHolidayByIds(String[] ids);
}
