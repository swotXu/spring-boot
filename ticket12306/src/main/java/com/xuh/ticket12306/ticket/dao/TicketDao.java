package com.xuh.ticket12306.ticket.dao;

import com.xuh.ticket12306.ticket.pojo.TicketEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TicketDao {
    /**
     * 插入数据
     * @param te
     * @return
     */
    @Insert("INSERT INTO TICKET(ticketname, ticketmoney, ticketbit, isuse, version) " +
            "VALUES(#{ticketname},#{ticketmoney},#{ticketbit},#{isuse},#{version})")
    int insert(TicketEntity te);
    /**
     * 查询所有余票
     * @return
     */
    @Select("SELECT * FROM TICKET WHERE VERSION = 0 AND ISUSE = 0")
    List<TicketEntity> queryAllTicket();

    /**
     * 更新票据版本号 -- 抢票
     * version=1 代表已消费
     * @param te
     * @return
     */
    @Insert("UPDATE TICKET SET VERSION = #{version} + 1 WHERE TICKETID = #{ticketid} AND VERSION = 0 AND ISUSE = 0")
    int updateTicketVersion(TicketEntity te);

    /**
     * 抢票成功
     * @param te
     * @return
     */
    @Insert("UPDATE TICKET SET ISUSE = 1 WHERE TICKETID = #{ticketid} AND VERSION = 1")
    int updateTicketIsuse(TicketEntity te);
}
