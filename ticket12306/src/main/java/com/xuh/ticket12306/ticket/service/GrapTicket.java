package com.xuh.ticket12306.ticket.service;

import com.alibaba.fastjson.JSONObject;
import com.xuh.ticket12306.mqtools.TicketReceive;
import com.xuh.ticket12306.mqtools.TicketSender;
import com.xuh.ticket12306.ticket.pojo.TicketEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

/**
 * 抢票业务
 */
@Component
public class GrapTicket {
    @Autowired
    TicketService ticketService;
    @Autowired
    TicketSender ticketSender;

    /**
     * 抢票方法
     * @return {"respCode":1/2/3}   1-余票不足 2-成功 3-未抢到
     */
    @Transactional
    public String grapTicket(){
        // 检查余票
        List<TicketEntity> list = ticketService.queryAllTicket();
        JSONObject retObj = new JSONObject();
        // 余票不足
        if(list.size() == 0){
            retObj.put(TicketReceive.Return_Code, TicketReceive.Return_Code_None);
            return retObj.toString();
        }
        // 有余票
        int index = new Random().nextInt(list.size());
        TicketEntity ticketEntity = list.get(index);
        // 随机获取一张票，尝试占有, 分布式锁
        int isOk = ticketService.updateTicketVersion(ticketEntity);
        if(isOk == 1){
            // 占有成功,
            ticketService.updateTicketIsuse(ticketEntity);
            retObj.put(TicketReceive.Return_Code, TicketReceive.Return_Code_Success);
            retObj.put("respTicket", JSONObject.toJSONString(ticketEntity));
            return retObj.toString();
        }
        retObj.put(TicketReceive.Return_Code, TicketReceive.Return_Code_Other);
        return retObj.toString();
    }
}
