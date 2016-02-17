var newTrCount;

jQuery(document).ready(function($){
	
	$("#newOther").click (function(){
		createNewOtherTr();
	});
	
	$("#saveOther").click (function(){
		saveOther();
	});
	$("#sm_submitBtn").click(function(){
		saveOther();
	});
	
	$(".showOther").click(function(){
		
	});
	
	$(".modifyOther").click(function(){
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

function createNewOtherTr(){
	newTrCount = $(".newOtherTr").length + 1;
	
	var newTr = "<tr class=\"newOtherTr\">";
	newTr = newTr + "<td><input type=\"text\" class=\"form-control\" id=\"otherName"+newTrCount+"\" name=\"otherName\"></td>";
	newTr = newTr + "<td><input type=\"text\" class=\"form-control\" id=\"otherDesc"+newTrCount+"\" name=\"otherDesc\"></td>";
	jQuery.ajax({
		url: basePath+"getSubjectList",
		success: function(response){
			var result = response.result;
			var subjectList = response.subjectList;
			if( result == 'Y' ){
				var subjectOptionList = "<select id=\"subjectSelection"+newTrCount+"\" name =\"subjectSelection\" onchange=\"populateModuleBySubject(this.value,"+newTrCount+")\"><option value=\"\">--请选择主体--</option>";
				$.each(subjectList,function(n,subjectObj){
					subjectOptionList = subjectOptionList + "<option value='"+subjectObj.id+"'>"+subjectObj.name+"</option>";
	        	});
				subjectOptionList = subjectOptionList + "</select>";
				
				var moduleOptionList = "<select id=\"moduleSelection"+newTrCount+"\" name =\"moduleSelection\"><option value=\"\">--请选择构件--</option>";
				newTr = newTr + "<td>"+subjectOptionList+" - "+moduleOptionList+"</td>";
				newTr = newTr + "<td>&nbsp;</td>";
				newTr = newTr + "</tr>";
				$("#otherTable tbody").append(newTr);
			}
		}
	});
}

function saveOther(){
	var otherArray = new Array();
	for( var i = 1; i <= newTrCount; i++ ){
		var otherObj = new Object();
		otherObj.name = $("#otherName"+i).val();
		otherObj.desc = $("#otherDesc"+i).val();
		otherObj.subjectId = $("#subjectSelection"+i).val();
		otherObj.moduleId = $("#moduleSelection"+i).val();
		
		if( otherObj.name == '' ){
			jAlert("名称不能为空","提醒");
			return false;
		}
		
		if( otherObj.subjectId == '' || otherObj.subjectId == null ){
			jAlert("主体必须选择","提醒");
			return false;
		}
		
		if( otherObj.moduleId == '' || otherObj.moduleId == null ){
			jAlert("构件必须选择","提醒");
			return false;
		}
		
		otherArray.push(otherObj);
	}
	
	if( otherArray.length == 0 ){
		jAlert("未新增任何数据，无法保存","错误");
		return false;
	}
	
	jQuery.ajax({
		url: basePath+"saveOrUpdateOther",
		data : {
			otherList : JSON.stringify(otherArray)
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
function emptyOtherForm(){
	$("#moduleType").selectBoxIt().data("selectBoxIt");
	$("#moduleType").data("selectBox-selectBoxIt").refresh();
	$("#moduleName").val('');
	$("#moduleDesc").val('');
}