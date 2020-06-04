package com.google.sps.data;

public final class Comment {
  String name;
  String message;
  int upvotes;
  int downvotes;
  long timestamp;
  long id;

  public Comment(String name, String message,  long timestamp, int upvotes, int downvotes, long id) {
    this.name = name;
    this.message = message;    
    this.timestamp = timestamp;
    this.upvotes = upvotes;
    this.downvotes = downvotes;
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public String getMessage() {
    return this.message;
  }
}
