var setting = {
	async: {
		enable: true,
		url:basePath+"subjectViewList",
		autoParam:["id"]
	},
	data: {
		simpleData: {
			enable: true
		}
	},
	callback: {
		onClick : subjectNodeClick
	}
};

var viewSetting = {
		async: {
			enable: true,
			autoParam:["id"]
		},
		data: {
			simpleData: {
				enable: true
			}
		}
	};

jQuery(document).ready(function($){
	$(".houseNameLink").click(function(){
		var houseId = this.tabIndex;
		jQuery.ajax({
			url: basePath+"getHouseDetails",
			data:{
				houseId :houseId
			}
			,success: function(response){
				var result = response.result;
				var obj = response.obj;
				var deviceList = response.deviceList;
				if( result == 'Y' ){
					$("#show_buildingName").html(obj.buildingName);
					$("#show_province").html(obj.province);
					$("#show_city").html(obj.city);
					$("#show_location").html(obj.location);
					$("#show_company").html(obj.company);
					$("#show_projectPositionE").html(obj.projectPositionE);
					$("#show_projectPositionW").html(obj.projectPositionW);
					$("#show_projectPositionS").html(obj.projectPositionS);
					$("#show_projectPositionN").html(obj.projectPositionN);
					$("#show_finishYear").html(obj.finishYear+" 年");
					$("#show_finishMonth").html(obj.finishMonth+" 月");
					$("#show_finishDate").html(obj.finishDate+" 日");
					$("#show_startYear").html(obj.startYear+" 年");
					$("#show_startMonth").html(obj.startMonth+" 月");
					$("#show_startDate").html(obj.startDate+" 日");
					$("#show_checkinYear").html(obj.checkinYear+" 年");
					$("#show_checkinMonth").html(obj.checkinMonth+" 月");
					$("#show_checkinDate").html(obj.checkinDate+" 日");
					$("#show_floorSpace").html(obj.floorSpace);
					
					
					
					jQuery('#showHouseDiv').modal('show', {backdrop: 'static'});
				}else if( result == 'N' ){
					jAlert("您已经申请过该产品","提醒");
				}else if( result == 'E' ){
					jAlert(response.message,"提醒");
				}
			}
		});
	});
	$("#publicBody").click(function(){
		jQuery('#houseModuleDiv_body').modal('show', {backdrop: 'static'});
		jQuery('#houseSubjectFrame').attr("src",basePath+"houseSubjectiframe");
	});
	$("#publicInstallation").click(function(){
		jQuery('#houseModuleDiv_installation').modal('show', {backdrop: 'static'});
	});
	$("#publicEquipment").click(function(){
		jQuery('#houseModuleDiv_equipment').modal('show', {backdrop: 'static'});
	});
	$(".module_body_detail").click(function(){
		$(".module_detail_cursorTd").removeClass("module_detail_cursorTd");
//		$(this).parent().css("background","red");
		$(this).parent().attr("class","module_detail_cursorTd");
		$("#newBodyDiv").fadeOut();
		$("#newBodyModuleDiv").fadeOut();
		
		$("#rightBody").fadeOut();
		$("#rightBody").fadeIn();
	});
	$("#editHouseLink").click(function(){
		jQuery('#newHouseDiv').modal('show', {backdrop: 'static'});
	});
	$("#bodyModuleType").change(function(){
		var subjectId = $("#houseSubjectId").val();
		
		jQuery.ajax({
			url: basePath+"getBodyDetailsByType",
			data:{
				moduleType :this.value,
				subjectId : subjectId
			},
	        error : function() {
	        },
	        success : function(data) {
	        	$('#bodyModuleDetailTable').find('tbody').html("");
	        	var trList = data.dataList;
	        	var tbody = "";
	        	$.each(trList,function(n,value){
	        		tbody = tbody + "<tr><td >"+value.moduleName+"</td><td >"+value.moduleDesc+"</td></tr>";
	        	});
	        	$('#bodyModuleDetailTable').find('tbody').html(tbody);
	        }
	    });
	});
	$("#newEquLink").click(function(){
		$("#equInfo").css("width","50%");
		$("#newEquDiv").fadeIn();
	});
	$("#equ_cancelBtn").click(function(){
		$("#newEquDiv").fadeOut();
		setTimeout(function(){
			$("#equInfo").css("width","80%");
		}, 300);
	});
	
	$("#newBodyLink").click(function(){
		$("#bodyInfo").css("width","50%");
		$("#rightBody").fadeOut();
		
		$("#newBodyDiv").fadeIn();
	});
	$("#body_cancelBtn").click(function(){
		$("#newBodyDiv").fadeOut();
		setTimeout(function(){
			$("#bodyInfo").css("width","80%");
		}, 300);
	});
	
	$("#newBodyModuleLink").click(function(){
		clearNewModuleForm();
		$("#bodyModuleDetailDiv").fadeOut();
		$("#newBodyModuleDiv").fadeIn();
	});
	$("#bodyModule_cancelBtn").click(function(){
		$("#newBodyModuleDiv").fadeOut();
		$("#bodyModuleDetailDiv").fadeIn();
	});
	
	$("#newOtherLink").click(function(){
		$("#otherInfo").css("width","50%");
		$("#newOtherDiv").fadeIn();
	});
	$("#other_cancelBtn").click(function(){
		$("#newOtherDiv").fadeOut();
		setTimeout(function(){
			$("#otherInfo").css("width","80%");
		}, 300);
	});
	$("#bodyModule_submitBtn").click(function(){
		modifySubjectModule();
	});
	$("#eq_submitBtn").click(function(){
		modifyHouseDevice();
	});
	$("#eqSubject").change(function(){
		populateModule();
	});
	$("#houseSourceFileBtn").click(function(){
		uploadHouseSourceFile();
		return false;
	});
	$(".isMainPicCheckbox").change(function(){
	    if ($(this)[0].checked) {
          alert( $(this).attr('value') );
	    }
	});
	$.fn.zTree.init($("#subjectTree"), setting);
});

