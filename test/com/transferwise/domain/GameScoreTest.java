package com.transferwise.domain;

import com.transferwise.constants.GameResult;
import org.junit.Test;

import static com.transferwise.constants.RoundResult.LOSS;
import static com.transferwise.constants.RoundResult.WIN;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class GameScoreTest {

  @Test
  public void shouldGetLastRoundResult() {
    GameScore gameScore = new GameScore();

    gameScore.getRoundResults().add(LOSS);
    gameScore.getRoundResults().add(WIN);

    assertThat(gameScore.lastRoundResult(), is(WIN));
  }

  @Test
  public void shouldUpdateScoreWithLastRoundResult() {
    GameScore gameScore = new GameScore();

    gameScore.addRoundResult(LOSS);
    gameScore.addRoundResult(LOSS);
    gameScore.addRoundResult(WIN);

    assertThat(gameScore.getRoundResults(), is(asList(LOSS, LOSS, LOSS)));
  }

  @Test
  public void shouldKeepScores() {
    GameScore gameScore = new GameScore();

    gameScore.addRoundResult(LOSS);
    gameScore.addRoundResult(LOSS);
    gameScore.addRoundResult(WIN);

    assertThat(gameScore.playerScore(), is(1L));
    assertThat(gameScore.computerScore(), is(2L));
  }

  @Test
  public void shouldDecideIfGameIsWon() {
    GameScore gameScore = new GameScore();

    gameScore.addRoundResult(LOSS);
    gameScore.addRoundResult(LOSS);
    gameScore.addRoundResult(WIN);
    gameScore.addRoundResult(LOSS);

    assertThat(gameScore.result(), is(GameResult.LOSS));
  }

  @Test
  public void shouldReturnUndecidedIfGameUndecided() {
    GameScore gameScore = new GameScore();

    gameScore.addRoundResult(LOSS);
    gameScore.addRoundResult(LOSS);
    gameScore.addRoundResult(WIN);
    gameScore.addRoundResult(WIN);

    assertThat(gameScore.result(), is(GameResult.UNDECIDED));
  }
}