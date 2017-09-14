<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>部门列表</title>
</head>

<body>
<form name="icform" method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
	<li id="save"><a href="#" onclick="formSubmit('save','_self');this.blur();">保存</a></li>
	<li id="back"><a href="#" onclick=" window.history.go(-1)">返回</a></li>
	
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="../../staticfile/skin/default/images/icon/currency_yen.png"/>
    部门新增
  </div> 
  
<div>


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<tr class="odd">
		<td>部门ID:</td>
		<td><input  type="text" name="deptId"/></td>
	</tr>
	<tr class="odd">
		<td>上级部门:</td>
		<td>
			<select name="parentDept.deptId" style="width:122px">
				<option value="0">请选择/一级部门</option>
				<!--准备上级部门列表信息  -->
				<c:forEach items="${parentList}" var="p">
					<option value="${p.deptId}">${p.deptName}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr class="odd">
		<td>部门名称:</td>
		<td><input  type="text" name="deptName"/></td>
	</tr>
	<tr class="odd">
		<td>状态:</td>
		<td>
			<input type="radio" name="state" value="1"/>启用
			<input type="radio" name="state" value="0"/>停用
		</td>
	</tr>
</table>
</div>
 
</div>
 
 
</form>
</body>
</html>

