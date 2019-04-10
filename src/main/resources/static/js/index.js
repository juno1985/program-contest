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
});