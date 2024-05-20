package plant;

import classes.map.Map;
import zombie.Zombie;

public class Jalapeno extends Tanaman implements TanamanPenyerang{
    public Jalapeno() {
        super("Jalapeno", 125, 100, 50000, 0, -1, 30, false);
    }
    public void attackPlant(Zombie zombie, Map[][] tiles){

    }

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
    }
}