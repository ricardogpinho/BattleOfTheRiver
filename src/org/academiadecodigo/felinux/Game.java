package org.academiadecodigo.felinux;

import org.academiadecodigo.felinux.GameObjects.Decor.Tree;
import org.academiadecodigo.felinux.GameObjects.GameObject;
import org.academiadecodigo.felinux.GameObjects.Players.COM;
import org.academiadecodigo.felinux.GameObjects.Players.CharacterType;
import org.academiadecodigo.felinux.GameObjects.Players.Player;
import org.academiadecodigo.felinux.GameObjects.Projectiles.Projectile;
import org.academiadecodigo.felinux.GameObjects.Tunning.Potion;
import org.academiadecodigo.felinux.GameObjects.Tunning.PotionType;
import org.academiadecodigo.felinux.Grid.*;
import org.academiadecodigo.felinux.Utils.Utils;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.LinkedList;

public class Game implements KeyboardHandler {

    /**
     * Player one is always controlled by the user
     */
    private Sound sound;
    private Player playerOne;
    private COM playerCOM;
    private GameObject[] gameObjects;
    public static LinkedList<Projectile> projectilesList;
    private LinkedList<Potion> potionLinkedList;
    private int delay;
    private Keyboard keyboard;
    private Battleground battleground;
    private Menu menu;
    private ScoreBar scoreBar;
    private int counter = 10000;
    private boolean endGame;
    private boolean wantToRestart;

    public Game() {
        delay = 50;
        gameObjects = new GameObject [8];
        projectilesList = new LinkedList<>();
        potionLinkedList = new LinkedList<>();
        sound = new Sound("/audio/robinHood.wav");
        endGame = false;
        keyboard = new Keyboard(this);
        kbInit();
    }

    public void init() throws InterruptedException {


        menu = new Menu();

        battleground = new Battleground();
        playerOne = new Player(CharacterType.CHARACTER1,FieldType.FIELD1);
        playerCOM = new COM(CharacterType.CHARACTER2, FieldType.FIELD2);

        scoreBar = new ScoreBar(playerOne,playerCOM);

        createObjects();

    }

    public void start() throws InterruptedException {

        sound.play(true);

        while(!endGame ) {

            Thread.sleep(delay);
            moveAllObjects();
            potionsDrop();
            potionHit();
            scoreBar.updateStatusBar();
            endGame = checkWin();
        }

        Thread.sleep(7000);
        if(wantToRestart) {
            endGame = false;
            restart();
            return;
        }

        System.exit(0);

    }

    public void restart() throws InterruptedException {

        projectilesList.clear();
        wantToRestart = false;
        init();
        sound.play(true);
        start();


    }


    // This is not working
    private void potionsDrop(){
        counter-=delay;
       if(counter == 5000 && potionLinkedList.size()>1) {
            potionLinkedList.getFirst().getImage().delete();
            potionLinkedList.removeFirst();
            return;
        }
        if(counter>0){return;}
        switch (getPotionType()){
            case 1:
                Position a = getPotionPosition();
                Picture pa =  new Picture(a.getColumn() * 32,a.getRow() * 32,PotionType.values()[0].getImage());
                pa.draw();
                potionLinkedList.add(new Potion(a,pa, PotionType.POTION_HP));
            case 2:
                Position b = getPotionPosition();
                Picture pb = new Picture(b.getColumn() * 32,b.getRow() * 32,PotionType.values()[1].getImage());
                pb.draw();
                potionLinkedList.add(new Potion(b,pb, PotionType.POTION_HEALTH));
        }
        counter = 10000;
    }


    private Position getPotionPosition(){

        return new Position(Utils.Randomizer(19),Utils.Randomizer(14));
    }

    private int getPotionType(){
        return Utils.Randomizer(2);
    }

    private boolean checkWin() throws InterruptedException {
        if(playerOne.getHealth()<=0){
            Thread.sleep(2000);
            new Picture(10,10, "finalLost.jpg").draw();
            Thread.sleep(4000);
            Picture credits = new Picture( 10, 10, "credits.png");
            credits.draw();
            return true;

        }
        if(playerCOM.getHealth()<=0){
            Thread.sleep(2000);
            new Picture(10,10, "finalWin.jpg").draw();
            Thread.sleep(4000);
            Picture credits = new Picture( 10, 10, "credits.png");
            credits.draw();
            return true;
        }
        return false;
    }
    /**
     * When multiple arrows are shot, game throws an exception. Maybe because we're removing something
     * while iterating over the list
     */

