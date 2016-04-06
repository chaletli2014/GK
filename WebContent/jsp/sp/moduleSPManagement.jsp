<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/header.jsp"%>
<script type="text/javascript" src="<%=basePath%>js/goodsquick/moduleSPManagement.js"></script>
<body class="page-body">
	<jsp:include page="../common/settings_pane.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
	<div class="page-container">
		<jsp:include page="../common/sidebar.jsp" flush="true">
        	<jsp:param name="basePath" value="<%=basePath%>"/>
        	<jsp:param name="opened" value="${opened}"/>
        	<jsp:param name="actived" value="${actived}"/>
        </jsp:include>
		<div class="main-content">
			<jsp:include page="../common/main-nav.jsp" flush="true">
	        	<jsp:param name="basePath" value="<%=basePath%>"/>
	        </jsp:include>
			<div class="page-title">
				<%@include file="../common/nav-title.jsp"%>
				<span style="margin-left:28px;font-size:16px;font-weight: bold;">物链列表</span>
				<span style="float:right;margin-top:2px;">
					<a style="margin-right:30px;" id="newSpLink" class="btn btn-orange">
						<i class="fa fa-plus"></i>
						新增供应商
					</a>
				</span>
			</div>
			<div class="panel panel-default">
				<div class="panel-body" style="padding-top:0px;">
					<input type="hidden" name="partCode" id="partCode" value="${partCode}"/>
					<input type="hidden" name="spTypeCode" id="spTypeCode" value="${spTypeCode}"/>
					<ul class="sp_type_ul" id="sp_type_ul">
						<c:forEach items="${spTypes}" var="spType">
							<li lid="${spType.dicCode}" class="<c:if test="${spType.dicCode == spTypeCode}">active</c:if>" >${spType.dicName}</li>
						</c:forEach>
					</ul>
					<script type="text/javascript">
						jQuery(document).ready(function($){
							$("#moduleType1").selectBoxIt().on('open', function(){
								$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
							});
							$("#moduleType2").selectBoxIt().on('open', function(){
								$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
							});
						});
					</script>
					<ul class="moduleType_ul">
						<li style="width:60px;height:35px;line-height: 35px;margin-right:10px;">
							<span>产品类别</span>
						</li>
						<li>
							<select class="form-control" id="moduleType1" name="moduleType1" onchange="populateModuleType2ByType1('moduleType2',this.value)">
								<option value="">请选择</option>
								<c:forEach items="${moduleType1}" var="moduleType">
									<option value="${moduleType.dicCode}">${moduleType.dicName}</option>
								</c:forEach>
							</select>
						</li>
						<li>
							<select class="form-control" id="moduleType2" name="moduleType2">
								<option value="">--请选择--</option>
							</select>
						</li>
					</ul>
					<div>
						<table class="table table-bordered table-striped" id="moduleSPTable">
							<thead>
								<tr>
									<th width="20%">${spTypeName}名称</th>
									<th width="15%">产品与服务</th>
									<th width="15%">联系电话</th>
									<th width="10%">来源</th>
									<th width="10%">状态</th>
									<th width="30%">操作 </th>
								</tr>
							</thead>
							<tbody class="middle-align">
								<c:forEach items="${houseModuleSPList}" var="moduleSP">
									<tr>
										<td >${moduleSP.spName}</td>
										<td >${moduleSP.proServiceName}</td>
										<td >${moduleSP.spTel}</td>
										<td >${moduleSP.fromSource}</td>
										<td >${moduleSP.relationStatus}</td>
										<td>
											<a aid="${moduleSP.id}" href="#" class="btn btn-orange btn-sm btn-icon icon-left moduleViewLink">
												查看
											</a>
											<a aid="${moduleSP.id}" href="#" class="btn btn-secondary btn-sm btn-icon icon-left moduleEditLink">
												编辑
											</a>
											<a aid="${moduleSP.id}" href="#" class="btn btn-blue btn-sm btn-icon icon-left moduleRelateLink">
												关联
											</a>
											<a aid="${moduleSP.id}" href="#" class="btn btn-black btn-sm btn-icon icon-left moduleDeleteLink">
												删除
											</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<%@include file="../common/footer.jsp" %>
		</div>
	</div>
	<%@include file="addEquModuleSP.jsp" %>
	<jsp:include page="../common/bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
</body>
</html>