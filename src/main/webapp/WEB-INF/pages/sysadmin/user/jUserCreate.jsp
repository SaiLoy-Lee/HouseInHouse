<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>

	<title>用户新增</title>
	<link rel="stylesheet" href="${ctx}/staticfile/css/form.css" />
	<script type="text/javascript" src="${ctx}/staticfile/js/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="${ctx}/staticfile/js/jquery.validate.js"></script>
	<script type="text/javascript" src="${ctx}/staticfile/js/jquery.validate.extend.js"></script>
	<script type="text/javascript" src="${ctx}/staticfile/js/additional-methods.js"></script>
	<script type="text/javascript">

		$(function(){
			$("#myform").validate({
				onfocusout: function(element) { $(element).valid(); },
				rules:{
					hhUserUsername:{
						required:true, //必填。如果验证方法不需要参数，则配置为true
						rangelength:[1,12],
						remote:{
							url:"checkUsernameAjax.action",
							type:"post",
							DataType:"json",
							data:{hhUserUsername:function () {
								return $("#hhUserUsername").val();
							}}
						}
					},
					hhUserPassword:{
						required:true,
						rangelength:[6,16]
					},
					hhUserPassword2:{
						required:true,
						equalTo:'#hhUserPassword'
					},
					hhUserName:{
						required:true
					},
					hhUserSex:{
						required:true
					},
					hhUserAge:{
						required:true,
						number:true
					},
					hhDeptCourse:{
						required:true
					},
					hhDeptNum:{
						required:true
					},
					hhDeptRoomnum:{
						required:true
					},

					hhUserCardid:{
						required:true
					},
					hhUserTel:{
						required:true,
						phone:true
					},
					hhUserStatus:{
						required:true
					}
				},
				messages:{
					hhUserUsername:{
						required:"请输入用户名",
						rangelength:$.validator.format("用户名长度为{0}-{1}个字符"),
						remote:"用户名已存在"
					},
					hhUserPassword:{
						required:"请输入密码",
						rangelength:$.validator.format("密码长度为{0}-{1}个字符")
					},
					hhUserPassword2:{
						required:"请再次输入密码",
						equalTo:"两次密码必须一致" //表示和id="spass"的值相同
					},
					hhUserName:{
						required:"请输入真实姓名"
					},
					hhUserSex:{
						required:"请输入性别"
					},
					hhUserAge:{
						required:"请输入年龄"
					},
					hhDeptCourse:{
						required:"请选择课程"
					},
					hhDeptNum:{
						required:"请选择班级"
					},
					hhDeptRoomnum:{
						required:"请选择教室"
					},
					hhUserCardid:{
						required:"请输入身份证号"
					},
					hhUserTel:{
						required:"请输入电话号码",
						number:"电话号码格式不正确"
					},
					hhUserStatus:{
						required:"请选择用户状态"
					}
				}
		 	});
			$("#btn").click(function(){
				if($("#myform").valid()){

					formSubmit('save','_self');
					this.blur();
				}
			});
		})





	</script>
</head>

<body>

	<form  id="myform" name="icform" method="post">

		<div id="menubar">
			<div id="middleMenubar">
				<div id="innerMenubar">
					<div id="navMenubar">
						<ul>
							<li id="save"><a id="btn" href="#">保存</a></li>
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


		<fieldset>

	<legend>管理员录入用户信息</legend>
			<div class="eXtremeTable">


					<div class="item">
						<label for="hhUserUsername" class="item-label">用户名:</label>
						<input type="text" id="hhUserUsername" name="hhUserUsername" class="item-text"
							   autocomplete="off" />
					</div>


					<div class="item">
						<label for="hhUserPassword" class="item-label">密码:</label>
						<input type="password" id="hhUserPassword" name="hhUserPassword" class="item-text"
							    />
					</div>
					<div class="item">
						<label for="hhUserPassword2" class="item-label">确认密码:</label>
						<input type="password" name="hhUserPassword2" class="item-text"  id="hhUserPassword2"/>
					</div>
					<div class="item">
						<label for="hhUserName" class="item-label">真实姓名:</label>
						<input type="text" id="hhUserName" name="hhUserName" class="item-text"  />
					</div>
					<div class="item">
						<label  class="item-label">性别:</label>
						<input type="radio"   name="hhUserSex"    value="男"/>男
						<input type="radio"   name="hhUserSex"    value="女"/>女
					</div>
					<div class="item">
						<label for="hhUserAge" class="item-label">年龄:</label>
						<input type="text" id="hhUserAge" name="hhUserAge" class="item-text" >
					</div>
					<div class="item">
						<label for="hhDeptCourse" class="item-label">所学课程:</label>
						<select id="hhDeptCourse" name="hhDeptCourse" class="item-select">
							<option value="">---请选择---</option>
							<c:forEach items="${deptList}" var="d">
								<option value="${d.hhDeptId}">${d.hhDeptCourse}</option>
							</c:forEach>
						</select>
					</div>
					<div class="item">
						<label for="hhDeptNum" class="item-label">班级:</label>
						<select id="hhDeptNum" name="hhDeptNum" class="item-select">
							<option value="">---请选择---</option>
							<c:forEach items="${deptList}" var="d">
								<option value="${d.hhDeptId}">${d.hhDeptNum}</option>
							</c:forEach>
						</select>
					</div>
					<div class="item">
						<label for="hhDeptRoomnum" class="item-label">教室:</label>
						<select id="hhDeptRoomnum" name="hhDeptRoomnum" class="item-select">
							<option value="">---请选择---</option>
							<c:forEach items="${deptList}" var="d">
								<option value="${d.hhDeptId}">${d.hhDeptRoomnum}</option>
							</c:forEach>
						</select>
					</div>
					<div class="item">
						<label for="hhUserCardid" class="item-label">身份证号:</label>
						<input type="text" id="hhUserCardid" name="hhUserCardid" class="item-text"/>
					</div>
					<div class="item">
						<label for="phone" class="item-label">电话号码:</label>
						<input type="text" id="phone" name="hhUserTel" class="item-text" />
					</div>
					<div class="item">
						<label  class="item-label">状态:</label>
						<input type="radio"  name="hhUserStatus"   value="1"/>启用
						<input type="radio"  name="hhUserStatus"   value="0"/>停用
					</div>
			</div>


		</fieldset>

</form>

</body>
</html>

