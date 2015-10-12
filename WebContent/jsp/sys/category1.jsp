<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/header.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	$(".editLinkDiv a").each(function(i, el){
		$(this).on('click', function(ev){
			var linkTitle = $(this)[0].title;
			var linkName = $(this)[0].name;
			
			if( linkTitle == 'edit' ){
				jQuery('#editDiv').modal('show', {backdrop: 'static'});
				jQuery.ajax({
					url: "<%=basePath%>getcategorybyid",
					data:{
						categoryId : linkName
					}
					,success: function(response){
						var data = response.category;
						
						jQuery('#editDiv .modal-body .content').show();
						jQuery('#editDiv .modal-body .content #categoryname').val(data.name);
						jQuery('#editDiv .modal-body .content #categorycode').val(data.code);
						jQuery('#editDiv .modal-body .content #categorydesc').val(data.desc);
						jQuery('#editDiv .modal-body .content #parentId').val(data.parentId);
					}
				});
			}else if( linkTitle == 'add' ){
				jQuery('#editDiv').modal('show', {backdrop: 'static'});
				jQuery('#editDiv .modal-body .content').show();
				jQuery('#editDiv .modal-body .content #parentId').val(data.parentId);
			}else if( linkTitle == 'delete' ){
				alert('delete');
			}
		});
	});
	
	var count = 0;
	$(".category_li a").each(function(i, el){
		$(this).on('click', function(ev){
			var categoryId = $(this)[0].id;
			
			var currentLi = $("#"+$(this)[0].parentNode.id);
			
			currentLi.toggleClass("opened");
			
			jQuery.ajax({
				url: "<%=basePath%>getcategorybypid",
				data:{
					pid : categoryId
				}
				,success: function(response){
					if( count == 0 ){
						var categoryList = response.categoryList;
						var childNodes = '<ul>';
						$.each(categoryList,function(n,value) {
							if( '1' == value.hasChild ){
								childNodes = childNodes + '<li class="category_li has-sub" title="cate_li" id="categoryli_'+value.id+'"> ';
							}else{
								childNodes = childNodes + '<li class="category_li" title="cate_li" id="categoryli_'+value.id+'"> ';
							}
							childNodes = childNodes + '<a href="#" onclick="javascript:void(0)" id="' + value.id + '"><span class="title">'+value.name+'</span></a></li>';
							childNodes = childNodes + '<div class="editLinkDiv" style="position:absolute;top:1px;left:180px;padding:13px 5px;width:100%;">';
							childNodes = childNodes + '<a href="#" title="edit" name="${goodsCategory.id}" style="display:inline;padding-left:2px;">编辑</a>';
							childNodes = childNodes + '<a href="#" title="add" name="${goodsCategory.id}" style="display:inline;padding-left:2px;">新增子类</a>';
							childNodes = childNodes + '<a href="#" title="delete" name="${goodsCategory.id}" style="display:inline;padding-left:2px;">删除</a>';
							childNodes = childNodes + '</div>';
			            });
						childNodes = childNodes + '</ul>';
						currentLi.append(childNodes);
						count = 1;
					}else{
						currentLi.children().filter('ul').remove();
						count = 0;
					}
				}
			});
		});
		
	});
});
</script>
<style>
#editDiv .modal-body .content div.panel-body div{
	margin:10px 0px;
}
</style>
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
					<div>
						<a href="<%=basePath%>addcategory" class="btn btn-info btn-sm btn-icon icon-left">新增一级分类</a>
					</div>
					<div class="sidebar-menu categoryTree_top_div">
						<ul id="main-menu" class="main-menu">
							<c:forEach items="${categoryList}" var="goodsCategory">
								<c:choose>
									<c:when test="${goodsCategory.hasChild=='1'}">
										<li class="category_li has-sub" title="cate_li" id="categoryli_${goodsCategory.id}">
									</c:when>
									<c:otherwise>
										<li class="category_li" title="cate_li" id="categoryli_${goodsCategory.id}">
									</c:otherwise>
								</c:choose>
									<a href="#" id="${goodsCategory.id}">
										<i class="linecons-cog"></i>
										<span class="title">${goodsCategory.name}</span>
									</a>
									<div class="editLinkDiv" style="position:absolute;top:1px;left:180px;padding:13px 5px;width:100%;">
										<a href="#" title="edit" name="${goodsCategory.id}" style="display:inline;padding-left:2px;">编辑</a>
										<a href="#" title="add" name="${goodsCategory.id}" style="display:inline;padding-left:2px;">新增子类</a>
										<a href="#" title="delete" name="${goodsCategory.id}" style="display:inline;padding-left:2px;">删除</a>
									</div>
								</li>
							</c:forEach>
							<li>
								<a href="#" onclick="javascript:void(0)">
									<i class="linecons-cog"></i>
									<span class="title">动产</span>
								</a>
								<div class="editLinkDiv" style="position:absolute;top:1px;left:180px;padding:13px 5px;width:100%;">
									<a href="#" title="edit" name="dc" style="display:inline;padding-left:2px;">编辑</a>
									<a href="#" title="add" name="dc" style="display:inline;padding-left:2px;">新增子类</a>
									<a href="#" title="delete" name="dc" style="display:inline;padding-left:2px;">删除</a>
								</div>
								<ul>
									<li>
										<a href="#" onclick="javascript:void(0)">
											<span class="title">居家物业</span>
										</a>
										<div class="editLinkDiv" style="position:absolute;top:1px;left:180px;padding:13px 5px;width:100%;">
											<a href="#" title="edit" name="jjwy" style="display:inline;padding-left:2px;">编辑</a>
											<a href="#" title="add" name="jjwy" style="display:inline;padding-left:2px;">新增子类</a>
											<a href="#" title="delete" name="jjwy" style="display:inline;padding-left:2px;">删除</a>
										</div>
									</li>
									<li>
										<a href="#" onclick="javascript:void(0)">
											<span class="title">角色管理</span>
										</a>
									</li>
									<li>
										<a href="#" onclick="javascript:void(0)">
											<span class="title">权限管理</span>
										</a>
									</li>
									<li>
										<a href="#" onclick="javascript:void(0)">
											<span class="title">分类管理</span>
										</a>
									</li>
									<li>
										<a href="#" onclick="javascript:void(0)">
											<span class="title">数据字典管理</span>
										</a>
									</li>
									<li>
										<a href="#" onclick="javascript:void(0)">
											<span class="title">配置项管理</span>
										</a>
									</li>
								</ul>
							</li>
							<li>
								<a href="#" onclick="javascript:void(0)">
									<i class="linecons-note"></i>
									<span class="title">不动产</span>
								</a>
								<ul>
									<li>
										<a href="#" onclick="javascript:void(0)">
											<span class="title">我的物品库</span>
										</a>
									</li>
								</ul>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<!-- Main Footer -->
			<%@include file="../common/footer.jsp" %>
		</div>
	</div>
	<div class="modal fade" id="editDiv">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">分类编辑</h4>
				</div>
				<div class="modal-body">
					<div class="content" style="display:none;">
						<div class="panel-body">
							<div class="form-group">
								<label class="col-sm-2 control-label" for="field-1">分类名称</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="categoryname" name="categoryname">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label" for="field-1">分类编码</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="categorycode" name="categorycode">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label" for="field-1">分类描述</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="categorydesc" name="categorydesc">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label" for="field-1">上级分类</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="parentId" name="parentId">
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-info">提交</button>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../common/bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
</body>
</html>