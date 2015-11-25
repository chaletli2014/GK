jQuery(document).ready(function($){
	$("#nav_newrepository").click(function(){
		jQuery('#addrepositorydiv').modal('show', {backdrop: 'fade'});
		$("#repo_from_source").val('nav');
	});
	
	$("#addNewRepositoryBtn").click(function(){
		var repoName = $("#repositoryName").val();
		var repoDesc = $("#repositoryDesc").val();
		var repoType = $("#repositoryType").val();
		if( repoName == '' ){
			jAlert("物库名称不能为空","提醒");
			return false;
		}
		if( repoDesc == '' ){
			jAlert("物库描述不能为空","提醒");
			return false;
		}
		if( repoType == '' ){
			jAlert("物库类型不能为空","提醒");
			return false;
		}
		jQuery.ajax({
			url: basePath+"saveOrUpdateRepository",
			data : {
				repositoryName : repoName,
				repositoryDesc : repoDesc,
				repositoryType : repoType
			},
			success: function(response){
				var result = response.result;
				if( result != 'Y' ){
					jAlert("新增资品库错误","提醒");
				}else{
					jAlert("资品库添加成功，点击确定进入新资品库","信息",function(){
						window.location.href = basePath+"index?repository_code="+response.repositoryCode;
					});
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

