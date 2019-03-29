$(function() {
	$('#problem_description').summernote({
		placeholder: '在此添加问题描述...',
		height : 300, // set editor height
		minHeight : null, // set minimum height of editor
		maxHeight : null, // set maximum height of editor
		focus : false,
		callbacks:{
			//获取焦点后触发
				onFocus: function(e){
					
				
				}
		}
	// set focus to editable area after initializing summernote
	});

	$('#save').bind(
			"click",
			function() {
				/* 得到问题描述html */
				var str_problem_description = $('#problem_description')
						.summernote('code');

				var str_title = $('#title').val();
				var str_problem_input = $('#problem_input').val();
				var str_problem_output = $('#problem_output').val();
				var str_problem_explanation = $('#problem_explanation').val();
				var code_prefix = '';
				var code_suffix = '';
				var status = 0;
				
				if($('#hasPrefix').is(':checked')){
					code_prefix = $('#code_prefix').val();
				}
				if($('#hasSuffix').is(':checked')){
					code_suffix = $('#code_suffix').val();
				}
				if ($("#statusswitch").is(':checked')){
					//问题上线
					status = 1;
				}
				var problemModel = new ProblemModel(str_title,str_problem_input,
						str_problem_output, str_problem_description,str_problem_explanation,code_prefix,code_suffix,status);
				var data = JSON.stringify(problemModel);
				
				// 解决ajax POST被springsecurity误伤
				var header = $("meta[name='_csrf_header']").attr('content');
				var token = $("meta[name='_csrf']").attr('content');

				$.ajax({
					url: "/contest/mgt/addproblem",
					type: "POST",
					contentType: "application/json",
					data: data,
					dataType: "json",
					// 解决ajax POST被springsecurity误伤
					beforeSend : function(xhr) {
						xhr.setRequestHeader(header, token);
					},
					success:function(obj){
						alert(obj.mesg);
						//销毁富文本编辑器
						$('#problem_description').summernote('destroy');
						//删除body内所有元素
						$('body').children().remove();
					}
				
				});	

			});
	
	$('#hasPrefix').change(function(){
		var isChecked = $(this).is(':checked');
		if(isChecked){
			$(this).parent().after('<textarea id="code_prefix" class="form-control"></textarea>');
		}else{
			$('#code_prefix').remove();
		}
	});
	
	$('#hasSuffix').change(function(){
		var isChecked = $(this).is(':checked');
		if(isChecked){
			$(this).parent().after('<textarea id="code_suffix" class="form-control"></textarea>');
		}else{
			$('#code_suffix').remove();
		}
	});

});
function ProblemModel(title,input,output,description,explanation,code_prefix,code_suffix,status) {
	this.title = title;
	this.input = input;
	this.output = output;
	this.description = description;
	this.explanation = explanation;
	this.code_prefix = code_prefix;
	this.code_suffix = code_suffix;
	this.status = status;
}