<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<link rel="stylesheet" rev="stylesheet" type="text/css" href="${ctx}/staticfile/skin/default/css/default.css" media="all"/>
<link rel="stylesheet" rev="stylesheet" type="text/css" href="${ctx}/staticfile/skin/default/css/button.css" media="all"/>
<link rel="stylesheet" rev="stylesheet" type="text/css" href="${ctx}/staticfile/skin/default/css/table.css" media="all"/>
<script language="javascript" src="${ctx}/staticfile/js/common.js"></script>
<link rel="stylesheet" href="${ctx}/staticfile/css/form.css" />
<script type="text/javascript" src="${ctx}/staticfile/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${ctx}/staticfile/js/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/staticfile/js/jquery.validate.extend.js"></script>
<script type="text/javascript" src="${ctx}/staticfile/js/additional-methods.js"></script>

<style type="text/css">
	body, html{width: 100%;height: 100%;margin:0;font-family:"微软雅黑";font-size:14px;}
	#l-map{height:95%;width:100%;}
	#r-result{width:100%;}
	#findLngLat{width: 100%;height:250px}
</style>
<script type="text/javascript">
	$(function(){
		$("#myform").validate({
			onfocusout: function(element) { $(element).valid(); },
			rules: {
				hhHouseName: {
					required: true
					<%--remote:{
						url:"checkUsernameAjax.action",
						type:"post",
						DataType:"json",
						data:{hhUserUsername:function () {
						return $("#hhUserUsername").val();
							}}
						}--%>
				},
				hhHousePublishtime: {
					required: true
				},
				hhHouseMaxnum: {
					required: true,
					max: 'hhHouseMaxnum'
				},
				hhHouseResidenum: {
					required: true
				},
				hhHouseTelephone: {
					required: true,
					phone: true

				},
				hhHousePrice: {
					required: true,

				},
				hhHouseArea: {
					required: true
				},
				hhHouseStatus: {
					required: true
				},
				hhHouseImg: {
					required: true
				}

},
			messages:{
				hhHouseName:{
					required:"请输入小区名称",
				},
				hhHousePublishtime:{
					required:"请输入发布时间"

				},
				hhHouseMaxnum:{
					required:"请选择可住人数"
},
				hhHouseResidenum:{
					required:"请输入已住人数",
					max: $.validator.format( "已住人数不能大于{0}." )
				},
				hhHouseTelephone:{
					required:"请输入联系电话",
					phone:"请输入正确的联系电话"
				},
				hhHousePrice:{
					required:"请输入月租金"
				},
				hhHouseArea:{
					required:"请输入房屋面积"
				},
				hhHouseStatus:{
					required:"请选择房屋状态"
				},
				hhHouseImg:{
					required:"请上传房源图片"
				}

}
});
		$("#btn").click(function(){
			if($("#icform").valid()){

				formSubmit('save','_self');
				this.blur();
			}
		});
})
</script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=lmzmXu8zuVQieAs9UMqi3gY27pQFewEp"></script>

<head>
	<title>房源新增</title>
</head>

