package com.csit.web.controller.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.csit.common.json.JSONParam;
import com.csit.framework.shiro.service.SysPasswordService;
import com.csit.system.domain.*;
import com.csit.system.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.csit.common.annotation.Log;
import com.csit.common.enums.BusinessType;
import com.csit.common.core.controller.BaseController;
import com.csit.common.core.domain.AjaxResult;
import com.csit.common.utils.poi.ExcelUtil;
import com.csit.common.core.page.TableDataInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 设备信息采集Controller
 * 
 * @author csit
 * @date 2019-11-26
 */
@Api("用户信息管理")
@RestController
@RequestMapping("/Api/Device")
public class YxDeviceDayController extends BaseController
{
    private String prefix = "system/deviceData";

    @Autowired
    private IYxDeviceDayService yxDeviceDayService;

    @Autowired
    private IYxAssetinfoListService yxAssetinfoListService;

    @Autowired
    private IYxDeviceConfigService yxDeviceConfigService;


    @Autowired
    private IYxAssetfaultlogService yxAssetfaultlogService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private IYxDeviceService yxDeviceService;

    @Autowired
    private SysPasswordService passwordService;


    @Autowired
    private IYxWechartInfoService yxWechartInfoService;

    @Autowired
    private IYxWechartDeviceMonthService yxWechartDeviceMonthService;


    @Autowired
    private IYxQrcodeConfigService yxQrcodeConfigService;

    @RequiresPermissions("system:deviceData:view")
    @GetMapping()
    public String deviceData()
    {
        return prefix + "/deviceData";
    }




    @ApiOperation("新增设备信息")
    @ApiImplicitParam(name = "YxDeviceDay", value = "新增设备信息", dataType = "YxDeviceDay")
    @PostMapping("/save")
    public AjaxResult addSave(YxDeviceDay yxDeviceDay)
    {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        yxDeviceDay.setId(uuid);
        System.out.print(yxDeviceDay.toString());
        return toAjax(yxDeviceDayService.insertYxDeviceDay(yxDeviceDay));
    }


    /**
     * 产品信息查询
     */
    @CrossOrigin(origins = "*")
    @ApiOperation("产品信息查询")
    @GetMapping("/asset/{qrcode}")
    public AjaxResult asset(@PathVariable("qrcode") String qrcode)
    {
        YxAssetinfoList yxAssetinfoList = yxAssetinfoListService.selectYxAssetinfoListByQrcode(qrcode);

        if (yxAssetinfoList!=null)
        {
            return AjaxResult.success(yxAssetinfoList);
        }else
        {
            return error("数据不存在！");
        }
    }

    /**
     * 维修信息查询
     */
    @CrossOrigin(origins = "*")
    @ApiOperation("维修信息查询")
    @GetMapping("/assetFault/{qrcode}")
    public AjaxResult assetFault(@PathVariable("qrcode") String qrcode)
    {
        YxAssetfaultlog Assetfault=new YxAssetfaultlog();
       //String code[]= qrcode.split("/");

        Assetfault.setQrcode(qrcode);

       List<YxAssetfaultlog> logList = yxAssetfaultlogService.selectYxAssetfaultlogList(Assetfault) ;

        if (logList!=null)
        {
            return AjaxResult.success(logList);
        }else
        {
            return error("数据不存在！");
        }
    }

    /**
     * 新增保存维修记录
     */
    @CrossOrigin(origins = "*")
    @Log(title = "新增保存维修记录", businessType = BusinessType.INSERT)
    @PostMapping("/assetFaultAdd")
    @ResponseBody
    public AjaxResult assetFaultAdd(HttpServletRequest request)
    {
        JSONParam jp=new JSONParam();
        JSONObject object = jp.getJSONParam(request);

        YxAssetfaultlog Assetfault=new YxAssetfaultlog();

        Assetfault.setCreateby(object.get("uid").toString());
        Assetfault.setQrcode(object.get("qrcode").toString());
        Assetfault.setFault(object.get("fault").toString());
        Assetfault.setRepair(object.get("repair").toString());

        return toAjax(yxAssetfaultlogService.insertYxAssetfaultlog(Assetfault));
    }

