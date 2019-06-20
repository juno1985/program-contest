package com.contest.mapper;

import com.contest.model.UserRealNameModel;
import com.contest.model.UserRealNameModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRealNameModelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_realname
     *
     * @mbg.generated Thu Jun 20 13:21:51 CST 2019
     */
    long countByExample(UserRealNameModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_realname
     *
     * @mbg.generated Thu Jun 20 13:21:51 CST 2019
     */
    int deleteByExample(UserRealNameModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_realname
     *
     * @mbg.generated Thu Jun 20 13:21:51 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_realname
     *
     * @mbg.generated Thu Jun 20 13:21:51 CST 2019
     */
    int insert(UserRealNameModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_realname
     *
     * @mbg.generated Thu Jun 20 13:21:51 CST 2019
     */
    int insertSelective(UserRealNameModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_realname
     *
     * @mbg.generated Thu Jun 20 13:21:51 CST 2019
     */
    List<UserRealNameModel> selectByExample(UserRealNameModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_realname
     *
     * @mbg.generated Thu Jun 20 13:21:51 CST 2019
     */
    UserRealNameModel selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_realname
     *
     * @mbg.generated Thu Jun 20 13:21:51 CST 2019
     */
    int updateByExampleSelective(@Param("record") UserRealNameModel record, @Param("example") UserRealNameModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_realname
     *
     * @mbg.generated Thu Jun 20 13:21:51 CST 2019
     */
    int updateByExample(@Param("record") UserRealNameModel record, @Param("example") UserRealNameModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_realname
     *
     * @mbg.generated Thu Jun 20 13:21:51 CST 2019
     */
    int updateByPrimaryKeySelective(UserRealNameModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_realname
     *
     * @mbg.generated Thu Jun 20 13:21:51 CST 2019
     */
    int updateByPrimaryKey(UserRealNameModel record);
}