package plant;


public class Magnetshroom extends Tanaman {
    public Magnetshroom() {
        super("Magnetshroom", 100, 100, 0, 10000, -1, 20000, false);
    }

    // public void removeItem(Zombie zombie){
    //     if (!zombie.getIsItemRemovedZombie()) {
    //         zombie.setIsItemRemovedZombie(true);
    //     }   
    // }
}

//     @Override
//     public void attackPlant(Tile tile, Map map) {
//         Tanaman plant;
//         int row;
//         int x = getColPlant();

//         executorService.scheduleAtFixedRate(() ->{
//             List<Zombie> kosong = new ArrayList<>();
//             List<Tile> baris = map.getRow(tile.getY());
//             for (Tile tiles : baris) {
//                 if (!tiles.getZombies().isEmpty() && !tiles.getZombies().getIsItemRemovedZombie()) {
//                     if (a >= x){
//                         for (Zombie zombie : tiles.getZombies()){
//                             int a = getColZombie();
//                             // Mengambil item pada zombie terdepan
//                             tiles.removeItem(zombie);
//                         }
//                     }
//                 }
//             }
//         } , 0, plant.getAttackSpeedTanaman(), TimeUnit.SECONDS);
//     } 
// }