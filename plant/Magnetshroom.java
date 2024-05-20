package plant;
import zombie.*;

public class Magnetshroom extends Tanaman{
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
    public void attackPlant(Tile tile, Map map, Zombie zombie) {
        Tanaman plant;
        Tile tile;
        Map map;
        int row;

        executorService.scheduleAtFixedRate(() ->{
            List<Zombie> kosong = new ArrayList<>();
            List<Tile> baris = map.getRow(tile.getY());
            for (Tile tiles : baris) {
                if (!tiles.getZombies().isEmpty()) {
                    // Ambil zombie terdepan
                    Zombie zombieTerdepan = tiles.getZombies().get(0);
                    // Mengambil item pada zombie terdepan
                    tiles.removeItem(zombieTerdepan);
                }
            }
        } , 0, plant.getAttackSpeedTanaman(), TimeUnit.SECONDS);
    } */
}