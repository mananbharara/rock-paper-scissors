package com.transferwise;

import com.transferwise.domain.ComputerPlayer;
import com.transferwise.domain.Move;

public class Game {
  private final ComputerPlayer cPlayer;

  public Game(ComputerPlayer cPlayer) {
    this.cPlayer = cPlayer;
  }

  public Boolean play(Move playerMove) {
    return playerMove.beats(cPlayer.move());
  }
}
