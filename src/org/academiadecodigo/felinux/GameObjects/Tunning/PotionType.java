package org.academiadecodigo.felinux.GameObjects.Tunning;

public enum PotionType {
    POTION_HP(10, "potionHP.png" ),
    POTION_HEALTH(-10 , "/potions/venomPotion.png");

    private int healthAmount;

    public String getImage() {
        return image;
    }

    private String image;

    PotionType(int healthAmount, String image) {
        this.healthAmount = healthAmount;
        this.image = image;
    }

    public int getHealthAmount() {
        return healthAmount;
    }
}
