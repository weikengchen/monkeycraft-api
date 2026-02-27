# AGENTS.md

Instructions for AI agents working on this codebase.

## Project Overview

MonkeyCraft API is a Fabric mod API providing event hooks and utilities for Minecraft client interaction.

## Build Commands

```bash
# Build the project
./gradlew build

# Clean build
./gradlew clean build

# Publish to Maven
./gradlew publish
```

## Code Style

- Java 21
- No code comments unless explicitly requested
- Package: `com.chenweikeng.monkeycraft_api.v1`
- Use Fabric's `EventFactory.createArrayBacked()` for events
- Functional interfaces should be annotated with `@FunctionalInterface`
- Use `final` for utility classes with private constructors

## Architecture

### Core Classes

| Class | Purpose |
|-------|---------|
| `MonkeycraftApi` | Main API entry point with static events and methods |
| `MonkeycraftApiProvider` | Interface for API implementation |
| `MonkeycraftApiRegistration` | Provider registration (singleton) |

### Event Pattern

Events use Fabric's array-backed event system:
- Each event has a corresponding listener interface
- Listeners return a result enum (`PASS` to continue, other values to short-circuit)
- Context objects are mutable for modification scenarios

### Result Enums

- `ChatMessageResult`: ALLOW, MODIFY, DENY, PASS
- `CommandExecutionResult`: ALLOW, DENY, PASS

## Dependencies

- Fabric Loader
- Fabric API (compile only)
- Minecraft (see gradle.properties for version)

## File Structure

```
src/main/java/com/chenweikeng/monkeycraft_api/v1/
├── MonkeycraftApi.java              # Main API class
├── MonkeycraftApiProvider.java      # Provider interface
├── MonkeycraftApiRegistration.java  # Provider registration
├── IncomingChatContext.java         # Incoming chat context
├── OutgoingChatContext.java         # Outgoing chat context
├── IncomingChatListener.java        # Incoming chat listener
├── OutgoingChatListener.java        # Outgoing chat listener
├── MonkeycraftConnectedListener.java
├── MonkeycraftDisconnectedListener.java
├── MonkeycraftCommandExecutionListener.java
├── CommandExecutionResult.java      # Command result enum
└── ChatMessageResult.java           # Chat result enum
```

## Key Conventions

1. All public API is in `MonkeycraftApi` as static members
2. Events are `public static final Event<T>` fields
3. Provider methods check for null before delegating
4. State queries return `Boolean` (nullable) when provider unavailable
5. Only one provider can be registered at a time
