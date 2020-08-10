package org.academiadecodigo.felinux.Grid;

import static org.academiadecodigo.felinux.Grid.Battleground.CELL_SIZE;

public enum FieldType {

    /**
     * These numbers represent the limitations of each field, so the character doesn't go out of bonds
     */
    FIELD1(0, 0, Battleground.rows -1,Battleground.column-12, 0, 7),
    FIELD2(11, 0, Battleground.rows - 1, Battleground.column - 1, 19, 7);

    private final int initRow;
    private final int initCol;
    private final int minCol;
    private final int minRow;
    private final int maxRow;
    private final int maxCol;

    FieldType (int minCol, int minRow, int maxRow, int maxCol, int initCol, int initRow) {
        this.minCol = minCol;
        this.minRow = minRow;
        this.maxRow = maxRow;
        this.maxCol = maxCol;
        this.initCol = initCol;
        this.initRow = initRow;
    }

    public int getMinRow() {
        return minRow;
    }

    public int getMaxRow() {
        return maxRow;
    }

    public int getMaxCol() {
        return maxCol;
    }

    public int getMinCol() {
        return minCol;
    }

    public int rowToY(int row) {
        return CELL_SIZE * row;
    }

    public int columnToX(int column) {
        return CELL_SIZE * column;
    }

    public int getInitCol() {
        return initCol;
    }

    public int getInitRow() {
        return initRow;
    }
}
