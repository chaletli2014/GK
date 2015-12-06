jQuery(document).ready(function($){
	$(".modifyRepository").click(function(){
		jQuery('#addrepositorydiv').modal('show', {backdrop: 'fade'});
		$("#repo_from_source").val('nav');
		$("#repositoryId").val(this.id);
		
		$(this).parent().parent().find("td").each(function(i){
			if( $(this).attr("title") == 'name' ){
				$("#repositoryName").val($(this).text());
			}else if( $(this).attr("title") == 'type' ){
				var optionValue = $(this).text();
				$("#repositoryType option").each(function() {
			        if ($(this).val() == optionValue ) {
		                $(this).attr("selected", "selected");
		            }
			        $(this).attr("disabled", true);
			        $("#repositoryType").selectBoxIt().data("selectBoxIt");
					$("#repositoryType").data("selectBox-selectBoxIt").refresh();
		        });
			}else if( $(this).attr("title") == 'desc' ){
				$("#repositoryDesc").val($(this).text());
			}
		});
	});
	
	$(".removeRepository").click(function(){
		var repoId = this.id;
//		var c = $(this).css("background-color");
//		$(this).parent().parent().css('background-color','red');
		jConfirm("是否注销当前物库？","信息",function(r) {
	    	if(r){
	    		jQuery.ajax({
	    			url: basePath+"removeRepository",
	    			data : {
	    				repositoryId : repoId
	    			},
	    			success: function(response){
    					window.location.reload();
    					jQuery('.close').click();
	    			}
	    		});
	    	}else{
//	    		$(this).parent().parent().css('background-color',c);
	    	}
	    });
	});
});