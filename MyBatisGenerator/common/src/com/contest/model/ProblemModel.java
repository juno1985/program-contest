package com.contest.model;

public class ProblemModel {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_problems.id
     *
     * @mbg.generated Thu Jun 20 13:21:50 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_problems.title
     *
     * @mbg.generated Thu Jun 20 13:21:50 CST 2019
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_problems.status
     *
     * @mbg.generated Thu Jun 20 13:21:50 CST 2019
     */
    private Integer status;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_problems.id
     *
     * @return the value of t_problems.id
     *
     * @mbg.generated Thu Jun 20 13:21:50 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_problems.id
     *
     * @param id the value for t_problems.id
     *
     * @mbg.generated Thu Jun 20 13:21:50 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_problems.title
     *
     * @return the value of t_problems.title
     *
     * @mbg.generated Thu Jun 20 13:21:50 CST 2019
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_problems.title
     *
     * @param title the value for t_problems.title
     *
     * @mbg.generated Thu Jun 20 13:21:50 CST 2019
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_problems.status
     *
     * @return the value of t_problems.status
     *
     * @mbg.generated Thu Jun 20 13:21:50 CST 2019
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_problems.status
     *
     * @param status the value for t_problems.status
     *
     * @mbg.generated Thu Jun 20 13:21:50 CST 2019
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}