package com.google.sps.data;

public final class RatingData {
  
  long commentID;
  boolean isUpvote;
  
  public long getID() {
    return this.commentID;
  }

  public boolean isUpvote() {
    return this.isUpvote;
  }
}
