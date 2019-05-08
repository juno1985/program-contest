package com.contest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contest.mapper.CourseModelMapper;
import com.contest.model.CourseModel;


@Service
public class CourseService {
	
	@Autowired
	private CourseModelMapper courseModelMapper;
	
	public List<CourseModel> getAllCourses(){
		return courseModelMapper.selectByExample(null);
	}

}
