package com.transferwise.service;

import com.transferwise.constants.GameResult;
import com.transferwise.component.ComputerPlayer;
import com.transferwise.domain.Game;
import org.junit.Test;

import static com.transferwise.constants.Move.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameServiceTest {

  @Test
  public void shouldPlayRoundsAndReturnGameStats() {
    ComputerPlayer cPlayer = mock(ComputerPlayer.class);
    when(cPlayer.move())
      .thenReturn(SCISSOR)
      .thenReturn(ROCK)
      .thenReturn(SCISSOR)
      .thenReturn(PAPER);

    GameService gameService = new GameService(cPlayer);

    gameService.playNewRound(PAPER);
    gameService.playNewRound(PAPER);
    gameService.playNewRound(ROCK);
    Game score = gameService.playNewRound(SCISSOR);

    assertThat(score.result(), is(GameResult.WIN));
  }

  @Test(expected = IllegalStateException.class)
  public void shouldThrowErrorIfGameFinished() {
    ComputerPlayer cPlayer = mock(ComputerPlayer.class);
    when(cPlayer.move())
      .thenReturn(SCISSOR)
      .thenReturn(ROCK)
      .thenReturn(SCISSOR)
      .thenReturn(PAPER);

    GameService gameService = new GameService(cPlayer);

    gameService.playNewRound(PAPER);
    gameService.playNewRound(PAPER);
    gameService.playNewRound(ROCK);
    gameService.playNewRound(SCISSOR);

    gameService.playNewRound(ROCK);
  }

  @Test
  public void shouldGetCurrentGame() {
    ComputerPlayer cPlayer = mock(ComputerPlayer.class);
    when(cPlayer.move())
      .thenReturn(SCISSOR)
      .thenReturn(ROCK)
      .thenReturn(SCISSOR)
      .thenReturn(PAPER);

    GameService gameService = new GameService(cPlayer);

    gameService.playNewRound(PAPER);
    gameService.playNewRound(PAPER);
    gameService.playNewRound(ROCK);
    gameService.playNewRound(SCISSOR);

    assertThat(gameService.getCurrentGame().result(), is(GameResult.WIN));
  }

  @Test
  public void shouldResetGame() {
    ComputerPlayer cPlayer = mock(ComputerPlayer.class);
    when(cPlayer.move())
      .thenReturn(SCISSOR)
      .thenReturn(ROCK)
      .thenReturn(SCISSOR)
      .thenReturn(PAPER);

    GameService gameService = new GameService(cPlayer);

    gameService.playNewRound(PAPER);
    gameService.playNewRound(PAPER);
    gameService.playNewRound(ROCK);
    gameService.playNewRound(SCISSOR);

    Game newGame = gameService.newGame();

    assertThat(newGame.getRoundResults().size(), is(0));
    assertThat(newGame.result(), is(GameResult.UNDECIDED));
  }
}
