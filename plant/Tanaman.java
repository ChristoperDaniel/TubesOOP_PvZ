package plant;
import classes.map.Map;
import classes.map.Tile;
import zombie.Zombie;
import classes.objects.*;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.*;

public class Tanaman extends Aquatic {
    private ScheduledExecutorService executorService;
    private String namaTanaman;
    private int costTanaman;
    private int healthTanaman;
    private int attack_damageTanaman;
    private int attack_speedTanaman;
    private int rangeTanaman;
    private int cooldownTanaman;
    private int row;
    private int col;
    private boolean isOnCooldown = false;
    private Tile tile;
    private Map map;
    
    
    // konstruktor
    public Tanaman(String namaTanaman, int costTanaman, int healthTanaman, int attack_damageTanaman, int attack_speedTanaman, int rangeTanaman, int cooldownTanaman, boolean isaquatic){
        super(isaquatic);
        this.namaTanaman = namaTanaman;
        this.costTanaman = costTanaman;
        this.healthTanaman = healthTanaman;
        this.attack_damageTanaman = attack_damageTanaman;
        this.attack_speedTanaman = attack_speedTanaman;
        this.rangeTanaman = rangeTanaman;
        this.cooldownTanaman = cooldownTanaman;
        this.row = -1;
        this.col = -1;
        executorService = Executors.newSingleThreadScheduledExecutor();
    }
    
    // method
    public String getNamaTanaman(){
        return namaTanaman;
    }
    public int getCostTanaman(){
        return costTanaman;
    }
    public int getHealthTanaman(){
        return healthTanaman;
    }
    public void setHealthTanaman(int healthTanaman){
        this.healthTanaman = healthTanaman;
    }
    public int getAttackDamageTanaman(){
        return attack_damageTanaman;
    }
    public int getAttackSpeedTanaman(){
        return attack_speedTanaman;
    }
    public int getRangeTanaman(){
        return rangeTanaman;
    }
    public int getCooldownTanaman(){
        return cooldownTanaman;
    }
    public int getRowPlant(){
        return row;
    }
    public void setRowPlant(int row){
        this.row = row;
    }
    public int getColPlant(){
        return col;
    }
    public void setColPlant(int col){
        this.col = col;
    }
    public boolean isOnCooldown() {
        return isOnCooldown;
    }
    public void setIsLilyPadAvail(boolean nilai){
        if ((nilai == true) && (namaTanaman != "Lilypad")){
            healthTanaman += (new Lilypad()).getHealthTanaman();
        }
    }
    public void startCooldown() {
        new Thread(() -> {
            isOnCooldown = true;
            try {
                Thread.sleep(cooldownTanaman);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            isOnCooldown = false;
        }).start();
    }

    public void removeItem(Zombie zombie){
        if (!zombie.getIsItemRemovedZombie()) {
            zombie.setIsItemRemovedZombie(true);
        }   
    }

    public synchronized void attackPlant(Tile tile, Map map) {
        this.tile = tile;
        this.map = map;
        int x = getColPlant();
        // List<Zombie> kosong = new ArrayList<>();
        List<Tile> baris = map.getRow(tile.getY());
        if (getRangeTanaman() == -1) {
            if (getNamaTanaman() == "Snowpea"){
                    for (Tile tiles : baris) {
                        if ((!tiles.getZombies().isEmpty())&&(tiles.getX() >= x)) {
                            for (int i = 0; i < tiles.getZombies().size();i++){                                      
                                tiles.getZombies().get(i).setHealthZombie(tiles.getZombies().get(i).getHealthZombie() - getAttackDamageTanaman());
                                tiles.getZombies().get(i).SetIsGetSlowedZombie(true);
                                if (tiles.getZombies().get(i).getHealthZombie() <= 0) {
                                    tiles.getZombies().remove(i);
                                }
                            }
                        }
                    }
            }
            else if (getNamaTanaman() != "Snowpea") {
                Tile closestTile = null;
            
                // Cari tile terdekat yang berisi zombie
                for (Tile tiles : baris) {
                    if (!tiles.getZombies().isEmpty() && tiles.getX() >= x) {
                        closestTile = tiles;
                        break;  // Berhenti setelah menemukan tile terdekat
                    }
                }
            
                // Jika tile terdekat ditemukan, serang semua zombie di tile tersebut
                if (closestTile != null) {
                    List<Zombie> zombies = closestTile.getZombies();
                    for (int i = 0; i < zombies.size(); i++) {
                        Zombie zombie = zombies.get(i);
                        zombie.setHealthZombie(zombie.getHealthZombie() - getAttackDamageTanaman());
                        System.out.println("Health zombie: " + zombie.getHealthZombie()+", "+zombie.getNamaZombie());
                        map.displayMap();
                        if (zombie.getHealthZombie() <= 0) {
                            map.removeZombie(closestTile.getY(),x,zombie);
                            zombies.remove(i);
                            i--;  // Sesuaikan indeks setelah penghapusan
                        }
                    }
                }
            }
        }
        else if (getRangeTanaman() == 1) {  
            while (getHealthTanaman() > 0) {
                synchronized (tile) {
                    for (Tile tiles : baris) {
                        if (!tiles.getZombies().isEmpty()) {
                            if (tiles.getX() == x - 1){
                                for (int i = 0; i < tiles.getZombies().size();i++){  
                                    tiles.getZombies().get(i).setHealthZombie(tiles.getZombies().get(i).getHealthZombie() - getAttackDamageTanaman());
                                    if (tiles.getZombies().get(i).getHealthZombie() <= 0) {
                                        tiles.getZombies().remove(i);
                                    }
                                }
                            }
                            else if (tiles.getX() == x){
                                for (int i = 0; i < tiles.getZombies().size();i++){  
                                    tiles.getZombies().get(i).setHealthZombie(tiles.getZombies().get(i).getHealthZombie() - getAttackDamageTanaman());
                                    if (tiles.getZombies().get(i).getHealthZombie() <= 0) {
                                        tiles.getZombies().remove(i);
                                    }
                                }
                            }
                            else if (tiles.getX() == x + 1){
                                for (int i = 0; i < tiles.getZombies().size();i++){  
                                    tiles.getZombies().get(i).setHealthZombie(tiles.getZombies().get(i).getHealthZombie() - getAttackDamageTanaman());
                                    if (tiles.getZombies().get(i).getHealthZombie() <= 0) {
                                        tiles.getZombies().remove(i);
                                    }
                                }
                            }
                        }   
                    }setHealthTanaman(0);
                }
            }
        }
        else if (getRangeTanaman() == 2) {
            while (getHealthTanaman() > 0) {
                    for (Tile tiles : baris) {
                        if (!tiles.getZombies().isEmpty()) {
                            // Membunuh semua zombie di baris ini
                                List<Zombie> list = tiles.getZombies();
                                Iterator<Zombie> iterator = list.iterator();
                                while (iterator.hasNext()) {
                                    Zombie zombie = iterator.next();
                                    zombie.setHealthZombie(0);
                                    iterator.remove();
                                }
                                tiles.setDisplayName("___");
                                System.out.println("coba");
                            }
                    }
                    setHealthTanaman(0);
            }
        }
    }
}