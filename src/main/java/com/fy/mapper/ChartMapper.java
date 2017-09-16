package com.fy.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * Created by Administrator on 2017/9/15.
 */
public interface ChartMapper {

    public Integer[] findDayHouseNum(@Param("date1") Date date1, @Param("date2") Date date2);

    public Integer[] findDayOrderNum(@Param("date1") Date date1,@Param("date2") Date date2);

    public Integer[] findHouseRents(@Param("date1") Date date1, @Param("date2") Date date2);

    public Integer[] findOrderRents(@Param("date1") Date date1, @Param("date2") Date date2);

    public Integer[] findAdminDayNum(@Param("date1") Date date1, @Param("date2") Date date2);

    public Integer[] findUserDayNum(@Param("date1") Date date1, @Param("date2") Date date2);
}
