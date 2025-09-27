package org.example.models;

import org.example.exceptions.InvalidBotCountException;
import org.example.exceptions.InvalidMoveException;
import org.example.strategies.winningStrategy.ColWinningStrategy;
import org.example.strategies.winningStrategy.DiagonalWinningStrategy;
import org.example.strategies.winningStrategy.RowWinningStrategy;
import org.example.strategies.winningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private GameState gameState;
    private List<Move> moves;
    private Player winner;
    private int nextPlayerMoveIndex;

    private List<WinningStrategy> winningStrategies;

    private Game(int dimension,
                List<Player> players,
                 List<WinningStrategy> winningStrategies) {
        this.board = new Board(dimension);
        this.players = players;
        this.gameState = GameState.IN_PROGRESS;
        this.moves = new ArrayList<>();
        this.nextPlayerMoveIndex = 0;
        this.winningStrategies = winningStrategies;
    }

    public Player getWinner() {
        return winner;
    }

    public GameState getGameState() {
        return gameState;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public void displayBoard() {
        this.board.displayBoard(new PlayerColorManager(players));
    }

    public void makeMove() throws InvalidMoveException {
        // Pick the player who needs to make the move
        Player currentPlayer = players.get(nextPlayerMoveIndex);

        System.out.println("This is " +
                currentPlayer.getName() +
                "'s move");

        Move move = currentPlayer.makeMove(board);

        // Game will validate the move
        if(!isMoveValid(move)) {
            System.out.println("This is not a valid cell");
            return;
        }

        Cell cell = move.getCell();
        if(!cell.isEmpty()) {
            System.out.println("This cell is not empty. Please try another cell");
            return;
        }

        // Make the move on the board
        cell.setCellState(CellState.FILLED);
        cell.setPlayer(currentPlayer);

        System.out.println(currentPlayer.getName() + " has made the move.");

        nextPlayerMoveIndex = (nextPlayerMoveIndex + 1) % players.size();

//        move.setCell(cell); // Imp: We set the real cell(the one the board)

        // Is this correct?
        // Does this move object contain the same
        // cell object that is present on the board? - No
        moves.add(move); // And then we add it to the movesList

        // check winner
        if(checkWinner(move)) {
            gameState = GameState.ENDED;
            winner = currentPlayer;
        } else if(moves.size() == board.getDimension() * board.getDimension()) {
            // DRAW
            gameState = GameState.DRAW;
        }
    }

    private boolean checkWinner(Move move) {
        for (WinningStrategy winningStrategy: winningStrategies) {
            if (winningStrategy.checkWinner(move, this.board)) {
                return true;
            }
        }

        return false;
    }

    private boolean isMoveValid(Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        // dimension == 3
        // rows = 0, 1, 2
        // cols = 0, 1, 2
        return row >= 0 &&
                row < this.board.getDimension() &&
                col >= 0 &&
                col < this.board.getDimension();
    }

    public void undo() {
        // implement undo
        // Check if we can even undo?
        if(moves.isEmpty()) {
            System.out.println("No moves to undo");
            return;
        }

        // Get the last move
        Move lastMove = moves.remove(moves.size()-1);

        // Reset the cell on the board
        Cell cell = lastMove.getCell();
        cell.setCellState(CellState.EMPTY);
        cell.setPlayer(null);

        // Update the next player index to the previous player
        nextPlayerMoveIndex = (nextPlayerMoveIndex - 1 + players.size()) % players.size();

        System.out.println("Last move is undone. It's now " + players.get(nextPlayerMoveIndex).getName() + "'s turn");

        // 0 1 2 3 4 5 => 3 + 6 = 9 % 6 = 3
        // Alok -> Shikha
    }

    public static class Builder {
        private int dimension;
        private List<Player> players;

        public int getDimension() {
            return dimension;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public List<Player> getPlayers() {
            return players;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        private void validateBotCount() throws InvalidBotCountException {
            // botCount == 1
            int botCount = 0;
            for (Player player: players) {
                if(player.getPlayerType() == PlayerType.BOT) {
                    botCount++;
                }
            }

            if(botCount > 1) {
                throw new InvalidBotCountException("There are more than one bots in the game");
            }
        }

        private void validatePlayerCounts() {

            // TODO:: Complete this exception
            // players count == dimension - 1
            // Complete first the requirements that are a must
        }

        private void validateDimensions() {
            // TODO:: Complete this exception
        }

        private void validateGame() throws InvalidBotCountException {
            validateDimensions();

            validatePlayerCounts();

            validateBotCount();

        }

        public Game build() throws InvalidBotCountException {
            // validations
            validateGame();

            // Currently we are hardcoding these
            List<WinningStrategy> winningStrategies1 = new ArrayList<>();
            winningStrategies1.add(new RowWinningStrategy());
            winningStrategies1.add(new ColWinningStrategy());
            winningStrategies1.add(new DiagonalWinningStrategy());

            return new Game(
                   dimension,
                   players,
                   winningStrategies1
            );
        }
    }
}

/*

<<GameRule>> -> rule()

RowWinningRule -> rowWin()

ColAndRowWinning -> rowWin() + colWin()

<<GameRule>> -> rule()

RowWinningRule -> rule()

ColWinningRule -> rule()

StandardTTT -> List<> -> RowWinningRule
ComplexTT -> List<> -> RowWinningRule + ColWinningRule
SuperComplexTT -> List<> -> DiagonalWinningRule

 */
