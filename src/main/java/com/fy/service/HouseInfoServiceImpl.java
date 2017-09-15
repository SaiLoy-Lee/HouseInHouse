package com.fy.service;

import com.fy.pojo.HouseInfo;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/9/13.
 */
@Service("HouseInfoService")
public class HouseInfoServiceImpl implements HouseInfoService{
    @Override
    public List<HouseInfo> findAll() {
        return null;
    }

    @Override
    public SolrDocumentList searchproduct(HouseInfo houseInfo) {
        SolrServer solrServer = new HttpSolrServer("http://localhost:8083/solr/house_info");
        SolrQuery query=new SolrQuery();
        query.setQuery("v_house_name:"+houseInfo.getvHouseName());
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
}
