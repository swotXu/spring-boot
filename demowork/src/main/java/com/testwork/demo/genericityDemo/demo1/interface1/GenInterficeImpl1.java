package com.testwork.demo.genericityDemo.demo1.interface1;

public class GenInterficeImpl1 implements GenInterfice{
    @Override
    public String likeFoot() {
        return "苹果";
    }

    @Override
    public void eat(String foot) {
        System.out.println("GenInterficeImpl1 foot: " + foot);
    }
}
