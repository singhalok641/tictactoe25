package org.example;

import org.example.controllers.GameController;
import org.example.exceptions.InvalidBotCountException;
import org.example.exceptions.InvalidMoveException;
import org.example.models.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InvalidBotCountException, InvalidMoveException {
        System.out.println("=========== Welcome to Tic Tac Toe =========");

        // Gateway to talk to the tic tac toe service
        GameController gameController = new GameController();

        int dimension = 3;
        List<Player> players = new ArrayList<>();
        players.add(new Player(
                "Abhishek",
                new Symbol('X'))
        );
        players.add(new Bot("GPT Player",
                new Symbol('O'),
                BotDifficultyLevel.MEDIUM)
        );

        Game game = gameController.startGame(players, dimension);

        while (gameController.getGameState(game) == GameState.IN_PROGRESS) {
            // Display the board
            gameController.displayBoard(game);

            // Make move
            gameController.makeMove(game);
        }



        // check if win or draw
        // if win -> display the winner
        if(gameController.getGameState(game) == GameState.ENDED) {
            gameController.displayBoard(game);
            System.out.println("The winner is: " + gameController.getWinner(game).getName());
        } else {
            // DRAW
            System.out.println("It's a DRAW. No one won the game. Play again? ");
        }


        // startGame
        // makeMove
        // displayBoard
        // displayWinner
        // checkGameStatus
        // checkWinner

    }
}

// MVC - Model View Controller?
// Controller -> Service -> Repository -> DB
// We do not have DB
//
// Game Controller -> Tic Tac toe Service