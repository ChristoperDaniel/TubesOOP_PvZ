package classes.map.threadmap;
import classes.objects.*;
public class UpdateSunThread implements Runnable {
    private volatile boolean running;
    private volatile boolean gameOver;
    private boolean isDayTime;
    private long nextSunInterval;
    private Sun sun;

    public UpdateSunThread(Sun sun) {
        this.sun = sun;
        this.running = true;
        this.gameOver = false;
        this.isDayTime = true;
        this.nextSunInterval = getRandomSunInterval();
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(1); // Update setiap milisecond

                long currentTime = System.currentTimeMillis() % 1000; // Sesuaikan dengan siklus yang Anda inginkan

                if ((!gameOver || !Thread.currentThread().isInterrupted()) && isDayTime && currentTime % nextSunInterval == 0 && currentTime <= 100) {
                    sun.addSun(); // Menambahkan 25 Sun
                    nextSunInterval = getRandomSunInterval(); // Mengatur ulang interval acak berikutnya
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
        return (long) (Math.random() * 100 + 1); // Contoh interval acak antara 1 dan 100 milisecond
    }
}
