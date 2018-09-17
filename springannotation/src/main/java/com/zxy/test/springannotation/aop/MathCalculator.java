package com.zxy.test.springannotation.aop;

public class MathCalculator {

    public int div(int i,int j){
        System.out.println("方法被调用");
        return i/j;
    }
}
