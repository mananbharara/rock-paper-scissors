package com.transferwise.domain;

import com.transferwise.component.ComputerPlayer;
import com.transferwise.constants.Move;
import com.transferwise.constants.RoundResult;

import java.util.HashMap;
import java.util.Map;

import static com.transferwise.constants.RoundResult.DRAW;
import static com.transferwise.constants.RoundResult.LOSS;
import static com.transferwise.constants.RoundResult.WIN;

public class Round {
  private final ComputerPlayer cPlayer;
  private static Map<Move, Move> BEATEN_MOVES_MAP;

  static {
    BEATEN_MOVES_MAP = new HashMap<Move, Move>() {{
      put(Move.ROCK, Move.SCISSOR);
      put(Move.SCISSOR, Move.PAPER);
      put(Move.PAPER, Move.ROCK);
    }};
  }

  public Round(ComputerPlayer cPlayer) {
    this.cPlayer = cPlayer;
  }

  public RoundResult play(Move playerMove) {
    Move computerMove = cPlayer.move();

    if (playerMove.equals(computerMove)) {
      return DRAW;
    }

    return BEATEN_MOVES_MAP.get(playerMove).equals(computerMove) ? WIN : LOSS;
  }
}
