jQuery(document).ready(function($){
	var basePath = $("#basePath").val();
	
	$(".product_link").click(function(){
		$("#productType").val(this.title);
		showPopDiv('newProductObjDiv');
	});
	
	$(".removeProduct").click(function(){
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
	
	$("#productObjBtn").click(function(){
		var productType = $("#productType").val();
		var productName = $("#productName").val();
		var productBrand = $("#productBrand").val();
		var productModel = $("#productModel").val();
		var itemCode = $("#itemCode").val();
		var productDom = $("#productDom").val();
		var productQA = $("#productQA").val();
		var productPrice = $("#productPrice").val();
		var productId = $("#productId").val();
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
				productPrice : productPrice
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