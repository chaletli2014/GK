var goodsDictionaryURL = basePath + "getGoodsDics";
var dics;
jQuery(document).ready(function($){
	initDics('trusteeship_module');
	
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

function initDataTable(){
	$("#moduleSPTable").dataTable({
		dom: "t" + "<'row'<'col-xs-3'i><'col-xs-9'p>>",
		aoColumns: [
        null,
        null,
        null,
        null
        ],
        "aoColumnDefs": [
         {
        	 "render": function (data, type, full) {
        		 return getDicNameByCode(dics,data);
        	 },
        	 "targets": 0
         },
         {
        	 "render": function (data, type, full) {
        		 return data;
        	 },
        	 "targets": 1
         },
         {
        	 "render": function (data, type, full) {
        		 return data;
        	 },
        	 "targets": 2
         },
         ]
	});
}
function initDropDown(){
	var options="";
	$.each(dics,function(i,item){
		options = options + "<option value='"+item.dicCode+"'>"+item.dicName+"</option>";
	});
	$("#secondModuleType").append(options);
	$("#childGoodsCategory").selectBoxIt().data("selectBoxIt");
	$("#secondModuleType").data("selectBox-selectBoxIt").refresh();
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
			initDropDown();
		}
	});
}