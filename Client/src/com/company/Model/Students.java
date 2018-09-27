package com.company.Model;

import javafx.beans.property.SimpleStringProperty;

public class Students {

    private SimpleStringProperty subject;
    private SimpleStringProperty topic;
    private SimpleStringProperty eventdate;

    public Students(String subject, String topic, String eventdate) {
        this.subject = new SimpleStringProperty(subject);
        this.topic = new SimpleStringProperty(topic);
        this.eventdate = new SimpleStringProperty(eventdate);
    }

    public String getSubject() { return subject.get(); }

    public void setSubject(String subject) {
        this.subject = new SimpleStringProperty(subject);
    }

    public String getTopic() {
        return topic.get();
    }

    public void setTopic(String topic) {
        this.topic = new SimpleStringProperty(topic);
    }

    public String getEventdate() {
        return eventdate.get();
    }

    public void setEventdate(String eventdate) {
        this.eventdate = new SimpleStringProperty(eventdate);
    }
}