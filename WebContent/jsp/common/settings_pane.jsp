<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="settings-pane">
	<a href="#" data-toggle="settings-pane" data-animate="true">
		&times;
	</a>
	<div class="settings-pane-inner">
		<div class="row">
			<div class="col-md-4">
				<div class="user-info">
					<div class="user-details">
						<h3>
							<a href="#" onclick="javascript:void(0)">admin</a>
							<!-- Available statuses: is-online, is-idle, is-busy and is-offline -->
							<span class="user-status is-online"></span>
						</h3>
						<p class="user-title">测试管理员</p>
						<div class="user-links">
							<a href="<%=request.getParameter("basePath")%>editprofile?userId=1" class="btn btn-primary">资料编辑</a>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-8 link-blocks-env">
				<div class="links-block left-sep">
					<h4>
						<a href="#">
							<span>帮助</span>
						</a>
					</h4>
					<ul class="list-unstyled">
						<li>
							<a href="#">
								<i class="fa-angle-right"></i>咨询
							</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>