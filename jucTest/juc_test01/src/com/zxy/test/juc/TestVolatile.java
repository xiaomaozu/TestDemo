package com.zxy.test.juc;

/*
 * 一、volatile 关键字：当多个线程进行操作共享数据时，可以保证内存中的数据可见。
 * 					  相较于 synchronized 是一种较为轻量级的同步策略。
 *
 * 注意：
 * 1. volatile 不具备“互斥性”
 * 2. volatile 不能保证变量的“原子性”
 */
public class TestVolatile {

    public static void main(String[] args) {

        ThreadDemo threadDemo = new ThreadDemo();
        new Thread(threadDemo).start();

        while (true) {
            //1.volatile 解决的是内存可见性问题，当子线程对Thread内部的一个属性进行修改时，
            //这时该属性对主线程来说时不可见的，也就是说主线程不能访问到该属性修改后的值。
            //有两种解决方法  第一种使用同步Synchronized 同步代码，会对不断该变量进行同步。如下:
           /* synchronized (threadDemo) {
                if (threadDemo.isFlag()) {
                    System.out.println("---------------");
                    break;
                }
            }*/
           //第二种:  设置属性为volatile
            if(threadDemo.isFlag()){
                System.out.println("----------------");
                break;
            }
        }
    }
}
class ThreadDemo implements Runnable{

    private volatile boolean flag=false;

    @Override
    public void run() {

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag=true;
        System.out.println("flag=" + isFlag());

    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