    public void moveAllObjects() {
        Projectile x;
        comPlay();
        for (int i = 0; i < projectilesList.size(); i++) {
            x = projectilesList.get(i);
            x.move();
            System.out.println("possible hit");
            hitObjects(x);
        }

        /*for(Projectile projectile : projectilesList) {
            projectile.move();
            hitObjects(projectile);
        }*/
    }

    public void potionHit () {
        Potion p;
        //Sound potion = new Sound("/audio/potionDrink.wav");
        for(int i = 0; i < potionLinkedList.size(); i++) {
            p = potionLinkedList.get(i);
            if(p.getPosition().getColumn() == playerOne.getPosition().getColumn() && p.getPosition().getRow() == playerOne.getPosition().getRow()){
                if(playerOne.getHealth()+p.getPotionType().getHealthAmount()>100) {
                    playerOne.setHealth(100);
                    p.getImage().delete();
                    System.out.println("aaaaaaaaaa");
                    //potion.play(true);
                    potionLinkedList.remove(p);
                    return;
                }
                playerOne.setHealth(playerOne.getHealth() + p.getPotionType().getHealthAmount());

                p.getImage().delete();
                System.out.println("aaaaaaaa");
                //potion.play(true);
                potionLinkedList.remove(p);
                return;
            }
            if(p.getPosition().getColumn() == playerCOM.getPosition().getColumn() && p.getPosition().getRow() == playerCOM.getPosition().getRow()) {
                if(playerCOM.getHealth()+p.getPotionType().getHealthAmount()>100) {
                    playerCOM.setHealth(100);

                    p.getImage().delete();
                    System.out.println("aaaaaaaaaa");
                    //potion.play(true);
                    potionLinkedList.remove(p);
                    return;
                }
                playerCOM.setHealth(playerCOM.getHealth() + p.getPotionType().getHealthAmount());

                p.getImage().delete();
                System.out.println("aaaaaaaaaaa");
                //potion.play(true);
                potionLinkedList.remove(p);
                return;
            }
        }
    }

    public void hitObjects(Projectile x) {

        for(int i = 0; i < gameObjects.length; i++) {
            if((x.getPosition().getRow() == gameObjects[i].getPosition().getRow()) && x.getPosition().getColumn() == gameObjects[i].getPosition().getColumn()) {
                x.getImage().delete();
                System.out.println("projectile removed");
                projectilesList.remove(x);
            }
        }

        if(x.getField() == FieldType.FIELD2) {
            if (x.getPosition().getRow() == playerOne.getPosition().getRow() && x.getPosition().getColumn() == playerOne.getPosition().getColumn()) {
                playerOne.hit();
                x.getImage().delete();
                System.out.println("projectile removed");
                projectilesList.remove(x);
            }
        }

        if(x.getField() == FieldType.FIELD1) {
            if(x.getPosition().getRow() == playerCOM.getPosition().getRow() && x.getPosition().getColumn() == playerCOM.getPosition().getColumn()) {
                playerCOM.hit();
                x.getImage().delete();
                System.out.println("projectile removed");
                projectilesList.remove(x);
            }
        }
    }

    public void comPlay() {
        if(playerCOM.isDead()) {
            return;
        }
        double random = Math.random();

        if(random < 0.15) {
            projectilesList.add(playerCOM.shoot());
            return;
        }
        playerCOM.move(gameObjects);
    }

    public void createObjects () {

        //TreeFactory factory = new TreeFactory();

        /*gameObjects[0] = factory.createTree(new Position(2, 2), Tree.TreeType.TREE6);
        gameObjects[0].getImage().draw();

        gameObjects[1] = factory.createTree(new Position(6 + Tree.TreeType.TREE2.getCol(), 2 + Tree.TreeType.TREE2.getRow()), Tree.TreeType.TREE2);*/

        gameObjects[0] = new Tree(new Position(2, 2), new Picture (battleground.columnToX(2), battleground.rowToY(2), "tree6_1x1.png"));
        gameObjects[0].getImage().draw();

        gameObjects[1] = new Tree(new Position(6, 9), new Picture (battleground.columnToX(6), battleground.rowToY(9), "tree6_1x1.png"));
        gameObjects[1].getImage().draw();

        gameObjects[2] = new Tree(new Position(13, 8), new Picture (battleground.columnToX(13), battleground.rowToY(8), "tree6_1x1.png"));
        gameObjects[2].getImage().draw();

        gameObjects[3] = new Tree(new Position(17, 13), new Picture (battleground.columnToX(17), battleground.rowToY(13), "tree6_1x1.png"));
        gameObjects[3].getImage().draw();

        gameObjects[4] = new Tree(new Position(5, 4), new Picture (battleground.columnToX(4), battleground.rowToY(2), "tree4_3x3.png"));
        gameObjects[4].getImage().draw();

        gameObjects[5] = new Tree(new Position(3, 8), new Picture (battleground.columnToX(2), battleground.rowToY(6), "tree4_3x3.png"));
        gameObjects[5].getImage().draw();

        gameObjects[6] = new Tree(new Position(14, 3), new Picture (battleground.columnToX(13), battleground.rowToY(1), "tree4_3x3.png"));
        gameObjects[6].getImage().draw();

        gameObjects[7] = new Tree(new Position(15, 10), new Picture (battleground.columnToX(14), battleground.rowToY(8), "tree4_3x3.png"));
        gameObjects[7].getImage().draw();


          }

