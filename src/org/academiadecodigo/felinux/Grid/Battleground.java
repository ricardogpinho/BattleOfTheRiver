package org.academiadecodigo.felinux.Grid;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Battleground {

    private Picture map;
    public static final int CELL_SIZE = 32;
    public static final int column = 20;
    public static final int rows = 15;
    public static final int width = column * CELL_SIZE;
    public static final int height = rows * CELL_SIZE;

    /**
     * When a battleground is instanced , both fields are instanced.
     * PlayerOne will be on field1, and playerTwo will be on field2;
     */
    public Battleground() {
        init();
    }

    public void init() {

        map = new Picture(10, 10, "map-1.png");
        map.draw();
    }

    public int rowToY(int row) {
        return CELL_SIZE * row;
    }

    public int columnToX(int column) {
        return CELL_SIZE * column;
    }
}