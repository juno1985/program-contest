package com.contest.mapper;

import com.contest.model.UserRoleModel;
import com.contest.model.UserRoleModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRoleModelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_role
     *
     * @mbg.generated Thu Mar 14 16:42:30 CST 2019
     */
    long countByExample(UserRoleModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_role
     *
     * @mbg.generated Thu Mar 14 16:42:30 CST 2019
     */
    int deleteByExample(UserRoleModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_role
     *
     * @mbg.generated Thu Mar 14 16:42:30 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_role
     *
     * @mbg.generated Thu Mar 14 16:42:30 CST 2019
     */
    int insert(UserRoleModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_role
     *
     * @mbg.generated Thu Mar 14 16:42:30 CST 2019
     */
    int insertSelective(UserRoleModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_role
     *
     * @mbg.generated Thu Mar 14 16:42:30 CST 2019
     */
    List<UserRoleModel> selectByExample(UserRoleModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_role
     *
     * @mbg.generated Thu Mar 14 16:42:30 CST 2019
     */
    UserRoleModel selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_role
     *
     * @mbg.generated Thu Mar 14 16:42:30 CST 2019
     */
    int updateByExampleSelective(@Param("record") UserRoleModel record, @Param("example") UserRoleModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_role
     *
     * @mbg.generated Thu Mar 14 16:42:30 CST 2019
     */
    int updateByExample(@Param("record") UserRoleModel record, @Param("example") UserRoleModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_role
     *
     * @mbg.generated Thu Mar 14 16:42:30 CST 2019
     */
    int updateByPrimaryKeySelective(UserRoleModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_role
     *
     * @mbg.generated Thu Mar 14 16:42:30 CST 2019
     */
    int updateByPrimaryKey(UserRoleModel record);
}