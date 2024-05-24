package plant;
import classes.map.Map;
import classes.map.Tile;
import interfaces.ZombieWithItem;
import zombie.Zombie;
import java.util.*;

public class Tanaman extends Aquatic {
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

    public synchronized void removeItem(Tile tile, Map map){
        int x = getColPlant();
        // List<Zombie> kosong = new ArrayList<>();
        List<Tile> baris = map.getRow(tile.getY());
        if (getHealthTanaman() > 0) {
            if (getRangeTanaman() == -1) {
                if (getNamaTanaman() == "Magnetshroom"){
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
                        for (int i = 0; i < closestTile.getZombies().size();i++){  
                            if (closestTile.getZombies().get(i) instanceof ZombieWithItem) {
                                ((ZombieWithItem)closestTile.getZombies().get(i)).reduceStat(true);
                            }
                            System.out.println("Health zombie: " +  closestTile.getZombies().get(i).getHealthZombie()+", "+ closestTile.getZombies().get(i).getNamaZombie());
                            //System.out.println("Health zombie: " + closestTile.getZombies().get(i).getHealthZombie() +", "+closestTile.getZombies().get(i).getNamaZombie());
                            if (closestTile.getZombies().get(i).getHealthZombie() <= 0) {
                                closestTile.getZombies().remove(i);
                                closestTile.setDisplayName("___");
                            }
                        }
                    }
                }
            }
        }   
    }

    public synchronized void attackPlant(Tile tile, Map map) {
        int x = getColPlant();
        // List<Zombie> kosong = new ArrayList<>();
        List<Tile> baris = map.getRow(tile.getY());
        if (getHealthTanaman() > 0) {
            if (getRangeTanaman() == -1) {
                if (getNamaTanaman() == "Snowpea"){
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
                        for (int i = 0; i < closestTile.getZombies().size();i++){  
                            closestTile.getZombies().get(i).setHealthZombie(closestTile.getZombies().get(i).getHealthZombie() - getAttackDamageTanaman());
                            closestTile.getZombies().get(i).SetIsGetSlowedZombie(true);
                            System.out.println("Health zombie: " +  closestTile.getZombies().get(i).getHealthZombie()+", "+ closestTile.getZombies().get(i).getNamaZombie());
                            //System.out.println("Health zombie: " + closestTile.getZombies().get(i).getHealthZombie() +", "+closestTile.getZombies().get(i).getNamaZombie());
                            if (closestTile.getZombies().get(i).getHealthZombie() <= 0) {
                                closestTile.getZombies().remove(i);
                                closestTile.setDisplayName("___");
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
                        for (int i = 0; i < closestTile.getZombies().size();i++){  
                            closestTile.getZombies().get(i).setHealthZombie(closestTile.getZombies().get(i).getHealthZombie() - getAttackDamageTanaman());
                            //System.out.println("Health zombie: " + closestTile.getZombies().get(i).getHealthZombie() +", "+closestTile.getZombies().get(i).getNamaZombie());
                            if (closestTile.getZombies().get(i).getHealthZombie() <= 0) {
                                closestTile.getZombies().remove(i);
                                closestTile.setDisplayName("___");
                            }
                        }

                        /*List<Zombie> zombies = closestTile.getZombies();
                        for (Zombie zombie : zombies) {
                            zombie.setHealthZombie(zombie.getHealthZombie() - getAttackDamageTanaman());
                            System.out.println("Health zombie: " + zombie.getHealthZombie() +", "+zombie.getNamaZombie());
                            map.displayMap();
                            if (zombie.getHealthZombie() <= 0) {
                                closestTile.getZombies().remove(i);
                                map.removeZombie(closestTile.getY(),x,zombie);
                                //zombie.setColZombie(-1);
                                zombies.remove(zombie);
                            }
                        }*/
                        /*for (int i = 0; i < zombies.size(); i++) {
                            Zombie zombie = zombies.get(i);
                            zombie.setHealthZombie(zombie.getHealthZombie() - getAttackDamageTanaman());
                            System.out.println("Health zombie: " + zombie.getHealthZombie()+", "+zombie.getNamaZombie());
                            map.displayMap();
                            if (zombie.getHealthZombie() <= 0) {
                                map.removeZombie(closestTile.getY(),x,zombie);
                                zombie.setColZombie(-1);
                                zombies.remove(i);
                                i--;  // Sesuaikan indeks setelah penghapusan
                                
                            }
                        }*/
                    }
                }
            }
            else if (getRangeTanaman() == 1) {  
                boolean berhasilbunuh = false;
                while (getHealthTanaman() > 0) {
                    synchronized (tile) {
                            if ((!tile.getZombies().isEmpty())||(!map.getTile(tile.getY(), tile.getX()+1).getZombies().isEmpty()) ||(!map.getTile(tile.getY(), tile.getX()-1).getZombies().isEmpty())) {
                                Tile tiledepan = map.getTile(tile.getY(), tile.getX()+1);
                                Tile tilebelakang = map.getTile(tile.getY(), tile.getX()-1);
                                if (!tiledepan.getZombies().isEmpty()){
                                    for (int i = 0; i < tiledepan.getZombies().size();i++){  
                                        tiledepan.getZombies().get(i).setHealthZombie(tiledepan.getZombies().get(i).getHealthZombie() - getAttackDamageTanaman());
                                        if (tiledepan.getZombies().get(i).getHealthZombie() <= 0) {
                                            tiledepan.getZombies().remove(i);
                                            tiledepan.setDisplayName("___");
                                            berhasilbunuh= true;
                                        }
                                    }
                                }
                                else if (!tile.getZombies().isEmpty()){
                                    for (int i = 0; i < tile.getZombies().size();i++){  
                                        tile.getZombies().get(i).setHealthZombie(tile.getZombies().get(i).getHealthZombie() - getAttackDamageTanaman());
                                        if (tile.getZombies().get(i).getHealthZombie() <= 0) {
                                            tile.getZombies().remove(i);
                                            tile.setDisplayName("___");
                                            berhasilbunuh= true;
                                        }
                                    }
                                }
                                else if (!tilebelakang.getZombies().isEmpty()){
                                    for (int i = 0; i < tilebelakang.getZombies().size();i++){  
                                        tilebelakang.getZombies().get(i).setHealthZombie(tilebelakang.getZombies().get(i).getHealthZombie() - getAttackDamageTanaman());
                                        if (tilebelakang.getZombies().get(i).getHealthZombie() <= 0) {
                                            tilebelakang.getZombies().remove(i);
                                            tilebelakang.setDisplayName("___");
                                            berhasilbunuh= true;
                                        }
                                    }
                                }
                            }   
                        }
                        if (berhasilbunuh){
                            System.out.println("Apa dah");
                            setHealthTanaman(0);
                        }
                }
            }
            else if (getRangeTanaman() == 2) {
                while (getHealthTanaman() > 0) {
                        for (Tile tiles : baris) {
                            if (!tiles.getZombies().isEmpty()) {
                                // Membunuh semua zombie di baris ini
                                    List<Zombie> list = tiles.getZombies();
                                    for (Zombie zombie : list) {
                                        if (!(list == null)) {
                                            //zombie.setColZombie(-1);
                                            zombie.setHealthZombie(0);
                                        }
                                        else {
                                            break;
                                        }
                                    }
                                    tiles.setDisplayName("___");
                                }
                        }
                        setHealthTanaman(0);
                }
            }
        }
    }
}