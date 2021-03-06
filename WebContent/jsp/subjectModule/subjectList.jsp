<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%
	String subjectTip = "";
	String addBtnName = "";
	String level = request.getParameter("level");
	if( StringUtils.isBlank(level) ){
		level = (String)request.getSession().getAttribute("subjectLevel");
	}
	
	if( "1".equalsIgnoreCase(level) ){
		subjectTip = "注：一级主体是指住宅小区里的独栋楼宇或裙楼";
		addBtnName = "添加一级主体";
	}else if( "2".equalsIgnoreCase(level) ){
		subjectTip = "注：二级主体是指独栋楼宇或裙楼的单元楼";
		addBtnName = "添加二级主体";
	}else if( "3".equalsIgnoreCase(level) ){
		subjectTip = "注：三级主体是指具体楼层，如地下室、地面层、标准层等";
		addBtnName = "添加三级主体";
	}
	String parentId = request.getParameter("parentId");
%>
<%@include file="../common/header.jsp"%>
<script type="text/javascript" src="<%=basePath%>js/goodsquick/subjectList.js"></script>
<body class="page-body" onload="checkMessage('${errorMessage}')">
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
	        <div class="two_nav">
				<ul class="nav_ul">
					<li class="dropdown <%if( "1".equalsIgnoreCase(level) ){ %>hover-line active<%}%>">
						<a aid="1" class="asset_a_subject" href="javascript:void(0)" data-toggle="dropdown">
							<span class="">一级主体</span>
						</a>
					</li>
					<li class="dropdown <%if( "2".equalsIgnoreCase(level) ){ %>hover-line active<%}%>">
						<a aid="2" class="asset_a_subject" href="javascript:void(0)" data-toggle="dropdown">
							<span class="">二级主体</span>
						</a>
					</li>
					<li class="dropdown <%if( "3".equalsIgnoreCase(level) ){ %>hover-line active<%}%>">
						<a aid="3" class="asset_a_subject" href="javascript:void(0)" data-toggle="dropdown">
							<span class="">三级主体</span>
						</a>
					</li>
					<li>
						<a aid="3" class="asset_a_subject" href="javascript:void(0)" data-toggle="dropdown">
							<span class="">一键创建</span>
						</a>
					</li>
				</ul>
			</div>
			<div class="panel panel-default asset_div" id="subject_div" style="display:none;">
				<input type="hidden" id="subjectLevel_h" value="<%=level%>"/>
				<input type="hidden" id="parentId_h" value="<%=parentId%>"/>
				<div>
					<button id="newSubject" class="btn btn-info">
						<li class="fa-plus-square"><%=addBtnName %></li>
					</button>
					<button id="saveSubject" class="btn btn-info">
						<span>保存</span>
					</button>
					<span class="subject_tip"><%=subjectTip %></span>
				</div>
				<div class="panel-body" style="padding-top:0px;">
					<table class="table table-hover" id="subjectTable">
						<thead>
							<tr>
								<%if( !"1".equalsIgnoreCase(level) ){%>
								<th width="25%">隶属于</th>
								<%} %>
								<th width="15%">主体名称</th>
								<th width="20%">主体描述</th>
								<th width="15%">主体操作</th>
								<th width="10%">附属构件</th>
							</tr>
						</thead>
						<tbody class="middle-align">
							<c:forEach items="${subjectList}" var="subject">
								<tr>
									<%if( "2".equalsIgnoreCase(level) ){%>
									<td>${subject.subject1Name}</td>
									<%}else if( "3".equalsIgnoreCase(level) ){%>
									<td>${subject.subject2Name}</td>
									<%} %>
									<td class="dataEditable" title="${subject.name}">${subject.name}</td>
									<td class="dataEditable" title="${subject.desc}">${subject.desc}</td>
									<td class="subjectListBtnTD">
										<a id="${subject.id}" class="btn btn-secondary btn-sm btn-icon icon-left modifySubject">
											编辑
										</a>
										<a id="${subject.id}" class="btn btn-danger btn-sm btn-icon icon-left removeSubject">
											删除
										</a>
									</td>
									<td>
										<a id="${subject.id}" class="btn btn-secondary btn-sm btn-icon icon-left showModule">
											查看构件
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
	<%@include file="subjectModule.jsp" %>
	<%@include file="subjectModify.jsp" %>
	<jsp:include page="../common/bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
</body>
</html>