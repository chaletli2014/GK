var goodsDictionaryLikeURL;
var goodsDictionaryURL;
	
jQuery(document).ready(function($){
	$.ajaxSetup({ cache: false });
	
	goodsDictionaryURL = basePath + "getGoodsDics";
	goodsDictionaryLikeURL = basePath + "getGoodsDicsLike";
	
	$(".nav_newrepository").click(function(){
		$(".modal-title").html($(this).prev().html()+"新增");
		$("#repositoryId").val('');
		$("#repo_from_source").val('nav');
		if( $(this).prev().html() == '资品库' ){
			$("#repositoryType").val('1');
		}else if( $(this).prev().html() == '产品库' ){
			$("#repositoryType").val('2');
		}else if( $(this).prev().html() == '需品库' ){
			$("#repositoryType").val('3');
		}
		$("#repositoryName").val('');
		$("#repositoryDesc").val('');
		jQuery('#addrepositorydiv').modal('show', {backdrop: 'fade'});
	});
	
	$(document).on('click', '.nav_editrepository', function(){
		$(".modal-title").html("物库编辑");
		$("#repositoryType").val($(this).attr("gid"));
		$("#repositoryId").val($(this).attr("rid"));
		$("#repo_from_source").val('nav');
		var repositoryCode = $(this).attr("rcode");
		jQuery.ajax({
			url: basePath+"getRepositoryByCode",
			data:{ 
				repositoryCode : repositoryCode
			},
			success: function(response){
				var repository = response.repository;
				$("#repositoryName").val(repository.repositoryName);
				$("#repositoryDesc").val(repository.repositoryDesc);
				jQuery('#addrepositorydiv').modal('show', {backdrop: 'fade'});
			}
		});
	});
	
	$(document).on('click', '.nav_delrepository', function(){
		var repositoryId = $(this).attr("rid");
		var gId = $(this).attr("gid");
		var repositoryName = $(this).attr("rname");
		var message = "";
		if( gId == '1' ){
			message = "是否删除资品库[ "+repositoryName+" ]?";
		}else if( gId == '2' ){
			message = "是否删除产品库[ "+repositoryName+" ]?";
		}else if( gId == '3' ){
			message = "是否删除需品库[ "+repositoryName+" ]?";
		}
		jConfirm(message,"信息",function(r) {
	    	if(r){
	    		jQuery.ajax({
	    			url: basePath+"removeRepository",
	    			data:{
	    				repositoryId : repositoryId
	    			},
	    			success: function(response){
	    				var result = response.result;
	    				if( result == 'Y' ){
	    					jAlert("物库删除成功", "提示");
	    				}else{
	    					jAlert("物库删除失败", "提示");
	    				}
	    			}
	    		});
	    	}
		});
	});
	
	$(".repositoryNav").click(function(){
		jQuery.ajax({
			url: basePath+"getRepository",
			success: function(response){
				var list1 = response.repositoryList1;
				var list2 = response.repositoryList2;
				var list3 = response.repositoryList3;
				
				if( list1 != null ){
					$(".dyrepos1").remove();
					$.each(list1,function(n,repos1){
						var reposDiv = "<div><a href=\""+basePath+"\mainIndex?repository_code="+repos1.repositoryCode+"\">"+repos1.repositoryName+"</a></div>";
						var editA = "<a gid=\"1\" rid=\""+repos1.id+"\" rcode=\""+repos1.repositoryCode+"\" class=\"nav_editrepository\"><i class=\"fa-edit\"></i></a>";
						var deleteA = "<a gid=\"1\" rid=\""+repos1.id+"\" rname=\""+repos1.repositoryName+"\" class=\"nav_delrepository\"><i class=\"fa-close\"></i></a>";
						$(".own_menu").append("<li class=\"menu-list-own dyrepos1\">"+reposDiv+"<div>"+editA+deleteA+"</div></li>");
					});
				}
				
				if( list2 != null ){
					$(".dyrepos2").remove();
					$.each(list2,function(n,repos2){
						var reposDiv = "<div><a href=\""+basePath+"\mainIndex?repository_code="+repos2.repositoryCode+"\">"+repos2.repositoryName+"</a></div>";
						var editA = "<a gid=\"2\" rid=\""+repos2.id+"\" rcode=\""+repos2.repositoryCode+"\" class=\"nav_editrepository\"><i class=\"fa-edit\"></i></a>";
						var deleteA = "<a gid=\"2\" rid=\""+repos2.id+"\" rname=\""+repos2.repositoryName+"\" class=\"nav_delrepository\"><i class=\"fa-close\"></i></a>";
						$(".goods_menu").append("<li class=\"menu-list-goods dyrepos2\">"+reposDiv+"<div>"+editA+deleteA+"</div></li>");
					});
				}
				
				if( list3 != null ){
					$(".dyrepos3").remove();
					$.each(list3,function(n,repos3){
						var reposDiv = "<div><a href=\""+basePath+"\mainIndex?repository_code="+repos3.repositoryCode+"\">"+repos3.repositoryName+"</a></div>";
						var editA = "<a gid=\"3\" rid=\""+repos3.id+"\" rcode=\""+repos3.repositoryCode+"\" class=\"nav_editrepository\"><i class=\"fa-edit\"></i></a>";
						var deleteA = "<a gid=\"3\" rid=\""+repos3.id+"\" rname=\""+repos3.repositoryName+"\" class=\"nav_delrepository\"><i class=\"fa-close\"></i></a>";
						$(".req_menu").append("<li class=\"menu-list-req dyrepos3\">"+reposDiv+"<div>"+editA+deleteA+"</div></li>");
					});
				}
			}
		});
	});
	
	$("#addNewRepositoryBtn").click(function(){
		var repoName = $("#repositoryName").val();
		var repoDesc = $("#repositoryDesc").val();
		var repoType = $("#repositoryType").val();
		var repoId = $("#repositoryId").val();
		if( repoName == '' ){
			jAlert("物库名称不能为空","提醒");
			return false;
		}
		if( repoType == '' ){
			jAlert("物库类型不能为空","提醒");
			return false;
		}
		jQuery.ajax({
			url: basePath+"saveOrUpdateRepository",
			data : {
				repositoryId : repoId,
				repositoryName : repoName,
				repositoryDesc : repoDesc,
				repositoryType : repoType
			},
			success: function(response){
				var result = response.result;
				if( result != 'UPDATE' && result != 'NEW' ){
					jAlert("编辑物库错误","提醒");
				}else if( result == 'NEW' ){
					jAlert("物库添加成功，点击确定进入新物库","信息",function(){
						window.location.href = basePath+"mainIndex?repository_code="+response.repositoryCode;
					});
					jQuery('.close').click();
				}else if( result == 'UPDATE' ){
					jAlert("更新物库成功","信息",function(){
						window.location.reload();
						jQuery('.close').click();
					});
				}
			}
		});
	});
	
	$("body").delegate('#repositoryName', 'blur', function(){
		jQuery.ajax({
			url: basePath+"getRepositoryByName",
			data : {
				repositoryName : $(this).val()
			},
			success: function(response){
				var result = response.result;
				if( result == 'Y' ){
					jAlert("该物库已经存在，请重新命名","提示");
					$("#addNewRepositoryBtn").attr("disabled",true);
				}else{
					$("#addNewRepositoryBtn").attr("disabled",false);
				}
			}
		});
	});
	
	$("<span style='color:red;display:inline;font-size:24px;height:10px;line-height:10px;'>*</span>").appendTo(".form_input_required");
});

