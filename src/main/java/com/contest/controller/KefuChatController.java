package com.contest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.contest.pojo.AjaxPojo;
import com.contest.service.KefuChatService;

@Controller
public class KefuChatController {
	
	@Autowired
	private KefuChatService kefuChatService;
	
	@RequestMapping(value = "/com/addChatCont", method = { RequestMethod.POST })
	@ResponseBody
	public AjaxPojo addChatContent(@RequestParam(required = true) String cont, HttpServletRequest request) {

		String session_id = request.getSession().getId();
		
		kefuChatService.addChatContent(cont, session_id);
		
		return new AjaxPojo(0, "success");
	}

}
