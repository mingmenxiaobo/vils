package com.csit.web.controller.tool;

import com.csit.common.core.domain.AjaxResult;
import com.csit.system.domain.*;
import com.csit.system.service.IYxDeviceDayService;
import com.csit.system.service.IYxWechartDeviceMonthService;
import com.csit.system.service.IYxWechartDeviceYearService;
import com.csit.system.service.IYxWechartInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 设备信息采集Controller
 * 
 * @author csit
 * @date 2019-11-26
 */
@Controller
@RequestMapping("/wx")
public class WxReportController
{
    private String prefix = "wx";

    @Autowired
    private IYxDeviceDayService yxDeviceDayService;

    @Autowired
    private IYxWechartDeviceMonthService yxWechartDeviceMonthService;


    @Autowired
    private IYxWechartDeviceYearService yxWechartDeviceYearService;

    @Autowired
    private IYxWechartInfoService yxWechartInfoService;

    /**
     * 小程序
     */
    @GetMapping("/report/{qrcode}")
    public String report(@PathVariable("qrcode") String qrcode ,ModelMap mmap) throws ParseException {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("qrcode",qrcode);
        mmap.put("qrcode",qrcode);


        //获取最新数据
        if(yxDeviceDayService.selectNewestDataByDeviceId(map)!=null) {
            mmap.put("Last", yxDeviceDayService.selectNewestDataByDeviceId(map));
        }else
        {
            YxDeviceDay days=new YxDeviceDay();
            days.setDuration(0l);
            days.setSeatstatus(0l);
            days.setStarttime( "空闲");

            mmap.put("Last",days);
        }

        //获取当天数据；
        List<YxDeviceDay> yxDay= yxDeviceDayService.selectDayDataByDeviceId(map);

        List<String> days = new ArrayList<String>();
        List<Integer> data0 = new ArrayList<Integer>();
        List<Integer> data1 = new ArrayList<Integer>();
        List<Integer> data2 = new ArrayList<Integer>();
        List<Integer> data3 = new ArrayList<Integer>();
        List<Integer> data4 = new ArrayList<Integer>();

        int num=0;
        if(yxDay.size()>0) {
            for (YxDeviceDay day : yxDay) {


                if (num == 0) {
                    days.add("00:00");
                    data0.add(11);
                    data1.add(0);
                    data2.add(0);
                    data3.add(0);
                    data4.add(0);
                }
                if (day.getSeatstatus() == 0) {
                    data0.add(0);
                    data1.add(10);
                    data2.add(0);
                    data3.add(0);
                    data4.add(0);
                }
                if (day.getSeatstatus() == 1 && day.getDuration() < 60) {
                    data0.add(0);
                    data1.add(0);
                    data2.add(10);
                    data3.add(0);
                    data4.add(0);
                }
                if (day.getSeatstatus() == 1 && day.getDuration() >= 60) {
                    data0.add(0);
                    data1.add(0);
                    data2.add(0);
                    data3.add(10);
                    data4.add(0);
                }
                if (day.getSeatstatus() == 2) {
                    data0.add(0);
                    data1.add(0);
                    data2.add(0);
                    data3.add(0);
                    data4.add(10);
                }
                String time = day.getCreatetime().replace(" ", "");

                time = time.substring(0, time.lastIndexOf(":"));

                days.add(time);
                num++;
            }
        }else
        {
            Date day1=new Date();

            SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");

            System.out.println(dfs.format(day1));



         Date date = new Date();// 创建Date类型对象
         // 创建SimpleDateFormat类型对象、 "yyyy-MM-dd HH:ss:mm.SSS"是正则式，分别表示年月日时分秒毫秒
         SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
         // 定义两个时间
         String startTime = dfs.format(day1)+" 00:00:00";
         // String endTime = "2020-02-27 12:00:00";
          // 将两个String类型的时间转换为Date类型，从而计算差值、parse()方法的作用是将String类型的时间解析为Date类型

          Date d1 = df.parse(startTime);

            System.out.println(((date.getTime() - d1.getTime()) / (60 * 60 * 1000)) % 24);

            //计算小时
            int times= (int) (((date.getTime() - d1.getTime()) / (60 * 60 * 1000)) % 24);

            for (int i=0;i<=times ;i++ ) {

                if (i== 0) {
                    days.add("00:00");
                    data0.add(11);
                    data1.add(0);
                    data2.add(0);
                    data3.add(0);
                    data4.add(0);
                }else if(i<10 && i>0) {
                    days.add("0" + i+":00");

                }else
                {
                    days.add(i+":00");

                }
                data1.add(10);
            }
            days.add(date.toString().substring(11,16));
            data1.add(10);

        }
        System.out.println(days.toString());

        mmap.put("days", days.toString().replace("[","").replace("]",""));
        mmap.put("data0", data0.toString());
        mmap.put("data1",  data1.toString());
        mmap.put("data2",  data2.toString());
        mmap.put("data3",  data3.toString());
        mmap.put("data4",  data4.toString());
        mmap.put("qrcode",  qrcode);
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


        }else
        {

        }




