<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>部门查看</title>
</head>

<body>
<form name="icform" method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
	<li id="back"><a href="#" onclick=" window.history.go(-1)">返回</a></li>
	
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="../../staticfile/skin/default/images/icon/currency_yen.png"/>
    部门查看
  </div> 
  
<div>


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<tr class="odd">
		<td>部门ID:</td>
		<td>${dept.deptId}</td>
	</tr>
	<tr class="odd">
		<td>上级部门:</td>
		<td>
			${dept.parentDept.deptName}
		</td>
	</tr>
	<tr class="odd">
		<td>部门名称:</td>
		<td>${dept.deptName}</td>
	</tr>
	<tr class="odd">
		<td>状态:</td>
		<td>
			<c:if test="${dept.state ==1}">启用</c:if>  
			<c:if test="${dept.state ==0}">停用</c:if>  
		</td>
	</tr>
</table>
</div>
 
</div>
 
 
</form>
</body>
</html>

