package com.fy.pojo;

import java.util.Date;

/**
 * Created by ryp on 2017/9/14.
 * 短信表
 */

public class SMessage {



    //ID
    private String sMessageId;
    //订单ID
    private String sMessageOrdersId;
    //用户ID
    private String sMessageUserID;
    //收件人
    private String sMessageRecipients;
    //手机号
    private String sMessageCell;
    //发送内容
    private String sMessageContent;
    //是否成功
    private String sMessageIsOk;
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
    private String sMessageType;




    public String getsMessageOrdersId() {
        return sMessageOrdersId;
    }

    public void setsMessageOrdersId(String sMessageOrdersId) {
        this.sMessageOrdersId = sMessageOrdersId;
    }

    public String getsMessageType() {
        return sMessageType;
    }

    public void setsMessageType(String sMessageType) {
        this.sMessageType = sMessageType;
    }




    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getsMessageUserID() {
        return sMessageUserID;
    }

    public void setsMessageUserID(String sMessageUserID) {
        this.sMessageUserID = sMessageUserID;
    }

    public String getsMessageId() {
        return sMessageId;
    }

    public void setsMessageId(String sMessageId) {
        this.sMessageId = sMessageId;
    }

    public String getsMessageRecipients() {
        return sMessageRecipients;
    }

    public void setsMessageRecipients(String sMessageRecipients) {
        this.sMessageRecipients = sMessageRecipients;
    }

    public String getsMessageCell() {
        return sMessageCell;
    }

    public void setsMessageCell(String sMessageCell) {
        this.sMessageCell = sMessageCell;
    }

    public String getsMessageContent() {
        return sMessageContent;
    }

    public void setsMessageContent(String sMessageContent) {
        this.sMessageContent = sMessageContent;
    }

    public String getsMessageIsOk() {
        return sMessageIsOk;
    }

    public void setsMessageIsOk(String sMessageIsOk) {
        this.sMessageIsOk = sMessageIsOk;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }





}