    /**
     * 新增保存维修记录
     */
    @CrossOrigin(origins = "*")
    @Log(title = "新增保存维修记录", businessType = BusinessType.INSERT)
    @PostMapping("/Login")
    @ResponseBody
    public AjaxResult Login(HttpServletRequest request)
    {
        JSONParam jp=new JSONParam();
        JSONObject object = jp.getJSONParam(request);

        String password=object.get("password").toString();
        String name =object.get("uid").toString();

        SysUser user=  userService.selectUserByLoginName(name);


        if(user!=null) {

            SysUser sysu = new SysUser();


           String Pwd=passwordService.encryptPassword(name, password, user.getSalt());

             if(Pwd.equals(user.getPassword()))
             {
                 return AjaxResult.success("登录成功！");
             }else
             {
                 return error("密码错误！");

             }

        }else
        {
            return error("用户名不存在！");
        }



    }



    /**
     * 新增保存绑定设备
     */

    @CrossOrigin(origins = "*")
    @Log(title = "绑定设备", businessType = BusinessType.INSERT)
     @PostMapping("/assetLink")
    @ResponseBody
    public AjaxResult assetLink(HttpServletRequest request)
    {
        JSONParam jp=new JSONParam();
        JSONObject object = jp.getJSONParam(request);


        YxDeviceConfig yxDeviceConfig=new YxDeviceConfig();
        yxDeviceConfig.setQrcode(object.get("qrcode").toString());
        yxDeviceConfig.setDeviceId(object.get("device_id").toString());
        yxDeviceConfig.setCreateBy(object.get("uid").toString());
        yxDeviceConfig.setUpdateBy(object.get("uid").toString());

        return toAjax(yxDeviceConfigService.insertYxDeviceConfig(yxDeviceConfig));
    }




    @ApiOperation("获取用户列表")
    @GetMapping("/DeviceDataList")
    public AjaxResult DeviceDataList(YxDeviceDay yxDeviceDay)
    {
        List<YxDeviceDay> yxDevice = yxDeviceDayService.selectYxDeviceDayList(yxDeviceDay);
        return AjaxResult.success(yxDevice);
    }

    /**
     * 传感器列表查询
     */
    @CrossOrigin(origins = "*")
    @ApiOperation("传感器列表查询")
    @GetMapping("/DeviceDataList/{deviceCode}")
    public AjaxResult DeviceDataList(@PathVariable("deviceCode") String devicecode)
    {
        YxDeviceCode dev=new YxDeviceCode();

        dev.setDeviceCode(devicecode);

        List<YxDeviceCode> devList = yxDeviceService.selectYxDeviceByCode(dev) ;

        if (devList!=null)
        {
            return AjaxResult.success(devList);
        }else
        {
            return error("数据不存在！");
        }
    }

