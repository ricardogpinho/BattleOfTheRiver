package org.academiadecodigo.felinux.Grid;

import org.academiadecodigo.felinux.GameObjects.Players.Character;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import javax.security.sasl.RealmCallback;

public class ScoreBar {

    private CharacterStatus healthB1;
    private CharacterStatus healthB2;

    public ScoreBar(Character one, Character two) {
        Rectangle blackRec = new Rectangle(10, Battleground.height + 10, Battleground.width, 4 * Battleground.CELL_SIZE);
        blackRec.setColor(Color.BLACK);
        blackRec.fill();

        Rectangle whiteRec = new Rectangle(15, Battleground.height + 15, Battleground.width - 10, (4 * Battleground.CELL_SIZE) - 10);
        whiteRec.setColor(Color.WHITE);
        whiteRec.draw();

        Rectangle whiteRec2 = new Rectangle(17, Battleground.height + 13, Battleground.width - 14, (4 * Battleground.CELL_SIZE) - 14);
        whiteRec2.setColor(Color.WHITE);
        whiteRec2.draw();

        healthB1 = new CharacterStatus(80, 510, one);
        healthB1.drawName();
        healthB1.drawHealthBar();

        healthB2 = new CharacterStatus(580, 510, two);
        healthB2.drawName();
        healthB2.drawHealthBar();
    }

    public void updateStatusBar() {
        healthB1.updateHealthBar();
        healthB2.updateHealthBar();
    }



    private class CharacterStatus {

        private int posX;
        private int posY;
        private Character character;
        private Rectangle healthBar;
        private int health;

        public CharacterStatus(int posX, int posY, Character character) {
            this.posX = posX;
            this.posY = posY;
            this.character = character;
            this.health = character.getHealth();
        }

        public void drawName() {
            Text name = new Text (posX - 20, posY , character.getCharacter().getName());
            name.grow(15, 10);
            name.setColor(Color.WHITE);
            name.draw();
        }

        public void drawHealthBar() {
            Rectangle frame = new Rectangle(posX -52,posY+48,104,24);
            frame.setColor(Color.WHITE);
            frame.fill();
            healthBar = new Rectangle(posX -50, posY + 50, character.getHealth(), 20);
            healthBar.setColor(Color.RED);
            healthBar.fill();
        }

        public void updateHealthBar() {
            if(character.isDead()) {
                System.out.println("");
                healthBar.delete();
                return;
            }
            healthBar.delete();
            System.out.println("testehp");
            healthBar = new Rectangle(posX -50 , posY + 50, character.getHealth(),20);
            healthBar.setColor(Color.RED);
            System.out.println("after hp");
            healthBar.fill();
        }
    }
}
