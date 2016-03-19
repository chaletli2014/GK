var goodsDictionaryURL = basePath + "getGoodsDics";
var dics;

jQuery(document).ready(function($){
	initDics('trusteeship_module');
	
	$("#newSpLink").click(function(){
		jQuery('#new_module_sp_div').modal('show', {backdrop: 'static'});
	});
	$("#newEquSpLink").click(function(){
		loadBrandByModuleType();
		jQuery('#new_equ_module_sp_div').modal('show', {backdrop: 'static'});
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
	
	$("#newEquSpSaveBtn").click(function(){
		var spName = $("#spName").val();
		var spTel = $("#spTel").val();
		var brandCode = $("#brandCode").val();
		var moduleType1 = $("#moduleType1").val();
		var moduleType2 = $("#moduleType2").val();
		var partCode = $("#partCode").val();
		var spTypeCode = $("#spTypeCode").val();
		var moduleSPId = $("#moduleSPId").val();
		
		jQuery.ajax({
			url: basePath + "saveOrUpdateModuleSP",
			data:{
				moduleSPId : moduleSPId,
				spName : spName,
				spTel : spTel,
				moduleType1 : moduleType1,
				moduleType2 : moduleType2,
				brandCode : brandCode,
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
	
	$("#moduleType2").change(function(){
		var moduleType2 = $(this).val();
		refreshDataTable(moduleType2);
	});
});

function refreshDataTable(moduleType2){
	var partCode = $("#partCode").val();
	var spTypeCode = $("#spTypeCode").val();
	jQuery.ajax({
		url: basePath + "getModuleSPByModuleType",
		data:{
			moduleType2 : moduleType2,
			partCode : partCode,
			spTypeCode : spTypeCode
		}
		,success: function(response){
			var spList = response.spList;
			var moduleTable = $("#moduleSPTable");
			moduleTable.find('tbody').html("");
        	var tbody = "";
        	$.each(spList,function(n,sp){
        		tbody = tbody + "<tr><td>"+sp.brandName+"</td>";
        		tbody = tbody + "<td >"+sp.spName+"</td>";
        		tbody = tbody + "<td>"+sp.spTel+"</td>";
        		tbody = tbody + "<td><a href=\"#\" class=\"btn btn-orange btn-sm btn-icon icon-left\">编辑</a>";
        		tbody = tbody + "<a href=\"#\" class=\"btn btn-danger btn-sm btn-icon icon-left\">删除</a>";
        		tbody = tbody + "<a href=\"#\" class=\"btn btn-blue btn-sm btn-icon icon-left\">关联</a></td></tr>";
        	});
        	moduleTable.find('tbody').html(tbody);
		}
	});
}

function loadBrandByModuleType(){
	var moduleType2 = $("#moduleType2").val();
	jQuery.ajax({
		url: basePath + "getGoodsDics",
		data:{
			dicType : moduleType2
		}
	,success: function(response){
		var dics = response.dics;
		var childSelection = "";
		$("#brandCode option[value!='']").remove();
		
		$.each(dics,function(n,value) {
			childSelection = childSelection + "<option value='"+value.dicCode+"'>"+value.dicName+"</option>";
		});
		
		$("#brandCode").append(childSelection);
		
		$("#brandCode").selectBoxIt().data("selectBoxIt");
		$("#brandCode").data("selectBox-selectBoxIt").refresh();
	}
	});
}

function submitModuleSP(){
	if( $("#spName").val() == '' ){
		jAlert("名称不能为空","错误");
		return false;
	}
	
	$("#spTypeCode_h").val(''+$("#spTypeCode").val());
	$("#partCode_h").val(''+$("#partCode").val());
	return true;
}

function submitEquModuleSP(){
	if( $("#equSPName").val() == '' ){
		jAlert("名称不能为空","错误");
		return false;
	}
	
	$("#spTypeCode_equ_h").val(''+$("#spTypeCode").val());
	$("#partCode_equ_h").val(''+$("#partCode").val());
	$("#moduleType1_equ_h").val(''+$("#moduleType1").val());
	$("#moduleType2_equ_h").val(''+$("#moduleType2").val());
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