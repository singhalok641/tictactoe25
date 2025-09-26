package org.example.factories;

import org.example.exceptions.InvalidBotDifficultyException;
import org.example.models.BotDifficultyLevel;
import org.example.strategies.botPlayingStrategy.BotPlayingStrategy;
import org.example.strategies.botPlayingStrategy.EasyBotPlayingStrategy;
import org.example.strategies.botPlayingStrategy.HardBotPlayingStrategy;
import org.example.strategies.botPlayingStrategy.MediumBotPlayingStrategy;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategy(BotDifficultyLevel level) {
        return switch (level) {
            case EASY -> new EasyBotPlayingStrategy();
            case MEDIUM -> new MediumBotPlayingStrategy();
            case HARD -> new HardBotPlayingStrategy();
        };
    }
}
