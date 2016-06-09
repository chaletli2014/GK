var goodsDictionaryURL = basePath + "getGoodsDics";
var dics;

jQuery(document).ready(function($){
	initDics('trusteeship_module');
	
	$("#newSpLink").click(function(){
		$("#moduleSPId").val('');
		$("#spName").val('');
		$("#spTel").val('');
		$("#spPhone").val('');
		$("#modifyModuleType1").val('');
		$("#modifyModuleType1").removeAttr("disabled");
		$("#modifyModuleType1").selectBoxIt().data("selectBoxIt");
		$("#modifyModuleType1").data("selectBox-selectBoxIt").refresh();
		$("#modifyModuleType2").val('');
		$("#modifyModuleType2").removeAttr("disabled");
		$("#modifyModuleType2").selectBoxIt().data("selectBoxIt");
		$("#modifyModuleType2").data("selectBox-selectBoxIt").refresh();
		
		jQuery('#new_module_sp_div').modal('show', {backdrop: 'static'});
	});
	
	$("#sp_type_ul li").click(function(){
		$("#sp_type_ul li").each(function(){
			$(this).removeClass("active");
		});
		$(this).addClass("active");
		window.location.href=basePath+"moduleSPManagement?spTypeCode="+$(this).attr("lid");
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
	
	$("body").delegate('.moduleEditLink', 'click', function(){
		var spId = $(this).attr("aid");
		jQuery.ajax({
			url: basePath + "getModuleSPInfoById",
			data:{
				spId : spId
			}
			,success: function(response){
				var moduleSP = response.moduleSP;
				$("#spName").val(moduleSP.spName);
				$("#spPhone").val(moduleSP.spPhone);
				$("#spTel").val(moduleSP.spTel);
				$("#moduleSPId").val(moduleSP.id);
				
				var moduleType1 = moduleSP.moduleType1;
				var moduleType2 = moduleSP.moduleType2;
				$("#modifyModuleType1 option").each(function() {
			        if ($(this).val() == moduleType1 ) {
			            $(this).attr("selected", "selected");
			        }
			    });
				$("#modifyModuleType1").attr("disabled","disabled");
				$("#modifyModuleType1").selectBoxIt().data("selectBoxIt");
				$("#modifyModuleType1").data("selectBox-selectBoxIt").refresh();
				
				var options="<option value='"+moduleType2+"'>"+moduleSP.proServiceName+"</option>";
				$("#modifyModuleType2").append(options);
				$("#modifyModuleType2 option").each(function() {
			        if ($(this).val() == moduleType2 ) {
			            $(this).attr("selected", "selected");
			        }
			    });
				$("#modifyModuleType2").attr("disabled","disabled");
				$("#modifyModuleType2").selectBoxIt().data("selectBoxIt");
				$("#modifyModuleType2").data("selectBox-selectBoxIt").refresh();
				$("#spName").attr("disabled","disabled");
				jQuery('#new_module_sp_div').modal('show', {backdrop: 'static'});
			}
		});
		
	});
	
	$("body").delegate('.moduleDeleteLink', 'click', function(){
		var spId = $(this).attr("aid");
		jConfirm("是否确定删除？","提醒",function(r) {
	    	if(r){
	    		window.location.href=basePath+"deleteModuleSP?spId="+spId;
	    	}
	    });
	});
	$("body").delegate('.moduleRelateLink', 'click', function(){
		handleRelation($(this).attr("aid"));
	});
	
	$("body").delegate('.moduleViewLink', 'click', function(){
		jAlert("功能建设中：暂不支持查看供应商信息","提示");
	});
});

function refreshDataTable(moduleType2){
	var spTypeCode = $("#spTypeCode").val();
	jQuery.ajax({
		url: basePath + "getModuleSPByModuleType",
		data:{
			moduleType2 : moduleType2,
			spTypeCode : spTypeCode
		}
		,success: function(response){
			var spList = response.spList;
			var moduleTable = $("#moduleSPTable");
			moduleTable.find('tbody').html("");
        	var tbody = "";
        	$.each(spList,function(n,sp){
        		tbody = tbody + "<tr>";
        		tbody = tbody + "<td >"+sp.spName+"</td>";
        		tbody = tbody + "<td>"+sp.proServiceName+"</td>";
        		tbody = tbody + "<td>"+sp.spTel+"</td>";
        		tbody = tbody + "<td>"+sp.fromSource+"</td>";
        		tbody = tbody + "<td>"+sp.relationStatus+"</td>";
        		tbody = tbody + "<td><a aid=\""+sp.id+"\" href=\"#\" class=\"btn btn-orange btn-sm btn-icon icon-left moduleViewLink\">查看</a>";
        		tbody = tbody + "<a aid=\""+sp.id+"\" href=\"#\" class=\"btn btn-secondary btn-sm btn-icon icon-left moduleEditLink\">编辑</a>";
        		tbody = tbody + "<a aid=\""+sp.id+"\" href=\"#\" class=\"btn btn-blue btn-sm btn-icon icon-left moduleRelateLink\">关联</a>";
        		tbody = tbody + "<a aid=\""+sp.id+"\" href=\"#\" class=\"btn btn-black btn-sm btn-icon icon-left moduleDeleteLink\">删除</a></td></tr>";
        	});
        	moduleTable.find('tbody').html(tbody);
		}
	});
}

function handleRelation(spId){
	jQuery.ajax({
		url: basePath + "handleSpRelation",
		data:{
			spId : spId
		}
		,success: function(response){
			var result = response.result;
			if( result == 'N' ){
				jAlert("未发现该注册公司","提示");
			}else if( result == 'Y' ){
				jAlert("已关联成功","提示",function(){
					window.location.reload();
				});
			}else{
				jAlert("系统错误，关联失败！请重新登录，或联系系统管理员","错误");
			}
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
	return true;
}

function populateModuleType2ByType1(type2NodeId,moduleType1){
	jQuery.ajax({
		url: goodsDictionaryURL,
		data:{
			dicType : moduleType1
		},
		success: function(response){
			var modules = response.dics;
			var optionList = $("#"+type2NodeId);
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
