package com.chenweikeng.monkeycraft_api.v1;

@FunctionalInterface
public interface IncomingChatListener {
  ChatMessageResult onIncomingChat(IncomingChatContext context);
}
