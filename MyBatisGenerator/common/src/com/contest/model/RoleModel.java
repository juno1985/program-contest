package com.contest.model;

public class RoleModel {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_role.id
     *
     * @mbg.generated Thu Mar 14 16:42:30 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_role.rolename
     *
     * @mbg.generated Thu Mar 14 16:42:30 CST 2019
     */
    private String rolename;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_role.status
     *
     * @mbg.generated Thu Mar 14 16:42:30 CST 2019
     */
    private Integer status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_role.comment
     *
     * @mbg.generated Thu Mar 14 16:42:30 CST 2019
     */
    private String comment;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_role.id
     *
     * @return the value of t_role.id
     *
     * @mbg.generated Thu Mar 14 16:42:30 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_role.id
     *
     * @param id the value for t_role.id
     *
     * @mbg.generated Thu Mar 14 16:42:30 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_role.rolename
     *
     * @return the value of t_role.rolename
     *
     * @mbg.generated Thu Mar 14 16:42:30 CST 2019
     */
    public String getRolename() {
        return rolename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_role.rolename
     *
     * @param rolename the value for t_role.rolename
     *
     * @mbg.generated Thu Mar 14 16:42:30 CST 2019
     */
    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_role.status
     *
     * @return the value of t_role.status
     *
     * @mbg.generated Thu Mar 14 16:42:30 CST 2019
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_role.status
     *
     * @param status the value for t_role.status
     *
     * @mbg.generated Thu Mar 14 16:42:30 CST 2019
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_role.comment
     *
     * @return the value of t_role.comment
     *
     * @mbg.generated Thu Mar 14 16:42:30 CST 2019
     */
    public String getComment() {
        return comment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_role.comment
     *
     * @param comment the value for t_role.comment
     *
     * @mbg.generated Thu Mar 14 16:42:30 CST 2019
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}