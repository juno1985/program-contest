package com.contest.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.contest.common.UUIDGenerator;
import com.contest.exception.ContestCommonException;
import com.contest.mapper.CourseInforModelMapper;
import com.contest.mapper.CourseModelMapper;
import com.contest.model.CourseInforModel;
import com.contest.model.CourseInforModelExample;
import com.contest.model.CourseModel;
import com.contest.pojo.CourseInfor;


@Service
public class CourseService {
	
	@Autowired
	private CourseModelMapper courseModelMapper;
	@Autowired
	private CourseInforModelMapper courseInforModelMapper;
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
	
	public CourseInforModel getSingleCourseInfor(Integer id) {
		CourseInforModelExample courseInforModelExample = new CourseInforModelExample();
		CourseInforModelExample.Criteria courseInforModelExampleCri = courseInforModelExample.createCriteria();
		courseInforModelExampleCri.andFidEqualTo(id);
		List<CourseInforModel> courseInforList = courseInforModelMapper.selectByExampleWithBLOBs(courseInforModelExample);
		if(courseInforList.size() !=0 ) {
			return courseInforList.get(0);
		}
		else return null;
	}
	
	public void updateCourseIntro(CourseInfor courseInfor) {
		//插入课程介绍前查看是否已经存在
		CourseInforModelExample courseInforModelExample = new CourseInforModelExample();
		CourseInforModelExample.Criteria courseInforModelExampleCri = courseInforModelExample.createCriteria();
		courseInforModelExampleCri.andFidEqualTo(courseInfor.getCid());
		List<CourseInforModel> courseInforList = courseInforModelMapper.selectByExample(courseInforModelExample);
		//不存在怎添加
		if(courseInforList.size() == 0) {
			CourseInforModel courseInforModel = new CourseInforModel();
			courseInforModel.setFid(courseInfor.getCid());
			courseInforModel.setInfor(courseInfor.getInfor());
			courseInforModelMapper.insertSelective(courseInforModel);
		}else if(courseInforList.size()==1){
			//存在则更新
			CourseInforModel courseInforModel = courseInforList.get(0);
			courseInforModel.setInfor(courseInfor.getInfor());
			courseInforModelMapper.updateByPrimaryKeySelective(courseInforModel);
		}else {
			throw new ContestCommonException("duplicated course infor record found");
		}
		
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
