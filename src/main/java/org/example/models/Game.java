package org.example.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
//@Builder
public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private Player winner;
    private GameStatus status;
    private int nextPlayerIndex;

    public static Builder builder() {
        return new Builder();
    }

    private Game(int dimension, List<Player> players) {
        this.board = new Board(dimension);
        this.players = players;
        this.moves = new ArrayList<>();
        this.status = GameStatus.IN_PROGRESS;
        this.nextPlayerIndex = 0;
    }

    public void printBoard() {
        board.printBoard();
    }

    public static class Builder {
        private int dimension;
        private List<Player> players;

        public Builder dimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder players(List<Player> players) {
            this.players = players;
            return this;
        }

        public Game build() {
            validateBotCount();
            return new Game(dimension, players);
        }

        private void validateBotCount() {
            int botCount = 0;
            for (Player player : players) {
                if (player.getType() == PlayerType.BOT) {
                    botCount++;
                }
            }
            if(botCount > 1) {
                throw new IllegalArgumentException("Only one bot player is allowed in the game.");
            }
        }
    }
}
