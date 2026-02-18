package com.chenweikeng.monkeycraft_api.v1;

@FunctionalInterface
public interface MonkeycraftChatListener {
  ChatMessageResult onChatMessage(ChatMessageContext context);
}
