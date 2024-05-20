package zombie;

import classes.map.*;
import interfaces.ZombieWithItem;

public class RugbyZombie extends Zombie implements ZombieWithItem {
    public RugbyZombie(Tile tile) {
        super("RugbyZombie", 500, 100, 1,  3, 1, false);
    }

    @Override
    public void reduceStat(boolean is_item_removedZombie){
        if (is_item_removedZombie) {
            setHealthZombie(getHealthZombie() - 200);
        }
    }
}
