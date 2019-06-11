package com.contest.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.contest.exception.ContestCommonException;
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
		if(pic.isEmpty()) {
			ajaxPojo.setCode(1);
			ajaxPojo.setMesg("请上传封面图片");
			return ajaxPojo;
		}
		
		//以下文件上传应该封装起来
		try {
			byte[] bytes = pic.getBytes();
			Path path = Paths.get("/" + pic.getOriginalFilename());
			Files.write(path, bytes);
		}catch(IOException e) {
			throw new ContestCommonException("图片上传失败");
		}
		
		ajaxPojo.setCode(0);
		ajaxPojo.setMesg("创建成功");
		return ajaxPojo;
		
	}
}
