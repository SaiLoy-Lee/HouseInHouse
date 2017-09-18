<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>角色列表</title>
</head>

<body>
<form name="icform" method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
	<li id="view"><a href="#" onclick="formSubmit('toview','_self');this.blur();">查看</a></li>
	<li id="new"><a href="#" onclick="formSubmit('tocreate','_self');this.blur();">新增</a></li>
	<li id="update"><a href="#" onclick="formSubmit('toupdate','_self');this.blur();">修改</a></li>
	<li id="delete"><a href="#" onclick="formSubmit('delete','_self');this.blur();">删除</a></li>
	<li id="new"><a href="#" onclick="formSubmit('roleModule','_self');this.blur();">模块</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="../../staticfile/skin/default/images/icon/currency_yen.png"/>
    角色列表
  </div> 
  
<div>



		<div>


			<div class="eXtremeTable">
				<table id="ec_table" class="tableRegion" width="98%">
					<thead>
						<tr>
							<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('hhRoleId',this)"></td>
							<td class="tableHeader">序号</td>
							<td class="tableHeader">角色名</td>
							<td class="tableHeader">备注信息</td>
							<td class="tableHeader">排序号</td>
							<td class="tableHeader">修改时间</td>
							<td class="tableHeader">状态</td>
						</tr>
					</thead>
					<tbody class="tableBody">

						<c:forEach items="${hhRoleList}" var="h" varStatus="status">
							<tr class="odd" onmouseover="this.className='highlight'"
								onmouseout="this.className='odd'">
								<td><input type="checkbox" name="hhRoleId"
									value="${h.hhRoleId}" /></td>
								<td>${status.index+1}</td>
								<td>${h.hhRoleName}</td>
								<td>${h.hhRoleRemarks}</td>
								<td>${h.hhRoleOrderNo}</td>
								<td><fmt:formatDate value="${h.updateTime}"
										pattern="yyyy-MM-dd" /></td>
								<td>
								<c:if test="${h.hhRoleStatus==1}"><a href="stop?hhRoleId=${h.hhRoleId}"><font color="green">启用</font> </a> </c:if>
									<c:if test="${h.hhRoleStatus==0}"><a href="start?hhRoleId=${h.hhRoleId}"><font color="red">停用</font> </a> </c:if>
								</td>
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

