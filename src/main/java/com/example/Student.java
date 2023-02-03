package com.example;

public class Student {
  private String id;
  private String name;

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
}
