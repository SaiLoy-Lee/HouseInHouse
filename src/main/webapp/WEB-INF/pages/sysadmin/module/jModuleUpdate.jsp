
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>模块修改</title>
</head>

<body>
	<form name="icform" method="post">

		<div id="menubar">
			<div id="middleMenubar">
				<div id="innerMenubar">
					<div id="navMenubar">
						<ul>
							<li id="save"><a href="#"
								onclick="formSubmit('update','_self');this.blur();">保存</a></li>
							<li id="back"><a href="#" onclick=" window.history.go(-1)">返回</a></li>

						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="textbox-title">
			<img src="../../staticfile/skin/default/images/icon/currency_yen.png" />
			模块修改
		</div>

		<div>


			<div class="eXtremeTable">
				<table id="ec_table" class="tableRegion" width="98%">
					<tr class="odd">
						<td>模块名称:</td>
						<td><input type="text" name="hhModuleName" value = "${module.hhModuleName}"/></td>
						<td><input type="hidden" name="hhModuleId" value = "${module.hhModuleId}"/></td>
					</tr>
					<tr class="odd">
						<td>上级模块:</td>
						<td><select name="parentModule.hhModuleId" style="width: 120px;">
								<option value="0">无上级模块</option>
								<c:forEach items="${parentList}" var="p">
									<c:if test="${module.hhModuleId!=p.hhModuleId}">
										<option <c:if test="${p.hhModuleId == module.parentModule.hhModuleId}">selected="selected"</c:if>  value="${p.hhModuleId}">${p.hhModuleName}</option>
									</c:if>
								</c:forEach>
						</select></td>
					</tr>
					<tr class="odd">
						<td>展现类型:</td>
						<td>
							<select name="hhModuleCtype" style="width: 120px;">
									<option value="1" <c:if test="${module.hhModuleCtype=='1'}">selected="selected"</c:if>>主菜单</option>
									<option value="2" <c:if test="${module.hhModuleCtype=='2'}">selected="selected"</c:if>>左侧菜单</option>
									<option value="3" <c:if test="${module.hhModuleCtype=='3'}">selected="selected"</c:if>>按钮</option>
							</select>
						</td>
					</tr>
					<tr class="odd">
						<td>排序号:</td>
						<td><input type="text" name="hhModuleOrderNo" value = "${module.hhModuleOrderNo}"/></td>
					</tr>
					<tr class="odd">
						<td>状态:</td>
						<td>
							<input type="radio" name="hhModuleState" value="1" <c:if test="${module.hhModuleState == '1'}">checked="checked"</c:if> />启用
							<input type="radio" name="hhModuleState" value="0" <c:if test="${module.hhModuleState == '0'}">checked="checked"</c:if> />停用
						</td>
					</tr>
					<tr class="odd">
						<td>备注信息:</td>
						<td colspan="3"><textarea style="height: 80px; width: 90%"
								name="hhModuleRemarks">${module.hhModuleRemarks}</textarea></td>
					</tr>
				</table>
			</div>

		</div>


	</form>
</body>
</html>


