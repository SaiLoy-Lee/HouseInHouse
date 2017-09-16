package com.fy.pojo;

import java.util.Date;

/**
 * Created by ryp on 2017/9/14.
 * 短信表
 */

public class SMessage {

    //ID
    private String hhSmessageId;
    //订单ID
    private String hhSmessageOrdersId;
    //用户ID
    private String hhSmessageUserID;
    //收件人
    private String hhSmessageRecipients;
    //手机号
    private String hhSmessageCell;
    //发送内容
    private String hhSmessageContent;
    //是否成功
    private String hhSmessageIsOk;
    //创建人
    private String createBy;
    //创建班级
    private String createDept;
    //创建时间
    private Date createTime;
    //修改人
    private String updateBy;
    //修改时间
    private Date updateTime;
    //短信类型
    private String hhSmessageType;


    public String getHhSmessageId() {
        return hhSmessageId;
    }

    public void setHhSmessageId(String hhSmessageId) {
        this.hhSmessageId = hhSmessageId;
    }

    public String getHhSmessageOrdersId() {
        return hhSmessageOrdersId;
    }

    public void setHhSmessageOrdersId(String hhSmessageOrdersId) {
        this.hhSmessageOrdersId = hhSmessageOrdersId;
    }

    public String getHhSmessageUserID() {
        return hhSmessageUserID;
    }

    public void setHhSmessageUserID(String hhSmessageUserID) {
        this.hhSmessageUserID = hhSmessageUserID;
    }

    public String getHhSmessageRecipients() {
        return hhSmessageRecipients;
    }

    public void setHhSmessageRecipients(String hhSmessageRecipients) {
        this.hhSmessageRecipients = hhSmessageRecipients;
    }

    public String getHhSmessageCell() {
        return hhSmessageCell;
    }

    public void setHhSmessageCell(String hhSmessageCell) {
        this.hhSmessageCell = hhSmessageCell;
    }

    public String getHhSmessageContent() {
        return hhSmessageContent;
    }

    public void setHhSmessageContent(String hhSmessageContent) {
        this.hhSmessageContent = hhSmessageContent;
    }

    public String getHhSmessageIsOk() {
        return hhSmessageIsOk;
    }

    public void setHhSmessageIsOk(String hhSmessageIsOk) {
        this.hhSmessageIsOk = hhSmessageIsOk;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateDept() {
        return createDept;
    }

    public void setCreateDept(String createDept) {
        this.createDept = createDept;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getHhSmessageType() {
        return hhSmessageType;
    }

    public void setHhSmessageType(String hhSmessageType) {
        this.hhSmessageType = hhSmessageType;
    }

    @Override
    public String toString() {
        return "SMessage{" +
                "hhSmessageId='" + hhSmessageId + '\'' +
                ", hhSmessageOrdersId='" + hhSmessageOrdersId + '\'' +
                ", hhSmessageUserID='" + hhSmessageUserID + '\'' +
                ", hhSmessageRecipients='" + hhSmessageRecipients + '\'' +
                ", hhSmessageCell='" + hhSmessageCell + '\'' +
                ", hhSmessageContent='" + hhSmessageContent + '\'' +
                ", hhSmessageIsOk='" + hhSmessageIsOk + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createDept='" + createDept + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", hhSmessageType='" + hhSmessageType + '\'' +
                '}';
    }
}
