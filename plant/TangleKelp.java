
package plant;
public class TangleKelp extends Tanaman {
    public TangleKelp() {
        super("Tangle Kelp", 25, 100, 5000, 0, 1, 20, false);
    }
/*
    @Override
    public void serang(Map map, int x, int y) {
        if (statusTanaman()) {
            List<Tile> baris = map.getBaris(y);
            for (Tile tiles : baris) {
                // Memeriksa apakah ada zombie di baris ini
                if (!tiles.getZombies().isEmpty()) {
                    // Ambil zombie terdepan
                    Zombie zombieTerdepan = tiles.getZombies().get(0);
                    // Serang zombie terdepan
                    zombieTerdepan.setHealthZombie(zombieTerdepan.getHealthZombie() - this.getAttackDamageTanaman());
                    // Hancurkan zombie jika sudah mati
                    if (zombieTerdepan.getHealthZombie() <= 0) {
                        tiles.removeZombie(zombieTerdepan);
                    }
                }
            }
        }
    }
*/
}