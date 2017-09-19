package com.fy.mapper;

import com.fy.pojo.HouseInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/9/13.
 */
public interface HouseInfoMapper {
    public List<HouseInfo> findAll();

    public List<HouseInfo> findById(String houseInfoId);

    public void save(HouseInfo houseInfo);
    public void deletehhHouseId(@Param("hhHouseIds") String[] hhHouseIds , @Param("hhHouseIdStatus") int hhHouseIdStatus);

    public void toStart(@Param("hhHouseIds") String[] hhHouseIds, @Param("hhHouseIdStatus") int hhHouseIdStatus);

    public void toStop(@Param("hhHouseIds")String[] hhHouseIds,@Param("hhHouseIdStatus") int hhHouseIdStatus);
}
