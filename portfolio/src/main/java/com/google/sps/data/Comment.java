package com.google.sps.data;

/** Class containing server statistics. */
public final class Comment {
  String name;
  String message;
  long timestamp;
  Long id;

  public Comment(String name, String message,  long timestamp, Long id) {
    this.name = name;
    this.message = message;    
    this.timestamp = timestamp;
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public String getMessage() {
    return this.message;
  }
}
