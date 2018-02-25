package com.transferwise.service;

import com.transferwise.constants.GameResult;
import com.transferwise.constants.Move;
import com.transferwise.constants.RoundResult;
import com.transferwise.domain.ComputerPlayer;
import com.transferwise.domain.Game;
import com.transferwise.domain.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {
  public static final String GAME_OVER_NO_NEW_MOVES_ALLOWED_START_NEW_GAME = "Game over. No new moves allowed. Start new game";
  private ComputerPlayer cPlayer;
  private Game currentGame;

  @Autowired
  public GameService(ComputerPlayer cPlayer) {
    this.cPlayer = cPlayer;
    currentGame = new Game();
  }

  public Game playNewRound(Move playerMove) {
    if(!currentGame.result().equals(GameResult.UNDECIDED)) {
      throw new IllegalStateException(GAME_OVER_NO_NEW_MOVES_ALLOWED_START_NEW_GAME);
    }
    Round round = new Round(cPlayer);

    RoundResult roundResult = round.play(playerMove);

    return currentGame.addRoundResult(roundResult);
  }

  public Game newGame() {
    return currentGame.reset();
  }

  public Game getCurrentGame() {
    return currentGame;
  }
}
