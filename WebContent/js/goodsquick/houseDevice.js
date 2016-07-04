var newTrCount;
var newTrNum = new Array();
var deviceTable;

jQuery(document).ready(function($){
	initDeviceTable();
	
	$("#newDevice").click (function(){
		createNewDeviceTr();
	});
	
	$("#saveDevice").click (function(){
		saveDevice();
	});
	$("#saveDeviceBasicBtn").click(function(){
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
				populateBasicInfo(response.deviceObj);
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
	
	$("body").delegate('.asset_a_device', 'click', function(){
		$("#deviceDiv").css("display","none");
		$("#deviceDiv").slideDown(500);
		$(this).parent().parent().children().removeClass("hover-line active");
		$(this).parent().addClass("hover-line active");
		$("#eqTypeCode_h").val($(this).attr("aid"));
		refreshTables();
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

function populateBasicInfo(deviceObj){
	$("#deviceId").val(deviceObj.id);
	$("#subjectId").val(deviceObj.subjectId);
	$("#subjectName_device").val(deviceObj.subjectName);
	$("#moduleId").val(deviceObj.moduleId);
	$("#moduleName_device").val(deviceObj.moduleName);
	$("#eqTypeName").val(deviceObj.eqTypeName);
	$("#deviceName").val(deviceObj.name);
	$("#deviceDesc").val(deviceObj.eqDesc);
	$("#deviceBrand").val(deviceObj.brand);
	$("#deviceStyle").val(deviceObj.style);
	$("#manufacturer").val(deviceObj.manufacturer);
	$("#velocity").val(deviceObj.velocity);
	$("#num").val(deviceObj.num);
	$("#enableTime").val(deviceObj.enableTime);
	$("#maintenanceUnit").val(deviceObj.maintenanceUnit);
	$("#telephone").val(deviceObj.telephone);
	$("#remark").val(deviceObj.remark);
}

function createNewDeviceTr(){
	if( newTrCount && newTrCount != 0 ){
		newTrCount = newTrCount + 1;
	}else{
		newTrCount = 1;
	}
	
	var newTr = "<tr class=\"newDeviceTr\">";
	var deviceTypeList = "<select id=\"deviceTypeSelection"+newTrCount+"\" name =\"deviceTypeSelection\"><option value=\"\">--请选择--</option>";
	
	var deviceTypeArray = eval($("#assetDeviceTypeArray").val());
	$.each(deviceTypeArray,function(n,value) {
        deviceTypeList = deviceTypeList + "<option value=\""+value.dicCode+"\">"+value.dicName+"</option>";
	});
	
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
	var deviceObj = new Object();
	deviceObj.id = $("#deviceId").val();
	deviceObj.subjectId = $("#subjectId").val();
	deviceObj.moduleId = $("#moduleId").val();
	deviceObj.eqTypeCode = $("#deviceType").val();
	deviceObj.name = $("#deviceName").val();
	deviceObj.eqDesc = $("#deviceDesc").val();
	deviceObj.brand = $("#deviceBrand").val();
	deviceObj.style = $("#deviceStyle").val();
	deviceObj.manufacturer = $("#manufacturer").val();
	deviceObj.velocity = $("#velocity").val();
	deviceObj.num = $("#num").val();
	if( $("#enableTime").val() != '' ){
		deviceObj.enableTime = $("#enableTime").val();
	}
	deviceObj.maintenanceUnit = $("#maintenanceUnit").val();
	deviceObj.telephone = $("#telephone").val();
	deviceObj.remark = $("#remark").val();
	jQuery.ajax({
		url: basePath+"modifyDevice",
		data : {
			deviceObj : JSON.stringify(deviceObj)
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

function refreshTables(){
	deviceTable.fnClearTable(0);
	deviceTable.fnDraw(); //重新加载数据  
}

function initDeviceTable(){
	deviceTable = $("#deviceTable").dataTable({
	        "bProcessing": false, // 是否显示取数据时的那个等待提示
	        "bServerSide": true,//这个用来指明是通过服务端来取数据
	        "sAjaxSource": basePath+"getassetdevicelist",//这个是请求的地址
	        "fnServerData": retrieveData, // 获取数据的处理函数
	        "sAjaxDataProp": "aaData",
	        "bLengthChange": false,
	        "searching": false,
	        "fnServerParams" : function(aoData) {  
                aoData.push({
                    "name":"eqTypeCode","value":$("#eqTypeCode_h").val()
                });  
            },
	        "aoColumns": [
	            {"mDataProp":"eqTypeName"},
	             {"mDataProp":"name"},
	             {"mDataProp":"eqDesc"},
	             {"mDataProp":"subjectName"},
	             {"mDataProp":"id"}
	        ],
	        aoColumnDefs : [ 
				{ 
					"aTargets" :　[3], "mRender" : function(data, type, full){
						return full.subjectName + "-" + full.moduleName;
					}
				},
	            { "aTargets" :　[4], "mRender" : function(data, type, full){
		        		var editlink = "<a id='"+full.id+"' dtype='"+full.eqTypeCode+"' class='btn btn-secondary btn-sm btn-icon icon-left modifyDevice'>编辑</a>";
		        		var dellink = "<a id='"+full.id+"' dtype='"+full.eqTypeCode+"' class='btn btn-danger btn-sm btn-icon icon-left removeDevice'>删除</a>";
		        		return editlink + dellink;
	        		} 
	            }
			]
    });
}
function retrieveData( sSource111,aoData111, fnCallback111) {
    $.ajax({
        url : sSource111,//这个就是请求地址对应sAjaxSource
        data : {"aoData":JSON.stringify(aoData111)},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
        type : 'post',
        dataType : 'json',
        dataSrc: "aaData",
        async : false,
        success : function(result) {
            fnCallback111(result);//把返回的数据传给这个方法就可以了,datatable会自动绑定数据的
        },
        error : function(msg) {
        }
    });
}

function emptyDeviceForm(){
	$("#moduleType").selectBoxIt().data("selectBoxIt");
	$("#moduleType").data("selectBox-selectBoxIt").refresh();
	$("#moduleName_housedevice").val('');
	$("#moduleDesc").val('');
}