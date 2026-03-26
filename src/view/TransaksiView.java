package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentListener;


public class TransaksiView extends JPanel {

    // ==========================================
    // 1. ATRIBUT SESUAI STRUKTUR
    // ==========================================
    private JTextField txtNamaPelanggan;
    private JTextField txtPlatNomor;
    private JTextField txtNamaMekanik;
    
    private JTextField txtSearchJasa;
    private JPopupMenu popupSaranJasa;
    private JButton btnTambahJasa;
    
    private JTextField txtNamaJasaManual;
    private JTextField txtTarifJasaManual;
    private JButton btnTambahManual;
    
    private JTextField txtSearchSparepart;
    private JPopupMenu popupSaranSparepart;
    private JTextField txtKuantitas;
    private JButton btnTambahSparepart;
    
  
    private JLabel lblTotalBiaya;
    private JButton btnSelesaikanTransaksi;
    
    private JPanel pnlDaftarItem; 
    private JScrollPane scrollDaftarItem; 
    // Palet Warna (Mengikuti referensi DashboardView & Screenshot)
    private final Color COLOR_BG_MAIN = new Color(14, 15, 19);
    private final Color COLOR_BG_PANEL = new Color(26, 27, 36);
    private final Color COLOR_INPUT_BG = new Color(14, 15, 19);
    private final Color COLOR_TEXT_NORMAL = new Color(200, 200, 200);
    private final Color COLOR_ACCENT = new Color(255, 60, 90); // Pink/Merah aksen
    private final Color COLOR_BTN_NORMAL = new Color(50, 52, 68);

    // ==========================================
    // 2. CONSTRUCTOR
    // ==========================================
    public TransaksiView() {
        setLayout(new BorderLayout(20, 20));
        setBackground(COLOR_BG_MAIN);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // -- INISIALISASI KOMPONEN --
        initKomponen();

        JPanel pnlAtas = new JPanel();
        pnlAtas.setLayout(new BoxLayout(pnlAtas, BoxLayout.Y_AXIS));
        pnlAtas.setBackground(COLOR_BG_MAIN);
        
        // Judul Halaman
        JLabel lblTitle = new JLabel("Transaksi Kasir");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        pnlAtas.add(lblTitle);
        pnlAtas.add(Box.createVerticalStrut(20));
        pnlAtas.add(buatPanelInformasi());
        pnlAtas.add(Box.createVerticalStrut(20));
        pnlAtas.add(buatPanelTambahItem());

        add(pnlAtas, BorderLayout.NORTH);
        add(buatPanelTabel(), BorderLayout.CENTER);
        add(buatPanelBawah(), BorderLayout.SOUTH);
    }

    // ==========================================
    // 3. INISIALISASI & DESAIN KOMPONEN (UI HELPER)
    // ==========================================
    private void initKomponen() {
        txtNamaPelanggan = buatTextField("Masukkan nama pelanggan");
        txtPlatNomor = buatTextField("Contoh: KB 1234 XX");
        txtNamaMekanik = buatTextField("Nama mekanik yang bertugas");

        txtSearchJasa = buatTextField("-- Ketik Jasa --");
        popupSaranJasa = new JPopupMenu();
        btnTambahJasa = buatButton("+ Tambah Jasa", COLOR_BTN_NORMAL);

        txtNamaJasaManual = buatTextField("Ketik nama jasa manual...");
        txtTarifJasaManual = buatTextField("Contoh: 25000");
        btnTambahManual = buatButton("+ Tambah Manual", COLOR_BTN_NORMAL);

        txtSearchSparepart = buatTextField("-- Ketik Sparepart --");
        popupSaranSparepart = new JPopupMenu();
        txtKuantitas = buatTextField("1");
        btnTambahSparepart = buatButton("+ Tambah Sparepart", COLOR_BTN_NORMAL);

        lblTotalBiaya = new JLabel("RP 0");
        lblTotalBiaya.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTotalBiaya.setForeground(COLOR_ACCENT);
        
        btnSelesaikanTransaksi = buatButton("✓ Selesaikan Transaksi", COLOR_ACCENT);
        btnSelesaikanTransaksi.setPreferredSize(new Dimension(200, 40));
    }

    private JPanel buatPanelInformasi() {
        JPanel pnl = buatSectionPanel("Informasi Pelanggan & Kendaraan");
        pnl.setLayout(new GridLayout(2, 3, 15, 5));

        pnl.add(buatLabel("Nama Pelanggan"));
        pnl.add(buatLabel("Nomor Kendaraan"));
        pnl.add(buatLabel("Nama Mekanik"));
        pnl.add(txtNamaPelanggan);
        pnl.add(txtPlatNomor);
        pnl.add(txtNamaMekanik);

        return pnl;
    }

