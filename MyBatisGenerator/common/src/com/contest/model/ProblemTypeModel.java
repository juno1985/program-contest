package com.contest.model;

public class ProblemTypeModel {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_problem_type.id
     *
     * @mbg.generated Thu Mar 14 16:42:30 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_problem_type.problem_type
     *
     * @mbg.generated Thu Mar 14 16:42:30 CST 2019
     */
    private String problemType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_problem_type.fid
     *
     * @mbg.generated Thu Mar 14 16:42:30 CST 2019
     */
    private Integer fid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_problem_type.id
     *
     * @return the value of t_problem_type.id
     *
     * @mbg.generated Thu Mar 14 16:42:30 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_problem_type.id
     *
     * @param id the value for t_problem_type.id
     *
     * @mbg.generated Thu Mar 14 16:42:30 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_problem_type.problem_type
     *
     * @return the value of t_problem_type.problem_type
     *
     * @mbg.generated Thu Mar 14 16:42:30 CST 2019
     */
    public String getProblemType() {
        return problemType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_problem_type.problem_type
     *
     * @param problemType the value for t_problem_type.problem_type
     *
     * @mbg.generated Thu Mar 14 16:42:30 CST 2019
     */
    public void setProblemType(String problemType) {
        this.problemType = problemType == null ? null : problemType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_problem_type.fid
     *
     * @return the value of t_problem_type.fid
     *
     * @mbg.generated Thu Mar 14 16:42:30 CST 2019
     */
    public Integer getFid() {
        return fid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_problem_type.fid
     *
     * @param fid the value for t_problem_type.fid
     *
     * @mbg.generated Thu Mar 14 16:42:30 CST 2019
     */
    public void setFid(Integer fid) {
        this.fid = fid;
    }
}