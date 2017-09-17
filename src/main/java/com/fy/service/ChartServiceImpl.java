package com.fy.service;

import com.fy.mapper.ChartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Administrator on 2017/9/15.
 */
@Service
public class ChartServiceImpl implements ChartService {

    @Autowired
    private ChartMapper chartMapper;

    @Override
    public Integer[] findDayHouseNum(Date date1, Date date2) {
        return chartMapper.findDayHouseNum(date1,date2);
    }

    @Override
    public Integer[] findDayOrderNum(Date date1,Date date2) {
        return chartMapper.findDayOrderNum(date1,date2);
    }

    @Override
    public Integer[] findHouseRents(Date date1, Date date2) {
        return chartMapper.findHouseRents(date1,date2);
    }

    @Override
    public Integer[] findOrderRents(Date date1, Date date2) {
        return chartMapper.findOrderRents(date1,date2);
    }

    @Override
    public Integer[] findAdminDayNum(Date date1, Date date2) {
        return chartMapper.findAdminDayNum(date1, date2);
    }

    @Override
    public Integer[] findUserDayNum(Date date1, Date date2) {
        return chartMapper.findUserDayNum(date1, date2);
    }
}