        mmap.put("year0", year0.toString());
        mmap.put("year1",  year1.toString());
        mmap.put("year2",  year2.toString());
        mmap.put("year9",  year9.toString());




         // 获取使用数据 时长及次数
        List<YxDeviceDay> dvList= yxDeviceDayService.selectUsedTimeByDeviceId(map);
        if(dvList==null)
        {
            mmap.put("standRatio", "0");
            mmap.put("currentStandTime", "0");
            mmap.put("currentSitTime", "0");
            mmap.put("standNum",  "0");
        }else
        {
              int sitTime=0;
              int standTime=0;
              int standRatio=0;
              int standNum=0;

            for (YxDeviceDay dv : dvList) {

                if(dv.getSeatstatus()==1)
                {
                    sitTime=Integer.parseInt(dv.getDuration().toString());

                }

                if(dv.getSeatstatus()==2)
                {
                    standTime=Integer.parseInt(dv.getDuration().toString());
                    standNum=Integer.parseInt(dv.getStarttime());

                }


            }
             if(sitTime==0&&standTime==0) {
                 standRatio=0;
             }
             if(sitTime==0&&standTime>0)
             {
                 standRatio=100;
             }
            if(sitTime>0&&standTime>0)
            {
                double rat=(standTime*100)/sitTime;
                standRatio=(int)rat;
            }

            mmap.put("currentStandTime",standTime);
            mmap.put("standNum",standNum);
            mmap.put("standRatio", standRatio);
            mmap.put("TotalTime", sitTime+standTime);
            mmap.put("currentSitTime",sitTime);

            if(standNum!=0) {
                mmap.put("avgStandTime", (int) standTime / standNum);
            }else
            {
                mmap.put("avgStandTime", '0');
            }

            //久坐次数
            int sitNum = 0;

                sitNum= yxDeviceDayService.selectSitNumByDeviceId(map);

            //最大久坐时长
            int maxSitTime = 0;
                maxSitTime = yxDeviceDayService.selectMaxSitTimeByDeviceId(map);


            //最大连续站立的时长
            int maxStandTime = 0;
                maxStandTime=yxDeviceDayService.selectMaxStandTimeByDeviceId(map);

            mmap.put("sitNum", sitNum);
            mmap.put("maxSitTime", maxSitTime);
            mmap.put("maxStandTime", maxStandTime);

        }

