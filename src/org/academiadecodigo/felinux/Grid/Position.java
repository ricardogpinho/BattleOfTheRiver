package org.academiadecodigo.felinux.Grid;

public class Position {

    private int column;
    private int row;

    public Position(int col, int row) {
        this.column = col;
        this.row = row;
    }

    public Position getPos() {
        return this;
    }

    public void setPos(int col, int row) {
        this.row = row;
        this.column = col;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void moveUp() {
        setPos(getColumn(), getRow() -1);
    }

    public void moveDown() {
        setPos(getColumn(), getRow() + 1);
    }

    public void moveLeft() {
        setPos(getColumn() - 1, getRow());
    }

    public void moveRight() {
        setPos(getColumn() + 1, getRow());
    }
}
