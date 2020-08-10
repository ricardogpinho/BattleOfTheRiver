package org.academiadecodigo.felinux.GameObjects.Projectiles;

import org.academiadecodigo.felinux.GameObjects.GameObject;
import org.academiadecodigo.felinux.GameObjects.Movable;
import org.academiadecodigo.felinux.Grid.FieldType;
import org.academiadecodigo.felinux.Grid.Position;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public abstract class Projectile extends GameObject {

    private int speed;
    private FieldType field;

    public Projectile(Position position, Picture image, FieldType field, int speed) {
        super(position,image);
        this.speed = speed;
        this.field = field;
    }

    public int getSpeed() {
        return speed;
    }

    public void move() {}

    public FieldType getField() {
        return field;
    }
}
