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
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=lmzmXu8zuVQieAs9UMqi3gY27pQFewEp"></script>
    <style>
        html, body, #map {
            height: 100%;
            width: 100%;
            margin: 0;
            padding: 0;
        }
        body {
            background-color: #FFF;
            overflow: hidden;
            font-family: "Trebuchet MS";
        }
        .info-content a{
            float:right;
        }
        .popup_main{
            border:1px solid #cdcdcd;
            z-index:1001;
            position:absolute;
            background:#fff;
            overflow:hidden;
            left:20px;
            top:100px;
            width:330px;
            display: none;
        }
        .popup_main .title {
            border-bottom:1px solid #dadada;
            height:25px;
            line-height:25px;
            font-size:12px;
            color:#4c4c4c;
            padding-left:7px;
        }
        .popup_main .summary {
            margin:2px;
            background: #ccc;
            padding: 8px;
            border-bottom:1px solid #ddd;
        }
        .popup_main .content {
            height:auto;
            padding: 8px;
            border-bottom:1px solid #ddd;
        }
        .popup_main ul{
            list-style: none;
            margin: -0px 0;
            overflow:hidden;
            overflow-y:auto;
            max-height: 500px;
        }
        .popup_main ul li {
            position: relative;
            margin-left: -40px;
            position: relative;
            border-bottom:1px solid #ddd;
            cursor: pointer;
            height: 50px;
        }
        .popup_main ul li:hover{
            background: #ccc;
        }
        .popup_main ul li .image{
            position:absolute;
            left:8px;
            top: 10px;
            height:30px;
            text-align:center;
            width:30px;
            line-height:15px;
        }
        .popup_main ul li .image img{
            height: 100%;
        }
        .popup_main ul li .content{
            width: 85%;
            float: right;
            border: none;
        }
        .popup_main ul li .content div.item-tle{
            font-size: 14px;
            color: #262626;
            font-weight: 800;
            overflow: hidden;
            text-overflow: ellipsis;
            line-height: 1;
        }
        .popup_main button{
            position: absolute;
            z-index: 50;
            top: 7px;
            right: 6px;
            width: 12px;
            height: 12px;
            background: url("") no-repeat;
            border: 0;
            cursor: pointer;
        }
    </style>
    <script type="text/javascript">
        dojoConfig = {
            parseOnLoad: true,
            packages: [{
                name: 'tdlib',
                location: this.location.pathname.replace(/\/[^/]+$/, "")+"/tdtlib"
            }]
        };
    </script>
    <script src="http://jsapi.thinkgis.cn/init.js"></script>
    <script src="http://lzugis.d152.ptzygj.com/app/js/jquery/jquery-1.8.3.js"></script>
    <script src="terraformer/terraformer.js"></script>
    <script src="terraformer/terraformer-wkt-parser.js"></script>
    <script src="terraformer/terraformer-arcgis-parser.js"></script>
    <script src="data.js"></script>
    <script>
        var map;
        require([
                    "esri/map",
                    "tdlib/TDTTilesLayer",
                    "esri/layers/GraphicsLayer",
                    "esri/geometry/Point",
                    "esri/geometry/Extent",
                    "esri/geometry/Polyline",
                    "esri/symbols/SimpleLineSymbol",
                    "esri/symbols/SimpleMarkerSymbol",
                    "esri/symbols/PictureMarkerSymbol",
                    "esri/symbols/SimpleFillSymbol",
                    "esri/symbols/TextSymbol",
                    "esri/Color",
                    "esri/geometry/jsonUtils",
                    "esri/graphic",
                    "esri/dijit/InfoWindow",
                    "dojo/domReady!"],
                function(Map,
                         TDTTilesLayer,
                         GraphicsLayer,
                         Point,
                         Extent,
                         Polyline,
                         SimpleLineSymbol,
                         SimpleMarkerSymbol,
                         PictureMarkerSymbol,
                         SimpleFillSymbol,
                         TextSymbol,
                         Color,
                         geometryJsonUtils,
                         Graphic,InfoWindow)
                {
                    map = new Map("map", {
                        logo: false,
                        center: [116.43228121152976, 40.20122178384614], // longitude, latitude
                        zoom: 7
                    });
                    var vec_c = new TDTTilesLayer("vec")
                    map.addLayer(vec_c);
                    var zLayer = new GraphicsLayer();
                    map.addLayer(zLayer);
                    var gLayer = new GraphicsLayer();
                    map.addLayer(gLayer);
                    var lLayer = new GraphicsLayer();
                    map.addLayer(lLayer);

                    lLayer.on("mouse-over",function(e){
                        map.setMapCursor("pointer");
                    })
                    lLayer.on("mouse-out",function(e){
                        map.setMapCursor("default");
                    })
                    lLayer.on("click",function(e){
                        var attr = e.graphic.attributes;
                        if(attr){
                            showObjInfo(attr);
                        }
                    })

                    map.on("zoom-end",function(anchor,extent,level,zoomFactor){
                        if(map.getZoom()<8){
                            gLayer.show();
                            lLayer.hide();
                            map.infoWindow.hide();
                        };
                    });

                    $("#network").on("click",function(){
                        for(var i=0;i<data.length;i++){
                            var _d = data[i];
                            var geometry = getGeomByWKT(_d.wkt);
                            var pt = geometry.getCentroid();
                            var sms = new SimpleMarkerSymbol(
                                    SimpleMarkerSymbol.STYLE_CIRCLE, 60,
                                    new SimpleLineSymbol(SimpleLineSymbol.STYLE_SOLID,
                                            new Color([255,0,0]), 2),
                                    new Color([255,0,0,0.5]));
                            var tsname = new TextSymbol(_d.counname).setOffset(0, 6).setColor(new Color([255,255,255])) ;
                            var tsprice = new TextSymbol(_d.price.toFixed(2)+"万").setOffset(0, -7).setColor(new Color([255,255,255]));
                            var tscount = new TextSymbol(_d.count+"套").setOffset(0, -22).setColor(new Color([255,255,255]));
                            var _gd = _d;
                            _d.index = i;
                            _gd.id="graphic"+i;
                            gLayer.add(new Graphic(pt, sms, _gd));
                            gLayer.add(new Graphic(pt, tsname,_d));
                            gLayer.add(new Graphic(pt, tsprice,_d));
                            gLayer.add(new Graphic(pt, tscount,_d));
                        }
                    });

                    gLayer.on("mouse-over",function(e){
                        map.setMapCursor("pointer");
                        var sms = new SimpleMarkerSymbol(
                                SimpleMarkerSymbol.STYLE_CIRCLE, 60,
                                new SimpleLineSymbol(SimpleLineSymbol.STYLE_SOLID,
                                        new Color([0,0,255]), 2),
                                new Color([0,0,255,0.5]));
                        var _zoneG = getGraphicById("graphic"+e.graphic.attributes.index);
                        _zoneG.setSymbol(sms);
                        gLayer.redraw();
                        zLayer.clear();
                        var geometry = getGeomByWKT(e.graphic.attributes.wkt);
                        var sfs = new SimpleFillSymbol(SimpleFillSymbol.STYLE_SOLID,
                                new SimpleLineSymbol(SimpleLineSymbol.STYLE_DASHDOT,
                                        new Color([0,0,255]), 2),new Color([0,0,255,0.25])
                        );
                        var gfx = new Graphic(geometry,sfs);
                        zLayer.add(gfx);
                    });
                    gLayer.on("mouse-out",function(e){
                        map.setMapCursor("default");
                        var sms = new SimpleMarkerSymbol(
                                SimpleMarkerSymbol.STYLE_CIRCLE, 60,
                                new SimpleLineSymbol(SimpleLineSymbol.STYLE_SOLID,
                                        new Color([255,0,0]), 2),
                                new Color([255,0,0,0.5]));
                        var _zoneG = getGraphicById("graphic"+e.graphic.attributes.index);
                        _zoneG.setSymbol(sms);
                        gLayer.redraw();
                        zLayer.clear();
                    })
                    gLayer.on("click",function(e){
                        gLayer.hide();
                        addZoneData(e.graphic.attributes);
                    });
                    function getGraphicById(id){
                        var g = null;
                        var graphics = gLayer.graphics;
                        for(var i=0,len = graphics.length;i<len;i++){
                            var graphic = graphics[i];
                            if(graphic.attributes.id===id){
                                g = graphic;
                                break;
                            }
                        }
                        return g;
                    }
                    function getGeomByWKT(wkt){
                        var primitive = Terraformer.WKT.parse(wkt);
                        var arcgis = Terraformer.ArcGIS.convert(primitive);
                        return geometryJsonUtils.fromJson(arcgis);
                    }
                    function getZoneData(extent){
                        var data = [];
                        for(var i=0;i<10;i++){
                            var nusnum = GetRandomNum(10,100).toFixed(3);
                            nusnum = parseFloat(nusnum);
                            data.push({
                                X:GetRandomNum(extent.xmin,extent.xmax),
                                Y:GetRandomNum(extent.ymin,extent.ymax),
                                NSRNAME:"name"+i,
                                NSRSUM:nusnum,
                                URL:"#"
                            });
                        }
                        return data;
                    }
                    function addZoneData(attr){
                        var geometry = getGeomByWKT(attr.wkt);
                        var extent = geometry.getExtent();
                        map.setExtent(extent);
                        var data = getZoneData(extent);
                        $(".popup_main").show();
                        var nsrlist = $("#nsrlist").html("");
                        lLayer.clear();
                        lLayer.show();
                        var path = "m127,193l103,0l0,33l-56,0l-11,12l-11,-11l-25.00003,0.00002l0.00003,-34.00002z";
                        var _sum = 0;
                        for(var i=0;i<data.length;i++){
                            var _d = data[i];
                            var pt = new Point(_d.X, _d.Y, map.spatialReference);
                            var pms = new PictureMarkerSymbol("img/marker.png",24,24);
                            lLayer.add(new Graphic(pt, pms,_d));
                            var _li = $("<li/>").css("position","relative").appendTo(nsrlist);
                            _li.append('<div class="image"><img src="img/marker.png" /></div>');
                            _li.append('<div class="content">'+
                                    '<div class="item-tle">'+_d.NSRNAME+'</div>'+_d.NSRSUM+'万'+
                                    '</div>');
                            _sum+=_d.NSRSUM;
                            _li.data("attr",_d);
                            _li.on("click",function(){
                                var _attr = $(this).data("attr");
                                showObjInfo(_attr);
                            });
                        }
                        $("#summary").html(attr.counname+"共有纳税人"+data.length+"人，共纳税"+_sum.toFixed(3)+"万元。")
                        $("#block_close").on("click",function(){
                            $(".popup_main").hide();
                            lLayer.clear();
                            map.infoWindow.hide();
                        });
                    }

                    function showObjInfo(data){
                        var location = new Point(data.X, data.Y, map.spatialReference);
                        map.infoWindow.setTitle(data.NSRNAME);
                        var content= $("<div/>").addClass("info-content");
                        content.append("<b>纳税人名称：</b>"+data.NSRNAME);
                        content.append("<br/><b>纳税人金额：</b>"+data.NSRSUM);
                        content.append("<br/><a href="+data.URL+">详细信息>></a>");
                        map.infoWindow.setContent(content[0]);
                        map.infoWindow.show(location, InfoWindow.ANCHOR_UPPERRIGHT);
                        map.centerAt(location);
                    }
                });
        function GetRandomNum(min, max){
            var r = Math.random()*(max - min);
            var re=r+min;
            return re;
        }
    </script>
</head>
<body>
<div id="map">
    <div class="popup_main">
        <div class="title">列表</div>
        <div class="summary" id="summary">温泉镇共有纳税人100人，共纳税100万元。</div>
        <ul id="nsrlist">
        </ul>
        <button id="block_close" title="关闭"></button>
    </div>
    <button style="position:absolute;top:10px;right:10px;z-index: 99;" id="network">network</button>
</div>
</body>
</html>