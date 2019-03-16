package com.contest.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.contest.exception.UserAlreadyExistsException;
import com.contest.pojo.AjaxPojo;

@ControllerAdvice
public class ContestExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(ContestExceptionHandler.class);

	@ResponseBody
	@ExceptionHandler(value = UserAlreadyExistsException.class)
	public AjaxPojo UserAlreadyExistExceptionHandler(Exception ex) {

		logger.error("捕获到Exception异常", ex);

		AjaxPojo ajaxPojo = new AjaxPojo();
		ajaxPojo.setCode(1);
		ajaxPojo.setMesg(ex.getMessage());

		return ajaxPojo;
	}
	
	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public AjaxPojo GlobalExceptionHandler(Exception ex) {

		logger.error("捕获到Exception异常", ex);

		AjaxPojo ajaxPojo = new AjaxPojo();
		ajaxPojo.setCode(1);
		ajaxPojo.setMesg(ex.getMessage());

		return ajaxPojo;
	}

}
