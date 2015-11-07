jQuery(document).ready(function($){
	$("#nav_newrepository").click(function(){
		jQuery('#addrepositorydiv').modal('show', {backdrop: 'fade'});
		$("#repo_from_source").val('nav');
	});
	
	$("#addNewRepositoryBtn").click(function(){
		var repoName = $("#repositoryName").val();
		var repoDesc = $("#repositoryDesc").val();
		var repoType = $("#repositoryType").val();
		if( checkRepositoryForm(repoName,repoDesc,repoType) ){
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
						jConfirm("是否直接进入新增资品库？","提醒",function(r){
							if( r ){
								window.location.href = basePath+"index?repository_code="+response.repositoryCode;
							}
						});
						jQuery('.close').click();
					}
					
				}
			});
		}else{
			jAlert("必填项不能为空","提醒");
		}
	});
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

