package classes.map.threadmap;

import classes.map.Map;
import main.*;

public class PerangThread implements Runnable {
    private Map map;
    private volatile boolean running;
    long startTime;
    Game game;

    public PerangThread(Map map, Game game) {
        this.map = map;
        this.startTime = System.currentTimeMillis();
        this.game = game;
        this.running = true;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(1000); // Update setiap milisecond

                long currentTime = System.currentTimeMillis();
                long elapsedTime = (currentTime - startTime)/1000; //mulai dari 0
                

                // Kondisi kemenangan
                if ((elapsedTime > 160 && map.getTotalZombies() == 0)||(map.isZombieReachedFirstColumn())||elapsedTime == 200) {
                    stop();
                }
                else{
                    game.perang(map);
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
