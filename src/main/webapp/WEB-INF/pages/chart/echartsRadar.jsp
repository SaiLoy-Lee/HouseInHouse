<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>ECharts</title>
    <!-- 引入 echarts.js -->
    <script type="text/javascript" src="${ctx}/staticfile/js/echarts.min.js" ></script>
    <script type="text/javascript">

	    window.onload = function(){
		    // 基于准备好的dom，初始化echarts实例
	        var myChart = echarts.init(document.getElementById('picture'));

	        //var data1= ${data1};     //房屋发布的租金  [73, 200, 380, 350, 500, 290]
		    //var data2= ${data2};     //订单成交的租金  [50, 140, 280, 310, 420, 210]

	        option = {
			    title: {
			        text: '最近一季度租金占比图'
			    },
			    tooltip: {},
			    legend: {
			        data: ['房屋发布的租金', '订单成交的租金']
			    },
			    radar: {
			        // shape: 'circle',
			        name: {
			            textStyle: {
			                color: '#fff',
			                backgroundColor: '#999',
			                borderRadius: 3,
			                padding: [3, 5]
			           }
			        },
			        indicator: [
			           { name: '0-100', max: 500},
			           { name: '1000-2000', max: 500},
			           { name: '2000-300', max: 500},
			           { name: '3000-4000', max: 500},
			           { name: '4000-500', max: 500},
			           { name: '5000以上', max: 500}
			        ]
			    },
			    series: [{
			        name: '发布 vs 成交',
			        type: 'radar',
			        // areaStyle: {normal: {}},
			        data : [
			            {
			                value : ${data1},
			                name : '房屋发布的租金'
			            },
			             {
			                value : ${data2},
			                name : '订单成交的租金'
			            }
			        ]
			    }]
			};			

	        // 使用刚指定的配置项和数据显示图表。
	        myChart.setOption(option);
		    
	    }
	    
        
    </script>
</head>
<body>
	<div id="back"><a href="#" onclick=" window.history.go(-1)">返回</a></div>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="picture" style="width: 800px;height:500px;"></div>
    
</body>
</html>