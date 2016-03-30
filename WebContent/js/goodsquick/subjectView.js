var setting = {
	async: {
		enable: true,
		url:basePath+"subjectViewList",
		autoParam:["id"]
	},
	data: {
		simpleData: {
			enable: true
		}
	},
	callback: {
	}
};

$(document).ready(function(){
	$.fn.zTree.init($("#subjectTree"), setting);
	$.fn.zTree.init($("#moduleTree"), setting);
});