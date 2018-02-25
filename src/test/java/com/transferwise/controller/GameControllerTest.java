package com.transferwise.controller;

import com.transferwise.constants.GameResult;
import com.transferwise.constants.Move;
import com.transferwise.constants.RoundResult;
import com.transferwise.domain.Game;
import com.transferwise.service.GameService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.transferwise.controller.GameController.INVALID_MOVE;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameControllerTest {

  private GameService gameService;
  private GameController gameController;

  @Before
  public void setUp() {
    gameService = mock(GameService.class);
    gameController = new GameController(gameService);
  }

  @Test
  public void shouldCreateNewGame() {
    Game newGame = mock(Game.class);
    when(newGame.result()).thenReturn(GameResult.UNDECIDED);
    when(gameService.newGame()).thenReturn(newGame);

    ResponseEntity<Map<String, Object>> responseEntity = gameController.createGame();

    assertThat(responseEntity.getStatusCode(), is(HttpStatus.CREATED));
    assertThat(responseEntity.getBody().get("gameResult"), is(GameResult.UNDECIDED.name()));
  }

  @Test
  public void shouldGetCurrentGameStats() {
    Game currentGame = mock(Game.class);
    when(currentGame.result()).thenReturn(GameResult.WIN);
    when(gameService.getCurrentGame()).thenReturn(currentGame);

    ResponseEntity<Map<String, Object>> responseEntity = gameController.getCurrentGame();

    assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
    assertThat(responseEntity.getBody().get("gameResult"), is(GameResult.WIN.name()));
  }

  @Test
  public void shouldPlayNewRoundWithPlayerMove() {
    Game updatedCurrentGame = mock(Game.class);
    when(updatedCurrentGame.lastRoundResult()).thenReturn(Optional.of(RoundResult.LOSS));
    when(updatedCurrentGame.result()).thenReturn(GameResult.LOSS);
    when(gameService.playNewRound(Move.PAPER)).thenReturn(updatedCurrentGame);

    Map<String, String> playerMove = new HashMap<String, String>() {{
      put("playerMove", Move.PAPER.name());
    }};

    ResponseEntity<Map<String, Object>> responseEntity = gameController.playRound(playerMove);

    assertThat(responseEntity.getStatusCode(), is(HttpStatus.CREATED));
    assertThat(responseEntity.getBody().get("lastRoundResult"), is(RoundResult.LOSS));
    assertThat(responseEntity.getBody().get("gameResult"), is(GameResult.LOSS));
  }

  @Test
  public void shouldReturnErrorIfGameDoesntAllowMoves() {
    String errorMsg = "error";
    IllegalStateException error = new IllegalStateException(errorMsg);

    Map<String, String> playerMove = new HashMap<String, String>() {{
      put("playerMove", Move.PAPER.name());
    }};

    when(gameService.playNewRound(Move.PAPER)).thenThrow(error);

    ResponseEntity<Map<String, Object>> response = gameController.playRound(playerMove);

    assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    assertThat(response.getBody().get("error"), is(errorMsg));
  }

  @Test
  public void shouldReturnErrorIfInvalidMove() {
    Map<String, String> playerMove = new HashMap<String, String>() {{
      put("playerMove", "LAVA");
    }};

    ResponseEntity<Map<String, Object>> response = gameController.playRound(playerMove);

    assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    assertThat(response.getBody().get("error"), is(INVALID_MOVE));
  }
}
