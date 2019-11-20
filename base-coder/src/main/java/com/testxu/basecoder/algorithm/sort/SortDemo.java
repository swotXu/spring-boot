package com.testxu.basecoder.algorithm.sort;

import java.util.Arrays;

/**
 * @Classname: spring-boot
 * @Date: 2019/11/19 0019 9:17
 * @Author: xu.hai
 * @Description:
 */
public class SortDemo {

    public static int[] getArr(int length){
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int)(Math.random() * (length>>1)) + 1;
        }
        return arr;
    }
    public static void toStr(int[] arr){
        System.out.println(Arrays.toString(arr));
    }


    public static void main(String[] args) {
        int[] arr = getArr(2000);
        toStr(arr);

        sort1(arr);

    }

    public static void sort1(int[] arr){

    }
}