<body>
<form id="myform" name="icform" method="post">

	<div id="menubar">
		<div id="middleMenubar">
			<div id="innerMenubar">
				<div id="navMenubar">
					<ul>
						<li id="save"><a id="btn" href="#" onclick="formSubmit('save','_self');this.blur();">保存</a></li>
						<li id="back"><a href="#" onclick=" window.history.go(-1)">返回</a></li>

					</ul>
				</div>
			</div>
		</div>
	</div>

	<div class="textbox-title">
		<img src="../../staticfile/skin/default/images/icon/currency_yen.png"/>
		房源新增
	</div>

	<div>


		<div class="eXtremeTable" >
			<table id="ec_table" class="tableRegion" width="98%" >
				<tr class="odd">
					<td><span style="color: red">*</span>小区名称</td>
					<td><input  type="text" name="hhHouseName" id="hhHouseVillage" class="item-text"/></td>
					<td><span style="color: red">*</span>房屋名称</td>
					<td><input  type="text" name="hhHouseName" id="hhHouseName" class="item-text"/></td>
					<%--<td><span style="color: red">*</span>发布时间:</td>--%>
					<%--<td>--%>


							<%--<input type="text" id="hhDeptStarttime" name="hhHousePublishtime " class="item-text"--%>
								   <%--autocomplete="off"  onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});">--%>

			<%--			<input type="text" style="width:121px;" name="hhHousePublishtime"
							   onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>--%>

					</td>

				</tr>
				<tr class="odd">
					<td><span style="color: red">*</span>可住人数</td>
					<td><input  type="number" name="hhHouseMaxnum" id="hhHouseMaxnum"/></td>
					<td><span style="color: red">*</span>已住人数:</td>
					<td><input  type="text" name="hhHouseResidenum" id="hhHouseResidenum"/></td>
				</tr>
				<tr class="odd">
					<td>朝向</td>
					<td><input  type="text" name="hhHouseOrient" id="hhHouseOrient"/></td>
					<td>楼层:</td>
					<td><input  type="text" name="hhHouseFloor" id="hhHouseFloor"/></td>
				</tr>
				<tr class="odd">
					<td><span style="color: red">*</span>联系电话</td>
					<td><input  type="text" name="hhHouseTelephone" id="phone"/></td>
					<td><span style="color: red">*</span>月租金:</td>
					<td><input  type="number" name="hhHousePrice" id="hhHousePrice"/></td>
				</tr>
				<tr class="odd">
					<td><span style="color: red">*</span>面积</td>
					<td><input  type="text" name="hhHouseArea"/></td>
					<td>配套信息:</td>
					<td><input  type="text" name="hhHouseSupport"/></td>
				</tr>
				<tr class="odd">
					<td>发布人</td>
					<td>${UserName}
					</td>
					<td><span style="color: red">*</span>房源状态:</td>
					<td>
						<select id="hhHouseStatus" name="hhHouseStatus" class="item-select">
							<option value="0">上架房源</option>
							<option value="1">下架房源</option>
						</select>
					</td>
				</tr>
				<tr class="odd">
					<td><span style="color: red">*</span>上传房源图片</td>
					<td><input  type="file"  id="importFile" />
					</td>

				</tr>
				<tr class="odd">
					<td>房源描述:</td>
					<td colspan="3">
						<textarea style="height:80px;width:100%" name="hhHouseDescription"></textarea>
					</td>
				</tr>
				<tr class="odd"  style="margin-top: 30px;">
					<td><span style="color: red">*</span>经纬度</td>
					<td >
						<input readonly="readonly" style="width: 100%"  type="text" id="hhHouseLatlng" name="hhHouseLatlng"/>
					</td>
					<td><span style="color: red">*</span>详情地址:</td>
					<td><input  readonly="readonly" type="text" id="hhHouseAddress" name="hhHouseAddress" style="width: 100%"/></td>
				</tr>
				<tr class="odd">
					<td colspan="4">
					<div id="findLngLat">
						<div id="r-result" style="padding:5px 0px 0px 300px;">请输入房源地址:<input type="text" id="suggestId" size="20" value="百度" style="width:150px;" />
							<span style="color: red">鼠标点击地图即可获取经纬度及详情地址</span>
						</div>
						<div id="l-map"></div>

						<div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>
					</div>
					</td>

				</tr>


			</table>
		</div>

	</div>


</form>
</body>
</html>
<script type="text/javascript">
	// 百度地图API功能
	function G(id) {
		return document.getElementById(id);
	}

	var map = new BMap.Map("l-map");
	map.centerAndZoom(new BMap.Point(116.561437,39.783354),16);              // 初始化地图,设置城市和地图级别。
	map.enableScrollWheelZoom();
	map.enableContinuousZoom();
	var longitude;
	var latitude;

	var geoc = new BMap.Geocoder();


	map.addEventListener("click", function(e){
		longitude = e.point.lng;
		latitude  = e.point.lat;

		//给文本框赋值 要改id!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		document.getElementById("hhHouseLatlng").value=latitude+","+longitude;

		map.clearOverlays();

		var new_point = new BMap.Point(longitude,latitude);

		var marker = new BMap.Marker(new_point);
		map.addOverlay(marker);
		map.panTo(new_point);

		var pt = e.point;
		geoc.getLocation(pt, function(rs){
			var addComp = rs.addressComponents;
			//alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
			document.getElementById("hhHouseAddress").value=addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber;
		});

	});

	var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
			{"input" : "suggestId"
				,"location" : map
			});

	ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
		var str = "";
		var _value = e.fromitem.value;
		var value = "";
		if (e.fromitem.index > -1) {
			value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		}
		str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;

		value = "";
		if (e.toitem.index > -1) {
			_value = e.toitem.value;
			value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		}
		str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
		G("searchResultPanel").innerHTML = str;
	});

	var myValue;
	ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
		var _value = e.item.value;
		myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;

		setPlace();
	});

	function setPlace(){
		map.clearOverlays();    //清除地图上所有覆盖物
		function myFun(){
			var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
			map.centerAndZoom(pp, 18);
			map.addOverlay(new BMap.Marker(pp));    //添加标注
		}
		var local = new BMap.LocalSearch(map, { //智能搜索
			onSearchComplete: myFun
		});
		local.search(myValue);
	}


</script>

