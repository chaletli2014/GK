var goodsDictionaryURL = basePath + "getGoodsDics";
var dics;

jQuery(document).ready(function($){
	initDics('trusteeship_module');
	
	$("#newSpLink").click(function(){
		jQuery('#new_module_sp_div').modal('show', {backdrop: 'static'});
	});
	
	$("#newSpSaveBtn").click(function(){
		var spName = $("#spName").val();
		var spTel = $("#spTel").val();
		var partCode = $("#partCode").val();
		var spTypeCode = $("#spTypeCode").val();
		var moduleSPId = $("#moduleSPId").val();
		
		jQuery.ajax({
			url: basePath + "saveOrUpdateModuleSP",
			data:{
				moduleSPId : moduleSPId,
				spName : spName,
				spTel : spTel,
				partCode : partCode,
				spTypeCode : spTypeCode
			}
			,success: function(response){
				jAlert("新增成功","信息",function(){
					window.location.href=basePath+"moduleSPManagement?partCode="+partCode+"&spTypeCode="+spTypeCode;
				});
			}
		});
	});
	
	$("#sp_type_ul li").click(function(){
		$("#sp_type_ul li").each(function(){
			$(this).removeClass("active");
		});
		$(this).addClass("active");
		var partCode = $("#partCode").val();
		window.location.href=basePath+"moduleSPManagement?partCode="+partCode+"&spTypeCode="+$(this).attr("lid");
	});
});

function submitModuleSP(){
	if( $("#spName").val() == '' ){
		jAlert("名称不能为空","错误");
		return false;
	}
	
	$("#spTypeCode_h").val(''+$("#spTypeCode").val());
	$("#partCode_h").val(''+$("#partCode").val());
	return true;
}

function initDics(dicTypeParam){
	jQuery.ajax({
		url: goodsDictionaryURL,
		data:{
			dicType : dicTypeParam
		}
		,success: function(response){
			dics = response.dics;
			initDataTable();
		}
	});
}

function initDataTable(){
	
}
function initDics(dicTypeParam){
	jQuery.ajax({
		url: goodsDictionaryURL,
		data:{
			dicType : dicTypeParam
		}
		,success: function(response){
			dics = response.dics;
			initDataTable();
		}
	});
}