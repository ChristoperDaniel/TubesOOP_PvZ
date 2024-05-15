import java.util.ArrayList;
import java.util.List;

public class DeckdenganException {
    private static final int MaxDeckSize = 6;
    private List<Tanaman> listPlants = new ArrayList<>();

    // Metode-metode lain dari kelas Deck
    public int getMaxDeckSize(){
        return MaxDeckSize;
    }
    public int getSize(){
        return listPlants.size();
    }
    public void addPlants(Tanaman tanaman) throws DeckFullException, TanamanAlreadyInDeckException {
        if (listPlants.size() >= MaxDeckSize) {
            throw new DeckFullException("Deck penuh. Tidak bisa menambah tanaman lagi.");
        }

        if (listPlants.contains(tanaman)) {
            throw new TanamanAlreadyInDeckException("Tanaman sudah ada di dalam deck.");
        }

        listPlants.add(tanaman);
        System.out.println(tanaman.getNamaTanaman() + " berhasil ditambahkan ke deck.");
    }

    public void swapPlants(int pos1, int pos2) throws InvalidPositionException {
        if (pos1 < 0 || pos1 >= listPlants.size() || pos2 < 0 || pos2 >= listPlants.size()) {
            throw new InvalidPositionException("Posisi tidak valid.");
        }

        Tanaman temp = listPlants.get(pos1);
        listPlants.set(pos1, listPlants.get(pos2));
        listPlants.set(pos2, temp);

        System.out.println("Tanaman berhasil ditukar posisinya.");
    }

    public Tanaman removePlants(int pos) throws InvalidPositionException {
        if (pos < 0 || pos >= listPlants.size())  {
            throw new InvalidPositionException("Posisi tidak valid.");
        }

        Tanaman removedTanamanDeck = listPlants.remove(pos);
        System.out.println("Tanaman " + removedTanamanDeck.getNamaTanaman() + " berhasil dihapus dari deck.");
        return removedTanamanDeck;
    }

    public void displayDeckPlants() throws EmptyDeckException {
        System.out.println("-----------------");
        if (listPlants.isEmpty()) {
            throw new EmptyDeckException("Deck kosong.");
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

// Deklarasi kelas-kelas exception di luar kelas Deck
class DeckFullException extends Exception {
    public DeckFullException(String message) {
        super(message);
    }
}

class TanamanAlreadyInDeckException extends Exception {
    public TanamanAlreadyInDeckException(String message) {
        super(message);
    }
}

class InvalidPositionException extends Exception {
    public InvalidPositionException(String message) {
        super(message);
    }
}

class EmptyDeckException extends Exception {
    public EmptyDeckException(String message) {
        super(message);
    }
}
