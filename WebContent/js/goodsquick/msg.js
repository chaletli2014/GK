jQuery(document).ready(function($){
	if( $("#inBoxTable") ){
		initInDataTable();
	}
	if( $("#outBoxTable") ){
		initOutDataTable();
	}
	
	$("#sendMsgLink").click(function(){
		if( !checkInput() ){
			return false;
		}
		
		sendNewMsg();
	});
	
	$("#addTargetUser").click(function(){
		showTargetUsers();
	});
	
	$("#chooseTarget").click(function(){
		chooseTargetUser();
	});
	
	$("body").delegate('.msgViewLink', 'click', function(){
		showViewMsgDiv($(this).attr("aid"));
	});
	
	$("body").delegate('#delInMsgLink', 'click', function(){
		var msgInboxCheckbox = $("input[name='incheckbox']");
		var choseMsgId = choseMsg(msgInboxCheckbox);
		if( choseMsgId != '' ){
			deleteMsg(choseMsgId,'in');
		}else{
			jAlert("未选择任何讯息", "提示");
		}
	});
	
	$("body").delegate('#delOutMsgLink', 'click', function(){
		var msgOutboxCheckbox = $("input[name='outcheckbox']");
		var choseMsgId = choseMsg(msgOutboxCheckbox);
		if( choseMsgId != '' ){
			deleteMsg(choseMsgId,'out');
		}else{
			jAlert("未选择任何讯息", "提示");
		}
	});
	
	$("body").delegate('#readMsgLink', 'click', function(){
		var msgInboxCheckbox = $("input[name='incheckbox']");
		var choseMsgId = choseMsg(msgInboxCheckbox);
		if( choseMsgId != '' ){
			makeReadMsg(choseMsgId);
		}else{
			jAlert("未选择任何讯息", "提示");
		}
	});
});

function initInDataTable(){
	$("#inBoxTable").dataTable({
		dom: "t" + "<'row'<'col-xs-6'i><'col-xs-6'p>>",
		aoColumns: [
			null,
			null,
			null,
			null,
			null,
			null,
			null
		]
	});
	// Replace checkboxes when they appear
	var $state = $("#inBoxTable thead input[type='checkbox']");
	
	$("#inBoxTable").on('draw.dt', function() {
		cbr_replace();
		$state.trigger('change');
	});
	
	// Script to select all checkboxes
	$state.on('change', function(ev) {
		var $chcks = $("#inBoxTable tbody input[type='checkbox']");
		if($state.is(':checked')){
			$chcks.prop('checked', true).trigger('change');
		} else {
			$chcks.prop('checked', false).trigger('change');
		}
	});
}
function initOutDataTable(){
	$("#outBoxTable").dataTable({
		dom: "t" + "<'row'<'col-xs-6'i><'col-xs-6'p>>",
		aoColumns: [
		            null,
		            null,
		            null,
		            null,
		            null,
		            null
		            ]
	});
	// Replace checkboxes when they appear
	var $state = $("#outBoxTable thead input[type='checkbox']");
	
	$("#outBoxTable").on('draw.dt', function() {
		cbr_replace();
		$state.trigger('change');
	});
	
	// Script to select all checkboxes
	$state.on('change', function(ev) {
		var $chcks = $("#outBoxTable tbody input[type='checkbox']");
		if($state.is(':checked')){
			$chcks.prop('checked', true).trigger('change');
		} else {
			$chcks.prop('checked', false).trigger('change');
		}
	});
}

function checkInput(){
	if( $("#targetUser").val() == '' ){
		jAlert("收件人不能为空", "错误");
		return false;
	}
	if( $("#msgTitle").val() == '' ){
		jAlert("讯息标题不能为空", "错误");
		return false;
	}
	
	var msgContent = UE.getEditor('msgContent').getContent();
	if( msgContent == '' ){
		jAlert("内容不能为空", "错误");
		return false;
	}
	
	return true;
}

function choseMsg(checkboxs){
	var choseMsgId="";
	$.each(checkboxs, function(i, checkbox) {
		if(this.checked){
			choseMsgId = choseMsgId + $(this).attr("mid") + ","
		}
	});
	return choseMsgId.substr(0,choseMsgId.length-1);
}

function sendNewMsg(){
	var targetUser = $("#targetUser").val();
	var targetUserIds = $("#targetUserIds").val();
	var msgTitle = $("#msgTitle").val();
	
	jQuery.ajax({
		url: basePath+"sendNewMsg",
		data:{
			msgType : 'common',
			targetUser : targetUser,
			targetUserIds : targetUserIds,
			msgTitle : msgTitle,
			msgContent : UE.getEditor('msgContent').getContent()
		},
		success: function(response){
			jAlert("讯息发送成功", "提示");
		}
	});
}

function deleteMsg(msgIds,boxType){
	jQuery.ajax({
		url: basePath+"updateMsg",
		data:{
			msgIds : msgIds,
			boxType : boxType,
			status : '0'
		},
		success: function(response){
			jAlert("删除成功", "提示",function(){
				window.location.reload();
			});
		}
	});
}

function makeReadMsg(msgIds){
	jQuery.ajax({
		url: basePath+"updateMsg",
		data:{
			msgIds : msgIds,
			boxType : 'in',
			status : '2'
		},
		success: function(response){
			jAlert("标记成功", "提示",function(){
				window.location.reload();
			});
		}
	});
}

function chooseTargetUser(){
	var spCheckbox = $("input[name='spCheckbox']");
	
	var selectedUser='';
	var selectedIds='';
	$.each(spCheckbox, function(i, checkbox) {
		if(this.checked){
			selectedUser = selectedUser + $(this).parent().parent().find("td:eq(1)").text() +',';
			selectedIds = selectedIds + $(this).parent().parent().find("td:eq(0)").children().attr("cid") +',';
		}
	});
	$("#targetUser").val(selectedUser.substr(0,selectedUser.length-1));
	$("#targetUserIds").val(selectedIds);
	jQuery('.close').click();
}

function showTargetUsers(){
	jQuery.ajax({
		url: basePath+"getTargetUser",
		success: function(response){
			var targetUsers = response.targetUsers;
			if( targetUsers != null ){
				var targetUserTable = $("#targetUserTable");
				targetUserTable.find('tbody').html("");
	        	var tbody = "";
	        	$.each(targetUsers,function(n,sp){
	        		tbody = tbody + "<tr>";
	        		tbody = tbody + "<td ><input cid=\""+sp.id+"\" type=\"checkbox\" name=\"spCheckbox\" class=\"cbr\"></td>";
	        		tbody = tbody + "<td >"+sp.name+"</td>";
	        		tbody = tbody + "</tr>";
	        	});
	        	targetUserTable.find('tbody').html(tbody);
			}
			jQuery('#targetUsersDiv').modal('show', {backdrop: 'static'});
		}
	});
}

function showViewMsgDiv(msgId){
	jQuery.ajax({
		url: basePath+"getMsgInfo",
		data:{
			msgId:msgId
		},
		success: function(response){
			var msgObj = response.msgObj;
			if( msgObj != null ){
				$("#msgTitle").html(msgObj.messageTitle);
				$("#msgFrom").html(msgObj.sender);
				$("#msgTo").html(msgObj.receiverNames);
				$("#msgFromTime").html(msgObj.createDateFull);
				$("#msgContent").html(msgObj.messageContent);
			}
			jQuery('#viewMsgDiv').modal('show', {backdrop: 'static'});
		}
	});
}