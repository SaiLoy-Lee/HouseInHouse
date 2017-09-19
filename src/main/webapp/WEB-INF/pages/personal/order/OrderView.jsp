<%@ page import="com.fy.pojo.Order" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../../baselist.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>--%>

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title>订单详情</title>

    <meta http-equiv="Content-type" content="text/html; charset=UTF-8"/>
    <script src="${ctx}/staticfile/js/jquery-1.6.2.js"></script>
    <script type="text/javascript">
        function alt(meg, task, target) {
            <c:if test="${order.hhOrdersStatus==1}">
            var text = $("#hhOrdersRemarks").val().trim();
            if (text == "") {
                alert("拒签原因不能为空！！！")
                return
            } else {
                var remark = $("input[name='hhOrdersRemarks']").val().trim() + "<br />" + text;
                $("input[name='hhOrdersRemarks']").val(remark);
            }
            </c:if>
            if (confirm("你确定要" + meg)) {
                formSubmit(task, target)
            }
        }

        //-------------------------------------------------------
    </script>
    <style>
        #warp {
            width: 800px;
            margin: 10px auto;
            border: 1px solid #CCCCCC;
            text-align: center;
        }

        #right #right_top #prod_name {
            display: block;
            color: #494949;
            font-size: 22px;
            padding-top: 50px;
            padding-left: 30px;
        }

        #right #right_top #prod_desc {
            display: block;
            color: #FF0000;
            font-size: 14px;
            padding-left: 30px;
        }

        #right #right_middle {
            height: 270px;
            line-height: 35px;
            border-bottom: 1px solid #CCCCCC;
        }

        #right #right_middle #right_middle_span {
            display: block;
            padding-top: 55px;
            margin-left: 30px;
            color: #666666;
            font-size: 16px;
        }

        #right #right_middle #delNum, #right #right_middle #addNum {
            border: 1px solid #CCCCCC;
            color: #999999;
            padding: 2px 7px;
            text-decoration: none;
            vertical-align: middle;
        }

        #right #right_middle #buyNumInp {
            border: 1px solid #CCCCCC;
            width: 40px;
            height: 24px;
            line-height: 20px;
            text-align: center;
            vertical-align: middle;
        }

        #right #right_bottom {
            height: 200px;
        }

        #right #right_bottom .add_cart_but {
            background-image: url("../img/prodInfo/jr.jpg");
            width: 189px;
            height: 42px;
            border-style: none;
            margin: 30px;
        }

        *, a {

            font-family: "微软雅黑", Arial, sans-serif;
            color: #5b6869;
            font-size: 14px;

        }

        table {

            margin: 0 50px;

            width: 90%;

        }

        tr {
        / / border: 1 px solid red;

            margin: 10px;
            width: 100%;
        }

        td {
        / / border: 1 px solid red;
            font-family: "微软雅黑", Arial, sans-serif;
            color: #4cae4c;
            font-size: 14px;
            line-height: 20px;
            width: 250px;

        }

        .tda {
            width: 120px;
            color: #5b6869;
            text-align: right;
        }

        .sub {
            margin-left: 140px;
            height: 30px;

        }

        body {
            margin: 0px;
            padding: 0px;
            font-family: "微软雅黑";
        }

        #warp:after {
            display: block;
            content: '.';
            clear: both;
            line-height: 0;
            visibility: hidden;
        }

    </style>
</head>

