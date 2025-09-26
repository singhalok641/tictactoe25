package org.example.strategies.winningStrategy;

import org.example.models.Board;
import org.example.models.Move;
import org.example.models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy{
    private Map<Integer, Map<Symbol, Integer>> rowMaps = new HashMap<>();
    @Override
    public boolean checkWinner(Move move, Board board) {
        int row = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();

        if(!rowMaps.containsKey(row)) {
            // This is the first move in this row, so we need to
            // initialise the rowMap
            rowMaps.put(row, new HashMap<>());
        }

        // Retrieving the same map we created first time
        Map<Symbol, Integer> currRowMap = rowMaps.get(row);

        if (!currRowMap.containsKey(symbol)) {
            currRowMap.put(symbol,0);
        }

        currRowMap.put(symbol, currRowMap.get(symbol) + 1);

        return currRowMap.get(symbol) == board.getDimension();
    }
}
