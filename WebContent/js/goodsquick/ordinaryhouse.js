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
});
