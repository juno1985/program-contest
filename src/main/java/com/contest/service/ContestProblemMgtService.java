package com.contest.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contest.mapper.ProblemModelMapper;
import com.contest.model.ProblemModelWithBLOBs;
import com.contest.pojo.ProblemPojo;

@Service
public class ContestProblemMgtService {

	@Autowired
	private ProblemModelMapper problemModelMapper;
	
	public int addProblem(ProblemPojo problemPojo) {
		
		ProblemModelWithBLOBs problemModelWithBLOBs = new ProblemModelWithBLOBs();
		
		BeanUtils.copyProperties(problemPojo, problemModelWithBLOBs);
		
		return problemModelMapper.insertSelective(problemModelWithBLOBs);
	}
}
