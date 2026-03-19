package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/pos_bengkel";
        String user = "root";
        String password = "";

        Connection koneksi = null;

        try {            
            koneksi = DriverManager.getConnection(url, user, password);
            System.out.println("Yeay! Koneksi ke database berhasil.");

            
        } catch (SQLException e) {
            System.out.println("Yah, koneksi gagal.");
            e.printStackTrace();
        } finally {
            try {
                if (koneksi != null) {
                    koneksi.close();
                    System.out.println("Koneksi ditutup.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}