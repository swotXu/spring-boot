package com.testwork.demo.genericityDemo.demo1.interface1;

public class GenInterficeImpl2 implements GenInterfice{
    @Override
    public String likeFoot() {
        return "香蕉";
    }

    @Override
    public void eat(String foot) {
        System.out.println("GenInterficeImpl2 foot: " + foot);
    }
}
