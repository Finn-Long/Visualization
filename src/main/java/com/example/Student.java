package com.example;

import com.example.logs.log;

public class Student {
  private String id;
  private String name;
  private log[] cyverseLogs;
  private log[] bashLogs;

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

  public void setCyverseLogs(log[] logs) {
    cyverseLogs = logs;
  }

  public void setbashLogs(log[] logs) {
    bashLogs = logs;
  }
}
