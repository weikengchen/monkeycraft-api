package com.chenweikeng.monkeycraft_api.v1;

public class OutgoingChatContext {
  private String message;

  public OutgoingChatContext(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
