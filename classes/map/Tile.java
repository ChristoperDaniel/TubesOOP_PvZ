package classes.map;

import zombie.*;
import plant.*;

import java.util.ArrayList;
import java.util.List;

public class Tile {
    private String displayName;
    private Tanaman tanaman;
    private List<Zombie> zombies;

    // Constructor
    public Tile(String displayName) {
        this.displayName = displayName;
        this.tanaman = null;
        this.zombies = new ArrayList<>();
    }

    // Getters and Setters
    public String getIdentifier() {
        return displayName;
    }

    public void setIdentifier(String displayName) {
        this.displayName = displayName;
    }

    public Tanaman getTanaman() {
        return tanaman;
    }

    public void setTanaman(Tanaman tanaman) {
        this.tanaman = tanaman;
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
        return tanaman == null && zombies.isEmpty();
    }
}
