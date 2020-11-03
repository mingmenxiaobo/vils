package com.csit.system.service.impl;

import com.csit.system.domain.InspectionAir;
import com.csit.system.domain.SysConfig;
import com.csit.system.mapper.InspectionMapper;
import com.csit.system.mapper.SysConfigMapper;
import com.csit.system.service.IInspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class InspectionServiceImpl implements IInspectionService {
    @Resource
    private InspectionMapper inspectionMapper;

    /**
     * 查询参数配置信息
     *
     * @param id 参数配置ID
     * @return 参数配置信息
     */
    @Override
    public InspectionAir selectInspectionById(String id)
    {
        //InspectionAir inspectionAir = new InspectionAir();
        return inspectionMapper.selectInspectionById(id);
    }

    @Override
    public InspectionAir selectInspectionByQrCode(String qrcode) {
        return inspectionMapper.selectInspectionByQrCode(qrcode);
    }

    @Override
    public int insertInspection(InspectionAir inspectionAir) {
        return inspectionMapper.insertInspection(inspectionAir);
    }
}
