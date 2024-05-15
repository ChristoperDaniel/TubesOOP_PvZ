package Zombie;

public class RugbyZombie extends Zombie implements ZombieWithItem {
    public RugbyZombie() {
        super("RugbyZombie", 500, 100, 1,  3, 1);
    }
    public void attackZombie(){}
    public void reduceStat() {}
}
