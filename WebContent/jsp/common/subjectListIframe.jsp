<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="header.jsp"%>
<script type="text/javascript" src="<%=basePath%>/js/goodsquick/subjectList.js"></script>
<style type="text/css">
.ztree li span.button.add {
	margin-left:2px; 
	margin-right: -1px; 
	background-position:-144px 0; 
	vertical-align:top; 
	*vertical-align:middle
}
</style>
<SCRIPT type="text/javascript">
var zTree;
var setting = {
		async: {
			enable: true,
			url:"<%=basePath%>subjectlist",
			autoParam:["id", "name", "pid"],
			otherParam:{"otherParam":"zTreeAsyncTest"},
			dataFilter: filter
		},
		view: {
			expandSpeed:"",
			selectedMulti: false,
			addHoverDom: addHoverDom,
			removeHoverDom: removeHoverDom
		},
		edit: {
			enable: true
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			onClick: zTreeOnClick,
			beforeRemove: beforeRemove,
			beforeRename: beforeRename
		}
	};

$(document).ready(function(){
	$.fn.zTree.init($("#treeDemo"), setting);
	zTree = $.fn.zTree.getZTreeObj("treeDemo");
});
function filter(treeId, parentNode, childNodes) {
	if (!childNodes) {
		return null;
	}
	for (var i=0, l=childNodes.length; i<l; i++) {
		childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
	}
	return childNodes;
}

function zTreeOnClick(event, treeId, treeNode){
	$("#levelName_h").val(treeNode.name);
	$('#houseSubjectId', window.parent.document).val(treeNode.id);
	showModule(treeNode.id);
}

function beforeRemove(treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	zTree.selectNode(treeNode);
	return confirm("确认删除 节点 -- " + treeNode.name + " 吗？");
}		
function beforeRename(treeId, treeNode, newName) {
	if (newName.length == 0) {
		alert("节点名称不能为空.");
		return false;
	}
	return true;
}

var newCount = 1;
function addHoverDom(treeId, treeNode) {
	var sObj = $("#" + treeNode.tId + "_span");
	if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
	var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
		+ "' title='添加新主体' onfocus='this.blur();'></span>";
	sObj.after(addStr);
	var btn = $("#addBtn_"+treeNode.tId);
	if (btn) btn.bind("click", function(){
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"new node" + (newCount++)});
		return false;
	});
};
function removeHoverDom(treeId, treeNode) {
	$("#addBtn_"+treeNode.tId).unbind().remove();
};
function submitHouseSubject(){
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	var subjectJson = zTree.transformToArray(zTree.getNodes());
	var subjectArr = new Array();
	for(var c in subjectJson){
		var subjectParam={
			id:'',
			name:'',
			pId:''
		};
		subjectParam.id=subjectJson[c].id;
		subjectParam.name=subjectJson[c].name;
		if( subjectJson[c].pId == null ){
			subjectParam.pId='0';
		}else{
			subjectParam.pId=subjectJson[c].pId;
		}
		
		subjectArr.push(subjectParam);
	}
	
	jQuery.ajax({
		url: basePath+"updateSubject",
		data:{
			treeNodes : JSON.stringify(subjectArr)
		},
        error : function() {
        },
        success : function(data) {
        	window.location.reload();
        }
    });
}
</SCRIPT>
<body class="iframe-body">
	<jsp:include page="settings_pane.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
	<div class="page-container">
		<div class="main-content iframe-content">
			<div class="panel panel-default  iframe-panel">
				<div class="panel-body">
					<input type="hidden" id="levelName_h"/>
					<div class="zTreeDemoBackground left">
						<ul id="treeDemo" class="ztree"></ul>
					</div>
					<div style="text-align: center;margin-top:20px;">
						<button class="btn btn-success btn-icon" onclick="submitHouseSubject()">
							<i class="fa-check"></i>
							<span>保存</span>
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
</body>
</html>