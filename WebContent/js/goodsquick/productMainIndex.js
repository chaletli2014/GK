jQuery(document).ready(function($){
		var data = [
			[Date.UTC(2013,5,2),0.2695],
			[Date.UTC(2013,6,3),0.2648],
			[Date.UTC(2013,7,4),0.3645],
			[Date.UTC(2013,8,5),0.3638],
			[Date.UTC(2013,9,6),0.2549],
			[Date.UTC(2013,10,7),0.2562],
			[Date.UTC(2013,11,9),0.3574],
			[Date.UTC(2013,12,10),0.2543],
			[Date.UTC(2014,5,11),0.2510],
			[Date.UTC(2015,1,12),0.3498],
			[Date.UTC(2015,2,12),0.3898],
			[Date.UTC(2015,3,12),0.4198],
			[Date.UTC(2015,4,12),0.4098],
			[Date.UTC(2015,5,12),0.4098]
		];
		$(function() {
			var $draggable_portlets = $(".draggable-portlets");
			$(".draggable-portlets .sorted" ).sortable({
				connectWith: ".draggable-portlets .sorted",
				handle: '.panel-heading',
				start: function()
				{
					$draggable_portlets.addClass('dragging');
				},
				stop: function()
				{
					$draggable_portlets.removeClass('dragging');
				}
			});
			$( ".draggable-portlets .sorted .panel-heading" ).disableSelection();
			
			$('#alertreport').highcharts({
				chart: {
	                zoomType: 'x'
	            },
	            title: {
	                text: '历史用户数'
	            },
	            subtitle: {
	                text: '通过鼠标划区可以查看更小时间段信息'
	            },
	            xAxis: {
	                type: 'datetime'
	            },
	            yAxis: {
	                title: {
	                    text: '用户数'
	                },
	                labels: {
	                    formatter: function() {
	                        return this.value * 100;
	                    }
	                }
	            },
	            legend: {
	                enabled: false
	            },
	            plotOptions: {
	                area: {
	                    fillColor: {
	                        linearGradient: {
	                            x1: 0,
	                            y1: 0,
	                            x2: 0,
	                            y2: 1
	                        },
	                        stops: [
	                            [0, Highcharts.getOptions().colors[0]],
	                            [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
	                        ]
	                    },
	                    marker: {
	                        radius: 2
	                    },
	                    lineWidth: 1,
	                    states: {
	                        hover: {
	                            lineWidth: 1
	                        }
	                    },
	                    threshold: null
	                }
	            },

	            series: [{
	                type: 'area',
	                name: '故障率',
	                data: data
	            }]
		    });
			
			$('#deviceArea').highcharts({
				chart: {
		            type: 'pie',
		            options3d: {
		                enabled: true,
		                alpha: 45,
		                beta: 0
		            }
		        },
		        title: {
		            text: '产品分布图'
		        },
		        tooltip: {
		            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
		        },
		        plotOptions: {
		            pie: {
		                allowPointSelect: true,
		                cursor: 'pointer',
		                depth: 35,
		                dataLabels: {
		                    enabled: true,
		                    format: '{point.name}'
		                }
		            }
		        },
		        series: [{
		            type: 'pie',
		            name: '该区域产品占比',
		            data: [
		                ['上海',	45.0],
		                ['杭州',	6.8],
		                ['北京',	28.5],
		                ['广州', 16.2],
		                ['深圳', 0.7]
		            ]
		        }]
		    });
		});
	});