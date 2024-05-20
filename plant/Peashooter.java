package plant;
import zombie.*;
public class Peashooter extends Tanaman{
    public Peashooter() {
        super("Peashooter", 100, 100, 25, 4, -1, 10, false);
    }
    
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
                    
                    // Serang setiap attack_speedTanaman detik sekali
                    zombieTerdepan.setHealthZombie(zombieTerdepan.getHealthZombie() - plant.getAttackDamageTanaman());
                    if (zombieTerdepan.getHealthZombie() <= 0) {
                            tiles.removeZombie(zombieTerdepan);
                    }
                }
            }
        } , 0, plant.getAttackSpeedTanaman(), TimeUnit.SECONDS);
    }
}
    
        //  public void attackPlant(Zombie zombie, int x, int y) {
        // Mengecek apakah zombie berada pada row yang sama dengan tanaman
            /*     if (zombie.get.getRow() == tiles.getRow()) {
            // Menyerang zombie dengan mengurangi health sesuai dengan attack damage tanaman
            zombie.setHealthZombie(zombie.getHealthZombie() - this.getAttackDamageTanaman());