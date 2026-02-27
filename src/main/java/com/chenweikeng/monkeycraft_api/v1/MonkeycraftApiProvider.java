package com.chenweikeng.monkeycraft_api.v1;

public interface MonkeycraftApiProvider {
  void setTimedNotification(
      Long fireAtEpochMs, String title, String body, boolean sound, String countDownText);

  void cancelTimedNotification();

  void sendImmediateNotification(String title, String body, boolean sound);

  void startHibernation(String message);

  void setHibernationMessage(String message);

  void endHibernation();

  boolean isClientConnected();

  boolean isHibernating();

  boolean isServerStarted();
}
