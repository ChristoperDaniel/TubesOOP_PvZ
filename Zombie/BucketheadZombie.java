package Zombie;

public class BucketheadZombie extends Zombie implements ZombieWithItem {
    public BucketheadZombie() {
        super("BucketheadZombie", 300, 100, 1,  5, 1);
    }
    public void attackZombie() {}
    public void reduceStat() {}
}
