package com.testwork.demo.tomcat.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class NioHttpServere {
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
    private static ServerSocketChannel serverSocketChannel;

    private static Selector selector;

    public static void main(String[] args) throws IOException {
        int port = 8085;
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false); // 设置为非阻塞
        serverSocketChannel.bind(new InetSocketAddress(port));
        System.out.println(Thread.currentThread().getName() + " NIO启动 " + port);

        // 获取选择器，一个工具  不同操作系统的实现不同，选择需要的网络连接
        selector = Selector.open();

        while (true){ // boss线程轮询seleceor，处理java程序需要处理的网络连接
            // 检查 操作系统底层，如果没有需要的网络连接，等待1s
            selector.select(1000);
            // 结果 -- java程序需要的网络连接
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

        }
    }
}
