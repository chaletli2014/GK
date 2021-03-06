<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/header.jsp"%>
<script type="text/javascript" src="<%=basePath%>js/goodsquick/houseOther.js"></script>
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
				<div>
					<button id="newOther" class="btn btn-info">
						<li class="fa-plus-square">添加材料&装饰</li>
					</button>
					<button id="saveOther" class="btn btn-info">
						<span>保存</span>
					</button>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-body" style="padding-top:0px;">
					<table class="table table-hover" id="otherTable">
						<thead>
							<tr>
								<th width="7%">分类</th>
								<th width="15%">名称</th>
								<th width="30%">描述</th>
								<th>使用位置</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody class="middle-align">
							<c:forEach items="${houseOthers}" var="houseOther">
								<tr>
									<td class="dataEditable" title="${houseOther.typeName}">${houseOther.typeName}</td>
									<td class="dataEditable" title="${houseOther.name}">${houseOther.name}</td>
									<td class="dataEditable" title="${houseOther.desc}">${houseOther.desc}</td>
									<td>${houseOther.subjectName}-${houseOther.moduleName}</td>
									<td>
										<a id="${houseOther.id}" dtype="${houseOther.typeCode}" class="btn btn-secondary btn-sm btn-icon icon-left modifyOther">
											编辑
										</a>
										<a id="${houseOther.id}" dtype="${houseOther.typeCode}" class="btn btn-danger btn-sm btn-icon icon-left removeOther">
											删除
										</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<%@include file="../common/footer.jsp" %>
		</div>
	</div>
	<%@include file="houseOtherPaint.jsp" %>
	<jsp:include page="../common/bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
</body>
</html>