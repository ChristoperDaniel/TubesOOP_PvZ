package plant;
public class Squash extends Tanaman implements TanamanPenyerang{
    public Squash() {
        super("Squash", 50, 100, 5000, 0, 1, 20,false);
    }

    @Override
    public void attackPlant(Tile tile, Map map){
        Tanaman plant;
        Tile tile;
        Map map;
        int row;
        int x = getColPlant();
        int a = getColZombie();

        synchronized (tile) {
            
            boolean benar = false;
            List<Tile> baris = new ArrayList<>(List.of(
                map.getTile(plant.getRowPlant(), plant.getColPlant() + 1),
                map.getTile(plant.getRowPlant(), plant.getColPlant() - 1)
            ));
            Zombie zombiedepan1 = baris.get(0).getZombies().get(0);
            Zombie zombiedepan2 = baris.get(1).getZombies().get(0);
            if (!baris.get(0).getZombies().isEmpty()){
                zombiedepan1.setHealthZombie(zombiedepan1.getHealthZombie() - plant.getAttackDamageTanaman());
                benar = true;
            }
            else{
                zombiedepan2.setHealthZombie(zombiedepan1.getHealthZombie() - plant.getAttackDamageTanaman());
                benar = true;
            }
            if (benar){
                plant.setHealthTanaman(0);
            }
        }
    }
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