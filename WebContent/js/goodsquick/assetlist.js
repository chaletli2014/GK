var devicetable;

jQuery(document).ready(function($){
	
	initDeviceTable();
//	doSearch();
	
	$("#asset_a_overview").click(function(){
		assetNavHandle($(this));
		$("#asset_overview").slideDown(500);
		$(this).parent().addClass("active");
	});
	$("#asset_a_subject").click(function(){
		assetNavHandle($(this));
		$("#asset_subject").slideDown(500);
		$(this).parent().addClass("active");
	});
	$("#asset_a_subject2").click(function(){
		assetNavHandle($(this));
		$("#asset_subject2").slideDown(500);
		$(this).parent().addClass("active");
	});
	$("#asset_a_subject3").click(function(){
		assetNavHandle($(this));
		$("#asset_subject3").slideDown(500);
		$(this).parent().addClass("active");
	});
	$("#asset_a_device").click(function(){
		assetNavHandle($(this));
		$("#asset_device").slideDown(500);
		$(this).parent().addClass("active");
	});
	$("#asset_a_other").click(function(){
		assetNavHandle($(this));
		$("#asset_other").slideDown(500);
		$(this).parent().addClass("active");
	});
});

function assetNavHandle(nav){
	$(".asset_div").css({'display':'none'});
	nav.parent().parent().children().removeClass("active");
}

function initDeviceTable(){
	$("#assetDeviceTable").dataTable({
		dom: "t" + "<'row'<'col-xs-3'i><'col-xs-9'p>>"
	});
}

function doSearch() {  
	if(devicetable!=null){
		devicetable.fnClearTable(0);  
		devicetable.fnDraw(); //重新加载数据  
    }else{
    	devicetable = $("#assetDeviceTable").dataTable({
            "bStateSave" : true,  
            "bJQueryUI" : true,  
            "bPaginate" : true,// 分页按钮  
            "bFilter" : false,// 搜索栏  
            "bLengthChange" : true,// 每行显示记录数  
            "iDisplayLength" : 10,// 每页显示行数  
            "bSort" : false,// 排序  
            "bInfo" : true,// Showing 1 to 10 of 23 entries 总记录数没也显示多少等信息  
            "bWidth" : true,  
            "bScrollCollapse" : true,  
            "sPaginationType" : "full_numbers", // 分页，一共两种样式 另一种为two_button // 是datatables默认  
            "bProcessing" : true,  
            "bServerSide" : true,  
            "bDestroy" : true,  
            "bSortCellsTop" : true,  
            "sAjaxSource" : basePath+"getassetdevicelist",  
            "sScrollY": "100%",
            "fnInitComplete": function() {  
                this.fnAdjustColumnSizing(true);  
             },
            "aoColumns" : [
               {"mDataProp": "eqTypeName"},	
			   {"mDataProp": "name"},
			   {"mDataProp": "eqDesc"},
			   null,
			   null
            ],
            "aoColumnDefs":[
                {  
                    "aTargets":[0],
                    "visible":true  
                },  
                {  
                    "aTargets":[1],  
                    "visible":true  
                },  
                {  
                    "aTargets":[2],  
                    "visible":true  
                },
                {  
                    "aTargets":[3],  
                    "visible":true  
                },
                {  
                    "aTargets":[4],  
                    "visible":true  
                }
            ],  
            "fnRowCallback" : function(nRow, aData, iDisplayIndex) {//相当于对字段格式化  
                
            },  
            "fnServerData" : function(sSource, aoData, fnCallback) {  
                $.ajax({
                    "type" : 'get',  
                    "url" : sSource,  
                    "dataType" : "json",
                    "data" : {
                        aoData : JSON.stringify(aoData)  
                    },  
                    "success" : function(resp) {
                        fnCallback(resp.deviceJson);  
                    }
                });  
            }  
        });  
    }}