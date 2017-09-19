<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>房源列表</title>
</head>

<body>
<form name="icform" method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
	<li id="view"><a href="#" onclick="formSubmit('houseFindById','_self');this.blur();">查看</a></li>
	<li id="new"><a href="#" onclick="formSubmit('tocreate','_self');this.blur();">新增</a></li>
	<li id="update"><a href="#" onclick="formSubmit('toupdate','_self');this.blur();">修改</a></li>
	<li id="delete"><a href="#" onclick="formSubmit('delete','_self');this.blur();">删除</a></li>
	<li id="new"><a href="#" onclick="formSubmit('start','_self');this.blur();">上架房源</a></li>
	<li id="new"><a href="#" onclick="formSubmit('stop','_self');this.blur();">下架房源</a></li>
	<%--<li id="new"><a href="#" onclick="formSubmit('userRole','_self');this.blur();">角色</a></li>--%>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="../../staticfile/skin/default/images/icon/currency_yen.png"/>
    房源列表
  </div> 
  
<div>


	<div class="eXtremeTable" >
		<table id="ec_table" class="tableRegion" width="98%" >
			<thead>
			<tr>
				<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('hhHouseId',this)"></td>
				<td class="tableHeader" width="3%" >序号</td>
				<td class="tableHeader" width="25%">房屋名称</td>
				<td class="tableHeader" width="8%">地址</td>
				<td class="tableHeader" width="15%">小区名称</td>
                <td class="tableHeader" width="3%">可住人数</td>
                <td class="tableHeader" width="3%">已住人数</td>
                <td class="tableHeader">发布人</td>
                <td class="tableHeader">电话</td>
				<td class="tableHeader">发布时间</td>
                <td class="tableHeader">状态</td>
			</tr>
			</thead>
			<tbody class="tableBody" >

			<c:forEach items="${houseList}" var="u" varStatus="status">
				<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'">
					<td><input type="checkbox" name="hhHouseId" value="${u.hhHouseId}"/></td>
					<td>${status.index+1}</td>
					<td>${u.hhHouseName}</td>
					<td>${u.hhHouseAddress}</td>
					<td>${u.hhHouseVillage}</td>
					<td>${u.hhHouseMaxnum}</td>
					<td>${u.hhHouseResidenum}</td>
					<td>${u.hhHousePublisher}</td>
					<td>${u.hhHouseTelephone}</td>
					<td><fmt:formatDate value="${u.hhHousePublishtime}" pattern="yyyy-MM-dd"/></td>
					<td>
						<c:if test="${u.hhHouseStatus==0}"><a href="stop?hhHouseId=${u.hhHouseId}"><font color="green">点击下架房源</font></a></c:if>
						<c:if test="${u.hhHouseStatus==1}"><a href="start?hhHouseId=${u.hhHouseId}"><font color="red">点击发布房源</font></a></c:if>
					</td>
				</tr>
			</c:forEach>

			</tbody>
		</table>
	</div>
</div>
 
 
</form>
</body>
</html>

