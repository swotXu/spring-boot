package com.testwork.demo.genericityDemo.demo3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname: spring-boot
 * @Date: 2019/9/17 22:03
 * @Author: xuhai
 */
public class ClassTypeCapture {
    private Map<String,Class<?>> map = new HashMap<>();

    void addType(String typeName, Class<?> kind){
        map.put(typeName, kind);
    }

    <E>E createNew(String typeName){
        Class<E> aClass = (Class<E>) map.get(typeName);
        try {
            Object o = aClass.newInstance();
            return (E) aClass.cast(o);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        ClassTypeCapture ctc = new ClassTypeCapture();
        ctc.addType("testString", String.class);
        ctc.addType("testList", ArrayList.class);

        String testString = ctc.createNew("testString");
        System.out.println(testString);

        Object testList = ctc.createNew("testList");
        System.out.println(testList);
    }
}
