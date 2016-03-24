var newTrCount;
var newTrNum = new Array();

jQuery(document).ready(function($){
	initOtherTable();
	$("#newOther").click (function(){
		createNewOtherTr();
	});
	
	$("#saveOther").click (function(){
		saveOther();
	});
	
	$("#savePaintBtn").click(function(){
		savePaintInfo();
		return false;
	});
	
	$("body").delegate('.modifyOther', 'click', function(){
		jQuery.ajax({
			url: basePath+"getOtherByIdAndType",
			data:{
				otherId : $(this).attr("id"),
				otherType : $(this).attr("dtype")
			},
			success: function(response){
				if( null != response.otherObj ){
					populatePaintInfo(response.otherObj);
				}
				jQuery('#housePaintDiv').modal('show', {backdrop: 'static'});
			}
		});
	});

	$("body").delegate('.removeOther', 'click', function(){
		var otherId = $(this).attr("id");
		var otherType = $(this).attr("dtype");
		jConfirm("是否确定删除？","提醒",function(r) {
	    	if(r){
	    		window.location.href=basePath+"deleteOtherByIdAndType?otherId="+otherId+"&otherType="+otherType;
	    	}
	    });
	});

	$("body").delegate('.newTrCancle', 'click', function(){
		$(this).parent().parent().remove();
	});
});

function initOtherTable(){
	$("#otherTable").dataTable({
		dom: "t" + "<'row'<'col-xs-3'i><'col-xs-9'p>>"
	});
}

function createNewOtherTr(){
	if( newTrCount && newTrCount != 0 ){
		newTrCount = newTrCount + 1;
	}else{
		newTrCount = 1;
	}
	
	var newTr = "<tr class=\"newOtherTr\">";
	var otherTypeList = "<select id=\"otherTypeSelection"+newTrCount+"\" name =\"otherTypeSelection\"><option value=\"\">--请选择--</option>";
	otherTypeList = otherTypeList + "<option value=\"wpp\">防水涂料</option>";
	otherTypeList = otherTypeList + "</select>";
	
	newTr = newTr + "<td>"+otherTypeList+"</td>";
	
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
				newTr = newTr + "<td><a trNum=\""+newTrCount+"\" class=\"btn btn-danger btn-sm btn-icon icon-left fa-close newTrCancle\">取消</a></td>";
				newTr = newTr + "</tr>";
				$("#otherTable tbody").prepend(newTr);
			}
		}
	});
	
	newTrNum.push(newTrCount);
}

