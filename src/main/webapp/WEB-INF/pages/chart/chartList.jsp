<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>图表列表</title>
</head>

<body>
<form name="icform" method="post">
   
  <div class="textbox-title">
	<img src="../staticfile/skin/default/images/icon/currency_yen.png"/>
    图表列表
  </div> 
  
<div>

<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<tr class="odd">
		<td>查询开始日期:</td>
		<td>
			<input type="text" style="width:121px;" name="startDate"
				   onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
		</td>
	</tr>
	<tr class="odd">
		<td>查询结束日期:</td>
		<td>
			<input type="text" style="width:121px;" name="endDate"
				   onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
		</td>
	</tr>
</table>
</div>
 
</div>

	<div id="menubar">
		<div id="middleMenubar">
			<div id="innerMenubar">
				<div id="navMenubar">
					<ul>
						<li id="chartMultiX" style="width: 200px;font: bold 20px arial,sans-serif;"><a href="#" onclick="formSubmit('chartMultiX','_self');this.blur();">房源概况图</a></li>
						<li id="chartRadar"  style="width: 200px;font: bold 20px arial,sans-serif;"><a href="#" onclick="formSubmit('chartRadar','_self');this.blur();">租金占比图</a></li>
						<li id="chartBars"  style="width: 200px;font: bold 20px arial,sans-serif;"><a href="#" onclick="formSubmit('chartBars','_self');this.blur();">房屋来源占比图</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
 
</form>
</body>
</html>

