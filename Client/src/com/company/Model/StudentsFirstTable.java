package com.company.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

public class StudentsFirstTable {

    private SimpleStringProperty teacher;
    private SimpleStringProperty subject;
    private Button button;
    private SimpleStringProperty eventdate;
    private SimpleStringProperty duration;
    private SimpleStringProperty noOfQuestion;

    public StudentsFirstTable(String teacher, String subject, Button button, String eventdate, String duration, String noOfQuestion, String topic ) {
        this.teacher = new SimpleStringProperty(teacher);
        this.subject = new SimpleStringProperty(subject);
        this.button = button;
        this.eventdate = new SimpleStringProperty(eventdate);
        this.duration = new SimpleStringProperty(duration);
        this.noOfQuestion = new SimpleStringProperty(noOfQuestion);
        this.button.setText(topic);
    }

    public void setTeacher(String teacher) {
        this.teacher.set(teacher);
    }

    public void setSubject(String subject) {
        this.subject.set(subject);
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public void setEventdate(String eventdate) {
        this.eventdate.set(eventdate);
    }

    public void setDuration(String duration) {
        this.duration.set(duration);
    }

    public void setNoOfQuestion(String noOfQuestion) {
        this.noOfQuestion.set(noOfQuestion);
    }

    public String getTeacher() {
        return teacher.get();
    }

    public SimpleStringProperty teacherProperty() {
        return teacher;
    }

    public String getSubject() {
        return subject.get();
    }

    public SimpleStringProperty subjectProperty() {
        return subject;
    }

    public Button getButton() {
        return button;
    }

    public String getEventdate() {
        return eventdate.get();
    }

    public SimpleStringProperty eventdateProperty() {
        return eventdate;
    }

    public String getDuration() {
        return duration.get();
    }

    public SimpleStringProperty durationProperty() {
        return duration;
    }

    public String getNoOfQuestion() {
        return noOfQuestion.get();
    }

    public SimpleStringProperty noOfQuestionProperty() {
        return noOfQuestion;
    }
}