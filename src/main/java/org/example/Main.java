package org.example;

import org.example.controllers.GameController;
import org.example.exceptions.InvalidBotCountException;
import org.example.exceptions.InvalidMoveException;
import org.example.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InvalidBotCountException, InvalidMoveException {
        System.out.println("=========== Welcome to Tic Tac Toe =========");

        // Gateway to talk to the tic tac toe service
        GameController gameController = new GameController();

        Scanner in = new Scanner(System.in);
        System.out.println("What should be the dimension of the game?");

        int dimension = in.nextInt();

        System.out.println("Will there be any bot in this game? y/n");
        String isBotString = in.next();
        List<Player> players = new ArrayList<>();

        int toIterate = dimension - 1;

        if (isBotString.equals("y")) {
            toIterate = dimension - 2;
        }

        for(int i=0; i<toIterate; i++) {
            System.out.println("What is the name of the player " + (i+1));
            String playerName = in.next();

            System.out.println("What is the symbol for the player " + (i+1));
            String playerSymbol = in.next();

            players.add(new Player(playerName, new Symbol(playerSymbol.charAt(0))));
        }

        if (isBotString.equals("y")) {
            System.out.println("What is the name of the Bot?");
            String botName = in.next();

            System.out.println("What is the symbol for the Bot?");
            String botSymbol = in.next();

            players.add(new Bot(botName, new Symbol(botSymbol.charAt(0)), BotDifficultyLevel.EASY));
        }

        Game game = gameController.startGame(players, dimension);

        while (gameController.getGameState(game) == GameState.IN_PROGRESS) {
            // Display the board
            gameController.displayBoard(game);

            System.out.println("Does anyone want to undo? y/n");
            String input = in.next();

            if(input.equals("y")) {
                gameController.undo(game);
            } else {
                // Make move
                gameController.makeMove(game);
            }
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