package org.example;

import org.example.controllers.GameController;
import org.example.models.*;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        int dimension = 3;
        System.out.println("Tic Tac Toe Game of dimension: " + dimension);

        List<Player> players = new ArrayList<>();
        players.add(new Player("Nikhil", new Symbol("X")));
        players.add(new Bot("Bot1", new Symbol("O"), BotDiffcultyLevel.EASY));

        GameController gameController = new GameController();

        Game game = gameController.startGame(dimension, players);

        System.out.println("Game started with ID: " + game.getStatus());
        gameController.printBoard(game);
    }
}