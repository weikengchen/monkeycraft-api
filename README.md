# MonkeyCraft API

A Fabric mod API for MonkeyCraft that provides event hooks and utilities for interacting with the Minecraft client.

## Version compatibility

Published via JitPack. Branches `mc-<version>` track each Minecraft line; tag `1.0.0-mc<version>` is the release per line.

| Tag              | MC       | Loom          | Loader  | Fabric API        | Java |
|------------------|----------|---------------|---------|-------------------|------|
| 1.0.0-mc1.19     | 1.19     | 1.16-SNAPSHOT | 0.19.2  | 0.58.0+1.19       | 17   |
| 1.0.0-mc1.21.11  | 1.21.11  | 1.16-SNAPSHOT | 0.19.2  | 0.141.3+1.21.11   | 21   |
| 1.0.0-mc26.1     | 26.1     | 1.15-SNAPSHOT | 0.18.5  | 0.144.3+26.1      | 25   |

## Installation

Add the JitPack repository and one line per target MC version — the `minecraft_version` property in your consumer's `gradle.properties` selects the matching artifact:

```groovy
repositories {
    maven { url = "https://jitpack.io" }
}

dependencies {
    modImplementation("com.github.weikengchen:monkeycraft-api:1.0.0-mc${project.minecraft_version}") { transitive = false }
    include "com.github.weikengchen:monkeycraft-api:1.0.0-mc${project.minecraft_version}"
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
