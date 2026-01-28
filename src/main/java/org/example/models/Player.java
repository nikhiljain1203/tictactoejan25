package org.example.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {
    private String name;
    private Symbol symbol;
    private PlayerType type;

    public Player(String name, Symbol symbol) {
        this.name = name;
        this.symbol = symbol;
        this.type = PlayerType.HUMAN;
    }
}
