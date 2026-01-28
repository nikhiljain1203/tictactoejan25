package org.example.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bot extends Player {
    private BotDiffcultyLevel botDiffcultyLevel;

    public Bot(String name,
               Symbol symbol,
               BotDiffcultyLevel botDiffcultyLevel) {
        super(name, symbol);
        this.botDiffcultyLevel = botDiffcultyLevel;
        this.setType(PlayerType.BOT);
    }
}
