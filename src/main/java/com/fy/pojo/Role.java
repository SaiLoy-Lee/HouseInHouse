package com.fy.pojo;

/**
 * Created by Administrator on 17-9-14.
 */
public class Role extends BaseEntity {
    private String hhRoleId;
    private String hhRoleName;
    private String hhRoleRemarks;
    private Integer hhRoleOrderNo;
    private Integer hhRoleStatus;
    private Boolean  checked;


    public  String getId(){
        return hhRoleId;
    }
    public  String getName(){
        return hhRoleName;
    }
    public String getHhRoleId() {
        return hhRoleId;
    }

    public void setHhRoleId(String hhRoleId) {
        this.hhRoleId = hhRoleId;
    }

    public String getHhRoleName() {
        return hhRoleName;
    }

    public void setHhRoleName(String hhRoleName) {
        this.hhRoleName = hhRoleName;
    }

    public Integer getHhRoleOrderNo() {
        return hhRoleOrderNo;
    }

    public void setHhRoleOrderNo(Integer hhRoleOrderNo) {
        this.hhRoleOrderNo = hhRoleOrderNo;
    }

    public String getHhRoleRemarks() {
        return hhRoleRemarks;
    }

    public void setHhRoleRemarks(String hhRoleRemarks) {
        this.hhRoleRemarks = hhRoleRemarks;
    }

    public Integer getHhRoleStatus() {
        return hhRoleStatus;
    }

    public void setHhRoleStatus(Integer hhRoleStatus) {
        this.hhRoleStatus = hhRoleStatus;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
