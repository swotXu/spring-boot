package com.xuh.ticket12306.mqtools;

import com.alibaba.fastjson.JSONObject;
import com.xuh.ticket12306.config.SenderConf;
import com.xuh.ticket12306.ticket.service.GrapTicket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketReceive {
    private Logger logger = LoggerFactory.getLogger(TicketReceive.class);

    public static String Return_Code = "respCod";
    public static String Return_Code_None = "1";
    public static String Return_Code_Success = "2";
    public static String Return_Code_Other = "3";

    @Autowired
    private GrapTicket grapTicket;
    @Autowired
    private TicketSender ticketSender;

    @RabbitListener(queues = "ticket.queue")
    public void processc(String msg){
        long star = System.currentTimeMillis();
        logger.info(Thread.currentThread().getName()+"从ticket.queue队列中收到消息：" + msg + "====>正在处理美团抢票<====");
        // 内部抢票
        String tickeMsg = grapTicket.grapTicket();
        String logMsg = reGrapTicket(JSONObject.parseObject(tickeMsg));
        // 返回消息
        ticketSender.send(SenderConf.TICKET_EXCHANGE, SenderConf.TICKET_ROUTEKEY, logMsg);
        logger.info(Thread.currentThread().getName()+"结果为:" + logMsg + ",抢票时间：" + (System.currentTimeMillis() - star) + "MS ====>抢票结束<====");
    }

    /**
     * 处理抢票结果
     * @param ticketObj
     * @return
     */
    private String reGrapTicket(JSONObject ticketObj){
        String code = ticketObj.getString(Return_Code);
        if(Return_Code_None.equals(code))
            return "余票不足";
        if(Return_Code_Success.equals(code))
            return "抢票成功," + ticketObj.getString("respTicket");
        // 抢票失败，重新抢票
        String tickeMsg = grapTicket.grapTicket();
        return reGrapTicket(JSONObject.parseObject(tickeMsg));
    }
}
