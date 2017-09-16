package com.fy.controller;

import com.fy.pojo.HouseInfo;
import javafx.beans.binding.IntegerBinding;
import org.apache.solr.common.SolrDocument;
import com.fy.service.HouseInfoService;
import org.apache.solr.common.SolrDocumentList;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/9/13.
 */
@Controller
public class HouseInfoController {

    @Autowired
    private HouseInfoService houseInfoService;
   /* @RequestMapping("houseFindAll")
    public String findAll(Model model){
        List<HouseInfo> houseList= houseInfoService.findAll();
        model.addAttribute("houseList",houseList);
        return "";
    }*/
    /**
     * 根据关键字查询所有房屋信息
     * @return
     * @throws ParseException
     */
    @RequestMapping("/query")
    public String jddnproductquer(HouseInfo houseInfo,Model model) throws ParseException{
        SolrDocumentList solrdocument=houseInfoService.searchproduct(houseInfo);
        List<HouseInfo> houseInfo1list=new ArrayList<HouseInfo>();
        for(SolrDocument doc :solrdocument){
            HouseInfo houseInfo1=new HouseInfo();

            houseInfo1.setId(doc.get("id")==null?"":doc.get("id").toString());//主键
            houseInfo1.setvHouseName(doc.get("v_house_name")==null?"":doc.get("v_house_name").toString());//房屋名称
            houseInfo1.setvAddress(doc.get("v_address")==null?"":doc.get("v_address").toString());//房屋地址
            houseInfo1.setvLatlngJ(doc.get("v_latlng_j")==null?"":doc.get("v_latlng_j").toString());//经度
            houseInfo1.setvLatlngV(doc.get("v_latlng_v")==null?"":doc.get("v_latlng_v").toString());//维度
            houseInfo1.setvVillage(doc.get("v_village")==null?"":doc.get("v_village").toString());//小区名称
            houseInfo1.setvType(doc.get("v_type")==null?"":doc.get("v_type").toString());//户型
            houseInfo1.setiMaxnum(doc.get("i_maxnum")==null?0:(Integer)Integer.parseInt(doc.get("i_maxnum").toString()));// 可住人数
            houseInfo1.setiResidenu(doc.get("i_residenu")==null?0:(Integer)Integer.parseInt(doc.get("i_residenu").toString()));//已住人数
            houseInfo1.setvOrient(doc.get("v_orient")==null?"":doc.get("v_orient").toString());// 朝向
            houseInfo1.setiFloor(doc.get("i_floor")==null?0:(Integer)Integer.parseInt(doc.get("i_floor").toString()));//楼层
            houseInfo1.setvArea(doc.get("v_area")==null?"":doc.get("v_area").toString());//面积
            houseInfo1.setvVarchar(doc.get("v_varchar")==null?"":doc.get("v_varchar").toString());//配套信息
            houseInfo1.setvPublisher(doc.get("v_publisher")==null?"":doc.get("v_publisher").toString());//发布人
            houseInfo1.setvStatus(doc.get("v_status")==null?"":doc.get("v_status").toString());//状态
            houseInfo1.setvImg(doc.get("v_img")==null?"":doc.get("v_img").toString());//图片路径
            houseInfo1.setvMonmay(doc.get("v_monmay")==null?"":doc.get("v_monmay").toString());//租金
          /*  if(doc.get("d_publishtime")!=null){
                houseInfo1.setdPublishtime(Date(doc.get("d_publishtime").toString()));
            }
            houseInfo1.setdPublishtime(doc.get("d_publishtime")==null?n: Date.parse(doc.get("d_publishtime").toString()));//发布时间*/
          /*  houseInfo1.((Number) (doc.get("n_area")==null?"":Integer.valueOf((String) doc.get("n_area"))));
            houseInfo1.setvFloor(doc.get("v_floor")==null?"":doc.get("v_floor").toString());
            houseInfo1.setnMonthlyRent((Number) (doc.get("n_monthly_rent")==null?"":Integer.valueOf((String) doc.get("n_monthly_rent"))));
            houseInfo1.setvInageAddress(doc.get("v_inage_address")==null?"":doc.get("v_inage_address").toString());
            houseInfo1.setvCellPhoneNuber(doc.get("v_cell_phone_nuber")==null?"":doc.get("v_cell_phone_nuber").toString());
            homeData.setvHouseNature(doc.get("v_house_nature")==null?"":doc.get("v_house_nature").toString());
            homeData.setvHouseType(doc.get("v_house_type")==null?"":doc.get("v_house_type").toString());
            homeData.setvStreetDetails(doc.get("v_street_details")==null?"":doc.get("v_street_details").toString());
            homeData.setvHouseContition(doc.get("v_house_contition")==null?"":doc.get("v_house_contition").toString());
            homeData.setvOrientation(doc.get("v_orientation")==null?"":doc.get("v_orientation").toString());
            homeData.setvLandlord(doc.get("v_landlord")==null?"":doc.get("v_landlord").toString());
            homeData.setdReleaseDate((doc.get("d_release_date")==null?"":doc.get("d_release_date").toString()));*/

            //jd.setHomeAddress(doc.get("link").toString());
            houseInfo1list.add(houseInfo1);
            //System.out.println("id:"+doc.get("id")+"title:"+doc.get("title")+"link:"+doc.get("link")+"price:"+doc.get("price"));
        }
        model.addAttribute("size",houseInfo1list.size());
        model.addAttribute("houseInfo1list",houseInfo1list);
        //System.out.println("执行到这了2");
        return "/sysadmin/main";
    }


}
