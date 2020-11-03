package com.csit.system.mapper;

import com.csit.system.domain.YxDeviceDay;
import com.csit.system.domain.YxVilsqrcode;
import java.util.List;

/**
 * 编码生成Mapper接口
 * 
 * @author csit
 * @date 2020-06-27
 */
public interface YxVilsqrcodeMapper 
{
    /**
     * 查询编码生成
     * 
     * @param codeindex 编码生成ID
     * @return 编码生成
     */
    public YxVilsqrcode selectYxVilsqrcodeById(Long codeindex);

    /**
     * 查询编码生成列表
     * 
     * @param yxVilsqrcode 编码生成
     * @return 编码生成集合
     */
    public List<YxVilsqrcode> selectYxVilsqrcodeList(YxVilsqrcode yxVilsqrcode);

    /**
     * 新增编码生成
     * 
     * @param yxVilsqrcode 编码生成
     * @return 结果
     */
    public int insertYxVilsqrcode(YxVilsqrcode yxVilsqrcode);

    public int  insertYxVilsqrcodeBatch(List<YxVilsqrcode> list);

    /**
     * 修改编码生成
     * 
     * @param yxVilsqrcode 编码生成
     * @return 结果
     */
    public int updateYxVilsqrcode(YxVilsqrcode yxVilsqrcode);

    /**
     * 删除编码生成
     * 
     * @param codeindex 编码生成ID
     * @return 结果
     */
    public int deleteYxVilsqrcodeById(Long codeindex);

    /**
     * 批量删除编码生成
     * 
     * @param codeindexs 需要删除的数据ID
     * @return 结果
     */
    public int deleteYxVilsqrcodeByIds(String[] codeindexs);
}