    private JPanel buatPanelTambahItem() {
        JPanel pnl = buatSectionPanel("Tambah Jasa & Sparepart");
        pnl.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 10, 5);

        // Baris 1: Jasa DB
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.8; gbc.gridwidth = 2;
        pnl.add(buatLabel("Cari Jasa (Dari Database)"), gbc);
        gbc.gridy = 1; 
        pnl.add(txtSearchJasa, gbc);
        gbc.gridx = 2; gbc.weightx = 0.2; gbc.gridwidth = 1;
        pnl.add(btnTambahJasa, gbc);

        // Baris 2: Jasa Manual
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.5;
        pnl.add(buatLabel("Nama Jasa (Manual)"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.3;
        pnl.add(buatLabel("Tarif Jasa (Rp)"), gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        pnl.add(txtNamaJasaManual, gbc);
        gbc.gridx = 1;
        pnl.add(txtTarifJasaManual, gbc);
        gbc.gridx = 2; gbc.weightx = 0.2;
        pnl.add(btnTambahManual, gbc);

        // Baris 3: Sparepart
        gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 0.5;
        pnl.add(buatLabel("Cari Sparepart"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.3;
        pnl.add(buatLabel("Kuantitas"), gbc);
        
        gbc.gridx = 0; gbc.gridy = 5;
        pnl.add(txtSearchSparepart, gbc);
        gbc.gridx = 1;
        pnl.add(txtKuantitas, gbc);
        gbc.gridx = 2; gbc.weightx = 0.2;
        pnl.add(btnTambahSparepart, gbc);

        return pnl;
    }

    private JPanel buatPanelTabel() {
    JPanel pnl = buatSectionPanel("Daftar Item Transaksi");
    pnl.setLayout(new BorderLayout(0, 10));

    // Inisialisasi Panel Dinamis dengan BoxLayout (Menumpuk ke bawah)
    pnlDaftarItem = new JPanel();
    pnlDaftarItem.setLayout(new BoxLayout(pnlDaftarItem, BoxLayout.Y_AXIS));
    pnlDaftarItem.setBackground(COLOR_BG_PANEL);

    // Masukkan ke dalam ScrollPane
    scrollDaftarItem = new JScrollPane(pnlDaftarItem);
    scrollDaftarItem.getViewport().setBackground(COLOR_BG_PANEL);
    scrollDaftarItem.setBorder(BorderFactory.createEmptyBorder());
    scrollDaftarItem.getVerticalScrollBar().setUnitIncrement(16); // Agar scroll mouse mulus
    
    pnl.add(scrollDaftarItem, BorderLayout.CENTER);

    return pnl;
    }

    private JPanel buatPanelBawah() {
        JPanel pnl = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        pnl.setBackground(COLOR_BG_PANEL);
        pnl.setBorder(new LineBorder(new Color(40, 42, 54), 1, true));

        JLabel lblTextTotal = new JLabel("TOTAL BIAYA: ");
        lblTextTotal.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTextTotal.setForeground(COLOR_TEXT_NORMAL);

        pnl.add(lblTextTotal);
        pnl.add(lblTotalBiaya);
        pnl.add(btnSelesaikanTransaksi);

        return pnl;
    }

    // --- Method Desain Khusus ---
    private JPanel buatSectionPanel(String title) {
        JPanel pnl = new JPanel();
        pnl.setBackground(COLOR_BG_PANEL);
        TitledBorder border = BorderFactory.createTitledBorder(
                new LineBorder(COLOR_BG_PANEL), title);
        border.setTitleColor(Color.WHITE);
        border.setTitleFont(new Font("Segoe UI", Font.BOLD, 14));
        pnl.setBorder(BorderFactory.createCompoundBorder(
                border, new EmptyBorder(10, 15, 15, 15)));
        return pnl;
    }

    private JTextField buatTextField(String tooltip) {
        JTextField txt = new JTextField();
        txt.setBackground(COLOR_INPUT_BG);
        txt.setForeground(Color.WHITE);
        txt.setCaretColor(Color.WHITE);
        txt.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(60, 60, 70)),
                new EmptyBorder(8, 10, 8, 10)));
        txt.setToolTipText(tooltip);
        return txt;
    }

    private JButton buatButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setBorder(new EmptyBorder(10, 15, 10, 15));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private JLabel buatLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setForeground(COLOR_TEXT_NORMAL);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        return lbl;
    }

   

