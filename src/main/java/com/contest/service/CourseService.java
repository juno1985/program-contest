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
import com.contest.mapper.CourseVideoMapperExt;
import com.contest.mapper.CourseVideoModelMapper;
import com.contest.model.CourseInforModel;
import com.contest.model.CourseInforModelExample;
import com.contest.model.CourseModel;
import com.contest.model.CourseVideoModel;
import com.contest.model.CourseVideoModelExample;
import com.contest.pojo.CourseInfor;


@Service
public class CourseService {
	
	@Autowired
	private CourseModelMapper courseModelMapper;
	@Autowired
	private CourseVideoMapperExt courseVideoMapperExt;
	@Autowired
	private CourseVideoModelMapper courseVideoMapper;
	@Autowired
	private CourseInforModelMapper courseInforModelMapper;
	@Value("${softcits.website.resource.path}")
	private String resource_path;
	@Value("${softcits.website.course.pic.folder}")
	private String course_pic_folder;
	@Value("${softcits.website.course.video.folder}")
	private String course_video_folder;
	
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

	public void uploadVideo(MultipartFile video, String cid, String videoName) {
		
		Integer course_id = Integer.parseInt(cid);
	
		Integer serial = courseVideoMapperExt.getMaxSerial(course_id);
		if(null == serial) {
			serial = 0;
		}
		String fullUploadVideoName = video.getOriginalFilename();
		String extName = fullUploadVideoName.substring(fullUploadVideoName.lastIndexOf(".")+1);
		String newName = course_id.toString() + "_" + (serial+1) + "_" + videoName + "." + extName;
		
		try {
			byte[] bytes = video.getBytes();
			Path path = Paths.get(resource_path + course_video_folder + newName);
			Files.write(path, bytes);
		}catch(IOException e) {
			throw new ContestCommonException("视频上传失败");
		}
		CourseVideoModel vModel = new CourseVideoModel();
		vModel.setFid(Integer.parseInt(cid));
		vModel.setSerial(serial + 1);
		vModel.setName(newName);
		courseVideoMapper.insertSelective(vModel);
	}
	
	public List<CourseVideoModel> getCourseVideosByCourseId(Integer course_id){
		CourseVideoModelExample courseVideoModelExample = new CourseVideoModelExample();
		CourseVideoModelExample.Criteria courseVideoModelExaCri = courseVideoModelExample.createCriteria();
		courseVideoModelExaCri.andFidEqualTo(course_id);
		return courseVideoMapper.selectByExample(courseVideoModelExample);
	}

}
