package com.google.sps.data;

/** Class containing server statistics. */
public final class Comment {
  String name;
  String message;
  String timeCreated;
  Long id;

  public Comment(String name, String message,  String timestamp, Long id) {
    this.name = name;
    this.message = message;    
    this.timeCreated = timestamp;
    this.id = id;
  }

  public void setDate(String newDate) {
    this.timeCreated = newDate;
  }

  public String getName() {
    return this.name;
  }

  public String getMessage() {
    return this.message;
  }
}
