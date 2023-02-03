package com.example.logs;

import com.example.Student;

public class Log extends abstractLog{
  private Student student;
  private String timestamp;
  private String eventType;
  private Boolean milestone;
  private String event;
  private String source;

  public Log(Student student, String timestamp, String eventType, String event) {
    this.student = student;
    this.timestamp = timestamp;
    this.eventType = eventType;
    this.event = event;
  }

  public Student getStudent() {
    return student;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public String getEventType() {
    return eventType;
  }

  public String getEvent() {
    return event;
  }

  public Boolean isMilestone() {
    return milestone;
  }

  public void setMilestone(Boolean milestone) {
    this.milestone = milestone;
  }

  public String toString() {
    return student.getName() + ", " + student.getId() + ", " + timestamp + ", " + event + ", "  + eventType;
  }

  public String getSource(){
    return "basic log";
  };
}
