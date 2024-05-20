package classes.map.threadmap;

import classes.map.Map;

public class SpawnZombieThread implements Runnable {
    private Map map;
    private volatile boolean running;

    public SpawnZombieThread(Map map) {
        this.map = map;
        this.running = true;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(1); // Update setiap milisecond

                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime % 60000; // Waktu dalam siklus 60 detik

                // Panggil placeZombie dalam range detik ke 20 hingga 160, setiap 3 detik
                if (elapsedTime >= 20000 && elapsedTime <= 160000 && elapsedTime % 3000 == 0) {
                    map.placeZombie(map.listofZombies);
                    map.displayMap();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        running = false;
    }
}
