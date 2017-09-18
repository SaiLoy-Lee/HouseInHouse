package com.fy.pojo;

import java.util.Date;

/**
 * Created by Administrator on 2017/9/13.
 */
public class Order extends BaseEntity{
    private String hhOrdersId;//订单D
    private User user;//用户ID

    private HouseInfo houseInfo;//房间ID
    private Integer hhOrdersStatus;//订单状态
    private Date hhOrdersIntime;//入住日期
    private Date hhOrdersOuttime;//退租日期
    private String hhOrdersRemarks;//备注
    private double hhOrdersPrice;




    public String getHhOrdersId() {
        return hhOrdersId;
    }

    public void setHhOrdersId(String hhOrdersId) {
        this.hhOrdersId = hhOrdersId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public HouseInfo getHouseInfo() {
        return houseInfo;
    }

    public void setHouseInfo(HouseInfo houseInfo) {
        this.houseInfo = houseInfo;
    }

    public Integer getHhOrdersStatus() {
        return hhOrdersStatus;
    }

    public void setHhOrdersStatus(Integer hhOrdersStatus) {
        this.hhOrdersStatus = hhOrdersStatus;
    }

    public Date getHhOrdersIntime() {
        return hhOrdersIntime;
    }

    public void setHhOrdersIntime(Date hhOrdersIntime) {
        this.hhOrdersIntime = hhOrdersIntime;
    }

    public Date getHhOrdersOuttime() {
        return hhOrdersOuttime;
    }

    public void setHhOrdersOuttime(Date hhOrdersOuttime) {
        this.hhOrdersOuttime = hhOrdersOuttime;
    }

    public String getHhOrdersRemarks() {
        return hhOrdersRemarks;
    }

    public void setHhOrdersRemarks(String hhOrdersRemarks) {
        this.hhOrdersRemarks = hhOrdersRemarks;
    }

    public double getHhOrdersPrice() {
        return hhOrdersPrice;
    }

    public void setHhOrdersPrice(double hhOrdersPrice) {
        this.hhOrdersPrice = hhOrdersPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "hhOrdersId='" + hhOrdersId + '\'' +
                ", user=" + user +
                ", houseInfo=" + houseInfo +
                ", hhOrdersStatus=" + hhOrdersStatus +
                ", hhOrdersIntime=" + hhOrdersIntime +
                ", hhOrdersOuttime=" + hhOrdersOuttime +
                ", hhOrdersRemarks='" + hhOrdersRemarks + '\'' +
                ", hhOrdersPrice=" + hhOrdersPrice +
                '}';
    }
}
