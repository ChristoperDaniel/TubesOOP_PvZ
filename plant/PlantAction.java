package plant;

import classes.map.*;
import classes.objects.*;
import interfaces.ZombieWithAbility;
import zombie.DolphinRiderZombie;
import zombie.JackInTheBoxZombie;
import zombie.PoleVaultingZombie;
import zombie.Zombie;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PlantAction implements Runnable {
    private Tanaman plant;
    private Tile tile;
    private Map map;
    private int row;
    private Sun sun;
    private ScheduledExecutorService executorService;

    public PlantAction(Tanaman plant, Tile tile, Map map, Sun sun) {
        this.plant = plant;
        this.tile = tile;
        this.map = map;
        this.sun = sun;
        this.row = tile.getY();  // Simpan baris untuk memudahkan akses nanti
        this.executorService = Executors.newScheduledThreadPool(1);
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
                    /*executorService.scheduleAtFixedRate(() ->{
                        sun.addCustomSun(sunflower.generateSun());
                        System.out.println("hehe");
                    } , 0, 3000, TimeUnit.MILLISECONDS); */
                }
                else if (plant.getNamaTanaman() == "Jalapeno"){
                    plant.attackPlant(tile, map);
                }
                else{
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
            //synchronized (tile) {
                tile.removeTanaman(plant);
            //}
        }
    }
}
