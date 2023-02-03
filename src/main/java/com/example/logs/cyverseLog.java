package com.example.logs;

import com.example.Student;

public class CyverseLog extends Log{
  private Student student;
  private String timestamp;
  private String eventType;
  private Boolean milestone;
  private String event;
  private String source;
  private String url;

  public CyverseLog(Student student, String timestamp, String eventType, String event, String url) {
    super(student, timestamp, eventType, event);
    this.url = url;
  }

  public String getSource() {
    return "Cyverse";
  }

  public String getUrl() {
    return url;
  }


}
