<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<link rel="stylesheet" rev="stylesheet" type="text/css" href="${ctx}/staticfile/skin/default/css/default.css" media="all"/>
<link rel="stylesheet" rev="stylesheet" type="text/css" href="${ctx}/staticfile/skin/default/css/button.css" media="all"/>
<link rel="stylesheet" rev="stylesheet" type="text/css" href="${ctx}/staticfile/skin/default/css/table.css" media="all"/>
<script language="javascript" src="${ctx}/staticfile/js/common.js"></script>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <style type="text/css">
        body, html{width: 100%;height: 100%;margin:0;font-family:"微软雅黑";}
        #allmap{height:95%;width:100%;}
        #r-result{width:100%; font-size:14px;}
        #lngLatFind{width: 300px;height:300px;}
    </style>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=lmzmXu8zuVQieAs9UMqi3gY27pQFewEp"></script>
    <title>房中房获取小区地址</title>

</head>
<body>
<div id="lngLatFind">
<div id="allmap"></div>
<div id="r-result">
    经度: <input name="longitude" id="longitude" type="text" value="39.799384,116.591304" style="width:100px; margin-right:10px;" />
    纬度: <input name="latitude" id="latitude" type="text" value=" 北小营路 " style="width:100px; margin-right:10px;" />
    <input name="village" id="village" type="text" value="次渠嘉园一区" style="width:100px; margin-right:10px;" />
    <%--<input type="button" value="查询" onclick="theLocation()" />--%>


        <input type="button" value="地图详情>" onclick="theMapdetails()" style="width:280px; height:50px;  margin-right:10px;"/>
</div>
    </div>
</body>
</html>
<script type="text/javascript">
    // 百度地图API功能
    var map = new BMap.Map("allmap");
    map.centerAndZoom(new BMap.Point(116.561437,39.783354),16);
    map.enableScrollWheelZoom(true);


//    window.onload = function() {
//        map.clearOverlays();
//        var new_point = new BMap.Point(99,33);
//        var marker = new BMap.Marker(new_point);  // 创建标注
//        map.addOverlay(marker);              // 将标注添加到地图中
//        map.panTo(new_point);
//
//
//    }


    //要改id!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    var ll=document.getElementById("longitude").value;
    var arr=ll.split(",");
    address=document.getElementById("latitude").value.trim();
    village=document.getElementById("village").value;

    window.onload=function(){

//        var ll="39.872488,116.694164";
//        var arr=ll.split(",");



        var longitude=arr[1];
        var latitude=arr[0];


        map.clearOverlays();

        var new_point = new BMap.Point(longitude,latitude);

        var marker = new BMap.Marker(new_point);
        map.addOverlay(marker);
        map.panTo(new_point);
    }

    function theMapdetails(){



        window.location.href="http://map.baidu.com/?latlng="+ll+"&title="+address+"&content="+village+"&autoOpen=true&l";


    }


    function theLocation(){
        if(document.getElementById("longitude").value != "" && document.getElementById("latitude").value != ""){
            map.clearOverlays();
            var new_point = new BMap.Point(document.getElementById("longitude").value,document.getElementById("latitude").value);
            var marker = new BMap.Marker(new_point);  // 创建标注
            map.addOverlay(marker);              // 将标注添加到地图中
            map.panTo(new_point);
        }
    }
</script>
