# MonkeyCraft API

A Fabric mod API for MonkeyCraft that provides event hooks and utilities for interacting with the Minecraft client.

## Version

- API Version: 1.0.0
- Java: 21
- Minecraft: See `gradle.properties`

## Installation

Add to your `build.gradle`:

```groovy
repositories {
    maven { url = "https://your-maven-repo" }
}

dependencies {
    modImplementation "com.chenweikeng.monkeycraft_api:monkeycraft-api:1.0.0"
}
```

## Events

All events use Fabric's event system and can be registered using `MonkeycraftApi.EVENT_NAME.register()`.

### Connection Events

| Event | Listener | Description |
|-------|----------|-------------|
| `CONNECTION` | `MonkeycraftConnectedListener` | Fired when the client connects to a server |
| `DISCONNECTION` | `MonkeycraftDisconnectedListener` | Fired when the client disconnects |

### Chat Events

| Event | Listener | Description |
|-------|----------|-------------|
| `INCOMING_CHAT` | `IncomingChatListener` | Fired when receiving a chat message |
| `OUTGOING_CHAT` | `OutgoingChatListener` | Fired when sending a chat message |

### Command Event

| Event | Listener | Description |
|-------|----------|-------------|
| `COMMAND_EXECUTION` | `MonkeycraftCommandExecutionListener` | Fired before a command is executed |

## Result Enums

### ChatMessageResult

| Value | Description |
|-------|-------------|
| `ALLOW` | Allow the message to pass through unchanged |
| `MODIFY` | Allow with modifications (message was changed in context) |
| `DENY` | Block the message |
| `PASS` | Pass to next listener (default behavior) |

### CommandExecutionResult

| Value | Description |
|-------|-------------|
| `ALLOW` | Allow the command to execute |
| `DENY` | Block the command |
| `PASS` | Pass to next listener (default behavior) |

## Context Classes

### IncomingChatContext

| Field | Type | Description |
|-------|------|-------------|
| `message` | `Component` | The chat message (mutable) |
| `senderUuid` | `String` | UUID of the message sender |
| `senderName` | `String` | Name of the message sender |

### OutgoingChatContext

| Field | Type | Description |
|-------|------|-------------|
| `message` | `String` | The outgoing message (mutable) |

## API Methods

### Availability

```java
boolean isAvailable = MonkeycraftApi.isAvailable();
```

### Notifications

```java
// Schedule a timed notification
MonkeycraftApi.setTimedNotification(
    fireAtEpochMs,  // When to fire (epoch milliseconds)
    "Title",        // Notification title
    "Body",         // Notification body
    true,           // Play sound
    "Countdown"     // Countdown text
);

// Cancel scheduled notification
MonkeycraftApi.cancelTimedNotification();

// Send immediate notification
MonkeycraftApi.sendImmediateNotification("Title", "Body", true);
```

### Hibernation

```java
// Start hibernation mode
MonkeycraftApi.startHibernation("AFK message");

// Update hibernation message
MonkeycraftApi.setHibernationMessage("New AFK message");

// End hibernation mode
MonkeycraftApi.endHibernation();
```

### Client State

```java
Boolean connected = MonkeycraftApi.isClientConnected();
Boolean hibernating = MonkeycraftApi.isHibernating();
```

## Provider Registration

Implement `MonkeycraftApiProvider` and register it:

```java
MonkeycraftApiRegistration.register(myProvider);
```

Only one provider can be registered at a time. Unregister with:

```java
MonkeycraftApiRegistration.unregister();
```

## Building

```bash
./gradlew build
```

## Publishing

```bash
./gradlew publish
```
