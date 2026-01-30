package org.example.strategies;

import org.example.models.Board;
import org.example.models.Move;

public interface WinningStrategy {
    boolean checkWinner(Board board, Move move);
}
