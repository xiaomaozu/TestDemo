package com.zxy.test.juc;

import java.util.concurrent.CountDownLatch;

/**
 * 在完成某些运算时只有其他所有线程的运算1全部完成其他的于是才会继续执行
 *
 * 正如每个Java文档所描述的那样，CountDownLatch是一个同步工具类，它允许一个或多个线程一直等待，直到其他线程的
 * 操作执行完后再执行。
 *
 * 它们都存在于java.util.concurrent包下。CountDownLatch这个类能够使一个线程等待其他线程完成各自的工作后再执行。
 * 例如，应用程序的主线程希望在负责启动框架服务的线程已经启动所有的框架服务之后再执行。
 *
 * CountDownLatch是通过一个计数器来实现的，计数器的初始值为线程的数量。每当一个线程完成了自己的任务后，\\
 * 计数器的值就会减1。当计数器值到达0时，
 * 它表示所有的线程已经完成了任务，然后在闭锁上等待的线程就可以恢复执行任务。
 *
 * 在实时系统中的使用场景
 * 让我们尝试罗列出在java实时系统中CountDownLatch都有哪些使用场景。我所罗列的都是我所能想到的。如果你有别的可
 * 能的使用方法，请在留言里列出来，这样会帮助到大家。
 *
 * 实现最大的并行性：有时我们想同时启动多个线程，实现最大程度的并行性。例如，我们想测试一个单例类。
 * 如果我们创建一个初始计数为1的CountDownLatch，并让所有线程都在这个锁上等待，那么我们可以很轻松地完成测试。
 * 我们只需调用 一次countDown()方法就可以让所有的等待线程同时恢复执行。
 * 开始执行前等待n个线程完成各自任务：例如应用程序启动类要确保在处理用户请求前，所有N个外部系统已经启动和运行了。
 *
 * 死锁检测：一个非常方便的使用场景是，你可以使用n个线程访问共享资源，在每次测试阶段的线程数目是不同的，并尝试
 * 产生死锁。
 */
public class TestCountDownLatch {

    public static void main(String[] args) {

        //构造器中的计数值（count）实际上就是闭锁需要等待的线程数量。
        // 这个值只能被设置一次，而且CountDownLatch没有提供任何机制去重新设置这个计数值。
        CountDownLatch countDownLatch = new CountDownLatch(5);
        CountDownLatchDemo test=new CountDownLatchDemo(countDownLatch);

        long startTime=System.currentTimeMillis();

        for(int i=0;i<5;i++){
            new Thread(test).start();
        }

        try {
            countDownLatch.await();
        }catch (Exception e){
            e.printStackTrace();
        }
        long endTime=System.currentTimeMillis();

        System.out.println("时间总花费："+(endTime-startTime));
    }
}

class CountDownLatchDemo implements  Runnable{
    private CountDownLatch countDownLatch;
    public CountDownLatchDemo(CountDownLatch countDownLatch){
        this.countDownLatch=countDownLatch;
    }

    @Override
    public void run() {
            synchronized (this){
                try{
                    for(int i=0;i<50000;i++){
                        if(i %2 ==0){
                            System.out.println("----"+i+"-----");
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    /**
                     * 与CountDownLatch的第一次交互是主线程等待其他线程。主线程必须在启动其他线程后立即调用
                     * CountDownLatch.await()方法。这样主线程的操作就会在这个方法上阻塞，直到其他线程完成各自
                     * 的任务。
                     *
                     * 其他N 个线程必须引用闭锁对象，因为他们需要通知CountDownLatch对象，他们已经完成了各自
                     * 的任务。这种通知机制是通过 CountDownLatch.countDown()方法来完成的；每调用一次这个方法，
                     * 在构造函数中初始化的count值就减1。所以当N个线程都调 用了这个方法，count的值等于0，
                     * 然后主线程就能通过await()方法，恢复执行自己的任务。
                     */
                    countDownLatch.countDown();
                }
            }
    }
}