        return prefix + "/echarts";
    }

    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

    }




    /**
     * 小程序
     */
    @GetMapping("/userReport/{qrcode}")
    public String userReport(@PathVariable("qrcode") String qrcode ,ModelMap mmap) throws ParseException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("qrcode", qrcode);
        //获取最新数据

        // 获取使用数据 时长及次数
        List<YxDeviceDay> dvList= yxDeviceDayService.selectUsedTimeByDeviceId(map);
        if(dvList==null)
        {

            mmap.put("currentStandTime", "0");
            mmap.put("sitNum",  "0");
        }else
        {
            int sitTime=0;
            //久坐次数
            int sitNum = 0;
            for (YxDeviceDay dv : dvList) {

                if (dv.getSeatstatus() == 2) {
                    sitTime = Integer.parseInt(dv.getDuration().toString());

                }
                sitNum = yxDeviceDayService.selectSitNumByDeviceId(map);

            }
            mmap.put("currentStandTime", sitTime);
            mmap.put("sitNum", sitNum);

        }

        return prefix + "/userReportold";

    }

    /**
     * 小程序
     */
    @GetMapping("/userCenter/{openid}")
    public String userCenter(@PathVariable("openid") String openid ,ModelMap mmap) throws ParseException {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("openid",openid);
        mmap.put("openid",openid);


        YxWechartInfo weinfo   = yxWechartInfoService.selectYxWechartInfoById(openid);
        mmap.put("wechar",weinfo);

        /*
         * 获取周的数据
         *
         * */


        List<YxWechartDeviceMonth> yxweek=yxWechartDeviceMonthService.selectYxWechartDeviceWeekList(map);

        List<String> week = new ArrayList<String>();
        List<Integer> week0 = new ArrayList<Integer>();
        List<Integer> week1 = new ArrayList<Integer>();
        List<Integer> week2 = new ArrayList<Integer>();
        List<Integer> week9 = new ArrayList<Integer>();


        Date weekday=new Date();

        SimpleDateFormat dfs0 = new SimpleDateFormat("yyyy-MM-dd");


        Calendar calw = Calendar.getInstance();
        int allweekDay = calw.get(Calendar.DATE);
        int wmonth = calw.get(Calendar.MONTH) + 1;
        int wyear = calw.get(Calendar.YEAR);

        System.out.println(allweekDay);



        for(int day=allweekDay-7;day<=allweekDay;day++)
        {
            String dayStr="";
            if(day<10)
            {
                dayStr="0"+String.valueOf(day);
            }else
            {
                dayStr=String.valueOf(day);
            }
            week.add(dayStr);

            week0.add(0);
            week1.add(0);
            week2.add(0);
            week9.add(0);

        }

        if(yxweek.size()>0) {
            for (YxWechartDeviceMonth mo : yxweek) {
                int siteN=0;

                String day = mo.getCreatetime().replace(" ", "");

                String getDate [] =day.split("-");
                if(getDate.length>=2) {
                    String Cdate = getDate[2].toString();


                    int index=-1;
                    for(String m : week){
                        index++;
                        if(m.equals(Cdate)){

                            System.out.println("找到："+index);
                            break;
                        }
                    }

                    if (mo.getSeatstatus() == 0) {
                        // month0.remove(index);
                        week0.add(index,Integer.parseInt(mo.getDuration().toString()));

                    }
                    if (mo.getSeatstatus() == 1) {
                        week1.add(index,Integer.parseInt(mo.getDuration().toString()));
                        siteN=Integer.parseInt(mo.getDuration().toString());
                    }
                    if (mo.getSeatstatus() == 2) {

                        week2.add(index,Integer.parseInt(mo.getDuration().toString()));
                    }
                    if (mo.getSeatstatus() == 9) {
                        week9.add(index,Integer.parseInt(mo.getDuration().toString())-siteN);
                    }
                    // String day = mo.getCreatetime().replace(" ", "");
                    //months.add(day);
                }
            }
        }else
        {

        }

        removeArr(week0,6);
        removeArr(week1,6);
        removeArr(week2,6);
        removeArr(week9,6);

        mmap.put("week",  week.toString().replace("[","").replace("]",""));
        mmap.put("week0", week0.toString());
        mmap.put("week1", week1.toString());
        mmap.put("week2", week2.toString());
        mmap.put("week9", week9.toString());


        /*
         * 获取月的数据
         *
         * */


        List<YxWechartDeviceMonth> yxmoths=yxWechartDeviceMonthService.selectYxWechartDeviceMonthList(map);

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
            for (YxWechartDeviceMonth mo : yxmoths) {
                int siteN=0;

                String day = mo.getCreatetime().replace(" ", "");

                String getDate [] =day.split("-");
                if(getDate.length>=2) {
                    String Cdate = getDate[2].toString();


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

        List<YxWechartDeviceYear> yxyear= yxWechartDeviceYearService.selectYxWechartDeviceYearList(map);



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

            for (YxWechartDeviceYear ye : yxyear) {


                String mm = ye.getCreatetime().replace(" ", "");

                String getMM [] =mm.split("-");
                int index = -1;
                if(getMM.length>=2) {
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


        }else
        {

        }




        mmap.put("year0", year0.toString());
        mmap.put("year1",  year1.toString());
        mmap.put("year2",  year2.toString());
        mmap.put("year9",  year9.toString());



        List<YxWechartDeviceMonth> totalWeek= yxWechartDeviceMonthService.selectYxWechartDeviceWeekTotal(openid);

        List<YxWechartDeviceMonth> totalmonths= yxWechartDeviceMonthService.selectYxWechartDeviceMonthTotal(openid);

        List<YxWechartDeviceYear> totalYears=   yxWechartDeviceYearService.selectYxWechartDeviceYearTotal(openid);


        // 获取使用数据周汇总
        if(totalWeek==null)
        {
            mmap.put("weekStandTime", "0");
            mmap.put("weekSitTime", "0");
        }else
        {
            int sitTime=0;
            int standTime=0;

            for (YxWechartDeviceMonth weekDv : totalWeek) {

                if(weekDv.getSeatstatus()==9)
                {
                    sitTime=Integer.parseInt(weekDv.getDuration().toString());

                }

                if(weekDv.getSeatstatus()==2)
                {
                    standTime=Integer.parseInt(weekDv.getDuration().toString());
                }


            }
            mmap.put("weekStandTime",standTime);
            mmap.put("weekSitTime",sitTime);
        }

        // 获取使用数据月汇总
        if(totalmonths==null)
        {
            mmap.put("monthStandTime", "0");
            mmap.put("monthSitTime", "0");
        }else
        {
            int sitTime=0;
            int standTime=0;

            for (YxWechartDeviceMonth monthDv : totalmonths) {

                if(monthDv.getSeatstatus()==9)
                {
                    sitTime=Integer.parseInt(monthDv.getDuration().toString());

                }

                if(monthDv.getSeatstatus()==2)
                {
                    standTime=Integer.parseInt(monthDv.getDuration().toString());
                }


            }
            mmap.put("monthStandTime",standTime);
            mmap.put("monthSitTime",sitTime);
        }

        // 获取使用数据年汇总
        if(totalYears==null)
        {
            mmap.put("yearStandTime", "0");
            mmap.put("yearSitTime", "0");
        }else
        {
            int sitTime=0;
            int standTime=0;

            for (YxWechartDeviceYear yearDv : totalYears) {

                if(yearDv.getSeatstatus()==9)
                {
                    sitTime=Integer.parseInt(yearDv.getDuration().toString());

                }

                if(yearDv.getSeatstatus()==2)
                {
                    standTime=Integer.parseInt(yearDv.getDuration().toString());
                }


            }
            mmap.put("yearStandTime",standTime);
            mmap.put("yearSitTime",sitTime);
        }

        return prefix + "/userReport";

    }
    public static void removeArr(List<Integer> list,int index) {
        for (int i = 0, len = list.size(); i < len; i++) {
            if (i>index) {
                list.remove(i);
                len--;
                i--;
            }
        }
    }


    @GetMapping("/projectMap")
    public String projectMap(String deptid, ModelMap mmap)
    {

        return prefix + "/projectMap";
    }




}
