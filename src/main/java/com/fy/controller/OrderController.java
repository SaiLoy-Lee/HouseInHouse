package com.fy.controller;

import com.aliyuncs.exceptions.ClientException;
import com.fy.Exception.MegException;
import com.fy.pojo.HouseInfo;
import com.fy.pojo.Order;
import com.fy.pojo.User;
import com.fy.service.HouseInfoService;
import com.fy.service.OrderService;
import com.fy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/9/13.
 */
@Controller
@RequestMapping("/personal/order")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    HouseInfoService houseInfoService;





    //转向订单确认页面
    @RequestMapping("/toCreateOrder")
    public String toCreateOrder(@RequestParam(required = true) String hhUserId, @RequestParam(required = true) String HouseInfoId, Model model, HttpSession session) {
//        if (session.getAttribute("User") == null) {
//            return "redirect:login";
//        }
        User user = null;
        //user = userService.findUserById(hhUserId);
        HouseInfo houseInfo = null;
        // HouseInfo houseInfo=HouseInfoService.findHouseInfoById(HouseInfoId);

        //生成订单
        Order order = new Order();
        //生成订单号.
        order.setHhOrdersId(UUID.randomUUID().toString());
        order.setUser(user);
        order.setHouseInfo(houseInfo);
        order.setHhOrdersIntime(new Date());
        Calendar calender = Calendar.getInstance();
        calender.setTime(order.getHhOrdersIntime());
        calender.set(Calendar.MONTH, calender.get(Calendar.MONTH) + 1);
        order.setHhOrdersOuttime(calender.getTime());

        session.setAttribute("order", order);
        return "/personal/order/OrderCreate";
    }

    //    @RequestMapping("/personal/order/getImgUrl/${ImgUrl}")
//    public void getImgUrl(@Validated String ImgUrl , HttpServletResponse response) {
//        File img=new File(ImgUrl);
////        FileInputStream is= new FileInputStream(img)
////       OutputStream os= response.getOutputStream();
////        os.close();
//    }
    //获取手机验证
    @RequestMapping("/getVerfiy/{mobile}")
    @ResponseBody
    public String getVerfy(@PathVariable String mobile, HttpSession session) throws ClientException, InterruptedException {

        Object ord = session.getAttribute("order");
        if (ord == null) {
            return "login";// 用户未登入
        }

        Order order = (Order) ord;
        order.getUser().setHhUserTel(mobile);


        int code = orderService.SendVerfyCode(order);

        if (code == 1 || code == 0) {
            return "1"; //发送失败
        }
        session.setAttribute("code", code);
        return "0"; //发送失败
    }
    //创建订单
    @RequestMapping("/createOrder")
    public String createOrder(String hhOrdersId, String verfyCode, Order order, HttpSession session, Model model) {
        if (verfyCode.equals(session.getAttribute("code"))) {//检查验证码是否匹配
            model.addAttribute("message", "验证码有误");
            return "/personal/order/OrderCreate";
        }
        Object ord = session.getAttribute("order");
        if (ord == null) {//检查订单是否失效
            return "redirect:/personal/order/login";// 用户未登入
        }

        Order orde = (Order) ord;
        if (orde.getHhOrdersId() != hhOrdersId) {//检查订单id是否区配
            model.addAttribute("message", "订单ID不匹配，请重新下单");
            return "/personal/order/OrderCreate";
        }
        order.setHhOrdersId(hhOrdersId);
        order.setHouseInfo(order.getHouseInfo());
        order.setUser(order.getUser());
        int numPer = 1;//入住人数
        try {
            orderService.createOrder(order, numPer);
        } catch (MegException e) {
            model.addAttribute("message", e.getMessage());
            return "/personal/order/OrderCreate";
        }
        session.removeAttribute("order");
        session.removeAttribute("code");
        return "/personal/order/orderList";
    }
    //全部订单
    @RequestMapping("/list")
    public String getOrderList(HttpSession session,Model model) {
        User a=new User();
        a.setHhUserId("12");
        session.setAttribute("user",a);
        Object obj = session.getAttribute("user");

        if (obj == null) {//如果用户未登入就登入
            return "redirect:login";
        }
        User user=(User)obj;
        List<Order> orderList=orderService.findOrdersById(user);
        model.addAttribute("orderList",orderList);


        return "/personal/order/OrderList";

    }

    @RequestMapping("/findList")
    public String findOrderList(int status,Model model) {
        List<Order> orderList=null;
        if(status!=0){
            orderList=orderService.findOrdersByStatus(status);
        }else {
            orderList=orderService.findAll();
        }
        model.addAttribute("orderList",orderList);
        return "/personal/order/OrderList";
    }





    // /订单详情
    @RequestMapping("/toview")
    public String toView(@RequestParam(required = true) String hhOrdersId ,Model model){
        //        if (session.getAttribute("User") == null) {
//            return "redirect:login";
//        }
        Order order=orderService.findOrderByOrderId(hhOrdersId);
        model.addAttribute("order",order);
        return "/personal/order/OrderView";

    }
    //删除订单
    @RequestMapping("delete")
    public String deleteOrders(@RequestParam(required = true,value="hhOrdersId") String[] hhOrdersIds){
        String status="11" ;//用户删除订单;  12//管理员删除订单
        orderService.updateOrderStatus( hhOrdersIds, status);
        return "redirect:/personal/order/list";
    }
    //取消订单
    @RequestMapping("/cancel")
    public String cancelOrder(String hhOrdersId, Model model){
        try {
            orderService.cancelOrder(hhOrdersId);
        } catch (MegException e) {
            model.addAttribute("message" ,e.getMessage());
            return "/personal/order/toview";
        }
        return "redirect:/personal/order/list";

    }

    //退租
    @RequestMapping("/checkOut")
    public String checkOutOrder(String hhOrdersId, Model model){
        try {
            orderService.checkOutOrder(hhOrdersId);
        } catch (MegException e) {
            model.addAttribute("message" ,e.getMessage());
            return "/personal/order/toview";
        }
        return "redirect:/personal/order/list";
    }


}
