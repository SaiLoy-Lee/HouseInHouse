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

    public String gethhRoleId() {
        return hhRoleId;
    }

    public void sethhRoleId(String hhRoleId) {
        this.hhRoleId = hhRoleId;
    }

    public String gethhRoleName() {
        return hhRoleName;
    }

    public void sethhRoleName(String hhRoleName) {
        this.hhRoleName = hhRoleName;
    }

    public String gethhRoleRemarks() {
        return hhRoleRemarks;
    }

    public void sethhRoleRemarks(String hhRoleRemarks) {
        this.hhRoleRemarks = hhRoleRemarks;
    }

    public Integer gethhRoleOrderNo() {
        return hhRoleOrderNo;
    }

    public void sethhRoleOrderNo(Integer hhRoleOrderNo) {
        this.hhRoleOrderNo = hhRoleOrderNo;
    }

    public Integer gethhRoleStatus() {
        return hhRoleStatus;
    }

    public void sethhRoleStatus(Integer hhRoleStatus) {
        this.hhRoleStatus = hhRoleStatus;
    }
}
