<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../../baselist.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>全部订单</title>

</head>

<body>
<form name="icform" method="post">

    <div id="menubar">
        <div id="middleMenubar">
            <div id="innerMenubar">
                <div id="navMenubar">
                    <ul>
                        <li id="view"><a href="#" onclick="formSubmit('toview','_self');this.blur();">查看</a></li>
                        <c:if test="${o.hhOrdersStatus!=12}">
                        <li id="delete"><a href="#" onclick="formSubmit('updateStatus/12','_self');this.blur();">删除</a>
                            </c:if>
                        <li id="download"><a href="#" onclick="formSubmit('download','_self');this.blur();">下载订单报表</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="textbox-title">
        <img src="../../staticfile/skin/default/images/icon/currency_yen.png"/>
        全部订单
    </div>

    <div>


        <div class="eXtremeTable">
            <table id="ec_table" class="tableRegion" width="98%">
                <thead>
                <tr>
                    <td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('hhOrdersId',this)"></td>
                    <td class="tableHeader">序号</td>
                    <td class="tableHeader">订单号</td>
                    <td class="tableHeader">订单状态</td>
                    <td class="tableHeader">房源ID</td>
                    <td class="tableHeader">小区</td>
                    <td class="tableHeader">地址</td>
                    <td class="tableHeader">租户</td>
                    <td class="tableHeader">联系方式</td>
                    <td class="tableHeader">起租日期</td>
                    <td class="tableHeader">退租日期</td>
                    <td class="tableHeader">月租金</td>
                    <td class="tableHeader">备注</td>

                </tr>
                </thead>
                <tbody class="tableBody">
                <c:forEach items="${orderList}" var="o" varStatus="status">


                            <tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'">
                                <td><input type="checkbox" name="hhOrdersId" value="${o.hhOrdersId}"/></td>
                                <td>${status.index+1}</td>

                                <td style="word-wrap: break-word;width:120px;"><a
                                        href="dept/toview?id=${o.hhOrdersId}">${o.hhOrdersId}</a></td>
                                <td><c:if test="${o.hhOrdersStatus==1}"><span style="color:red">订单审核中</span></c:if>
                                    <c:if test="${o.hhOrdersStatus==2}"><span style="color:red">审核未通过</span></c:if>
                                    <c:if test="${o.hhOrdersStatus==3}">已入住</c:if>
                                    <c:if test="${o.hhOrdersStatus==4}">已退房</c:if>
                                    <c:if test="${o.hhOrdersStatus==5}">已取消</c:if>
                                    <c:if test="${o.hhOrdersStatus==12}">已删除</c:if>
                                    <c:if test="${o.hhOrdersStatus==6}"><span style="color:red">退租审核中</span></c:if>

                                </td>

                                <td style="word-wrap: break-word;width:120px;">${o.houseInfo.hhHouseId}12</td>
                                <td>${o.houseInfo.hhHouseVillage}</td>
                                <td>${o.houseInfo.hhHouseAddress}</td>

                                <td>${o.user.hhUserName}</td>
                                <td>${o.user.hhUserTel}</td>

                                <td><fmt:formatDate value="${o.hhOrdersIntime}" pattern="yyyy-MM-dd"/></td>
                                <td><fmt:formatDate value="${o.hhOrdersOuttime}" pattern="yyyy-MM-dd"/></td>
                                <td>${o.hhOrdersPrice}</td>
                                <td>${o.hhOrdersRemarks}</td>
                            </tr>

                </c:forEach>
                </tbody>
            </table>
        </div>

    </div>


</form>
</body>
</html>

