package com.xuh.ticket12306.ticket.service;

import com.xuh.ticket12306.ticket.dao.TicketDao;
import com.xuh.ticket12306.ticket.pojo.TicketEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 票据信息类
 */
@Service
public class TicketService {

    @Autowired
    TicketDao ticketDao;

    public List<TicketEntity> queryAllTicket(){
        return ticketDao.queryAllTicket();
    }
    public int updateTicketVersion(TicketEntity te){
        return ticketDao.updateTicketVersion(te);
    }
    public int updateTicketIsuse(TicketEntity te){
        return ticketDao.updateTicketIsuse(te);
    }
}
