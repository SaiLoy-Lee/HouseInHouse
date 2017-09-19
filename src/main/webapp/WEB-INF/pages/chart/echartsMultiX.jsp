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
			//颜色: 蓝色,红色
			var colors = ['#5793f3', '#d14a61', '#675bba'];

		    /* var data1=document.getElementById('area1').getAttribute('value');
		    var data2=document.getElementById('area2').getAttribute('value'); */
		    //var year1 = ${year};       //统计年份
		    //var season1 = ${season};  //统计季度
		    //var txt = "${year}年第${season}季度房源概况"; //"2017年第二季度房源概况"
		    //var data1= ${data1};     //房屋信息发布数量   [102, 105, 119, 126, 128, 170, 175, 182, 148, 118, 106, 102]
		    //var data2= ${data2};     //订单成交入住数量   [83, 85, 91, 98, 108, 109, 131, 96, 85, 88, 80, 80]
		    
	        // 指定图表的配置项和数据
	        var option = {

		        title: {
	                text: "最近一季度房源概况"
	            },
		        
	            color: colors,

			    tooltip: {
			        trigger: 'none',
			        axisPointer: {
			            type: 'cross'
			        }
			    },
			    legend: {
			        data:['房屋信息发布数量', '订单成交入住数量']
			    },
			    grid: {
			        top: 70,
			        bottom: 50
			    },
			    xAxis: [
			        {
			            type: 'category',
			            axisTick: {
			                alignWithLabel: true
			            },
			            axisLine: {
			                onZero: false,
			                lineStyle: {
			                    color: colors[1]
			                }
			            },
			            axisPointer: {
			                label: {
			                    formatter: function (params) {
			                        return '入住数量  ' + params.value
			                            + (params.seriesData.length ? '：' + params.seriesData[0].data : '');
			                    }
			                }
			            },
			            data: ["第一周", "第二周", "第三周", "第四周", "第五周", "第六周", "第七周", "第八周", "第九周", "第十周", "第十一周", "第十二周"]
			        },
			        {
			            type: 'category',
			            axisTick: {
			                alignWithLabel: true
			            },
			            axisLine: {
			                onZero: false,
			                lineStyle: {
			                    color: colors[0]
			                }
			            },
			            axisPointer: {
			                label: {
			                    formatter: function (params) {
			                        return '发布数量  ' + params.value
			                            + (params.seriesData.length ? '：' + params.seriesData[0].data : '');
			                    }
			                }
			            },
			            data: ["第一周", "第二周", "第三周", "第四周", "第五周", "第六周", "第七周", "第八周", "第九周", "第十周", "第十一周", "第十二周"]
			        }		        
			    ],
			    yAxis: [
			        {
			            type: 'value'
			        }
			    ],
			    series: [
			        {
			            name:'房屋信息发布数量',
			            type:'line',
			            xAxisIndex: 1,		            
			            smooth: true,
			            data: ${data1}
			        },
			        {
			            name:'订单成交入住数量',
			            type:'line',		            
			            smooth: true,
			            data: ${data2}
			        }
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
    <div id="picture" style="width: 1000px;height:400px;"></div>
    <%-- <textarea style="height: 80px;width: 100%" id="area1">${data1}</textarea> --%>
    <%-- <textarea style="height: 80px;width: 100%" id="area2">${data2}</textarea> --%>
    
</body>
</html>