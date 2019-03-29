package com.contest.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.contest.exception.ContestCommonException;
import com.contest.mapper.ProblemCasesModelMapper;
import com.contest.mapper.ProblemCodeRestrictModelMapper;
import com.contest.mapper.ProblemModelMapper;
import com.contest.model.ProblemCasesModelWithBLOBs;
import com.contest.model.ProblemCodeRestrictModelWithBLOBs;
import com.contest.model.ProblemModel;
import com.contest.model.ProblemModelExample;
import com.contest.model.ProblemModelWithBLOBs;
import com.contest.pojo.CasePojo;
import com.contest.pojo.ProblemPojo;

@Service
public class ContestProblemMgtService {

	@Autowired
	private ProblemModelMapper problemModelMapper;
	@Autowired
	private ProblemCasesModelMapper problemCasesModelMapper;
	@Autowired
	private ProblemCodeRestrictModelMapper problemCodeRestrictModelMapper;
	
	public void addProblem(ProblemPojo problemPojo) {
		
		ProblemModelWithBLOBs problemModelWithBLOBs = new ProblemModelWithBLOBs();
		ProblemCodeRestrictModelWithBLOBs problemCodeRestrictModelWithBLOBs = new ProblemCodeRestrictModelWithBLOBs();
		
		BeanUtils.copyProperties(problemPojo, problemModelWithBLOBs);
		problemModelWithBLOBs.setTitle(problemModelWithBLOBs.getTitle().trim());
		BeanUtils.copyProperties(problemPojo, problemCodeRestrictModelWithBLOBs);
		
		//增加问题
		problemModelMapper.insertSelective(problemModelWithBLOBs);
		
		//需要得到新添加problem的FID
		Integer problemId = getProblemPrimaryKeyByTitle(problemModelWithBLOBs.getTitle());
		problemCodeRestrictModelWithBLOBs.setFid(problemId);
		
		//增加问题对应的stub code
		problemCodeRestrictModelMapper.insertSelective(problemCodeRestrictModelWithBLOBs);
		
	}



	public int addCase(CasePojo casePojo) {
		ProblemCasesModelWithBLOBs caseModel = new ProblemCasesModelWithBLOBs();
		BeanUtils.copyProperties(casePojo, caseModel);
		return problemCasesModelMapper.insertSelective(caseModel);
	}
	/**
	 * 
	 * @param title
	 * @return -1则有相同的title
	 */
	public Integer getProblemPrimaryKeyByTitle(String title) {
		ProblemModelExample problemModelExample = new ProblemModelExample();
		ProblemModelExample.Criteria problemModelCri = problemModelExample.createCriteria();
		problemModelCri.andTitleEqualTo(title);
		List<ProblemModel> problemList = problemModelMapper.selectByExample(problemModelExample);
		if(problemList.size() > 1) {
			throw new ContestCommonException("有重复的问题title");
		}
		else if(problemList.size() == 0){
			throw new ContestCommonException("未能获得问题id,添加stub code不可能");
		}
		else {
			return problemList.get(0).getId();
		}
	}
}
