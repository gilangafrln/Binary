import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

// Interface untuk mendefinisikan perilaku pencarian
interface Searchable<T> { // Interface untuk membandingkan nilai dengan nilai tengah dalam binary search.
    int compare(T value); // Method untuk membandingkan nilai. Mengembalikan nilai negatif jika nilai target lebih kecil, 0 jika sama, dan positif jika lebih besar.
}

public class GenericBinarySearch { // Kelas utama untuk menjalankan program pencarian generik.

    // Metode binary search generik
    public static <T> int binarySearch(T[] array, Searchable<T> searchable) { //Metode binary search generik yang menerima array dan objek Searchable.
        int low = 0;
        int high = array.length - 1;

        while (low <= high) { //Looping selama batas bawah kurang dari atau sama dengan batas atas.
            int mid = low + (high - low) / 2; //Menghitung indeks tengah.

            int comparison = searchable.compare(array[mid]); //Membandingkan nilai target dengan nilai tengah menggunakan method compare dari interface Searchable.

            if (comparison == 0) { //Jika sama, nilai ditemukan.
                return mid; //Mengembalikan indeks nilai yang ditemukan.
            } else if (comparison < 0) { //Jika nilai target lebih kecil dari nilai tengah.
                high = mid - 1; //Mengubah batas atas menjadi mid - 1.
            } else { //Jika nilai target lebih besar dari nilai tengah.
                low = mid + 1; //Mengubah batas bawah menjadi mid + 1.
            }
        }

        return -1; //Jika nilai tidak ditemukan.
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //Scanner untuk menerima input dari user.

        System.out.println("=== SISTEM PENCARIAN DATASET ===");
        System.out.println("Pilih jenis data yang ingin dicari:");
        System.out.println("1. Integer");
        System.out.println("2. Double");
        System.out.println("3. String");
        System.out.print("Pilihan Anda (1-3): ");

        int pilihan = scanner.nextInt();
        scanner.nextLine(); // Membersihkan buffer

        switch (pilihan) {
            case 1:
                // Pencarian nilai Integer
                Integer[] dataInteger = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100}; //Array Integer.

                System.out.println("\nData Integer: " + Arrays.toString(dataInteger));
                System.out.print("Masukkan nilai integer yang dicari: ");
                int targetInt = scanner.nextInt(); //Menerima input nilai Integer dari user.

                int indexInt = binarySearch(dataInteger, new Searchable<Integer>() { //Memanggil fungsi binarySearch dengan objek anonymous class Searchable untuk Integer.
                    @Override
                    public int compare(Integer value) {
                        return targetInt - value; // Membandingkan nilai target dengan nilai tengah.
                    }
                });

                // Menampilkan hasil pencarian.
                if (indexInt != -1) {
                    System.out.println("Nilai " + targetInt + " ditemukan pada indeks " + indexInt);
                } else {
                    System.out.println("Nilai " + targetInt + " tidak ditemukan dalam dataset");
                }
                break;

            case 2:
                // Pencarian nilai Double
                Double[] dataDouble = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7, 8.8, 9.9}; //Array Double.

                System.out.println("\nData Double: " + Arrays.toString(dataDouble));
                System.out.print("Masukkan nilai double yang dicari: ");
                double targetDouble = scanner.nextDouble(); //Menerima input nilai Double dari user.

                int indexDouble = binarySearch(dataDouble, new Searchable<Double>() { //Memanggil fungsi binarySearch dengan objek anonymous class Searchable untuk Double.
                    @Override
                    public int compare(Double value) {
                        return Double.compare(targetDouble, value); //Membandingkan nilai target dengan nilai tengah menggunakan Double.compare().
                    }
                });

                // Menampilkan hasil pencarian.
                if (indexDouble != -1) {
                    System.out.println("Nilai " + targetDouble + " ditemukan pada indeks " + indexDouble);
                } else {
                    System.out.println("Nilai " + targetDouble + " tidak ditemukan dalam dataset");
                }
                break;

            case 3:
                // Pencarian nilai String
                String[] dataString = {"alpha", "beta", "delta", "gamma", "omega", "sigma", "theta", "zeta"}; //Array String.
                Arrays.sort(dataString); // Mengurutkan array String sebelum pencarian.

                System.out.println("\nData String: " + Arrays.toString(dataString));
                System.out.print("Masukkan string yang dicari: ");
                String targetString = scanner.nextLine(); //Menerima input nilai String dari user.

                int indexString = binarySearch(dataString, new Searchable<String>() { //Memanggil fungsi binarySearch dengan objek anonymous class Searchable untuk String.
                    @Override
                    public int compare(String value) {
                        return targetString.compareTo(value); //Membandingkan nilai target dengan nilai tengah menggunakan compareTo().
                    }
                });

                // Menampilkan hasil pencarian.
                if (indexString != -1) {
                    System.out.println("String \"" + targetString + "\" ditemukan pada indeks " + indexString);
                } else {
                    System.out.println("String \"" + targetString + "\" tidak ditemukan dalam dataset");
                }
                break;

            default:
                System.out.println("Pilihan tidak valid!");
        }

        scanner.close(); //Menutup scanner.
    }
}