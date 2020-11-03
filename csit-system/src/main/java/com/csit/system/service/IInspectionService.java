package com.csit.system.service;

import com.csit.system.domain.InspectionAir;
import com.csit.system.domain.SysConfig;

import java.util.Map;

public interface IInspectionService {
    public InspectionAir selectInspectionById(String id);

    public InspectionAir selectInspectionByQrCode(String qrcode);

    public int insertInspection(InspectionAir inspectionAir);
}
