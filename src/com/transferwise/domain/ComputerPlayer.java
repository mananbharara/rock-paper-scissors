package com.transferwise.domain;

import com.transferwise.constants.Move;

import java.util.Random;

public class ComputerPlayer {
  public Move move() {
    Random random = new Random();

    return Move.values()[random.nextInt(Move.values().length)];
  }
}
