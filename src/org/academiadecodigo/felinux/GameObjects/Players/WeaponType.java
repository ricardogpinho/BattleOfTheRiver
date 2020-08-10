package org.academiadecodigo.felinux.GameObjects.Players;

public enum WeaponType {
    BOW (5),
    GUN(10),
    CANNON (30);

    WeaponType(int damage) {
        this.damage = damage;
    }

    private int damage;

    public int getDamage() {
        return damage;
    }
}
