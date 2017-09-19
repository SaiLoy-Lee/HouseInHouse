<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../base.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>模块介绍</title>
  	<link rel="stylesheet" rev="stylesheet" type="text/css" href="${ctx}/staticfile/skin/default/css/main.css" media="all"/>
</head>

<body>
<form>
<div class="textbox"></div>

	<div class="modelDiv">

        <table class="modelTable" cellspacing="1">
        	<tr>
        		<td colspan="2" class="modelTitle">房源模块介绍</td>
        	</tr>
        	<tr>
        		<td colspan="2" class="subModelTitle">房源概况：</td>
        	</tr>
        	<tr>
				<td class="model_intro_left">房源概况：</td>
				<td class="model_intro_right"> 数据来源：房屋发布的租金 、订单成交的租金	</br>	图解：反映了大部分人的消费趋向</td>
        	</tr>        	
			<tr>
				<td class="model_intro_left">租金占比图：</td>
				<td class="model_intro_right">数据来源：	房屋信息发布数量、订单成交入住数量</br>	  图解：当发布数量与入住数量的间隔越小的时候，说明房屋资源利用率越高，反之则越低</td>
        	</tr>        	
			<tr>
				<td class="model_intro_left" width="169">房屋来源占比图：</td>
				<td class="model_intro_right" width="81%">数据来源：房源发布信息【管理员/个人】</br>	  图解反映了房源信息来源情况 </td>
			</tr>
			
			<tfoot>
				<tr>
					<td colspan="2" class="tableFooter"></td>
				</tr>
			</tfoot>
        </table>
 
	</div>
</form>
</body>

</html>