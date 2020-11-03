package com.csit.system.mapper;

import com.csit.system.domain.SysTemplate_layout;

import java.util.List;
import java.util.Map;

public interface SysTemplateMapper {

    public List<SysTemplate_layout> selectTemplateList(SysTemplate_layout SysTemplate_layout);

    public int SaveTemplateLayout(Map map);

    public List<Map> SelectTemplateName(String template_name);

    public SysTemplate_layout getTemplateDetailById(String template_id);

    public int Deletetemplate_control(Map map);

    public int updateTemplateLayoutStatus(Map map);

    public int Inserttemplate_detail_Pach(List list);

    public int SelectTemplateByName(String name);

    public int insertDataFromOldTemplateLayout(Map map);

    public int getMaxIdFromTemplateLayout();

    public int updateTemplateDetailById(Map map);

    public List<Map> selectAllTemplateList();

    public List<Map> selectAllSystem();

    public List<Map> getAllAssetTypeInfoList();

    public int deleteControlById(String template_id);

    public int InsertTempContent_detail(List list);

    public int AssociateControl(Map map);

    public List<Map> SelectQrcodeCheckList(Map map);

    public Map checkControlSetUp(Map map);

    public int updateTempContentByControlName(Map map);

    public SysTemplate_layout getTemplateDetailByMouldCode(String template_id);

    public int getDeviceSeatStatusBy();

    public int setUpQrCodeByTemplateId(Map map);
}
