package com.xuh.meituan.ticketTest;

import com.xuh.meituan.MeituanApplication;
import com.xuh.meituan.config.SenderConf;
import com.xuh.meituan.mqtools.TicketSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MeituanApplication.class)
@ContextConfiguration(classes = SenderConf.class)
public class MqTest {

    @Autowired
    TicketSender send;
    int count = 3000;   // 并发线程数
    CountDownLatch cdl = new CountDownLatch(count); // 线程中断起跑线

    @Test
    public void send() {
        for (int i = 0,temp = count>>1; i< temp; i++)
            send.send(SenderConf.TICKET_EXCHANGE, SenderConf.TICKET_ROUTEKEY, "【"+i+"】.....发送消息过来啦....！");
    }

    @Test
    public void fanoutSend() {
        send.send("fanoutExchange","","广播一个消息！！");
    }
    /**
     * 测试美团高并发
     */
    @Test
    public void getCountTickets(){
        for (int i = 0; i < count; i++){
            int name = i;
            new Thread((Runnable) () -> {
                try {
                    cdl.await();
                }catch (Exception e){
                    e.printStackTrace();
                }
                // 发送到消息队列
                send.send(SenderConf.TICKET_EXCHANGE,SenderConf.TICKET_ROUTEKEY,Thread.currentThread().getName()+ name + "：OK");
            }).start();
            cdl.countDown();
        }
        try {
            TimeUnit.MICROSECONDS.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
