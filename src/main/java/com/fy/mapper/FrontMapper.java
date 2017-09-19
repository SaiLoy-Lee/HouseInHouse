package com.fy.mapper;

import com.fy.pojo.*;
import com.fy.pojo.HouseInfo;

/**
 * Created by Administrator on 2017/9/18.
 */
public interface FrontMapper {

    public HouseInfo findHouseInfoById(String hhHouseId);
}
