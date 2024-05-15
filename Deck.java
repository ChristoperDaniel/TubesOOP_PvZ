import java.util.ArrayList;
import java.util.List;

public class Deck {
    private static final int MaxDeckSize = 6;
    private List<Tanaman> listPlants = new ArrayList<>();

    public int getMaxDeckSize(){
        return MaxDeckSize;
    }
    public int getSize(){
        return listPlants.size();
    }
    public void addPlants(Tanaman tanaman) {
        if (listPlants.size() >= MaxDeckSize) {
            System.out.println("Deck penuh. Tidak bisa menambah tanaman lagi.");
            return;
        }

        if (listPlants.contains(tanaman)) {
            System.out.println("Tanaman sudah ada di dalam deck.");
            return;
        }

        listPlants.add(tanaman);
        System.out.println(tanaman.getNamaTanaman() + " berhasil ditambahkan ke deck.");
    }

    public void swapPlants(int pos1, int pos2) {
        if (pos1 < 0 || pos1 >= listPlants.size() || pos2 < 0 || pos2 >= listPlants.size()) {
            System.out.println("Posisi tidak valid.");
            return;
        }

        Tanaman temp = listPlants.get(pos1);
        listPlants.set(pos1, listPlants.get(pos2));
        listPlants.set(pos2, temp);

        System.out.println("Tanaman berhasil ditukar posisinya.");
    }

    public Tanaman removePlants(int pos) {
        if (pos < 0 || pos >= listPlants.size())  {
            System.out.println("Posisi tidak valid.");
            return null;
        }

        Tanaman removedTanamanDeck = listPlants.remove(pos);
        System.out.println("Tanaman " + removedTanamanDeck.getNamaTanaman() + " berhasil dihapus dari deck.");
        return removedTanamanDeck;
    }

    public void displayDeckPlants() {
        System.out.println("-----------------");
        if (listPlants.isEmpty()) {
            System.out.println("Deck kosong.");
        }
        else{
            System.out.println("Isi deck tanaman:");
            for (int i = 0; i < listPlants.size(); i++) {
                System.out.println((i + 1) + ". " + listPlants.get(i).getNamaTanaman());
            }
        }
        System.out.println("-----------------");
    }
}
