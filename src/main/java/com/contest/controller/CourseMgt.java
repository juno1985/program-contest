package com.contest.controller;


import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.contest.pojo.AjaxPojo;
import com.contest.service.CourseService;

@Controller
public class CourseMgt {
	@Autowired
	private CourseService courseService;
	
	@RequestMapping(value="/mgt/addcourse", method = { RequestMethod.POST})
	@ResponseBody
	public AjaxPojo addCourse(@RequestParam("pic") MultipartFile pic,  @RequestParam("name") String name, 
			@RequestParam("isCharge") String isCharge) {
		AjaxPojo ajaxPojo = new AjaxPojo();
		
		if(StringUtils.isEmpty(name)) {
			ajaxPojo.setCode(1);
			ajaxPojo.setMesg("课程名不能为空");
			return ajaxPojo;
		}
		
		
		if(pic.isEmpty()) {
			ajaxPojo.setCode(1);
			ajaxPojo.setMesg("请上传封面图片");
			return ajaxPojo;
		}
		
		//检查是否为图片
		try {
			BufferedImage bi = ImageIO.read(pic.getInputStream());
			if(bi == null) {
				ajaxPojo.setCode(1);
				ajaxPojo.setMesg("图片格式错误");
				return ajaxPojo;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		courseService.addCourse(pic, name, isCharge);
		
		ajaxPojo.setCode(0);
		ajaxPojo.setMesg("创建成功");
		return ajaxPojo;
		
	}
}
