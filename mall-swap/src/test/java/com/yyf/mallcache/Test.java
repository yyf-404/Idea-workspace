package com.yyf.mallcache;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class Test {
    public static void main(String[] args) throws UnsupportedEncodingException {
       byte by[]= "语言".getBytes("gbk");
        System.out.println(new String(by,"gbk"));
        System.out.println(new Date());
           //String ss=new String("","")
//        for (int i=0;i<100;i++){
//            double timeOut= ((Math.random())*6);
//            System.out.println((int)timeOut);
//        }
    }
}