function subjectNodeClick(event, treeId, treeNode){
	initModuleNodes(treeNode.id);
//	initDeviceNodes(treeNode.id);
//	initOtherNodes(treeNode.id);
}

function initModuleNodes(subjectId){
	var nodeList = "[";
	jQuery.ajax({
		url: basePath+"subjectModuleNodes",
		type: 'POST',
		async: false,
		data : {
			subjectId : subjectId
		},
		dataType:"json",
		success: function(result) {
			var modules = result.modules;
			$.each(modules, function(i, item) {
				nodeList = nodeList + "{id:'" + item.id + "',pId:'0', name:'" + item.moduleName + "'},";
			});
			if( nodeList == '[' ){
				nodeList = nodeList + "]";
			}else{
				nodeList = nodeList.substring(0, nodeList.length - 1) + "]";
			}
			$.fn.zTree.init($("#moduleTree"), viewSetting, eval('(' + nodeList + ')'));
		}
	});
}

function initDeviceNodes(subjectId){
	var nodeList = "[";
	jQuery.ajax({
		url: basePath+"subjectmodulelist",
		type: 'POST',
		async: false,
		data : {
			subjectId : subjectId
		},
		dataType:"json",
		success: function(result) {
			var modules = result.modules;
			$.each(modules, function(i, item) {
				nodeList = nodeList + "{id:'" + item.id + "',pId:'0', name:'" + item.moduleName + "'},";
			});
			if( nodeList == '[' ){
				nodeList = nodeList + "]";
			}else{
				nodeList = nodeList.substring(0, nodeList.length - 1) + "]";
			}
			$.fn.zTree.init($("#moduleTree"), viewSetting, eval('(' + nodeList + ')'));
		}
	});
}

function initOtherNodes(subjectId){
	var nodeList = "[";
	jQuery.ajax({
		url: basePath+"subjectmodulelist",
		type: 'POST',
		async: false,
		data : {
			subjectId : subjectId
		},
		dataType:"json",
		success: function(result) {
			var modules = result.modules;
			$.each(modules, function(i, item) {
				nodeList = nodeList + "{id:'" + item.id + "',pId:'0', name:'" + item.moduleName + "'},";
			});
			if( nodeList == '[' ){
				nodeList = nodeList + "]";
			}else{
				nodeList = nodeList.substring(0, nodeList.length - 1) + "]";
			}
			$.fn.zTree.init($("#moduleTree"), viewSetting, eval('(' + nodeList + ')'));
		}
	});
}

function initSubjectLevel(level){
	var subjectLevelName = "subjectLevel"+level;
	var selectId = "#sLevel"+level;
	jQuery.ajax({
		url: basePath+"getGoodsDics",
		data:{
			dicType : subjectLevelName
		}
		,success: function(response){
			var dics = response.dics;
			var childSelection = "";
			
			$.each(dics,function(n,value) {
				childSelection = childSelection + "<option value='"+value.dicCode+"'>"+value.dicName+"</option>";
			});
			
			$(selectId).append(childSelection);
			
			$(selectId).selectBoxIt().data("selectBoxIt");
			$(selectId).data("selectBox-selectBoxIt").refresh();
		}
    });
}

