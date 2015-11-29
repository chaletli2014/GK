jQuery(document).ready(function($){
	initMessage();
});

function initMessage(){
	jQuery.ajax({
		url: basePath+"getUnReadMessage"
		,success: function(response){
			var messageCount = response.messageSize;
			$("#newMessageList").append("");
			if(messageCount > 0){
				$("#messageCount").html("您当前有<strong>"+messageCount+"</strong>条新消息.");
				$("#messageTips").html(messageCount);
			}else{
				$("#messageCount").html("当前没有新消息");
			}
		}
	});
}