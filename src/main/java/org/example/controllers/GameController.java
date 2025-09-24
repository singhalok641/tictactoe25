package org.example.controllers;

import org.example.exceptions.InvalidBotCountException;
import org.example.exceptions.InvalidMoveException;
import org.example.models.Game;
import org.example.models.GameState;
import org.example.models.Player;

import java.util.List;

public class GameController {

    public Game startGame(List<Player> players, int dimension) throws InvalidBotCountException {
        // Call the GameBuilder to start the game
        return Game.getBuilder()
                .setDimension(dimension)
                .setPlayers(players)
                .build();
    }

    public void makeMove(Game game) throws InvalidMoveException {
        game.makeMove();
    }

    public GameState getGameState(Game game) {
        return game.getGameState();
    }

    public void displayBoard(Game game) {
        game.displayBoard();
    }

    public Player getWinner() {
        return null;
    }
}
