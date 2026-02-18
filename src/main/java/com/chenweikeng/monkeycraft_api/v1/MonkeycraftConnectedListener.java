package com.chenweikeng.monkeycraft_api.v1;

@FunctionalInterface
public interface MonkeycraftConnectedListener {
  void onConnected(String remoteAddress);
}
