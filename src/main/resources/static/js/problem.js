$(function() {

	var code_area_obj = document.getElementById('code_textarea');

	code_area_obj.onkeyup = function() {
		this.style.height = 'auto';

		//使textarea自适应高度
		//console.log(this.scrollHeight);
		this.style.height = this.scrollHeight + "px";
	};

	$('#code_submit_btn').on('click', function() {
		//得到页面的代码内容
		var code_input = $(code_area_obj).val();

		var pro_id = $('#problem_id').val();
		
		//解决ajax POST被springsecurity误伤
		var header = $("meta[name='_csrf_header']").attr('content');
		var token = $("meta[name='_csrf']").attr('content');
		
		$.ajax({
			
				url: "/contest/challeng/" + pro_id + "/submit",
				type: "POST",
				contentType: "application/x-www-form-urlencoded",
				//向后台传对象
				data: {"codeInput" : code_input},
				dataType: "json",
				//解决ajax POST被springsecurity误伤
				beforeSend : function(xhr){
					xhr.setRequestHeader(header, token);
				},
				success:function(obj){
					if(obj.code == 1)
						{
							$('#code_compile_result')
								.css("background-color","#F0FFF0")
								.css("margin-top","50px")
								.html("<h3 style='color:red'>编译错误</h3>")
								.append(obj.mesg);
						}
					
				}
			
			});	
	});
});