# Tic-Tac-Toe Game

This is a console-based Tic-Tac-Toe game implemented in Java. The game supports multiple players, bots, and customizable board dimensions. It is designed to be modular and extensible, making it a great project for practicing object-oriented programming and design patterns.

## Features

- **Customizable Board Dimensions**: Play on boards of any size (e.g., 3x3, 4x4, etc.).
- **Human and Bot Players**: Supports both human players and bots.
- **Undo Feature**: Undo the last move to correct mistakes.
- **Game-Winning Strategies**: Modular strategies for determining the winner.

## How to Run

1. Clone the repository:

```
git clone https://github.com/your-username/tictactoe25.git
```

2. Navigate to the project directory:

```
cd TicTacToe25
   ```

3. Build the project using Maven:

```
mvn clean install
 ```

4. Run the game:

```
java -jar target/tic-tac-toe.jar
```

## Enhancements to Practice

Here are some additional features you can implement to enhance the project and practice your coding skills:

### 1. Improve Validation Logic
- Ensure no two players have the same symbol.
- Allow only one bot in the game.
- Validate that the board dimension is within a reasonable range (e.g., 3 to 10).

### 2. Enhance Undo Feature
- Limit the number of undo operations allowed per player.
- Track the history of moves for each player.

### 3. Add Difficulty Levels for Bots
- Implement multiple difficulty levels (e.g., EASY, MEDIUM, HARD).
- Use strategies like random moves for EASY and Minimax for HARD.

### 4. Add Game Replay Feature
- Store the sequence of moves and allow users to replay the game step-by-step after it ends.

### 5. Improve User Interface
- Replace System.out.println with a more user-friendly console-based UI.
- Use colors or symbols to differentiate players and their moves.

### 6. Add Support for Multiple Game Modes
- Classic Tic-Tac-Toe (3x3).
- Custom dimensions (e.g., 4x4, 5x5).
- Timed mode where players must make a move within a time limit.

### 7. Introduce Game Persistence
- Save the game state to a file or database to allow resuming later.
- Serialize and deserialize the Game object.

### 8. Add Unit Tests
- Write unit tests for critical methods like `makeNextMove`, `undo`, and `checkWinner`.
- Use JUnit for testing.

### 9. Refactor Code for Better Design
- Use design patterns like:
    - Factory Pattern for creating players.
    - Strategy Pattern for game-winning strategies.
    - Command Pattern for undo/redo functionality.
- Move validation logic to a dedicated class.

### 10. Add Logging
- Use SLF4J or Log4j for better debugging and tracking.

### 11. Add Player Statistics
- Track and display statistics like:
    - Number of games played.
    - Number of wins, losses, and draws.
    - Win percentage.

### 12. Handle Edge Cases
- Handle invalid moves (e.g., selecting an already filled cell).
- Manage scenarios where players quit mid-game.

### 13. Add Support for Multiplayer Over Network
- Allow players to connect and play over a network using sockets or a REST API.

### 14. Improve Bot Decision-Making
- Use algorithms like Minimax or Alpha-Beta Pruning for smarter bot moves.

### 15. Add Game Timer
- Introduce a timer for each player's turn to make the game more challenging.

### 16. Add Observability
- Display the current score and leaderboard after each game.
- Show a summary of the game at the end.

## Contributing

Contributions are welcome! Feel free to fork the repository and submit a pull request with your improvements.

## License

This project is licensed under the MIT License. See the LICENSE file for details.