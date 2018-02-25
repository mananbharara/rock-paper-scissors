package com.transferwise.domain;

import com.transferwise.constants.GameResult;
import com.transferwise.constants.RoundResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.transferwise.constants.RoundResult.LOSS;
import static com.transferwise.constants.RoundResult.WIN;

public class Game {
  public static final int GAMES_TO_WIN = 3;

  private List<RoundResult> roundResults;

  public Game() {
    this.roundResults = new ArrayList<>();
  }

  public List<RoundResult> getRoundResults() {
    return roundResults;
  }

  public Game addRoundResult(RoundResult roundResult) {
    roundResults.add(roundResult);

    return this;
  }

  public Optional<RoundResult> lastRoundResult() {
    if (roundResults.isEmpty()) {
      return Optional.empty();
    }

    return Optional.of(roundResults.get(roundResults.size() - 1));
  }

  public long playerScore() {
    return roundResults.stream().filter(r -> r.equals(WIN)).count();
  }

  public long computerScore() {
    return roundResults.stream().filter(r -> r.equals(LOSS)).count();
  }

  public GameResult result() {
    if (playerScore() == GAMES_TO_WIN) {
      return GameResult.WIN;
    }
    if (computerScore() == GAMES_TO_WIN) {
      return GameResult.LOSS;
    }

    return GameResult.UNDECIDED;
  }

  public Game reset() {
    this.roundResults = new ArrayList<>();
    return this;
  }
}
