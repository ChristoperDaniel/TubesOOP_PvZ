package plant;
<<<<<<< HEAD
import zombie.*;
public class Peashooter extends Tanaman {
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

public class Peashooter extends Tanaman implements TanamanPenyerang{
>>>>>>> 8d3d6166ffabac5e7fdebc75dec0674c8cd5ca1b
    public Peashooter() {
        super("Peashooter", 100, 100, 25, 4, -1, 10, false);
    }
    /*
    @Override
    public void attackPlant(Tile tile, Map map) {
        Tanaman plant;
        Tile tile;
        Map map;
        int row;
        int x = getColPlant();
        int a = getColZombie();

        executorService.scheduleAtFixedRate(() ->{
            List<Zombie> kosong = new ArrayList<>();
            List<Tile> baris = map.getRow(tile.getY());
            for (Tile tiles : baris) {
                if (!tiles.getZombies().isEmpty()) {
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
    
        //  public void attackPlant(Zombie zombie, int x, int y) {
        // Mengecek apakah zombie berada pada row yang sama dengan tanaman
            /*     if (zombie.get.getRow() == tiles.getRow()) {
            // Menyerang zombie dengan mengurangi health sesuai dengan attack damage tanaman
            zombie.setHealthZombie(zombie.getHealthZombie() - this.getAttackDamageTanaman());*/