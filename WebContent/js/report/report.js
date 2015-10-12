function showChart(basePath){
	var chartType = $("#chartType").val();
	if("chart1" == chartType ){
		showHouseYearChart(basePath);
	}else if( "chart2" == chartType ){
		showLiftArea(basePath);
	}else if( "chart3" == chartType ){
		showSalesLine(basePath);
	}else{
		$('#container').html('');
	}
}

function showHouseYearChart(basePath){
	jQuery.ajax({
		url: basePath+"houseData",
		success: function(response){
			var chart1Objs = response.chart1Objs;
			var xyears=new Array();
			var yvalues=new Array();
			$.each(chart1Objs,function(n,value) {
				xyears[n]=value.xName;
				yvalues[n]=value.yValue;
			});
			
			$('#container').highcharts({
		        chart: {
		            type: 'column'
		        },
		        title: {
		            text: '建筑年代分布'
		        },
		        xAxis: {
		            categories: xyears
		        },
		        yAxis: {
		            title: {
		                text: '数量'
		            }
		        },
		        series: [{
		            name: '建筑年代',
		            data: yvalues,
		            events: {
	                    click: function (event) {
	                        showYearChartDetail(xyears[event.point.x]);
	                    }
	                }
		        }]
		    });
		}
	});
}

function showLiftArea(basePath){
	jQuery.ajax({
		url: basePath+"liftArea",
		success: function(response){
			var liftAreas = response.liftArea;
			var areas=new Array();
			$.each(liftAreas,function(n,value) {
				var liftPercent=new Object();
				liftPercent.name = value.xName;
				liftPercent.y = value.yValue2;
				liftPercent.drilldown = value.xName;
				areas.push(liftPercent);
			});
			// Create the chart
		    $('#container').highcharts({
		        chart: {
		            type: 'pie'
		        },
		        title: {
		            text: '最新全国电梯数量分布图'
		        },
		        subtitle: {
		            text: '点击饼图可以查看某地区电梯品牌占比'
		        },
		        plotOptions: {
		            series: {
		                dataLabels: {
		                    enabled: true,
		                    format: '{point.name}: {point.y:.1f}%'
		                }
		            }
		        },

		        tooltip: {
		            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
		            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'
		        },
		        series: [{
		            name: "电梯区域分布",
		            colorByPoint: true,
		            data: areas
		        }],
		        drilldown: {
		            series: getBrandDetail(basePath,liftAreas)
		        }
		    });
		}
	});
}

function getBrandDetail(basePath,liftAreas){
	var brandDetails = new Array();
	$.each(liftAreas,function(n,value) {
		var brandDetail=new Object();
		brandDetail.name = value.xName;
		brandDetail.id = value.xName;
		
		jQuery.ajax({
			url : basePath+"liftBrandDetail",
			data : {
				city: value.xName
			},
			success: function(response){
				var details = response.brandDetails;
				var brandPercentArray=new Array();
				$.each(details,function(n,value) {
					var brandPercent=new Object();
					brandPercent.name = value.xName;
					brandPercent.y = value.yValue2;
					brandPercentArray.push(brandPercent);
				});
				brandDetail.data = brandPercentArray;
				brandDetails.push(brandDetail);
			}
		});
	});
	return brandDetails;
}

function showSalesLine(){
	$('#container').highcharts({
        chart: {
            type: 'line'
        },
        title: {
            text: '市场销售趋势'
        },
        subtitle: {
            text: '电梯品牌'
        },
        xAxis: {
            categories: ['2005', '2006', '2007', '2008', '2009', '2010', '2011', '2012', '2013', '2014']
        },
        yAxis: {
            title: {
                text: '销售数量'
            }
        },
        plotOptions: {
            line: {
                dataLabels: {
                    enabled: true
                },
                enableMouseTracking: false
            }
        },
        series: [{
            name: '日立',
            data: [7, 10, 15, 14, 18, 21, 25, 26, 23, 18]
        }, {
            name: '三菱',
            data: [3, 4, 5, 8, 11, 15, 17, 16, 14, 10]
        }, {
            name: '奥迪斯',
            data: [4, 3, 4, 7, 8, 12, 15, 17, 15, 17]
        }]
    });
}