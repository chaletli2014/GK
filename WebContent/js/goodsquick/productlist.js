var typeDics;
var nameDics;

jQuery(document).ready(function($){
	initDataTable();
	initProductObjTable();
	
	$("#newProductLink").click(function(){
//		clearModifyProductForm();
//		jQuery('#new_product_div').modal('show', {backdrop: 'static'});
		window.location.href=basePath+"newProductPre";
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
	
	$("body").delegate('.removeServiceP', 'click', function(){
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
	
	$("body").delegate('.modifyProductObj', 'click', function(){
		$("#productId").val(this.id);
		
		jQuery.ajax({
			url: basePath+"getProductInfoById",
			data:{
				productId : this.id
			},
			success: function(response){
				populateProductObjInfo(response.productObj);
				jQuery('#newProductObjDiv').modal('show', {backdrop: 'fade'});
				populateProductSource(response.productSources);
			}
		});
	});
	
	$("body").delegate('.removeProductObj', 'click', function(){
		var productId = this.id;
		jConfirm("是否删除当前产品？","信息",function(r) {
			if(r){
				jQuery.ajax({
					url: basePath+"removeProductObj",
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
	
	$("body").delegate('.modifyServiceP', 'click', function(){
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
	
	$("body").delegate('.modifyProduct', 'click', function(){
		$("#liftId").val(this.id);
		
		jQuery.ajax({
			url: basePath+"getLiftInfoById",
			data:{
				liftId : this.id
			},
			success: function(response){
				populateLiftInfo(response.liftObj);
				jQuery('#newLiftDiv').modal('show', {backdrop: 'fade'});
			}
		});
	});
	
	$("body").delegate('#liftFinishWizard', 'click', function(){
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
	$("body").delegate('.isMainPicCheckbox', 'change', function(){
		var pics = $(".isMainPicCheckbox");
		$.each(pics, function(i, pic) {
			$(this).removeAttr("checked");
		});
		$(this).prop("checked",true);
		$("#mainPicId").val($(this).val());
	});
	
	$("body").delegate('#productObjBtn', 'click', function(){
		var productType = $("#productType").val();
		var productName = $("#productName").val();
		var productBrand = $("#productBrand").val();
		var productModel = $("#productModel").val();
		var itemCode = $("#itemCode").val();
		var productDom = $("#productDom").val();
		var productQA = $("#productQA").val();
		var productPrice = $("#productPrice").val();
		var remark = $("#remark").val();
		var productId = $("#productId").val();
		var isMain = $("#mainPicId").val();
		if( productName == '' ){
			jAlert("产品名称不能为空","提醒");
			return false;
		}
		if( productPrice == '' ){
			jAlert("产品价格不能为空","提醒");
			return false;
		}
		jQuery.ajax({
			url: basePath+"saveOrUpdateProductObj",
			data : {
				productId : productId,
				productType : productType,
				productName : productName,
				productBrand : productBrand,
				productModel : productModel,
				itemCode : itemCode,
				productDom : productDom,
				productQA : productQA,
				productPrice : productPrice,
				remark : remark,
				isMain: isMain
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
					jAlert("新增产品成功","提醒",function(){
						window.location.reload();
						jQuery('.close').click();
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
});

function initDataTable(){
	$("#productTable").dataTable({
		dom: "t" + "<'row'<'col-xs-3'i><'col-xs-9'p>>",
		aoColumns: [
        null,//类目
        null,//名称
        null,//价格
        null,//备注
        null//操作
        ]
	});
	
	$("#productTable").dataTable().yadcf([
		{column_number : 0}
	]);
}

function initProductObjTable(){
	$("#productObjTable").dataTable({
		dom: "t" + "<'row'<'col-xs-3'i><'col-xs-9'p>>",
		aoColumns: [
        null,//分类
        null,//名称
        null,//品牌
        null,//型号
        null,//编码
        null,//生产日期
        null,//质保
        null,//价格
        null//操作
        ]
	});
	$("#productObjTable").dataTable().yadcf([
  		{column_number : 0}
  	]);
}

function submitProduct(){
	if( $("#productName").val() == '' ){
		jAlert("产品名称不能为空","错误");
		return false;
	}
	if( $("#productType").val() == '' ){
		jAlert("产品类目不能为空","错误");
		return false;
	}
	if( $("#productPrice").val() == '' ){
		jAlert("产品价格不能为空","错误");
		return false;
	}
	
	return true;
}

function populateLiftInfo(liftObj){
	$("#liftBrand").val(liftObj.liftBrand);
	var liftPurpose = liftObj.liftPurpose;
	$("#liftPurpose option").each(function() {
        if ($(this).val() == liftPurpose ) {
            $(this).attr("selected", "selected");
        }
        $("#liftPurpose").selectBoxIt().data("selectBoxIt");
		$("#liftPurpose").data("selectBox-selectBoxIt").refresh();
    });
	var liftStyle = liftObj.liftStyle;
	$("#liftStyle option").each(function() {
		if ($(this).val() == liftStyle ) {
			$(this).attr("selected", "selected");
		}
		$("#liftStyle").selectBoxIt().data("selectBoxIt");
		$("#liftStyle").data("selectBox-selectBoxIt").refresh();
	});
	$("#liftCT").val(liftObj.liftCT);
	$("#liftNS").val(liftObj.liftNS);
	$("#liftQA").val(liftObj.liftQA);
	$("#liftPrice").val(liftObj.liftPrice);
	$("#holeSize").val(liftObj.holeSize);
	$("#pitDepth").val(liftObj.pitDepth);
	$("#overheadHeight").val(liftObj.overheadHeight);
	$("#reservation").val(liftObj.reservation);
	$("#roomSize").val(liftObj.roomSize);
	$("#roomHeight").val(liftObj.roomHeight);
	$("#carSize").val(liftObj.carSize);
	$("#carHeight").val(liftObj.carHeight);
	$("#doorSize").val(liftObj.doorSize);
	$("#mainPower").val(liftObj.mainPower);
}

function populateProductObjInfo(productObj){
	$("#productType").val(productObj.productType);
	$("#productName").val(productObj.productName);
	$("#productBrand").val(productObj.productBrand);
	$("#productModel").val(productObj.productModel);
	$("#itemCode").val(productObj.itemCode);
	$("#productDom").val(productObj.productDom);
	$("#productQA").val(productObj.productQA);
	$("#productPrice").val(productObj.productPrice);
}

function populateProductSource(productSource){
	$("#existsFileTable tbody").empty();
	var count = 1;
	$.each(productSource,function(n,fileObj){
		var fileTR = "<tr><td>"+count+"</td>";
		fileTR = fileTR + "<td>"+fileObj.fileType+"</td>";
		fileTR = fileTR + "<td><a href='"+fileObj.filePath+"'>"+fileObj.fileName+"</a></td>";
		if( fileObj.fileType == 'JPG' || fileObj.fileType == 'jpg' || fileObj.fileType == 'png' || fileObj.fileType == 'PNG' ){
			if( fileObj.isMain == '是' ){
				fileTR = fileTR + "<td><input value='"+fileObj.id+"' name='isMain' type='checkbox' checked class='iswitch iswitch-secondary isMainPicCheckbox'></td>";
			}else{
				fileTR = fileTR + "<td><input value='"+fileObj.id+"' name='isMain' type='checkbox' class='iswitch iswitch-secondary isMainPicCheckbox'></td>";
			}
		}else{
			fileTR = fileTR + "<td></td>";
		}
		fileTR = fileTR + "<td></td>";
		fileTR = fileTR + "</tr>";
		$("#existsFileTable tbody").append(fileTR);
		count++;
	});
}

function clearModifyProductForm(){
	$("#productType").val('');
	$("#productName").val('');
	$("#productPrice").val('');
	$("#remark").val('');
	
	$("#productType").selectBoxIt().data("selectBoxIt");
	$("#productType").data("selectBox-selectBoxIt").refresh();
	
	$("#productName").selectBoxIt().data("selectBoxIt");
	$("#productName").data("selectBox-selectBoxIt").refresh();
}
