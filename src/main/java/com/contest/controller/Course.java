package com.contest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.contest.model.CourseModel;
import com.contest.service.CourseService;

@Controller
public class Course {
	
	@Autowired
	private CourseService courseService;
	
	@RequestMapping(value="/course", method = { RequestMethod.GET })
	public String CoursePage(Model model) {
		
		List<CourseModel> courses = courseService.getAllCourses();
		
		model.addAttribute("courses", courses);
		
		return "course";
	}

}
