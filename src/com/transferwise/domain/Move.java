package com.transferwise.domain;

public enum Move {
  SCISSOR,
  PAPER,
  ROCK;

  public Boolean beats(Move move) {
    if (this.equals(SCISSOR)) {
      return move.equals(PAPER);
    }
    if (this.equals(PAPER)) {
      return move.equals(ROCK);
    }
    return move.equals(SCISSOR);
  }
}
