package zombie;

import classes.map.*;
import interfaces.ZombieWithAbility;
import plant.Tanaman;
import java.util.List;

public class DolphinRiderZombie extends Zombie implements ZombieWithAbility {
    public DolphinRiderZombie(Tile tile) {
        super("DolphinRiderZombie", 175, 100, 1,  5, 1, true);
    }

    @Override
    public void useAbility(boolean is_ability_used, Tile tile, Map map, Tanaman tanaman) {
        int x = getRowZombie();
        int y = getColZombie();
        Tile xy = map.getTile(x, y);

        if (is_ability_used == false) {
            List<Tanaman> planted = xy.getTanaman();
            if (planted != null) {
                for (Tanaman t : planted) {
                    t.setHealthTanaman(t.getHealthTanaman() - 5000);
                    if (t.getHealthTanaman() <= 0) {
                        xy.removeTanaman(t);
                        setIsAbilityUsed(true);
                        setColZombie(y - 1);
                    }
                }
            }
        }
    }
}
