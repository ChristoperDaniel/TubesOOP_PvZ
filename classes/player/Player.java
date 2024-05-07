package classes.player;

public class Player {
    private String namaLengkap;

    // Constructor
    public Player(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    // mendapatkan nama lengkap
    public String get_namaLengkap() {
        return namaLengkap;
    }

    // mengatur nama lengkap
    public void set_namaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    // menanam tanaman
    public void menanam() {
        // Implementasi logika menanam tanaman
        //soon
    }

    // menggali tanaman
    public void menggali() {
        // Implementasi logika menggali tanaman
        //soon
    }

    // menampilkan daftar aksi
    public void displayListActions() {
        System.out.println("Actions:");
        System.out.println("1. Menanam tanaman"); //cth
        System.out.println("2. Menggali tanaman"); //cth
        //soon
    }
}

