package Zombie;

import classes.map.*;
import interfaces.ZombieWithAbility;
import plant.Tanaman;

import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class DuckyTubeZombie extends Zombie implements ZombieWithAbility {
    private ScheduledExecutorService executorService;
    public DuckyTubeZombie(Tile tile) {
        super("DuckyTubeZombie", 100, 100, 1,  5, 1, true);
        executorService = Executors.newSingleThreadScheduledExecutor();
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
    public void useAbility(boolean is_ability_used, Tile tile, Map map, Tanaman tanaman) {
        int x = tile.getX();
        int y = tile.getY();
        Tile xy = map.getTile(x, y);
        if (is_ability_used == false) {
            List<Tanaman> planted = xy.getTanaman();
            if (planted != null) {
                for (Tanaman t : planted) {
                    t.setHealthTanaman(t.getHealthTanaman() - 5000);
                    if (t.getHealthTanaman() <= 0) {
                        xy.removeTanaman(t);
                        setIsAbilityUsed(true);
                        xy.setY(y - 1);
                    }
                }
            }
            else {
                moveZombie(tile, map);
            }
        }
    }

    @Override
    public void moveZombie(Tile tile, Map map) {
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
