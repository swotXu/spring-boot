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
        int[] arr = getArr(10000);
        toStr(arr);

        // 冒泡
        sort1(arr, true);
//        sort1(arr, false);
        // 选择
//        sort2(arr, true);
//        sort2(arr, false);


        toStr(arr);

    }

    /**
     * 冒泡排序  O(n^2)
     * @param arr
     * @param escOrDesc true-正序 false-倒序
     */
    public static void sort1(int[] arr, boolean escOrDesc){
        long start = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                boolean flag = (escOrDesc && arr[i] > arr[j]) || (!escOrDesc && arr[i] < arr[j]);
                if(flag){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("冒泡排序  O(n^2) : " + (end - start));
    }

    /**
     * 选择排序 O(n^2)
     * @param arr
     * @param escOrDesc true-正序 false-倒序
     */
    public static void sort2(int[] arr, boolean escOrDesc){
        long start = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            int index = i;
            for (int j = i+1; j < arr.length; j++) {
                boolean flag = (escOrDesc && arr[index] > arr[j]) || (!escOrDesc && arr[index] < arr[j]);
                if(flag){
                    index = j;
                }
            }
            if(index != i){
                int temp = arr[i];
                arr[i] = arr[index];
                arr[index] = temp;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("选择排序  O(n^2) : " + (end - start));
    }

    /**
     * 基数排序 O(n)
     * @param arr
     * @param escOrDesc
     */
    public static void sort3(int[] arr, boolean escOrDesc){

    }

}
