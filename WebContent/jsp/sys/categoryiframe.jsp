<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/header.jsp"%>
<style type="text/css">
	.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
</style>
<SCRIPT type="text/javascript">
var zTree;
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
			selectedMulti: false
		},
		edit: {
			enable: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			onClick: zTreeOnClick
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
	
	function zTreeOnClick(event, treeId, treeNode){
		$("#categoryName_h").val(treeNode.name);
	}

	$(document).ready(function(){
		$.fn.zTree.init($("#treeDemo"), setting);
		zTree = $.fn.zTree.getZTreeObj("treeDemo");
	});
</SCRIPT>
<body class="iframe-body">
	<jsp:include page="../common/settings_pane.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
	<div class="page-container">
		<div class="main-content iframe-content">
			<div class="panel panel-default  iframe-panel">
				<div class="panel-body">
					<input type="hidden" id="categoryName_h"/>
					<div class="zTreeDemoBackground left">
						<ul id="treeDemo" class="ztree"></ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../common/bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
</body>
</html>