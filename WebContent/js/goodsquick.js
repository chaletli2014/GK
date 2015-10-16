

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