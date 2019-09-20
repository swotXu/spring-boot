package com.testxu.basecoder.ThinkingInJava.c15Generic.demo3;

import java.util.Iterator;

/**
 * @Classname: spring-boot
 * @Date: 2019/9/20 0020 12:19
 * @Author: xu.hai
 * @Description:
 */
public class IterableFibonacci extends Fibonacci implements Iterable<Integer> {
    private int count;

    IterableFibonacci(int count){
        this.count = count;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return count > 0;
            }

            @Override
            public Integer next() {
                count--;
                return IterableFibonacci.this.next();
            }
        };
    }

    public static void main(String[] args) {
        IterableFibonacci fibonacci = new IterableFibonacci(10);
        for (int i : fibonacci) {
            System.out.println(i);
        }
    }
}
