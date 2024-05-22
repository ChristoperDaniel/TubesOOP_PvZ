package plant;
import classes.map.*;
import zombie.Zombie;
import java.util.List;

public class Jalapeno extends Tanaman {
    public Jalapeno() {
        super("Jalapeno", 125, 100, 50000, 0, 2, 30000, false);
    }
    /* 
=======

<<<<<<< HEAD
>>>>>>> 8d3d6166ffabac5e7fdebc75dec0674c8cd5ca1b
    @Override
    public void attackPlant(Tile tile, Map map) {
        Tanaman plant;
        Tile tile;
        Map map;
        int row;

        while (plant.getHealthTanaman() > 0) {
            synchronized (tile) {
                for (Tile tiles : baris) {
                    if (!tiles.getZombies().isEmpty()) {
                    // Membunuh semua zombie di baris ini
                        for (Zombie zombie : tiles.getZombies()) {
                            tiles.removeZombie(zombie);
                        }
                    }
                    plant.setHealthTanaman(0);
                }
            }
        } 
    }*/
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