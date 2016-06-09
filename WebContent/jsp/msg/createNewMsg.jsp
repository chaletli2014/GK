<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/header.jsp"%>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>js/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>js/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>js/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="<%=basePath%>js/goodsquick/msg.js"></script>
<body class="page-body">
	<input type="hidden" id="basePath" value="<%=basePath%>">
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
				<a style="margin-left:30px;" id="sendMsgLink" class="btn btn-secondary">
					<i class="fa fa-send"></i>
					发送
				</a>
			</div>
			<div class="panel panel-default" style="padding-top:0px;">
				<div class="panel-body" style="padding-top:0px;">
					<div class="row">
					<div class="col-md-12">
						<ul class="nav nav-tabs nav-tabs-justified">
							<li class="active">
								<a href="#msg_tab_basic" data-toggle="tab">
									<span class="visible-xs"><i class="fa-home"></i></span>
									<span class="hidden-xs">普通讯息</span>
								</a>
							</li>
							<li>
								<a href="#msg_tab_vote" data-toggle="tab">
									<span class="visible-xs"><i class="fa-user"></i></span>
									<span class="hidden-xs">投票</span>
								</a>
							</li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane active" id="msg_tab_basic">			
								<div class="form-group">
									<label class="col-sm-2 control-label form_input_required" for="targetUser" style="padding-left:0px;">收件人</label>
									<div class="col-sm-12">
										<input type="text" class="form-control" id="targetUser" name="targetUser" readonly="readonly" data-validate="required" data-message-required="收件人不能为空">
										<button id="addTargetUser" style="margin:10px 0px;padding:4px;">添加收件人</button>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label form_input_required" for="msgTitle" style="padding-left:0px;">讯息标题</label>
									<div class="col-sm-12">
										<input type="text" class="form-control" id="msgTitle" name="msgTitle" data-validate="required" data-message-required="标题不能为空">
									</div>
								</div>
								<div class="form-group" style="width:800px;padding-top:180px;">
									<label class="control-label form_input_required" for="msgContent">内容</label>
									<div style="margin-left:20px;">
										<textarea id="msgContent" name="msgContent" style="width:800px;height:400px;"></textarea>
									</div>
								</div>
							</div>
							<div class="tab-pane" id="msg_tab_vote">
								<div style="color:#aaa;">该功能暂不支持</div>
							</div>
						</div>
					</div>
					</div>
				</div>
			</div>
			<%@include file="../common/footer.jsp" %>
		</div>
	</div>
	<%@include file="targetUsers.jsp" %>
	<jsp:include page="../common/bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
</body>
</html>