    // ==========================================
    // 4. METHOD SESUAI STRUKTUR (GETTER & SETTER UI)
    // ==========================================
    public String getNamaPelanggan() { return txtNamaPelanggan.getText(); }
    public String getPlatNomor() { return txtPlatNomor.getText(); }
    public String getNamaMekanik() { return txtNamaMekanik.getText(); }
    public String getSearchJasa() { return txtSearchJasa.getText(); }
    public String getNamaJasaManual() { return txtNamaJasaManual.getText(); }
    
    public double getTarifJasaManual() {
        try { return Double.parseDouble(txtTarifJasaManual.getText()); } 
        catch (NumberFormatException e) { return 0; }
    }
    
    public String getSearchSparepart() { return txtSearchSparepart.getText(); }
    
    public int getKuantitas() {
        try { return Integer.parseInt(txtKuantitas.getText()); } 
        catch (NumberFormatException e) { return 1; } // Default 1
    }

    public void tampilkanSaranJasa(List<String> listJasa) {
    // 1. Bersihkan sisa menu sebelumnya
    popupSaranJasa.removeAll();
    
        // 2. Jika tidak ada hasil pencarian (kosong), sembunyikan popup dan berhenti di sini
        if (listJasa.isEmpty()) {
            popupSaranJasa.setVisible(false);
            return;
        }
    
        // 3. Masukkan hasil pencarian baru ke dalam menu
        for (String jasa : listJasa) {
            JMenuItem item = new JMenuItem(jasa);
            item.addActionListener(e -> {
                txtSearchJasa.setText(jasa);
                popupSaranJasa.setVisible(false); // Otomatis menutup popup setelah dipilih
            });
            popupSaranJasa.add(item);
        }
    
    // 4. KUNCI PERTAMA: Set supaya tidak mencuri fokus (taruh SEBELUM di-show)
    popupSaranJasa.setFocusable(false); 

    // 5. Tampilkan popup HANYA JIKA kursor memang sedang berada di kotak pencarian
    if (txtSearchJasa.isFocusOwner()) {
        popupSaranJasa.show(txtSearchJasa, 0, txtSearchJasa.getHeight());
        
        // 6. KUNCI KEDUA: Paksa kursor berkedip lagi di kotak teks!
        txtSearchJasa.requestFocusInWindow();
    }
}
    public void tampilkanSaranSparepart(List<String> listSparepart) {
        popupSaranSparepart.removeAll();
        for (String sp : listSparepart) {
            JMenuItem item = new JMenuItem(sp);
            item.addActionListener(e -> txtSearchSparepart.setText(sp));
            popupSaranSparepart.add(item);
        }
        if (!listSparepart.isEmpty() && txtSearchSparepart.isFocusOwner()) {
            popupSaranSparepart.show(txtSearchSparepart, 0, txtSearchSparepart.getHeight());
        }
    }

   

    public void setTotalBiaya(double total) {
        lblTotalBiaya.setText("RP " + String.format("%,.0f", total));
    }

    public void tampilkanStruk(String dataStruk) {
        // Bisa menggunakan JOptionPane berukuran besar atau JDialog khusus
        JTextArea txtStruk = new JTextArea(dataStruk);
        txtStruk.setEditable(false);
        txtStruk.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JOptionPane.showMessageDialog(this, new JScrollPane(txtStruk), "Struk Transaksi", JOptionPane.INFORMATION_MESSAGE);
    }

    public void tampilkanPesan(String pesan) {
        JOptionPane.showMessageDialog(this, pesan);
    }


    // ==========================================
    // 5. METHOD LISTENER UNTUK CONTROLLER
    // ==========================================
    public void addKetikJasaListener(DocumentListener listener) {
        txtSearchJasa.getDocument().addDocumentListener(listener);
    }

    public void addKetikSparepartListener(DocumentListener listener) {
        txtSearchSparepart.getDocument().addDocumentListener(listener);
    }

    public void addTambahJasaListener(ActionListener listener) {
        btnTambahJasa.addActionListener(listener);
    }

    public void addTambahManualListener(ActionListener listener) {
        btnTambahManual.addActionListener(listener);
    }

    public void addTambahSparepartListener(ActionListener listener) {
        btnTambahSparepart.addActionListener(listener);
    }

  
    public void addSelesaikanTransaksiListener(ActionListener listener) {
        btnSelesaikanTransaksi.addActionListener(listener);
    }


    // ==========================================
    // METHOD UNTUK MEMBERSIHKAN KOTAK INPUT
    // ==========================================

    // 1. Kosongkan kotak pencarian Jasa DB
    public void bersihkanInputJasa() {
        txtSearchJasa.setText("");
        txtSearchJasa.requestFocus(); // Biar kursor langsung kedap-kedip di sini lagi
    }

