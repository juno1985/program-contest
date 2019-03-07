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
				/* 得到问题输入和输出 */
				// var array_problem_input_output = $('.problem_input_output');
				/*
				 * $.each(array_problem_input_output,function(index, value){
				 * console.log(index + "--->" + $(value).summernote('code'));
				 * });
				 */

				// console.log(str_problem_description);
				// $('#problem_description').summernote('destroy');
				/*
				 * var str_problem_input =
				 * $('#problem_input').summernote('code'); var
				 * str_problem_outputput
				 * =$('#problem_output').summernote('code');
				 */

				var str_title = $('#title').val();
				var str_problem_input = $('#problem_input').val();
				var str_problem_output = $('#problem_output').val();
				var str_problem_explanation = $('#problem_explanation').val();

				var problemModel = new ProblemModel(str_title,str_problem_input,
						str_problem_output, str_problem_description,str_problem_explanation);
				// console.log(problemModel);
				//console.log(JSON.stringify(problemModel));
				
				var data = JSON.stringify(problemModel);
				
				$.ajax({
					
				//	url: "http://localhost:8080/contest/mgt/addproblem",
					url: "/contest/mgt/addproblem",
					type: "POST",
					contentType: "application/json",
					data: data,
					dataType: "json",
					success:function(obj){
						alert(obj.mesg);
						//销毁富文本编辑器
						$('#problem_description').summernote('destroy');
						//删除body内所有元素
						$('body').children().remove();
					}
				
				});	

			})
});
function ProblemModel(title, input, output, description, explanation) {
	this.title = title;
	this.input = input;
	this.output = output;
	this.description = description;
	this.explanation = explanation;
}