package com.contest.mapper;

import com.contest.model.RoleModel;
import com.contest.model.RoleModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleModelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    long countByExample(RoleModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    int deleteByExample(RoleModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    int insert(RoleModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    int insertSelective(RoleModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    List<RoleModel> selectByExample(RoleModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    RoleModel selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    int updateByExampleSelective(@Param("record") RoleModel record, @Param("example") RoleModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    int updateByExample(@Param("record") RoleModel record, @Param("example") RoleModelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    int updateByPrimaryKeySelective(RoleModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    int updateByPrimaryKey(RoleModel record);
}