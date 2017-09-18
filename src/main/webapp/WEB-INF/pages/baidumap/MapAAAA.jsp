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
    <meta http-equiv="Content-Type" content="text/html; charset=utf8" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <title>地图找房</title>
    <style type="text/css">
        body, html{width: 100%;height: 100%;margin:0;font-family:"微软雅黑";}
        #l-map{height:300px;width:100%;}
        #r-result{width:100%; font-size:14px;line-height:20px;}
        #mapFind{width: 750px;height:750px;}
    </style>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=lmzmXu8zuVQieAs9UMqi3gY27pQFewEp"></script>
</head>
<body>
<div id="mapFind">
<div id="l-map"></div>
<div id="r-result">
    <%--<input type="button" value="批量地址解析" onclick="bdGEO()" />--%>
    <%--<div id="result"></div>--%>
</div>
</div>
</body>
</html>
<script type="text/javascript">
    // 百度地图API功能
    var map = new BMap.Map("l-map");
    map.centerAndZoom(new BMap.Point(116.561437,39.783354), 13);
    map.enableScrollWheelZoom(true);
    var index = 0;
    var myGeo = new BMap.Geocoder();

    var jsHouseInfo ="[{hhHouseName:'(单间出租)(单间出租)个人出租主卧可住4人K2玉兰湾 2室1厅 主卧',hhHouseAddress:'怡乐北街',hhHouseVillage:'K2玉兰湾'}," +
                "{hhHouseName:'(单间出租)(单间出租)二六三,永顺小区,单间甩租,无中介费用',hhHouseAddress:'通惠河北沿河路',hhHouseVillage:'永顺南里2号院'}," +
                "{hhHouseName:'(单间出租)(单间出租)便宜啦，北京现代音乐学院对面精装朝南大卧室甩租',hhHouseAddress:'万盛北街',hhHouseVillage:'小稿村小区'}," +
                "{hhHouseName:'(单间出租)(单间出租)六号线附近小区精装 温馨家园 个人房',hhHouseAddress:'新建街',hhHouseVillage:'中仓小区'}," +
                "{hhHouseName:'(单间出租)(单间出租)梨园城铁出口 空调卧室 采光好 空间大 押一付一',hhHouseAddress:'梨园北街',hhHouseVillage:'梨花园'}," +
                "{hhHouseName:'(单间出租)(单间出租)梨园城铁北杨家洼葛布店东里 温馨单间 能做饭 上',hhHouseAddress:'运河西大街',hhHouseVillage:'葛布店东里'},] ";



    var adds = [
        "K2玉兰湾（怡乐北街）",
        "永顺南里2号院（通惠河北沿河路）",
        "小稿村小区(万盛北街)",
        "中仓小区(新建街) ",
        "梨花园 梨园北街",
        "葛布店东里（运河西大街）"
    ];
    window.onload = function(){
        bdGEO();

    }
    function bdGEO(){
        var add = adds[index];
        geocodeSearch(add);
        index++;
    }
    function geocodeSearch(add){
        if(index < adds.length){
            setTimeout(window.bdGEO,400);
        }
        myGeo.getPoint(add, function(point){
            if (point) {
//                document.getElementById("result").innerHTML +=  index + "、" + add + ":" + point.lng + "," + point.lat + "</br>";
                var address = new BMap.Point(point.lng, point.lat);
                addMarker(address,new BMap.Label(index+":"+add,{offset:new BMap.Size(20,-10)}));
            }
        }, "北京市");
    }
    // 编写自定义函数,创建标注
    function addMarker(point,label){
        var marker = new BMap.Marker(point);
        map.addOverlay(marker);
        marker.setLabel(label);
    }
</script>