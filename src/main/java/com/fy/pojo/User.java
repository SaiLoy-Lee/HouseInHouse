package com.fy.pojo;

/**
 * Created by Administrator on 2017/9/13.
 */
public class User extends BaseEntity{
    private Dept dept;
    private String hhUserUsername;
    private String hhUserPassword;
    private String hhUserId;
    private String hhUserName;
    private String hhUserSex;
    private Integer hhUserAge;
    private String hhUserCardid;
    private String hhUserTel;
    private String hhUserStatus;

    private Boolean checked;

    public String getId(){
        return  hhUserId;
     }
    public String getName(){
        return hhUserName;
    }
    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public Integer getHhUserAge() {
        return hhUserAge;
    }

    public void setHhUserAge(Integer hhUserAge) {
        this.hhUserAge = hhUserAge;
    }

    public String getHhUserCardid() {
        return hhUserCardid;
    }

    public void setHhUserCardid(String hhUserCardid) {
        this.hhUserCardid = hhUserCardid;
    }

    public String getHhUserId() {
        return hhUserId;
    }

    public void setHhUserId(String hhUserId) {
        this.hhUserId = hhUserId;
    }

    public String getHhUserName() {
        return hhUserName;
    }

    public void setHhUserName(String hhUserName) {
        this.hhUserName = hhUserName;
    }

    public String getHhUserPassword() {
        return hhUserPassword;
    }

    public void setHhUserPassword(String hhUserPassword) {
        this.hhUserPassword = hhUserPassword;
    }

    public String getHhUserSex() {
        return hhUserSex;
    }

    public void setHhUserSex(String hhUserSex) {
        this.hhUserSex = hhUserSex;
    }

    public String getHhUserStatus() {
        return hhUserStatus;
    }

    public void setHhUserStatus(String hhUserStatus) {
        this.hhUserStatus = hhUserStatus;
    }

    public String getHhUserTel() {
        return hhUserTel;
    }

    public void setHhUserTel(String hhUserTel) {
        this.hhUserTel = hhUserTel;
    }

    public String getHhUserUsername() {
        return hhUserUsername;
    }

    public void setHhUserUsername(String hhUserUsername) {
        this.hhUserUsername = hhUserUsername;
    }
}
