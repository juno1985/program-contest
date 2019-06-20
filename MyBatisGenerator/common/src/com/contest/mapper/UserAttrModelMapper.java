package com.contest.mapper;

import com.contest.model.UserAttrModel;
import com.contest.model.UserAttrModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserAttrModelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_attr
     *
     * @mbg.generated Thu Jun 20 13:21:50 CST 2019
     */
    long countByExample(UserAttrModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_attr
     *
     * @mbg.generated Thu Jun 20 13:21:50 CST 2019
     */
    int deleteByExample(UserAttrModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_attr
     *
     * @mbg.generated Thu Jun 20 13:21:50 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_attr
     *
     * @mbg.generated Thu Jun 20 13:21:50 CST 2019
     */
    int insert(UserAttrModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_attr
     *
     * @mbg.generated Thu Jun 20 13:21:50 CST 2019
     */
    int insertSelective(UserAttrModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_attr
     *
     * @mbg.generated Thu Jun 20 13:21:50 CST 2019
     */
    List<UserAttrModel> selectByExample(UserAttrModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_attr
     *
     * @mbg.generated Thu Jun 20 13:21:50 CST 2019
     */
    UserAttrModel selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_attr
     *
     * @mbg.generated Thu Jun 20 13:21:50 CST 2019
     */
    int updateByExampleSelective(@Param("record") UserAttrModel record, @Param("example") UserAttrModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_attr
     *
     * @mbg.generated Thu Jun 20 13:21:50 CST 2019
     */
    int updateByExample(@Param("record") UserAttrModel record, @Param("example") UserAttrModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_attr
     *
     * @mbg.generated Thu Jun 20 13:21:50 CST 2019
     */
    int updateByPrimaryKeySelective(UserAttrModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_attr
     *
     * @mbg.generated Thu Jun 20 13:21:50 CST 2019
     */
    int updateByPrimaryKey(UserAttrModel record);
}