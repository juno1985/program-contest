package com.contest.mapper;

import com.contest.model.ProblemCodeRestrictModel;
import com.contest.model.ProblemCodeRestrictModelExample;
import com.contest.model.ProblemCodeRestrictModelWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProblemCodeRestrictModelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_problem_code_restrict
     *
     * @mbg.generated Fri Mar 29 13:29:55 CST 2019
     */
    long countByExample(ProblemCodeRestrictModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_problem_code_restrict
     *
     * @mbg.generated Fri Mar 29 13:29:55 CST 2019
     */
    int deleteByExample(ProblemCodeRestrictModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_problem_code_restrict
     *
     * @mbg.generated Fri Mar 29 13:29:55 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_problem_code_restrict
     *
     * @mbg.generated Fri Mar 29 13:29:55 CST 2019
     */
    int insert(ProblemCodeRestrictModelWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_problem_code_restrict
     *
     * @mbg.generated Fri Mar 29 13:29:55 CST 2019
     */
    int insertSelective(ProblemCodeRestrictModelWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_problem_code_restrict
     *
     * @mbg.generated Fri Mar 29 13:29:55 CST 2019
     */
    List<ProblemCodeRestrictModelWithBLOBs> selectByExampleWithBLOBs(ProblemCodeRestrictModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_problem_code_restrict
     *
     * @mbg.generated Fri Mar 29 13:29:55 CST 2019
     */
    List<ProblemCodeRestrictModel> selectByExample(ProblemCodeRestrictModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_problem_code_restrict
     *
     * @mbg.generated Fri Mar 29 13:29:55 CST 2019
     */
    ProblemCodeRestrictModelWithBLOBs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_problem_code_restrict
     *
     * @mbg.generated Fri Mar 29 13:29:55 CST 2019
     */
    int updateByExampleSelective(@Param("record") ProblemCodeRestrictModelWithBLOBs record, @Param("example") ProblemCodeRestrictModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_problem_code_restrict
     *
     * @mbg.generated Fri Mar 29 13:29:55 CST 2019
     */
    int updateByExampleWithBLOBs(@Param("record") ProblemCodeRestrictModelWithBLOBs record, @Param("example") ProblemCodeRestrictModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_problem_code_restrict
     *
     * @mbg.generated Fri Mar 29 13:29:55 CST 2019
     */
    int updateByExample(@Param("record") ProblemCodeRestrictModel record, @Param("example") ProblemCodeRestrictModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_problem_code_restrict
     *
     * @mbg.generated Fri Mar 29 13:29:55 CST 2019
     */
    int updateByPrimaryKeySelective(ProblemCodeRestrictModelWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_problem_code_restrict
     *
     * @mbg.generated Fri Mar 29 13:29:55 CST 2019
     */
    int updateByPrimaryKeyWithBLOBs(ProblemCodeRestrictModelWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_problem_code_restrict
     *
     * @mbg.generated Fri Mar 29 13:29:55 CST 2019
     */
    int updateByPrimaryKey(ProblemCodeRestrictModel record);
}