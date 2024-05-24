package plant;

import classes.map.*;
import classes.objects.*;

public class PlantAction implements Runnable {
    private Tanaman plant;
    private Tile tile;
    private Map map;
    private Sun sun;

    public PlantAction(Tanaman plant, Tile tile, Map map, Sun sun) {
        this.plant = plant;
        this.tile = tile;
        this.map = map;
        this.sun = sun;
    }

    @Override
    public void run() {
        while (plant.getHealthTanaman() > 0) {
            // Sinkronisasi untuk menghindari akses bersamaan ke tile
            //synchronized (tile) {
                if (plant.getNamaTanaman() == "Sunflower"){
                    Sunflower sunflower = (Sunflower) plant; 
                    try {
                        Thread.sleep(3000); // Misalnya menunggu 1 detik antara setiap aksi
                        sun.addCustomSun(sunflower.generateSun());
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                else if (plant.getNamaTanaman() == "Jalapeno"){
                    plant.attackPlant(tile, map);
                }

                else if (plant.getNamaTanaman() == "Magnetshroom") {
                    try {
                        plant.removeItem(tile, map);
                        Thread.sleep(20000); // Misalnya menunggu 1 detik antara setiap aksi
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                
                else {
                    try {
                        plant.attackPlant(tile, map);
                        Thread.sleep(plant.getAttackSpeedTanaman()); // Misalnya menunggu 1 detik antara setiap aksi
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            //}
        }

        // Periksa apakah health tanaman kurang dari atau sama dengan 0
        if (plant.getHealthTanaman() <= 0) {
            // Jika iya, hapus tanaman dari list tanaman pada tile
                map.removeTanaman(plant.getRowPlant(), plant.getColPlant(), plant);

        }
    }
}
