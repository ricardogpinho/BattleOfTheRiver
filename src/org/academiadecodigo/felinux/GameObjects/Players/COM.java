package org.academiadecodigo.felinux.GameObjects.Players;

import org.academiadecodigo.felinux.Game;
import org.academiadecodigo.felinux.GameObjects.GameObject;
import org.academiadecodigo.felinux.GameObjects.Projectiles.Arrow;
import org.academiadecodigo.felinux.GameObjects.Projectiles.Bullet;
import org.academiadecodigo.felinux.GameObjects.Projectiles.Projectile;
import org.academiadecodigo.felinux.Grid.Battleground;
import org.academiadecodigo.felinux.Grid.FieldType;
import org.academiadecodigo.felinux.Grid.Position;
import org.academiadecodigo.felinux.Utils.Utils;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class COM extends Character{

    private int delayCounter;
    private int random;

    public COM(CharacterType character, FieldType field) {
        super(character, field);
        //image.draw();
        this.delayCounter = 3;
    }

    @Override
    public void move(GameObject[] objects) {
        random = Utils.Randomizer(4);
        if(delayCounter>0){delayCounter--;return;}
        switch(random){
            case 1:if(super.getPosition().getRow()> super.getField().getMinRow()){
                for(GameObject object : objects) {
                    if ((object.getPosition().getColumn() == this.getPosition().getColumn()) && (object.getPosition().getRow() == (this.getPosition().getRow() - 1))) {
                        return;}}
                    super.getPosition().moveUp(); super.getImage().translate(0,-Battleground.CELL_SIZE);}break;

            case 2:if(super.getPosition().getRow()<super.getField().getMaxRow()){
                for(GameObject object : objects) {
                    if ((object.getPosition().getColumn() == this.getPosition().getColumn()) && (object.getPosition().getRow() == (this.getPosition().getRow() + 1))) {
                        return; } }
                   super.getPosition().moveDown(); super.getImage().translate(0, Battleground.CELL_SIZE);} break;

            case 3:if(super.getPosition().getColumn()>super.getField().getMinCol()){
                for(GameObject object : objects) {
                    if ((object.getPosition().getColumn() == this.getPosition().getColumn() - 1) && (object.getPosition().getRow() == (this.getPosition().getRow()))) {
                        return; } }
                   super.getPosition().moveLeft(); super.getImage().translate(-Battleground.CELL_SIZE,0);} break;

            case 4:if(super.getPosition().getColumn()<super.getField().getMaxCol()){
                for(GameObject object : objects) {
                    if ((object.getPosition().getColumn() == this.getPosition().getColumn() + 1) && (object.getPosition().getRow() == (this.getPosition().getRow()))) {
                        return; } }
                   super.getPosition().moveRight(); super.getImage().translate(Battleground.CELL_SIZE,0);} break;

        }
        delayCounter= 3;
    }
    @Override
    public Projectile shoot() {

        if(isDead() == false) {
            switch (getWeapon()) {
                case BOW:
                    return new Arrow(new Position(super.getPosition().getColumn(), super.getPosition().getRow()), new Picture((super.getPosition().getColumn() - 1) * Battleground.CELL_SIZE, super.getPosition().getRow() * Battleground.CELL_SIZE+10, "arrows/arrow7.field2.png"), getField());
                case GUN:
                    return new Bullet(new Position(super.getPosition().getColumn(), super.getPosition().getRow()), new Picture((super.getPosition().getColumn() - 1) * Battleground.CELL_SIZE, super.getPosition().getRow() * Battleground.CELL_SIZE, "arrows/arrow5.field2.png"), getField());
            }
        }
        return null;
    }
}
