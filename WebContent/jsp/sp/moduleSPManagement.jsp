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
				<c:if test="${partCode == 'sp_equ' || partCode == 'sp_other'}">
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
						<li style="width:100px;height:35px;line-height: 35px;">
							<span>选择组件</span>
						</li>
						<li>
							<select class="form-control" id="moduleType1" name="moduleType1" onchange="populateModuleType2ByType1(this.value)">
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
				</c:if>
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
					<div style="margin-top:20px;">
						<c:if test="${partCode == 'sp_equ'}">
						<a style="float:right;margin-right:16px;" id="newEquSpLink" class="btn btn-orange">
							<i class="fa fa-plus"></i>
							新增
						</a>
						</c:if>
						<c:if test="${partCode == 'sp_subject'}">
						<a style="float:right;margin-right:16px;" id="newSpLink" class="btn btn-orange">
							<i class="fa fa-plus"></i>
							新增
						</a>
						</c:if>
						<c:if test="${partCode == 'sp_other'}">
						<a style="float:right;margin-right:16px;" id="newSpLink" class="btn btn-orange">
							<i class="fa fa-plus"></i>
							新增
						</a>
						</c:if>
					</div>
					<div>
						<table class="table table-bordered table-striped" id="moduleSPTable">
							<thead>
								<tr>
									<c:if test="${partCode == 'sp_equ'}">
									<th>品牌</th>
									<th>单位名称</th>
									</c:if>
									<c:if test="${partCode == 'sp_subject' || partCode == 'sp_other'}">
									<th>${spTypeName}名称</th>
									</c:if>
									<th>联系电话</th>
									<th>操作 </th>
								</tr>
							</thead>
							<tbody class="middle-align">
								<c:forEach items="${houseModuleSPList}" var="moduleSP">
									<tr>
										<c:if test="${partCode == 'sp_equ'}">
										<td>${moduleSP.brandName}</td>
										</c:if>
										<td >${moduleSP.spName}</td>
										<td >${moduleSP.spTel}</td>
										<td>
											<a aid="${moduleSP.id}" href="#" class="btn btn-orange btn-sm btn-icon icon-left moduleEditLink">
												编辑
											</a>
											<a aid="${moduleSP.id}" href="#" class="btn btn-danger btn-sm btn-icon icon-left moduleDeleteLink">
												删除
											</a>
											<a aid="${moduleSP.id}" href="#" class="btn btn-blue btn-sm btn-icon icon-left moduleRelateLink">
												关联
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
	<%@include file="addModuleSP.jsp" %>
	<%@include file="addEquModuleSP.jsp" %>
	<jsp:include page="../common/bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
</body>
</html>