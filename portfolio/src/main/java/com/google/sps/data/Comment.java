package com.google.sps.data;
import java.util.List;
import com.google.appengine.api.datastore.EmbeddedEntity;

public final class Comment {
  String name;
  String message;  
  long timestamp;
  EmbeddedEntity voteMap;
  String userID;  
  long id;

  public Comment(String name, String message, long timestamp, EmbeddedEntity voteMap, String userID, long id) {
    this.name = name;
    this.message = message;    
    this.timestamp = timestamp;
    this.voteMap = voteMap;
    this.userID = userID;
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public String getMessage() {
    return this.message;
  }
}
