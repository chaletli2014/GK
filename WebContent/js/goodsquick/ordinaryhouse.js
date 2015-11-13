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
		$("#rightBody").fadeOut();
		$("#rightBody").fadeIn();
	});
	$("#editHouseLink").click(function(){
		jQuery('#newHouseDiv').modal('show', {backdrop: 'static'});
	});
	$("#bodyModuleType").change(function(){
		jQuery.ajax({
			url: basePath+"getBodyDetailsByType",
			data:{
				moduleType :this.value
			},
	        error : function() {
	        },
	        success : function(data) {
	        	$('#moduleBodyDetailTable').find('tbody').html("");
	        	var trList = data.dataList;
	        	var tbody = "";
	        	$.each(trList,function(n,value){
	        		tbody = tbody + "<tr><td >"+value+"</td><td >"+value+"</td></tr>";
	        	});
	        	$('#moduleBodyDetailTable').find('tbody').html(tbody);
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
});
