package com.csit.system.mapper;

import com.csit.system.domain.InspectionAir;

import java.util.Map;

public interface InspectionMapper {

    public InspectionAir selectInspectionById(String id);

    public InspectionAir selectInspectionByQrCode(String qrcode);

    public int insertInspection(InspectionAir inspectionAir);

}
