package plant;
import classes.map.*;
import zombie.Zombie;
import java.util.List;

public class Jalapeno extends Tanaman implements TanamanPenyerang{
    public Jalapeno() {
        super("Jalapeno", 125, 100, 50000, 0, 2, 30, false);
    }

    // @Override
    // public void attackPlant(Tile tile, Map map) {

    //     while (getHealthTanaman() > 0) {
    //         synchronized (tile) {
    //             List<Tile> baris = map.getRow(tile.getY());
    //             for (Tile tiles : baris ) {
    //                 if (!tiles.getZombies().isEmpty()) {
    //                 // Membunuh semua zombie di baris ini
    //                     for (Zombie zombie : tiles.getZombies()) {
    //                         tiles.removeZombie(zombie);
    //                     }
    //                 }
    //                 setHealthTanaman(0);
    //             }
    //         }
    //     } 
    // }
}