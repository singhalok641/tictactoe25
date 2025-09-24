package org.example.models;

import org.example.exceptions.InvalidBotCountException;
import org.example.exceptions.InvalidMoveException;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private GameState gameState;
    private List<Move> moves;
    private Player winner;
    private int nextPlayerMoveIndex;

    private Game(int dimension,
                List<Player> players) {
        this.board = new Board(dimension);
        this.players = players;
        this.gameState = GameState.IN_PROGRESS;
        this.moves = new ArrayList<>();
        this.nextPlayerMoveIndex = 0;
    }

    public GameState getGameState() {
        return gameState;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public void displayBoard() {
        this.board.displayBoard();
    }

    public void makeMove() throws InvalidMoveException {
        // Pick the player who needs to make the move
        Player currentPlayer = players.get(nextPlayerMoveIndex);

        System.out.println("This is " +
                currentPlayer.getName() +
                "'s move");

        Move move = currentPlayer.makeMove(board);

        // Game will validate the move
        if(!validateMove(move)) {
            throw new InvalidMoveException("Invalid Move!");
        }

        // Make the move on the board
        Cell cell = move.getCell();
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

    }

    private boolean validateMove(Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        // dimension == 3
        // rows = 0, 1, 2
        // cols = 0, 1, 2
        if(row < 0 ||
                row >= this.board.getDimension() ||
                col < 0 ||
                col >= this.board.getDimension()) {
            return false;
        }

        return board.getBoard().get(row).get(col).isEmpty();
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
            return new Game(
                   dimension,
                   players
            );
        }
    }
}
