var goodsDictionaryURL = basePath + "getGoodsDics";
var dics;

jQuery(document).ready(function($){
	initDics('trusteeship_module');
	
	$("#newModuleSPLink").click(function(){
		jQuery('#new_module_sp_div').modal('show', {backdrop: 'static'});
	});
});

function submitModuleSP(){
	if( $("#spName").val() == '' ){
		jAlert("组件商名称不能为空","错误");
		return false;
	}
	if( $("#moduleType").val() == '' ){
		jAlert("组件不能为空","错误");
		return false;
	}
	
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