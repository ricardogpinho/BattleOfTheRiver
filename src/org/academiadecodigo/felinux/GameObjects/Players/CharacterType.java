package org.academiadecodigo.felinux.GameObjects.Players;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public enum CharacterType {
    //String name, field1_img, field2:img
    CHARACTER1("C a d e t s", "characters/shootVerde1.png", "characters/shootVerde2.png"), // mudar imagem player
    CHARACTER2("M a s t e r s", "characters/shootVermelho1.png", "characters/shootVermelho2.png"); // mudar imagem do pc
    //CHARACTER3(),
    //CHARACTER4(),
    //CHARACTER5(),
    //CHARACTER6(),
    //CHARACTER7(),
    //CHARACTER8();

    private String name;
    private String img1;
    private String img2;

    CharacterType(String name, String  img1, String img2){
       this.name = name;
       this.img1 = img1;
       this.img2 = img2;
    }

    public String getName() {
        return name;
    }

    public String getImg1() {
        return img1;
    }

    public String getImg2() {
        return img2;
    }
}
