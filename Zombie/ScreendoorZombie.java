package zombie;

public class ScreendoorZombie extends Zombie implements ZombieWithItem{
    public ScreendoorZombie() {
        super("ScreendoorZombie", 725, 100, 1,  5, 1);
    }
    public void attackZombie(){}
    public void reduceStat() {}
}
