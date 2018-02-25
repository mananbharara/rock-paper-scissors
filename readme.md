To run:

- mvn spring-boot:run

Notes

- Only one game is playable at a time
- Java 8 is available

Routes

- POST /games/new
Creates a new game in memory. Resets the Game object.
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
Content-Type: application/json
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