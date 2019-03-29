package com.contest.mapper;

import com.contest.model.ProblemTypeModel;
import com.contest.model.ProblemTypeModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProblemTypeModelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_problem_type
     *
     * @mbg.generated Fri Mar 29 13:29:55 CST 2019
     */
    long countByExample(ProblemTypeModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_problem_type
     *
     * @mbg.generated Fri Mar 29 13:29:55 CST 2019
     */
    int deleteByExample(ProblemTypeModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_problem_type
     *
     * @mbg.generated Fri Mar 29 13:29:55 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_problem_type
     *
     * @mbg.generated Fri Mar 29 13:29:55 CST 2019
     */
    int insert(ProblemTypeModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_problem_type
     *
     * @mbg.generated Fri Mar 29 13:29:55 CST 2019
     */
    int insertSelective(ProblemTypeModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_problem_type
     *
     * @mbg.generated Fri Mar 29 13:29:55 CST 2019
     */
    List<ProblemTypeModel> selectByExample(ProblemTypeModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_problem_type
     *
     * @mbg.generated Fri Mar 29 13:29:55 CST 2019
     */
    ProblemTypeModel selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_problem_type
     *
     * @mbg.generated Fri Mar 29 13:29:55 CST 2019
     */
    int updateByExampleSelective(@Param("record") ProblemTypeModel record, @Param("example") ProblemTypeModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_problem_type
     *
     * @mbg.generated Fri Mar 29 13:29:55 CST 2019
     */
    int updateByExample(@Param("record") ProblemTypeModel record, @Param("example") ProblemTypeModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_problem_type
     *
     * @mbg.generated Fri Mar 29 13:29:55 CST 2019
     */
    int updateByPrimaryKeySelective(ProblemTypeModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_problem_type
     *
     * @mbg.generated Fri Mar 29 13:29:55 CST 2019
     */
    int updateByPrimaryKey(ProblemTypeModel record);
}