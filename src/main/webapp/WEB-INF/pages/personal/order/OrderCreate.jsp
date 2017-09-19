<%@ page import="com.fy.pojo.Order" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../../baselist.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title>订单确认</title>
    <link rel="stylesheet" rev="stylesheet" type="text/css" href="${ctx}/staticfile/css/extreme/orderCreate.css"/>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8"/>
    <script src="${ctx}/staticfile/js/jquery-1.6.2.js"></script>
    <script type="text/javascript">
        //-----------------------------------------------------

        //发送验证码给手机

        function getVerfiy() {
            var mobile = $("#mobile").val();
            var urlA = "/personal/order/getVerfiy/" + mobile;

            $.ajax({
                type: "GET",
                url: urlA,
                success: function (data) {
                    if (data == "login") {
                        alert("您还未登入帐号，请登入后重新下单！！！" +
                                "点击确定后，跳转回登入页面");

                        window.location.href = "/tologin";

                    } else if (data == 0) {
                        alert("已发送");


                        $(".code1").css("background-color", "#b4b2b3");
//下面就是实现倒计时的效果代码
                        var d = new Date();
                        d.setSeconds(d.getSeconds() + 59);
                        var m = d.getMonth() + 1;
                        var time = d.getFullYear() + '-' + m + '-' + d.getDate() + ' ' + d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds();
                        var id = ".code1";
                        var end_time = new Date(Date.parse(time.replace(/-/g, "/"))).getTime(),
//月份是实际月份-1
                                sys_second = (end_time - new Date().getTime()) / 1000;
                        var timer = setInterval(function () {
                                    if (sys_second > 1) {
                                        sys_second -= 1;
                                        var day = Math.floor((sys_second / 3600) / 24);
                                        var hour = Math.floor((sys_second / 3600) % 24);
                                        var minute = Math.floor((sys_second / 60) % 60);
                                        var second = Math.floor(sys_second % 60);
                                        var time_text = '';
                                        if (day > 0) {
                                            time_text += day + '天';
                                        }
                                        if (hour > 0) {
                                            if (hour < 10) {
                                                hour = '0' + hour;
                                            }
                                            time_text += hour + '小时';
                                        }
                                        if (minute > 0) {
                                            if (minute < 10) {
                                                minute = '0' + minute;
                                            }
                                            time_text += minute + '分';
                                        }
                                        if (second > 0) {
                                            if (second < 10) {
                                                second = '0' + second;
                                            }
                                            time_text += second + '秒';
                                        }
                                        $(".code1").html(time_text);
                                    } else {
                                        clearInterval(timer);
                                        $(".code1").html("<a  href='#'  onclick='getVerfiy()' style=' display: block ;text-align:center ;background-color:lightgreen;'>获取验证码</a>");


                                    }
                                },
                                1000);
                    } else {
                        alert("发送失败，请再试一次。");
                    }
                }

            });
        }

        //-----------------------
        function checkDate() {
            var startDate = $("input[name='hhOrdersIntime']").val();
            var hhOrdersIntime = $("#hhOrdersIntime").val();

            startDate = startDate.replace(new RegExp("-", "gm"), "/");
            hhOrdersIntime = hhOrdersIntime.replace(new RegExp("-", "gm"), "/");
            var startDates = (new Date(startDate)).getTime();
            var hhOrdersIntimes = (new Date(hhOrdersIntime)).getTime();


            if (startDates < hhOrdersIntimes) {
                alert("您选定入住日期应大于当前日期，已帮您重置为默认日期");
                $("input[name='hhOrdersIntime']").val($("#hhOrdersIntime").val());
                return;
            }

            var endDate = $("input[name='hhOrdersOuttime']").val();
            endDate = endDate.replace(new RegExp("-", "gm"), "/");
            var endDates = (new Date(endDate)).getTime()


            if (parseInt(endDates) - parseInt(startDates) < 30 * 24 * 3600 * 1000) {
                alert("对不起，最少租住30天，已帮您重置为默认日期");
                $("input[name='hhOrdersOuttime']").val($("#hhOrdersOuttime").val());
                return;
            }

            if($("#verfyCode").val()==""||$("#verfyCode").val()==null){
                alert("验证码不能为空")
                return;
            }
            formSubmit('/personal/order/createOrder', '_self')

        }
        //确认开始时间
        <%
                Order order=(Order)session.getAttribute("order");
                String  imgUrlB=order.getHouseInfo().getHhHouseImg();
                String[] imgUrls=imgUrlB.split(",");
                 String firstImg=null;
                 String middImg=null;
                 String lastImg=null;
                 int nx=0;
                if(imgUrls.length==1){
                    firstImg=middImg=lastImg=imgUrls[0];
                }else if(imgUrls.length==2){
                    firstImg=middImg=imgUrls[0];
                    lastImg=imgUrls[1];
                }else if(imgUrls.length>2){
                firstImg=imgUrls[0];
                middImg=imgUrls[1];
                lastImg=imgUrls[2];
                }else{
                 firstImg=middImg=lastImg="";
                }

                 %>


        //-------------------------------------------------------
    </script>
    <style>
        *, a {

            font-family: "微软雅黑", Arial, sans-serif;
            color: #5b6869;
            font-size: 14px;

        }

        table {

            margin: 0 15px;

            width: 100%;
        }

        tr {
            border:1px red solid;
            margin: 10px;
            width: 100%;
        }

        td {
            font-family: "微软雅黑", Arial, sans-serif;
            color: #4cae4c;
            font-size: 14px;
            line-height: 20px;
            width: 180px;
        }

        .tda {
            width: 100px;
            text-align: right;
            color: #5b6869;
        }

        .sub {
            margin-left: 140px;
            height: 30px;

        }
    </style>
