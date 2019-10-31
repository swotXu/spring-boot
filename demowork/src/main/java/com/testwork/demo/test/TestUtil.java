package com.testwork.demo.test;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestUtil {

    public static void regex(){
        String src = "pase:{parmes:'dwada'},maxlength:{maggess:'大势大8',parmes:'8'},pase:{parmes:'dwada'}";
        String reg = "(?<=maxlength:.*?parmes:').*?(?=')";
        reg = "(?<=maxlength:\\{).*?(?=})";
        reg = "\\d+";
        Pattern compile = Pattern.compile(reg);
        Matcher matcher = compile.matcher(src);
        if (matcher.find()) {
            String group = matcher.group();
            System.out.println(group);
        }
    }

    public static void regexUrl(){
        String reg = "(?<=\\(['\"]).*?(?=['\"])|(?<=m_strContextPath.{0,10}?['\"]).*?(?=['\"])";
        reg = "(?<=\\(['\"]).*?(?=['\"])|(?<=,['\"]).*?(?=['\"],)";
        String url = "javascript:openiframe('http://tool.oschina.net/regex/');";
        url = "javascript:openiframe(m_strContextPath+\"form/529405995663.xform?moduleId=469946&codeNo=SPSC&listTitle=\"+encodeURIComponent(\"视频上传\")+\"&linkname=\"+encodeURIComponent(\"首页-->日常事务-->视频上传\")+\"&btnName=\"+encodeURIComponent(\"新建\"),this)";
        url = "javascript:openiframe(\"1231231\",'33421241',341,23154);";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(url);

        String result = matcher.find() ? matcher.group() : "";
        System.out.println(result);
        result = matcher.find() ? matcher.group() : "";
        System.out.println(result);
    }

    public static void main(String[] args) {
        TestUtil.regex();
        TestUtil.regexUrl();

        /*BigDecimal bignum1 = new BigDecimal("10.0");
        BigDecimal bignum2 = new BigDecimal("5");
//        BigDecimal subtract = bignum1.subtract(bignum2);
//        System.out.println(subtract);
        BigDecimal divide = bignum1.divide(bignum2,8, BigDecimal.ROUND_HALF_UP);
        System.out.println(divide.doubleValue());*/
    }
}
