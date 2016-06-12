<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
.viewMsgDiv div{
	margin:10px;
}
span.msgPre{
	color:#aaa;
}
#msgContent{
	border-top:2px solid #aaa;
	padding-top:14px;
}
</style>
<div class="modal fade" id="viewMsgDiv">
	<div class="modal-dialog" style="width:70%;">
		<div class="modal-content" style="min-height: 400px;padding:0px;padding-top:30px;">
			<div class="modal-header">
				<div style="position:absolute;top:0px;left:10px;width: 120px; background: #00a4ef;  color: #fff;margin-left:10px;padding:8px;">
					<span style="margin-left:20px;">物讯内容</span>
				</div>
				<div style="position:absolute;top:0px;right:10px;">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="padding:6px 12px;background:#cc0000;opacity:1;color:white">
						<li class="fa-close"></li>
					</button>
				</div>
			</div>
			<div class="modal-body">
				<div class="content">
					<div class="panel-body viewMsgDiv" style="padding-top:0px;">
						<div id="msgTitle" style="font-weight: bold;"></div>
						<div>
							<span class="msgPre">发件人：</span>
							<span id="msgFrom"></span>
						</div>
						<div>
							<span class="msgPre">收件人：</span>
							<span id="msgTo"></span>
						</div>
						<div>
							<span class="msgPre">时&nbsp;&nbsp;&nbsp;间：</span>
							<span id="msgFromTime"></span>
						</div>
						<div id="msgContent"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>