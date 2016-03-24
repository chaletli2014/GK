var newTrCount;
var newTrNum = new Array();

jQuery(document).ready(function($){
	initDeviceTable();
	populateDropdownByDic("lift_type","deviceType");
	populateDropdownByDic("lift_brand","deviceBrand");
	populateDropdownByDic("lift_purpose","devicePurpose");
	populateDropdownByDic("lift_style","deviceStyle");
	populateDropdownByDic("lift_QA","deviceQA");
	populateDropdownByDic("lift_mainPower","mainPower");
	populateDropdownByDic("lift_lifeTime","lifeTime");
	
	$("#newDevice").click (function(){
		createNewDeviceTr();
	});
	
	$("#saveDevice").click (function(){
		saveDevice();
	});
	$("#saveLiftBtn").click(function(){
		saveDeviceInfo();
		return false;
	});
	
	$(".showDevice").click(function(){
		emptyDeviceForm();
		showDevice();
	});
	
	$("body").delegate('.modifyDevice', 'click', function(){
		jQuery.ajax({
			url: basePath+"getDeviceByIdAndType",
			data:{
				deviceId : $(this).attr("id"),
				deviceType : $(this).attr("dtype")
			},
			success: function(response){
				populateLiftBasicInfo(response.deviceObj);
				jQuery('#houseDeviceDiv').modal('show', {backdrop: 'static'});
				$("#houseDeviceDiv").on("shown.bs.modal",function(e){
					populateLiftMainInfo(response.deviceObj);
				});
			}
		});
	});
	$("body").delegate('.removeDevice', 'click', function(){
		var deviceId = $(this).attr("id");
		var deviceType = $(this).attr("dtype");
		jConfirm("是否确定删除？","提醒",function(r) {
	    	if(r){
	    		window.location.href=basePath+"deleteDeviceByIdAndType?deviceId="+deviceId+"&deviceType="+deviceType;
	    	}
	    });
	});
	
	$("body").delegate('.newTrCancle', 'click', function(){
		$(this).parent().parent().remove();
	});
});

function populateLiftMainInfo(deviceObj){
	var deviceBrandValue = deviceObj.brandCode;
	$("#deviceBrand option").each(function() {
        if ($(this).val() == deviceBrandValue ) {
            $(this).attr("selected", "selected");
        }
        $("#deviceBrand").selectBoxIt().data("selectBoxIt");
		$("#deviceBrand").data("selectBox-selectBoxIt").refresh();
    });
	
	var liftPurposeValue = deviceObj.liftPurpose;
	$("#devicePurpose option").each(function() {
		if ($(this).val() == liftPurposeValue ) {
			$(this).attr("selected", "selected");
		}
		$("#devicePurpose").selectBoxIt().data("selectBoxIt");
		$("#devicePurpose").data("selectBox-selectBoxIt").refresh();
	});
	
	var liftStyleValue = deviceObj.liftStyle;
	$("#deviceStyle option").each(function() {
		if ($(this).val() == liftStyleValue ) {
			$(this).attr("selected", "selected");
		}
		$("#deviceStyle").selectBoxIt().data("selectBoxIt");
		$("#deviceStyle").data("selectBox-selectBoxIt").refresh();
	});
	
	$("#deviceCT").val(deviceObj.liftCT);
	$("#deviceNS").val(deviceObj.liftNS);
	
	var liftQAValue = deviceObj.liftQA;
	$("#deviceQA option").each(function() {
		if ($(this).val() == liftQAValue ) {
			$(this).attr("selected", "selected");
		}
		$("#deviceQA").selectBoxIt().data("selectBoxIt");
		$("#deviceQA").data("selectBox-selectBoxIt").refresh();
	});
	
	$("#carSize").val(deviceObj.carSize);
	$("#carHeight").val(deviceObj.carHeight);
	$("#doorSize").val(deviceObj.doorSize);
	
	var mainPowerValue = deviceObj.mainPower;
	$("#mainPower option").each(function() {
		if ($(this).val() == mainPowerValue ) {
			$(this).attr("selected", "selected");
		}
		$("#mainPower").selectBoxIt().data("selectBoxIt");
		$("#mainPower").data("selectBox-selectBoxIt").refresh();
	});
	
	$("#madeDate").val(deviceObj.madeDate);

	var lifeTimeValue = deviceObj.lifeTime;
	$("#lifeTime option").each(function() {
		if ($(this).val() == lifeTimeValue ) {
			$(this).attr("selected", "selected");
		}
		$("#lifeTime").selectBoxIt().data("selectBoxIt");
		$("#lifeTime").data("selectBox-selectBoxIt").refresh();
	});
}

