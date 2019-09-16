package com.testwork.demo.genericityDemo.demo1;


import com.testwork.demo.genericityDemo.demo1.interface1.GenInterfice;
import com.testwork.demo.genericityDemo.demo1.interface1.GenInterficeImpl1;
import com.testwork.demo.genericityDemo.demo1.interface1.GenInterficeImpl2;

public class Main1 {
    static class Adapt<T extends GenInterfice>{
        public void adp(T t){
            t.eat(t.likeFoot());
        }
    }

    public static void main(String[] args) {
        Adapt adapt = new Adapt();
        adapt.adp(new GenInterficeImpl1());
        adapt.adp(new GenInterficeImpl2());
    }
}
