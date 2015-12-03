jQuery(document).ready(function($){
	$(".modifyRepository").click(function(){
		jQuery('#addrepositorydiv').modal('show', {backdrop: 'fade'});
		$("#repo_from_source").val('nav');
		$("#repositoryId").val(this.id);
		
		$(this).parent().parent().find("td").each(function(i){
			if( $(this).attr("title") == 'name' ){
				$("#repositoryName").val($(this).text());
			}else if( $(this).attr("title") == 'type' ){
				$("#repositoryType").data("selectBox-selectBoxIt").val($(this).text());
			}else if( $(this).attr("title") == 'desc' ){
				$("#repositoryDesc").val($(this).text());
			}
		});
	});
});