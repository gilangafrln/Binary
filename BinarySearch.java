public class BinarySearch {
    // Metode pencarian biner iteratif
    public static int binarySearch(int[] arr, int target) { // Fungsi ini melakukan pencarian biner pada array yang sudah terurut.
        int low = 0; // Indeks batas bawah ruang pencarian. Diinisialisasi ke awal array.
        int high = arr.length - 1; // Indeks batas atas ruang pencarian. Diinisialisasi ke akhir array.

        while (low <= high) { // Loop berlanjut selama ruang pencarian tidak kosong.
            // Cari indeks tengah
            int mid = low + (high - low) / 2; // Menghitung indeks tengah untuk menghindari potensi overflow integer.

            // Jika elemen tengah adalah target
            if (arr[mid] == target) { // Periksa apakah elemen di indeks tengah sama dengan target.
                return mid; // Jika ditemukan, kembalikan indeksnya.
            }

            // Jika target lebih kecil, abaikan setengah kanan
            if (arr[mid] > target) { // Jika target lebih kecil dari elemen di indeks tengah.
                high = mid - 1; // Kurangi ruang pencarian menjadi setengah kiri.
            }
            // Jika target lebih besar, abaikan setengah kiri
            else { // Jika target lebih besar dari elemen di indeks tengah.
                low = mid + 1; // Kurangi ruang pencarian menjadi setengah kanan.
            }
        }

        // Jika target tidak ditemukan
        return -1; // Jika target tidak ditemukan setelah ruang pencarian habis, kembalikan -1.
    }

    public static void main(String[] args) {
        int[] arr = {2, 5, 8, 12, 16, 23, 38, 45, 56, 72, 91}; // Array terurut yang akan dicari.
        int target = 23; // Nilai target yang akan dicari.

        int result = binarySearch(arr, target); // Lakukan pencarian biner.

        if (result == -1) { // Periksa hasil pencarian biner.
            System.out.println("Elemen " + target + " tidak ditemukan dalam array"); // Cetak pesan jika target tidak ditemukan.
        } else {
            System.out.println("Elemen " + target + " ditemukan pada indeks " + result); // Cetak indeks jika target ditemukan.
        }
    }
}