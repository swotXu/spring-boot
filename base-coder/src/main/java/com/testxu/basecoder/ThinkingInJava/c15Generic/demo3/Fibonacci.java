package com.testxu.basecoder.ThinkingInJava.c15Generic.demo3;

/**
 * @Classname: spring-boot
 * @Date: 2019/9/20 0020 10:14
 * @Author: xu.hai
 * @Description:
 */
public class Fibonacci implements Generator<Integer> {
    private int count = 0;

    @Override
    public Integer next() {
        return fib(count++);
    }
    private int fib(int n){
        if(n<2) return 1;
        return fib(n-2) + fib(n-1);
    }

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        for (int i = 0; i < 10; i++) {
            Integer next = fibonacci.next();
            System.out.println(next + " ");
        }
    }
}
