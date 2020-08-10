package org.academiadecodigo.felinux.GameObjects.Decor;

import org.academiadecodigo.felinux.GameObjects.GameObject;
import org.academiadecodigo.felinux.Grid.Position;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Tree extends GameObject {


    public Tree(Position position, Picture image) {
        super(position, image);
    }

    public enum TreeType {
        TREE1("trees/tree1_5x2.png", 2 - 1, 5 - 1),
        TREE2("trees/tree2_4x3.png", 3 - 1, 4 - 1),
        TREE3("trees/tree5_4x3.png", 3 - 1, 4 - 1),
        TREE6("tree6_1x1.png", 0, 0);


        private final String path;
        private final int col;
        private final int row;

        TreeType(String path, int col, int row) {
            this.path = path;
            this.col = col;
            this.row = row;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public String getPath() {
            return path;
        }
    }
}

