package org.academiadecodigo.felinux.GameObjects.Players;

import org.academiadecodigo.felinux.Game;
import org.academiadecodigo.felinux.GameObjects.Decor.Tree;
import org.academiadecodigo.felinux.GameObjects.GameObject;
import org.academiadecodigo.felinux.GameObjects.Projectiles.Arrow;
import org.academiadecodigo.felinux.GameObjects.Projectiles.Bullet;
import org.academiadecodigo.felinux.GameObjects.Projectiles.Projectile;
import org.academiadecodigo.felinux.Grid.Battleground;
import org.academiadecodigo.felinux.Grid.FieldType;
import org.academiadecodigo.felinux.Grid.Position;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Player extends Character {

    private boolean isMoving;
    private DirectionType direction;
    private boolean isShooting;

    public Player(CharacterType character, FieldType field) {
        super(character, field);
        isMoving = false;
        //image.draw();
        direction = DirectionType.RIGHT;
        isShooting = false;

    }

    /**
     * Create a new object (bullet, eg), taking in consideration the position of the player in that moment
     */

    @Override
    public Projectile shoot() {
        if (!isDead()) {

            if (isShooting) {

                switch (super.getWeapon()) {
                    case BOW:
                        System.out.println("added arrow");
                        return new Arrow(new Position(super.getPosition().getColumn(), super.getPosition().getRow()), new Picture((super.getPosition().getColumn()) * Battleground.CELL_SIZE, super.getPosition().getRow() * Battleground.CELL_SIZE+10, "arrows/arrow1.field1.png"), getField());
                    case GUN:
                        System.out.println("added bullet");
                        return new Bullet(new Position(super.getPosition().getColumn(), super.getPosition().getRow()), new Picture(super.getPosition().getColumn() * Battleground.CELL_SIZE, super.getPosition().getRow() * Battleground.CELL_SIZE+10, "arrows/arrow2.field1.png"), getField());
                }
            }
        }
        return null;
    }


    @Override
    public void move(GameObject[] objects){

        if(isDead()) {
            return;
        }

        if(isMoving) {

            switch(direction) {
                case UP:
                    if(super.getPosition().getRow() > super.getField().getMinRow()) {
                        for(int i = 0 ; i < objects.length ; i++) {
                            if ((objects[i].getPosition().getColumn() == this.getPosition().getColumn()) && (objects[i].getPosition().getRow() == (this.getPosition().getRow() - 1))) {
                                return;
                            }
                        }
                        super.getPosition().moveUp();
                        System.out.println("up");
                        getImage().translate(0, -Battleground.CELL_SIZE);
                    }
                    break;

                case DOWN:
                    if(super.getPosition().getRow() < super.getField().getMaxRow()) {
                        for(GameObject object : objects) {
                            if ((object.getPosition().getColumn() == this.getPosition().getColumn()) && (object.getPosition().getRow() == (this.getPosition().getRow() + 1))) {
                                return;
                            }
                        }
                        super.getPosition().moveDown();
                        System.out.println("baixo");
                        getImage().translate(0, Battleground.CELL_SIZE);
                    }
                    break;

                case LEFT:
                    if(super.getPosition().getColumn() > super.getField().getMinCol()) {
                        for(GameObject object : objects) {
                            if ((object.getPosition().getColumn() == this.getPosition().getColumn() - 1) && (object.getPosition().getRow() == (this.getPosition().getRow()))) {
                                return;
                            }
                        }
                        super.getPosition().moveLeft();
                        System.out.println("left");
                        getImage().translate(-Battleground.CELL_SIZE, 0);
                    }
                    break;

                case RIGHT:
                    if(super.getPosition().getColumn() < super.getField().getMaxCol()) {
                        for(GameObject object : objects) {
                            if ((object.getPosition().getColumn() == this.getPosition().getColumn() + 1) && (object.getPosition().getRow() == (this.getPosition().getRow()))) {
                                return;
                            }
                        }
                        super.getPosition().moveRight();
                        System.out.println("right");
                        getImage().translate(Battleground.CELL_SIZE, 0);
                    }
                    break;
            }
        }
    }

    public void setShooting(boolean shooting) {
        isShooting = shooting;
    }

    public boolean isDead() {
        return super.isDead();
    }

    public void setMoving(boolean moving) {
        this.isMoving = true;
    }

    public void setDirection(DirectionType direction) {
        this.direction = direction;
    }

    public enum DirectionType {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

}
