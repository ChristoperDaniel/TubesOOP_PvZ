package classes.map;

import plant.*;
import zombie.*;

import java.util.ArrayList;
import java.util.List;

public class Tile {
    private String displayName;
    private List<Tanaman> plants;
    private List<Zombie> zombies;
    private int x;
    private int y;

    // Constructor
    public Tile(String displayName, int y, int x) {
        this.displayName = displayName;
        this.plants = new ArrayList<>();
        this.zombies = new ArrayList<>();
        this.x = x;
        this.y = y;
    }

    // Getters and Setters
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<Tanaman> getTanaman() {
        return plants;
    }

    public void addTanaman(Tanaman plants) {
        this.plants.add(plants);
    }

    public void removeTanaman(Tanaman plants) {
        displayName = "___";
        this.plants.remove(plants);
    }

    public List<Zombie> getZombies() {
        return zombies;
    }

    public void addZombie(Zombie zombie) {
        this.zombies.add(zombie);
        switch (zombie.getNamaZombie()){
            case "NormalZombie":
                this.setDisplayName("NMz");
                break;
            case "ConeheadZombie":
                this.setDisplayName("CHz");
                break;
            case "BucketheadZombie":
                this.setDisplayName("BHz");
                break;
            case "PoleVaultingZombie":
                this.setDisplayName("PVz");
                break;
            case "DuckyTubeZombie":
                this.setDisplayName("DTz");
                break;
            case "DolphinRiderZombie":
                this.setDisplayName("DRz");
                break;
            case "PeashooterZombie":
                this.setDisplayName("PSz");
                break;
            case "ScreendoorZombie":
                this.setDisplayName("SDz");
                break;
            case "JackInTheBoxZombie":
                this.setDisplayName("JBz");
                break;
            case "RugbyZombie":
                this.setDisplayName("RGz");
                break;
            default:
                System.out.println("Jenis zombie tidak dikenali.");
                return;
        }
    }

    public void removeZombie(Zombie zombie) {
        this.zombies.remove(zombie);
    }

    

    // Method untuk mengecek apakah tile kosong
    public boolean isEmpty() {
        return plants.isEmpty() && zombies.isEmpty();
    }
}