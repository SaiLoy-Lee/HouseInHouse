package com.fy.service;

import com.fy.pojo.HouseInfo;
import org.apache.solr.common.SolrDocumentList;

import java.util.List;

/**
 * Created by Administrator on 2017/9/13.
 */
public interface HouseInfoService {
    public List<HouseInfo> findAll();
    public List<HouseInfo> findById(String houseInfoId);
    public void save(HouseInfo houseInfo);
    public SolrDocumentList searchproduct(HouseInfo houseInfo);
    public SolrDocumentList searchproduct3();
    public SolrDocumentList searchAll();
    public void deletehhHouseId(String[] hhHouseIds, int hhHouseIdStatus);

    public void toStart(String[] hhHouseIds, int hhHouseIdStatus);

    public void toStop(String[] hhHouseIds, int hhHouseIdStatus);
}
