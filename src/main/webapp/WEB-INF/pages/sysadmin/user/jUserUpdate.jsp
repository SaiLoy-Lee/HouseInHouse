<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

	<title>用户修改</title>
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
    用户修改

  </div> 
  
<div>


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >

	<tr class="odd" hidden="hidden">
		<td><input  type="text" name="hhUserId" value="${user.hhUserId}"/></td>
	</tr>
	<tr class="odd">
		<td>用户名:</td>
		<td><input  type="text" name="hhUserUsername" value="${user.hhUserUsername}"/></td>
		<td>密码:</td>
		<td><input  type="password" name="hhUserPassword" value="${user.hhUserPassword}"/></td>
	</tr>
	<tr class="odd">
		<td>真实姓名:</td>
		<td><input  type="text" name="hhUserName" value="${user.hhUserName}"/></td>
		<td>性别:</td>
		<td>
			<input type="radio" name="hhUserSex" value="男" <c:if test="${user.hhUserSex =='男'}">checked="checked"</c:if>/>男
			<input type="radio" name="hhUserSex" value="女" <c:if test="${user.hhUserSex =='女'}">checked="checked"</c:if>/>女
		</td>
		<td>年龄:</td>
		<td><input  type="text" name="hhUserAge" value="${user.hhUserAge}"/></td>

	</tr>
	<tr class="odd">
		<td>身份证号:</td>
		<td><input type="text" name="hhUserCardid" value="${user.hhUserCardid}"/>
		</td>
		<td>电话:</td>
		<td><input type="text" name="hhUserTel" value="${user.hhUserTel}"/>
		</td>
	</tr>
	<tr class="odd">
		<td>课程:</td>
		<td>
			<select name="dept.hhDeptId" style="width:121px">
				
				<c:forEach items="${deptList}" var="d">
					<option value="${d.hhDeptId}" <c:if test="${d.hhDeptId == user.dept.hhDeptId}">selected="selected"</c:if>>${d.hhDeptCourse}</option>
				</c:forEach>
			</select>
		</td>
		<td>班级:</td>
		<td>
			<select name="dept.hhDeptId" style="width:121px">

				<c:forEach items="${deptList}" var="d">
					<option value="${d.hhDeptId}" <c:if test="${d.hhDeptId == user.dept.hhDeptId}">selected="selected"</c:if>>${d.hhDeptNum}</option>
				</c:forEach>
			</select>
		</td>
		<td>教室:</td>
		<td>
			<select name="dept.hhDeptId" style="width:121px">

				<c:forEach items="${deptList}" var="d">
					<option value="${d.hhDeptId}" <c:if test="${d.hhDeptId == user.dept.hhDeptId}">selected="selected"</c:if>>${d.hhDeptRoomnum}</option>
				</c:forEach>
			</select>
		</td>

	</tr>
	<%--<tr class="odd">

		<td>上级领导:</td>
		<td>
			<select name="userInfo.manager.userInfoId" style="width:121px">
				<option value="">--无上级---</option>
				<c:forEach items="${parentList}" var="p">  userInfo
					<!--其中包含自己的信息，自己不能当自己的上级  -->
					<c:if test="${p.userInfoId !=user.userId}">
						<option value="${p.userInfoId}"
						<c:if test="${p.userInfoId ==user.userInfo.manager.userInfoId}">selected="selected"</c:if>
						>${p.name}</option>
					</c:if>	
				</c:forEach>
			</select>
		</td>
	</tr>--%>
	<%--<tr class="odd">
		<td>入职日期:</td>
		<td>

			<input type="text" style="width:121px;" name="userInfo.joinDate"
	   		onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"
	   		value="<fmt:formatDate value="${user.userInfo.joinDate}" pattern="yyyy-MM-dd"/>"
	   		/>	
		</td>
		<td>薪资:</td>
		<td><input  type="text" name="userInfo.salary" value="${user.userInfo.salary}"/></td>
	</tr>--%>
	
	<%--<tr class="odd">
		<td>生日:</td>
		<td>
			<input type="text" style="width:121px;" name="userInfo.birthday"
	   		onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"
	   		value="<fmt:formatDate value="${user.userInfo.birthday}" pattern="yyyy-MM-dd"/>"/>
		</td>

	</tr>
	<tr class="odd">
		<td>岗位描述:</td>

		<td>
			<input type="text" name="userInfo.station" value="${user.userInfo.station}"/>
		</td>
		<td>电话号码:</td>
		<td>
			<input type="text" name="userInfo.telePhone" value="${user.userInfo.telePhone}"/>
		</td>

	</tr>
	<tr class="odd">
		<td>用户级别:</td>
		<td>

			<select name="userInfo.userLevel" style="width:121px">
				
				<option value="1" <c:if test="${user.userInfo.userLevel ==1}">selected="selected"</c:if>>总经理</option>
				<option value="2" <c:if test="${user.userInfo.userLevel ==2}">selected="selected"</c:if>>副总</option>
				<option value="3" <c:if test="${user.userInfo.userLevel ==3}">selected="selected"</c:if>>部门经理</option>
				<option value="4" <c:if test="${user.userInfo.userLevel ==4}">selected="selected"</c:if>>普通用户</option>
			</select>
		</td>
		<td>排序号:</td>
		<td><input  type="text" name="userInfo.orderNo" value="${user.userInfo.orderNo}"/></td>

	</tr>--%>
	<tr class="odd">
		<td>状态:</td>
		<td>

		
			<input type="radio" name="hhUserStatus" value="1" <c:if test="${user.hhUserStatus ==1}">checked="checked"</c:if> />启用
			<input type="radio" name="hhUserStatus" value="0" <c:if test="${user.hhUserStatus ==0}">checked="checked"</c:if>/>停用

		</td>
	</tr>
	<%--<tr class="odd">
		<td>备注信息:</td>
		<td colspan="3">

			<textarea style="height:80px;width:100%" name="userInfo.remark">${user.userInfo.remark}</textarea>
		</td>
	</tr>	--%>


</table>
</div>
 
</div>
 
 
</form>
</body>
</html>

