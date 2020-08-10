package org.academiadecodigo.felinux;

import org.academiadecodigo.felinux.Grid.Battleground;

public class Playground {

    public static void main(String[] args) {

        Game game = new Game();

        try {
            game.init();
            game.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
