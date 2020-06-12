package com.google.sps.data;

public final class RatingData {
  
  long commentID;
  int vote;
  
  public long getID() {
    return this.commentID;
  }

  public int getVote() {
    return this.vote;
  }
}
