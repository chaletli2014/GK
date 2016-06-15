jQuery(document).ready(function($){
	initDataTable();
	$("body").delegate('#repoUserEditLink', 'click', function(){
	});
	$("body").delegate('.repoUserDeleteLink', 'click', function(){
		var repoUserId = $(this).attr("aid");
		jConfirm("是否删除当前库用户？","信息",function(r) {
	    	if(r){
	    		jQuery.ajax({
	    			url: basePath+"removeRepositoryUser",
	    			data : {
	    				repoUserId : repoUserId
	    			},
	    			success: function(response){
	    				if( response.result == 'Y' ){
	    					jAlert("成功 删除库用户","错误",function(){
	    						window.location.reload();
	    						jQuery('.close').click();
	    					});
	    				}else{
	    					jAlert("删除库用户错误","错误");
	    				}
	    			}
	    		});
	    	}else{
	    	}
	    });
	});
	$("body").delegate('#newRepoUserLink', 'click', function(){
		jQuery('#addRepositoryUsersDiv').modal('show', {backdrop: 'static'});
	});
	$("body").delegate('#searchUserSpan', 'click', function(){
		searchUserSpan();
	});
	$("body").delegate('#chooseTarget', 'click', function(){
		chooseTarget();
	});
});

function initDataTable(){
	$("#repositoryUserTable").dataTable({
		dom: "t" + "<'row'<'col-xs-6'i><'col-xs-6'p>>",
		aoColumns: [
			null,
			null,
			null
		]
	});
}

function searchUserSpan(){
	var searchStr = $("#userSearch").val();
	if( searchStr == '' ){
		jAlert("搜索内容不能为空","错误");
		return;
	}
	
	jQuery.ajax({
		url: basePath+"searchUserByName",
		data : {
			searchStr : searchStr
		},
		success: function(response){
			var searchUser = response.searchUser;
			if( null != searchUser ){
				var searchUserTable = $("#searchUserTable");
				searchUserTable.find('tbody').html("");
        		var tbody = "<tr>";
        		tbody = tbody + "<td ><input cid=\""+searchUser.id+"\" type=\"checkbox\" name=\"userCheckbox\" class=\"cbr\"></td>";
        		tbody = tbody + "<td >"+searchUser.name+"</td>";
        		tbody = tbody + "<td >"+searchUser.telephone+"</td>";
        		tbody = tbody + "</tr>";
        		searchUserTable.find('tbody').html(tbody);
				$("#searchUserInfo").css({'display':''});
			}else{
				jAlert("用户不存在，请检查搜索内容","提示");
			}
		}
	});
}

function chooseTarget(){
	var userCheckbox = $("input[name='userCheckbox']");
	
	var selectedId='';
	$.each(userCheckbox, function(i, checkbox) {
		if(this.checked){
			selectedId = $(this).attr("cid");
		}
	});
	if( selectedId == '' ){
		jAlert("未选中任何用户", "提示");
		return;
	}
	
	jQuery.ajax({
		url: basePath+"saveRepositoryUser",
		data:{
			selectedId : selectedId
		},
		success: function(response){
			if( response.result && response.result == 'Y' ){
				jAlert("关联成功", "提示",function(){
					window.location.reload();
				});
			}else if( response.result == 'E' ){
				jAlert("用户已经关联，无法重复添加", "提示");
			}
		}
	});
}
