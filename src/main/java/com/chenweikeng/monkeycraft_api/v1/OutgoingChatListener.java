package com.chenweikeng.monkeycraft_api.v1;

@FunctionalInterface
public interface OutgoingChatListener {
  ChatMessageResult onOutgoingChat(OutgoingChatContext context);
}
