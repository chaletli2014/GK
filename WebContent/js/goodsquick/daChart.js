jQuery(document).ready(function($){
	if( $("#mainBar") ){
		initAssetYear();
	}
});

function initAssetYear2(xData,yData,pointData){
	// 配置echarts包所在的路径，是放置所有包的路径
	require.config({
		paths : {
			echarts : basePath+'js/echarts'
		}
	});
	// 根据上面引用的配置路径，选择当前要用到的js包，下面的echarts即上面配置的路径，后面的即单签需啊哟用到的js包名：bar.js、line。js、。。。
	require( [ 'echarts', 'echarts/chart/bar', 'echarts/chart/line', 'echarts/chart/map', 'echarts/chart/pie' ]
	, function(ec) {//回调函数，即引用包后的操作，通过require获得echarts接口后（或者命名空间上）实例化图表，echarts接口仅有一个方法init
	//--- 折柱 ---
	var myChart = ec.init(document.getElementById('mainBar'));//容器对象，初始化接口、图表所在节点
	var option1 = {//图表操作属性
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			data : ['小区数量']//图例说明
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : false
				},
				dataView : {
					show : false
				},
				magicType : {
					show : true,
					type : [ 'line', 'bar' ]
				},
				restore : {
					show : false
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : true,
		xAxis : [
			{
				type : 'category',
				position : 'bottom',
				boundaryGap : true,
				axisLine : { // 轴线
					show : true,
					lineStyle : {
						color : 'red',
						type : 'solid',
						width : 2
					}
				},
				axisTick : { // 轴标记
					show : true,
					length : 10,
					lineStyle : {
						color : 'red',
						type : 'solid',
						width : 2
					}
				},
				axisLabel : {
					show : true,
					interval : 'auto', // {number}
					rotate : 45,
					margin : 8,
					formatter : '{value}年',
					textStyle : {
						color : 'blue',
						fontFamily : 'sans-serif',
						fontSize : 15,
						fontStyle : 'italic',
						fontWeight : 'bold'
					}
				},
				splitLine : {
					show : true,
					lineStyle : {
						color : '#483d8b',
						type : 'dashed',
						width : 1
					}
				},
				splitArea : {
					show : true,
					areaStyle : {
						color : [ 'rgba(144,238,144,0.3)', 'rgba(135,200,250,0.3)' ]
					}
				},
				data : xData
			}
		],
		yAxis : [
			{
				type : 'value',
				position : 'left',
				splitNumber: 10,
				axisLine : { // 轴线
					show : true,
					lineStyle : {
						color : 'red',
						type : 'dashed',
						width : 2
					}
				},
				splitNumber : 10,
				axisTick : { // 轴标记
					show : true,
					length : 10,
						lineStyle : {
						color : 'green',
						type : 'solid',
						width : 2
					}
				},
				//Y轴label
				axisLabel : {
					show : true,
					interval : 'auto', // {number}
					rotate : -45,
					margin : 18,
					formatter : function(value) {
						return value + "个";
					},
					textStyle : {
						color : '#1e90ff',
						fontFamily : 'verdana',
						fontSize : 10,
						fontStyle : 'normal',
						fontWeight : 'bold'
					}
				},
				splitLine : {
					show : true,
					lineStyle : {
						color : '#483d8b',
						type : 'dotted',
						width : 2
					}
				},
				splitArea : {
					show : true,
					areaStyle : {
					color : [ 'rgba(205,92,92,0.3)', 'rgba(255,215,0,0.3)' ]
					}
				}
			}
		],
		series : [//图例对应的数据
			{
				name : '小区数量',
				type : 'bar',
				data : yData,
				markPoint : {
	                data : pointData
	            },
	            itemStyle:{
                  normal:{color:'skyblue'}
	            }
			}
		]
	};
	myChart.setOption(option1);
	});
}

function initAssetYear(){
	var sourceArray = new Array();
	jQuery.ajax({
		url: basePath+"getAssetYears",
		success: function(response){
			var assetYearList = response.assetYearList;
			var xData = new Array();
			var yData = new Array();
			var pointDataArray = new Array();
			var count = 0;
			$.each(assetYearList,function(n,assetYear){
				var dataSource = new Object();
				var pointData = new Object();
				if( assetYear.xName == '05-10' ){
					dataSource.year = '6-10';
				}else if( assetYear.xName == '10-15' ){
					dataSource.year = '11-15';
				}else if( assetYear.xName == '15-20' ){
					dataSource.year = '16-20';
				}else{
					dataSource.year = assetYear.xName;
				}
				dataSource.num = assetYear.yValue;
				xData.push(dataSource.year);
				yData.push(dataSource.num);
				
				pointData.value = assetYear.yValue;
				pointData.xAxis = count;
				pointData.yAxis = assetYear.yValue + 1;
				pointData.symbolSize = 18;
				pointDataArray.push(pointData);
				count++;
			});
			initAssetYear2(xData,yData,pointDataArray);
		}
	});
}