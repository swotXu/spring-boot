package com.testxu.basecoder.ThinkingInJava.c15Generic.demo1;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname: spring-boot
 * @Date: 2019/9/18 0018 15:40
 * @Author: xu.hai
 * @Description:
 */
public class ClassTypeCapture {

    Map<String,Class<?>> map = new HashMap<>();

    void add(String key,Class<?> kind){
        map.put(key, kind);
    }
    Object createNew(String key){
        Class<?> aclass = map.get(key);
        Object o = null;
        try {
            o = aclass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return o;
    }
}
