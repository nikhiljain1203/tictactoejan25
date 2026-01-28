package org.example.controllers;

import org.example.models.Game;
import org.example.models.Player;

import java.util.List;

public class GameController {
    public Game startGame(int dimension, List<Player> players) {
        return Game.builder()
                .dimension(dimension)
                .players(players)
                .build();
    }

    public void printBoard(Game game) {
        game.printBoard();
    }
}
