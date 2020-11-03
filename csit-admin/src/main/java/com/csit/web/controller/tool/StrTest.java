package com.csit.web.controller.tool;

import com.alibaba.druid.sql.visitor.functions.Substring;
import com.csit.system.domain.*;
import com.csit.system.service.IYxVilsqrcodeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileNotFoundException;
import java.sql.Array;
import java.util.HashMap;
import java.util.List;

public class StrTest {

    public static void main(String[] args)
    {

//
//        long start_Time = 0,end_Time = 0;
//
//        //计算循环使用的时间
//        start_Time = System.currentTimeMillis(); //从1970-1-1到现在的毫秒
//
//        String str[]={"A","B","C","D","E","F","G","H","I","J","K"};
//        List<YxVilsqrcode> list=null;
//
//        for(int i=10000000;i<=19000000;i++)
//        {
//            YxVilsqrcode qrcode=new YxVilsqrcode();
//            String Code="";
//            for(int j=0;j<8;j++)
//            {
//                int id= Integer.parseInt((i+"").substring(j,j+1));
//                Code+=str[id];
//            }
//            qrcode.setCodeindex(Long.parseLong(i+""));
//            qrcode.setQrcode(Code);
//            list.add(qrcode);
//            if(i%100000==0) {
//                yxVilsqrcodeService.insertYxVilsqrcodeBatch()
//                System.out.println("每十万输出一次："+i + " - " + Code);
//            }
//
//        }
//        end_Time = System.currentTimeMillis();
//        System.out.println("for循环结束," );
//        System.out.printf("循环使用时间是: %d 毫秒\n",(end_Time - start_Time));

    }
}
