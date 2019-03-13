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
		
		$.ajax({
			
				url: "/contest/challeng/" + pro_id + "/submit",
				type: "POST",
				contentType: "application/x-www-form-urlencoded",
				//向后台传对象
				data: {"codeInput" : code_input},
				dataType: "json",
				success:function(obj){
					alert(obj.mesg);
					
				}
			
			});	
	});
});