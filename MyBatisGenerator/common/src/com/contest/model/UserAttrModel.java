package com.contest.model;

public class UserAttrModel {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_attr.id
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_attr.uid
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    private Integer uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_attr.phone
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    private String phone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_attr.email
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    private String email;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_attr.id
     *
     * @return the value of t_user_attr.id
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_attr.id
     *
     * @param id the value for t_user_attr.id
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_attr.uid
     *
     * @return the value of t_user_attr.uid
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_attr.uid
     *
     * @param uid the value for t_user_attr.uid
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_attr.phone
     *
     * @return the value of t_user_attr.phone
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_attr.phone
     *
     * @param phone the value for t_user_attr.phone
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_attr.email
     *
     * @return the value of t_user_attr.email
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_attr.email
     *
     * @param email the value for t_user_attr.email
     *
     * @mbg.generated Mon Mar 25 08:13:49 CST 2019
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }
}