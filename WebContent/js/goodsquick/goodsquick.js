var goodsDictionaryLikeURL;
var goodsDictionaryURL;
	
jQuery(document).ready(function($){
	goodsDictionaryURL = basePath + "getGoodsDics";
	goodsDictionaryLikeURL = basePath + "getGoodsDicsLike";
	
	$("#nav_newrepository").click(function(){
		jQuery('#addrepositorydiv').modal('show', {backdrop: 'fade'});
		$("#repo_from_source").val('nav');
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
					jAlert("新增资品库错误","提醒");
				}else if( result == 'NEW' ){
					jAlert("资品库添加成功，点击确定进入新资品库","信息",function(){
						window.location.href = basePath+"index?repository_code="+response.repositoryCode;
					});
					jQuery('.close').click();
				}else if( result == 'UPDATE' ){
					window.location.reload();
					jQuery('.close').click();
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