function checkRepositoryForm(){
	return true;
}

function showProductCategorys(projectPath){
	jQuery('#productCategoryDiv').modal('show', {backdrop: 'fade'});
	jQuery('#categoryFrame').attr("src",projectPath+"categoryiframe");
}
function chooseCategory(){
	var categoryName = $("#categoryFrame").contents().find("#categoryName_h").val();
	$("#productCategory").val(categoryName);
	$("#categoryName").val(categoryName);
}
function checkMessage(errorMessage){
	if( errorMessage && errorMessage != '' ){
		jAlert(errorMessage,"提醒");
	}
}
function showPopDiv(divName){
	jQuery('#'+divName).modal('show', {backdrop: 'static'});
}

function getDicNameByCode(dics,dicCodeParam){
	var returnedDicName = dicCodeParam;
	
	if( null != dics ){
		$.each(dics,function(i,item){
			if( item.dicCode == dicCodeParam ){
				returnedDicName = item.dicName;
				return false;
			}
		});
	}
	
	return returnedDicName;
}

/**
 * 初始化新增设备和装饰材料时的构件
 * */
function populateModuleBySubject(subjectId,newTrCount){
	jQuery.ajax({
		url: basePath+"subjectmodulelist",
		data : {
			subjectId : subjectId
		},
		success: function(response){
			var result = response.result;
			var modules = response.modules;
			if( result == 'Y' ){
				var optionList = $("#moduleSelection"+newTrCount);
				optionList.empty();
				optionList.append("<option value=''>--请选择构件--</option>");
				$.each(modules,function(n,moduleObj){
					optionList.append("<option value='"+moduleObj.id+"'>"+moduleObj.moduleName+"</option>");
	        	});
			}
		}
	});
}


function populateDropdownByDic(dicTypeCode,selectionId){
	jQuery.ajax({
		url: goodsDictionaryURL,
		data : {
			dicType : dicTypeCode
		},
		success: function(response){
			var dics = response.dics;
			var optionList = $("#"+selectionId);
			optionList.empty();
			optionList.append("<option value=''>--请选择--</option>");
			
			if( dics != null ){
				$.each(dics,function(n,dicObj){
					optionList.append("<option value='"+dicObj.dicCode+"'>"+dicObj.dicName+"</option>");
	        	});
			}
		}
	});
}