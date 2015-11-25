jQuery(document).ready(function($){
	$("#newModuleSPLink").click(function(){
		jQuery('#new_module_sp_div').modal('show', {backdrop: 'static'});
	});
});

function submitModuleSP(){
	if( $("#spName").val() == '' ){
		jAlert("组件商名称不能为空","错误");
		return false;
	}
	if( $("#moduleType").val() == '' ){
		jAlert("组件不能为空","错误");
		return false;
	}
	
	return true;
}