$(function() {

	var form_validate_metadata = {
		'username' : {
			type : 'input',
			// 是否必填
			required : true,
			minLength : 4,
			// 只能是数字字母下划线
			noSepcialChar : true
		},
		'password' : {
			type : 'input',
			required : true,
			minLength : 6,
			noSepcialChar : true
		},
		'password_rep' : {
			type : 'input',
			// 被比较的dom id
			validwith : 'password',
			required : true,
		},
		'email' : {
			type : 'input',
			spec : 'email',
			required : true,
		},
		'phone' : {
			type : 'input',
			spec : 'phone',
			required : false
		}
	}
	
	function jumpPage(link){
		window.location.href=link;
	}

	//提交表单
	function regist_form_submit() {

		var username = $('#regist_form').find('#username').val();
		var password = $('#regist_form').find('#password').val();
		var email = $('#regist_form').find('#email').val();
		var phone = $('#regist_form').find('#phone').val();
		var user = {
				'username': username,
				'password': password,
				'email': email,
				'phone': phone
		}
		var user_str = JSON.stringify(user);
		
		$.ajax({
			
			url: "/contest/user/regist",
			type: "POST",
			contentType: "application/json",
			//向后台传对象
			data: user_str,
			dataType: "json",
			success:function(obj){
				
				alert(obj.mesg);
				
				//注册成功才跳转到登录页面
				if(obj.code==0){
					
					setTimeout(jumpPage("/contest/login"),3000);
				}
			}
		
		});	

	}

	$('#login_btn').bind(
			'click',
			function() {

				// 验证表单数据
				var isValid = contest_online_form_validation($('#regist_form'),
						form_validate_metadata);

				if (isValid) {
					// 提交表单
					regist_form_submit();
				
				}

			});

});

function contest_online_form_validation(form_obj, validate_metadata) {
	
	var flag = true;

	/**
	 * 
	 * @param attr_name
	 *            form中元素的class识别名
	 * @param attr_value
	 *            属性对象
	 * @returns
	 */
	$.each(validate_metadata,function(attr_name, attr_value) {
						// 得到form内的input等html dom
						var form_element = document.getElementById(attr_name);

						switch (attr_value.type) {
						case 'input':

							if (attr_value.required
									&& $(form_element).val().trim() == '') {
								// 不加show()就只能fadeOut一次
								$(form_element).next('span.err').text('不能为空')
										.show().fadeOut(2000);
								flag = false;
								//这里真的需要注意 ！！！！
								//return 就只是跳出switch
								//return false则是能直接跳出外部的each
								return false;
							}
							if (attr_value.noSepcialChar) {
								var reg = /[A-Za-z0-9_]/g;

								if (!reg.test($(form_element).val())) {
									$(form_element).next('span.err').text(
											'只能为字母数字或下划线').show().fadeOut(2000);
									flag = false;
									return false;

								}

							}
							if (attr_value.hasOwnProperty('minLength')
									&& $(form_element).val().length < attr_value.minLength) {
								$(form_element).next('span.err').text(
										'最小长度为:' + attr_value.minLength).show()
										.fadeOut(2000);
								flag = false;
								return false;
							}
							if (attr_value.hasOwnProperty('validwith')
									&& $.trim(attr_value.validwith) != '') {
								var toCompare_element = document
										.getElementById(attr_value.validwith);

								if ($(toCompare_element).val() != $(
										form_element).val()) {
									$(form_element).next('span.err').text(
											'两次输入不匹配').show().fadeOut(2000);
									flag = false;
									return false;
								}
							}
							if (attr_value.hasOwnProperty('spec')) {
								var spec_val = attr_value.spec;
								if ('email' == spec_val) {
									// 验证邮箱
									var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
									if (!reg.test($(form_element).val())) {
										$(form_element).next('span.err').text(
												'邮箱格式不正确').show().fadeOut(2000);
										flag = false;
										return false;
									}
								} else if ('phone' == spec_val
										&& $(form_element).val().trim() != '') {
									// 验证手机号
									var reg = /^1[34578]\d{9}$/;
									if (!reg.test($(form_element).val())) {
										$(form_element).next('span.err').text(
												'手机号不正确').show().fadeOut(2000);
										flag = false;
										return false;
									}
								}

							}
							break;

						}

					});
	
	return flag;

}