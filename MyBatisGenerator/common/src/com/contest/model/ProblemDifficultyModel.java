package com.contest.model;

public class ProblemDifficultyModel {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_problem_difficulty.id
     *
     * @mbg.generated Thu Apr 04 10:27:28 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_problem_difficulty.problem_difficulty
     *
     * @mbg.generated Thu Apr 04 10:27:28 CST 2019
     */
    private String problemDifficulty;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_problem_difficulty.fid
     *
     * @mbg.generated Thu Apr 04 10:27:28 CST 2019
     */
    private Integer fid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_problem_difficulty.id
     *
     * @return the value of t_problem_difficulty.id
     *
     * @mbg.generated Thu Apr 04 10:27:28 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_problem_difficulty.id
     *
     * @param id the value for t_problem_difficulty.id
     *
     * @mbg.generated Thu Apr 04 10:27:28 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_problem_difficulty.problem_difficulty
     *
     * @return the value of t_problem_difficulty.problem_difficulty
     *
     * @mbg.generated Thu Apr 04 10:27:28 CST 2019
     */
    public String getProblemDifficulty() {
        return problemDifficulty;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_problem_difficulty.problem_difficulty
     *
     * @param problemDifficulty the value for t_problem_difficulty.problem_difficulty
     *
     * @mbg.generated Thu Apr 04 10:27:28 CST 2019
     */
    public void setProblemDifficulty(String problemDifficulty) {
        this.problemDifficulty = problemDifficulty == null ? null : problemDifficulty.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_problem_difficulty.fid
     *
     * @return the value of t_problem_difficulty.fid
     *
     * @mbg.generated Thu Apr 04 10:27:28 CST 2019
     */
    public Integer getFid() {
        return fid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_problem_difficulty.fid
     *
     * @param fid the value for t_problem_difficulty.fid
     *
     * @mbg.generated Thu Apr 04 10:27:28 CST 2019
     */
    public void setFid(Integer fid) {
        this.fid = fid;
    }
}