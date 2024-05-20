package plant;
public class Squash extends Tanaman {
    public Squash() {
        super("Squash", 50, 100, 5000, 0, 1, 20,false);
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
                    
                    // Mencari column dari zombie dan tanaman

                    // Membunuh satu zombie yang ada di satu column depan tanaman berada

                    // Kalau ga ada di depannya, bunuh satu zombie terdepan yang ada di column yang sama dengan tanaman berada
                    
                    // Kalau ga ada di depan atau di column yang sama, bunuh satu zombie terdepan yang ada di satu column setelah tanaman berada
                    
                    setHealthTanaman(0);
                }
            }
        } , 0, plant.getAttackSpeedTanaman(), TimeUnit.SECONDS);
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