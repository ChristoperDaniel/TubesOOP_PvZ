package plant;

<<<<<<< HEAD
import classes.map.Map;
import zombie.Zombie;

public class Jalapeno extends Tanaman{
=======
public class Jalapeno extends Tanaman implements TanamanPenyerang{
>>>>>>> 8d3d6166ffabac5e7fdebc75dec0674c8cd5ca1b
    public Jalapeno() {
        super("Jalapeno", 125, 100, 50000, 0, -1, 30, false);
    }
    public void attackPlant(Zombie zombie, Map tiles){

    }
<<<<<<< HEAD
    /* 
=======

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
<<<<<<< HEAD
    }*/
=======
    }
>>>>>>> 8d3d6166ffabac5e7fdebc75dec0674c8cd5ca1b
}