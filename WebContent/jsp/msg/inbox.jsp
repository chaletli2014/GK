<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/header.jsp"%>
<script type="text/javascript" src="<%=basePath%>js/goodsquick/msg.js"></script>
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
				<span style="margin-left:28px;font-size:16px;font-weight: bold;">收件箱</span>
			</div>
			<div class="panel panel-default">
				<div class="panel-body" style="padding-top:0px;">
					<div>
						<button style="margin-right:10px;" id="delInMsgLink" class="btn btn-gray">
							删除
						</button>
						<button style="margin-right:10px;" id="readMsgLink" class="btn btn-gray">
							标记为已读
						</button>
						<span style="float:right;">
							<input placeholder="输入你想要查找的关键字" name="searchKey" id="searchKey"/>
							<button><i class="fa-search"></i></button>
						</span>
					</div>
					<div>
						<table class="table table-bordered table-striped" id="inBoxTable">
							<thead>
								<tr>
									<th class="no-sorting">
										
									</th>
									<th class="no-sorting">
										&nbsp;
									</th>
									<th width="20%">发件人</th>
									<th width="35%">发送讯息</th>
									<th width="15%">发送时间</th>
									<th width="15%">信息类型</th>
									<th width="15%">操作 </th>
								</tr>
							</thead>
							<tbody class="middle-align">
							<c:forEach items="${messagelist}" var="msg">
								<tr>
									<td>
										<input mid="${msg.id}" type="checkbox" class="cbr" name="incheckbox">
									</td>
									<td>
										<c:if test="${msg.statusReceiver=='1'}">
										<i class="linecons-mail"></i>
										</c:if>
									</td>
									<td >${msg.sender}</td>
									<td >${msg.messageTitle}</td>
									<td >${msg.createDate}</td>
									<td >${msg.messageTypeName}</td>
									<td>
										<a aid="${msg.id}" href="#" class="btn btn-orange btn-sm btn-icon icon-left msgViewLink">
											查看
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
	<%@include file="viewMsg.jsp" %>
	<jsp:include page="../common/bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
</body>
</html>