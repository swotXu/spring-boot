package com.xuh.ticket12306.ticketTest;

import com.xuh.ticket12306.ticket.dao.TicketDao;
import com.xuh.ticket12306.ticket.pojo.TicketEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketTest {

    @Autowired
    TicketDao ticketDao;

    @Test
    public void insert(){
        TicketEntity entity = new TicketEntity();
        entity.setIsuse(0);
        entity.setVersion(0);
        entity.setTicketname("G600");
        int[] arr = {40,55,76,93,128};
        for (int i = 1; i<= 1000;i++ ){
            entity.setTicketbit(i);
            entity.setTicketmoney(arr[i%5]);
            int insert = ticketDao.insert(entity);
            System.out.println("=================>> " + insert);
        }
    }
}
