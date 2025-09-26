package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int dimension;
    private List<List<Cell>> board;

    public Board(int dimension) {
        this.dimension = dimension;
        this.board = new ArrayList<>();

        for (int i=0; i < dimension; i++) {
            board.add(new ArrayList<>());

            for (int j=0; j < dimension; j++) {
                board.get(i).add(new Cell(i, j));
            }
        }
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public List<List<Cell>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }

    public void displayBoard(List<Player> players) {
        for (List<Cell> row : board) {
            for (Cell cell : row) {
                if (cell.getCellState() == CellState.EMPTY) {
                    System.out.print("[ ] ");
                } else {
                    String color = getPlayerColor(cell.getPlayer(), players);
                    System.out.print(color + "[" + cell.getPlayer().getSymbol().getaChar() + "]\u001B[0m ");
                }
            }
            System.out.println();
        }
    }

    private String getPlayerColor(Player player, List<Player> players) {
        int playerIndex = players.indexOf(player);
        switch (playerIndex) {
            case 0: return "\u001B[31m"; // Red for Player 1
            case 1: return "\u001B[34m"; // Blue for Player 2
            case 2: return "\u001B[32m"; // Green for Player 3
            case 3: return "\u001B[33m"; // Yellow for Player 4
            default: return "\u001B[37m"; // White for others
        }
    }



}
