package plant;

public class SnowPea extends Tanaman {
    //private SlowingEffect slowingEffect;

    public SnowPea() {
        super("Snow Pea", 175, 100, 25, 4, -1, 10,false);
        /*this.slowingEffect = new SlowingEffect(0.5f, 3000);*/
    }
/*
    @Override
    public void attackPlant(Tile tile, Map map) {
        Tanaman plant;
        Tile tile;
        Map map;
        int row;
        int x = getColPlant();
<<<<<<< HEAD
        int a = getColZombie();
        /*
=======

>>>>>>> 8d3d6166ffabac5e7fdebc75dec0674c8cd5ca1b
        executorService.scheduleAtFixedRate(() ->{
            List<Zombie> kosong = new ArrayList<>();
            List<Tile> baris = map.getRow(tile.getY());
            for (Tile tiles : baris) {
                if (!tiles.getZombies().isEmpty()) {
                    int a = getColZombie();
                    if (a >= x){
                        for (Zombie zombie : tiles.getZombies()){
                            tiles.setHealthZombie(tiles.getHealthZombie() - plant.getAttackDamageTanaman());
                            SetIsGetSlowed(true);
                            if (tiles.getHealthZombie() <= 0) {
                                tiles.removeZombie(zombie);
                            }
                        }
                    }
                }
            }
        } , 0, plant.getAttackSpeedTanaman(), TimeUnit.SECONDS);
    }*/
}
/*
    @Override
    public void serang(Map map, int x, int y) {
        if (statusTanaman()) {
            int detikgames = 200;
            int i = 0;
            private ScheduledExecutorService executorService;
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
*/