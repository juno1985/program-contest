$(function() {

	// 解决ajax POST被springsecurity误伤
	var header = $("meta[name='_csrf_header']").attr('content');
	var token = $("meta[name='_csrf']").attr('content');
	// 默认展现所有已解决未解决问题
	var sum = 3;
	initPage(sum);
	// 绑定监听
	$('.solve_problem_state').change(function() {
		var isChecked = $(this).is(':checked');
		if (isChecked) {
			sum += parseInt($(this).val());
		} else {
			sum -= parseInt($(this).val());
		}
		initPage(sum);
	});

	function initPage(sum) {
		$.ajax({
			url : "/contest/listproblem",
			type : "GET",
			contentType : "application/json",
			data : {
				'sum' : sum
			},
			dataType : "json",
			success : function(obj) {

				var array_elment = obj.object;
				var array_attr = [ "id", "title" ];
				var array_thead = [ "序号", "名称" ];

				// 删除body内所有元素
				$('#content').children().remove();

				if (array_elment == null || array_elment == 'undefined') {
					$('#content').html('<strong>暂无内容</strong>');
					return false;
				}

				var table = create_table("问题列表", array_thead, array_elment,
						array_attr);

				$('#content').html(table);
			}

		});
	}

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

				td += "<td>"
						+ generateSwitchHTML(element['id'], element['status'])
						+ "</td>";

				td += "<td>" + "<button  style=\"color:#FF0033\" value=\""
						+ element["id"]
						+ "\" class=\"btn btn-default case_add_btn\" >"
						+ "增加case" + "</button></td>";
			}

			td = "<tr>" + td + "</tr>";
		}
		td = "<tbody>" + td + "</tbody>";
		$(obj).find("thead").after(td);

		// 为管理员button绑定事件
		if ($('#isAdmin').val() == 0) {
			var array_add_btn = $(obj).find("button.case_add_btn");
			$.each(array_add_btn, function(index, value) {

				$(value).on(
						"click",
						function() {

							// 问题id
							var id = $(this).attr("value");
							$('#case_add_modal').find('#myModalLabel').text(
									"#" + id + ": 增加CASE");
							$('#case_add_modal').modal('show');
							$('#case_add_modal').find("input#problem_id").val(
									id);

						});
			});

			var array_status_switch = $(obj).find('div.switch');
			$.each(array_status_switch, function(index, element) {
				$(element).on('click', function() {
					// 问题id
					var id = $(this).attr("value");

					var ele = $(this).find("div.move");
					if (ele.attr("data-state") == "on") {
						problemStatusChange(id, 0, $(this));

					} else if (ele.attr("data-state") == "off") {
						problemStatusChange(id, 1, $(this));
					}

				});
			});

		}
		return obj;
	}
	// 初始页面加载
	function generateSwitchHTML(id, status) {
		var switch_html = '';
		// 下线状态
		if (status == 0) {
			switch_html += '<div class="switch" value="' + id
					+ '"><div class="btn_fath clearfix off">';
			switch_html += '<div class="move" data-state="off"></div><div class="btnSwitch btn1">上线</div><div class="btnSwitch btn2 ">下线</div></div></div>';
		}// 上线状态
		else {
			switch_html += '<div class="switch" value="' + id
					+ '"><div class="btn_fath clearfix on">';
			switch_html += '<div class="move" data-state="on" style="left : 60px;"></div><div class="btnSwitch btn1">上线</div><div class="btnSwitch btn2 ">下线</div></div></div>';
		}
		return switch_html;
	}

	function problemStatusChange(problemId, statusId, obj) {

		$.ajax({
			url : "/contest/mgt/" + problemId + "/status",
			type : "POST",
			contentType : "application/x-www-form-urlencoded",
			data : {
				'statusId' : statusId
			},
			dataType : "json",
			// 解决ajax POST被springsecurity误伤
			beforeSend : function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success : function(result) {
				if (result.code == 0) {
					if (statusId == 0) {
						switchAnimate('off', obj);
					} else {
						switchAnimate('on', obj);
					}
				}
			}

		});
	}

	function switchAnimate(action, obj) {
		var move_ele = $(obj).find("div.move");
		var btn_fath_ele = $(obj).find('.btn_fath');
		if (action == 'off') {
			$(move_ele).animate({
				left : "0"
			}, 300, function() {
				// 使问题下线
				$(move_ele).attr("data-state", "off");
			});
			$(btn_fath_ele).removeClass("on").addClass("off");
		} else {
			$(move_ele).animate({
				left : '60px'
			}, 300, function() {
				// 使问题上线
				$(move_ele).attr("data-state", "on");
			});
			// 使问题上线

			$(btn_fath_ele).removeClass("off").addClass("on");
		}

	}
	
	function clearPage() {
		// 删除body内所有元素
		$('#content').children().remove();
		$('#sidebar').children().remove();
	}
	
	$('#course_add_btn').on('click', function(){
		clearPage();
		$.get("/contest/page/add_course.html", function(html_cont){
			$('#content').html(html_cont);
		});
	});
	
	
	$('#course_list_btn').on('click', function(){
		clearPage();
		
		$.ajax({
			url : "/contest/mgt/courses",
			type : "GET",
			success : function(obj) {
				
				var tableHtml = "<table class=\"table table-striped\">";
				
				$.each(obj, function(index, value){
					tableHtml += "<tr><td>" + value.id +"</td><td>" + value.name + "</td><td><a href=\"#\" id=\"delete_course\">" + '删除' + "</a></td><td><a href=\"#\" class=\"add_intro\" value=\""+ value.id +"\">" +'编辑介绍' +
						"</a></td><td>" + '上传视频' + "</td></tr>";
					
				});
				
				tableHtml += "</table>";
				
				$('#content').html(tableHtml);
				
				$('.add_intro').on('click', function(){
					$('#course_intro_add_model').modal('show');
					$('#course_intro_add_model').find("input#course_id").val(
							$(this).attr('value'));
				});
			
			}

		});
		
	});
	
	
	
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
	// console.log(caseModel);
	var data = JSON.stringify(caseModel);
	// 解决ajax POST被springsecurity误伤
	var header = $("meta[name='_csrf_header']").attr('content');
	var token = $("meta[name='_csrf']").attr('content');

	$.ajax({
		url : "/contest/mgt/addcase",
		type : "POST",
		contentType : "application/json",
		data : data,
		dataType : "json",
		// 解决ajax POST被springsecurity误伤
		beforeSend : function(xhr) {
			xhr.setRequestHeader(header, token);
		},

		success : function(obj) {

			if (obj.code == 0) {
				// 关闭模态框
				$('#case_add_modal').modal('hide');
				// 清空输入框
				$('#case_add_modal').find("textarea#case_input").val("");
				$('#case_add_modal').find("textarea#case_output").val("");
			}
		}

	});
}
function course_intro_add_submit(){
	var cid = $('#course_intro_add_model').find("input#course_id").val();
	var infor = $('#course_intro_add_model').find("textarea#course_intro_input").val();
	var course_modal = {
			'cid' : cid,
			'infor' : infor
	}
	// console.log(caseModel);
	var data = JSON.stringify(course_modal);
	// 解决ajax POST被springsecurity误伤
	var header = $("meta[name='_csrf_header']").attr('content');
	var token = $("meta[name='_csrf']").attr('content');
	
	$.ajax({
		url : "/contest/mgt/updateCourseIntro",
		type : "POST",
		contentType : "application/json",
		data : data,
		dataType : "json",
		// 解决ajax POST被springsecurity误伤
		beforeSend : function(xhr) {
			xhr.setRequestHeader(header, token);
		},

		success : function(obj) {

			if (obj.code == 0) {
				// 关闭模态框
				$('#course_intro_add_model').modal('hide');
				
			}
		}

	});

}
