package com.contest.mapper;

import com.contest.model.CodeHistModel;
import com.contest.model.CodeHistModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CodeHistModelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_code_hist
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    long countByExample(CodeHistModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_code_hist
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    int deleteByExample(CodeHistModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_code_hist
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_code_hist
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    int insert(CodeHistModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_code_hist
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    int insertSelective(CodeHistModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_code_hist
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    List<CodeHistModel> selectByExampleWithBLOBs(CodeHistModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_code_hist
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    List<CodeHistModel> selectByExample(CodeHistModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_code_hist
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    CodeHistModel selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_code_hist
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    int updateByExampleSelective(@Param("record") CodeHistModel record, @Param("example") CodeHistModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_code_hist
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    int updateByExampleWithBLOBs(@Param("record") CodeHistModel record, @Param("example") CodeHistModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_code_hist
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    int updateByExample(@Param("record") CodeHistModel record, @Param("example") CodeHistModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_code_hist
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    int updateByPrimaryKeySelective(CodeHistModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_code_hist
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    int updateByPrimaryKeyWithBLOBs(CodeHistModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_code_hist
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    int updateByPrimaryKey(CodeHistModel record);
}