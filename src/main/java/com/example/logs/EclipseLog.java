package com.example.logs;

import com.example.Student;

public class EclipseLog extends Log{

  public EclipseLog(Student student, String timestamp, String eventType, String event) {
    super(student, timestamp, eventType, event);
  }

  public String getSource() {
    return "Eclipse";
  }
}
