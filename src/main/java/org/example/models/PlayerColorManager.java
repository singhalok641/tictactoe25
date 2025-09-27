package org.example.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerColorManager {
    private final Map<Player, String> playerColorMap;

    public PlayerColorManager(List<Player> players) {
        this.playerColorMap = new HashMap<>();
        initializePlayerColors(players);
    }

    private void initializePlayerColors(List<Player> players) {
        String[] colors = {"\u001B[31m", "\u001B[34m", "\u001B[32m", "\u001B[33m"};
        for (int i = 0; i < players.size(); i++) {
            playerColorMap.put(players.get(i), i < colors.length ? colors[i] : "\u001B[37m");
        }
    }

    public String getPlayerColor(Player player) {
        return playerColorMap.getOrDefault(player, "\u001B[37m"); // Default to white
    }
}
