$(function() {
	$.ajax({
		url : "/contest/mgt/listproblem",
		type : "GET",
		contentType : "application/json",
		//		data: data,
		dataType : "json",
		success : function(obj) {

			var array_elment = obj.object;
			var array_attr = [ "id", "title" ];
			var array_thead = [ "序号", "名称" ];

			//删除body内所有元素
			$('#content').children().remove();

			var table = create_table("问题列表", array_thead, array_elment, array_attr);

			$('#content').html(table);
		}

	});
});
