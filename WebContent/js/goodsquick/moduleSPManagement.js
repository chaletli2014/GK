var goodsDictionaryURL = basePath + "getGoodsDics";
var dics;

jQuery(document).ready(function($){
	initDics('trusteeship_module');
	
	$("#newSpLink").click(function(){
		if( $("#moduleType2") && $("#moduleType2").val() == '' ){
			jAlert("新增前请选择组件","提醒");
		}else{
			jQuery('#new_module_sp_div').modal('show', {backdrop: 'static'});
		}
	});
	$("#newEquSpLink").click(function(){
		if( $("#moduleType2") && $("#moduleType2").val() == '' ){
			jAlert("新增前请选择组件","提醒");
		}else{
			loadBrandByModuleType();
			jQuery('#new_equ_module_sp_div').modal('show', {backdrop: 'static'});
		}
	});
	/**
	 * 
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
	 */
	
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
	$("#moduleType1").change(function(){
		if( $(this).val() == '' ){
			refreshDataTable('');
		}
	});
	
	$(".moduleEditLink").click(function(){
		var spId = $(this).attr("aid");
		var partCode = $("#partCode").val();
		
		jQuery.ajax({
			url: basePath + "getModuleSPInfoById",
			data:{
				spId : spId
			}
			,success: function(response){
				var moduleSP = response.moduleSP;
				if( partCode == 'sp_subject' || partCode == 'sp_other' ){
					$("#spName").val(moduleSP.spName);
					$("#spTel").val(moduleSP.spTel);
					$("#moduleSPId").val(moduleSP.id);
					jQuery('#new_module_sp_div').modal('show', {backdrop: 'static'});
				}else if( partCode == 'sp_equ' ){
					$("#equSPName").val(moduleSP.spName);
					$("#equSPTel").val(moduleSP.spTel);
					$("#moduleSPId_equ").val(moduleSP.id);
					
					$("#brandCode").append("<option value='"+moduleSP.brandCode+"' selected=\"selected\">"+moduleSP.brandName+"</option>");
					$("#brandCode").selectBoxIt().data("selectBoxIt");
					$("#brandCode").data("selectBox-selectBoxIt").refresh();
					$("#brandCode").attr("disabled",true);
					jQuery('#new_equ_module_sp_div').modal('show', {backdrop: 'static'});
				}
			}
		});
		
	});
	
	$(".moduleDeleteLink").click(function(){
		var spId = $(this).attr("aid");
		jConfirm("是否确定删除？","提醒",function(r) {
	    	if(r){
	    		window.location.href=basePath+"deleteModuleSP?spId="+spId;
	    	}
	    });
	});
	
	$(".moduleRelateLink").click(function(){
		jAlert("功能测试中：未发现该注册公司","提示");
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
        		tbody = tbody + "<tr>";
        		if( "sp_equ" == partCode ){
        			tbody = tbody + "<td>"+sp.brandName+"</td>";
        		}
        		tbody = tbody + "<td >"+sp.spName+"</td>";
        		tbody = tbody + "<td>"+sp.spTel+"</td>";
        		tbody = tbody + "<td><a aid=\""+sp.id+"\" href=\"#\" class=\"btn btn-orange btn-sm btn-icon icon-left moduleEditLink\">编辑</a>";
        		tbody = tbody + "<a aid=\""+sp.id+"\" href=\"#\" class=\"btn btn-danger btn-sm btn-icon icon-left moduleDeleteLink\">删除</a>";
        		tbody = tbody + "<a aid=\""+sp.id+"\" href=\"#\" class=\"btn btn-blue btn-sm btn-icon icon-left moduleRelateLink\">关联</a></td></tr>";
        	});
        	moduleTable.find('tbody').html(tbody);
		}
	});
}

function loadBrandByModuleType(){
	var moduleType2 = $("#moduleType2").val();
	if( moduleType2 == "moduleType2_dt" ){
		moduleType2 = "lift_brand";
	}
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
	if( $("#moduleType1_other_h") ){
		$("#moduleType1_other_h").val(''+$("#moduleType1").val());
	}
	if( $("#moduleType2_other_h") ){
		$("#moduleType2_other_h").val(''+$("#moduleType2").val());
	}
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

function populateModuleType2ByType1(moduleType1){
	jQuery.ajax({
		url: goodsDictionaryURL,
		data:{
			dicType : moduleType1
		},
		success: function(response){
			var modules = response.dics;
			var optionList = $("#moduleType2");
			optionList.empty();
			optionList.append("<option value=''>--请选择--</option>");
			$.each(modules,function(n,moduleObj){
				optionList.append("<option value='"+moduleObj.dicCode+"'>"+moduleObj.dicName+"</option>");
        	});
			optionList.selectBoxIt().data("selectBoxIt");
			optionList.data("selectBox-selectBoxIt").refresh();
		}
	});
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
