package com.csit.system.service.impl;

import com.csit.system.domain.SysTemplate_layout;
import com.csit.system.mapper.SysNoticeMapper;
import com.csit.system.mapper.SysTemplateMapper;
import com.csit.system.service.ISysTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysTemplateServiceImpl implements ISysTemplateService {
    @Autowired
    private SysTemplateMapper templateMapper;

    @Override
    public List<SysTemplate_layout> selectTemplateList(SysTemplate_layout SysTemplate_layout) {
        return templateMapper.selectTemplateList(SysTemplate_layout);
    }

    @Override
    public int SaveTemplateLayout(Map map) {
        return templateMapper.SaveTemplateLayout(map);
    }

    @Override
    public List<Map> SelectTemplateName(String template_name) {
        return templateMapper.SelectTemplateName(template_name);
    }

    @Override
    public SysTemplate_layout getTemplateDetailById(String template_id) {
        return templateMapper.getTemplateDetailById(template_id);
    }

    @Override
    public int Deletetemplate_control(Map map) {
        return templateMapper.Deletetemplate_control(map);
    }

    @Override
    public int updateTemplateLayoutStatus(Map map) {
        return templateMapper.updateTemplateLayoutStatus(map);
    }


    @Override
    public int Inserttemplate_detail_Pach(List list)
    {
        // 第一步：先不管有没有重复关联的数据都添加到中间表
        int resultInt = 0;
        int batchCount = 250;// 每批commit的个数，7个字段总250*7个参数未超过2100
        int batchLastIndex = batchCount;// 每批最后一个的下标
        for (int index = 0; index < list.size();)
        {
            if (batchLastIndex >= list.size())
            {
                batchLastIndex = list.size();
                resultInt = resultInt
                        * ((int) (templateMapper.Inserttemplate_detail_Pach(list
                        .subList(index, batchLastIndex))));
                System.out.println("index:" + index + " batchLastIndex:"
                        + batchLastIndex);
                break;// 数据插入完毕，退出循环
            }
            else
            {
                resultInt = resultInt
                        * ((int) (templateMapper.Inserttemplate_detail_Pach(list
                        .subList(index, batchLastIndex))));
                System.out.println("index:" + index + " batchLastIndex:"
                        + batchLastIndex);
                index = batchLastIndex;// 设置下一批下标
                batchLastIndex = index + (batchCount - 1);
            }
        }
        return resultInt;
    }

    @Override
    public int SelectTemplateByName(String name) {
        return templateMapper.SelectTemplateByName(name);
    }

    @Override
    public int insertDataFromOldTemplateLayout(Map map) {
        return templateMapper.insertDataFromOldTemplateLayout(map);
    }

    @Override
    public int getMaxIdFromTemplateLayout() {
        return templateMapper.getMaxIdFromTemplateLayout();
    }

    @Override
    public int updateTemplateDetailById(Map map) {
        return templateMapper.updateTemplateDetailById(map);
    }

    @Override
    public List<Map> selectAllTemplateList() {
        return templateMapper.selectAllTemplateList();
    }

    @Override
    public List<Map> selectAllSystem() {
        return templateMapper.selectAllSystem();
    }

    @Override
    public List<Map> getAllAssetTypeInfoList() {
        return templateMapper.getAllAssetTypeInfoList();
    }

    @Override
    public int deleteControlById(String template_id) {
        return templateMapper.deleteControlById(template_id);
    }


    @Override
    public int InsertTempContent_detail(List list)
    {
        // 第一步：先不管有没有重复关联的数据都添加到中间表
        int resultInt = 0;
        int batchCount = 250;// 每批commit的个数，7个字段总250*7个参数未超过2100
        int batchLastIndex = batchCount;// 每批最后一个的下标
        for (int index = 0; index < list.size();)
        {
            if (batchLastIndex >= list.size())
            {
                batchLastIndex = list.size();
                resultInt = resultInt
                        * ((int) (templateMapper.Inserttemplate_detail_Pach(list
                        .subList(index, batchLastIndex))));
                System.out.println("index:" + index + " batchLastIndex:"
                        + batchLastIndex);
                break;// 数据插入完毕，退出循环
            }
            else
            {
                resultInt = resultInt
                        * ((int) (templateMapper.Inserttemplate_detail_Pach(list
                        .subList(index, batchLastIndex))));
                System.out.println("index:" + index + " batchLastIndex:"
                        + batchLastIndex);
                index = batchLastIndex;// 设置下一批下标
                batchLastIndex = index + (batchCount - 1);
            }
        }
        return resultInt;
    }

    @Override
    public int AssociateControl(Map map) {
        return templateMapper.AssociateControl(map);
    }

    @Override
    public List<Map> SelectQrcodeCheckList(Map map) {
        return templateMapper.SelectQrcodeCheckList(map);
    }

    @Override
    public Map checkControlSetUp(Map map) {
        return templateMapper.checkControlSetUp(map);
    }

    @Override
    public int updateTempContentByControlName(Map map) {
        return templateMapper.updateTempContentByControlName(map);
    }

    @Override
    public SysTemplate_layout getTemplateDetailByMouldCode(String template_id) {
        return templateMapper.getTemplateDetailByMouldCode(template_id);
    }

    @Override
    public int getDeviceSeatStatusBy() {
        return templateMapper.getDeviceSeatStatusBy();
    }

    @Override
    public int setUpQrCodeByTemplateId(Map map) {
        return templateMapper.setUpQrCodeByTemplateId(map);
    }


}
