package com.transferwise.service;

import com.transferwise.constants.Move;
import com.transferwise.constants.RoundResult;
import com.transferwise.domain.ComputerPlayer;
import com.transferwise.domain.Game;
import com.transferwise.domain.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {
  private ComputerPlayer cPlayer;
  private Game currentGame;

  @Autowired
  public GameService(ComputerPlayer cPlayer) {
    this.cPlayer = cPlayer;
    currentGame = new Game();
  }

  public Game playNewRound(Move playerMove) {
    Round round = new Round(cPlayer);

    RoundResult roundResult = round.play(playerMove);

    return currentGame.addRoundResult(roundResult);
  }

  public String getComputerMove() {
    return cPlayer.move().name();
  }

  public Game newGame() {
    return currentGame.reset();
  }

  public Game getCurrentGame() {
    return currentGame;
  }
}
