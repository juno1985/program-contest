$(function() {
	$.ajax({
		url : "/contest/listproblem",
		type : "GET",
		contentType : "application/json",
		// data: data,
		dataType : "json",
		success : function(obj) {

			var array_elment = obj.object;
			var array_attr = [ "id", "title" ];
			var array_thead = [ "序号", "名称" ];

			// 删除body内所有元素
			$('#content').children().remove();

			var table = create_table("问题列表", array_thead, array_elment,
					array_attr);

			$('#content').html(table);
		}

	});

	/*
	 * str_caption: caption array_thead: thead array_elment: 对象数组,展示表格的数据源
	 * array_attr: array_elment的属性数组
	 */
	function create_table(str_caption, array_thead, array_elment, array_attr) {

		var table = create_table_1();

		table = create_caption_2(table, str_caption);

		table = create_thead_3(table, array_thead);

		table = create_tbody_4(table, array_elment, array_attr);

		return table;
	}
	function create_table_1() {
		return $("<table class=\"table table-striped\"></table>");
	}
	function create_caption_2(obj, str_caption) {
		$(obj).html("<caption>" + str_caption + "</caption>");
		return obj;
	}
	// array_thead: 各列标题组成的数组
	function create_thead_3(obj, array_thead) {
		var th = "";
		$.each(array_thead, function(index, value) {
			th += "<th>" + value + "</th>";
		});
		var thead = "<thead>" + "<tr>" + th + "</tr>" + "</thead>";
		$(obj).find("caption").after(thead);
		return obj;
	}
	// array_element: 数据对象组成的数组
	// array_attr: 数据对象的属性组成的数组, "id"/"title"/"description"等等
	function create_tbody_4(obj, array_element, array_attr) {
		var td = "";
		// 对象变量
		var element = {};
		for (var i = 0; i < array_element.length; i++) {
			element = array_element[i];
			for (var j = 0; j < array_attr.length; j++) {
				var attr = array_attr[j];
				td += "<td><a href=\"/contest/challenge/" + element["id"]
						+ "/problem\">" + element[attr] + "</a></td>";
			}

			// 生成管理员操作button
			if ($('#isAdmin').val() == 0) {
				td += "<td>" + "<button  style=\"color:#FF0033\" value=\"" + element["id"]
						+ "\" class=\"btn btn-default case_add_btn\" >"
						+ "增加case" + "</button></td>"
			}

			td = "<tr>" + td + "</tr>";
		}
		td = "<tbody>" + td + "</tbody>";
		// console.log(td);
		// console.log($(obj));
		$(obj).find("thead").after(td);

		// 为管理员button绑定事件
		if ($('#isAdmin').val() == 0) {
			var array_add_btn = $(obj).find("button.case_add_btn");
			$.each(array_add_btn, function(index, value) {

				$(value).on("click", function() {

					// 问题id
					var id = $(this).attr("value");

					$('#case_add_modal').modal('show');
					$('#case_add_modal').find("input#problem_id").val(id);

				});
			});
		}
		return obj;
	}
	
});

function case_add_submit() {
	// console.log(">>"+$("#problem_id").val());
	var fid = $('#case_add_modal').find("input#problem_id").val();
	var input = $('#case_add_modal').find("textarea#case_input").val();
	var output = $('#case_add_modal').find("textarea#case_output").val();
	var caseModel = {
			'fid' : fid,
			'input' : input,
			'output' : output
	}
	//console.log(caseModel);
	var data = JSON.stringify(caseModel);
	//解决ajax POST被springsecurity误伤
	var header = $("meta[name='_csrf_header']").attr('content');
	var token = $("meta[name='_csrf']").attr('content');
	
	$.ajax({
		url : "/contest/mgt/addcase",
		type : "POST",
		contentType : "application/json",
		data : data,
		dataType : "json",
		//解决ajax POST被springsecurity误伤
		beforeSend : function(xhr){
			xhr.setRequestHeader(header, token);
		},
			
		success : function(obj) {

			if (obj.code == 0) {
				//关闭模态框
				$('#case_add_modal').modal('hide');
				//清空输入框
				$('#case_add_modal').find("textarea#case_input").val("");
				$('#case_add_modal').find("textarea#case_output").val("");
			}
		}

	});
}
