package com.transferwise;

import com.transferwise.domain.ComputerPlayer;
import com.transferwise.domain.Move;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameTest {
  @Test
  public void shouldReturnTrueIfPlayerMoveRockAndComputerMoveScissor() {
    ComputerPlayer cPlayer = mock(ComputerPlayer.class);
    when(cPlayer.move()).thenReturn(Move.SCISSOR);

    Game game = new Game(cPlayer);

    assertTrue(game.play(Move.ROCK));
  }

  @Test
  public void shouldReturnFalseIfPlayerMovePaperAndComputerMoveScissor() {
    ComputerPlayer cPlayer = mock(ComputerPlayer.class);
    when(cPlayer.move()).thenReturn(Move.SCISSOR);

    Game game = new Game(cPlayer);

    assertFalse(game.play(Move.PAPER));
  }
}