</head>

<body>
<form name="icform" method="post">
    <div class="textbox-title">
        <img src="../../staticfile/skin/default/images/icon/currency_yen.png"/>
        订单确认
    </div>

    <div>
        <div id="warp">
            <div id="left">

                <div id="left_top" style="height: 300px">
                    <img src="/personal/order/getImgUrl?imgUrl=<%=firstImg%>"/>
                </div>
                <div style="text-align: center ; margin: 20px 10px ;  font-size: 14px;word-wrap: break-word">

                    <div style="mapping:5px ">配套设施:${order.houseInfo.hhHouseSupport}</div>

                    <br/>
                        <div style="mapping:5px ">其它说明:${order.houseInfo.hhHouseDescription}</div>

                </div>




            </div>

            <div id="right">
                <div id="right_top">
                    <table>
                        <tr class="odd">
                            <td class="tda" colspan="4"
                                style="font-size: 18px; text-align: center; color: #000; padding-top: 10px;padding-bottom: 10px">
                                订单详情:
                                <input type="hidden" name="hhOrdersId" value="${order.hhOrdersId}"/>
                            </td>
                        </tr>
                        <tr class="odd">
                            <td class="tda" colspan="4"
                                style="font-size: 14px; text-align: center; color: red">
                                ${message}
                            </td>
                        </tr>
                        <tr class="odd">
                            <td class="tda" colspan="4"
                                style="font-size: 16px; text-align: left; color: #000; padding-top: 5px;padding-bottom: 5px">
                                一.房源信息:
                            </td>

                        </tr>
                        <tr class="odd">
                            <td class="tda">房源编号:</td>
                            <td colspan="3">${order.houseInfo.hhHouseId}<input value="${order.houseInfo.hhHouseId }"
                                                                     name="houseInfo.hhHouseId"
                                                                     type="hidden">
                            </td>
                        </tr>
                        <tr class="odd">
                            <td class="tda">房源简介:</td>
                            <td colspan="3">${order.houseInfo.hhHouseName}</td>
                        </tr>
                        <tr class="odd">
                            <td class="tda">发布时间:</td>
                            <td><fmt:formatDate value="${order.houseInfo.hhHousePublishtime}"
                                                                pattern="yyyy年MM月dd日"/></td>

                            <td class="tda">月租金:</td>
                            <td>￥ ${order.houseInfo.hhHousePrice}</td>
                        </tr>

                        <tr class="odd">
                            <td class="tda">小区:</td>
                            <td>${order.houseInfo.hhHouseVillage}</td>
                            <td class="tda">户型:</td>
                            <td>${order.houseInfo.hhHouseType}</td>
                        </tr>
                        <tr class="odd">
                            <td class="tda">楼层:</td>
                            <td>${order.houseInfo.hhHouseFloor}</td>
                            <td class="tda">面积:</td>
                            <td>${order.houseInfo.hhHouseArea}</td>
                        </tr>
                        <tr class="odd">
                            <td class="tda" colspan="4"
                                style="font-size: 16px; text-align: left; color: #000; padding-top: 5px;padding-bottom: 5px">
                                二.地址及联系人
                            </td>
                        </tr>
                        <tr class="odd">
                            <td class="tda">联系人:</td>
                            <td>${order.houseInfo.hhHousePublisher}</td>
                            <td class="tda">联系电话:</td>
                            <td>${order.houseInfo.hhHouseTelephone}</td>
                        </tr>
                        <tr class="odd">
                            <td class="tda">地址:</td>
                            <td colspan="3">${order.houseInfo.hhHouseAddress}</td>
                        </tr>
                        <td class="tda" colspan="4"
                            style="font-size: 16px; text-align: left; color: #000; padding-top: 5px;padding-bottom: 5px">
                            三.用户信息
                        </td>

                        <tr class="odd">
                            <td class="tda">用户ID:</td>
                            <td colspan="3">${order.user.hhUserId}<input value="${order.user.hhUserId}" name="user.hhUserId"
                                                               type="hidden"></td>

                        </tr>
                        <tr class="odd">
                        <td class="tda">用户名:</td>
                        <td>${order.user.hhUserName}</td>
                        </tr>
                        <tr class="odd">
                            <td class="tda">入住时间:</td>
                            <td><input type="text" style="width:90px;" name="hhOrdersIntime"
                                       value="<fmt:formatDate value="${order.hhOrdersIntime}" pattern="yyyy-MM-dd"/>"

                                       onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"

                            />
                                <input type="hidden" id="hhOrdersIntime"
                                       value="<fmt:formatDate value="${order.hhOrdersIntime}" pattern="yyyy-MM-dd"/>"/>
                            </td>
                            <td class="tda">退租时间:</td>
                            <td><input type="text" style="width:90px;" name="hhOrdersOuttime"
                                       value="<fmt:formatDate value="${order.hhOrdersOuttime}" pattern="yyyy-MM-dd"/>"

                                       onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'})"/>

                                <input type="hidden" id="hhOrdersOuttime"
                                       value="<fmt:formatDate value="${order.hhOrdersOuttime}" pattern="yyyy-MM-dd"/>"/>
                            </td>
                        </tr>
                    </table>


                </div>


                <table style=" width: 300px; margin-left: 100px; margin-bottom: 10px">
                    <tr class="odd">
                        <td class="tda">手机号:</td>
                        <td colspan="1"><input type="tel" class="tel" id="mobile"
                                               value="${user.hhUserTel}18600960865"></td>
                    </tr>
                    <tr class="odd">
                        <td class="tda">
                            <div style="float: right; "><span class="code1">
                                <a  href="#"  onclick="getVerfiy()"
                                      style=" display: block ;text-align:center ;background-color:lightgreen;">获取验证码</a>
                                </span>
                            </div>
                        </td>
                        <td><input type="text" name="verfyCode" class="code" placeholder="输入验证码" id="verfyCode"/></td>
                    </tr>
                </table>
                <div id="navMenubar" class="sub">
                    <ul>
                        <li id="save"><a href="#"
                                         onclick="checkDate();this.blur();">提交</a>
                        </li>
                        <li id="back"><a href="#" onclick=" window.history.go(-1)">取消</a></li>
                    </ul>

                </div>
            </div>
        </div>
    </div>

</form>


</body>
</html>


