# Chat Handler API Changes

## Summary

The chat handling system has been refactored to separate incoming and outgoing chat contexts into distinct types with appropriate data structures for each use case.

## Changes

### Removed Classes

- `ChatMessageContext` - Previously shared for both incoming and outgoing
- `MonkeycraftChatListener` - Previously shared listener interface

### New Classes

#### `IncomingChatContext`
- `message`: `net.minecraft.network.chat.Component` (Minecraft Component) - changed from `String`
- `senderUuid`: `String`
- `senderName`: `String`

#### `OutgoingChatContext`
- `message`: `String` - simplified, no sender info needed

#### `IncomingChatListener` (functional interface)
```java
ChatMessageResult onIncomingChat(IncomingChatContext context);
```

#### `OutgoingChatListener` (functional interface)
```java
ChatMessageResult onOutgoingChat(OutgoingChatContext context);
```

### Updated Events in `MonkeycraftApi`

| Before | After |
|--------|-------|
| `Event<MonkeycraftChatListener> INCOMING_CHAT` | `Event<IncomingChatListener> INCOMING_CHAT` |
| `Event<MonkeycraftChatListener> OUTGOING_CHAT` | `Event<OutgoingChatListener> OUTGOING_CHAT` |

## Migration Guide

### For API Consumers (Plugins/Mods)

#### Before
```java
MonkeycraftApi.INCOMING_CHAT.register(context -> {
    String message = context.getMessage(); // String
    String uuid = context.getSenderUuid();
    String name = context.getSenderName();
    boolean outgoing = context.isOutgoing();
    // ...
    return ChatMessageResult.PASS;
});

MonkeycraftApi.OUTGOING_CHAT.register(context -> {
    String message = context.getMessage();
    // ...
    return ChatMessageResult.PASS;
});
```

#### After
```java
MonkeycraftApi.INCOMING_CHAT.register(context -> {
    Component message = context.getMessage(); // Component
    String uuid = context.getSenderUuid();
    String name = context.getSenderName();
    // isOutgoing() removed - this listener is only for incoming
    // ...
    return ChatMessageResult.PASS;
});

MonkeycraftApi.OUTGOING_CHAT.register(context -> {
    String message = context.getMessage();
    // Only message available for outgoing
    // ...
    return ChatMessageResult.PASS;
});
```

### For API Implementations (Backend)

#### Incoming Chat Events

When firing the `INCOMING_CHAT` event:

```java
// Before
ChatMessageContext ctx = new ChatMessageContext(
    messageString,  // String
    senderUuid,
    senderName,
    false           // outgoing
);

// After
IncomingChatContext ctx = new IncomingChatContext(
    messageComponent,  // net.minecraft.network.chat.Component
    senderUuid,
    senderName
);
```

#### Outgoing Chat Events

When firing the `OUTGOING_CHAT` event:

```java
// Before
ChatMessageContext ctx = new ChatMessageContext(
    messageString,
    senderUuid,
    senderName,
    true
);

// After
OutgoingChatContext ctx = new OutgoingChatContext(
    messageString  // String only
);
```

#### Handling MODIFY Result

For incoming chat with modified message:
```java
ChatMessageResult result = MonkeycraftApi.INCOMING_CHAT.invoker().onIncomingChat(context);
if (result == ChatMessageResult.MODIFY) {
    Component modifiedMessage = context.getMessage(); // Get modified Component
    // Use modifiedMessage for further processing
}
```

For outgoing chat with modified message:
```java
ChatMessageResult result = MonkeycraftApi.OUTGOING_CHAT.invoker().onOutgoingChat(context);
if (result == ChatMessageResult.MODIFY) {
    String modifiedMessage = context.getMessage(); // Get modified String
    // Use modifiedMessage for further processing
}
```

## Rationale

1. **Type Safety**: Incoming messages now use `Text` (Component) which is the native Minecraft type for rich text, preserving formatting and styling information.

2. **Simplicity**: Outgoing messages only need a simple `String` since the client typically sends plain text.

3. **Separation**: Each context contains only the relevant data for its use case, making the API cleaner and more intuitive.
