package org.example.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.exceptions.InvalidMoveException;
import org.example.strategies.WinningStrategy;

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
    private List<WinningStrategy> winningStrategies;

    public static Builder builder() {
        return new Builder();
    }

    private Game(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) {
        this.board = new Board(dimension);
        this.players = players;
        this.moves = new ArrayList<>();
        this.status = GameStatus.IN_PROGRESS;
        this.nextPlayerIndex = 0;
        this.winningStrategies = winningStrategies;
    }

    public void printBoard() {
        board.printBoard();
    }

    public void makeMove() throws InvalidMoveException {
        Player currentPlayer = players.get(nextPlayerIndex);
        System.out.println("Current turn: " + currentPlayer.getName());
        // Move logic to be implemented

        Move move = currentPlayer.makeMove(board);
        if(!validateMove(move)) {
            System.out.println("Invalid move! Try again.");
            throw new InvalidMoveException("Invalid move attempted by player: " + currentPlayer.getName());
        }

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Cell cell = board.getBoardCells().get(row).get(col);
        cell.setPlayer(currentPlayer);
        cell.setStatus(CellStatus.FILLED);

        moves.add(move);
        nextPlayerIndex = (nextPlayerIndex + 1) % players.size();

        // Check Winner
        if(checkWinner(move)) {
            this.winner = currentPlayer;
            this.status = GameStatus.WIN;
        } else if(moves.size() == board.getDimension() * board.getDimension()) {
            this.status = GameStatus.DRAW;
        }
    }

    private boolean checkWinner(Move move) {
        for(WinningStrategy winningStrategy : winningStrategies) {
            if(winningStrategy.checkWinner(board, move)) {
                return true;
            }
        }
        return false;
    }

    private boolean validateMove(Move move) {
        Cell cell = move.getCell();

        int row = cell.getRow();
        int col = cell.getCol();

        if(row < 0 || row >= board.getDimension() ||
           col < 0 || col >= board.getDimension() ||
           board.getBoardCells().get(row).get(col).getStatus() != CellStatus.EMPTY) {
            return false;
        }
        return true;
    }

    public static class Builder {
        private int dimension;
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;

        public Builder dimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder players(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder winningStrategies(List<WinningStrategy> strategies) {
            this.winningStrategies = strategies;
            return this;
        }

        public Game build() {
            validateBotCount();
            return new Game(dimension, players, winningStrategies);
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
