package com.fy.tools;

import com.fy.pojo.Order;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/9/17.
 */
public class POIUtils {
    public static List<Order>[] split(List<Order> total){
        List<Order>[] lists=new ArrayList[5];
        for (int i=0;i<lists.length;i++){
            lists[i]=new ArrayList<Order>();
        }

        for (Order order: total) {
            Integer status = order.getHhOrdersStatus();
            switch (status) {
                case 1:
                    lists[0].add(order);
                    break;
                case 2:
                    lists[1].add(order);
                    break;
                case 3:
                    lists[2].add(order);
                    break;
                case 4:
                    lists[3].add(order);
                    break;
                case 5:
                    lists[4].add(order);
                    break;
                default:
                    break;
            }
        }

        return lists;
    }

    public static String[][]  toArray(List<Order> data){
        String[][] strArr=new String[data.size()][7];

       /* strArr[0][0]="0";
        strArr[0][1]="房中房订单报表";
        strArr[0][2]="Tarena";
        strArr[0][3]="5";
        strArr[0][4]="2017-9-12";
        strArr[0][5]="2017-9-17";
        strArr[0][6]="   ";*/



        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < data.size(); i++) {


            Order order=data.get(i);

            Date in=order.getHhOrdersIntime();
            Date out=order.getHhOrdersOuttime();

            int day=0;

            if(out==null){
                out= new Date();
            }


            long ms=out.getTime()-in.getTime();
            day=(int) (ms/(1000*60*60*24));


            strArr[i][0]=i+1+"";
            strArr[i][1]=order.getHouseInfo().getHhHouseAddress();
            strArr[i][2]=order.getUser().getHhUserName();
            strArr[i][3]=day+"";
            strArr[i][4]=sdf.format(in);
            strArr[i][5]=sdf.format(out);
            strArr[i][6]=order.getHhOrdersPrice()+"";


        }
        return strArr;
    }
}
