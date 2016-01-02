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