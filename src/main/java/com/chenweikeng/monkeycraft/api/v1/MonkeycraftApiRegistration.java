package com.chenweikeng.monkeycraft.api.v1;

public final class MonkeycraftApiRegistration {
  private static MonkeycraftApiProvider provider = null;

  private MonkeycraftApiRegistration() {}

  public static void register(MonkeycraftApiProvider provider) {
    if (MonkeycraftApiRegistration.provider != null) {
      throw new IllegalStateException("MonkeycraftApiProvider already registered");
    }
    MonkeycraftApiRegistration.provider = provider;
  }

  public static void unregister() {
    provider = null;
  }

  public static MonkeycraftApiProvider getProvider() {
    return provider;
  }

  public static boolean isAvailable() {
    return provider != null;
  }
}
