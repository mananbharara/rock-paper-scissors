package com.transferwise.domain;

import com.transferwise.constants.GameResult;
import com.transferwise.constants.RoundResult;

import java.util.ArrayList;
import java.util.List;

import static com.transferwise.constants.RoundResult.LOSS;
import static com.transferwise.constants.RoundResult.WIN;

public class GameScore {
  public static final int GAMES_TO_WIN = 3;

  private List<RoundResult> roundResults;

  public GameScore() {
    this.roundResults = new ArrayList<>();
  }

  public List<RoundResult> getRoundResults() {
    return roundResults;
  }

  public GameScore addRoundResult(RoundResult roundResult) {
    roundResults.add(roundResult);

    return this;
  }

  public RoundResult lastRoundResult() {
    return roundResults.get(roundResults.size() - 1);
  }

  public long playerScore() {
    return roundResults.stream().filter(r -> r.equals(WIN)).count();
  }

  public long computerScore() {
    return roundResults.stream().filter(r -> r.equals(LOSS)).count();
  }

  public GameResult result() {
    if(playerScore() == GAMES_TO_WIN) {
      return GameResult.WIN;
    }
    if(computerScore() == GAMES_TO_WIN) {
      return GameResult.LOSS;
    }

    return GameResult.UNDECIDED;
  }
}