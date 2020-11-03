package com.csit.system.service.impl;

import java.util.List;

import com.csit.system.domain.YxDeviceDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.csit.system.mapper.YxVilsqrcodeMapper;
import com.csit.system.domain.YxVilsqrcode;
import com.csit.system.service.IYxVilsqrcodeService;
import com.csit.common.core.text.Convert;

/**
 * 编码生成Service业务层处理
 * 
 * @author csit
 * @date 2020-06-27
 */
@Service
public class YxVilsqrcodeServiceImpl implements IYxVilsqrcodeService 
{
    @Autowired
    private YxVilsqrcodeMapper yxVilsqrcodeMapper;

    /**
     * 查询编码生成
     * 
     * @param codeindex 编码生成ID
     * @return 编码生成
     */
    @Override
    public YxVilsqrcode selectYxVilsqrcodeById(Long codeindex)
    {
        return yxVilsqrcodeMapper.selectYxVilsqrcodeById(codeindex);
    }

    /**
     * 查询编码生成列表
     * 
     * @param yxVilsqrcode 编码生成
     * @return 编码生成
     */
    @Override
    public List<YxVilsqrcode> selectYxVilsqrcodeList(YxVilsqrcode yxVilsqrcode)
    {
        return yxVilsqrcodeMapper.selectYxVilsqrcodeList(yxVilsqrcode);
    }

    /**
     * 新增编码生成
     * 
     * @param yxVilsqrcode 编码生成
     * @return 结果
     */
    @Override
    public int insertYxVilsqrcode(YxVilsqrcode yxVilsqrcode)
    {
        return yxVilsqrcodeMapper.insertYxVilsqrcode(yxVilsqrcode);
    }

    public  int insertYxVilsqrcodeBatch(List<YxVilsqrcode> list)
    {
        return yxVilsqrcodeMapper.insertYxVilsqrcodeBatch(list);
    }

    /**
     * 修改编码生成
     * 
     * @param yxVilsqrcode 编码生成
     * @return 结果
     */
    @Override
    public int updateYxVilsqrcode(YxVilsqrcode yxVilsqrcode)
    {
        return yxVilsqrcodeMapper.updateYxVilsqrcode(yxVilsqrcode);
    }

    /**
     * 删除编码生成对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteYxVilsqrcodeByIds(String ids)
    {
        return yxVilsqrcodeMapper.deleteYxVilsqrcodeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除编码生成信息
     * 
     * @param codeindex 编码生成ID
     * @return 结果
     */
    @Override
    public int deleteYxVilsqrcodeById(Long codeindex)
    {
        return yxVilsqrcodeMapper.deleteYxVilsqrcodeById(codeindex);
    }
}
