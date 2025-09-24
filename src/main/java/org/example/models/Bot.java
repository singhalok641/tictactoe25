package org.example.models;

public class Bot extends Player{
    private BotDifficultyLevel botDifficultyLevel;

    public Bot(String name,
            Symbol symbol,
            BotDifficultyLevel botDifficultyLevel) {
        super(name, symbol);
        this.botDifficultyLevel = botDifficultyLevel;
        this.setPlayerType(PlayerType.BOT);
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }
}
