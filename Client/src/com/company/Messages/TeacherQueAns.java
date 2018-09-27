package com.company.Messages;

import java.io.Serializable;

public class TeacherQueAns implements Serializable {
    private String Username;
    private String Subject;
    private String Topic;
    private String Question;
    private String Option1;
    private String Option2;
    private String Option3;
    private String Option4;
    private String Answer;
    private String Type;

    public TeacherQueAns(String username, String subject, String topic, String question, String option1, String option2, String option3, String option4, String answer, String type) {
        Username = username;
        Subject = subject;
        Topic = topic;
        Question = question;
        Option1 = option1;
        Option2 = option2;
        Option3 = option3;
        Option4 = option4;
        Answer = answer;
        Type = type;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public void setOption1(String option1) {
        Option1 = option1;
    }

    public void setOption2(String option2) {
        Option2 = option2;
    }

    public void setOption3(String option3) {
        Option3 = option3;
    }

    public void setOption4(String option4) {
        Option4 = option4;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public void setType(String type) { Type = type; }

    public String getUsername() {
        return Username;
    }

    public String getSubject() {
        return Subject;
    }

    public String getTopic() {
        return Topic;
    }

    public String getQuestion() {
        return Question;
    }

    public String getOption1() {
        return Option1;
    }

    public String getOption2() {
        return Option2;
    }

    public String getOption3() {
        return Option3;
    }

    public String getOption4() {
        return Option4;
    }

    public String getAnswer() {
        return Answer;
    }

    public String getType() { return Type; }
}
