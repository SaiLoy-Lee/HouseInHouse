<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户查看</title>
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
			<img src="../../staticfile/skin/default/images/icon/currency_yen.png" />
			用户查看
		</div>

		<div>


			<div class="eXtremeTable">
				<table id="ec_table" class="tableRegion" width="98%">
					<tr class="odd">
						<td>用户名:</td>
						<td><input readonly="readonly" readonly="readonly" type="text" name="hhUserUsername"
							value="${user.hhUserUsername }" />
							<input readonly="readonly" type="hidden" name="hhUserId"
							value="${user.hhUserId }" /></td>
						<!-- spring自动映射传参依赖name属性，与pojo对象要严格一致 -->
						<td>密&nbsp;&nbsp;&nbsp;&nbsp;码:</td>
						<td><input readonly="readonly" type="password" name="passWord" /></td>
					</tr>
					<tr class="odd">
						<td>真实姓名:</td>
						<td><input readonly="readonly" type="text" name="user.hhUserName"
								   value="${user.hhUserName}" /></td>
					</tr>
					<tr class="odd">
						<td>性别:</td>
						<td><input disabled="disabled" readonly="readonly" type="radio" name="hhUserSex" value="男"
								   <c:if test="${user.hhUserSex=='男'}">checked="checked"</c:if> />男
							<input disabled="disabled" readonly="readonly" type="radio" name="userInfo.gender" value="女"
								   <c:if test="${user.hhUserSex=='女'}">checked="checked"</c:if> />女
						</td>
					</tr>
					<tr class="odd">
						<td>年龄:</td>
						<td><input readonly="readonly" type="text" name="user.hhUserName"
								   value="${user.hhUserAge}" /></td>
					</tr>
					<tr class="odd">
						<td>身份证号:</td>
						<td><input readonly="readonly" type="text" name="hhUserCardid"
								   value="${user.hhUserCardid}" /></td>
						<%--<td>上级领导:</td>
						<td><select disabled="disabled" name="userInfo.manager.userInfoId"
							style="width: 120px;">
								<option value="">请选择/无上级</option>
								<c:forEach items="${parentList}" var="p">
									<c:if test="${ p.userInfoId != user.userId }">
										<option value="${p.userInfoId}"
											<c:if test="${user.userInfo.manager.userInfoId == p.userInfoId}">selected="selected"</c:if>>${p.name}</option>
									</c:if>
								</c:forEach>
						</select></td>--%>
					</tr>
					<tr class="odd">
						<td>课程:</td>
						<td><select disabled="disabled" name="dept.hhDeptCourse" style="width: 120px;">
							<option value="">---请选择---</option>

							<c:forEach items="${deptList}" var="d">
								<option value="${d.hhDeptId}"
										<c:if test="${d.hhDeptId == user.dept.hhDeptId}">selected="selected"</c:if>>${d.hhDeptCourse}</option>
							</c:forEach>
						</select></td>
						<td>班级:</td>
						<td><select disabled="disabled" name="dept.hhDeptId" style="width: 120px;">
								<option value="">---请选择---</option>

								<c:forEach items="${deptList}" var="d">
									<option value="${d.hhDeptId}"
										<c:if test="${d.hhDeptId == user.dept.hhDeptId}">selected="selected"</c:if>>${d.hhDeptNum}</option>
								</c:forEach>
						</select></td>
						<td>教室:</td>
						<td><select disabled="disabled" name="dept.hhDeptRoomnum" style="width: 120px;">
							<option value="">---请选择---</option>

							<c:forEach items="${deptList}" var="d">
								<option value="${d.hhDeptId}"
										<c:if test="${d.hhDeptId == user.dept.hhDeptId}">selected="selected"</c:if>>${d.hhDeptRoomnum}</option>
							</c:forEach>
						</select></td>

					</tr>

					<tr class="odd">
						<td>开班时间:</td>
						<td>
							<!-- <input readonly="readonly" type="text" name="userInfo.joinDate" /> --> 
							<input readonly="readonly" type="text" style="width: 110px;" name="hhUserStarttime"
							 value=' <fmt:formatDate value="${user.hhUserStarttime}"  pattern="yyyy-MM-dd" />'
							 />
						</td>
						<td>结课时间:</td>
						<td>
							<!-- <input readonly="readonly" type="text" name="userInfo.joinDate" /> -->
							<input readonly="readonly" type="text" style="width: 110px;" name="hhUserStoptime"
								   value=' <fmt:formatDate value="${user.hhUserStoptime}"  pattern="yyyy-MM-dd" />'
							/>
						</td>
					</tr>

					<tr class="odd">
						<td>电话号码:</td>
						<td><input readonly="readonly" type="text" name="hhUserTel"
							value="${user.hhUserTel}" /></td>
					</tr>
					<%--<tr class="odd">
						<td>用户级别:</td>
						<td><select disabled="disabled" disabled="disabled" name="userInfo.userLevel">
								<option value="1"
									<c:if test="${user.userInfo.userLevel=='1'}">selected="selected"</c:if>>总经理</option>
								<option value="2"
									<c:if test="${user.userInfo.userLevel=='2'}">selected="selected"</c:if>>副总</option>
								<option value="3"
									<c:if test="${user.userInfo.userLevel=='3'}">selected="selected"</c:if>>部门经理</option>
								<option value="4"
									<c:if test="${user.userInfo.userLevel=='4'}">selected="selected"</c:if>>普通用户</option>
						</select></td>
						<td>排序号:</td>
						<td><input readonly="readonly" type="text" name="userInfo.orderNo"
							value="${user.userInfo.orderNo}" /></td>
					</tr>--%>
					<tr class="odd">
						<td>状态:</td>
						<td><input readonly="readonly" type="radio" name="hhUserStatus" value="1"
							<c:if test="${user.hhUserStatus==1}">checked="checked"</c:if> />启用
							<input readonly="readonly" type="radio" name="hhUserStatus" value="0"
							<c:if test="${user.hhUserStatus==0}">checked="checked"</c:if> />停用</td>
					</tr>
					<%--<tr class="odd">
						<td>备注信息:</td>
						<td colspan="3"><textarea readonly="readonly" style="height: 80px; width: 90%"
								name="userInfo.remark">${user.userInfo.remark}</textarea></td>
					</tr>--%>
				</table>
			</div>

		</div>


	</form>
</body>
</html>

