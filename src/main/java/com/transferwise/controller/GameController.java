package com.transferwise.controller;

import com.transferwise.constants.Move;
import com.transferwise.domain.Game;
import com.transferwise.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class GameController {

  public static final String PLAYER_MOVE = "playerMove";
  public static final String INVALID_MOVE = "Invalid move";
  private GameService gameService;

  @Autowired
  public GameController(GameService gameService) {
    this.gameService = gameService;
  }

  @RequestMapping(value = "/games/new", method = RequestMethod.POST)
  public ResponseEntity<Map<String, Object>> createGame() {
    Game newGame = gameService.newGame();

    return getGameResponse(newGame, HttpStatus.CREATED);
  }

  @RequestMapping(value = "/games/current-game", method = RequestMethod.GET)
  public ResponseEntity<Map<String, Object>> getCurrentGame() {
    return getGameResponse(gameService.getCurrentGame(), HttpStatus.OK);
  }

  @RequestMapping(value = "/games/current-game/moves/new", method = RequestMethod.POST)
  public ResponseEntity<Map<String, Object>> playRound(@RequestBody Map<String, String> requestBody) {
    try {
      Game updatedGame = gameService.playNewRound(Move.valueOf(requestBody.get(PLAYER_MOVE)));
      return getGameResponse(updatedGame, HttpStatus.CREATED);
    } catch (IllegalStateException e) {
      return getErrorResponse(e.getMessage());
    } catch (IllegalArgumentException e) {
      return getErrorResponse(INVALID_MOVE);
    }
  }

  private ResponseEntity<Map<String, Object>> getGameResponse(Game game, HttpStatus statusCode) {
    Map<String, Object> gameResponse = new HashMap<>();
    gameResponse.put("gameResult", game.result());
    gameResponse.put("lastRoundResult", game.lastRoundResult().orElse(null));
    gameResponse.put("playerScore", game.playerScore());
    gameResponse.put("computerScore", game.computerScore());
    gameResponse.put("roundResults", game.getRoundResults());

    return new ResponseEntity<>(gameResponse, statusCode);
  }

  private ResponseEntity<Map<String, Object>> getErrorResponse(final String errorMessage) {
    Map<String, Object> errorBody = new HashMap<String, Object>() {{
      put("error", errorMessage);
    }};

    return ResponseEntity.badRequest().body(errorBody);
  }
}
