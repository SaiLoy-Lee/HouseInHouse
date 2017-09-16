package com.fy.service;

import java.util.Date;

/**
 * Created by Administrator on 2017/9/15.
 */
public interface ChartService {
    public Integer[] findDayHouseNum(Date date1, Date date2);

    public Integer[] findDayOrderNum(Date date1,Date date2);

    public Integer[] findHouseRents(Date date1,Date date2);

    public Integer[] findOrderRents(Date date1,Date date2);

    public Integer[] findAdminDayNum(Date date1, Date date2);

    public Integer[] findUserDayNum(Date date1, Date date2);
}
