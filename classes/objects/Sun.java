package classes.objects;
public class Sun{
    private int totalSun = 50;

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

    // mengatur totalSun sebanyak nilai
    public void addCustomSun(int nilai) {
        totalSun += nilai;
    }

    // menginisialisasi totalSun
    public int gettotalSun() {
        return totalSun;
    }


}