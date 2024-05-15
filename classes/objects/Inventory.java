package classes.objects;
import java.util.ArrayList;
import java.util.List;

import plant.*;

public class Inventory {
    private List<Tanaman> inventory = new ArrayList<>(List.of(
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

    public List<Tanaman> getInventory() {
        return inventory;
    }

    public void setInventory(List<Tanaman> inventory) {
        this.inventory = inventory;
    }

    public void addPlantsInventory(Tanaman tanaman) {
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

    public void swapPlantsInventory(int pos1, int pos2) { //pos disini tuh index jadi mulainya dari 0 sampai ke inventory.size()-1
        if (pos1 < 0 || pos1 >= inventory.size() || pos2 < 0 || pos2 >= inventory.size()) { //ini sengaja inventory.size yak
            System.out.println("Posisi tidak valid.");
            return;
        }

        Tanaman temp = inventory.get(pos1);
        inventory.set(pos1, inventory.get(pos2));
        inventory.set(pos2, temp);

        System.out.println("Kedua tanaman berhasil ditukarkan.");
    }

    public Tanaman removePlantsInventory(int pos) {
        if (pos < 0 || pos >= inventory.size())  {
            System.out.println("Posisi tidak valid.");
            return null;
        }

        Tanaman removedTanaman = inventory.remove(pos);
        System.out.println("Tanaman berhasil dihapus dari inventory.");
        return removedTanaman;
    }

    public void displayInventory() {
        System.out.println("=================");
        if (inventory.isEmpty()) {
            System.out.println("Inventory kosong.");
        } else {
            System.out.println("Inventory:");
            for (int i = 0; i < inventory.size(); i++) {
                System.out.println((i+1) + ". " + inventory.get(i).getNamaTanaman());
            }
        }
        System.out.println("=================");
    }
}
