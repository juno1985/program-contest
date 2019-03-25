package com.contest.mapper;
import com.contest.model.CodeHistModel;

public interface CodeHistModelMapperExt {
  Integer insertCodeHistAndGetKey(CodeHistModel codeHistModel);
}