function modifySubjectModule(){
	var subjectId = $("#houseSubjectId").val();
	var newBodyModuleType = $("#newBodyModuleType").val();
	var moduleName = $("#moduleName").val();
	var moduleDesc = $("#moduleDesc").val();
	jQuery.ajax({
		url: basePath+"modifySubjectModule",
		data:{
			subjectId : subjectId,
			moduleType : newBodyModuleType,
			moduleName : moduleName,
			moduleDesc : moduleDesc
		}
		,success: function(response){
			var result = response.result;
			if( result == 'Y' ){
				jAlert("保存成功","提醒",function(){
					jQuery.ajax({
						url: basePath+"subjectmodulelist",
						data:{
							subjectId :subjectId
						},
						success : function(data) {
							$("#bodyModuleDetailTable").find('tbody').html("");
							var trList = data.modules;
							var tbody = "";
							$.each(trList,function(n,module){
								tbody = tbody + "<tr><td >"+module.moduleName+"</td><td >"+module.moduleDesc+"</td></tr>";
							});
							$("#bodyModuleDetailTable").find('tbody').html(tbody);
							
							$("#newBodyModuleDiv").fadeOut();
							$("#bodyModuleDetailDiv").fadeIn();
						}
					});
				});
			}else{
				jAlert("保存失败","提醒");
			}
		}
    });
}

function modifyHouseDevice(){
	var eqType = $("#eqType_new").val();
	var eqName = $("#eqName").val();
	var eqBrand = $("#eqBrand").val();
	var eqStyle = $("#eqStyle").val();
	var eqDesc = $("#eqDesc").val();
	var subjectId = $("#eqSubject").val();
	var moduleId = $("#eqModule").val();
	jQuery.ajax({
		url: basePath+"saveHouseDevice",
		data:{
			eqType : eqType,
			eqName : eqName,
			eqBrand : eqBrand,
			eqStyle : eqStyle,
			eqDesc : eqDesc,
			eqSubject : subjectId,
			eqModule : moduleId
		}
	,success: function(response){
		var result = response.result;
		if( result == 'Y' ){
			jAlert("保存成功","提醒",function(){
				jQuery.ajax({
					url: basePath+"houseDevicelist",
					success : function(data) {
						$("#moduleEquDetailTable").find('tbody').html("");
						var trList = data.modules;
						var tbody = "";
						$.each(trList,function(n,module){
							tbody = tbody + "<tr><td >"+module.moduleName+"</td><td >"+module.moduleDesc+"</td></tr>";
						});
						$("#moduleEquDetailTable").find('tbody').html(tbody);
						
						$("#newEquDiv").fadeOut();
						$("#equInfo").fadeIn();
					}
				});
			});
		}else{
			jAlert("保存失败","提醒");
		}
	}
	});
}

function populateModule(){
	var subjectId = $("#eqSubject").val();
	jQuery.ajax({
		url: basePath+"subjectmodulelist",
		data:{
			subjectId : subjectId
		}
		,success : function(data) {
			var modules = data.modules;
			
			$("#eqModule option[value!='']").remove();
			var childSelection = "";
			
			$.each(modules,function(n,value) {
				childSelection = childSelection + "<option value='"+value.id+"'>"+value.moduleName+"</option>";
			});
			
			$("#eqModule").append(childSelection);
			
			$("#eqModule").selectBoxIt().data("selectBoxIt");
			$("#eqModule").data("selectBox-selectBoxIt").refresh();
		}
	});
}
function uploadHouseSourceFile(){
	var filename = $("#sourceFile").val();
	$.ajax({
      type: "POST",
      url: basePath+"uploadHouseSourceFile",
      enctype: 'multipart/form-data',
      data: {
    	  sourceFile: filename
      },
      success: function () {
        alert("Data Uploaded: ");
      }
    });
}

function clearNewModuleForm(){
	$("#newBodyModuleType").val('');
	$("#moduleName").val('');
	$("#moduleDesc").val('');
	$("#newBodyModuleType").selectBoxIt().data("selectBoxIt");
	$("#newBodyModuleType").data("selectBox-selectBoxIt").refresh();
}