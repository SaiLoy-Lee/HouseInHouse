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
		<td>${dept.hhDeptId}</td>
	</tr>
	<%--<tr class="odd">
		<td>上级部门:</td>
		<td>
			${dept.parentDept.deptName}
		</td>
	</tr>--%>
	<tr class="odd">
	<td>课程:</td>
	<td>${dept.hhDeptCourse}</td>
</tr>
	<tr class="odd">
		<td>班级:</td>
		<td>${dept.hhDeptNum}</td>
	</tr>
	<tr class="odd">
		<td>教室:</td>
		<td>${dept.hhDeptRoomnum}</td>
	</tr>
	<tr class="odd">
		<td>开班时间：</td>
		<td>${d.hhDeptStarttime}</td>
	</tr>
	<tr class="odd">
		<td>结课时间：</td>
		<td>${d.hhDeptStoptime}</td>
	</tr>

	<tr class="odd">
		<td>状态:</td>
		<td>
			<c:if test="${dept.hhDeptStatus==1}">启用</c:if>
			<c:if test="${dept.hhDeptStatus ==0}">停用</c:if>
		</td>
	</tr>
</table>
</div>
 
</div>
 
 
</form>
</body>
</html>

