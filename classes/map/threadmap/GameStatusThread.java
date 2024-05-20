package classes.map.threadmap;
import classes.map.Map;

public class GameStatusThread implements Runnable{
    private Map map;
    private volatile boolean running;
    long startTime;

    public GameStatusThread(Map map) {
        this.map = map;
        this.running = true;
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(1000); // Update setiap milisecond

                long currentTime = System.currentTimeMillis();
                long elapsedTime = (currentTime - startTime)/1000; //mulai dari 0
                

                // Kondisi kemenangan
                if (elapsedTime > 160 && map.getTotalZombies() == 0) {
                    System.out.println("Anda menang!");
                    stop();
                }

                // Kondisi kekalahan
                if (map.isZombieReachedFirstColumn()) {
                    System.out.println("Anda kalah!");
                    stop();
                }

                if(elapsedTime == 200 && map.getTotalZombies() != 0){
                    System.out.println("Anda kalah!");
                    stop();
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