function saveOther(){
	var otherArray = new Array();
	for(var i=0;i<newTrNum.length;i++){
		var otherObj = new Object();
		otherObj.typeCode = $("#otherTypeSelection"+newTrNum[i]).val();
		otherObj.name = $("#otherName"+newTrNum[i]).val();
		otherObj.desc = $("#otherDesc"+newTrNum[i]).val();
		otherObj.subjectId = $("#subjectSelection"+newTrNum[i]).val();
		otherObj.moduleId = $("#moduleSelection"+newTrNum[i]).val();

		if( typeof(otherObj.name) == "undefined" ){
			continue;
		}
		
		if( otherObj.typeCode == '' ){
			jAlert("分类必选","提醒");
			return false;
		}
		
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

function savePaintInfo(){
	var paintObj = new Object();
	paintObj.id = $("#paintId").val();
	paintObj.type1Code = $("#type1codeSelection").val();
	paintObj.type2Code = $("#type2codeSelection").val();
	paintObj.subjectId = $("#subjectId").val();
	paintObj.moduleId = $("#moduleId").val();
	paintObj.paintName = $("#paintName").val();
	paintObj.paintDesc = $("#paintDesc").val();
	if( $("#paintBrand").val() != '' ){
		paintObj.paintBrand = $("#paintBrand").val();
	}
	paintObj.paintModel = $("#paintModel").val();
	
	if( $("#paintStyle").val() != '' ){
		paintObj.paintStyle = $("#paintStyle").val();
	}
	paintObj.liquidType = $("#liquidType").val();
	paintObj.filmFormer = $("#filmFormer").val();
	paintObj.paintStorage = $("#paintStorage").val();
	
	if( $("#solidProportion").val() != '' ){
		paintObj.solidProportion = $("#solidProportion").val();
	}
	
	if( $("#brushArea").val() != '' ){
		paintObj.brushArea = $("#brushArea").val();
	}
	if( $("#tensileStrength").val() != '' ){
		paintObj.tensileStrength = $("#tensileStrength").val();
	}
	if( $("#breakElongation").val() != '' ){
		paintObj.breakElongation = $("#breakElongation").val();
	}
	if( $("#surfaceDryTime").val() != '' ){
		paintObj.surfaceDryTime = $("#surfaceDryTime").val();
	}
	
	if( $("#dryingTime").val() != '' ){
		paintObj.dryingTime = $("#dryingTime").val();
	}
	
	if( $("#coatingFilmColor").val() != '' ){
		paintObj.coatingFilmColor = $("#coatingFilmColor").val();
	}
	
	if( $("#originPlace").val() != '' ){
		paintObj.originPlace = $("#originPlace").val();
	}
	
	jQuery.ajax({
		url: basePath+"modifyOther",
		data : {
			otherObj : JSON.stringify(paintObj),
			otherType : "wpp"
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

function populatePaintInfo(paintObj){
	//basic
	$("#paintId").val(paintObj.id);
	$("#subjectId").val(paintObj.subjectId);
	$("#subjectName").val(paintObj.subjectName);
	$("#moduleId").val(paintObj.moduleId);
	$("#moduleName").val(paintObj.moduleName);
	var type1Code = paintObj.type1Code;
	$("#type1codeSelection option").each(function() {
        if ($(this).val() == type1Code ) {
            $(this).attr("selected", "selected");
        }
        $("#type1codeSelection").selectBoxIt().data("selectBoxIt");
		$("#type1codeSelection").data("selectBox-selectBoxIt").refresh();
    });
	var type2Code = paintObj.type2Code;
	$("#type2codeSelection option").each(function() {
		if ($(this).val() == type2Code ) {
			$(this).attr("selected", "selected");
		}
		$("#type2codeSelection").selectBoxIt().data("selectBoxIt");
		$("#type2codeSelection").data("selectBox-selectBoxIt").refresh();
	});
	
	$("#paintName").val(paintObj.paintName);
	$("#paintDesc").val(paintObj.paintDesc);
	
	//main
	var paintBrandValue = paintObj.paintBrand;
	$("#paintBrand option").each(function() {
        if ($(this).val() == paintBrandValue ) {
            $(this).attr("selected", "selected");
        }
        $("#paintBrand").selectBoxIt().data("selectBoxIt");
		$("#paintBrand").data("selectBox-selectBoxIt").refresh();
    });
	
	var paintModelValue = paintObj.paintModel;
	$("#paintModel option").each(function() {
		if ($(this).val() == paintModelValue ) {
			$(this).attr("selected", "selected");
		}
		$("#paintModel").selectBoxIt().data("selectBoxIt");
		$("#paintModel").data("selectBox-selectBoxIt").refresh();
	});
	
	var paintStyleValue = paintObj.paintStyle;
	$("#paintStyle option").each(function() {
		if ($(this).val() == paintStyleValue ) {
			$(this).attr("selected", "selected");
		}
		$("#paintStyle").selectBoxIt().data("selectBoxIt");
		$("#paintStyle").data("selectBox-selectBoxIt").refresh();
	});
	
	var liquidTypeValue = paintObj.liquidType;
	$("#liquidType option").each(function() {
		if ($(this).val() == liquidTypeValue ) {
			$(this).attr("selected", "selected");
		}
		$("#liquidType").selectBoxIt().data("selectBoxIt");
		$("#liquidType").data("selectBox-selectBoxIt").refresh();
	});
	
	var filmFormerValue = paintObj.filmFormer;
	$("#filmFormer option").each(function() {
		if ($(this).val() == filmFormerValue ) {
			$(this).attr("selected", "selected");
		}
		$("#filmFormer").selectBoxIt().data("selectBoxIt");
		$("#filmFormer").data("selectBox-selectBoxIt").refresh();
	});
	
	var paintStorageValue = paintObj.paintStorage;
	$("#paintStorage option").each(function() {
		if ($(this).val() == paintStorageValue ) {
			$(this).attr("selected", "selected");
		}
		$("#paintStorage").selectBoxIt().data("selectBoxIt");
		$("#paintStorage").data("selectBox-selectBoxIt").refresh();
	});
	
	$("#solidProportion").val(paintObj.solidProportion);
	$("#brushArea").val(paintObj.brushArea);
	$("#tensileStrength").val(paintObj.tensileStrength);
	$("#breakElongation").val(paintObj.breakElongation);
	$("#surfaceDryTime").val(paintObj.surfaceDryTime);
	$("#dryingTime").val(paintObj.dryingTime);
	$("#coatingFilmColor").val(paintObj.coatingFilmColor);
	$("#originPlace").val(paintObj.originPlace);
}