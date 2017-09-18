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

		/*$(function(){
			$("#myform").validate({
				rules:{
					hhUserUsername:{
						required:"true",
						minlength:2,
						maxlength:10
					},
					hhUserPassword:{
						required:"true",
						minlength:6
					},
					hhUserName:{
						required:"true"
					}


				},
				message:{
					hhUserUsername:{
						required:"用户名不能为空",
						minlength:"用户名长度为2~10个字符",
						maxlength:"用户名长度为2~10个字符"
					},
					hhUserPassword:{
						required:"密码不能为空",
						minlength:"请输入不小于六位字符"
					},
					hhUserName:{
						required:"姓名不能为空"
					}
				},
				errorPlacement:function (error,element) {
					error.appendTo(element.parent());
				}


			});
			$(".hhUserUsername").validate({
				onfocusout:false
			})

		});*/

		$.validator.setDefaults({
			submitHandler: function() {
				alert("提交事件!");
			}
		});
		$(function(){
				rules:{ //配置验证规则，key就是被验证的dom对象，value就是调用验证的方法(也是json格式)
					hhUserUsername:{
						required:true, //必填。如果验证方法不需要参数，则配置为true
						rangelength:[1,12]

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
					hhDeptStarttime:{
						required:true


					},
					hhDeptStoptime:{
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
						rangelength:$.validator.format("用户名长度为{0}-{1}个字符")
						//remote:"该用户名已存在！"
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
						required:"请输入年龄",
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
					hhDeptStarttime:{
						required:"请输入开班时间"

					},
					hhDeptStoptime:{
						required:"请输入结课时间"

					},
					hhUserCardid:{
						required:"请输入结课时间"

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
			/*$("#save").click(function () {
				if($("#myform").validate()){
					$("#myform").submit();
				}


			});*/

		});






	</script>
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


		<fieldset>

	<legend>管理员录入用户信息</legend>
			<div class="eXtremeTable">
				<%--<table id="ec_table" class="tableRegion" width="98%">--%>
					<div class="item">
						<label for="hhUserUsername" class="item-label">用户名:</label>
						<input type="text" id="hhUserUsername" name="hhUserUsername" class="item-text"
							   autocomplete="off" tip="请输入用户名"/>
					</div>
					<%--<tr class="odd" >

						<td>用户名:</td>
						<td><input type="text" name="hhUserUsername" placeholder="请输入用户名" id="hhUserUsername"/></td>
						<!-- spring自动映射传参依赖name属性，与pojo对象要严格一致 -->
						<td>密&nbsp;&nbsp;&nbsp;&nbsp;码:</td>
						<td><input type="password" name="hhUserPassword" placeholder="请输入密码"/></td>
					</tr>--%>
					<div class="item">
						<label for="hhUserPassword" class="item-label">密码:</label>
						<input type="password" id="hhUserPassword" name="hhUserPassword" class="item-text"
							    tip="长度为6-16个字符"/>
					</div>
					<div class="item">
						<label for="hhUserPassword2" class="item-label">确认密码:</label>
						<input type="password" name="hhUserPassword2" class="item-text"  id="hhUserPassword2"/>
					</div>
					<div class="item">
						<label for="hhUserName" class="item-label">真实姓名:</label>
						<input type="text" id="hhUserName" name="hhUserName" class="item-text"  tip="请输入真实姓名"/>
					</div>
					<%--<tr class="odd" >
						<td>真实姓名:</td>
						<td><input type="text" name="hhUserName" placeholder="请输入您的真实姓名"/></td>
					</tr>--%>
				<%--	<div>
					<legend>性别</legend>
					<label for="gender_male" class="item-label">
						<input  type="radio" id="gender_male" value="男" name="hhUserSex" >
						男
					</label>
					<label for="gender_female" class="item-label">
						<input  type="radio" id="gender_female" value="女" name="hhUserSex">
						女
					</label>
					</div>--%>
					<div class="item">
						<label  class="item-label">性别:</label>
						<input type="radio"   name="hhUserSex"   tip="请输入性别" value="男"/>男
						<input type="radio"   name="hhUserSex"   tip="请输入性别" value="女"/>女
					</div>
					<%--<tr class="odd">
						<td>性别:</td>
						<td><input type="radio" name="hhUserSex" value="男" />男
							<input type="radio" name="hhUserSex" value="女" />女
					</tr>--%>
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
					<%--<tr class="odd">
						<td>年龄:</td>
						<td><input type="text" name="hhUserAge" /></td>
					</tr>--%>
					<%--<tr class="odd">
						<td>所学课程:</td>
						<td><select name="hhDeptCourse"  style="width: 120px;" >
							<option value="">---请选择---</option>

							<c:forEach items="${deptList}" var="d">
								<option value="${d.hhDeptId}">${d.hhDeptCourse}</option>
							</c:forEach>
						</select></td>
						<td>所在班级:</td>
						<td><select name="hhDeptId"  style="width: 120px;" >
								<option value="">---请选择---</option>

								<c:forEach items="${deptList}" var="d">
									<option value="${d.hhDeptId}">${d.hhDeptNum}</option>
								</c:forEach>
						</select></td>

						<td>教室号:</td>
						<td><select name="hhDeptRoomnum" style="width: 120px;" >
							<option value="">---请选择---</option>

							<c:forEach items="${deptList}" var="d">
								<option value="${d.hhDeptId}">${d.hhDeptRoomnum}</option>
							</c:forEach>
						</select></td>


					</tr>--%>
					<div class="item">
						<label for="hhDeptStarttime" class="item-label">开班时间:</label>
						<input type="text" id="hhDeptStarttime" name="hhDeptStarttime " class="item-text"
							   autocomplete="off" tip="请输入开班时间" onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});">
					</div>
					<div class="item">
						<label for="hhDeptStoptime" class="item-label">结课时间:</label>
						<input type="text" id="hhDeptStoptime" name="hhDeptStoptime" class="item-text"
							   autocomplete="off" tip="请输入结课时间" onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});">
					</div>
					<%--<tr class="odd">
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

					</tr>--%>
					<div class="item">
						<label for="hhUserCardid" class="item-label">身份证号:</label>
						<input type="text" id="hhUserCardid" name="hhUserCardid" class="item-text"/>
					</div>
					<%--<tr class="odd">
						<td>身份证号:</td>
						<td><input type="text" name="hhUserCardid" /></td>--%>
						<%--<td>上级领导:</td>
						<td><select name="userInfo.manager.userInfoId"
							style="width: 120px;">
								<option value="">请选择/无上级</option>
								<c:forEach items="${parentList}" var="p">
									<option value="${p.userInfoId}">${p.name}</option>
								</c:forEach>
						</select></td>--%>
					<%--</tr>--%>
					<div class="item">
						<label for="phone" class="item-label">电话号码:</label>
						<input type="text" id="phone" name="hhUserTel" class="item-text" />
					</div>
				<%--	<tr class="odd">

						<td>联系方式:</td>
						<td><input type="text" name="hhUserTel" /></td>
					</tr>--%>
					<div class="item">
						<label for="hhUserStatus" class="item-label">状态:</label>
						<input type="radio"  name="hhUserStatus"  tip="选择用户状态" value="1"/>启用
						<input type="radio"  name="hhUserStatus"  tip="选择用户状态" value="0"/>停用
					</div>
					<%--<tr class="odd">
						<td>状态:</td>
						<td><input type="radio" name="hhUserStatus" value="1" />启用
							<input type="radio" name="hhUserStatus" value="0" />停用
						</td>
					</tr>--%>
				<%--</table>--%>
			</div>


		</fieldset>

</form>

</body>
</html>