    @ApiOperation("新增设备信息")
    @ApiImplicitParam(name = "jsonStr", value = "Json对象", required = true, dataType = "String", paramType = "path")
    @PostMapping("/saveBatch")
   // @RequestMapping(value = "/json/data", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
   public AjaxResult batchSave(HttpServletRequest request)
    {

        //获取到JSONObject
        JSONParam jp=new JSONParam();
        try{
        JSONObject object = jp.getJSONParam(request);

        String  gatewayid=object.get("gatewayid").toString();


        JSONArray deviceDataList = JSONArray.parseArray(object.get("deviceData").toString());

        List<YxDeviceDay> devlist=new ArrayList<YxDeviceDay>();
        for(int i=0;i<deviceDataList.size();i++)
        {
            String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");

            YxDeviceDay deviceDay=new YxDeviceDay();
            deviceDay.setId(uuid);
            deviceDay.setGatewayid(gatewayid);

            JSONObject rowData = new JSONObject();
            rowData = deviceDataList.getJSONObject(i);

            deviceDay.setDeviceid(rowData.get("deviceid").toString());
            deviceDay.setHigh(Long.parseLong(rowData.get("high").toString())/10);
            deviceDay.setSeatstatus(Long.parseLong(rowData.get("seatstatus").toString()));

            deviceDay.setDuration(Long.parseLong(rowData.get("cnt").toString()));
            deviceDay.setStarttime(rowData.get("stt").toString());


            String createtime = rowData.get("createtime").toString();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date =null;
            try {
             date = simpleDateFormat.parse(createtime);
            }  catch (ParseException e) {

                e.printStackTrace();
            }
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            Date date = null; //初始化date
//            try {
//                 date = sdf.parse(str); //Mon Jan 14 00:00:00 CST 2013
//                }  catch (ParseException e) {
//
//                e.printStackTrace();
//            }


            deviceDay.setCreatetime(createtime);
            devlist.add(deviceDay);
           }
           yxDeviceDayService.batchInset(devlist);

           return success("Success");

          } catch (Exception e) {
         return error("Error");
         }

    }



    @ApiOperation("获取服务器时间")
    @PostMapping(
            { "/getServerDataTime" })
    @ResponseBody
    public void getServerDataTime(final HttpServletRequest request,
                                  final HttpServletResponse response) throws Exception
    {
        final SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String time=df.format(new Date()) ;
        response.getWriter().print("#" + time+ "#");
    }

    @CrossOrigin(origins = "*")
    @ApiOperation("获取最新的一条设备数据")
    @GetMapping("/getNewestDeviceData")
    public AjaxResult getNewestDeviceData(String deviceId)
    {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("deviceId",deviceId);
        map.put("startTime",ToolsUtils.getTodayStartTime(new Date()));
        map.put("endTime",ToolsUtils.getTodayEndTime(new Date()));
        YxDeviceDay yxDevice = yxDeviceDayService.selectNewestDataByDeviceId(map);
        return AjaxResult.success(yxDevice);
    }
    @CrossOrigin(origins = "*")
    @ApiOperation("根据openid，deviceid获取当天站立次数")
    @GetMapping("/getDayStandUpTimesByOpenIdAndDeviceId")
    public AjaxResult getDayStandUpTimesByOpenIdAndDeviceId(String openid)
    {
        int count = yxDeviceDayService.selectDaySitDownTimesByOpenIdAndDeviceId(openid);
        return AjaxResult.success(count);
    }
    @CrossOrigin(origins = "*")
    @ApiOperation("根据openid，deviceid获取当天坐下次数")
    @GetMapping("/getDaySitDownTimesByOpenIdAndDeviceId")
    public AjaxResult getDaySitDownTimesByOpenIdAndDeviceId(String openid)
    {
        int count = yxDeviceDayService.selectDaySitDownTimesByOpenIdAndDeviceId(openid);
        return AjaxResult.success(count);
    }
    @CrossOrigin(origins = "*")
    @ApiOperation("获取日数据")
    @GetMapping("/getDayDataList")
    public AjaxResult getDayDataList(String deviceId)
    {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("deviceId",deviceId);
        map.put("startTime",ToolsUtils.getTodayStartTime(new Date()));
        map.put("endTime",ToolsUtils.getTodayEndTime(new Date()));
        List<YxDeviceDay> yxDevice = yxDeviceDayService.selectDayDataByDeviceId(map);
        return AjaxResult.success(yxDevice);
    }
    @CrossOrigin(origins = "*")
    @ApiOperation("获取月数据")
    @GetMapping("/getMonthDataList")
    public AjaxResult getMonthDataList(String deviceId)
    {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("deviceId",deviceId);
        map.put("startTime",ToolsUtils.getTodayStartTime(new Date()));
        map.put("endTime",ToolsUtils.getTodayEndTime(new Date()));
        List<YxDeviceDay> yxDevice = yxDeviceDayService.selectMonthDataByDeviceId(map);
        return AjaxResult.success(yxDevice);
    }
    @CrossOrigin(origins = "*")
    @ApiOperation("获取年数据")
    @GetMapping("/getYearDataList")
    public AjaxResult getYearDataList(String deviceId)
    {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("deviceId",deviceId);
        map.put("startTime",ToolsUtils.getTodayStartTime(new Date()));
        map.put("endTime",ToolsUtils.getTodayEndTime(new Date()));
        List<YxDeviceDay> yxDevice = yxDeviceDayService.selectYearDataByDeviceId(map);
        return AjaxResult.success(yxDevice);
    }
    @CrossOrigin(origins = "*")
    @ApiOperation("根据openid获取数据")
    @GetMapping("/getDeviceConfigByOpenId")
    public AjaxResult getDeviceConfigByOpenId(String openid)
    {
        YxDeviceConfig obj = yxDeviceConfigService.selectDeviceConfigByOpenId(openid);
        return AjaxResult.success(obj);
    }

//    @CrossOrigin(origins = "*")
//    @ApiOperation("根据deviceId汇总数据")
//    @GetMapping("/getStandUsedTimeByDeviceId")
//    public AjaxResult getStandUsedTimeByDeviceId(String deviceId,String startTime)
//    {
//        Map<String,Object> map = new HashMap<String,Object>();
//        map.put("deviceId",deviceId);
//        map.put("seatstatus","2");
//        map.put("startTime",startTime);
//        map.put("endTime",ToolsUtils.getTodayEndTime(new Date()));
//        int num = yxDeviceDayService.selectUsedTimeByDeviceId(map);
//        return AjaxResult.success(num);
//    }

