package org.academiadecodigo.felinux.GameObjects;

import org.academiadecodigo.felinux.Grid.Position;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public abstract class GameObject {

    private Position position;
    private Picture image;

    public GameObject(Position position, Picture image) {
        this.position = position;
        this.image = image;
    }

    public Picture getImage() {
        return image;
    }

    public Position getPosition() {
        return position;
    }

    public void setImage(Picture image) {
        this.image = image;
    }
}
