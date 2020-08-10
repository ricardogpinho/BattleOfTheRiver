package org.academiadecodigo.felinux.GameObjects.Decor;

import org.academiadecodigo.felinux.Grid.Battleground;
import org.academiadecodigo.felinux.Grid.Position;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class TreeFactory {

    private Position position;

    public Tree createTree(Position position, Tree.TreeType type) {
        this.position = position;
        return new Tree(position, imageFromType(type));

    }

    private Picture imageFromType(Tree.TreeType type) {
        switch (type) {
            case TREE1:
                return new Picture(position.getColumn() * Battleground.CELL_SIZE, position.getRow() + Tree.TreeType.TREE1.getRow(), Tree.TreeType.TREE1.getPath());
            case TREE2:
                return new Picture(position.getColumn() * Battleground.CELL_SIZE, position.getRow() + Tree.TreeType.TREE2.getRow(), Tree.TreeType.TREE2.getPath());
            case TREE3:
                return new Picture(position.getColumn() + Tree.TreeType.TREE3.getCol(), position.getRow() + Tree.TreeType.TREE3.getRow(), Tree.TreeType.TREE3.getPath());
        }
        return null;
    }
}
