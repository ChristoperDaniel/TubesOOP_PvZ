package plant;

/**
 * InnerTanamanPenyerang
 */

import zombie.*;
import classes.map.Map;

public interface TanamanPenyerang {
    public void attackPlant(Zombie zombie, Map[][] tiles);
}