    @CrossOrigin(origins = "*")
    @ApiOperation("根据deviceId汇总数据")
    @GetMapping("/getStandNumByDeviceId")
    public AjaxResult getStandNumByDeviceId(String deviceId,String startTime)
    {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("deviceId",deviceId);
        map.put("seatstatus","2");
        map.put("startTime",startTime);
        map.put("endTime",ToolsUtils.getTodayEndTime(new Date()));
        int num = yxDeviceDayService.selectStandNumByDeviceId(map);
        return AjaxResult.success(num);
    }
//    @CrossOrigin(origins = "*")
//    @ApiOperation("根据 qrcode 汇总站立 做下的时长和次数")
//    @GetMapping("/getSitUsedTimeByDeviceId")
//    public AjaxResult getSitUsedTimeByDeviceId(String deviceId,String startTime)
//    {
//        Map<String,Object> map = new HashMap<String,Object>();
//        map.put("deviceId",deviceId);
//        map.put("seatstatus","1");
//        map.put("startTime",startTime);
//        map.put("endTime",ToolsUtils.getTodayEndTime(new Date()));
//        int num = yxDeviceDayService.selectUsedTimeByDeviceId(map);
//        return AjaxResult.success(num);
//    }
    @CrossOrigin(origins = "*")
    @ApiOperation("根据deviceId查询时间段内久坐次数")
    @GetMapping("/getSitNumByDeviceId")
    public AjaxResult getSitNumByDeviceId(String deviceId,String startTime)
    {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("deviceId",deviceId);
        map.put("seatstatus","1");
        map.put("startTime",ToolsUtils.getTodayStartTime(new Date()));
        map.put("endTime",ToolsUtils.getTodayEndTime(new Date()));
        int num = yxDeviceDayService.selectSitNumByDeviceId(map);
        return AjaxResult.success(num);
    }
    @CrossOrigin(origins = "*")
    @ApiOperation("根据deviceId查询最大久坐时长")
    @GetMapping("/getMaxSitTimeByDeviceId")
    public AjaxResult getMaxSitTimeByDeviceId(String deviceId,String startTime)
    {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("deviceId",deviceId);
        map.put("seatstatus","1");
        map.put("startTime",ToolsUtils.getTodayStartTime(new Date()));
        map.put("endTime",ToolsUtils.getTodayEndTime(new Date()));
        int num = yxDeviceDayService.selectMaxSitTimeByDeviceId(map);
        return AjaxResult.success(num);
    }
    @CrossOrigin(origins = "*")
    @ApiOperation("根据deviceId查询时间段内最大连续站立时长")
    @GetMapping("/getMaxStandTimeByDeviceId")
    public AjaxResult selectMaxStandTimeByDeviceId(String deviceId,String startTime)
    {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("deviceId",deviceId);
        map.put("seatstatus","1");
        map.put("startTime",ToolsUtils.getTodayStartTime(new Date()));
        map.put("endTime",ToolsUtils.getTodayEndTime(new Date()));
        int num = yxDeviceDayService.selectMaxStandTimeByDeviceId(map);
        return AjaxResult.success(num);
    }
    @CrossOrigin(origins = "*")
    @ApiOperation("根据deviceId查询时间段内站平均每次站立时长")
    @GetMapping("/getAvgStandTimeByDeviceId")
    public AjaxResult getAvgStandTimeByDeviceId(String deviceId,String startTime)
    {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("deviceId",deviceId);
        map.put("seatstatus","1");
        map.put("startTime",ToolsUtils.getTodayStartTime(new Date()));
        map.put("endTime",ToolsUtils.getTodayEndTime(new Date()));
        int num = yxDeviceDayService.selectAvgStandTimeByDeviceId(map);
        return AjaxResult.success(num);
    }

