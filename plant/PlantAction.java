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
        this.row = tile.getY();  // Simpan baris untuk memudahkan akses nanti
        this.executorService = Executors.newScheduledThreadPool(1);
    }

    @Override
    public void run() {
        while (plant.getHealthTanaman() > 0) {
            // Sinkronisasi untuk menghindari akses bersamaan ke tile
            synchronized (tile) {
                List<TanamanPenyerang> plantack = new ArrayList<>();
                plantack.add(new Peashooter());
                plantack.add(new SnowPea());
                plantack.add(new JackInTheBoxZombie(tile));
                if (plant.getRangeTanaman() == -1){
                    if (plant.getNamaTanaman() == "Jalapeno"){
                        List<Tile> baris = map.getRow(tile.getY());
                        for (Tile tiles : baris) {
                            if (!tiles.getZombies().isEmpty()) {
                                // Membunuh semua zombie di baris ini
                                for (Zombie zombie : tiles.getZombies()) {
                                    tiles.removeZombie(zombie);
                                }
                            }
                        }
                        plant.setHealthTanaman(0);
                    }
                    else if (plant.getNamaTanaman() == "Peashooter"){
                        executorService.scheduleAtFixedRate(() ->{
                            List<Tile> baris = map.getRow(tile.getY());
                            for (Tile tiles : baris) {
                                if (tiles.getX()>=tile.getX()){
                                    if (!tiles.getZombies().isEmpty()) {
                                            // Ambil zombie terdepan
                                        Zombie zombieTerdepan = tiles.getZombies().get(0);
                                            // Serang setiap attack_speedTanaman detik sekali
                                        zombieTerdepan.setHealthZombie(zombieTerdepan.getHealthZombie() - plant.getAttackDamageTanaman());
                                        if (zombieTerdepan.getHealthZombie() <= 0) {
                                                tiles.removeZombie(zombieTerdepan);
                                        }
                                    }
                                }
                            }
                        } , 0, plant.getAttackSpeedTanaman(), TimeUnit.SECONDS);
                    }
                }
                else if (plant.getRangeTanaman() == 0){
                    if (plant.getNamaTanaman() == "Sunflower"){
                        Sunflower sunflower = (Sunflower) plant; 
                        executorService.scheduleAtFixedRate(() ->{
                            sun.addCustomSun(sunflower.generateSun());
                        } , 0, 3, TimeUnit.SECONDS);
                    }
                }
                else if (plant.getRangeTanaman() == 1){
                    if (plant.getNamaTanaman() == "Squash"){
                        boolean benar = false;
                        List<Tile> baris = new ArrayList<>(List.of(
                            map.getTile(plant.getRowPlant(), plant.getColPlant() + 1),
                            map.getTile(plant.getRowPlant(), plant.getColPlant() - 1)
                        ));
                        Zombie zombiedepan1 = baris.get(0).getZombies().get(0);
                        Zombie zombiedepan2 = baris.get(1).getZombies().get(0);
                        if (!baris.get(0).getZombies().isEmpty()){
                            zombiedepan1.setHealthZombie(zombiedepan1.getHealthZombie() - plant.getAttackDamageTanaman());
                            benar = true;
                        }
                        else{
                            zombiedepan2.setHealthZombie(zombiedepan1.getHealthZombie() - plant.getAttackDamageTanaman());
                            benar = true;
                        }
                        if (benar){
                            plant.setHealthTanaman(0);
                        }
                    }
                }
        }

            // Menunggu sebelum melakukan aksi berikutnya
            try {
                Thread.sleep(1000); // Misalnya menunggu 1 detik antara setiap aksi
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Periksa apakah health tanaman kurang dari atau sama dengan 0
        if (plant.getHealthTanaman() <= 0) {
            // Jika iya, hapus tanaman dari list tanaman pada tile
            synchronized (tile) {
                tile.removeTanaman(plant);
            }
        }
    }
}
