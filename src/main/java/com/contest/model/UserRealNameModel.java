package com.contest.model;

import java.util.Date;

public class UserRealNameModel {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_realname.id
     *
     * @mbg.generated Wed Apr 17 16:24:06 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_realname.realname
     *
     * @mbg.generated Wed Apr 17 16:24:06 CST 2019
     */
    private String realname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_realname.regist_time
     *
     * @mbg.generated Wed Apr 17 16:24:06 CST 2019
     */
    private Date registTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_realname.uid
     *
     * @mbg.generated Wed Apr 17 16:24:06 CST 2019
     */
    private Integer uid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_realname.id
     *
     * @return the value of t_user_realname.id
     *
     * @mbg.generated Wed Apr 17 16:24:06 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_realname.id
     *
     * @param id the value for t_user_realname.id
     *
     * @mbg.generated Wed Apr 17 16:24:06 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_realname.realname
     *
     * @return the value of t_user_realname.realname
     *
     * @mbg.generated Wed Apr 17 16:24:06 CST 2019
     */
    public String getRealname() {
        return realname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_realname.realname
     *
     * @param realname the value for t_user_realname.realname
     *
     * @mbg.generated Wed Apr 17 16:24:06 CST 2019
     */
    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_realname.regist_time
     *
     * @return the value of t_user_realname.regist_time
     *
     * @mbg.generated Wed Apr 17 16:24:06 CST 2019
     */
    public Date getRegistTime() {
        return registTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_realname.regist_time
     *
     * @param registTime the value for t_user_realname.regist_time
     *
     * @mbg.generated Wed Apr 17 16:24:06 CST 2019
     */
    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_realname.uid
     *
     * @return the value of t_user_realname.uid
     *
     * @mbg.generated Wed Apr 17 16:24:06 CST 2019
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_realname.uid
     *
     * @param uid the value for t_user_realname.uid
     *
     * @mbg.generated Wed Apr 17 16:24:06 CST 2019
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }
}