package com.transferwise;

import com.transferwise.constants.RoundResult;
import com.transferwise.domain.ComputerPlayer;
import com.transferwise.domain.GameScore;
import com.transferwise.constants.Move;
import com.transferwise.domain.Round;

public class GameService {
  private ComputerPlayer cPlayer;
  private GameScore gameScore;

  public GameService(ComputerPlayer cPlayer) {
    this.cPlayer = cPlayer;
    gameScore = new GameScore();
  }

  public GameScore playNewRound(Move playerMove) {
    Round round = new Round(cPlayer);

    RoundResult roundResult = round.play(playerMove);

    return gameScore.addRoundResult(roundResult);
  }
}