function populateLiftBasicInfo(deviceObj){
	$("#deviceId").val(deviceObj.id);
	$("#subjectId").val(deviceObj.subjectId);
	$("#subjectName").val(deviceObj.subjectName);
	$("#moduleId").val(deviceObj.moduleId);
	$("#moduleName").val(deviceObj.moduleName);
	var deviceTypeValue = deviceObj.liftType;
	$("#deviceType option").each(function() {
        if ($(this).val() == deviceTypeValue ) {
            $(this).attr("selected", "selected");
        }
        $("#deviceType").selectBoxIt().data("selectBoxIt");
		$("#deviceType").data("selectBox-selectBoxIt").refresh();
    });
	
	$("#deviceCode").val(deviceObj.liftCode);
	$("#deviceName").val(deviceObj.liftName);
	$("#deviceDesc").val(deviceObj.liftDesc);
	$("#deliveryDate").val(deviceObj.deliveryDate);
	$("#deviceQA").val(deviceObj.liftQA);
	$("#purchasePrice").val(deviceObj.purchasePrice);
	$("#deviceUser").val(deviceObj.userName);
}

function createNewDeviceTr(){
	if( newTrCount && newTrCount != 0 ){
		newTrCount = newTrCount + 1;
	}else{
		newTrCount = 1;
	}
	
	var newTr = "<tr class=\"newDeviceTr\">";
	var deviceTypeList = "<select id=\"deviceTypeSelection"+newTrCount+"\" name =\"deviceTypeSelection\"><option value=\"\">--请选择--</option>";
	deviceTypeList = deviceTypeList + "<option value=\"dt\">电梯</option>";
	deviceTypeList = deviceTypeList + "</select>";
	
	newTr = newTr + "<td>"+deviceTypeList+"</td>";
	newTr = newTr + "<td><input type=\"text\" class=\"form-control\" id=\"deviceName"+newTrCount+"\" name=\"deviceName\"></td>";
	newTr = newTr + "<td><input type=\"text\" class=\"form-control\" id=\"deviceDesc"+newTrCount+"\" name=\"deviceDesc\"></td>";
	jQuery.ajax({
		url: basePath+"getSubjectList",
		success: function(response){
			var result = response.result;
			var subjectList = response.subjectList;
			if( result == 'Y' ){
				var subjectOptionList = "<select id=\"subjectSelection"+newTrCount+"\" name =\"subjectSelection\" onchange=\"populateModuleBySubject(this.value,"+newTrCount+")\"><option value=\"\">--请选择主体--</option>";
				$.each(subjectList,function(n,subjectObj){
					subjectOptionList = subjectOptionList + "<option value='"+subjectObj.id+"'>"+subjectObj.name+"</option>";
	        	});
				subjectOptionList = subjectOptionList + "</select>";
				
				var moduleOptionList = "<select id=\"moduleSelection"+newTrCount+"\" name =\"moduleSelection\"><option value=\"\">--请选择构件--</option>";
				newTr = newTr + "<td>"+subjectOptionList+" - "+moduleOptionList+"</td>";
				newTr = newTr + "<td><a class=\"btn btn-danger btn-sm btn-icon icon-left fa-close newTrCancle\">取消</a></td>";
				newTr = newTr + "</tr>";
				$("#deviceTable tbody").prepend(newTr);
			}
		}
	});
	
	newTrNum.push(newTrCount);
}