<body>
<form name="icform" method="post">
    <div class="textbox-title">
        <img src="../../staticfile/skin/default/images/icon/currency_yen.png"/>
        订单详情
    </div>

    <div>
        <div id="warp">
            <div id="right">
                <div id="right_top">
                    <table>
                        <tr class="odd">
                            <td class="tda" colspan="4"
                                style="font-size: 18px; text-align: center; color: #000; padding-top: 10px;padding-bottom: 10px">
                                订单详情:
                                <input type="hidden" name="hhOrdersId" value="${order.hhOrdersId}"/>
                            </td>
                        <tr class="odd">
                            <td class="tda" colspan="4"
                                style="font-size: 14px; text-align: center; color: red">
                                ${message}
                            </td>
                        <tr class="odd">

                        <tr class="odd">
                            <td class="tda" colspan="4"
                                style="font-size: 16px; text-align: left; color: #000; padding-top: 5px;padding-bottom: 5px">
                                一.订单详情
                            </td>
                        </tr>
                        <tr class="odd">
                            <td class="tda">图片:</td>
                            <td colspan="3">
                                <% Order order = (Order) request.getAttribute("order");
                                    String imgUrlB = order.getHouseInfo().getHhHouseImg();
                                    String[] imgUrls = imgUrlB.split(",");
                                    int len = 3;
                                    if (imgUrls.length < len) {
                                        len = imgUrls.length;
                                    }
                                    for (int i = 0; i < len; i++) {
                                        String imgUrl = imgUrls[i];
                                %>
                                <img src="/personal/order/getImgUrl?imgUrl=<%=imgUrl %>" width="150px" height="150px">
                                <% } %>
                            </td>
                        </tr>
                        <tr class="odd">
                            <td class="tda">房源名:</td>
                            <td colspan="3">${order.houseInfo.hhHouseName}</td>
                        </tr>
                        <tr class="odd">
                            <td class="tda">订单号:</td>
                            <td>${order.hhOrdersId}</td>
                        </tr>
                        <td class="tda">订单状态:</td>
                        <td><c:if test="${order.hhOrdersStatus==1}"><span style="color:red">订单审核中</span></c:if>
                            <c:if test="${order.hhOrdersStatus==2}"><span style="color:red">审核未通过</span></c:if>
                            <c:if test="${order.hhOrdersStatus==3}">已入住</c:if>
                            <c:if test="${order.hhOrdersStatus==4}">已退房</c:if>
                            <c:if test="${order.hhOrdersStatus==5}">已取消</c:if>
                            <c:if test="${order.hhOrdersStatus==6}"><span style="color:red">退租审核中</span></c:if>
                        </td>
                        <td class="tda">生成时间:</td>
                        <td colspan="3"><fmt:formatDate value="${order.houseInfo.createTime}"
                                                        pattern="yyyy年MM月dd日"/></td>
                        <tr class="odd">
                            <td class="tda">入住时间:</td>
                            <td>
                                <fmt:formatDate value="${order.hhOrdersIntime}" pattern="yyyy年MM月dd日"/>
                            </td>
                            <td class="tda">退租时间:</td>
                            <td>
                                <fmt:formatDate value="${order.hhOrdersOuttime}" pattern="yyyy年MM月dd日"/>
                            </td>
                        </tr>
                        <tr class="odd">
                            <td class="tda">备注:</td>
                            <td colspan="3">${order.hhOrdersRemarks}</td>
                        </tr>


                        <tr class="odd">
                            <td class="tda" colspan="4"
                                style="font-size: 16px; text-align: left; color: #000; padding-top: 5px;padding-bottom: 5px">
                                二.房源信息:
                            </td>

                        </tr>
                        <tr class="odd">
                            <td class="tda">房源编号:</td>
                            <td colspan="3">${order.houseInfo.hhHouseId}<input value="${order.houseInfo.hhHouseId}"
                                                                               name="hhHouseId"
                                                                               type="hidden">
                            </td>
                        </tr>
                        <tr class="odd">
                            <td class="tda">发布时间:</td>
                            <td colspan="1"><fmt:formatDate value="${order.houseInfo.hhHousePublishtime}"
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
                            <td>${order.houseInfo.hhHouseFloor} 楼</td>
                            <td class="tda">面积:</td>
                            <td>${order.houseInfo.hhHouseArea} m²</td>
                        </tr>

                        <tr class="odd">
                            <td class="tda">配套设施:</td>
                            <td class="odd" colspan="3">${order.houseInfo.hhHouseSupport}</td>
                        </tr>
                        <tr class="odd">
                            <td class="tda">其它说明:</td>
                            <td class="odd" colspan="3">${order.houseInfo.hhHouseDescription}</td>
                        </tr>


                        <tr class="odd">
                            <td class="tda" colspan="4"
                                style="font-size: 16px; text-align: left; color: #000; padding-top: 5px;padding-bottom: 5px">
                                三.地址及联系人
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
                        <tr>
                            <td class="tda" colspan="4"
                                style="font-size: 16px; text-align: left; color: #000; padding-top: 5px;padding-bottom: 5px">
                                四.用户信息
                            </td>
                        </tr>

                        <tr class="odd">
                            <td class="tda">用户ID:</td>
                            <td colspan="3">${order.user.hhUserId}<input value="${order.user.hhUserId}" name="hhUserId"
                                                                         type="hidden"></td>

                        </tr>
                        <tr class="odd">
                            <td class="tda">用户名:</td>
                            <td>${order.user.hhUserName}</td>
                            <td class="tda">手机号:</td>
                            <td> ${order.user.hhUserTel}</td>
                        </tr>
                        <c:if test="${order.hhOrdersStatus==1}">
                            <tr class="odd">
                                <td class="tda">拒签原因:</td>
                                <td colspan="3" placeholder="请务必填写"><textarea cols="90%" rows="3"
                                                                              id="hhOrdersRemarks"></textarea></td>
                                <input type="hidden" value="${order.hhOrdersRemarks}" name="hhOrdersRemarks">
                            </tr>
                        </c:if>
                        <c:if test="${order.hhOrdersStatus==6}">
                            <tr class="odd">
                                <td class="tda" colspan="4"
                                    style="font-size: 16px; text-align: center; color: #000; padding-top: 5px;padding-bottom: 5px">
                                <input type="radio" name ="userStatus" value="0" />停用用户
                                </td>
                            </tr>
                        </c:if>
                        <tr class="odd">
                            <td class="tda"></td>
                            <td class="tda" colspan="3"
                                style="text-align: center; color: #000; padding-top:20px;padding-bottom: 5px">
                                <div id="navMenubar" class="sub">
                                    <ul>
                                        <li id="back"><a href="#" onclick=" window.history.go(-1)">返回</a></li>
                                        <c:if test="${order.hhOrdersStatus==1}">
                                            <li id="save1"><a href="#"
                                                               onclick="alt('取消订单','cancel','_self');this.blur();">取消订单</a>
                                            </li>
                                        </c:if>
                                        <shiro:hasPermission name="用户">
                                            <c:if test="${order.hhOrdersStatus==3}">
                                                <li id="new"><a href="#"
                                                                onclick="alt('退房','checkOut','_self');this.blur();">退房</a>
                                                </li>
                                            </c:if>
                                        </shiro:hasPermission>
                                        <shiro:hasPermission name="管理员">
                                            <c:if test="${order.hhOrdersStatus==1}">
                                                <li id="save1"><a href="#"
                                                                onclick="alt('签约订单','updateStatus/3','_self');this.blur();">签约订单</a>
                                                </li>
                                            </c:if>
                                            <c:if test="${order.hhOrdersStatus==1}">
                                                <li id="save1"><a href="#"
                                                                onclick="alt('拒签订单','updateStatus/2','_self');this.blur();">拒签订单</a>
                                                </li>
                                            </c:if>
                                            <c:if test="${order.hhOrdersStatus==6}">
                                                <li id="save1"><a href="#"
                                                                onclick="alt('同意退房','updateStatus/4','_self');this.blur();">同意退房</a>
                                                </li>
                                            </c:if>
                                        </shiro:hasPermission>
                                    </ul>

                                </div>
                            </td>

                        </tr>


                    </table>
                </div>

            </div>

        </div>

    </div>


</form>


</body>
</html>


