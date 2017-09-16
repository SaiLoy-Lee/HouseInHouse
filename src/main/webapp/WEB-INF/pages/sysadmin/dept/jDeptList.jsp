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
    部门列表
  </div> 
  
<div>


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader" style="text-align: center" width="80px"><input type="checkbox" name="selid" onclick="checkAll('hhDeptId',this)">全选</td>
		<td class="tableHeader" style="text-align: center" >序号</td>
		<td class="tableHeader" style="text-align: center" >课程</td>
		<td class="tableHeader" style="text-align: center" >班级</td>
		<td class="tableHeader" style="text-align: center" >教室</td>
		<td class="tableHeader" style="text-align: center" >开班时间</td>
		<td class="tableHeader" style="text-align: center" >结课时间</td>
		<td class="tableHeader" style="text-align: center" >状态</td>
	</tr>
	</thead>
	<tbody class="tableBody" >
	
	<c:forEach items="${deptList}" var="d" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'">
		<td style="text-align: center" ><input type="checkbox" name="hhDeptId" value="${d.hhDeptId}"/></td>
		<td style="text-align: center" >${status.index+1}</td>
		<%--<td>${d.deptId}</td>--%>
		<td style="text-align: center" >${d.hhDeptCourse}</td>
		<td style="text-align: center" >${d.hhDeptNum}</td>
		<td style="text-align: center" >${d.hhDeptRoomnum}</td>
		<%--<td><a href="dept/toview?id=${d.deptId}">${d.deptName}</a></td>--%>
		<td style="text-align: center" ><fmt:formatDate value="${d.hhDeptStarttime}" pattern="yyyy-MM-dd"/></td>
		<td style="text-align: center" ><fmt:formatDate value="${d.hhDeptStoptime}" pattern="yyyy-MM-dd"/></td>
		<td style="text-align: center" >
			<c:if test="${d.hhDeptStatus==1}"><a href="stop?hhDeptId=${d.hhDeptId}"><font color="green">启用</font></a></c:if>
			<c:if test="${d.hhDeptStatus==0}"><a href="start?hhDeptId=${d.hhDeptId}"><font color="red">停用</font></a></c:if>
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

