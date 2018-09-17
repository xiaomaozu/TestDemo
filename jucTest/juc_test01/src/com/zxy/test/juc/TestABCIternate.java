package com.zxy.test.juc;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 编写一个程序，开启 3 个线程，这三个线程的 ID 分别为 A、B、C，每个线程将自己的 ID 在屏幕上打印 10 遍，要求输出的结果必须按顺序显示。
 *	如：ABCABCABC…… 依次递归
 *5,3,8
 */
public class TestABCIternate {

    public static void main(String[] args) {

        final AlternateDemo alternateDemo=new AlternateDemo();

        new Thread(new Runnable(){
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    alternateDemo.loopA(i);
               //     System.out.println("-----------------------------------");
                }
            }
        }).start();
        new Thread(new Runnable(){
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    alternateDemo.loopB(i);
                 //   System.out.println("-----------------------------------");
                }
            }
        }).start();
        new Thread(new Runnable(){
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    alternateDemo.loopC(i);
                    System.out.println("-----------------------------------");
                }
            }
        }).start();
    }

}

class AlternateDemo{

    private String flag="A";
    private Lock lock=new ReentrantLock();

    private Condition condition1=lock.newCondition();
    private Condition condition2=lock.newCondition();
    private Condition condition3=lock.newCondition();

    public void loopA(int loopNum){

        lock.lock();

        try {
            if (!flag.equals("A")) {
                condition1.await();
            }
            for(int i=0;i<5;i++){
                System.out.println(Thread.currentThread().getName()+"--"+i+"----"+loopNum);
            }
            flag="B";
            condition2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void loopB(int loopNum){

        lock.lock();

        try {
            if (!flag.equals("B")) {
                condition2.await();
            }
            for(int i=0;i<3;i++){
                System.out.println(Thread.currentThread().getName()+"--"+i+"----"+loopNum);
            }
            flag="C";
            condition3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void loopC(int loopNum){

        lock.lock();

        try {
            if (!flag.equals("C")) {
                condition3.await();
            }
            for(int i=0;i<8;i++){
                System.out.println(Thread.currentThread().getName()+"--"+i+"----"+loopNum);
            }
            flag="A";
            condition1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
