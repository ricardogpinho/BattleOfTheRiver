package org.academiadecodigo.felinux;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Menu {

    // Image with the size of the battleground
    private boolean startGame;


    public void setStartGame(boolean startGame) {
        this.startGame = startGame;
    }

    public Menu() throws InterruptedException {
        startGame = false;
        init();
    }


    public void init() throws InterruptedException {
        //This is going to be an image
        Rectangle menu = new Rectangle(10, 10, 640, 480);
        menu.fill();

        Picture menuImage = new Picture( 10, 10, "menuImage.png");
        menuImage.draw();

        //por aqui khandler para passar do menu para o jogo em si

        /*while(true){
            if(startGame){
                break;
            }
        }*/

        Thread.sleep(2000);

        // Add game name and "Start button"
    }
}
