jQuery(document).ready(function($){
	var basePath = $("#basePath").val();
	
	$(".product_link").click(function(){
		if( "template_house_oh" == this.title ){
			jQuery.ajax({
				url: basePath+"checkOwnedHouse",
				success: function(response){
					var result = response.result;
					if( result == 'Y' ){
						jAlert("一个资品库只能存在一个不动产，无法继续添加","提醒");
					}else if( result == 'N' ){
						$("#assetType").val("template_house_oh");
						showPopDiv('newHouseDiv');
					}else{
						jAlert("系统错误，请联系管理员","错误");
					}
				}
			});
		}else{
			jAlert("该模板暂不可用","提醒");
		}
	});
});

function initDics(dicTypeParam){
	jQuery.ajax({
		url: goodsDictionaryURL,
		data:{
			dicType : dicTypeParam
		}
		,success: function(response){
			typeDics = response.dics;
			initProductName();
		}
	});
}