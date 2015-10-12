<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade" id="productCategoryDiv">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">选择一个提供服务的商品分类</h4>
			</div>
			<div class="modal-body dialog-iframe-category" id="productCategory_body">
				<iframe id="categoryFrame" width="100%" height="100%"></iframe>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-info" onclick="chooseCategory()" data-dismiss="modal">选择</button>
			</div>
		</div>
	</div>
</div>