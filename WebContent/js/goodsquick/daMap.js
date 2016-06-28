var opts = {
  width : 200,     // 信息窗口宽度
  height: 80,     // 信息窗口高度
  title : "" , // 信息窗口标题
  enableMessage:true,//设置允许信息窗发送短息
}
var map;

jQuery(document).ready(function($){
	if( $("#overviewMap").length>0 ){
		initLocationMap();
	}
	if( $("#densityMap").length>0 ){
		initDensityMap();
	}
	
	$("body").delegate('.density_tab_a', 'click', function(){
		$(this).parent().parent().children().removeClass("hover-line active");
		$(this).parent().addClass("hover-line active");
		initDensityList(map,$(this).attr("aid"));
	});
	
});

function initLocationMap(){
	map = new BMap.Map("overviewMap");    // 创建Map实例
	// 百度地图API功能
	map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
	map.setCurrentCity("上海");          // 设置地图显示的城市 此项是必须设置的
	map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
	
	addMarker(map,"高行镇人民政府","东靖路1801号", true);
	initCommunityRepositoryList(map);
}

function initDensityMap(){
	if(!isSupportCanvas()){
    	alert('热力图目前只支持有canvas支持的浏览器,您所使用的浏览器不能使用热力图功能~')
    	return;
    }
	
	map = new BMap.Map("densityMap");    // 创建Map实例
	// 百度地图API功能
	map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
	map.setCurrentCity("上海");          // 设置地图显示的城市 此项是必须设置的
	map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
	addMarker(map,"高行镇人民政府","东靖路1801号", true);
	
	initDensityList(map,"");
}

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

function initDensityList(map, type){
	map.clearOverlays();
	jQuery.ajax({
		url: basePath+"getDensity",
		data:{
			densityType: type
		},
		success: function(response){
			var densityList = response.densityList;
			var points = new Array();
			$.each(densityList,function(n,density){
				var pointObj = {"lng":0,"lat":0,"count":0};
				pointObj.lng = density.c5;
				pointObj.lat = density.c6;
				pointObj.count = density.c3;
			　　	points.push(pointObj);
			});
			heatmapOverlay = new BMapLib.HeatmapOverlay({"radius":20});
			map.addOverlay(heatmapOverlay);
			heatmapOverlay.setDataSet({data:points,max:10});
			heatmapOverlay.show();
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

function isSupportCanvas(){
    var elem = document.createElement('canvas');
    return !!(elem.getContext && elem.getContext('2d'));
}