/*
package com.csit.web.controller.inspection;

import com.csit.system.domain.InspectionAir;
import com.csit.system.domain.MyConst;
import com.csit.system.domain.ResultCode;
import com.csit.system.service.IInspectionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value="/inspection")
public class InspectionController {

    @Resource
    IInspectionService iInspectionService;

    @GetMapping(value = "/getInspectionById/{id}")
    @ResponseBody
    public ResultCode getInspectionById(@PathVariable("id") String id){
        InspectionAir inspectionAir = iInspectionService.selectInspectionById(id);
        return new ResultCode(MyConst.REQUEST_SUCCESS_CODE,MyConst.REQUEST_SUCCESS_MSG,inspectionAir);
    }

    @GetMapping(value = "/getInspectionByQrCode/{qrcode}")
    @ResponseBody
    public ResultCode getInspectionByQrCode(@PathVariable("qrcode") String qrcode){
        InspectionAir inspectionAir = iInspectionService.selectInspectionByQrCode(qrcode);
        return new ResultCode(MyConst.REQUEST_SUCCESS_CODE,MyConst.REQUEST_SUCCESS_MSG,inspectionAir);
    }

    @GetMapping(value = "/insertInspection")
    @ResponseBody
    public ResultCode insertInspection(InspectionAir inspectionAir){
        int count = iInspectionService.insertInspection(inspectionAir);
        return new ResultCode(MyConst.REQUEST_SUCCESS_CODE,MyConst.REQUEST_SUCCESS_MSG,count);
    }

}
*/
