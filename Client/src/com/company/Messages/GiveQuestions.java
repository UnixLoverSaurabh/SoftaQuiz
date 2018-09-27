package com.company.Messages;

import java.io.Serializable;

public class GiveQuestions implements Serializable {
    private String username;
    private String teacherUsername;
    private String subject;
    private String topic;

    public GiveQuestions(String username,String teacherUsername, String subject, String topic) {
        this.username = username;
        this.teacherUsername = teacherUsername;
        this.subject = subject;
        this.topic = topic;
    }

    public String getUsername() {
        return username;
    }

    public String getTeacherUsername() {
        return teacherUsername;
    }

    public String getSubject() {
        return subject;
    }

    public String getTopic() {
        return topic;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTeacherUsername(String teacherUsername) {
        this.teacherUsername = teacherUsername;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}

