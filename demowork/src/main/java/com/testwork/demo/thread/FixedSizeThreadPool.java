package com.testwork.demo.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class FixedSizeThreadPool {
    //1.仓库
    private BlockingQueue<Runnable> blockingQueue;
    //2.线程集合
    private List<Thread> workers;

    //3.具体干活线程
    public static class Worker extends Thread{
        private FixedSizeThreadPool pool;

        public Worker(FixedSizeThreadPool pool) {
            this.pool = pool;
        }
        @Override
        public void run(){
            //开始服务
            while (this.pool.isWorking || this.pool.blockingQueue.size() > 0){
                Runnable task = null;
                try {
                    if (this.pool.isWorking)
                        task = this.pool.blockingQueue.take(); //阻塞式获取
                    else
                        task = this.pool.blockingQueue.poll(); //非阻塞获取
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (task != null) {
                    task.run();
                    System.out.println("线程："+Thread.currentThread().getName());
                }
            }
        }
    }

    //4.初始化线程池
    public FixedSizeThreadPool(int poolSize, int taskSize){
        if(poolSize <=0 || taskSize <=0)
            throw new IllegalArgumentException("非法参数");

        this.blockingQueue = new LinkedBlockingDeque<>(taskSize);
        this.workers = Collections.synchronizedList(new ArrayList<>());

        for (int size = 0; size < poolSize; size++) {
            Worker worker = new Worker(this);
            worker.start();
            workers.add(worker);
        }
    }
    //5.引导客人方法
    public boolean submit(Runnable task){
        if(isWorking)
            return this.blockingQueue.offer(task);
        else
            return false;
    }
    //6.关闭线程
    //a.仓库停止接收任务
    //b.一旦仓库还有任务，继续执行
    //c.去仓库拿任务不应该阻塞
    //d.一旦任务阻塞就中断
    private volatile  boolean isWorking = true;
    public void shutDown(){
        this.isWorking = false;

        for (Thread t : workers) {
            if(Thread.State.BLOCKED.equals(t.getState()))
                t.interrupt();//中断线程
        }
    }

    public static void main(String[] args) {
        FixedSizeThreadPool pool = new FixedSizeThreadPool(3, 6);
        for(int i = 0;i<6;i++){
            int a = i;
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("放入一个线程"+a);
                    try {
                        Thread.sleep(2000L);
                    } catch (InterruptedException e) {
                        System.out.println("------");
                    }
                }
            });
        }
        pool.shutDown();
    }
}
