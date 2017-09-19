<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
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
        <div class="panel_icon"><img src="${ctx}/staticfile/skin/default/images/icon/user1_lock.png"/></div>
        <div class="panel-header">
        <div class="panel-content">
			<ul>
					<li><a href="${ctx}/chart/chartMultiX" onclick="linkHighlighted(this)" target="main" id="aa_1">房源概况图</a></li>
					<li><a href="${ctx}/chart/chartRadar" onclick="linkHighlighted(this)" target="main" id="aa_1">租金占比图</a></li>
					<li><a href="${ctx}/chart/chartBars" onclick="linkHighlighted(this)" target="main" id="aa_1">房屋来源占比图</a></li>
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
