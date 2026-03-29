package com.chenweikeng.monkeycraft_api.v1;

import com.google.gson.JsonObject;

@FunctionalInterface
public interface MonkeycraftInfoPacketListener {
    void onInfoPacket(String title, JsonObject payload);
}
