import java.util.Scanner;

class Buku { // Kelas Buku untuk merepresentasikan data buku.
    String isbn;
    String judul;
    String penulis;
    int tahunTerbit;

    public Buku(String isbn, String judul, String penulis, int tahunTerbit) { //Konstruktor kelas Buku.
        this.isbn = isbn;
        this.judul = judul;
        this.penulis = penulis;
        this.tahunTerbit = tahunTerbit;
    }

    @Override
    public String toString() { //Override method toString untuk menampilkan data buku dengan format yang rapi.
        return String.format("ISBN: %s\nJudul: %s\nPenulis: %s\nTahun Terbit: %d",
                            isbn, judul, penulis, tahunTerbit);
    }
}

public class PencarianBuku { // Kelas utama untuk menjalankan program pencarian buku.
    public static void main(String[] args) {
        // Data buku yang sudah diurutkan berdasarkan ISBN
        Buku[] daftarBuku = { //Array of Buku objects, sorted by ISBN.
            new Buku("9780071606301", "Java: The Complete Reference", "Herbert Schildt", 2007),
            new Buku("9780132222204", "Effective Java", "Joshua Bloch", 2008),
            new Buku("9780132778046", "Head First Java", "Kathy Sierra & Bert Bates", 2005),
            new Buku("9780134685991", "Effective Python", "Brett Slatkin", 2019),
            new Buku("9780135957059", "Clean Code", "Robert C. Martin", 2008),
            new Buku("9780137081073", "The Clean Coder", "Robert C. Martin", 2011),
            new Buku("9780262033848", "Introduction to Algorithms", "Cormen, Leiserson, Rivest & Stein", 2009),
            new Buku("9780321356680", "Effective Java", "Joshua Bloch", 2008),
            new Buku("9780596009205", "Head First Design Patterns", "Eric Freeman & Elisabeth Robson", 2004)
        };

        Scanner scanner = new Scanner(System.in); //Scanner untuk menerima input dari user.

        System.out.println("=== SISTEM PENCARIAN BUKU PERPUSTAKAAN ===");
        System.out.print("Masukkan nomor ISBN buku yang dicari: ");
        String isbnCari = scanner.nextLine(); //Menerima input ISBN dari user.

        // Lakukan pencarian binary search
        int index = cariBukuByISBN(daftarBuku, isbnCari); //Memanggil fungsi cariBukuByISBN untuk melakukan binary search.

        System.out.println("\nHASIL PENCARIAN:");
        if (index != -1) { //Mengecek hasil pencarian.
            System.out.println("Buku ditemukan!");
            System.out.println(daftarBuku[index]); //Menampilkan data buku jika ditemukan.
        } else {
            System.out.println("Buku dengan ISBN " + isbnCari + " tidak ditemukan."); //Menampilkan pesan jika buku tidak ditemukan.
        }

        scanner.close(); //Menutup scanner.
    }

    public static int cariBukuByISBN(Buku[] daftarBuku, String isbn) { //Fungsi untuk melakukan binary search berdasarkan ISBN.
        int low = 0;
        int high = daftarBuku.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Bandingkan ISBN
            int comparison = daftarBuku[mid].isbn.compareTo(isbn); //Membandingkan ISBN menggunakan compareTo().

            // Jika ISBN sama
            if (comparison == 0) { //Jika ISBN sama, kembalikan indeksnya.
                return mid;
            }

            // Jika ISBN yang dicari lebih kecil, cari di setengah kiri
            if (comparison > 0) { //Jika ISBN yang dicari lebih kecil, cari di bagian kiri array.
                high = mid - 1;
            }
            // Jika ISBN yang dicari lebih besar, cari di setengah kanan
            else { //Jika ISBN yang dicari lebih besar, cari di bagian kanan array.
                low = mid + 1;
            }
        }

        // Jika buku tidak ditemukan
        return -1; //Jika buku tidak ditemukan, kembalikan -1.
    }
}