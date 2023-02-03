package com.example.logs;

import com.example.Student;

public abstract class abstractLog {
  public abstract Student getStudent();
  public abstract String getEvent();
  public abstract String getEventType();
  public abstract String getTimestamp();
  public abstract Boolean isMilestone();
  public abstract void setMilestone(Boolean milestone);
  public abstract String toString();
  public abstract String getSource();


}
