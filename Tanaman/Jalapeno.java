public class Jalapeno extends Tanaman implements Serangan {
    public Jalapeno() {
        super("Jalapeno", 125, 100, 5000, 0, 2, 30);
    }

    @Override
    public void serang(Map map, int x, int y) {
        List<Tile> baris = map.getBaris(y);
        for (Tile tiles : baris) {
            if (!tiles.getZombies().isEmpty()) {
                // Membunuh semua zombie di baris ini
                for (Zombie zombie : tiles.getZombies()) {
                    tiles.removeZombie(zombie);
                }
            }
        }
    }
}