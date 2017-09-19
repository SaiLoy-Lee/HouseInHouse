package com.fy.service;

import com.fy.mapper.FrontMapper;
import com.fy.pojo.HouseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/9/18.
 */
@Service
public class FrontServiceImpl implements FrontService{

    @Autowired
    public FrontMapper frontMapper;

    @Override
    public HouseInfo findHouseInfoById(String hhHouseId) {

        return frontMapper.findHouseInfoById(hhHouseId);
    }
}
