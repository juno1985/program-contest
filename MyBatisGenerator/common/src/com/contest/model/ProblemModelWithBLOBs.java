package com.contest.model;

public class ProblemModelWithBLOBs extends ProblemModel {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_problems.description
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_problems.input
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    private String input;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_problems.output
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    private String output;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_problems.explanation
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    private String explanation;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_problems.description
     *
     * @return the value of t_problems.description
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_problems.description
     *
     * @param description the value for t_problems.description
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_problems.input
     *
     * @return the value of t_problems.input
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    public String getInput() {
        return input;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_problems.input
     *
     * @param input the value for t_problems.input
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    public void setInput(String input) {
        this.input = input == null ? null : input.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_problems.output
     *
     * @return the value of t_problems.output
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    public String getOutput() {
        return output;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_problems.output
     *
     * @param output the value for t_problems.output
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    public void setOutput(String output) {
        this.output = output == null ? null : output.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_problems.explanation
     *
     * @return the value of t_problems.explanation
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    public String getExplanation() {
        return explanation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_problems.explanation
     *
     * @param explanation the value for t_problems.explanation
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    public void setExplanation(String explanation) {
        this.explanation = explanation == null ? null : explanation.trim();
    }
}