package org.example.strategies;

import org.example.models.BotDiffcultyLevel;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getStrategyForLevel(BotDiffcultyLevel botDiffcultyLevel) {
        return switch (botDiffcultyLevel) {
            case EASY -> new EasyPlayingStrategy();
            case MEDIUM -> new MedPlayingStrategy();
            case HARD -> new HardPlayingStrategy();
            default -> throw new IllegalArgumentException("Unknown Bot Difficulty Level: " + botDiffcultyLevel);
        };
    }
}