    //获取openid
    /**
     * 获取登录用户的OPENID
     * @param
     */
    @CrossOrigin(origins = "*")
    @ApiOperation("根据deviceId汇总数据")
    @GetMapping("/getUserOpenID")
    public AjaxResult getUserOpenID(String code){
        //pd = this.getPageData();
        // 微信小程序ID
        String appid = "wx1d984bda2d1645ef";
        // 微信小程序秘钥
        String secret = "85ec4c5942244a3f61d2522c865a28c5";
        // 根据小程序穿过来的code想这个url发送请求
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + secret + "&js_code=" + code + "&grant_type=authorization_code";
        // 发送请求，返回Json字符串
        String str = WeChatUtil.httpRequest(url, "GET", null);
        // 转成Json对象 获取openid
        JSONObject jsonObject = JSONObject.parseObject(str.toString());
        // 我们需要的openid，在一个小程序中，openid是唯一的
        String openid = jsonObject.get("openid").toString();
        getAccess_token(appid,secret);
        // 然后书写自己的处理逻辑即可
        return AjaxResult.success("获取openid成功",openid);
    }
    public String getAccess_token(String appid, String appsecret) {
        //获取access_token
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential" +
                "&appid=" + appid + "&secret=" + appsecret;
        String json = WeChatUtil.httpRequest(url, "GET", null);
        return json;
    }

    @CrossOrigin(origins = "*")
    @ApiOperation("获取最新的一条设备数据")
    @GetMapping("/userDate/{qrcode}")
    public AjaxResult userDate(@PathVariable("qrcode") String qrcode)
    {
        Map<String, Object> mmap=new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("qrcode", qrcode);
        //获取最新数据

        // 获取使用数据 时长及次数
        List<YxDeviceDay> dvList= yxDeviceDayService.selectUsedTimeByDeviceId(map);

        System.out.println(dvList);
        if(dvList.size()==0)
        {

            mmap.put("StandTime", "0");
            mmap.put("sitNum",  "0");
        }else
        {
            int sitTime=0;
            for (YxDeviceDay dv : dvList) {

                if(dv.getSeatstatus()==2)
                {
                    sitTime=Integer.parseInt(dv.getDuration().toString());

                    System.out.println(sitTime);
                    mmap.put("StandTime",sitTime);
                }

            }

            //久坐次数
            int sitNum = 0;
            sitNum= yxDeviceDayService.selectSitNumByDeviceId(map);
            mmap.put("sitNum", sitNum);

        }

        return AjaxResult.success(mmap);
    }


