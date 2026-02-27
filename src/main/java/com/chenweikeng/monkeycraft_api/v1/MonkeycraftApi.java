package com.chenweikeng.monkeycraft_api.v1;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public final class MonkeycraftApi {
  private MonkeycraftApi() {}

  public static final Event<MonkeycraftConnectedListener> CONNECTION =
      EventFactory.createArrayBacked(
          MonkeycraftConnectedListener.class,
          (listeners) ->
              (remoteAddress) -> {
                for (MonkeycraftConnectedListener listener : listeners) {
                  listener.onConnected(remoteAddress);
                }
              });

  public static final Event<MonkeycraftDisconnectedListener> DISCONNECTION =
      EventFactory.createArrayBacked(
          MonkeycraftDisconnectedListener.class,
          (listeners) ->
              () -> {
                for (MonkeycraftDisconnectedListener listener : listeners) {
                  listener.onDisconnected();
                }
              });

  public static final Event<MonkeycraftCommandExecutionListener> COMMAND_EXECUTION =
      EventFactory.createArrayBacked(
          MonkeycraftCommandExecutionListener.class,
          (listeners) ->
              (command) -> {
                for (MonkeycraftCommandExecutionListener listener : listeners) {
                  CommandExecutionResult result = listener.onCommandExecution(command);
                  if (result != CommandExecutionResult.PASS) {
                    return result;
                  }
                }
                return CommandExecutionResult.PASS;
              });

  public static final Event<IncomingChatListener> INCOMING_CHAT =
      EventFactory.createArrayBacked(
          IncomingChatListener.class,
          (listeners) ->
              (context) -> {
                for (IncomingChatListener listener : listeners) {
                  ChatMessageResult result = listener.onIncomingChat(context);
                  if (result != ChatMessageResult.PASS) {
                    return result;
                  }
                }
                return ChatMessageResult.PASS;
              });

  public static final Event<OutgoingChatListener> OUTGOING_CHAT =
      EventFactory.createArrayBacked(
          OutgoingChatListener.class,
          (listeners) ->
              (context) -> {
                for (OutgoingChatListener listener : listeners) {
                  ChatMessageResult result = listener.onOutgoingChat(context);
                  if (result != ChatMessageResult.PASS) {
                    return result;
                  }
                }
                return ChatMessageResult.PASS;
              });

  public static boolean isAvailable() {
    return MonkeycraftApiRegistration.isAvailable();
  }

  public static void setTimedNotification(
      Long fireAtEpochMs, String title, String body, boolean sound, String countDownText) {
    MonkeycraftApiProvider p = MonkeycraftApiRegistration.getProvider();
    if (p != null) {
      p.setTimedNotification(fireAtEpochMs, title, body, sound, countDownText);
    }
  }

  public static void cancelTimedNotification() {
    MonkeycraftApiProvider p = MonkeycraftApiRegistration.getProvider();
    if (p != null) {
      p.cancelTimedNotification();
    }
  }

  public static void sendImmediateNotification(String title, String body, boolean sound) {
    MonkeycraftApiProvider p = MonkeycraftApiRegistration.getProvider();
    if (p != null) {
      p.sendImmediateNotification(title, body, sound);
    }
  }

  public static void startHibernation(String message) {
    MonkeycraftApiProvider p = MonkeycraftApiRegistration.getProvider();
    if (p != null) {
      p.startHibernation(message);
    }
  }

  public static void setHibernationMessage(String message) {
    MonkeycraftApiProvider p = MonkeycraftApiRegistration.getProvider();
    if (p != null) {
      p.setHibernationMessage(message);
    }
  }

  public static void endHibernation() {
    MonkeycraftApiProvider p = MonkeycraftApiRegistration.getProvider();
    if (p != null) {
      p.endHibernation();
    }
  }

  public static Boolean isClientConnected() {
    MonkeycraftApiProvider p = MonkeycraftApiRegistration.getProvider();
    return p != null ? p.isClientConnected() : null;
  }

  public static Boolean isHibernating() {
    MonkeycraftApiProvider p = MonkeycraftApiRegistration.getProvider();
    return p != null ? p.isHibernating() : null;
  }

  public static Boolean isServerStarted() {
    MonkeycraftApiProvider p = MonkeycraftApiRegistration.getProvider();
    return p != null ? p.isServerStarted() : null;
  }
}
