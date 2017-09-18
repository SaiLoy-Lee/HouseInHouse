package com.fy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fy.pojo.HouseInfo;
import com.fy.pojo.User;
import javafx.beans.binding.IntegerBinding;
import org.apache.http.impl.bootstrap.HttpServer;
import org.apache.solr.common.SolrDocument;
import com.fy.service.HouseInfoService;
import org.apache.solr.common.SolrDocumentList;

import java.io.IOException;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by Administrator on 2017/9/13.
 */
@Controller
@RequestMapping("/house")
public class HouseInfoController {
    @Autowired
    private HouseInfoService houseInfoService;
    @RequestMapping("/houseFindAll")
    public String findAll(Model model){
        List<HouseInfo> houseList= houseInfoService.findAll();
        model.addAttribute("houseList",houseList);
        return "/house/jHouseList";
    }



    @RequestMapping("/save")
    public String saveHouse(HouseInfo houseInfo){
        houseInfoService.save(houseInfo);
        return "redirect:/house/houseFindAll";
    }

    @RequestMapping("/tocreate")
    public String tocreate(Model model, HttpSession httpSession){
        User user=  (User)httpSession.getAttribute("SessionUser");
        model.addAttribute("UserName",user.getHhUserName());
        /*List<HouseInfo> houseList= houseInfoService.findAll();
        model.addAttribute("houseList",houseList);*/
        return "/house/jHouseInfo";
    }
    @RequestMapping("/delete")
    public String deleteRole(@RequestParam(value = "hhHouseId",required = true) String[]hhHouseIds){
       // int hhHouseIdStatus =2;   //假删除
        houseInfoService.deletehhHouseId(hhHouseIds,2);

        return "redirect:/house/houseFindAll";

    }

    @RequestMapping("/start")
    public String Start(@RequestParam(value ="hhHouseId" ,required=true) String[]hhHouseIds){
        //int hhHouseIdStatus =hhHouseIdStatus =0;  //上架
        houseInfoService.toStart(hhHouseIds,0);
        return "redirect:/house/houseFindAll";
    }

    @RequestMapping("/stop")
    public String Stop(@RequestParam(value ="hhHouseId" ,required=true) String[]hhHouseIds){
        //int hhHouseIdStatus =hhHouseIdStatus =1;//下架
        houseInfoService.toStop(hhHouseIds,1);
        return "redirect:/house/houseFindAll";
    }
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

