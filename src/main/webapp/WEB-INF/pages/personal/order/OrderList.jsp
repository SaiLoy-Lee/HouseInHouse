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
                        <li id="new"><a href="#" onclick="formSubmit('tocreate','_self');this.blur();">新增</a></li>
                        <li id="update"><a href="#" onclick="formSubmit('toupdate','_self');this.blur();">修改</a></li>
                        <li id="delete"><a href="#" onclick="formSubmit('delete','_self');this.blur();">删除</a></li>
                        <li id="new"><a href="#" onclick="formSubmit('start','_self');this.blur();">启用</a></li>
                        <li id="new"><a href="#" onclick="formSubmit('stop','_self');this.blur();">停用</a></li>
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
                    <td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('deptId',this)"></td>
                    <td class="tableHeader">序号</td>
                    <shiro:hasPermission name="用户">
                        <td class="tableHeader">图片</td>
                    </shiro:hasPermission>
                    <td class="tableHeader">订单号</td>
                    <td class="tableHeader">房源ID</td>
                    <td class="tableHeader">小区</td>
                    <td class="tableHeader">地址</td>
                    <shiro:hasPermission name="用户">
                        <td class="tableHeader">联系人</td>
                        <td class="tableHeader">联系方式</td>
                    </shiro:hasPermission>

                    <shiro:hasPermission name="管理员">
                        <td class="tableHeader">租户</td>
                        <td class="tableHeader">联系方式</td>
                    </shiro:hasPermission>
                    <td class="tableHeader">起租日期</td>
                    <td class="tableHeader">退租日期</td>
                </tr>
                </thead>
                <tbody class="tableBody">

                <c:forEach items="${orderList}" var="o" varStatus="status">
                    <tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'">
                        <td><input type="checkbox" name="o.hhOrdersId" value="${o.hhOrdersId}"/></td>
                        <shiro:hasPermission name="用户">
                        <td><img src="${o.houseInfo.hhHouseImg}" width="200px" height="200px"></td>
                        </shiro:hasPermission>
                        <td>${o.hhOrdersId}</td>
                        <td>${o.houseInfo.hhHouseId}</td>
                        <td>${o.houseInfo.hhHouseVillage}</td>
                        <td>${o.houseInfo.hhHouseAddress}</td>
                        <shiro:hasPermission name="用户">
                            <td>${o.houseInfo.hhHousePublisher}</td>
                        </shiro:hasPermission>
                        <td><a href="dept/toview?id=${d.deptId}">${d.deptName}</a></td>

                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>

    </div>


</form>
</body>
</html>

