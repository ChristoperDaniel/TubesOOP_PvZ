package zombie;

import classes.map.*;
import interfaces.ZombieWithItem;

public class ScreendoorZombie extends Zombie implements ZombieWithItem{
    public ScreendoorZombie(Tile tile) {
        super("ScreendoorZombie", 725, 100, 1000,  10000, 1, false, false);
    }

    @Override
    public void reduceStat(boolean is_item_removedZombie){
        if (is_item_removedZombie) {
            setHealthZombie(getHealthZombie() - 325);
        }
    }
}
