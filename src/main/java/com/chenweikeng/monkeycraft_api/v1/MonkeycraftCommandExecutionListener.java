package com.chenweikeng.monkeycraft_api.v1;

@FunctionalInterface
public interface MonkeycraftCommandExecutionListener {
  CommandExecutionResult onCommandExecution(String command);
}
