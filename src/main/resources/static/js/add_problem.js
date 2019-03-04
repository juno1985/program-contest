$(function() {
	$('#problem_description').summernote({
		height : 300, // set editor height
		minHeight : null, // set minimum height of editor
		maxHeight : null, // set maximum height of editor
		focus : true
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

				var str_problem_input = $('#problem_input').val();
				var str_problem_output = $('#problem_output').val();

				var problemModel = new ProblemModel(str_problem_input,
						str_problem_output, str_problem_description);
				// console.log(problemModel);
				console.log(JSON.stringify(problemModel));

			})
});
function ProblemModel(input, output, description) {
	this.input = input;
	this.output = output;
	this.description = description;
}