package com.example;

import com.example.logs.Log;

public class Student {
  private String id;
  private String name;
  private Log[] cyverseLogs;
  private Log[] bashLogs;

  public Student (String id, String name) {
    this.id = id;
    this.name = name;
  }

  public String getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public void setCyverseLogs(Log[] logs) {
    cyverseLogs = logs;
  }

  public void setbashLogs(Log[] logs) {
    bashLogs = logs;
  }
}
