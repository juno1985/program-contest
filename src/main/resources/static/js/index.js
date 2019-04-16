$(function(){
	var kefuWarpper = $('#whiteKefuWra');
	var wechatWrapper = $('#whiteWechatWra');
	$(kefuWarpper).hide();
	$('.kefu.show').hover(function(){
		$(this).css('background-color', '#007cb8');
		$(kefuWarpper).show();
	},function(){
		$(this).css('background-color', '#d3d3d3');
		$(kefuWarpper).hide();
	});
	
	$(wechatWrapper).hide();
	$('.weixin.show').hover(function(){
		$(this).css('background-color', '#007cb8');
		$(wechatWrapper).show();
	}, function(){
		$(this).css('background-color', '#d3d3d3');
		$(wechatWrapper).hide();
	});
	
	//初始化聊天窗口
	$('#chat-mainsection-messagebox-iscroll').append('<div class="chat_span"><img src="./img/default_kefu.png" class="kefu_chat_icon" /><p>您好,请留下您的电话或微信,稍后会有客服人员与您取得联系</p></div>');
	
	$('#chat_send_button').on('click', function(){
		userSendChat();
	});
	
	function userSendChat(){
		var user_chat_content = $('#nt-ui-send-message-textarea').text();
		$('#chat-mainsection-messagebox-iscroll')
		.append('<div class="chat_span"><p class="user_chat"><span>' 
				+ user_chat_content + '</span></p></div>');
		$('#nt-ui-send-message-textarea').text('');
		$('#chat-mainsection-messagebox-iscroll').append('<div class="chat_span"><img src="./img/default_kefu.png" class="kefu_chat_icon" /><p>您好,请留下您的电话或微信,稍后会有客服人员与您取得联系</p></div>');
		//滚动条始终保持在底部,保证看到最新消息
		var chat_hist = document.getElementById('chat-mainsection-messagebox-iscroll');
		chat_hist.scrollTop = chat_hist.scrollHeight - chat_hist.offsetHeight;
		
	}
	
});