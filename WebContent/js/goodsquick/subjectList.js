var subjectLevel;
var parentId;
var newTrCount;
var subject1Name;
var subject2Name;

jQuery(document).ready(function($){
	initSubjectTable();
	
	$("#newSubject").click (function(){
		createNewSubjectTr();
	});
	
	$("#saveSubject").click (function(){
		saveSubject();
	});
	$("#sm_submitBtn").click(function(){
		saveSubjectModule();
	});
	$("#modifySubjectBtn").click(function(){
		modifySubject();
	});
	
	$("body").delegate('.showModule', 'click', function(){
		emptyModuleForm();
		showSubjectModule($(this).attr("id"));
	});
	
	$("body").delegate('.removeSubject', 'click', function(){
		var subjectId = $(this).attr("id");
		jConfirm("是否确定删除？","提醒",function(r) {
	    	if(r){
	    		window.location.href=basePath+"deleteSubjectById?subjectId="+subjectId;
	    	}
	    });
	});
	
	$("body").delegate('.modifySubject', 'click', function(){
		showSubjectInfo($(this).attr("id"));
	});
});

function createNewSubjectTr(){
	newTrCount = $(".newSubjectTr").length + 1;
	
	subjectLevel = $("#subjectLevel_h").val();
	parentId = $("#parentId_h").val();
	
	var newTr = "<tr class=\"newSubjectTr\">";
	newTr = newTr + "<td><input type=\"text\" class=\"form-control\" id=\"subjectName"+newTrCount+"\" name=\"subjectName\"></td>";
	newTr = newTr + "<td><input type=\"text\" class=\"form-control\" id=\"subjectDesc"+newTrCount+"\" name=\"subjectDesc\"></td>";
	if( subjectLevel != '1' ){
		jQuery.ajax({
			url: basePath+"getParentSubjectListByLevel",
			data : {
				subjectLevel : '1'
			},
			success: function(response){
				var result = response.result;
				var subjectList = response.subjectList;
				if( result == 'Y' ){
					var optionList = "";
					if( subjectLevel == '2' ){
						optionList = "<select id=\"parentId"+newTrCount+"\" name =\"parentId\" onchange=\"populateParent(this,'2','"+newTrCount+"')\"><option value=\"\">--请选择--</option>";
					}else{
						optionList = "<select id=\"subject1Selection"+newTrCount+"\" name =\"subject1Selection\" onchange=\"populateSubject2BySubject1(this,'"+newTrCount+"')\"><option value=\"\">--请选择--</option>";
					}
					$.each(subjectList,function(n,subjectObj){
						optionList = optionList + "<option value='"+subjectObj.id+"'>"+subjectObj.name+"</option>";
		        	});
					optionList = optionList + "</select>";
					if( subjectLevel == '2' ){
						newTr = newTr + "<td>"+optionList+"</td>";
					}else if( subjectLevel == '3' ){
						var subject2List = "<select id=\"parentId"+newTrCount+"\" name =\"parentId\" onchange=\"populateParent(this,'3','"+newTrCount+"')\"><option value=\"\">--请选择--</option>";
						newTr = newTr + "<td>"+optionList+" - "+subject2List+"</td>";
					}
					newTr = newTr + "<td>&nbsp;</td>";
					newTr = newTr + "<td>&nbsp;</td>";
					newTr = newTr + "</tr>";
					$("#subjectTable tbody").prepend(newTr);
				}
			}
		});
	}else{
		newTr = newTr + "<td>&nbsp;</td>";
		newTr = newTr + "<td>&nbsp;</td>";
		newTr = newTr + "</tr>";
		$("#subjectTable tbody").prepend(newTr);
	}
}

/**
 * 关联二级主体
 * */
function populateSubject2BySubject1(subject1,newTrCount){
	subject1Name = subject1.selectedOptions[0].innerHTML+"-";
	jQuery.ajax({
		url: basePath+"getChildSubjectByParentId",
		data : {
			parentId : subject1.value
		},
		success: function(response){
			var result = response.result;
			var subjectList = response.subjectList;
			if( result == 'Y' ){
				var optionList = $("#parentId"+newTrCount);
				optionList.empty();
				optionList.append("<option value=''>--请选择--</option>");
				$.each(subjectList,function(n,subjectObj){
					optionList.append("<option value='"+subjectObj.id+"'>"+subjectObj.name+"</option>");
	        	});
			}
		}
	});
}

function saveSubject(){
	var subjectArray = new Array();
	for( var i = 1; i <= newTrCount; i++ ){
		var subjectObj = new Object();
		subjectObj.parentId = $("#parentId"+i).val();
		subjectObj.name = $("#subjectName"+i).val();
		subjectObj.desc = $("#subjectDesc"+i).val();
		subjectObj.level = $("#subjectLevel_h").val();
		
		if( subjectObj.name == '' ){
			jAlert("主体名称不能为空","提醒");
			return false;
		}
		
		if( subjectObj.parentId == '' ){
			jAlert("上级主体必须选择","提醒");
			return false;
		}
		
		subjectArray.push(subjectObj);
	}
	
	if( subjectArray.length == 0 ){
		jAlert("未新增任何数据，无法保存","错误");
		return false;
	}
	
	jQuery.ajax({
		url: basePath+"saveOrUpdateSubject",
		data : {
			subjectList : JSON.stringify(subjectArray)
		},
		success: function(response){
			var result = response.result;
			if( result == 'Y' ){
				jAlert("新增成功","提醒",function(){
					window.location.reload();
				});
			}
		}
	});
}

