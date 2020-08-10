package org.academiadecodigo.felinux.GameObjects.Players;

import org.academiadecodigo.felinux.GameObjects.GameObject;
import org.academiadecodigo.felinux.GameObjects.Movable;
import org.academiadecodigo.felinux.GameObjects.Projectiles.Arrow;
import org.academiadecodigo.felinux.GameObjects.Projectiles.Projectile;
import org.academiadecodigo.felinux.Grid.Battleground;
import org.academiadecodigo.felinux.Grid.FieldType;
import org.academiadecodigo.felinux.Grid.Position;
import org.academiadecodigo.felinux.Sound;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import static org.academiadecodigo.felinux.Grid.FieldType.FIELD1;
import static org.academiadecodigo.felinux.Grid.FieldType.FIELD2;

public abstract class Character extends GameObject implements Movable {

        private int health;
        private WeaponType weapon;
        private boolean dead;
        private FieldType field;
        private CharacterType character;
        private Object FieldType;

    public Character(CharacterType character, FieldType field) {
            super(new Position(field.getInitCol(), field.getInitRow()), new Picture(field.getInitCol() * Battleground.CELL_SIZE, field.getInitRow() * Battleground.CELL_SIZE,
                    (field == FIELD1 ? character.getImg1() : character.getImg2())));
            this.field = field;
            this.dead = false;
            this.health = 100;
            this.weapon = WeaponType.BOW;
            this.character = character;
            getImage().draw();
        }

        public FieldType getField() {
            return field;
        }

        public boolean isDead() {
            return dead;
        }

        public void setDead(boolean dead) {
            this.dead = dead;
        }

        public int getHealth() {
            return health;
        }

        public void setHealth(int health) {
            this.health = health;
        }

        public void setWeapon(WeaponType weapon) {
            this.weapon = weapon;
        }

        public WeaponType getWeapon() {
            return weapon;
        }

        public abstract void move(GameObject[] objects);

        public abstract Projectile shoot();

        public CharacterType getCharacter() {
            return character;
        }

        public void hit() {
            Sound hit = new Sound("/audio/arrowHit.wav");
            Sound deadSound = new Sound ("/audio/dead.wav");
            switch (weapon) {
                case BOW:
                    if (health <= weapon.getDamage()) {
                        health = 0;
                        dead = true;
                        deadSound.play(true);
                        getImage().delete();


                        if (field == FIELD1) {
                            setImage(new Picture(getPosition().getColumn() * 32, getPosition().getRow() * 32, "characters/deadVerde1.png"));
                        } else {
                            setImage(new Picture(getPosition().getColumn() * 32, getPosition().getRow() * 32, "characters/deadVermelho2.png"));
                        }
                        getImage().draw();


                        System.out.println("dead");
                        return;
                    }
                    hit.play(true);
                    health -= weapon.getDamage();
                    break;
            }
        }
    }
