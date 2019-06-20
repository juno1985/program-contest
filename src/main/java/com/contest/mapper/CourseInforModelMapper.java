package com.contest.mapper;

import com.contest.model.CourseInforModel;
import com.contest.model.CourseInforModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CourseInforModelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course_infor
     *
     * @mbg.generated Thu Jun 20 13:21:51 CST 2019
     */
    long countByExample(CourseInforModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course_infor
     *
     * @mbg.generated Thu Jun 20 13:21:51 CST 2019
     */
    int deleteByExample(CourseInforModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course_infor
     *
     * @mbg.generated Thu Jun 20 13:21:51 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course_infor
     *
     * @mbg.generated Thu Jun 20 13:21:51 CST 2019
     */
    int insert(CourseInforModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course_infor
     *
     * @mbg.generated Thu Jun 20 13:21:51 CST 2019
     */
    int insertSelective(CourseInforModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course_infor
     *
     * @mbg.generated Thu Jun 20 13:21:51 CST 2019
     */
    List<CourseInforModel> selectByExampleWithBLOBs(CourseInforModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course_infor
     *
     * @mbg.generated Thu Jun 20 13:21:51 CST 2019
     */
    List<CourseInforModel> selectByExample(CourseInforModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course_infor
     *
     * @mbg.generated Thu Jun 20 13:21:51 CST 2019
     */
    CourseInforModel selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course_infor
     *
     * @mbg.generated Thu Jun 20 13:21:51 CST 2019
     */
    int updateByExampleSelective(@Param("record") CourseInforModel record, @Param("example") CourseInforModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course_infor
     *
     * @mbg.generated Thu Jun 20 13:21:51 CST 2019
     */
    int updateByExampleWithBLOBs(@Param("record") CourseInforModel record, @Param("example") CourseInforModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course_infor
     *
     * @mbg.generated Thu Jun 20 13:21:51 CST 2019
     */
    int updateByExample(@Param("record") CourseInforModel record, @Param("example") CourseInforModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course_infor
     *
     * @mbg.generated Thu Jun 20 13:21:51 CST 2019
     */
    int updateByPrimaryKeySelective(CourseInforModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course_infor
     *
     * @mbg.generated Thu Jun 20 13:21:51 CST 2019
     */
    int updateByPrimaryKeyWithBLOBs(CourseInforModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course_infor
     *
     * @mbg.generated Thu Jun 20 13:21:51 CST 2019
     */
    int updateByPrimaryKey(CourseInforModel record);
}