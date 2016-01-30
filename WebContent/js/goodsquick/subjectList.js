var subjectLevel;
var parentId;
var newTrCount;

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
	
	$(".showModule").click(function(){
		emptyModuleForm();
		showSubjectModule();
	});
	
	$(".modifySubject").click(function(){
		$(this).parent().siblings("td").each(function() {
			if( $(this).hasClass("dataEditable") ){
				// 获取当前行的其他单元格
				obj_text = $(this).find("input:text");    // 判断单元格下是否有文本框
				if(!obj_text.length){// 如果没有文本框，则添加文本框使之可以编辑
					$(this).html("<input type='text' value='"+$(this).text()+"'>");
				}else{// 如果已经存在文本框，则将其显示为文本框修改的值
					$(this).html(obj_text.val());
				}
			}
        });
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
				subjectLevel : subjectLevel
			},
			success: function(response){
				var result = response.result;
				var subjectList = response.subjectList;
				if( result == 'Y' ){
					var optionList = "<select id=\"parentId"+newTrCount+"\" name =\"parentId\" onchange=\"populateParent(this)\"><option value=\"\">--请选择--</option>";
					$.each(subjectList,function(n,subjectObj){
						optionList = optionList + "<option value='"+subjectObj.id+"'>"+subjectObj.name+"</option>";
		        	});
					optionList = optionList + "</select>";
					newTr = newTr + "<td>"+optionList+"</td>";
					newTr = newTr + "<td>&nbsp;</td>";
					newTr = newTr + "<td>&nbsp;</td>";
					newTr = newTr + "</tr>";
					$("#subjectTable tbody").append(newTr);
				}
			}
		});
	}else{
		newTr = newTr + "<td>&nbsp;</td>";
		newTr = newTr + "<td>&nbsp;</td>";
		newTr = newTr + "</tr>";
		$("#subjectTable tbody").append(newTr);
	}
}

function saveSubject(){
	var subjectArray = new Array();
	for( var i = 1; i <= newTrCount; i++ ){
		var subjectObj = new Object();
		subjectObj.parentId = $("#parentId"+i).val();
		subjectObj.name = $("#subjectName"+i).val();
		subjectObj.desc = $("#subjectDesc"+i).val();
		subjectObj.level = $("#subjectLevel_h").val();
		
		if( subjectObj.subjectName == '' ){
			jAlert("主体名称不能为空","提醒");
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

function showSubjectModule(){
	var subjectId = this.id;
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

function populateParent(level){
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