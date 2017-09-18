<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../baselist.jsp" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <link rel="stylesheet" rev="stylesheet" type="text/css" href="${ctx}/staticfile/skin/default/css/left.css"
          media="all"/>
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
            <div class="panel-title">
<shiro:hasPermission name="用户">
                个人中心
</shiro:hasPermission>
                <shiro:hasPermission name="管理员">
                    订单管理
                </shiro:hasPermission>
            </div>
            <div class="panel-content">
                <ul>
                    <shiro:hasPermission name="管理员">

                        <li><a href="${ctx}/personal/order/findList?status=1" onclick="linkHighlighted(this)" target="main"
                               id="aa_8">待签订单</a></li>
                        <li><a href="${ctx}/personal/order/findList?status=6" onclick="linkHighlighted(this)" target="main"
                               id="aa_9">退租订单</a></li>
                        <li><a href="${ctx}/personal/order/findList?status=2" onclick="linkHighlighted(this)" target="main"
                               id="aa_e">未通过订单</a></li>
                        <li><a href="${ctx}/personal/order/findList?status=3" onclick="linkHighlighted(this)" target="main"
                               id="aa_a">在期订单</a></li>
                        <li><a href="${ctx}/personal/order/findList?status=4" onclick="linkHighlighted(this)" target="main"
                               id="aa_b">过期订单</a></li>
                        <li><a href="${ctx}/personal/order/findList?status=12" onclick="linkHighlighted(this)" target="main"
                               id="aa_c">已删除订单</a></li>
                        <li><a href="${ctx}/personal/order/findList?status=0" onclick="linkHighlighted(this)" target="main"
                               id="aa_d">全部订单</a></li>
                    </shiro:hasPermission>

                    <shiro:hasPermission name="用户">
                        <li><a href="${ctx}/personal/order/list" onclick="linkHighlighted(this)" target="main"
                               id="aa_3">我的订单</a></li>
                        <li><a href="${ctx}/sysadmin/user/toview?hhUserId=${SessionUser.hhUserId}" onclick="linkHighlighted(this)"
                               target="main" id="aa_1">我的信息</a></li>
                        <li><a href="${ctx}/personal/export/exportAction_contractList" onclick="linkHighlighted(this)"
                               target="main" id="aa_4">房源中心</a></li>
                        <li><a href="${ctx}/house/houseFindAll" onclick="linkHighlighted(this)"
                               target="main" id="aa_5">发布房源</a></li>
                        <li><a href="${ctx}/personal/export/exportAction_ws" onclick="linkHighlighted(this)"
                               target="main" id="aa_6">我的留言</a></li>
                        <li><a href="${ctx}/personal/export/contractHisAction_list" onclick="linkHighlighted(this)"
                               target="main" id="aa_7">找朋友</a></li>
                        <li><a href="${ctx}/personal/order/toCreateOrder?hhUserId=8be3ec9c-3fc6-492e-a395-b6d1ef156177&hhHouseId=00597d53-21dd-402b-ba92-829d279683f2" onclick="linkHighlighted(this)"
                               target="main" id="aa_y">新增订单临时入口</a></li>
                    </shiro:hasPermission>
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
