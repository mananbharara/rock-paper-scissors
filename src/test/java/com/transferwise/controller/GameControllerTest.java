package com.transferwise.controller;

import com.transferwise.constants.Move;
import com.transferwise.domain.Game;
import com.transferwise.service.GameService;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameControllerTest {

  private GameService gameService;
  private GameController gameController;

  @Before
  public void setUp() throws Exception {
    gameService = mock(GameService.class);
    gameController = new GameController(gameService);
  }

  @Test
  public void shouldCreateNewGame() {
    Game newGame = mock(Game.class);
    when(gameService.newGame()).thenReturn(newGame);

    Game game = gameController.createGame();

    assertThat(game, is(newGame));
  }

  @Test
  public void shouldGetCurrentGameStats() {
    Game currentGame = mock(Game.class);
    when(gameService.getCurrentGame()).thenReturn(currentGame);

    Game game = gameController.getCurrentGame();

    assertThat(game, is(currentGame));
  }

  @Test
  public void shouldPlayNewRoundWithPlayerMove() {
    Game updatedCurrentGame = mock(Game.class);
    when(gameService.playNewRound(Move.PAPER)).thenReturn(updatedCurrentGame);

    Game game = gameController.playRound(Move.PAPER);

    assertThat(game, is(updatedCurrentGame));
  }
}
