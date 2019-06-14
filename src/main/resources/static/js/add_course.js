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
					if(obj.code==1)
					alert(obj.mesg);
			}
			
			,
			
			xhr: function(){
				//计算上传进度条
				//myXhr = $.ajaxSettings.xhr();
				var myXhr = new window.XMLHttpRequest();
				
					
					myXhr.upload.addEventListener('progress', function(e){
						if(e.lengthComputable){
							$('#pic_upload_progress').attr('max',e.total);
							$('#pic_upload_progress').attr('value',e.loaded);
						}
					},false);
					//这个返回xhr一定不能少！！！
					return myXhr;
				
			}

		});
	});
})