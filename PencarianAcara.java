import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

class Acara { // Kelas untuk merepresentasikan data acara.
    LocalDate tanggal;
    String nama;
    String lokasi;
    String deskripsi;

    public Acara(LocalDate tanggal, String nama, String lokasi, String deskripsi) { //Konstruktor kelas Acara.
        this.tanggal = tanggal;
        this.nama = nama;
        this.lokasi = lokasi;
        this.deskripsi = deskripsi;
    }

    @Override
    public String toString() { //Override method toString untuk menampilkan data acara dengan format yang rapi.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return String.format("Tanggal: %s\nNama Acara: %s\nLokasi: %s\nDeskripsi: %s",
                            tanggal.format(formatter), nama, lokasi, deskripsi);
    }
}

public class PencarianAcara { // Kelas utama untuk menjalankan program pencarian acara.
    public static void main(String[] args) {
        // Data acara yang sudah diurutkan berdasarkan tanggal
        Acara[] jadwalAcara = { //Array of Acara objects, sorted by date.
            new Acara(LocalDate.of(2025, 5, 10), "Workshop Java", "Ruang Pelatihan A", "Workshop dasar pemrograman Java"),
            new Acara(LocalDate.of(2025, 5, 15), "Seminar AI", "Aula Utama", "Seminar tentang perkembangan Artificial Intelligence"),
            new Acara(LocalDate.of(2025, 5, 20), "Kompetisi Coding", "Lab Komputer", "Kompetisi coding untuk mahasiswa"),
            new Acara(LocalDate.of(2025, 5, 25), "Tech Talk", "Auditorium", "Diskusi tentang teknologi terbaru"),
            new Acara(LocalDate.of(2025, 6, 1), "Career Fair", "Gedung Serbaguna", "Pameran karir bidang IT"),
            new Acara(LocalDate.of(2025, 6, 5), "Webinar Cloud Computing", "Online", "Webinar tentang teknologi cloud"),
            new Acara(LocalDate.of(2025, 6, 10), "Hackathon", "Co-Working Space", "Hackathon 24 jam"),
            new Acara(LocalDate.of(2025, 6, 15), "Workshop Database", "Ruang Pelatihan B", "Workshop database SQL dan NoSQL"),
            new Acara(LocalDate.of(2025, 6, 20), "Game Development Talk", "Ruang Multimedia", "Diskusi tentang pengembangan game")
        };

        Scanner scanner = new Scanner(System.in); //Scanner untuk menerima input dari user.

        System.out.println("=== SISTEM PENCARIAN ACARA ===");
        System.out.println("Format tanggal: yyyy-MM-dd (contoh: 2025-05-20)");
        System.out.print("Masukkan tanggal yang ingin dicari: ");
        String tanggalInput = scanner.nextLine(); //Menerima input tanggal dari user.

        try {
            // Parse input tanggal
            LocalDate tanggalCari = LocalDate.parse(tanggalInput); //Mengubah string input menjadi LocalDate object.

            // Lakukan pencarian binary search
            int index = cariAcaraByTanggal(jadwalAcara, tanggalCari); //Memanggil fungsi cariAcaraByTanggal untuk melakukan binary search.

            System.out.println("\nHASIL PENCARIAN:");
            if (index != -1) { //Mengecek hasil pencarian.
                System.out.println("Acara ditemukan pada tanggal " + tanggalInput + "!");
                System.out.println(jadwalAcara[index]); //Menampilkan data acara jika ditemukan.
            } else {
                System.out.println("Tidak ada acara yang terjadwal pada tanggal " + tanggalInput + "."); //Menampilkan pesan jika acara tidak ditemukan.
            }
        } catch (Exception e) { //Menangani exception jika format tanggal salah.
            System.out.println("Format tanggal tidak valid. Gunakan format yyyy-MM-dd.");
        }

        scanner.close(); //Menutup scanner.
    }

    public static int cariAcaraByTanggal(Acara[] jadwalAcara, LocalDate tanggal) { //Fungsi untuk melakukan binary search berdasarkan tanggal.
        int low = 0;
        int high = jadwalAcara.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Bandingkan tanggal
            if (jadwalAcara[mid].tanggal.isEqual(tanggal)) { //Jika tanggal sama, kembalikan indeksnya.
                return mid;
            }

            // Jika tanggal yang dicari lebih awal, cari di setengah kiri
            if (jadwalAcara[mid].tanggal.isAfter(tanggal)) { //Jika tanggal yang dicari lebih awal, cari di bagian kiri array.
                high = mid - 1;
            }
            // Jika tanggal yang dicari lebih akhir, cari di setengah kanan
            else { //Jika tanggal yang dicari lebih akhir, cari di bagian kanan array.
                low = mid + 1;
            }
        }

        // Jika acara tidak ditemukan
        return -1; //Jika acara tidak ditemukan, kembalikan -1.
    }
}