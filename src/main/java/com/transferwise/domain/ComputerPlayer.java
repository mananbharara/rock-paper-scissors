package com.transferwise.domain;

import com.transferwise.constants.Move;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ComputerPlayer {
  public Move move() {
    Random random = new Random();

    return Move.values()[random.nextInt(Move.values().length)];
  }
}
