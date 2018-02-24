package com.transferwise.domain;

import com.transferwise.constants.GameResult;
import org.junit.Test;

import static com.transferwise.constants.RoundResult.LOSS;
import static com.transferwise.constants.RoundResult.WIN;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class GameTest {

  @Test
  public void shouldGetLastRoundResult() {
    Game game = new Game();

    game.getRoundResults().add(LOSS);
    game.getRoundResults().add(WIN);

    assertThat(game.lastRoundResult(), is(WIN));
  }

  @Test
  public void shouldUpdateScoreWithLastRoundResult() {
    Game game = new Game();

    game.addRoundResult(LOSS);
    game.addRoundResult(LOSS);
    game.addRoundResult(WIN);

    assertThat(game.getRoundResults(), is(asList(LOSS, LOSS, LOSS)));
  }

  @Test
  public void shouldKeepScores() {
    Game game = new Game();

    game.addRoundResult(LOSS);
    game.addRoundResult(LOSS);
    game.addRoundResult(WIN);

    assertThat(game.playerScore(), is(1L));
    assertThat(game.computerScore(), is(2L));
  }

  @Test
  public void shouldDecideIfGameIsWon() {
    Game game = new Game();

    game.addRoundResult(LOSS);
    game.addRoundResult(LOSS);
    game.addRoundResult(WIN);
    game.addRoundResult(LOSS);

    assertThat(game.result(), is(GameResult.LOSS));
  }

  @Test
  public void shouldReturnUndecidedIfGameUndecided() {
    Game game = new Game();

    game.addRoundResult(LOSS);
    game.addRoundResult(LOSS);
    game.addRoundResult(WIN);
    game.addRoundResult(WIN);

    assertThat(game.result(), is(GameResult.UNDECIDED));
  }
}