            houseInfo1.setHhHouseId(doc.get("id")==null?"":doc.get("id").toString());//主键   //修改成id才能从solr中获得值
            houseInfo1.setHhHouseName(doc.get("hh_house_name")==null?"":doc.get("hh_house_name").toString());//房屋名称
            houseInfo1.setHhHouseAddress(doc.get("hh_house_address")==null?"":doc.get("hh_house_address").toString());//房屋地址
            houseInfo1.setHhHouseLatlng(doc.get("hh_house_latlng")==null?"":doc.get("hh_house_latlng").toString());//经维度
            //houseInfo1.setvLatlngV(doc.get("v_latlng_v")==null?"":doc.get("v_latlng_v").toString());//维度
            houseInfo1.setHhHouseVillage(doc.get("hh_house_village")==null?"":doc.get("hh_house_village").toString());//小区名称
            houseInfo1.setHhHouseType(doc.get("hh_house_type")==null?"":doc.get("hh_house_type").toString());//户型
            houseInfo1.setHhHouseMaxnum(doc.get("hh_house_maxnum")==null?0:(Integer)Integer.parseInt(doc.get("hh_house_maxnum").toString()));// 可住人数
            houseInfo1.setHhHouseResidenum(doc.get("hh_house_residenum")==null?0:(Integer)Integer.parseInt(doc.get("hh_house_residenum").toString()));//已住人数
            houseInfo1.setHhHouseOrient(doc.get("hh_house_orient")==null?"":doc.get("hh_house_orient").toString());// 朝向
            houseInfo1.setHhHouseFloor(doc.get("hh_house_floor")==null?"":doc.get("hh_house_floor").toString());//楼层
            houseInfo1.setHhHouseArea(doc.get("hh_house_area")==null?"":doc.get("hh_house_area").toString());//面积
            houseInfo1.setHhHouseSupport(doc.get("hh_house_support")==null?"":doc.get("hh_house_support").toString());//配套信息
            houseInfo1.setHhHousePublisher(doc.get("hh_house_publisher")==null?"":doc.get("hh_house_publisher").toString());//发布人
            houseInfo1.setHhHouseStatus(doc.get("hh_house_status")==null?"":doc.get("hh_house_status").toString());//状态
            houseInfo1.setHhHouseImg(doc.get("hh_house_img")==null?"":doc.get("hh_house_img").toString());//图片路径
            houseInfo1.setHhHousePrice(doc.get("hh_house_price")==null?"":doc.get("hh_house_price").toString());//租金
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
    /**
     * 根据关键字查询所有房屋信息
     * @return
     * @throws ParseException
     */
    @RequestMapping("/searchHouseAddress")
    @ResponseBody
    public String jsonquerysolr(HouseInfo houseInfo,Model model,HttpServletResponse response) throws ParseException, IOException {
        //houseInfo.setHhHouseAddress("");
        SolrDocumentList solrdocument=houseInfoService.searchproduct(houseInfo);
        List houseInfo1list=new ArrayList();
        String nameadd="";
        for(SolrDocument doc :solrdocument){
            if(doc.get("HH_HOUSE_ADDRESS")!=null||!"".equals(doc.get("HH_HOUSE_ADDRESS"))){
                nameadd=doc.get("HH_HOUSE_ADDRESS").toString();//房屋地址
            }
            // nameadd=doc.get("HH_HOUSE_ADDRESS")==null?"":doc.get("HH_HOUSE_ADDRESS").toString();//房屋地址
            //houseInfo1.setHhHouseVillage(doc.get("HH_HOUSE_VILLAGE")==null?"":doc.get("HH_HOUSE_VILLAGE").toString());//小区名称
            houseInfo1list.add(nameadd);
            //System.out.println("id:"+doc.get("id")+"title:"+doc.get("title")+"link:"+doc.get("link")+"price:"+doc.get("price"));
        }
        ObjectMapper objectMapper =new ObjectMapper();
        String jsonStr="";
        try {
            jsonStr=objectMapper.writeValueAsString(houseInfo1list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
//        response.setCharacterEncoding("utf-8");
//        response.getWriter().write("美食:"+jsonStr);
        return "{\"美食\":"+jsonStr+"}";
    }
    /**
     * 根据关键字查询所有房屋信息
     * @return
     * @throws ParseException
     */
    @RequestMapping("/searchHouseXQMC")
    @ResponseBody
public String jsonquerysolrXQ(HouseInfo houseInfo,Model model,HttpServletResponse response) throws ParseException, IOException {
        //houseInfo.setHhHouseAddress("");
        SolrDocumentList solrdocument=houseInfoService.searchproduct(houseInfo);
        List houseInfo1list=new ArrayList();
        String nameadd="";
        for(SolrDocument doc :solrdocument){
            if(doc.get("HH_HOUSE_ADDRESS")!=null||!"".equals(doc.get("HH_HOUSE_ADDRESS"))){
                nameadd=doc.get("HH_HOUSE_ADDRESS").toString();//房屋地址
            }
            // nameadd=doc.get("HH_HOUSE_ADDRESS")==null?"":doc.get("HH_HOUSE_ADDRESS").toString();//房屋地址
            //houseInfo1.setHhHouseVillage(doc.get("HH_HOUSE_VILLAGE")==null?"":doc.get("HH_HOUSE_VILLAGE").toString());//小区名称
            houseInfo1list.add(nameadd);
            //System.out.println("id:"+doc.get("id")+"title:"+doc.get("title")+"link:"+doc.get("link")+"price:"+doc.get("price"));
        }
        ObjectMapper objectMapper =new ObjectMapper();
        String jsonStr="";
        try {
            jsonStr=objectMapper.writeValueAsString(houseInfo1list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
//        response.setCharacterEncoding("utf-8");
//        response.getWriter().write("美食:"+jsonStr);
        return "{\"美食\":"+jsonStr+"}";
    }

}
