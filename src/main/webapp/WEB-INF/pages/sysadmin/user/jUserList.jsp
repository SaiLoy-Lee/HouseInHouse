<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>用户列表</title>
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
	<li id="new"><a href="#" onclick="formSubmit('userRole','_self');this.blur();">角色</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="../../staticfile/skin/default/images/icon/currency_yen.png"/>
    用户列表
  </div> 
  
<div>


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" text-align="center">

	<tr >
		<td class="tableHeader" width="60px"><input type="checkbox" name="selid" onclick="checkAll('hhUserId',this)">全选</td>
		<%--<td class="tableHeader">序号</td>--%>
		<td class="tableHeader" style="text-align: center" >用户名</td>
		<td class="tableHeader" style="text-align: center" >姓名</td>
		<td class="tableHeader" style="text-align: center" >性别</td>
		<td class="tableHeader" style="text-align: center" >年龄</td>
		<td class="tableHeader" style="text-align: center" >身份证号</td>
		<td class="tableHeader" style="text-align: center" >电话</td>
		<td class="tableHeader" style="text-align: center" >创建时间</td>
		<td class="tableHeader" style="text-align: center" >状态</td>
	</tr>

	<tbody class="tableBody" >
	
	<c:forEach items="${userList}" var="u" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'">
		<td><input type="checkbox" name="hhUserId" value="${u.hhUserId}"/></td>
		<%--<td>${u.hhUserId}</td>--%>
		<td style="text-align: center" >${u.hhUserUsername}</td>
		<td style="text-align: center" >${u.hhUserName}</td>
		<td style="text-align: center" >${u.hhUserSex}</td>
		<td style="text-align: center" >${u.hhUserAge}</td>
		<td style="text-align: center" >${u.hhUserCardid}</td>
		<td style="text-align: center" >${u.hhUserTel}</td>
		<td style="text-align: center" ><fmt:formatDate value="${u.createTime}" pattern="yyyy-MM-dd"/></td>
		<td style="text-align: center" >
			<c:if test="${u.hhUserStatus==1}"><a href="stop?hhUserId=${u.hhUserId}"><font color="green">启用</font></a></c:if>
			<c:if test="${u.hhUserStatus==0}"><a href="start?hhUserId=${u.hhUserId}"><font color="red">停用</font></a></c:if>
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

