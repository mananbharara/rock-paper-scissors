package com.transferwise;

import com.transferwise.constants.GameResult;
import com.transferwise.domain.ComputerPlayer;
import com.transferwise.domain.GameScore;
import org.junit.Test;

import static com.transferwise.constants.Move.PAPER;
import static com.transferwise.constants.Move.ROCK;
import static com.transferwise.constants.Move.SCISSOR;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameServiceTest {

  @Test
  public void shouldReturnScoreOfGame() {
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
    GameScore score = gameService.playNewRound(SCISSOR);

    assertThat(score.result(), is(GameResult.WIN));
  }
}
