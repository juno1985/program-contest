package com.contest.model;

public class ProblemCodeRestrictModelWithBLOBs extends ProblemCodeRestrictModel {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_problem_code_restrict.prefix
     *
     * @mbg.generated Thu Jun 20 13:21:50 CST 2019
     */
    private String prefix;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_problem_code_restrict.suffix
     *
     * @mbg.generated Thu Jun 20 13:21:50 CST 2019
     */
    private String suffix;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_problem_code_restrict.prefix
     *
     * @return the value of t_problem_code_restrict.prefix
     *
     * @mbg.generated Thu Jun 20 13:21:50 CST 2019
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_problem_code_restrict.prefix
     *
     * @param prefix the value for t_problem_code_restrict.prefix
     *
     * @mbg.generated Thu Jun 20 13:21:50 CST 2019
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix == null ? null : prefix.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_problem_code_restrict.suffix
     *
     * @return the value of t_problem_code_restrict.suffix
     *
     * @mbg.generated Thu Jun 20 13:21:50 CST 2019
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_problem_code_restrict.suffix
     *
     * @param suffix the value for t_problem_code_restrict.suffix
     *
     * @mbg.generated Thu Jun 20 13:21:50 CST 2019
     */
    public void setSuffix(String suffix) {
        this.suffix = suffix == null ? null : suffix.trim();
    }
}