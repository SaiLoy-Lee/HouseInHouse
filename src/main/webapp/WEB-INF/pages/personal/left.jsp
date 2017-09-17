<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../baselist.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <link rel="stylesheet" rev="stylesheet" type="text/css" href="${ctx}/staticfile/skin/default/css/left.css" media="all"/>
</head>
 
<body id="left_frame">
<div class="PositionFrame_black" id="PositionFrame"></div>
 
 
<!-- begin1  -->
<div id="sidebar" class="sidebar">
	<div class="sidebar_t">
		<div class="sidebar_t_l"></div>
		<div class="sidebar_t_c"></div>
		<div class="sidebar_t_r"></div>
	</div>
        <div class="panel">
        <div class="panel_icon"><img src="${ctx}/staticfile/skin/default/images/icon/cubes.png"/></div>
        <div class="panel-header">
        <div class="panel-title">个人中心</div>
        <div class="panel-content">
			<ul>
				<li><a href="${ctx}/sysadmin/user/view" onclick="linkHighlighted(this)" target="main" id="aa_1">我的信息</a></li>
				<li><a href="${ctx}/personal/order/list" onclick="linkHighlighted(this)" target="main" id="aa_1">我的订单</a></li>
				<li><a href="${ctx}/personal/export/exportAction_contractList" onclick="linkHighlighted(this)" target="main" id="aa_1">房源中心</a></li>
				<li><a href="${ctx}/personal/export/exportAction_list" onclick="linkHighlighted(this)" target="main" id="aa_1">发布房源</a></li>
				<li><a href="${ctx}/personal/export/exportAction_ws" onclick="linkHighlighted(this)" target="main" id="aa_1">我的留言</a></li>
				<li><a href="${ctx}/personal/export/contractHisAction_list" onclick="linkHighlighted(this)" target="main" id="aa_1">找朋友</a></li>
			</ul>
        </div>
        </div>
    </div>
    <div class="sidebar_t">
		<div class="sidebar_b_l"></div>
		<div class="sidebar_t_c"></div>
		<div class="sidebar_b_r"></div>
	</div>  
</div>	


</body>
</html>
