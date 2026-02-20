package com.chenweikeng.monkeycraft_api.v1;

import net.minecraft.network.chat.Component;

public class IncomingChatContext {
  private Component message;
  private final String senderUuid;
  private final String senderName;

  public IncomingChatContext(Component message, String senderUuid, String senderName) {
    this.message = message;
    this.senderUuid = senderUuid;
    this.senderName = senderName;
  }

  public Component getMessage() {
    return message;
  }

  public void setMessage(Component message) {
    this.message = message;
  }

  public String getSenderUuid() {
    return senderUuid;
  }

  public String getSenderName() {
    return senderName;
  }
}
