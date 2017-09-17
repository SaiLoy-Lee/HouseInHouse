<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>订单详情</title>
<style type="text/css">
.td {
	width: 120px;
	text-align: right;
}
table{
width:500px;
}
</style>
</head>

<body>
<form name="icform" method="post">
	<div id="menubar">
		<div id="middleMenubar">
			<div id="innerMenubar">
				<div id="navMenubar">
					<ul>
						<li id="save"><a href="#"
							onclick="formSubmit('create','_self');this.blur();">保存</a></li>
						<li id="back"><a href="#" onclick="window.history.go(-1)">返回</a></li>

					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="textbox-title">
		<img src="../../staticfile/skin/default/images/icon/currency_yen.png" />
		订单详情
	</div>

	<div>
		<div class="eXtremeTable">
			<table id="ec_table" class="tableRegion" width="98%">
				<tr class="odd">
					<td class="td">图片:</td>
					<td>${module.name }</td>					
				</tr>
				<tr class="odd">
					<td class="td">类型:</td>
					<td>
						<c:if test="${module.ctype==1 }">主菜单</c:if>
						<c:if test="${module.ctype==2 }">左菜单</c:if>
						<c:if test="${module.ctype==3 }">按钮</c:if>					
					</td>					
				</tr>
				<tr class="odd">
					<td class="td">上级模块:</td>
					<td>${module.parentModule.name }</td>					
				</tr>
				<tr class="odd">
					<td class="td">状态:</td>
					<td>
					<c:if test="${module.state==1 }">启用</c:if>
					<c:if test="${module.state==1 }">停用</c:if>
					</td>					
				</tr>
				<tr class="odd">
					<td class="td">排序号:</td>
					<td>${module.orderNo }</td>
				</tr>
				<tr class="odd">
					<td class="td">创建人:</td>
					<td>${module.createBy }</td>
				</tr>
				<tr class="odd">
					<td class="td">创建部门:</td>
					<td>${module.createDept }</td>
				</tr>
				<tr class="odd">
					<td class="td">创建时间:</td>
					<td><fmt:formatDate value="${module.createTime }" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr class="odd">
					<td class="td">修改人:</td>
					<td>${module.updateBy }</td>
				</tr>	
				<tr class="odd">
					<td class="td">修改时间:</td>
					<td><fmt:formatDate value="${module.updateTime }" pattern="yyyy-MM-dd"/></td>
				</tr>							
				<tr class="odd">
					<td class="td">备注:</td>
					<td >${module.remark }</td>
				</tr>				
			</table>
		</div>
	</div>
</form>
</body>
</html>

