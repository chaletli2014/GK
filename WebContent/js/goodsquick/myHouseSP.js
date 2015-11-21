jQuery(document).ready(function($){
	$(".relation_delete").click(function(){
		var relationShipIdIndex = this.id.indexOf('_');
		var relationShipId = this.id.substring(0,relationShipIdIndex);
		var propertyColumn = this.id.substring(relationShipIdIndex+1);
		jConfirm("是否确定取消绑定该服务商？","信息",function(r) {
	    	if(r){
	    		window.location.href=basePath+"updateRelationShip?type=1&relationShipId="+relationShipId+"&propertyColumn="+propertyColumn;
	    	}
	    });
	});
	$(".relation_update").click(function(){
		var relationShipIdIndex = this.id.indexOf('_');
		var relationShipId_p = this.id.substring(0,relationShipIdIndex);
		var propertyColumn_p = this.id.substring(relationShipIdIndex+1);
		
		jPrompt("请输入要绑定的服务商名称","","更换服务商",function(value) {
			if( value == '' ){
				jAlert("更新的服务商名称不能为空","错误");
			} else if( value == null){
				
			} else {
				jQuery.ajax({
					url: basePath+"updateRelationShipAjax",
					data:{
						type : 2,
						relationShipId : relationShipId_p,
						propertyColumn : propertyColumn_p,
						propertyValue : value
					}
					,success: function(response){
						var result = response.result;
						if( "Y" == result ){
							jAlert("更新成功","提示",function(){
								window.location.href=basePath+"myHouseSP";
							});
						}
					}
				});
			}
		});
	});
	$("#addNewSP").click(function(){
		jQuery('#new_module_sp_div').modal('show', {backdrop: 'static'});
	});
	$("#editSpLink").click(function(){
		jQuery('#new_module_sp_div').modal('show', {backdrop: 'static'});
	});
	$("#newSecondSP").click(function(){
		jQuery('#new_second_module_sp_div').modal('show', {backdrop: 'static'});
	});
});
