<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="common/header.jsp"%>
<script type="text/javascript">
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
	                text: '设备故障率'
	            },
	            subtitle: {
	                text: '通过鼠标划区可以查看更小时间段信息'
	            },
	            xAxis: {
	                type: 'datetime'
	            },
	            yAxis: {
	                title: {
	                    text: '故障率'
	                },
	                labels: {
	                    formatter: function() {
	                        return this.value * 100 +'%';
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
		            text: '设备分布图'
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
		            name: '该区域设备占比',
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
</script>
<style>
.index_device li{
  float: left;
  list-style: none;
  margin: 10px;
}
.info_title{
	display: inline-block;
	padding: 10px;
  	background: #2c2e2f;
  	color: #fff;
  	font-weight: bold;
}
.info_detail{
	display: inline-block;
  	padding: 10px;
}
.color_red{
	color:red;
}
.color_green{
	color:green;
}
</style>
<body class="page-body">
	<jsp:include page="common/settings_pane.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
	<div class="page-container">
		<!-- add class "sidebar-collapsed" to close sidebar by default, "chat-visible" to make chat appear always -->
		<!-- Add "fixed" class to make the sidebar fixed always to the browser viewport. -->
		<!-- Adding class "toggle-others" will keep only one menu item open at a time. -->
		<!-- Adding class "collapsed" collapse sidebar root elements and show only icons. -->
		<jsp:include page="common/sidebar.jsp" flush="true">
        	<jsp:param name="basePath" value="<%=basePath%>"/>
        </jsp:include>
		<div class="main-content">
			<h3 style="margin-top:0px;margin-bottom:20px;">首页看板</h3>
			<div class="row draggable-portlets">
				<div class="col-md-12">
					<div class="sorted">
						<div class="panel panel-color panel-success">
							<!-- panel head -->
							<div class="panel-heading panel-heading-thin">
								<div class="panel-title">我的面板</div>
							</div>
							<!-- panel body -->
							<div class="panel-body">
								<ul class="index_device">
									<li>
										<span class="info_title">不动产数量</span>
										<span class="info_detail"><a href="javascript:void(0)">20</a></span>
									</li>
									<li>
										<span class="info_title">设备预警</span>
										<span class="info_detail"><a href="javascript:void(0)" class="color_red">20</a></span>
									</li>
									<li>
										<span class="info_title">我的消息</span>
										<span class="info_detail"><a href="javascript:void(0)" class="color_green">5</a></span>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="sorted">
						<div class="panel panel-color panel-success">
							<!-- panel head -->
							<div class="panel-heading  panel-heading-thin" style="background:#4C4F53">
								<div class="panel-title"><b>设备监控</b></div>
							</div>
							<!-- panel body -->
							<div class="panel-body">
								<div class="info-top" id="alertreport" style="width:100%;heigth:200px;"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="sorted">
						<div class="panel panel-color panel-success">
							<!-- panel head -->
							<div class="panel-heading  panel-heading-thin" style="background:#4C4F53">
								<div class="panel-title"><b>设备分布</b></div>
							</div>
							<!-- panel body -->
							<div class="panel-body">
								<div class="info-top" id="deviceArea" style="width:100%;heigth:200px;"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<%@include file="common/footer.jsp" %>
		</div>
	</div>
	<jsp:include page="common/bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
</body>
</html>