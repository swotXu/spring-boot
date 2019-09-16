package com.testwork.demo.genericityDemo.demo2;

import java.lang.reflect.Array;
import java.util.Arrays;

public class GenerMain1<T> {
    private Class<T> kind;
    public GenerMain1(Class<T> kind){
        this.kind = kind;
    }
    T[] create(int size){
        return (T[])Array.newInstance(kind, size);
    }

    public static void main(String[] args) {
        GenerMain1<String> gm = new GenerMain1(String.class);
        String[] arr = gm.create(10);
        System.out.println(Arrays.toString(arr));
    }
}
