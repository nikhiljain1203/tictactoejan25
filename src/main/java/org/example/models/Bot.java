package org.example.models;

import lombok.Getter;
import lombok.Setter;
import org.example.strategies.BotPlayingStrategy;
import org.example.strategies.BotPlayingStrategyFactory;

@Getter
@Setter
public class Bot extends Player {
    private BotDiffcultyLevel botDiffcultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(String name,
               Symbol symbol,
               BotDiffcultyLevel botDiffcultyLevel) {
        super(name, symbol);
        this.botDiffcultyLevel = botDiffcultyLevel;
        this.setType(PlayerType.BOT);
        this.botPlayingStrategy =
                BotPlayingStrategyFactory.getStrategyForLevel(botDiffcultyLevel);

    }

    @Override
    public Move makeMove(Board board) {
        Move move =  botPlayingStrategy.makeMove(board);
        move.setPlayer(this);
        return move;
    }
}
