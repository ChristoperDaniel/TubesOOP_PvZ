public class Peashooter extends Tanaman implements Serangan{
    public Peashooter() {
        super("Peashooter", 100, 100, 25, 4, -1, 10);
    }

    @Override
    public void serang(Map map, int x, int y) {
        if (statusTanaman()) {
            int detikgames = 200;
            int i = 0;
            while (i <= detikgames) {
                List<Zombie> kosong = new ArrayList<>();
                List<Tile> baris = map.getBaris(y);
                for (Tile tiles : baris) {
                    if (!tiles.getZombies().isEmpty()) {
                        // Ambil zombie terdepan
                        Zombie zombieTerdepan = tiles.getZombies().get(0);
                        // Serang setiap attack_speedTanaman detik sekali
                        zombieTerdepan.setHealthZombie(zombieTerdepan.getHealthZombie() - this.getAttackDamageTanaman());
                        if (zombieTerdepan.getHealthZombie() <= 0) {
                            tiles.removeZombie(zombieTerdepan);
                        }
                    }
                }
                i++;
            }
        }
    }
}