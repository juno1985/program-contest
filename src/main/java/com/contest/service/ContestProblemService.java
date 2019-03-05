package com.contest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contest.mapper.ProblemModelMapper;
import com.contest.model.ProblemModelWithBLOBs;

@Service
public class ContestProblemService {

	@Autowired
	private ProblemModelMapper problemModelMapper;
	
	public ProblemModelWithBLOBs getSingleProblem(int id) {
		return problemModelMapper.selectByPrimaryKey(id);
	}
}
