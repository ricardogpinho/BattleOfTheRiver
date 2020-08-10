package org.academiadecodigo.felinux.GameObjects.Tunning;

import org.academiadecodigo.felinux.GameObjects.GameObject;
import org.academiadecodigo.felinux.Grid.Position;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Potion extends GameObject {

    private PotionType potionType;

    public Potion(Position position, Picture image, PotionType potionType){
        super(position, image);
        this.potionType = potionType;
    }

    @Override
    public Position getPosition() {
        return super.getPosition();
    }

    @Override
    public Picture getImage() {
        return super.getImage();
    }

    public PotionType getPotionType() {
        return potionType;
    }
}
