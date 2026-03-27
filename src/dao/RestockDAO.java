package dao;

import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException; // Tambahan wajib untuk LocalDateTime
import java.sql.Timestamp;
import model.DetRestock;
import model.Restock;            

public class RestockDAO {

    // ==========================================
    // 1. ATRIBUT
    // ==========================================
    private Connection connection;

    // ==========================================
    // 2. CONSTRUCTOR
    // ==========================================
    public RestockDAO() {
        // Hapus try-catch karena getKoneksi() tidak melempar SQLException
        this.connection = DatabaseConnection.getKoneksi();
    }

    // ==========================================
    // 3. METHOD SIMPAN RIWAYAT RESTOCK
    // ==========================================
    public void simpanRiwayatRestock(Restock restockBaru, DetRestock detRestock) {
        // Query untuk tabel header menggunakan tb_restock
        String sqlRestock = "INSERT INTO tb_restock (id_user, nama_supplier, waktu_restock, total_biaya) VALUES (?, ?, ?, ?)";
        
        // Query untuk tabel detail
        String sqlDetRestock = "INSERT INTO tb_detail_restock (id_restock, id_sparepart, jumlah_restock, subtotal_restock) VALUES (?, ?, ?, ?)";

        // Query untuk update stok
        String sqlUpdateStok = "UPDATE tb_sparepart SET stok = stok + ? WHERE id_sparepart = ?";

        try {
            // Matikan auto-commit untuk memulai Transaction
            connection.setAutoCommit(false);
            
            int idRestockBaru = 0; // Variabel penampung ID otomatis dari database

            // 1. Insert ke tabel tb_restock DAN tangkap ID otomatisnya
            // 👇 TAMBAHKAN PreparedStatement.RETURN_GENERATED_KEYS 👇
            try (PreparedStatement pstRestock = connection.prepareStatement(sqlRestock, PreparedStatement.RETURN_GENERATED_KEYS)) {
                pstRestock.setInt(1, restockBaru.getIdUser());
                pstRestock.setString(2, restockBaru.getSupplier());
                pstRestock.setTimestamp(3, Timestamp.valueOf(restockBaru.getWaktuRestock())); 
                pstRestock.setInt(4, restockBaru.getBiayaRestock());
                pstRestock.executeUpdate();
                
                // 👇 AMBIL ID YANG BARU SAJA DIBUAT MYSQL 👇
                try (java.sql.ResultSet rs = pstRestock.getGeneratedKeys()) {
                    if (rs.next()) {
                        idRestockBaru = rs.getInt(1); 
                    }
                }
            }

            // 2. Insert ke tabel det_restock
            try (PreparedStatement pstDetRestock = connection.prepareStatement(sqlDetRestock)) {
                // 👇 SUDAH DIGANTI JADI setInt DAN MEMAKAI ID OTOMATIS TADI 👇
                pstDetRestock.setInt(1, idRestockBaru); 
                pstDetRestock.setInt(2, detRestock.getIdSparepart());
                pstDetRestock.setInt(3, detRestock.getJumlahRestock());
                pstDetRestock.setInt(4, detRestock.getSubTotalRest());
                pstDetRestock.executeUpdate();
            }

            // 3. Update jumlah stok di tabel sparepart
            try (PreparedStatement pstUpdateStok = connection.prepareStatement(sqlUpdateStok)) {
                pstUpdateStok.setInt(1, detRestock.getJumlahRestock());
                pstUpdateStok.setInt(2, detRestock.getIdSparepart());
                pstUpdateStok.executeUpdate();
            }

            // Jika semua query berhasil, Commit (simpan permanen)
            connection.commit();
            System.out.println("Data restock berhasil disimpan!");

        } catch (SQLException e) {
            // Jika terjadi error, Rollback (batalkan semua perubahan)
            try {
                if (connection != null) {
                    connection.rollback();
                    System.out.println("Terjadi kesalahan. Transaksi di-rollback.");
                }
            } catch (SQLException ex) {
                System.err.println("Gagal melakukan rollback: " + ex.getMessage());
            }
            throw new RuntimeException("Gagal menyimpan riwayat restock: " + e.getMessage(), e);
            
        } finally {
            // Kembalikan status auto-commit ke true
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                }
            } catch (SQLException e) {
                System.err.println("Gagal mengembalikan setAutoCommit: " + e.getMessage());
            }
        }
    }
}