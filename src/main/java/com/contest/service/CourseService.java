package com.contest.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.contest.common.UUIDGenerator;
import com.contest.exception.ContestCommonException;
import com.contest.mapper.CourseModelMapper;
import com.contest.model.CourseModel;


@Service
public class CourseService {
	
	@Autowired
	private CourseModelMapper courseModelMapper;
	@Value("${softcits.website.resource.path}")
	private String resource_path;
	@Value("${softcits.website.course.pic.folder}")
	private String course_pic_folder;
	
	public List<CourseModel> getAllCourses(){
		return courseModelMapper.selectByExample(null);
	}
	
	public CourseModel getSingleCourseById(Integer id) {
		return courseModelMapper.selectByPrimaryKey(id);
	}
	
	public void addCourse(MultipartFile pic,   String name,  String isCharge) {
		
		String fullName = pic.getOriginalFilename();
		String extName = fullName.substring(fullName.lastIndexOf(".")+1);
		String newName = UUIDGenerator.getUUID32() + '.' + extName;
		
		try {
			byte[] bytes = pic.getBytes();
			Path path = Paths.get(resource_path + course_pic_folder + newName);
			Files.write(path, bytes);
		}catch(IOException e) {
			throw new ContestCommonException("图片上传失败");
		}
		
		CourseModel course = new CourseModel();
		course.setIscharge(isCharge);
		course.setPic(newName);
		course.setName(name);
		course.setStatus("progress");
		courseModelMapper.insertSelective(course);
		
	}

}
