package model.login;

import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; // Pastikan import ini benar sesuai folder kamu

public class AuthService {
    private User loggedInUser = null;

    // Method login sekarang langsung cek ke Database
    public boolean login(String username, String password) {
        // 1. Ambil koneksi dari class DatabaseConnection
        System.out.println("ini mencoba saja ");
        Connection conn = DatabaseConnection.getKoneksi();
        System.out.println("ini mencoba saja ");
        String sql = "SELECT * FROM tb_user WHERE username = ? AND user_password = ?";
        
        
    
        
        try {
            // 3. Siapkan statement
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            
            // 4. Eksekusi
            ResultSet rs = ps.executeQuery();
            
            // 5. Jika data ditemukan
            if (rs.next()) {
                // Buat objek User baru dari data database
                this.loggedInUser = new User();
                this.loggedInUser.setUsername(rs.getString("username"));
                // Kamu bisa set field lain seperti nama atau role di sini
                
                System.out.println("Login berhasil untuk user: " + username);
                return true;
            }
            
        } catch (SQLException e) {
            System.out.println("Error saat login: " + e.getMessage());
        }
        
        return false; // Jika tidak ditemukan atau error
    }

    public void logout() {
        loggedInUser = null;
        System.out.println("User telah logout.");
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}