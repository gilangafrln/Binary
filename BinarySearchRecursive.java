public class BinarySearchRecursive {
    // Metode binary search rekursif
    public static int binarySearchRecursive(int[] arr, int target, int low, int high) {
        // Kasus dasar: elemen tidak ditemukan
        if (low > high) { //Kondisi basis rekursi: jika batas bawah lebih besar dari batas atas, berarti elemen tidak ditemukan.
            return -1;
        }

        // Cari indeks tengah
        int mid = low + (high - low) / 2; //Menghitung indeks tengah untuk menghindari overflow integer.

        // Jika elemen tengah adalah target
        if (arr[mid] == target) { //Jika elemen di indeks tengah sama dengan target, kembalikan indeks tengah.
            return mid;
        }

        // Jika target lebih kecil, cari di setengah kiri
        if (arr[mid] > target) { //Jika elemen di indeks tengah lebih besar dari target, cari di setengah kiri array.
            return binarySearchRecursive(arr, target, low, mid - 1); //Panggilan rekursif pada setengah kiri array.
        }

        // Jika target lebih besar, cari di setengah kanan
        return binarySearchRecursive(arr, target, mid + 1, high); //Panggilan rekursif pada setengah kanan array.
    }

    // Metode wrapper untuk memanggil metode rekursif
    public static int binarySearch(int[] arr, int target) { //Metode pembungkus (wrapper) untuk menyederhanakan pemanggilan fungsi rekursif.
        return binarySearchRecursive(arr, target, 0, arr.length - 1); //Memanggil fungsi rekursif dengan batas awal dan akhir array.
    }

    public static void main(String[] args) {
        int[] arr = {2, 5, 8, 12, 16, 23, 38, 45, 56, 72, 91}; //Array yang akan dicari.
        int target = 23; //Target yang akan dicari.

        int result = binarySearch(arr, target); //Memanggil fungsi pencarian biner.

        if (result == -1) { //Memeriksa hasil pencarian.
            System.out.println("Elemen " + target + " tidak ditemukan dalam array");
        } else {
            System.out.println("Elemen " + target + " ditemukan pada indeks " + result);
        }
    }
}