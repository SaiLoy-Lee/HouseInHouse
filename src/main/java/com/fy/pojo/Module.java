package com.fy.pojo;

/**
 * Created by Administrator on 2017/9/15.
 */
public class Module extends BaseEntity {
    private String hhModuleId;
    private Module parentModule;
    private String hhModuleName;
    private Integer hhModuleCtype;
    private Integer hhModuleState;
    private Integer hhModuleOrderNo;
    private String hhModuleRemarks;
    private Boolean checked;


    public  String getId(){
        return hhModuleId;
    }
    public  String getName(){
        return hhModuleName;
    }

    public String getpId(){
        //获取上级模块的id
        if(parentModule !=null){
            return parentModule.gethhModuleId();
        }
        return "";
    }


    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String gethhModuleId() {
        return hhModuleId;
    }

    public void sethhModuleId(String hhModuleId) {
        this.hhModuleId = hhModuleId;
    }

    public Module getParentModule() {
        return parentModule;
    }

    public void setParentModule(Module parentModule) {
        this.parentModule = parentModule;
    }

    public String gethhModuleName() {
        return hhModuleName;
    }

    public void sethhModuleName(String hhModuleName) {
        this.hhModuleName = hhModuleName;
    }

    public Integer gethhModuleCtype() {
        return hhModuleCtype;
    }

    public void sethhModuleCtype(Integer hhModuleCtype) {
        this.hhModuleCtype = hhModuleCtype;
    }

    public Integer gethhModuleState() {
        return hhModuleState;
    }

    public void sethhModuleState(Integer hhModuleState) {
        this.hhModuleState = hhModuleState;
    }

    public Integer gethhModuleOrderNo() {
        return hhModuleOrderNo;
    }

    public void sethhModuleOrderNo(Integer hhModuleOrderNo) {
        this.hhModuleOrderNo = hhModuleOrderNo;
    }

    public String gethhModuleRemarks() {
        return hhModuleRemarks;
    }

    public void sethhModuleRemarks(String hhModuleRemarks) {
        this.hhModuleRemarks = hhModuleRemarks;
    }
}

