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
    <title>普通地图&全景图</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=lmzmXu8zuVQieAs9UMqi3gY27pQFewEp"></script>
    <style type="text/css">
        body, html{width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
        #panorama {height: 50%;overflow: hidden;}
        #normal_map {height:50%;overflow: hidden;}
    </style>
</head>
<body>
<div id="panorama"></div>
<div id="normal_map"></div>
<script type="text/javascript">
    //全景图展示
    var panorama = new BMap.Panorama('panorama');
    panorama.setPosition(new BMap.Point(116.331398, 39.897445)); //根据经纬度坐标展示全景图
    panorama.setPov({heading: -40, pitch: 6});

    panorama.addEventListener('position_changed', function(e){ //全景图位置改变后，普通地图中心点也随之改变
        var pos = panorama.getPosition();
        map.setCenter(new BMap.Point(pos.lng, pos.lat));
        marker.setPosition(pos);
    });
    //普通地图展示
    var mapOption = {
        mapType: BMAP_NORMAL_MAP,
        maxZoom: 18,
        drawMargin:0,
        enableFulltimeSpotClick: true,
        enableHighResolution:true
    }
    var map = new BMap.Map("normal_map", mapOption);
    var testpoint = new BMap.Point(116.561437,39.783354);
    map.centerAndZoom(testpoint, 18);
    var marker=new BMap.Marker(testpoint);
    marker.enableDragging();
    map.addOverlay(marker);
    marker.addEventListener('dragend',function(e){
        panorama.setPosition(e.point); //拖动marker后，全景图位置也随着改变
        panorama.setPov({heading: -40, pitch: 6});}
    );
</script>
</body>
</html>