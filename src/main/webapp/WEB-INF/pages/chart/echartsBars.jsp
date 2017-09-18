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

	        //var data1= ${data1};        //管理员  [320, 302, 301, 334, 390, 330, 320, 302, 301, 334, 390, 330]
	        //var data2= ${data2};         //个人  [120, 132, 101, 134, 90, 230, 210, 302, 301, 334, 390, 330]

			option = {
				title: {
			        text: '最近一季度房屋来源占比图'
			    },
			    tooltip : {
			        trigger: 'axis',
			        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			        }
			    },
			    legend: {
			        //data: ['管理员', '个人','中介']
			        data: ['管理员', '个人']
			    },
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '3%',
			        containLabel: true
			    },
			    xAxis:  {
				    type: 'category',
			        data: ['第一周','第二周','第三周','第四周','第五周','第六周','第七周','第八周','第九周','第十周','第十一周','第十二周']
			    },
			    yAxis: {
			        type: 'value'
			    },
			    series: [
			        {
			            name: '管理员',
			            type: 'bar',
			            stack: '总量',
			            label: {
			                normal: {
			                    show: true,
			                    position: 'insideRight'
			                }
			            },
			            data: ${data1}
			        },
			        {
			            name: '个人',
			            type: 'bar',
			            stack: '总量',
			            label: {
			                normal: {
			                    show: true,
			                    position: 'insideRight'
			                }
			            },
			            data: ${data2}
			        }
			        /*{
			            name: '中介',
			            type: 'bar',
			            stack: '总量',
			            label: {
			                normal: {
			                    show: true,
			                    position: 'insideRight'
			                }
			            },
			            data: [220, 182, 191, 234, 290, 330, 310, 302, 301, 334, 390, 330]
			        }*/			       
			    ]
			};			

	        // 使用刚指定的配置项和数据显示图表。
	        myChart.setOption(option);		    
	    }        
    </script>
</head>
<body>
	<div id="back"><a href="#" onclick=" window.history.go(-1)">返回</a></div>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="picture" style="width: 900px;height:400px;"></div>
    
</body>
</html>