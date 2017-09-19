<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<link href="${ctx}/staticfile/css/style.css" rel='stylesheet' type='text/css' />
	<script src="${ctx}/staticfile/js/jquery.min.js"></script>
</head>
<body>
<div class="login-form">
	<div class="close"> </div>
		<div class="head-info">
			<label class="lbl-1"> </label>
			<label class="lbl-2"> </label>
			<label class="lbl-3"> </label>
		</div>
			<div class="clear"> </div>
	<div class="avtar">
		<img src="${ctx}/staticfile/images/avtar.png" />
	</div>
			<form method="post">
					<input type="text" value="" name="userName" id="userName" onFocus="this.select();" title="请您输入用户名"/>
						<div class="key">
						<input type="password" value="" name="password" id="password" onfocus="$('#ts').css('display','none');this.select();"
						onKeyDown="javascript:if(event.keyCode==13){ submitFind(); }" title="请您输入密码"/>
							</div>
			</form>

	<div class="msgtip">
		<c:if test="${!empty errorInfo}">
			${errorInfo}
		</c:if>
	</div>
	<div class="signin">
		<input  stype="height:70px padding:0" type="submit" value="Login" onclick="formSubmit('${ctx}/login.action','_self');" >
	</div>
</div>
</body>
</html>