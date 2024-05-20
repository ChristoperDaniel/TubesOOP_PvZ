package zombie;

import classes.map.*;
import interfaces.ZombieWithItem;

public class BucketheadZombie extends Zombie implements ZombieWithItem {
    public BucketheadZombie(Tile tile) {
        super("BucketheadZombie", 300, 100, 1000,  10000, 1, false, false);
    }

    @Override
    public void reduceStat(boolean is_item_removedZombie){
        if (is_item_removedZombie) {
            setHealthZombie(getHealthZombie() - 100);
        }
    }
}