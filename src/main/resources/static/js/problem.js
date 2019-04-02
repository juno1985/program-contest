$(function() {

	var code_area_obj = document.getElementById('code_textarea');

	code_area_obj.onkeyup = function() {
		this.style.height = 'auto';

		// 使textarea自适应高度
		// console.log(this.scrollHeight);
		this.style.height = this.scrollHeight + "px";
	};

	$('#code_submit_btn').on(
			'click',
			function() {
				// 提交按钮禁用
				$('#code_submit_btn').attr('disabled', true);

				$('#code_submit_hint').css('display', 'block');

				// 得到页面的代码内容
				var code_input = $(code_area_obj).val();

				var pro_id = $('#problem_id').val();

				// 解决ajax POST被springsecurity误伤
				var header = $("meta[name='_csrf_header']").attr('content');
				var token = $("meta[name='_csrf']").attr('content');

				$.ajax({

					url : "/contest/challeng/" + pro_id + "/submit",
					type : "POST",
					contentType : "application/x-www-form-urlencoded",
					// 向后台传对象
					data : {
						"codeInput" : code_input
					},
					dataType : "json",
					// 解决ajax POST被springsecurity误伤
					beforeSend : function(xhr) {
						xhr.setRequestHeader(header, token);
					},
					success : function(obj) {

						// 恢复提交按钮
						$('#code_submit_btn').attr('disabled', false);
						$('#code_submit_hint').css('display', 'none');

						// console.log(obj);
						// compile error
						if (obj.code == 1) {
							$('#code_compile_result').css("background-color",
									"#F0FFF0").css("margin-top", "50px").html(
									"<h3 style='color:red'>编译错误</h3>").append(
									obj.mesg);
						}
						// problem failed
						else if (obj.code == 2) {
							var case_result = "";
							$.each(obj.object, function(index, value) {
								// 0 - success
								// 1 - failed
								// 2 - timeout
								if (value.resultCode == 1) {
									case_result += "CASE" + (index + 1) + ":失败"
											+ "<br>";
								} else if (value.resultCode == 2) {
									case_result += "CASE" + (index + 1) + ":超时"
											+ "<br>";
								} else {
									case_result += "CASE" + (index + 1) + ":成功"
											+ "<br>";
								}
							});

							$('#code_compile_result').css("background-color",
									"#F0FFF0").css("margin-top", "50px").html(
									"<h3 style='color:red'>失败</h3>").append(
									case_result);
						}
						// sucess
						else {
							$('#code_compile_result').css("background-color",
									"#F0FFF0").css("margin-top", "50px").html(
									"<h3 style='color:green'>成功</h3>");
						}

					}

				});
			});

	$('#href_hist')
			.on(
					'click',
					function() {
						var pro_id = $('#problem_id').val();
						$
								.ajax({

									url : "/contest/challenge/" + pro_id
											+ "/hist",
									type : "GET",
									contentType : "application/json",
									dataType : "json",
									success : function(obj) {
										if (obj.code != 0) {
											$('#hist').html(
													"<strong>无记录</strong>");
										} else {
											var array_elment = obj.object;
											var array_attr = [ "submitTime",
													"result" ];
											var array_thead = [ "提交时间", "结果" ];

											// 删除body内所有元素
											$('#hist').children().remove();

											var table = create_table("我的历史",
													array_thead, array_elment,
													array_attr);

											$('#hist').html(table);

											$('#hist').find('.result-btn').on(
															'click',
															function() {
																var code_id = $(this).val();
																// 获取历史代码
																$.ajax({url : "/contest/challenge/"
																					+ code_id
																					+ "/code",
																		type : "GET",
																		dataType : "json",
																		success : function(obj) {
																				var code_html = obj.object;
																				$('#hist').find('#code_div').remove();
																				$('#hist').append('<div id="code_div" style="border:2px solid #939393;background-color: #f9f9f9;padding: 10px 10px;">'
																										+ code_html
																										+ '</div>');
																			}
																		});
															});
										}
									}

								});
					});

	$('#href_range')
			.on(
					'click',
					function() {
						var pro_id = $('#problem_id').val();
						$
								.ajax({
									url : "/contest/challenge/" + pro_id
											+ "/allhist",
									type : "GET",
									contentType : "application/json",
									dataType : "json",
									success : function(obj) {
										$('#range').find('table').remove();
										if (obj.code != 0) {
											$('#range').html(
													"<strong>无记录</strong>");
										} else {
											var element_array = obj.object;
											var rang_table_html = "";
											$.each(element_array, function(
													index, value) {
												rang_table_html += '<tr>'
														+ '<td>'
														+ (index + 1)
														+ '</td>'
														+ '<td>'
														+ value['username']
														+ '</td>'
														+ '<td>'
														+ value['submitTime']
																.substring(0,
																		19)
																.replace("T",
																		" ")
														+ '</td>' + '</tr>';
											});
											rang_table_html = '<table class="table table-striped"><caption>解決过该问题的学员</caption>'
													+ rang_table_html
													+ '</table>';

											$('#range').append(rang_table_html);
										}
									
									}
								});
					});
	$('#href_all_hist')
			.on(
					'click',
					function() {
						$('#all_hist').empty();
						var pro_id = $('#problem_id').val();
						$
								.ajax({
									url : "/contest/challenge/" + pro_id
											+ "/allsubmit",
									type : "GET",
									dataType : "json",
									success : function(obj) {
										$('#all_hist').find('table').remove();
										if (obj.code != 0) {
											$('#all_hist').html(
													"<strong>无记录</strong>");
										} else {
											var element_array = obj.object;
											var rang_table_html = "";
											$.each(element_array, function(
													index, value) {
												rang_table_html += '<tr>'
														+ '<td>'
														+ (index + 1)
														+ '</td>'
														+ '<td>'
														+ value['username']
														+ '</td>'
														+ '<td>'
														+ value['submitTime']
																.substring(0,
																		19)
																.replace("T",
																		" ")
														+ '</td>' ;
												var cell_data = '';
												if (value['result'] == "success") {
													cell_data = "成功";
													cell_data = '<button type="button" class="btn btn-success result-btn" value="'
															+ value["codeId"]
															+ '">'
															+ cell_data
															+ '</button>';
												} else {
													cell_data = "失败";
													cell_data = '<button type="button" class="btn btn-danger result-btn" value="'
															+ value["codeId"]
															+ '">'
															+ cell_data
															+ '</button>';
												}
												rang_table_html = rang_table_html + '<td>' + cell_data + '</td></tr>';
											});
											rang_table_html = '<table class="table table-striped"><caption>所有学员在该问题的提交历史</caption>'
													+ rang_table_html
													+ '</table>';
											$('#all_hist').append(
													rang_table_html);
										}
										$('#all_hist').find('.result-btn').on(
												'click',
												function() {
													var code_id = $(this).val();
													// 获取历史代码
													$.ajax({url : "/contest/challenge/"
																		+ code_id
																		+ "/code",
															type : "GET",
															dataType : "json",
															success : function(obj) {
																	var code_html = obj.object;
																	$('#all_hist').find('#code_div').remove();
																	$('#all_hist').append('<div id="code_div" style="border:2px solid #939393;background-color: #f9f9f9;padding: 10px 10px;">'
																							+ code_html
																							+ '</div>');
																}
															});
												});
									}
								});
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
		var cell_data = "";
		for (var i = 0; i < array_element.length; i++) {
			element = array_element[i];
			for (var j = 0; j < array_attr.length; j++) {
				var attr = array_attr[j];
				if (attr == "submitTime") {
					cell_data = element[attr].substring(0, 19)
							.replace("T", " ");
				} else if (attr == "result") {
					if (element[attr] == "success") {
						cell_data = "成功";
						cell_data = '<button type="button" class="btn btn-success result-btn" value="'
								+ element["id"]
								+ '">'
								+ cell_data
								+ '</button>';
					} else {
						cell_data = "失败";
						cell_data = '<button type="button" class="btn btn-danger result-btn" value="'
								+ element["id"]
								+ '">'
								+ cell_data
								+ '</button>';
					}
				}
				td += "<td>" + cell_data + "</td>";

			}
			td = "<tr>" + td + "</tr>";
		}
		td = "<tbody>" + td + "</tbody>";
		// console.log(td);
		// console.log($(obj));
		$(obj).find("thead").after(td);
		return obj;
	}
});