package com.contest.model;

import java.util.Date;

public class KefuChatModel {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_kefu_chat.id
     *
     * @mbg.generated Wed Apr 17 13:57:41 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_kefu_chat.session_id
     *
     * @mbg.generated Wed Apr 17 13:57:41 CST 2019
     */
    private String sessionId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_kefu_chat.chat_cont
     *
     * @mbg.generated Wed Apr 17 13:57:41 CST 2019
     */
    private String chatCont;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_kefu_chat.send_time
     *
     * @mbg.generated Wed Apr 17 13:57:41 CST 2019
     */
    private Date sendTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_kefu_chat.id
     *
     * @return the value of t_kefu_chat.id
     *
     * @mbg.generated Wed Apr 17 13:57:41 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_kefu_chat.id
     *
     * @param id the value for t_kefu_chat.id
     *
     * @mbg.generated Wed Apr 17 13:57:41 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_kefu_chat.session_id
     *
     * @return the value of t_kefu_chat.session_id
     *
     * @mbg.generated Wed Apr 17 13:57:41 CST 2019
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_kefu_chat.session_id
     *
     * @param sessionId the value for t_kefu_chat.session_id
     *
     * @mbg.generated Wed Apr 17 13:57:41 CST 2019
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId == null ? null : sessionId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_kefu_chat.chat_cont
     *
     * @return the value of t_kefu_chat.chat_cont
     *
     * @mbg.generated Wed Apr 17 13:57:41 CST 2019
     */
    public String getChatCont() {
        return chatCont;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_kefu_chat.chat_cont
     *
     * @param chatCont the value for t_kefu_chat.chat_cont
     *
     * @mbg.generated Wed Apr 17 13:57:41 CST 2019
     */
    public void setChatCont(String chatCont) {
        this.chatCont = chatCont == null ? null : chatCont.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_kefu_chat.send_time
     *
     * @return the value of t_kefu_chat.send_time
     *
     * @mbg.generated Wed Apr 17 13:57:41 CST 2019
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_kefu_chat.send_time
     *
     * @param sendTime the value for t_kefu_chat.send_time
     *
     * @mbg.generated Wed Apr 17 13:57:41 CST 2019
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}