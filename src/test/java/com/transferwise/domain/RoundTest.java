package com.transferwise.domain;

import com.transferwise.component.ComputerPlayer;
import com.transferwise.constants.Move;
import com.transferwise.constants.RoundResult;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RoundTest {
  @Test
  public void shouldReturnWinIfPlayerMoveRockAndComputerMoveScissor() {
    ComputerPlayer cPlayer = mock(ComputerPlayer.class);
    when(cPlayer.move()).thenReturn(Move.SCISSOR);

    Round round = new Round(cPlayer);

    assertThat(round.play(Move.ROCK), is(RoundResult.WIN));
  }

  @Test
  public void shouldReturnLossIfPlayerMovePaperAndComputerMoveScissor() {
    ComputerPlayer cPlayer = mock(ComputerPlayer.class);
    when(cPlayer.move()).thenReturn(Move.SCISSOR);

    Round round = new Round(cPlayer);

    assertThat(round.play(Move.PAPER), is(RoundResult.LOSS));
  }

  @Test
  public void shouldReturnDrawIfBothPlayerAndComputerMovePaper() {
    ComputerPlayer cPlayer = mock(ComputerPlayer.class);
    when(cPlayer.move()).thenReturn(Move.PAPER);

    Round round = new Round(cPlayer);

    assertThat(round.play(Move.PAPER), is(RoundResult.DRAW));
  }
}
