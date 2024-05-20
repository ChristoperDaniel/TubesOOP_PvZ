package zombie;

import classes.map.*;
import interfaces.ZombieWithAbility;
import plant.Tanaman;
import java.util.List;

public class JackInTheBoxZombie extends Zombie implements ZombieWithAbility {
    public JackInTheBoxZombie(Tile tile) {
        super("JackInTheBoxZombie", 300, 5000, 1000,  10000, 1, false);
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
                        setHealthZombie(0);
                    }
                }
            }
        }
    }
}
