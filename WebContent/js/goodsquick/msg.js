jQuery(document).ready(function($){
	initDataTable();
	
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
});

function initDataTable(){
	$("#inBoxTable").dataTable({
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

function chooseTargetUser(){
	var spCheckbox = $("input[name='spCheckbox']");
	
	var selectedUser='';
	var selectedIds=',';
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

//实例化编辑器
//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('msgContent')就能拿到相关的实例
var ue = UE.getEditor('msgContent');

function isFocus(e){
    alert(UE.getEditor('msgContent').isFocus());
    UE.dom.domUtils.preventDefault(e)
}
function setblur(e){
    UE.getEditor('msgContent').blur();
    UE.dom.domUtils.preventDefault(e)
}
function insertHtml() {
    var value = prompt('插入html代码', '');
    UE.getEditor('msgContent').execCommand('insertHtml', value)
}
function createEditor() {
    enableBtn();
    UE.getEditor('msgContent');
}
function getAllHtml() {
    alert(UE.getEditor('msgContent').getAllHtml())
}
function getContent() {
    var arr = [];
    arr.push("使用editor.getContent()方法可以获得编辑器的内容");
    arr.push("内容为：");
    arr.push(UE.getEditor('msgContent').getContent());
    alert(arr.join("\n"));
}
function getPlainTxt() {
    var arr = [];
    arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
    arr.push("内容为：");
    arr.push(UE.getEditor('msgContent').getPlainTxt());
    alert(arr.join('\n'))
}
function setContent(isAppendTo) {
    var arr = [];
    arr.push("使用editor.setContent('欢迎使用ueditor')方法可以设置编辑器的内容");
    UE.getEditor('msgContent').setContent('欢迎使用ueditor', isAppendTo);
    alert(arr.join("\n"));
}
function setDisabled() {
    UE.getEditor('msgContent').setDisabled('fullscreen');
    disableBtn("enable");
}

function setEnabled() {
    UE.getEditor('msgContent').setEnabled();
    enableBtn();
}

function getText() {
    //当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
    var range = UE.getEditor('msgContent').selection.getRange();
    range.select();
    var txt = UE.getEditor('msgContent').selection.getText();
    alert(txt)
}

function getContentTxt() {
    var arr = [];
    arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
    arr.push("编辑器的纯文本内容为：");
    arr.push(UE.getEditor('msgContent').getContentTxt());
    alert(arr.join("\n"));
}
function hasContent() {
    var arr = [];
    arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
    arr.push("判断结果为：");
    arr.push(UE.getEditor('msgContent').hasContents());
    alert(arr.join("\n"));
}
function setFocus() {
    UE.getEditor('msgContent').focus();
}
function deleteEditor() {
    disableBtn();
    UE.getEditor('msgContent').destroy();
}
function disableBtn(str) {
    var div = document.getElementById('btns');
    var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
    for (var i = 0, btn; btn = btns[i++];) {
        if (btn.id == str) {
            UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
        } else {
            btn.setAttribute("disabled", "true");
        }
    }
}
function enableBtn() {
    var div = document.getElementById('btns');
    var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
    for (var i = 0, btn; btn = btns[i++];) {
        UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
    }
}

function getLocalData () {
    alert(UE.getEditor('msgContent').execCommand( "getlocaldata" ));
}

function clearLocalData () {
    UE.getEditor('msgContent').execCommand( "clearlocaldata" );
    alert("已清空草稿箱")
}
