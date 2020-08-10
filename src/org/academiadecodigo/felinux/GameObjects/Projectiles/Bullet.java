package org.academiadecodigo.felinux.GameObjects.Projectiles;

import org.academiadecodigo.felinux.Game;
import org.academiadecodigo.felinux.Grid.Battleground;
import org.academiadecodigo.felinux.Grid.FieldType;
import org.academiadecodigo.felinux.Grid.Position;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Bullet extends Projectile{

    public Bullet(Position position, Picture image, FieldType field) {
        super(position, image, field, 3);
        image.draw();
    }

    @Override
    public void move() {

        switch(getField()) {
            case FIELD1:
                if (getPosition().getColumn() >= (Battleground.column - 1)) {
                    getImage().delete();
                    Game.projectilesList.remove(this);
                    return;
                }
                getPosition().moveRight();
                getImage().translate(32, 0);
                break;

            case FIELD2:
                if (getPosition().getColumn() <= 0) {
                    getImage().delete();
                    Game.projectilesList.remove(this);
                    return;
                }
                getPosition().moveLeft();
                getImage().translate(-32, 0);
                break;
        }
    }
}
