import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private static List<Tanaman> inventory = new ArrayList<>(List.of(
        new Sunflower(),
        new Peashooter(),
        new SnowPea(),
        new Seashroom(),
        new Squash(),
        new TangleKelp(),
        new Jalapeno(),
        new Wallnut(),
        new Lilypad(),
        new Magnetshroom()
        // Tambahkan tanaman baru di sini
    ));
    private static int MaxInventorySize = 10;

    public static List<Tanaman> getInventory() {
        return inventory;
    }

    public static void addPlantsInventory(Tanaman tanaman) {
        if (inventory.size() >= MaxInventorySize) {
            System.out.println("Inventory sudah penuh.");
            return;
        }

        // Cek apakah tanaman sudah ada di dalam inventory
        if (inventory.contains(tanaman)) {
            System.out.println("Tanaman sudah ada di dalam inventory.");
            return;
        }

        inventory.add(tanaman);
        System.out.println("Tanaman berhasil ditambahkan ke inventory.");
        return;
    }

    public static void swapPlantsInventory(int pos1, int pos2) {
        if (pos1 < 0 || pos1 >= inventory.size() || pos2 < 0 || pos2 >= inventory.size()||inventory.size() > MaxInventorySize) { //ini sengaja inventory.size yak
            System.out.println("Posisi tidak valid.");
            return;
        }

        Tanaman temp = inventory.get(pos1);
        inventory.set(pos1, inventory.get(pos2));
        inventory.set(pos2, temp);

        System.out.println("Kedua tanaman berhasil ditukarkan.");
    }

    public static void removePlantsInventory(int pos) {
        if (pos < 0 || pos >= inventory.size() || inventory.size() > MaxInventorySize||inventory.size() > MaxInventorySize)  {
            System.out.println("Posisi tidak valid.");
            return;
        }

        Tanaman removedTanaman = inventory.remove(pos);
        System.out.println("Tanaman berhasil dihapus dari inventory.");
        return removedTanaman;
    }

    public static void displayInventory() {
        System.out.println("Inventory:");
        if (inventory.isEmpty()) {
            System.out.println("Inventory kosong.");
        } else {
            for (int i = 0; i < inventory.size(); i++) {
                System.out.println(i + ". " + inventory.get(i).getNamaTanaman());
            }
        }
        System.out.println("=================");
    }
}
