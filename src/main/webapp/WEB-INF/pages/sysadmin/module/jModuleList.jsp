<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>模块列表</title>
</head>

<body>
<form name="icform" method="post">




<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>

	<li id="new"><a href="#" onclick="formSubmit('tocreate','_self');this.blur();">新增</a></li>
	<li id="delete"><a href="#" onclick="formSubmit('delete','_self');this.blur();">删除</a></li>
	<li id="new"><a href="#" onclick="formSubmit('start','_self');this.blur();">启用</a></li>
	<li id="new"><a href="#" onclick="formSubmit('stop','_self');this.blur();">停用</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="../../staticfile/skin/default/images/icon/currency_yen.png"/>
   	模块列表
  </div> 
  
<div>




		<div>


			<div class="eXtremeTable">
				<table id="ec_table" class="tableRegion" width="98%">
					<thead>
						<tr>
							<td class="tableHeader"><input type="checkbox" name="selid"
								onclick="checkAll('hhModuleId',this)"></td>
							<td class="tableHeader">序号</td>
							<td class="tableHeader">模块名称</td>
							<td class="tableHeader">模块类型</td>
							<td class="tableHeader">备注信息</td>
							<td class="tableHeader">排序号</td>
							<td class="tableHeader">状态</td>
						</tr>
					</thead>
					<tbody class="tableBody">

						<c:forEach items="${hhModuleList}" var="h" varStatus="status">
							<tr class="odd" onmouseover="this.className='highlight'"
								onmouseout="this.className='odd'">
								<td><input type="checkbox" name="hhModuleId"
									value="${h.hhModuleId}" /></td>
								<td>${status.index+1}</td>
								<td>${h.hhModuleName}</td>
								<td><c:if test="${h.hhModuleCtype==1}">主菜单</c:if> <c:if
										test="${h.hhModuleCtype==2}">左侧菜单</c:if> <c:if test="${h.hhModuleCtype==3}">按钮</c:if>
								</td>
								<td>${h.hhModuleRemarks}</td>
								<td>${h.hhModuleOrderNo}</td>
								<td><c:if test="${h.hhModuleState==1}">
										<a href="stop?hhModuleId=${h.hhModuleId}"><font color="green">启用</font></a>
									</c:if> <c:if test="${h.hhModuleState==0}">
										<a href="start?hhModuleId=${h.hhModuleId}"><font color="red">停用</font></a>
									</c:if></td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>

		</div>


	</form>


 
</div>
 
 
</form>

</body>
</html>

