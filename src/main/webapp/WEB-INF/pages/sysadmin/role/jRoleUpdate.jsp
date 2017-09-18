<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>角色修改</title>
</head>

<body>
<form name="icform" method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>

	<li id="update"><a href="#" onclick="formSubmit('update','_self');this.blur();">修改</a></li>

	<li id="back"><a href="#" onclick=" window.history.go(-1)">返回</a></li>
	
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="../../staticfile/skin/default/images/icon/currency_yen.png"/>
    角色修改
  </div> 
  
<div>

<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >

	<%--<tr class="odd" hidden="hidden">
		<td>角色Id:</td>
		<td><input  type="text" name="hhRoleId" value="${role.hhRoleId}"/></td>
	</tr>--%>
	
	<tr class="odd">
		<td>角色名称:</td>

		<td>
			<input  type="text" name="hhRoleName" value="${role.hhRoleName}"/>
			<input type="hidden" name="hhRoleId" value="${role.hhRoleId}"/>
		</td>

	<%--	<td><input  type="text" name="name" value="${role.name}"/></td>--%>

	</tr>
	<tr class="odd">
		<td>排序号:</td>
		<td><input  type="text" name="hhRoleOrderNo" value="${role.hhRoleOrderNo}"/></td>
	</tr>
	<tr class="odd">
		<td>备注信息:</td>

		<td colspan="3">
			<textarea style="height:80px;width:90%" name="hhRoleRemarks">${role.hhRoleRemarks}</textarea>
		</td>
	</tr>
	<tr class="odd">
		<td>状态:</td>
		<td>
			<input type="radio" name="hhRoleStatus" value="1" <c:if test="${role.hhRoleStatus ==1}">checked="checked"</c:if>/>启用
			<input type="radio" name="hhRoleStatus" value="0" <c:if test="${role.hhRoleStatus ==0}">checked="checked"</c:if>/>停用


		<%--<td>
			<textarea style="height:130px;width:100%" name="remarks">${role.remarks}</textarea>

		</td>--%>
	</tr>
</table>
</div>
 
</div>
 
 
</form>
</body>
</html>

