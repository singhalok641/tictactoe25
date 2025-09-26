package org.example.strategies.winningStrategy;

import org.example.models.Board;
import org.example.models.Move;
import org.example.models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class ColWinningStrategy implements WinningStrategy{

    // This is a Map which stores the "symbol -> count" mapping for every col
    private Map<Integer, Map<Symbol, Integer>> colMaps = new HashMap<>();

    @Override
    public boolean checkWinner(Move move, Board board) {
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        if(!colMaps.containsKey(col)) {
            // This is the first move in this col, so we need to
            // initialise the colMap
            colMaps.put(col, new HashMap<>());
        }

        // Retrieving the same map we created first time
        Map<Symbol, Integer> currColMap = colMaps.get(col);

        if (!currColMap.containsKey(symbol)) {
            currColMap.put(symbol,0);
        }

        currColMap.put(symbol, currColMap.get(symbol) + 1);

        return currColMap.get(symbol) == board.getDimension();
    }
}
