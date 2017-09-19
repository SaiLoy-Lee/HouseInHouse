package com.fy.service;


import com.aliyun.oss.OSSClient;
import com.fy.mapper.HouseInfoMapper;
import com.fy.pojo.HouseInfo;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/9/13.
 */
@Service("HouseInfoService")
public class HouseInfoServiceImpl implements HouseInfoService{

    @Autowired
    private  HouseInfoMapper houseInfoMapper;


    public List<HouseInfo> findAll() {
        return houseInfoMapper.findAll();
    }

    @Override
    public List<HouseInfo> findById(String HouseInfoId) {
        return houseInfoMapper.findById(HouseInfoId);
    }

    @Override
    public void save(HouseInfo houseInfo) {
        String uuId=UUID.randomUUID().toString();
        houseInfo.setHhHouseId(uuId);
        houseInfo.setCreateTime(new Date());
        houseInfo.setUpdateTime(houseInfo.getCreateTime());
        houseInfo.setHhHousePublishtime(houseInfo.getCreateTime());

      /*  *//**
         *   上传图片到阿里云OSS
         *//*
        // endpoint以杭州为例，其它region请按实际情况填写
        String endpoint = "http://oss-cn-qingdao.aliyuncs.com";
// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建
        String accessKeyId = "LTAISHKTDwPsVrov";
        String accessKeySecret = "z777ZltUpK5N0cbXGM6nj0gRfqMVtG";
// 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
// 上传文件

        ossClient.putObject("weizhen520", uuId, new File(houseInfo.getHhHouseImg()));


        System.out.print(ossClient.getObject("weizhen520",uuId).toString());
// 关闭client
        ossClient.shutdown();*/
        houseInfoMapper.save(houseInfo);
    }

    @Override
    public SolrDocumentList searchproduct(HouseInfo houseInfo) {
        SolrServer solrServer = new HttpSolrServer("http://10.8.37.210:8083/solr/house_info");


//        SolrServer solrServer = new HttpSolrServer("http://DADI:8083/solr/house_info");
        SolrQuery query=new SolrQuery();
        if (!"".equals(houseInfo.getHhHouseAddress())||houseInfo.getHhHouseAddress()!=null){
            query.setQuery("HH_HOUSE_ADDRESS:"+houseInfo.getHhHouseAddress()+"AND HH_HOUSE_STATUS:  0");  //房屋地址
        }else if (!"".equals(houseInfo.getHhHouseVillage())||houseInfo.getHhHouseVillage()!=null){
            query.setQuery("HH_HOUSE_VILLAGE:"+houseInfo.getHhHouseVillage()+"AND HH_HOUSE_STATUS:  0");//街道名称
        }else{
            query.setQuery("HH_HOUSE_TELEPHONE:1 AND HH_HOUSE_STATUS:  0");//手机号带1的
        }
        query.setHighlight(true);
        query.setHighlightSimplePre("<font color='red'>");
        query.setHighlightSimplePost("</font>");
		/*query.addSort("id",ORDER.asc);
		query.setStart(0);
		query.setRows(100);
		query.set("q","title:"+keyword);
		query.setHighlight(true);
		query.addHighlightField("title");
		query.setHighlightSimplePre("<font color='red'>");
		query.setHighlightSimplePost("</font>"); */
        SolrDocumentList solrDocumentList=null;
        try {
            QueryResponse response = solrServer.query(query);
            solrDocumentList = response.getResults();
			 /*for (SolrDocument doc : solrDocumentList) {
				 System.out.println(doc.get("id"));
			 }*/
        } catch (SolrServerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return solrDocumentList;
    }

    @Override
    public SolrDocumentList searchproduct3() {
        SolrServer solrServer = new HttpSolrServer("http://10.8.37.210:8083/solr/house_info");
//        SolrServer solrServer = new HttpSolrServer("http://DADI:8083/solr/house_info");
        SolrQuery query=new SolrQuery();
        query.setQuery("*:* AND HH_HOUSE_STATUS:  0");  //房屋地址
        //query.setQuery("hhHouseVillage:1");//手机号带1的
        query.setSort("HH_HOUSE_PUBLISHTIME", SolrQuery.ORDER.desc);
        query.setHighlight(true);
        query.setHighlightSimplePre("<font color='red'>");
        query.setHighlightSimplePost("</font>");
        query.setRows(3);
		/*query.addSort("id",ORDER.asc);
		query.setStart(0);
		query.setRows(100);
		query.set("q","title:"+keyword);
		query.setHighlight(true);
		query.addHighlightField("title");
		query.setHighlightSimplePre("<font color='red'>");
		query.setHighlightSimplePost("</font>"); */
        SolrDocumentList solrDocumentList=null;
        try {
            QueryResponse response = solrServer.query(query);
            solrDocumentList = response.getResults();
			 /*for (SolrDocument doc : solrDocumentList) {
				 System.out.println(doc.get("id"));
			 }*/
        } catch (SolrServerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return solrDocumentList;
    }

    @Override
    public SolrDocumentList searchAll() {
        SolrServer solrServer = new HttpSolrServer("http://10.8.37.210:8083/solr/house_info");
//        SolrServer solrServer = new HttpSolrServer("http://DADI:8083/solr/house_info");
        SolrQuery query=new SolrQuery();
        query.setQuery("*:* AND HH_HOUSE_STATUS:  0");  //房屋地址
        //query.setQuery("hhHouseVillage:1");//手机号带1的
        query.setHighlight(true);
        query.setStart(0);
        query.setRows(Integer.MAX_VALUE);
        query.setHighlightSimplePre("<font color='red'>");
        query.setHighlightSimplePost("</font>");
		/*query.addSort("id",ORDER.asc);
		query.setStart(0);
		query.setRows(100);
		query.set("q","title:"+keyword);
		query.setHighlight(true);
		query.addHighlightField("title");
		query.setHighlightSimplePre("<font color='red'>");
		query.setHighlightSimplePost("</font>"); */
        SolrDocumentList solrDocumentList=null;
        try {
            QueryResponse response = solrServer.query(query);
            solrDocumentList = response.getResults();
			 /*for (SolrDocument doc : solrDocumentList) {
				 System.out.println(doc.get("id"));
			 }*/
        } catch (SolrServerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return solrDocumentList;
    }


    @Override
    public void deletehhHouseId(String[] hhHouseIds, int hhHouseIdStatus) {
        houseInfoMapper.deletehhHouseId(hhHouseIds,hhHouseIdStatus);
    }

    @Override
    public void toStart(String[] hhHouseIds, int hhHouseIdStatus) {
        houseInfoMapper.toStart(hhHouseIds,hhHouseIdStatus);
    }

    @Override
    public void toStop(String[] hhHouseIds, int hhHouseIdStatus) {
        houseInfoMapper.toStop(hhHouseIds,hhHouseIdStatus);

    }
}