    // 2. Kosongkan kotak Jasa Manual
    public void bersihkanInputJasaManual() {
        txtNamaJasaManual.setText("");
        txtTarifJasaManual.setText("");
    }

    // 3. Kosongkan kotak Sparepart
    public void bersihkanInputSparepart() {
        txtSearchSparepart.setText("");
        txtKuantitas.setText(""); // Atau bisa diset default "1": txtKuantitas.setText("1");
        txtSearchSparepart.requestFocus();
    }

    // 4. Kosongkan SEMUANYA (Biasanya dipakai setelah klik tombol "Selesaikan Transaksi")
    public void bersihkanSemuaInput() {
        bersihkanInputJasa();
        bersihkanInputJasaManual();
        bersihkanInputSparepart();
        // Kalau ada txtNamaPelanggan atau txtPlatNomor, tambahkan juga di sini:
        // txtNamaPelanggan.setText("");
        // txtPlatNomor.setText("");
    }



    private JPanel buatBarisItem(String jenis, String nama, String harga, String qty, String subtotal, int index, String tipeItem, Consumer<String> onDelete) {
    JPanel pnlRow = new JPanel(new GridLayout(1, 6, 10, 0));
    pnlRow.setBackground(new Color(36, 38, 50)); // Warna baris sedikit lebih terang
    pnlRow.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(50, 52, 68))); // Garis bawah
    pnlRow.setPreferredSize(new Dimension(800, 50));
    pnlRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

    // Badge Jenis dengan HTML
    String color = jenis.equals("Sparepart") ? "#E67E22" : "#2980B9"; 
    JLabel lblJenis = new JLabel("<html><div style='background-color:"+color+"; color:white; padding:5px 15px; border-radius:5px;'>"+jenis+"</div></html>");
    lblJenis.setHorizontalAlignment(SwingConstants.CENTER);

    // Teks biasa
    JLabel lblNama = buatLabel(nama);
    JLabel lblHarga = buatLabel(harga);
    JLabel lblQty = buatLabel(qty);
    JLabel lblSubtotal = buatLabel(subtotal);

    // Tombol Hapus (Tong Sampah)
    JButton btnHapusRow = new JButton("🗑");
    btnHapusRow.setBackground(new Color(231, 76, 60)); // Merah
    btnHapusRow.setForeground(Color.WHITE);
    btnHapusRow.setFocusPainted(false);
    btnHapusRow.setBorderPainted(false);
    btnHapusRow.setCursor(new Cursor(Cursor.HAND_CURSOR));
    
    // Kirim trigger ke Controller saat tombol diklik
    btnHapusRow.addActionListener(e -> onDelete.accept(tipeItem + "_" + index));

    pnlRow.add(lblJenis);
    pnlRow.add(lblNama);
    pnlRow.add(lblHarga);
    pnlRow.add(lblQty);
    pnlRow.add(lblSubtotal);
    pnlRow.add(btnHapusRow);

    return pnlRow;
}

public void renderDaftarKeranjang(List<Object[]> listJasa, List<Object[]> listSparepart, Consumer<String> onDelete) {
    pnlDaftarItem.removeAll(); 

    // Header Kolom
    JPanel pnlHeader = new JPanel(new GridLayout(1, 6, 10, 0));
    pnlHeader.setOpaque(false);
    pnlHeader.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
    pnlHeader.add(buatLabel("JENIS"));
    pnlHeader.add(buatLabel("NAMA ITEM"));
    pnlHeader.add(buatLabel("HARGA SATUAN"));
    pnlHeader.add(buatLabel("QTY"));
    pnlHeader.add(buatLabel("SUBTOTAL"));
    pnlHeader.add(buatLabel("AKSI"));
    pnlDaftarItem.add(pnlHeader);

    // Looping Jasa
    for (int i = 0; i < listJasa.size(); i++) {
        Object[] j = listJasa.get(i);
        JPanel row = buatBarisItem("Jasa", j[0].toString(), j[1].toString(), j[2].toString(), j[3].toString(), i, "JASA", onDelete);
        pnlDaftarItem.add(row);
    }

    // Looping Sparepart
    for (int i = 0; i < listSparepart.size(); i++) {
        Object[] s = listSparepart.get(i);
        JPanel row = buatBarisItem("Sparepart", s[0].toString(), s[1].toString(), s[2].toString(), s[3].toString(), i, "SPAREPART", onDelete);
        pnlDaftarItem.add(row);
    }

    // Refresh UI
    pnlDaftarItem.revalidate();
    pnlDaftarItem.repaint();
}
}