    @CrossOrigin(origins = "*")
    @ApiOperation("获取用户绑定的 周 月 年 数据")
    @GetMapping("/userCenter/{qrcode}")
    public AjaxResult userCenter(@PathVariable("qrcode") String qrcode)
    {

        Map<String, Object> mmap=new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("qrcode", qrcode);

        /*
         * 获取月的数据
         *
         * */


        List<YxDeviceDay> yxmoths= yxDeviceDayService.selectMonthDataByDeviceId(map);

        List<String> months = new ArrayList<String>();
        List<Integer> month0 = new ArrayList<Integer>();
        List<Integer> month1 = new ArrayList<Integer>();
        List<Integer> month2 = new ArrayList<Integer>();
        List<Integer> month9 = new ArrayList<Integer>();


        Date day1=new Date();

        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");


        Calendar cal = Calendar.getInstance();
        int allDays = cal.get(Calendar.DATE);
        int Cmonth = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);

        System.out.println(allDays);

        // int allDays=getDaysOfMonth(dfs.parse(dfs.format(day1)));

        for(int day=1;day<=allDays;day++)
        {
            String dayStr="";
            if(day<10)
            {
                dayStr="0"+String.valueOf(day);
            }else
            {
                dayStr=String.valueOf(day);
            }
            months.add(dayStr);

            month0.add(0);
            month1.add(0);
            month2.add(0);
            month9.add(0);

        }



        if(yxmoths.size()>0) {
            for (YxDeviceDay mo : yxmoths) {
                int siteN=0;

                String day = mo.getCreatetime().replace(" ", "");

                String getDate [] =day.split("-");
                if(getDate.length==2) {
                    String Cdate = getDate[1].toString();


                    int index=-1;
                    for(String m : months){
                        index++;
                        if(m.equals(Cdate)){

                            System.out.println("找到："+index);
                            break;
                        }
                    }

                    if (mo.getSeatstatus() == 0) {
                        // month0.remove(index);
                        month0.add(index,Integer.parseInt(mo.getDuration().toString()));

                    }
                    if (mo.getSeatstatus() == 1) {
                        month1.add(index,Integer.parseInt(mo.getDuration().toString()));
                        siteN=Integer.parseInt(mo.getDuration().toString());
                    }
                    if (mo.getSeatstatus() == 2) {

                        month2.add(index,Integer.parseInt(mo.getDuration().toString()));
                    }
                    if (mo.getSeatstatus() == 9) {
                        month9.add(index,Integer.parseInt(mo.getDuration().toString())-siteN);
                    }
                    // String day = mo.getCreatetime().replace(" ", "");
                    //months.add(day);
                }
            }
        }else
        {

        }

        mmap.put("month", months.toString().replace("[","").replace("]",""));
        mmap.put("month0", month0.toString());
        mmap.put("month1",  month1.toString());
        mmap.put("month2",  month2.toString());
        mmap.put("month9",  month9.toString());

        /*
         *
         * 年
         * */

        List<YxDeviceDay> yxyear= yxDeviceDayService.selectYearDataByDeviceId(map);


        List<String> yearT = new ArrayList<String>();
        List<Integer> year0 = new ArrayList<Integer>();
        List<Integer> year1 = new ArrayList<Integer>();
        List<Integer> year2 = new ArrayList<Integer>();
        List<Integer> year9 = new ArrayList<Integer>();


        for(int y=1;y<=12;y++) {
            if(y<10) {
                yearT.add("0" + String.valueOf(y));
            }else
            {
                yearT.add(String.valueOf(y));
            }
            year0.add(0);
            year1.add(0);
            year2.add(0);
            year9.add(0);
        }

