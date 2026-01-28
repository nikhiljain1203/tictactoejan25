package org.example.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Cell {
    private int row;
    private int col;
    private Player player;
    private CellStatus status;

    public Cell(int i, int j) {
        this.row = i;
        this.col = j;
        this.status = CellStatus.EMPTY;
    }
}
