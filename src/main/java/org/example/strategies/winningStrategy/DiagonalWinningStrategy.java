package org.example.strategies.winningStrategy;

import org.example.models.Board;
import org.example.models.Move;
import org.example.models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class DiagonalWinningStrategy implements WinningStrategy{

    private Map<Symbol, Integer> leftDiagMap = new HashMap<>();
    private Map<Symbol, Integer> rightDiagMap = new HashMap<>();

    @Override
    public boolean checkWinner(Move move, Board board) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        Symbol symbol = move.getPlayer().getSymbol();

        // Left Diagonal
        if(row == col) {
            if(!leftDiagMap.containsKey(symbol)) {
                leftDiagMap.put(symbol, 0);
            }

            leftDiagMap.put(symbol, leftDiagMap.get(symbol) + 1);

            if(leftDiagMap.get(symbol) == board.getDimension()) {
                return true;
            }
        }

        // Right Diagonal
        if(row + col == board.getDimension() - 1) {
            if(!rightDiagMap.containsKey(symbol)) {
                rightDiagMap.put(symbol, 0);
            }

            rightDiagMap.put(symbol, rightDiagMap.get(symbol) + 1);

            return rightDiagMap.get(symbol) == board.getDimension();
        }

        return false;
    }
}
