package com.fy.pojo;

import java.util.Date;

/**
 * Created by Administrator on 2017/9/13.
 */
public class Dept extends BaseEntity{

    private String hhDeptId;
    private String hhDeptParentId;
    private String hhDeptCourse;
    private String hhDeptNum;
    private String hhDeptRoomnum;
    private Date hhDeptStarttime;
    private Date hhDeptStoptime;
    private String hhDeptStatus;

    public String getHhDeptCourse() {
        return hhDeptCourse;
    }

    public void setHhDeptCourse(String hhDeptCourse) {
        this.hhDeptCourse = hhDeptCourse;
    }

    public String getHhDeptId() {
        return hhDeptId;
    }

    public void setHhDeptId(String hhDeptId) {
        this.hhDeptId = hhDeptId;
    }

    public String getHhDeptNum() {
        return hhDeptNum;
    }

    public void setHhDeptNum(String hhDeptNum) {
        this.hhDeptNum = hhDeptNum;
    }

    public String getHhDeptParentId() {
        return hhDeptParentId;
    }

    public void setHhDeptParentId(String hhDeptParentId) {
        this.hhDeptParentId = hhDeptParentId;
    }

    public String getHhDeptRoomnum() {
        return hhDeptRoomnum;
    }

    public void setHhDeptRoomnum(String hhDeptRoomnum) {
        this.hhDeptRoomnum = hhDeptRoomnum;
    }

    public Date getHhDeptStarttime() {
        return hhDeptStarttime;
    }

    public void setHhDeptStarttime(Date hhDeptStarttime) {
        this.hhDeptStarttime = hhDeptStarttime;
    }

    public String getHhDeptStatus() {
        return hhDeptStatus;
    }

    public void setHhDeptStatus(String hhDeptStatus) {
        this.hhDeptStatus = hhDeptStatus;
    }

    public Date getHhDeptStoptime() {
        return hhDeptStoptime;
    }

    public void setHhDeptStoptime(Date hhDeptStoptime) {
        this.hhDeptStoptime = hhDeptStoptime;
    }


}
