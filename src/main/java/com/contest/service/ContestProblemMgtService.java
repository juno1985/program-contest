package com.contest.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.contest.mapper.ProblemCasesModelMapper;
import com.contest.mapper.ProblemModelMapper;
import com.contest.model.ProblemCasesModelWithBLOBs;
import com.contest.model.ProblemModel;
import com.contest.model.ProblemModelWithBLOBs;
import com.contest.pojo.CasePojo;
import com.contest.pojo.ProblemPojo;

@Service
public class ContestProblemMgtService {

	@Autowired
	private ProblemModelMapper problemModelMapper;
	@Autowired
	private ProblemCasesModelMapper problemCasesModelMapper;
	
	public int addProblem(ProblemPojo problemPojo) {
		
		ProblemModelWithBLOBs problemModelWithBLOBs = new ProblemModelWithBLOBs();
		
		BeanUtils.copyProperties(problemPojo, problemModelWithBLOBs);
		
		return problemModelMapper.insertSelective(problemModelWithBLOBs);
	}

	public List<ProblemModel> listProblem() {
		List<ProblemModel> listProblem = problemModelMapper.selectByExample(null);
		return listProblem;
	}

	public int addCase(CasePojo casePojo) {
		ProblemCasesModelWithBLOBs caseModel = new ProblemCasesModelWithBLOBs();
		BeanUtils.copyProperties(casePojo, caseModel);
		return problemCasesModelMapper.insertSelective(caseModel);
	}
}
