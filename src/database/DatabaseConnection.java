package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // 1. Kita siapin variabel buat nampung koneksinya
    private static Connection koneksi;

    // 2. INI DIA FUNGSINYA (Method)
    public static Connection getKoneksi() {
        // Cek dulu, kalau belum ada koneksi, baru kita buat
        if (koneksi == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/pos_bengkel";
                String user = "root";
                String password = "";

                // Buka koneksi ke MySQL
                koneksi = DriverManager.getConnection(url, user, password);
                System.out.println("Yeay! Koneksi ke database berhasil.");
                
            } catch (SQLException e) {
                    System.out.println("Yah, koneksi gagal: " + e.getMessage());
                      e.printStackTrace(); // ← tambah di sini, sebelum kurung kurawal tutup
            }
        }
        // 3. Kita "kasih" atau kembalikan koneksinya ke yang manggil
        return koneksi;
    }
//     public static void main(String[] args) {
//         System.out.println("Mulai testing koneksi...");
        
//         // Panggil fungsi getKoneksi()
//         Connection testConn = getKoneksi(); 
        
//         if (testConn != null) {
//             System.out.println("STATUS: AMAN! Database siap digunakan.");
//         } else {
//             System.out.println("STATUS: ERROR! Cek XAMPP atau MySQL Connector (.jar).");
//         }
    
//     // ----------------------------------------
// }
}
