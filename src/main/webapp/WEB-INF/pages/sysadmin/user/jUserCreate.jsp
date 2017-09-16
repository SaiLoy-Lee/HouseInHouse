<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%--<%--%>
	<%--String path = request.getContextPath();--%>
	<%--String basePath = request.getScheme() + "://" + request.getServerName() + ":"--%>
			<%--+ request.getServerPort() + path + "/";--%>
<%--%>--%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%--<base href="<%=basePath%>">--%>
	<title>用户新增</title>
	<%--<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="../../staticfile/js/jquery-validation-1.14.0/lib/jquery.js"></script>
	<script type="text/javascript" src="../../staticfile/js/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
	<script type="text/javascript" src="../../staticfile/js/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
	<script type="text/javascript">

	$(function()){
		$("#myform").validate({
			rules:{
				hhUserUsername:{
					required:true,
					rangelength:[3,12],
					remote:{
						url:"ajax_check.action",
						type:"post"
					}
				},
				hhUserPassWord:{
					required:true,
					rangelength:[6,12]
				},
				hhUserName:{
					required:true
				},
				hhUserSex:{
					required:true
				},
				hhUserAge:{
					required:true
				},
				hhDeptCourse:{
					required:true
				},
				hhDeptId:{
					required:true
				},
				hhDeptRoomnum:{
					required:true
				},
				hhDeptStarttime:{
					required:true,
					date:true
				},
				hhDeptStoptime:{
					required:true,
					date:true
				},
				hhUserCardid:{
					required:true
				},
				hhUserTel:{
					required:true
				},
				hhUserStatus:{
					required:true
				}
			},
			message:{
				hhUserUsername:{
					requierd:"请输入用户名",
					rangelength:$validate.format("用户名长度必须在3~12")
				},
				hhUserPassWord:{
					requierd:"请输入密码",
					rangelength:$validate.format("密码长度必须在6~12")
				}

			}
		}

		)
	}
</script>--%>
</head>

<body>
	<form  id="myform" name="icform" method="post">

		<div id="menubar">
			<div id="middleMenubar">
				<div id="innerMenubar">
					<div id="navMenubar">
						<ul>
							<li id="save"><a href="#"
								onclick="formSubmit('save','_self');this.blur();">保存</a></li>
							<li id="back"><a href="#" onclick=" window.history.go(-1)">返回</a></li>

						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="textbox-title">
			<img src="../../staticfile/skin/default/images/icon/currency_yen.png" />
			用户新增
		</div>

		<div>


			<div class="eXtremeTable">
				<table id="ec_table" class="tableRegion" width="98%">
					<tr class="odd" >
						<td>用户名:</td>
						<td><input type="text" name="hhUserUsername" /></td>
						<!-- spring自动映射传参依赖name属性，与pojo对象要严格一致 -->
						<td>密&nbsp;&nbsp;&nbsp;&nbsp;码:</td>
						<td><input type="password" name="hhUserPassword" /></td>
					</tr>
					<tr class="odd" >
						<td>真实姓名:</td>
						<td><input type="text" name="hhUserName" /></td>
					</tr>
					<tr class="odd">
						<td>性别:</td>
						<td><input type="radio" name="hhUserSex" value="男" />男
							<input type="radio" name="hhUserSex" value="女" />女
					</tr>
					<tr class="odd">
						<td>年龄:</td>
						<td><input type="text" name="hhUserAge" /></td>
					</tr>
					<tr class="odd">
						<td>所学课程:</td>
						<td><select name="hhDeptCourse" style="width: 120px;">
							<option value="">---请选择---</option>

							<c:forEach items="${deptList}" var="d">
								<option value="${d.hhDeptId}">${d.hhDeptCourse}</option>
							</c:forEach>
						</select></td>
						<td>所在班级:</td>
						<td><select name="hhDeptId" style="width: 120px;">
								<option value="">---请选择---</option>

								<c:forEach items="${deptList}" var="d">
									<option value="${d.hhDeptId}">${d.hhDeptNum}</option>
								</c:forEach>
						</select></td>

						<td>教室号:</td>
						<td><select name="hhDeptRoomnum" style="width: 120px;">
							<option value="">---请选择---</option>

							<c:forEach items="${deptList}" var="d">
								<option value="${d.hhDeptId}">${d.hhDeptRoomnum}</option>
							</c:forEach>
						</select></td>


					</tr>
					<tr class="odd">
						<td>开班时间:</td>
						<td>
							<input type="text" style="width: 110px;" name="dept.hhDeptStarttime"
								   onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});" />
						</td>
						<td>结课时间:</td>
						<td>
							<input type="text" style="width: 110px;" name="dept.hhDeptStoptime"
								   onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});" />
						</td>

					</tr>
					<tr class="odd">
						<td>身份证号:</td>
						<td><input type="text" name="hhUserCardid" /></td>
						<%--<td>上级领导:</td>
						<td><select name="userInfo.manager.userInfoId"
							style="width: 120px;">
								<option value="">请选择/无上级</option>
								<c:forEach items="${parentList}" var="p">
									<option value="${p.userInfoId}">${p.name}</option>
								</c:forEach>
						</select></td>--%>
					</tr>
					<tr class="odd">

						<td>联系方式:</td>
						<td><input type="text" name="hhUserTel" /></td>
					</tr>
					<tr class="odd">
						<td>状态:</td>
						<td><input type="radio" name="hhUserStatus" value="1" />启用
							<input type="radio" name="hhUserStatus" value="0" />停用
						</td>
					</tr>
				</table>
			</div>

		</div>


	</form>
</body>
</html>

