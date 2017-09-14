package com.fy.pojo;

/**
 * Created by Administrator on 17-9-14.
 */
public class Role extends BaseEntity {
    private String hhroleId;
    private String hhroleName;
    private String hhroleRemarks;
    private Integer hhroleorderNo;
    private Integer hhroleStatus;

    public String getHhroleId() {
        return hhroleId;
    }

    public void setHhroleId(String hhroleId) {
        this.hhroleId = hhroleId;
    }

    public String getHhroleName() {
        return hhroleName;
    }

    public void setHhroleName(String hhroleName) {
        this.hhroleName = hhroleName;
    }

    public String getHhroleRemarks() {
        return hhroleRemarks;
    }

    public void setHhroleRemarks(String hhroleRemarks) {
        this.hhroleRemarks = hhroleRemarks;
    }

    public Integer getHhroleorderNo() {
        return hhroleorderNo;
    }

    public void setHhroleorderNo(Integer hhroleorderNo) {
        this.hhroleorderNo = hhroleorderNo;
    }

    public Integer getHhroleStatus() {
        return hhroleStatus;
    }

    public void setHhroleStatus(Integer hhroleStatus) {
        this.hhroleStatus = hhroleStatus;
    }
}
