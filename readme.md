To run:

- mvn spring-boot:run

Assumptions

- Only one game is playable at a time

Routes

- POST /games/new
Creates a new game in memory. Resets the Game object to have empty values for player scores and rest of the shit.
Returns:

StatusCode: 201
```json 
{
    "playerScore": 0,
    "computerScore": 0,
    "gameResult": "UNDECIDED",
    "lastRoundResult": null,
    "roundResults": []
}
```

GET /games/current-game
StatusCode: 200

Returns: 
```json
{
    "playerScore": 0,
    "computerScore": 0,
    "gameResult": "UNDECIDED",
    "lastRoundResult": null,
    "roundResults": []
}
```

POST /games/current-game/moves/new
StatusCode: 201
Request Body:
```json
{
	"playerMove": "ROCK"
}
```

Returns: 
```json
{
    "playerScore": 0,
    "computerScore": 0,
    "gameResult": "UNDECIDED",
    "lastRoundResult": "DRAW",
    "roundResults": [
        "DRAW"
    ]
}
```