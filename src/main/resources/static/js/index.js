$(function(){
	
//	$('#nt-ui-chat-wrapper').draggable();
	
	var kefuWarpper = $('#whiteKefuWra');
	var wechatWrapper = $('#whiteWechatWra');
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
	//绑定发送按钮事件
	$('#chat_send_button').on('click', function(){
		userSendChat();
	});
	//绑定最小化对话框事件
	$('#chat-header-close-icon').on('click', function(){
		$('#nt-ui-chat-wrapper').hide("drop", {direction:"down"},"slow",function(){
			$('#nt-ui-minimum-wrapper').css('display','block');
			//$('#nt-ui-minimum-wrapper').show();
			});
		});
	
	$('#chat-mainsection-bottom-closeButton').on('click', function(){
		$('#nt-ui-chat-wrapper').hide("drop", {direction:"down"},"slow",function(){
			$('#nt-ui-minimum-wrapper').css('display','block');
			//$('#nt-ui-minimum-wrapper').show();
			});
		});
	
	//绑定最大化对话框
	$('#nt-ui-minimum-wrapper').on('click', function(){
		$('#nt-ui-chat-wrapper').show("drop", {direction:"down"},"slow",function(){
			$('#nt-ui-minimum-wrapper').hide();
			//$('#nt-ui-minimum-wrapper').show();
			});
	});
	
	//绑定聊天回车发送事件
	$('#nt-ui-send-message-textarea').keypress(function(e){
		var keyCode = e.keyCode ? e.keyCode : e.which?e.which:e.charCode;
		if(keyCode == 13){
			userSendChat();
			return false;
		}
	});
	function userSendChat(){
		var user_chat_content = $('#nt-ui-send-message-textarea').text();
		if('' == $.trim(user_chat_content)) return false;
		$('#chat-mainsection-messagebox-iscroll')
		.append('<div class="chat_span"><p class="user_chat"><span>' 
				+ user_chat_content + '</span></p></div>');
		alwaysScrollToBottom();
		$('#nt-ui-send-message-textarea').text('');
//		sleep(1000);
		kefuAutoReply('');
		
	}
	function kefuAutoReply(str){
		
		var new_reply = $('<div class="chat_span"><img src="./img/default_kefu.png" class="kefu_chat_icon" /><p>您好,请留下您的电话或微信,稍后会有客服人员与您取得联系</p></div>').css('opacity',0);
		
		$('#chat-mainsection-messagebox-iscroll')
			.append(new_reply);
		new_reply.animate({'opacity':1},1500,function(){
			alwaysScrollToBottom();
		});
	
	}
	function alwaysScrollToBottom(){
		//滚动条始终保持在底部,保证看到最新消息
		var chat_hist = document.getElementById('chat-mainsection-messagebox-iscroll');
		chat_hist.scrollTop = chat_hist.scrollHeight - chat_hist.offsetHeight;
	}
	//停顿毫秒
	function sleep(millSec){
		var start = new Date().getTime();
		while(true){
			if(new Date().getTime() - start > millSec)break;
		}
	}
	
});