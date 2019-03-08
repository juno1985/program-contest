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
			var array_thead = [ "序号", "名称"];

			//删除body内所有元素
			$('#content').children().remove();

			var table = create_table("问题列表", array_thead, array_elment,
					array_attr);

			$('#content').html(table);
		}

	});
	
	/*
	 * str_caption: caption
	 * array_thead: thead
	 * array_elment: 对象数组,展示表格的数据源
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
	//array_thead: 各列标题组成的数组
	function create_thead_3(obj, array_thead) {
		var th = "";
		$.each(array_thead, function(index, value) {
			th += "<th>" + value + "</th>";
		});
		var thead = "<thead>" + "<tr>" + th + "</tr>" + "</thead>";
		$(obj).find("caption").after(thead);
		return obj;
	}
	//array_element: 数据对象组成的数组
	//array_attr: 数据对象的属性组成的数组, "id"/"title"/"description"等等
	function create_tbody_4(obj, array_element, array_attr) {
		var td = "";
		//对象变量
		var element = {};
		for (var i = 0; i < array_element.length; i++) {
			element = array_element[i];
			for (var j = 0; j < array_attr.length; j++) {
				var attr = array_attr[j];
				td += "<td><a href=\"/contest/challenge/" + element["id"] + "/problem\">" + element[attr] + "</a></td>";

			}
			td = "<tr>" + td + "</tr>";
		}
		td = "<tbody>" + td + "</tbody>";
		//console.log(td);
		//	console.log($(obj));
		$(obj).find("thead").after(td);
		return obj;
	}
	
});
