$(function(){
	
	// 解决ajax POST被springsecurity误伤
	var header = $("meta[name='_csrf_header']").attr('content');
	var token = $("meta[name='_csrf']").attr('content');
	
	//jquery的delegate方法,获取未来HTML元素还可以绑定事件
	$('#content').delegate('#add_course_btn','click',function(){
		
		
		//要上传的图片
		var pic_primary = document.getElementById("pic_primary").files[0];
		var formData = new FormData();
		formData.append("pic", pic_primary);
		formData.append("name", $('#content').find('#pic_name').val());
		var isCharge = $("input[name='isCharge']:checked").val();
		formData.append('isCharge', isCharge);
		
		console.log(formData.get('pic'));
		console.log(pic_primary);
		
		$.ajax({
			url : "/contest/mgt/addcourse",
			type : "POST",
			//tell jquery not to process data
			processData: false,
			//tell jquery not to send contentType
			contentType : false,
			data : formData,
			// 解决ajax POST被springsecurity误伤
			beforeSend : function(xhr) {
				xhr.setRequestHeader(header, token);
			},
 
			success : function(obj) {

				
			}

		});
	});
})