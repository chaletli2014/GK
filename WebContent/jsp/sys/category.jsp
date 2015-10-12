<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/header.jsp"%>
<style type="text/css">
	.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
</style>
<SCRIPT type="text/javascript">
var setting = {
		async: {
			enable: true,
			url:"<%=basePath%>categorylist",
			autoParam:["id", "name", "pid"],
			otherParam:{"otherParam":"zTreeAsyncTest"},
			dataFilter: filter
		},
		view: {
			expandSpeed:"",
			addHoverDom: addHoverDom,
			removeHoverDom: removeHoverDom,
			selectedMulti: false
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
			beforeRemove: beforeRemove,
			beforeRename: beforeRename
		}
	};

	function filter(treeId, parentNode, childNodes) {
		if (!childNodes) {
			return null;
		}
		for (var i=0, l=childNodes.length; i<l; i++) {
			childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
		}
		return childNodes;
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
			+ "' title='添加新分类' onfocus='this.blur();'></span>";
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

	$(document).ready(function(){
		$.fn.zTree.init($("#treeDemo"), setting);
	});

	function submitForm(){
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		var categoryJson = zTree.transformToArray(zTree.getNodes());
		var categoryArr = new Array();;
		for(var c in categoryJson){
			var categoryParam={
				id:'',
				name:'',
				pId:''
			};
			categoryParam.id=categoryJson[c].id;
			categoryParam.name=categoryJson[c].name;
			if( categoryJson[c].pId == null ){
				categoryParam.pId='0';
			}else{
				categoryParam.pId=categoryJson[c].pId;
			}
			
			categoryArr.push(categoryParam);
		}
		$("#treeNodes").val(JSON.stringify(categoryArr));
		$("#updateCategory_form").submit();
	}
</SCRIPT>
<body class="page-body">
	<jsp:include page="../common/settings_pane.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
	<div class="page-container"><!-- add class "sidebar-collapsed" to close sidebar by default, "chat-visible" to make chat appear always -->
		<jsp:include page="../common/sidebar.jsp" flush="true">
        	<jsp:param name="basePath" value="<%=basePath%>"/>
        	<jsp:param name="opened" value="${opened}"/>
        	<jsp:param name="actived" value="${actived}"/>
        </jsp:include>
		<div class="main-content">
			<div class="page-title">
				<div class="title-env">
					<h1 class="title">分类列表</h1>
				</div>
					<div class="breadcrumb-env">
						<ol class="breadcrumb bc-1">
						<li>
							<a href="<%=basePath%>index"><i class="fa-home"></i>首页</a>
						</li>
						<li>
							<a href="#" onclick="javascript:void(0)">系统管理</a>
						</li>
						<li class="active">
							<strong>分类管理</strong>
						</li>
						</ol>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="zTreeDemoBackground left">
						<ul id="treeDemo" class="ztree"></ul>
					</div>
				</div>
				<form action="updateCategory" method="post" id="updateCategory_form" >
					<input type="hidden" id="treeNodes" name="treeNodes" />
				</form>
				<div style="text-align: center;margin-top:20px;">
					<button class="btn btn-success btn-icon" onclick="submitForm()">
						<i class="fa-check"></i>
						<span>提交</span>
					</button>
				</div>
			</div>
			<!-- Main Footer -->
			<%@include file="../common/footer.jsp" %>
		</div>
	</div>
	<jsp:include page="../common/bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
</body>
</html>