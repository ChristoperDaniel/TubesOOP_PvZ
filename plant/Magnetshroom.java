package plant;
import zombie.*;

public class Magnetshroom extends Tanaman {
    public Magnetshroom() {
        super("Magnetshroom", 100, 100, 0, 10, -1, 20, false);
    }

    public void removeItem(Zombie zombie){
        if (!zombie.getIsItemRemovedZombie()) {
            zombie.setIsItemRemovedZombie(true);
        }   
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
                if (!tiles.getZombies().isEmpty() && !tiles.getZombies().getIsItemRemovedZombie()) {
                    if (a >= x){
                        for (Zombie zombie : tiles.getZombies()){
                            // Mengambil item pada zombie terdepan
                            tiles.removeItem(zombieTerdepan);
                        }
                    }
                }
            }
        } , 0, plant.getAttackSpeedTanaman(), TimeUnit.SECONDS);
    } */
}