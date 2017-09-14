package com.fy.pojo;

import java.util.Date;

/**
 * Created by Administrator on 2017/9/13.
 */
public class HouseInfo {
  private  String id;          //  主键
  private  String vHouseName;          //  房屋名称
  private  String vAddress;         //  房屋地址
  private  String vLatlngJ;         //  经度
  private  String vLatlngV;         //  维度
  private  String vVillage;         //  小区名称
  private  String vType;         //  户型
  private  Integer iMaxnum;         //  可住人数
  private  Integer iResidenu;         //  已住人数
  private  String vOrient;         //  朝向
  private  Integer iFloor;         //  楼层
  private  String vArea;         //  面积
  private  String vVarchar;         //  配套信息
  private  String vPublisher;         //  发布人
  private  String vStatus;         //  状态
  private  String vImg;        //  图片路径
  private  Date dPublishtime;         //  发布时间
  private  String vDescriptime;         //  房屋描述
  private  String vCreateBy;         //  创建人
  private  Date dCreateTime;        //  创建时间
  private  String vCreateDept;         //  创建部门
  private  String vUpdateBy;         //  修改人
  private  Date dUpdateTime;        //  修改时间
  private  String vTelephone;         //  联系方式
    private String vMonmay;           //月租金

    public String getvMonmay() {
        return vMonmay;
    }

    public void setvMonmay(String vMonmay) {
        this.vMonmay = vMonmay;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getvHouseName() {
        return vHouseName;
    }

    public void setvHouseName(String vHouseName) {
        this.vHouseName = vHouseName;
    }

    public String getvAddress() {
        return vAddress;
    }

    public void setvAddress(String vAddress) {
        this.vAddress = vAddress;
    }

    public String getvLatlngJ() {
        return vLatlngJ;
    }

    public void setvLatlngJ(String vLatlngJ) {
        this.vLatlngJ = vLatlngJ;
    }

    public String getvLatlngV() {
        return vLatlngV;
    }

    public void setvLatlngV(String vLatlngV) {
        this.vLatlngV = vLatlngV;
    }

    public String getvVillage() {
        return vVillage;
    }

    public void setvVillage(String vVillage) {
        this.vVillage = vVillage;
    }

    public String getvType() {
        return vType;
    }

    public void setvType(String vType) {
        this.vType = vType;
    }

    public Integer getiMaxnum() {
        return iMaxnum;
    }

    public void setiMaxnum(Integer iMaxnum) {
        this.iMaxnum = iMaxnum;
    }

    public Integer getiResidenu() {
        return iResidenu;
    }

    public void setiResidenu(Integer iResidenu) {
        this.iResidenu = iResidenu;
    }

    public String getvOrient() {
        return vOrient;
    }

    public void setvOrient(String vOrient) {
        this.vOrient = vOrient;
    }

    public Integer getiFloor() {
        return iFloor;
    }

    public void setiFloor(Integer iFloor) {
        this.iFloor = iFloor;
    }

    public String getvArea() {
        return vArea;
    }

    public void setvArea(String vArea) {
        this.vArea = vArea;
    }

    public String getvVarchar() {
        return vVarchar;
    }

    public void setvVarchar(String vVarchar) {
        this.vVarchar = vVarchar;
    }

    public String getvPublisher() {
        return vPublisher;
    }

    public void setvPublisher(String vPublisher) {
        this.vPublisher = vPublisher;
    }

    public String getvStatus() {
        return vStatus;
    }

    public void setvStatus(String vStatus) {
        this.vStatus = vStatus;
    }

    public String getvImg() {
        return vImg;
    }

    public void setvImg(String vImg) {
        this.vImg = vImg;
    }

    public Date getdPublishtime() {
        return dPublishtime;
    }

    public void setdPublishtime(Date dPublishtime) {
        this.dPublishtime = dPublishtime;
    }

    public String getvDescriptime() {
        return vDescriptime;
    }

    public void setvDescriptime(String vDescriptime) {
        this.vDescriptime = vDescriptime;
    }

    public String getvCreateBy() {
        return vCreateBy;
    }

    public void setvCreateBy(String vCreateBy) {
        this.vCreateBy = vCreateBy;
    }

    public Date getdCreateTime() {
        return dCreateTime;
    }

    public void setdCreateTime(Date dCreateTime) {
        this.dCreateTime = dCreateTime;
    }

    public String getvCreateDept() {
        return vCreateDept;
    }

    public void setvCreateDept(String vCreateDept) {
        this.vCreateDept = vCreateDept;
    }

    public String getvUpdateBy() {
        return vUpdateBy;
    }

    public void setvUpdateBy(String vUpdateBy) {
        this.vUpdateBy = vUpdateBy;
    }

    public Date getdUpdateTime() {
        return dUpdateTime;
    }

    public void setdUpdateTime(Date dUpdateTime) {
        this.dUpdateTime = dUpdateTime;
    }

    public String getvTelephone() {
        return vTelephone;
    }

    public void setvTelephone(String vTelephone) {
        this.vTelephone = vTelephone;
    }
}
