package org.example.models;

import org.example.factories.BotPlayingStrategyFactory;
import org.example.strategies.botPlayingStrategy.BotPlayingStrategy;

public class Bot extends Player{
    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(String name,
            Symbol symbol,
            BotDifficultyLevel botDifficultyLevel) {
        super(name, symbol);
        this.botDifficultyLevel = botDifficultyLevel;
        this.setPlayerType(PlayerType.BOT);
        this.botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategy(botDifficultyLevel);
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }

    public Move makeMove(Board board) {
        return botPlayingStrategy.makeMove(board, this);
    }
}
