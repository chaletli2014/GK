<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%
	level = "3";
	subjectTip = "注：三级主体是指具体楼层，如地下室、地面层、标准层等";
	addBtnName = "添加三级主体";
	parentId = request.getParameter("parentId");
%>
<input type="hidden" id="subjectLevel_h" value="<%=level%>"/>
<div class="asset_div" id="asset_subject3" style="display:none;padding-top:10px;">
	<div class="two_nav">
		<button id="newSubject" class="btn btn-info">
			<span class="fa-plus-square"><%=addBtnName %></span>
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
					<th width="25%">隶属于</th>
					<th width="15%">主体名称</th>
					<th width="20%">主体描述</th>
					<th width="15%">主体操作</th>
					<th width="10%">附属构件</th>
				</tr>
			</thead>
			<tbody class="middle-align">
				<c:forEach items="${subjectList3}" var="subject">
					<tr>
						<td>${subject.subject1Name}&nbsp;-&nbsp;${subject.subject2Name}</td>
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