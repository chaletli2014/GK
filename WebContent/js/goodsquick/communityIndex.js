var opts = {
  width : 200,     // 信息窗口宽度
  height: 80,     // 信息窗口高度
  title : "" , // 信息窗口标题
  enableMessage:true,//设置允许信息窗发送短息
}

jQuery(document).ready(function($){
	var map = new BMap.Map("overviewMap");    // 创建Map实例
	// 百度地图API功能
	map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
	map.setCurrentCity("上海");          // 设置地图显示的城市 此项是必须设置的
	map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
	
	addMarker(map,"高行镇人民政府","东靖路1801号", true);
	initCommunityRepositoryList(map);
	initAssetYear();
});

function initCommunityRepositoryList(map){
	jQuery.ajax({
		url: basePath+"getCommunityRepos",
		success: function(response){
			var communityAssetList = response.communityAssetList;
			$.each(communityAssetList,function(n,communityAsset){
				addMarker(map, communityAsset.buildingName, communityAsset.location, false);
			});
		}
	});
}

function addMarker(map,assetName, location,centerAndZoom){
	var myGeo = new BMap.Geocoder();
	myGeo.getPoint(location, function(point){
		if (point) {
			if( centerAndZoom ){
				map.centerAndZoom(point, 14);
			}
			var marker = new BMap.Marker(point);
			map.addOverlay(marker);
			
			var infoWindow = new BMap.InfoWindow("不动产名称："+assetName+"<br/>地址："+location, opts);  // 创建信息窗口对象 
			marker.addEventListener("click", function(){
				map.openInfoWindow(infoWindow,point); //开启信息窗口
			});
		}else{
			alert("您选择地址没有解析到结果!");
		}
	}, "上海市");
}

function initAssetYear(){
	jQuery.ajax({
		url: basePath+"getCommunityRepos",
		success: function(response){
			var communityAssetList = response.communityAssetList;
			$.each(communityAssetList,function(n,communityAsset){
				addMarker(map, communityAsset.buildingName, communityAsset.location, false);
			});
		}
	});
	
	$("#assetYear").dxChart({
		dataSource: [
			{day: "1年", sales: 3},
			{day: "5年", sales: 2},
			{day: "10年", sales: 3},
			{day: "15年", sales: 4},
			{day: "20年", sales: 6}
		],
		series: {
			argumentField: "day",
			valueField: "sales",
			name: "房龄",
			type: "bar",
			color: '#68b828'
		}
	});
}