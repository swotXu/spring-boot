package com.testwork.demo.tomcat.boi;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class BioHttpServer {
    // 处理请求的线程池  -- 系统公用 tomcat默认 150-200
    public static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = (ThreadPoolExecutor) Executors.newCachedThreadPool();

    static {
        // 每三秒，打印一次服务器线程数量
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("当前活跃线程数：" + THREAD_POOL_EXECUTOR.getActiveCount());
            }
        }, 0, 3000);
    }

    public static void main(String[] args) throws IOException {
        int port = 8085;
        // JAVA API -- 封装jvm方法  --> 封装的操作系统的接口
        ServerSocket serverSocket = new ServerSocket(port); // tomcat 7 绑定端口
        System.out.println(Thread.currentThread().getName() + "启动 " + port);
        while (true){ // boos线程循环获取连接
            Socket accept = serverSocket.accept();
            THREAD_POOL_EXECUTOR.submit(()->{
                while (true){ // work线程循环处理请求
                    byte[] bytes = new byte[1024];
                    accept.getInputStream().read(bytes); // 读取数据，如果一直没数据，会阻塞
                    String request = new String(bytes);

                    // TODO 处理请求
                    System.out.println(request);

                    // 给一个当前时间作为返回值
                    String responseContent = "HTTP/1.1 200 OK\r\nContent-Length: 11\r\n\rHello World";
                    accept.getOutputStream().write(responseContent.getBytes());
                    accept.getOutputStream().flush();
                }
            });
        }
    }
}
