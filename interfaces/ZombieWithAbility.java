package interfaces;

import classes.map.*;
import plant.Tanaman;

public interface ZombieWithAbility {
    public void useAbility(boolean is_ability_used, Tile tile, Map map, Tanaman tanaman);
}
