package classes.map;

import zombie.*;
import plant.*;

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
    public void getY(int y){
        this.y = y;
    }
    public String getIdentifier() {
        return displayName;
    }

    public void setIdentifier(String displayName) {
        this.displayName = displayName;
    }

    public List<Tanaman> getTanaman() {
        return plants;
    }

    public void addTanaman(Tanaman plants) {
        this.plants.add(plants);
    }

    public void removeTanaman(Tanaman plants) {
        this.plants.remove(plants);
    }

    public List<Zombie> getZombies() {
        return zombies;
    }

    public void addZombie(Zombie zombie) {
        this.zombies.add(zombie);
    }

    public void removeZombie(Zombie zombie) {
        this.zombies.remove(zombie);
    }

    

    // Method untuk mengecek apakah tile kosong
    public boolean isEmpty() {
        return plants.isEmpty() && zombies.isEmpty();
    }
}