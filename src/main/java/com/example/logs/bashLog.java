package com.example.logs;

import com.example.Student;

public class BashLog extends Log{
  private String stdError;
  private String stdOutput;

  public BashLog(Student student, String timestamp, String eventType, String event, String stderr, String stdout) {
    super(student, timestamp, eventType, event);
    this.stdError = stderr;
    this.stdOutput = stdout;
  }

  public String getSource() {
    return "Bash";
  }

  public String getStdError() {
    return stdError;
  }

  public String getStdOutput() {
    return stdOutput;
  }
}
