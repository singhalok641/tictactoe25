package org.example.strategies.botPlayingStrategy;

import org.example.models.*;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Move makeMove(Board board, Player player) {
        // Bot is going to iterate over the board
        // And pick the first empty cell and make the move there
        for (int i=0; i<board.getDimension(); i++) {
            for (int j=0; j< board.getDimension(); j++) {
                Cell cell = board.getBoard().get(i).get(j);
                if(cell.getCellState() == CellState.EMPTY){
                    return new Move(cell, player);
                }
            }
        }
        return null;
    }
}
