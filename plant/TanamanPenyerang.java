package plant;
import classes.map.*;
import zombie.DolphinRiderZombie;
import zombie.JackInTheBoxZombie;
import zombie.PoleVaultingZombie;
import zombie.Zombie;
import classes.objects.*;
import interfaces.ZombieWithAbility;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * InnerTanamanPenyerang
 */

import zombie.*;
import classes.map.Map;

public interface TanamanPenyerang {
    public void attackPlant(Tile tile, Map map);
}