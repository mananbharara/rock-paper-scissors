package com.transferwise.controller;

import com.transferwise.constants.Move;
import com.transferwise.domain.Game;
import com.transferwise.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class GameController {

  private GameService gameService;

  @Autowired
  public GameController(GameService gameService) {
    this.gameService = gameService;
  }

  //TODO: Remove
  @RequestMapping(value = "/computerMove", method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  public String getHello(HttpServletRequest request, HttpServletResponse response) {
    return gameService.getComputerMove();
  }

  public Game createGame() {
    return gameService.newGame();
  }

  public Game getCurrentGame() {
    return gameService.getCurrentGame();
  }

  public Game playRound(Move playerMove) {
    return gameService.playNewRound(playerMove);
  }
}
