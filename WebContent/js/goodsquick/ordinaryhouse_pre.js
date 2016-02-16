jQuery(document).ready(function($){
	var basePath = $("#basePath").val();
	
	initDics('productType1');
	
	$(".product_link").click(function(){
		var curPath=window.document.location.href;
		var pathName=window.document.location.pathname;
		var pos=curPath.indexOf(pathName);  
	    var localhostPath=curPath.substring(0,pos);
	    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);

	    var fullPath = localhostPath + projectName;
	    
	    window.location.href=fullPath+"/newProductPreOne?type="+this.title;
	});
	
	$("#finishWizard").click(function(){
		if(!$("#buildingName") || $("#buildingName").val() == ''){
			jAlert("产品名称必填，否则无法完成注册","注意");
			return false;
		}if(!$("#province") || $("#province").val() == '' || !$("#city") || $("#city").val() == ''){
			jAlert("产品所在地域必填，否则无法完成注册","注意");
			return false;
		}if(!$("#location") || $("#location").val() == ''){
			jAlert("坐落位置必填，否则无法完成注册","注意");
			return false;
		}else{
			jQuery.ajax({
				url: basePath+"checkBuildingName",
				data:{
					houseName : $("#buildingName").val()
				}
				,success: function(response){
					var result = response.result;
					var obj = response.obj;
					
					if( result == 'own' ){
						jAlert("您已经拥有了该产品，无法继续添加","提醒");
						return false;
					}else if( result == 'sent' ){
						jAlert("您已经申请过该产品","提醒");
						return false;
					}else if( result == 'Y' ){
						jConfirm("该产品已经存在无法继续添加，是否直接申请权限？","信息",function(r) {
					    	if(r){
					    		jQuery.ajax({
					    			url: basePath+"sendRequestToBuildingName",
					    			data:{
					    				houseName : $("#buildingName").val(),
					    				houseOwner: obj.createUser
					    			}
					    			,success: function(response){
					    				var result = response.result;
					    				if( result == 'Y' ){
					    					jAlert("申请发送成功，等待管理员审批","信息",function(){
					    						$("#buildingName").val('');
						    					jQuery('.close').click();
					    					});
					    					return false;
					    				}
					    			}
					    		});
					    	}
						});
					}else if( result == 'N' ){
						$("#addordinaryhouse_form").submit();
					}else if( result == 'E' ){
						jAlert(response.message,"提醒");
						return false;
					}
				}
			});
			return false;
		}
	});
	
	$("#liftFinishWizard").click(function(){
		if(!$("#liftBrand") || $("#liftBrand").val() == ''){
			jAlert("品牌必填，否则无法提交","错误");
			return false;
		}if(!$("#liftPurpose") || $("#liftPurpose").val() == '' ){
			jAlert("用途必选，否则无法提交","错误");
			return false;
		}if(!$("#liftStyle") || $("#liftStyle").val() == ''){
			jAlert("款型必填，否则无法提交","错误");
			return false;
		}if(!$("#liftCT") || $("#liftCT").val() == ''){
			jAlert("载重量必填，否则无法提交","错误");
			return false;
		}if(!$("#liftNS") || $("#liftNS").val() == ''){
			jAlert("额定速度必填，否则无法提交","错误");
			return false;
		}if(!$("#liftQA") || $("#liftQA").val() == ''){
			jAlert("质保必填，否则无法提交","错误");
			return false;
		}if(!$("#liftPrice") || $("#liftPrice").val() == ''){
			jAlert("价格必填，否则无法提交","错误");
			return false;
		}else{
			$("#addlift_form").submit();
		}
	});
	
	$("#servicefinishWizard").click(function(){
		if(!$("#serviceName") || $("#serviceName").val() == ''){
			jAlert("产品名称必填，否则无法完成注册","注意");
			return false;
		}if(!$("#serviceRangeCode") || $("#serviceRangeCode").val() == ''){
			jAlert("服务范围必填，否则无法完成注册","注意");
			return false;
		}if(!$("#price") || $("#price").val() == ''){
			jAlert("服务价格必填，否则无法完成注册","注意");
			return false;
		}else{
			$("#addservice_form").submit();
		}
	});
	
	$("#buildingName").blur(function(){
		var houseNameParam = this.value;
		jQuery.ajax({
			url: basePath+"checkBuildingName",
			data:{
				houseName : houseNameParam
			}
			,success: function(response){
				var result = response.result;
				var obj = response.obj;
				
				if( result == 'own' ){
					jAlert("您已经拥有了该产品，无法继续添加","提醒");
				}else if( result == 'sent' ){ 
					jAlert("您已经申请过该产品，无法继续发送申请","提醒");
				}else if( result == 'E' ){
					jAlert(response.message,"提醒");
				}else if( result == 'Y' ){
					jConfirm("该产品已经存在无法继续添加，是否直接申请权限？","信息",function(r) {
				    	if(r){
				    		jQuery.ajax({
				    			url: basePath+"sendRequestToBuildingName",
				    			data:{
				    				houseName : houseNameParam,
				    				houseOwner: obj.createUser
				    			}
				    			,success: function(response){
				    				var result = response.result;
				    				if( result == 'Y' ){
				    					jAlert("申请发送成功，等待管理员审批","信息",function(){
				    						$("#buildingName").val('');
					    					jQuery('.close').click();
				    					});
				    				}
				    			}
				    		});
				    	}
					});
				}
			}
		});
	});
	
	$("#goodsCategory").change(function(){
		var selectedCategory = this.value;
		$("#childGoodsCategory option[value!='']").remove();
		
		if( selectedCategory != null && selectedCategory != ''){
			jQuery.ajax({
				url: basePath+"getcategorybypcode",
				data:{
					parentCode : selectedCategory
				}
				,success: function(response){
					var categoryList = response.categoryList;
					var childSelection = "";
					
					$.each(categoryList,function(n,value) {
						childSelection = childSelection + "<option value='"+value.code+"'>"+value.name+"</option>";
					});
					
					$("#childGoodsCategory").append(childSelection);
					
					$("#childGoodsCategory").selectBoxIt().data("selectBoxIt");
					var selectBox = $("#childGoodsCategory").data("selectBox-selectBoxIt");
					selectBox.refresh();
				}
			});
		}
		
		$("#childGoodsCategory").selectBoxIt().data("selectBoxIt");
		var selectBox = $("#childGoodsCategory").data("selectBox-selectBoxIt");
		selectBox.refresh();
	});
	
	$("#addNewHouseLink").click(function(){
		var childCategory = $("#childGoodsCategory").val();
		if( childCategory == null || childCategory == '' ){
			jAlert("请选择一个子分类，才能新增产品","错误");
		}else if( childCategory == '4' ){
			showPopDiv('newLiftDiv');
		}else{
			showPopDiv('newHouseDiv');
		}
	});
	
	$("#addNewServiceLink").click(function(){
		var serviceCategory = $("#serviceCategory").val();
		if( serviceCategory == null || serviceCategory == '' ){
			jAlert("请选择一个非实物分类，才能新增产品","错误");
		}else{
			showPopDiv('newServiceDiv');
		}
	});
	
	$("#productType").change(function(){
		var selectedType = this.value;
		$("#productName option[value!='']").remove();
		
		if( selectedType != null && selectedType != ''){
			jQuery.ajax({
				url: basePath+"getGoodsDics",
				data:{
					dicType : selectedType
				}
				,success: function(response){
					var dics = response.dics;
					var childSelection = "";
					
					$.each(dics,function(n,value) {
						childSelection = childSelection + "<option value='"+value.dicCode+"'>"+value.dicName+"</option>";
					});
					
					$("#productName").append(childSelection);
					
					$("#productName").selectBoxIt().data("selectBoxIt");
					$("#productName").data("selectBox-selectBoxIt").refresh();
				}
			});
		}else{
			$("#productName").selectBoxIt().data("selectBoxIt");
			$("#productName").data("selectBox-selectBoxIt").refresh();
		}
	});
	
	$(".removeProduct").click(function(){
		var productId = this.id;
		jConfirm("是否删除当前产品？","信息",function(r) {
	    	if(r){
	    		jQuery.ajax({
	    			url: basePath+"removeProduct",
	    			data : {
	    				productId : productId
	    			},
	    			success: function(response){
    					window.location.reload();
    					jQuery('.close').click();
	    			}
	    		});
	    	}else{
	    	}
	    });
	});
	
	$(".modifyProduct").click(function(){
		jQuery('#new_product_div').modal('show', {backdrop: 'fade'});
		$("#productId").val(this.id);
		
		$(this).parent().parent().find("td").each(function(i){
			if( $(this).attr("class") && $(this).attr("class").indexOf('ptype')>-1 ){
				var optionValue = $(this).attr("title");
				$("#productType option").each(function() {
			        if ($(this).val() == optionValue ) {
		                $(this).attr("selected", "selected");
		            }
			        $(this).attr("disabled", true);
			        $("#productType").selectBoxIt().data("selectBoxIt");
					$("#productType").data("selectBox-selectBoxIt").refresh();
		        });
				
				$("#productType").trigger('change');
			}else if( $(this).attr("class") && $(this).attr("class").indexOf('pname')>-1 ){
				var optionValue = $(this).attr("title");
				$("#productName option").each(function() {
			        if ($(this).val() == optionValue ) {
		                $(this).attr("selected", "selected");
		            }
			        $("#productName").selectBoxIt().data("selectBoxIt");
					$("#productName").data("selectBox-selectBoxIt").refresh();
		        });
			}else if( $(this).attr("class") && $(this).attr("class").indexOf('pprice')>-1 ){
				$("#productPrice").val($(this).attr("title"));
			}else if( $(this).attr("class") && $(this).attr("class").indexOf('remark')>-1 ){
				$("#remark").val($(this).attr("title"));
			}
		});
	});
	
	$("#addNewProductBtn").click(function(){
		var productType = $("#productType").val();
		var productName = $("#productName").val();
		var productPrice = $("#productPrice").val();
		var remark = $("#remark").val();
		var productId = $("#productId").val();
		if( productType == '' ){
			jAlert("产品类目不能为空","提醒");
			return false;
		}
		if( productName == '' ){
			jAlert("产品名称不能为空","提醒");
			return false;
		}
		if( productPrice == '' ){
			jAlert("产品价格不能为空","提醒");
			return false;
		}
		jQuery.ajax({
			url: basePath+"saveOrUpdateProduct",
			data : {
				productId : productId,
				productType : productType,
				productName : productName,
				productPrice : productPrice,
				remark : remark
			},
			success: function(response){
				var result = response.result;
				if( result != 'UPDATE' && result != 'NEW' ){
					jAlert("编辑产品失败","提醒");
				}else if( result == 'NEW' ){
					jConfirm("新增产品成功,是否进入产品列表？","信息",function(r) {
				    	if(r){
				    		window.location.href=basePath+"productlist";
				    	}else{
				    		window.location.reload();
				    		jQuery('.close').click();
				    	}
				    });
				}else if( result == 'UPDATE' ){
					jAlert("产品信息更新成功","提醒",function(){
						window.location.reload();
						jQuery('.close').click();
					});
				}
			}
		});
	});
	var i = 1;
	var fileTable = $("#houseFileTable");
	var example_dropzone = $("#advancedDropzone").dropzone({
		url: basePath+"uploadSourceFile",
		addedfile: function(file){
			if(i == 1){
				example_dropzone_filetable.find('tbody').html('');
			}
			var size = parseInt(file.size/1024, 10);
			size = size < 1024 ? (size + " KB") : (parseInt(size/1024, 10) + " MB");
			
			var	$el = $('<tr>\
				<td class="text-center">'+(i++)+'</td>\
				<td>'+file.name+'</td>\
				<td><div class="progress progress-striped"><div class="progress-bar progress-bar-warning"></div></div></td>\
				<td>'+size+'</td>\
				<td>上传中......</td>\
			</tr>');
			$example_dropzone_filetable.find('tbody').append($el);
			file.fileEntryTd = $el;
			file.progressBar = $el.find('.progress-bar');
		},
		uploadprogress: function(file, progress, bytesSent){
			file.progressBar.width(progress + '%');
		},
		success: function(file){
			file.fileEntryTd.find('td:last').html('<span class="text-success">上传成功</span>');
			file.progressBar.removeClass('progress-bar-warning').addClass('progress-bar-success');
		},
		error: function(file){
			file.fileEntryTd.find('td:last').html('<span class="text-danger">上传失败</span>');
			file.progressBar.removeClass('progress-bar-warning').addClass('progress-bar-red');
		}
	});
	$("#advancedDropzone").css({minHeight: 200});
});

