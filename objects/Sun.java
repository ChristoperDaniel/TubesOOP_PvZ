package objects;
public class Sun{
    private static int totalSun = 25;

    // menambahkan sun sebanyak 25
    public void addSun() {
        totalSun += 25;
    }

    // mengurangi totalSun sebanyak nilai
    public void reduceSun(int nilai) {
        totalSun -= nilai;
    }

    // menampilkan totalSun
    public void displaySun() {
        System.out.println("Total Sun: " + totalSun);
    }

    // get totalSun
    public static int get_totalSun() {
        return totalSun;
    }

    // mengatur totalSun sebanyak nilai
    public void setSun(int nilai) {
        totalSun += nilai;
    }

    // menginisialisasi totalSun
    public void initializeSun() {
        totalSun = 25;
    }
}