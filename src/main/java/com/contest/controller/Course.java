package com.contest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.contest.model.CourseInforModel;
import com.contest.model.CourseModel;
import com.contest.model.CourseVideoModel;
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
	
	@RequestMapping(value="/course", method = { RequestMethod.GET }, params = "cid")
	public String SingleCoursePage(@RequestParam(required = true) String cid, Model model) {
		
		Integer course_id = Integer.parseInt(cid);
		
		CourseModel course = courseService.getSingleCourseById(course_id);
		model.addAttribute("course", course);
		
		CourseInforModel courseInfor = courseService.getSingleCourseInfor(Integer.parseInt(cid));
		model.addAttribute("courseInfor", courseInfor);
		
		List<CourseVideoModel> courseVideos = courseService.getCourseVideosByCourseId(course_id);
		model.addAttribute("courseVideos", courseVideos);
		
		return "single_course";
	}

}