function initDics(dicTypeParam){
	jQuery.ajax({
		url: goodsDictionaryURL,
		data:{
			dicType : dicTypeParam
		}
		,success: function(response){
			typeDics = response.dics;
			initProductName();
		}
	});
}
function initProductName(){
	jQuery.ajax({
		url: goodsDictionaryLikeURL,
		data:{
			dicType : 'producttype_'
		}
		,success: function(response){
			nameDics = response.dics;
			initDataTable();
		}
	});
}

function initDataTable(){
	$("#productTable").dataTable({
		dom: "t" + "<'row'<'col-xs-3'i><'col-xs-9'p>>",
		aoColumns: [
        null,//类目
        null,//名称
        null,//价格
        null,//备注
        null//操作
        ],
        "aoColumnDefs": [
         {
        	 "render": function (data, type, full) {
        		 return getDicNameByCode(typeDics,data);
        	 },
        	 "targets": 0
         },
         {
        	 "render": function (data, type, full) {
        		 return getDicNameByCode(nameDics,data);
        	 },
        	 "targets": 1
         },
         {
        	 "render": function (data, type, full) {
        		 return data;
        	 },
        	 "targets": 2
         },
         {
        	 "render": function (data, type, full) {
        		 return data;
        	 },
        	 "targets": 3
         },
         ]
	});
	
	$("#productTable").dataTable().yadcf([
		{column_number : 0}
	]);
}