    public void kbInit() {

        KeyboardEvent right = new KeyboardEvent();
        right.setKey(KeyboardEvent.KEY_RIGHT);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent left = new KeyboardEvent();
        left.setKey(KeyboardEvent.KEY_LEFT);
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent down = new KeyboardEvent();
        down.setKey(KeyboardEvent.KEY_DOWN);
        down.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent up = new KeyboardEvent();
        up.setKey(KeyboardEvent.KEY_UP);
        up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent space = new KeyboardEvent();
        space.setKey(KeyboardEvent.KEY_SPACE);
        space.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent r = new KeyboardEvent();
        r.setKey(KeyboardEvent.KEY_R);
        r.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        /**
         * From this moment on, we decide what happens when keys are released
         */

        KeyboardEvent spacent = new KeyboardEvent();
        spacent.setKey(KeyboardEvent.KEY_SPACE);
        spacent.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        /*KeyboardEvent rightReleased = new KeyboardEvent();
        right.setKey(KeyboardEvent.KEY_RIGHT);
        right.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);


        KeyboardEvent leftReleased = new KeyboardEvent();
        left.setKey(KeyboardEvent.KEY_LEFT);
        left.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent downReleased = new KeyboardEvent();
        down.setKey(KeyboardEvent.KEY_DOWN);
        down.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);


        KeyboardEvent upReleased = new KeyboardEvent();
        up.setKey(KeyboardEvent.KEY_UP);
        up.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        /**
         * Listeners
         */

        keyboard.addEventListener(left);
        keyboard.addEventListener(right);
        keyboard.addEventListener(up);
        keyboard.addEventListener(down);
        keyboard.addEventListener(space);
        keyboard.addEventListener(r);
        keyboard.addEventListener(spacent);
        /*keyboard.addEventListener(upReleased);
        keyboard.addEventListener(downReleased);
        keyboard.addEventListener(leftReleased);
        keyboard.addEventListener(rightReleased);*/
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_LEFT:
                playerOne.setMoving(true);
                playerOne.setDirection(Player.DirectionType.LEFT);
                System.out.println("left");
                playerOne.move(gameObjects);
                break;

            case KeyboardEvent.KEY_RIGHT:
                playerOne.setMoving(true);
                playerOne.setDirection(Player.DirectionType.RIGHT);
                System.out.println("right");
                playerOne.move(gameObjects);
                break;

            case KeyboardEvent.KEY_UP:
                playerOne.setMoving(true);
                playerOne.setDirection(Player.DirectionType.UP);
                System.out.println("moving up");
                playerOne.move(gameObjects);
                break;
            case KeyboardEvent.KEY_DOWN:
                playerOne.setMoving(true);
                playerOne.setDirection(Player.DirectionType.DOWN);
                System.out.println("moving down");
                playerOne.move(gameObjects);
                break;

            case KeyboardEvent.KEY_SPACE:
                playerOne.setShooting(true);
                System.out.println("shoot");
                projectilesList.add(playerOne.shoot());
                break;

            case KeyboardEvent.KEY_R:
                wantToRestart = true;
                break;
        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_LEFT:
            case KeyboardEvent.KEY_RIGHT:
            case KeyboardEvent.KEY_UP:
            case KeyboardEvent.KEY_DOWN:
                playerOne.setMoving(false);
                break;
            case KeyboardEvent.KEY_SPACE:
                playerOne.setShooting(false);
                break;
        }
    }
}
