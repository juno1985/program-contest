package com.contest.mapper;
import java.util.List;

import com.contest.model.AllUsersCodeHistoryPojo;
import com.contest.model.CodeHistModel;

public interface CodeHistModelMapperExt {
  Integer insertCodeHistAndGetKey(CodeHistModel codeHistModel);
  
  List<AllUsersCodeHistoryPojo> getAllUsersHistByProblemId(Integer problemId);
}