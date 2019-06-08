package com.xuh.ticket12306.ticket.pojo;

public class TicketEntity {

    private Integer ticketid;
    private Integer ticketmoney;
    private Integer ticketbit;
    private Integer isuse;
    private Integer version;
    private String ticketname;

    public Integer getTicketid() {
        return ticketid;
    }

    public void setTicketid(Integer ticketid) {
        this.ticketid = ticketid;
    }

    public Integer getTicketmoney() {
        return ticketmoney;
    }

    public void setTicketmoney(Integer ticketmoney) {
        this.ticketmoney = ticketmoney;
    }

    public Integer getTicketbit() {
        return ticketbit;
    }

    public void setTicketbit(Integer ticketbit) {
        this.ticketbit = ticketbit;
    }

    public Integer getIsuse() {
        return isuse;
    }

    public void setIsuse(Integer isuse) {
        this.isuse = isuse;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getTicketname() {
        return ticketname;
    }

    public void setTicketname(String ticketname) {
        this.ticketname = ticketname;
    }

    @Override
    public String toString() {
        return "TicketEntity{" +
                "ticketid=" + ticketid +
                ", ticketmoney=" + ticketmoney +
                ", ticketbit=" + ticketbit +
                ", isuse=" + isuse +
                ", version=" + version +
                ", ticketname='" + ticketname + '\'' +
                '}';
    }
}
