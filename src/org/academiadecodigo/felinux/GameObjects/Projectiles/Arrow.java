package org.academiadecodigo.felinux.GameObjects.Projectiles;

import org.academiadecodigo.felinux.Game;
import org.academiadecodigo.felinux.Grid.Battleground;
import org.academiadecodigo.felinux.Grid.FieldType;
import org.academiadecodigo.felinux.Grid.Position;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Arrow extends Projectile {


    public Arrow(Position position, Picture image, FieldType field) {
        super(position, image, field, 3);
        image.draw();
    }

    /**
     * Test for arrow movement, needs to know where the end of the map is
     */
    @Override
    public void move() {

        switch(getField()) {
            case FIELD1:
                if (getPosition().getColumn() >= (Battleground.column - 1)) {
                    getImage().delete();
                    System.out.println("removed");
                    Game.projectilesList.remove(this);
                    return;
                }
                getPosition().moveRight();
                System.out.println("right");
                System.out.println("outro");
                getImage().translate(32, 0);
                break;

            case FIELD2:
                if (getPosition().getColumn() <= 0) {
                    getImage().delete();
                    System.out.println("removed");
                    Game.projectilesList.remove(this);
                    return;
                }
                getPosition().moveLeft();
                System.out.println("left");
                getImage().translate(-32, 0);
                break;
        }
    }


}
