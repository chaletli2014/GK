jQuery(document).ready(function($){
	var basePath = $("#basePath").val();
	
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
	
	var i = 1,
	$example_dropzone_filetable = $("#example-dropzone-filetable"),
	example_dropzone = $("#advancedDropzone").dropzone({
		url: basePath+"uploadSourceFile",
		addedfile: function(file){
			if(i == 1){
				$example_dropzone_filetable.find('tbody').html('');
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