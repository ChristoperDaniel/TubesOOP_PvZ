package classes.map.threadmap;

import classes.map.Map;

public class SpawnZombieThread implements Runnable {
    private Map map;
    private volatile boolean running;
    private long startTime;

    public SpawnZombieThread(Map map) {
        this.map = map;
        this.running = true;
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(1000); // Update setiap detik

                long currentTime = System.currentTimeMillis();
                long elapsedTime = (currentTime - startTime)/1000; // Waktu yang telah berlalu sejak mulai
                System.out.println(elapsedTime);
                // Panggil placeZombie dalam range detik ke 20 hingga 160, setiap 3 detik
                if (elapsedTime >= 20 && elapsedTime <= 160 && elapsedTime % 3 == 0) {
                    map.placeZombie(map.listofZombies);
                    map.displayMap();
                }

                // Berhenti jika waktu mencapai 200 detik
                if (elapsedTime >= 200) {
                    running = false;
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
