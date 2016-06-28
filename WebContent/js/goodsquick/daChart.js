jQuery(document).ready(function($){
	if( $("#assetYear") ){
		initAssetYear();
	}
});

function initAssetYear(){
	var sourceArray = new Array();
	jQuery.ajax({
		url: basePath+"getAssetYears",
		success: function(response){
			var assetYearList = response.assetYearList;
			$.each(assetYearList,function(n,assetYear){
				var dataSource = new Object();
				if( assetYear.xName == '05-10' ){
					dataSource.year = '5-10';
				}else{
					dataSource.year = assetYear.xName;
				}
				dataSource.num = assetYear.yValue;
				sourceArray.push(dataSource);
			});
			
			$("#assetYear").dxChart({
				dataSource: sourceArray,
				series: {
					argumentField: "year",
					valueField: "num",
					name: "房龄",
					type: "bar",
					color: '#68b828'
				},
				valueAxis: [{
			        grid: {
			            visible: true
			        }
			    }, {
			        name: "total",
			        position: "left",
			        grid: {
			            visible: true
			        },
			        title: {
			            text: "小区数量"
			        },
			        label: {
			            format: "largeNumber"
			        }
			    }],
			});
		}
	});
}