package com.zxy.test.juc;
//每次写入时，都会在底层复制

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 主要解决的问题:
 */
public class TestCopyOnWriteArrayList {

    public static void main(String[] args) {
        HelloThread helloThread=new HelloThread();

        for(int i=0;i<10;i++){
            new Thread(helloThread).start();
        }
    }

}
class HelloThread implements Runnable{
    //会产生并发修改异常
  //  private static List<String> list= Collections.synchronizedList(new ArrayList<String>());  //作用：把arrayList里面的方法变成同步方法
    //考虑线程安全时可以选择他，如果添加操作多时，每次添加都会进行复制，所以不会产生异常，可是相应的效率会很低。
    //如果迭代操作多，又有并发可以选择它。
    private static CopyOnWriteArrayList<String> list= new CopyOnWriteArrayList<>();  //每次写入时都会复制
    static{
        list.add("AA");
        list.add("BB");
        list.add("CC");
    }

    @Override
    public void run() {
        Iterator<String> it=list.iterator();
        while(it.hasNext()){
            System.out.println(it.next());

            list.add("AA");
        }
    }
}