function modifySubject(){
	var subjectArray = new Array();
	
	var subjectObj = new Object();
	subjectObj.id = $("#subjectIdModify").val();
	subjectObj.name = $("#subjectNameModify").val();
	subjectObj.desc = $("#subjectDescModify").val();
	
	if( subjectObj.name == '' ){
		jAlert("主体名称不能为空","提醒");
		return false;
	}
	subjectArray.push(subjectObj);
	
	jQuery.ajax({
		url: basePath+"saveOrUpdateSubject",
		data : {
			subjectList : JSON.stringify(subjectArray)
		},
		success: function(response){
			var result = response.result;
			if( result == 'Y' ){
				jAlert("编辑成功","提醒",function(){
					window.location.reload();
				});
			}
		}
	});
}

function saveSubjectModule(){
	var subjectId = $("#subjectId").val();
	var moduleType = $("#moduleType").val();
	var moduleName = $("#moduleName").val();
	var moduleDesc = $("#moduleDesc").val();
	
	jQuery.ajax({
		url: basePath+"modifySubjectModule",
		data : {
			subjectId : subjectId,
			moduleType : moduleType,
			moduleName : moduleName,
			moduleDesc : moduleDesc
		},
		success: function(response){
			var result = response.result;
			if( result == 'Y' ){
				jAlert("新增成功","提醒",function(){
					jQuery.ajax({
						url: basePath+"subjectmodulelist",
						data : {
							subjectId : subjectId
						},
						success: function(response){
							var result = response.result;
							var modules = response.modules;
							var subjectName = response.subjectName;
							if( result == 'Y' ){
								$("#subjectModuleTable tbody").empty();
								$.each(modules,function(n,moduleObj){
									$("#subjectModuleTable tbody").append("<tr><td>"+moduleObj.moduleTypeName+"</td><td>"+moduleObj.moduleName+"</td><td>"+moduleObj.moduleDesc+"</td><td>"+subjectName+"</td></tr>");
					        	});
							}else{
								jAlert("获取构件失败","错误");
							}
						}
					});
				});
			}
		}
	});
}

function showSubjectModule(subjectId){
	jQuery.ajax({
		url: basePath+"subjectmodulelist",
		data : {
			subjectId : subjectId
		},
		success: function(response){
			var result = response.result;
			var modules = response.modules;
			var subjectName = response.subjectName;
			if( result == 'Y' ){
				$("#subjectModuleTable tbody").empty();
				$.each(modules,function(n,moduleObj){
					$("#subjectModuleTable tbody").append("<tr><td>"+moduleObj.moduleTypeName+"</td><td>"+moduleObj.moduleName+"</td><td>"+moduleObj.moduleDesc+"</td><td>"+subjectName+"</td></tr>");
	        	});
				$("#subjectName").val(subjectName);
				$("#subjectId").val(subjectId);
				jQuery('#subjectModuleDiv').modal('show', {backdrop: 'static'});
			}else{
				jAlert("获取构件失败","提示");
			}
		}
	});
}

function showSubjectInfo(subjectId){
	jQuery.ajax({
		url: basePath+"getSubjectById",
		data : {
			subjectId : subjectId
		},
		success: function(response){
			var result = response.result;
			var subjectObj = response.subjectObj;
			if( result == 'Y' ){
				$("#subjectNameModify").val(subjectObj.name);
				$("#subjectDescModify").val(subjectObj.desc);
				$("#parentNameModify").val(subjectObj.parentName);
				$("#subjectIdModify").val(subjectId);
				jQuery('#subjectModifyDiv').modal('show', {backdrop: 'static'});
			}else{
				jAlert("获取主体失败","提示");
			}
		}
	});
}

function populateParent(level, subjectLevel, rowIndex){
	if( subjectLevel == '2' ){
		subject1Name = level.selectedOptions[0].innerHTML;
	}else if( subjectLevel == '3' ){
		subject2Name = level.selectedOptions[0].innerHTML;
	}
	$("#subjectName"+rowIndex).val(subject1Name+subject2Name);
	$("#parentId_h").val(level.value);
}

function showModule(subjectId){
	$('#rightBody', window.parent.document).fadeOut();
	$('#rightBody', window.parent.document).fadeIn();
	
	var moduleTable = $('#bodyModuleDetailTable',window.parent.document);
	
	jQuery.ajax({
		url: basePath+"subjectmodulelist",
		data:{
			subjectId :subjectId
		},
        error : function() {
        },
        success : function(data) {
        	moduleTable.find('tbody').html("");
        	var trList = data.modules;
        	var tbody = "";
        	$.each(trList,function(n,module){
        		tbody = tbody + "<tr><td >"+module.moduleName+"</td><td >"+module.moduleDesc+"</td></tr>";
        	});
        	moduleTable.find('tbody').html(tbody);
        }
    });
}
function initSubjectTable(){
	$("#subjectTable").dataTable({
		dom: "t" + "<'row'<'col-xs-3'i><'col-xs-9'p>>"
	});
}
function emptyModuleForm(){
	$("#moduleType").selectBoxIt().data("selectBoxIt");
	$("#moduleType").data("selectBox-selectBoxIt").refresh();
	$("#moduleName").val('');
	$("#moduleDesc").val('');
}