function saveDevice(){
	var deviceArray = new Array();
	for(var i=0;i<newTrNum.length;i++){
		var deviceObj = new Object();
		deviceObj.eqTypeCode = $("#deviceTypeSelection"+newTrNum[i]).val();
		deviceObj.name = $("#deviceName"+newTrNum[i]).val();
		deviceObj.eqDesc = $("#deviceDesc"+newTrNum[i]).val();
		deviceObj.subjectId = $("#subjectSelection"+newTrNum[i]).val();
		deviceObj.moduleId = $("#moduleSelection"+newTrNum[i]).val();
		
		if( typeof(deviceObj.name) == "undefined" ){
			continue;
		}
		
		if( deviceObj.eqTypeCode == '' ){
			jAlert("设备分类必选","提醒");
			return false;
		}
		
		if( deviceObj.name == '' ){
			jAlert("主体名称不能为空","提醒");
			return false;
		}
		
		if( deviceObj.subjectId == '' ){
			jAlert("主体不能为空","提醒");
			return false;
		}
		
		if( deviceObj.moduleId == '' ){
			jAlert("构件不能为空","提醒");
			return false;
		}
		
		deviceArray.push(deviceObj);
	}
	
	if( deviceArray.length == 0 ){
		jAlert("未新增任何数据，无法保存","错误");
		return false;
	}
	
	jQuery.ajax({
		url: basePath+"saveOrUpdateDevice",
		data : {
			deviceList : JSON.stringify(deviceArray)
		},
		success: function(response){
			var result = response.result;
			if( result == 'Y' ){
				jAlert("新增成功","提醒",function(){
					window.location.reload();
				});
			}
		}
	});
}

function saveDeviceInfo(){
	var liftObj = new Object();
	liftObj.id = $("#deviceId").val();
	liftObj.subjectId = $("#subjectId").val();
	liftObj.moduleId = $("#moduleId").val();
	liftObj.liftType = $("#deviceType").val();
	liftObj.liftCode = $("#deviceCode").val();
	liftObj.liftName = $("#deviceName").val();
	liftObj.liftDesc = $("#deviceDesc").val();
	if( $("#deliveryDate").val() != '' ){
		liftObj.deliveryDate = $("#deliveryDate").val();
	}
	liftObj.liftQA = $("#deviceQA").val();
	if( $("#purchasePrice").val() != '' ){
		liftObj.purchasePrice = $("#purchasePrice").val();
	}
	liftObj.userName = $("#deviceUser").val();
	liftObj.brandCode = $("#deviceBrand").val();
	liftObj.liftPurpose = $("#devicePurpose").val();
	liftObj.liftStyle = $("#deviceStyle").val();
	if( $("#deviceCT").val() != '' ){
		liftObj.liftCT = $("#deviceCT").val();
	}
	if( $("#deviceNS").val() != '' ){
		liftObj.liftNS = $("#deviceNS").val();
	}
	if( $("#carSize").val() != '' ){
		liftObj.carSize = $("#carSize").val();
	}
	if( $("#carHeight").val() != '' ){
		liftObj.carHeight = $("#carHeight").val();
	}
	
	if( $("#doorSize").val() != '' ){
		liftObj.doorSize = $("#doorSize").val();
	}
	
	if( $("#mainPower").val() != '' ){
		liftObj.mainPower = $("#mainPower").val();
	}
	
	if( $("#madeDate").val() != '' ){
		liftObj.madeDate = $("#madeDate").val();
	}
	
	if( $("#lifeTime").val() != '' ){
		liftObj.lifeTime = $("#lifeTime").val();
	}
	jQuery.ajax({
		url: basePath+"modifyDevice",
		data : {
			deviceObj : JSON.stringify(liftObj),
			deviceType : "dt"
		},
		success: function(response){
			var result = response.result;
			if( result == 'Y' ){
				jAlert("编辑成功","提醒",function(){
					window.location.reload();
				});
			}
		}
	});
}

function showModule(subjectId){
	$('#rightBody', window.parent.document).fadeOut();
	$('#rightBody', window.parent.document).fadeIn();
	
	var moduleTable = $('#bodyModuleDetailTable',window.parent.document);
	
	jQuery.ajax({
		url: basePath+"subjectmodulelist",
		data:{
			subjectId :subjectId
		},
        error : function() {
        },
        success : function(data) {
        	moduleTable.find('tbody').html("");
        	var trList = data.modules;
        	var tbody = "";
        	$.each(trList,function(n,module){
        		tbody = tbody + "<tr><td >"+module.moduleName+"</td><td >"+module.moduleDesc+"</td></tr>";
        	});
        	moduleTable.find('tbody').html(tbody);
        }
    });
}

function initDeviceTable(){
	$("#deviceTable").dataTable({
		dom: "t" + "<'row'<'col-xs-3'i><'col-xs-9'p>>"
	});
}
function emptyDeviceForm(){
	$("#moduleType").selectBoxIt().data("selectBoxIt");
	$("#moduleType").data("selectBox-selectBoxIt").refresh();
	$("#moduleName").val('');
	$("#moduleDesc").val('');
}