        if(yxyear.size()>0) {

            for (YxDeviceDay ye : yxyear) {


                String mm = ye.getCreatetime().replace(" ", "");

                String getMM [] =mm.split("-");
                int index = -1;
                if(getMM.length==2) {
                    String Cm = getMM[1].toString();

                    for (String m : yearT) {
                        index++;
                        if (m.equals(Cm)) {

                            System.out.println("找到 MM：" + index);
                            break;
                        }
                    }
                }

                int siteN=0;
                if (ye.getSeatstatus() == 0) {
                    year0.add(index,Integer.parseInt(ye.getDuration().toString()));

                }
                if (ye.getSeatstatus() == 1) {
                    siteN=Integer.parseInt(ye.getDuration().toString());
                    year1.add(index,Integer.parseInt(ye.getDuration().toString()));
                }
                if (ye.getSeatstatus() == 2) {
                    year2.add(index,Integer.parseInt(ye.getDuration().toString()));
                }
                if (ye.getSeatstatus() == 9) {
                    year9.add(index,Integer.parseInt(ye.getDuration().toString())-siteN);
                }

            }


        }

        mmap.put("year0", year0.toString());
        mmap.put("year1",  year1.toString());
        mmap.put("year2",  year2.toString());
        mmap.put("year9",  year9.toString());

        return AjaxResult.success(mmap);
    }


    @CrossOrigin(origins = "*")
    @ApiOperation("根据openid获取数据")
    @GetMapping("/getQrcodeByOpenId/{openid}")
    public AjaxResult getQrcodeByOpenId(@PathVariable("openid") String openid)
    {
        Map<String, Object> mmap=new HashMap<String, Object>();

        YxWechartInfo weinfo   = yxWechartInfoService.selectYxWechartInfoById(openid);
        mmap.put("openid", weinfo);

        return AjaxResult.success(mmap);
    }


    @CrossOrigin(origins = "*")
    @ApiOperation("判断用户是否已经绑定")
    @GetMapping("/CheckOpenId")
    public AjaxResult CheckOpenId(String openid)
    {
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("openid",openid);

        List<YxWechartDeviceMonth> totalmonths= yxWechartDeviceMonthService.selectYxWechartDeviceMonthList(map);

        YxQrcodeConfig weinfo =new YxQrcodeConfig();
        weinfo.setOpenid(openid);

       List<YxQrcodeConfig> qrinfo =yxQrcodeConfigService.selectYxQrcodeConfigList(weinfo);

        Map<String, Object> mmap=new HashMap<String, Object>();
        if(qrinfo.size()>0&&totalmonths.size()>0)
        {
            mmap.put("result","1");  //正常显示 绑定有数据

        }else if (qrinfo.size()>0&&totalmonths.size()==0){
            mmap.put("result","2"); //刚绑定未见数据
        }
        else if (qrinfo.size()==0&&totalmonths.size()>0){
            mmap.put("result","3");  //被解绑
        }
        else
        {
            mmap.put("result","0"); // 未绑定
        }
        return AjaxResult.success(mmap);
    }

    @CrossOrigin(origins = "*")
    @ApiOperation("新增openid获取数据")
    @GetMapping("/addOpenId")
    public AjaxResult addOpenId(String openid,String nike,String AvUrl)
    {
        YxWechartInfo weinfo =new YxWechartInfo();
        weinfo.setOpenid(openid);
        weinfo.setNickname(nike);
        weinfo.setAvatarurl(AvUrl);
       // yxWechartInfoService.insertYxWechartInfo(weinfo);
        return toAjax(yxWechartInfoService.insertYxWechartInfo(weinfo));
    }

    @CrossOrigin(origins = "*")
    @ApiOperation("绑定升降桌")
    @GetMapping("/addQrcodeOpenId")
    public AjaxResult addQrcodeOpenId(String openid,String Qrcode)
    {
        YxQrcodeConfig weinfo =new YxQrcodeConfig();
        weinfo.setOpenid(openid);
        weinfo.setQrcode(Qrcode);
        weinfo.setCreateBy("wechart");
        weinfo.setUpdateBy("wechart");
        return toAjax(yxQrcodeConfigService.insertYxQrcodeConfig(weinfo));
    }



}
