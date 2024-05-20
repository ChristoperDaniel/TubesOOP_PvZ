package zombie;

import classes.map.*;
import interfaces.ZombieWithItem;

public class BucketheadZombie extends Zombie implements ZombieWithItem {
    public BucketheadZombie(Tile tile) {
        super("BucketheadZombie", 300, 100, 1,  5, 1, false);
    }

    @Override
    public void reduceStat(boolean is_item_removedZombie){
        if (is_item_removedZombie) {
            setHealthZombie(getHealthZombie() - 100);
        }
    }
}

/*
    public void attackZombie(Tanaman tanaman, int x, int y) {
        Tile currentTile = getTile();
        int y = currentTile.getY();
        int damage = this.getAttackDamageZombie();
        int atkspeed = this.getAttackSpeedZombie();

        executorService.scheduleAtFixedRate(() -> {
            for (Character owner : currentTile.getOwners()) {
                if (owner instanceof Tanaman && ((Tanaman) owner).getTile().getY() == y) {
                    owner.setHealthTanaman(damage);
                }
            }
        }   , 0, atkspeed, TimeUnit.SECONDS); 
    }

    public void reduceStat() {
        if (is_item_removedZombie) {
            setHealthZombie( getHealthZombie() - 100);
        }
    }
*/