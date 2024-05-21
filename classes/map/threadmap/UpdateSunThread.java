package classes.map.threadmap;
import classes.objects.*;
public class UpdateSunThread implements Runnable {
    private volatile boolean running;
    private volatile boolean gameOver;
    private long nextSunInterval;
    private Sun sun;
    private long startTime;

    public UpdateSunThread(Sun sun) {
        this.sun = sun;
        this.running = true;
        this.gameOver = false;
        this.nextSunInterval = getRandomSunInterval();
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void run() {
        int i =0;
        while (running) {
            try {
                Thread.sleep(1000); // Update setiap milisecond

                long currentTime = System.currentTimeMillis();
                long elapsedTime = (currentTime - startTime)/1000; // Sesuaikan dengan siklus yang Anda inginkan
                
                if ((i >= nextSunInterval) && elapsedTime <= 100) {
                    sun.addSun(); // Menambahkan 25 Sun
                    nextSunInterval = getRandomSunInterval(); // Mengatur ulang interval acak berikutnya
                    i = 0;
                }
                i++;
                if (elapsedTime >= 100) {
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

    private long getRandomSunInterval() {
        // Implementasi logika untuk menghasilkan interval acak
        return 5 + (long) (Math.random() * 6); // Contoh interval acak antara 1 dan 100 milisecond
    }
}
