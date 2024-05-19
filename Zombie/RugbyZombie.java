package zombie;

import classes.map.*;
import interfaces.ZombieWithItem;
import plant.Tanaman;

import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class RugbyZombie extends Zombie implements ZombieWithItem {
    private ScheduledExecutorService executorService;
    public RugbyZombie(Tile tile) {
        super("RugbyZombie", 500, 100, 1,  3, 1, false);
        executorService = Executors.newSingleThreadScheduledExecutor();
    }

    @Override
    public void reduceStat(boolean is_item_removedZombie){
        if (is_item_removedZombie) {
            setHealthZombie(getHealthZombie() - 200);
        }
    }

    @Override
    public void attackZombie(Tile tile, Map map, Tanaman tanaman) {
        int x = tile.getX();
        int y = tile.getY();
        Tile xy = map.getTile(x, y);
        int dmg = getAttackDamageZombie();
        int atkspd = getAttackSpeedZombie();

        executorService.scheduleAtFixedRate(() ->{
            List<Tanaman> planted = xy.getTanaman();
            if (planted != null) {
                for (Tanaman t : planted) {
                    t.setHealthTanaman(t.getHealthTanaman() - dmg);
                    if (t.getHealthTanaman() <= 0) {
                        xy.removeTanaman(t);
                    }
                }
            }
            else {
                moveZombie(tile, map);
            }
        } , 0, atkspd, TimeUnit.SECONDS);
    }
    
    @Override
    public void moveZombie (Tile tile, Map map) {
        int x = tile.getX();
        int y = tile.getY();
        Tile xy = map.getTile(x, y);
        int spd = getSpeedZombie();

        executorService.scheduleAtFixedRate(() ->{
            for (int i = y; i > 0; i--) {
                if (!(map.isTanamanAvail(x, y) == true)) {
                    xy.setY(i - 1);
                }
                else {
                    break;
                }
            }
        } , 0, spd, TimeUnit.SECONDS);
    }
}
