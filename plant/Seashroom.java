package plant;
<<<<<<< HEAD
public class Seashroom extends Tanaman {
=======
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

public class Seashroom extends Tanaman implements TanamanPenyerang{
>>>>>>> 8d3d6166ffabac5e7fdebc75dec0674c8cd5ca1b
    public Seashroom() {
        super("Seashroom", 0, 100, 20, 5, -1, 10,true);
    }
    /*
    @Override
    public void attackPlant(Tile tile, Map map) {
        Tanaman plant;
        Tile tile;
        Map map;
        int row;
        int x = getColPlant();

        executorService.scheduleAtFixedRate(() ->{
            List<Zombie> kosong = new ArrayList<>();
            List<Tile> baris = map.getRow(tile.getY());
            for (Tile tiles : baris) {
                if (!tiles.getZombies().isEmpty()) {
                    int a = getColZombie();
                    if (a >= x){
                        for (Zombie zombie : tiles.getZombies()){
                            tiles.setHealthZombie(tiles.getHealthZombie() - plant.getAttackDamageTanaman());
                            if (tiles.getHealthZombie() <= 0) {
                                tiles.removeZombie(zombie);
                            }
                        }
                    }
                }
            }
        } , 0, plant.getAttackSpeedTanaman(), TimeUnit.SECONDS);
    }*/
}
/*
    @Override
    public void serang(Map map, int x, int y) {
        if (statusTanaman()) {
            int detikgames = 200;
            int i = 0;
            while (i <= detikgames) {
                List<Zombie> kosong = new ArrayList<>();
                List<Tile> baris = map.getBaris(y);
                for (Tile tiles : baris) {
                    if (!tiles.getZombies().isEmpty()) {
                        // Ambil zombie terdepan
                        Zombie zombieTerdepan = tiles.getZombies().get(0);
                        // Serang setiap attack_speedTanaman detik sekali
                        zombieTerdepan.setHealthZombie(zombieTerdepan.getHealthZombie() - this.getAttackDamageTanaman());
                        if (zombieTerdepan.getHealthZombie() <= 0) {
                            tiles.removeZombie(zombieTerdepan);
                        }
                    }
                }
                i++;
            }
        }
    }
    */