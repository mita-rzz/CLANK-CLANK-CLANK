package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class RegisterView extends JPanel {

    // ==========================================
    // ATRIBUT SESUAI PERMINTAAN (TIDAK DIUBAH)
    // ==========================================
    private JTextField txtFullName;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;
    private JButton btnSignUp;
    private JButton btnToggleMataPassword;
    private JButton btnToggleMataConfirm;
    private JLabel lblLogin; 
    
    private boolean isPasswordTerlihat = false;
    private boolean isConfirmTerlihat = false;

    // --- Palet Warna Baru (Sesuai React/Tailwind) ---
    private static final Color BG_PAGE      = new Color(248, 249, 250); // bg-[#F8F9FA]
    private static final Color BG_CARD      = Color.WHITE;              // Card putih
    private static final Color BG_INPUT     = new Color(243, 243, 245); // Light gray input
    private static final Color BORDER_INPUT = new Color(0, 0, 0, 26);   // Border tipis transparan
    private static final Color COLOR_FG     = new Color(3, 2, 19);      // Text dark
    private static final Color COLOR_MUTED  = new Color(113, 113, 130); // Text gray / placeholder
    private static final Color COLOR_BLUE   = new Color(58, 176, 255);  // #3AB0FF
    private static final Color COLOR_BLUE_H = new Color(42, 159, 239);  // Hover efek biru

    // ==========================================
    // METHOD CONSTRUCTOR
    // ==========================================
    public RegisterView() {
        setBackground(BG_PAGE); 
        setLayout(new GridBagLayout()); 

        // Membuat Custom Panel Form melengkung dan berbayang tipis
        JPanel formPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // Shadow tipis
                g2.setColor(new Color(0, 0, 0, 10));
                g2.fillRoundRect(3, 5, getWidth() - 4, getHeight() - 4, 16, 16);
                // Background Card
                g2.setColor(BG_CARD);
                g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 16, 16);
                // Border Card
                g2.setColor(BORDER_INPUT);
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 16, 16);
                g2.dispose();
            }
        };
        
        formPanel.setPreferredSize(new Dimension(420, 560)); 
        formPanel.setOpaque(false); 
        formPanel.setLayout(null); 

        // --- TITLE ---
        JLabel lblTitle = new JLabel("Create Account", SwingConstants.CENTER);
        lblTitle.setForeground(COLOR_FG);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setBounds(0, 35, 420, 35);
        formPanel.add(lblTitle);

        // --- SUBTITLE ---
        JLabel lblSubtitle = new JLabel("Buat akun baru untuk memulai", SwingConstants.CENTER);
        lblSubtitle.setForeground(COLOR_MUTED);
        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSubtitle.setBounds(0, 75, 420, 20);
        formPanel.add(lblSubtitle);

        int startX = 40;
        int width = 340;
        int inputHeight = 42;

        // --- FULL NAME ---
        JLabel lblFullName = new JLabel("Full Name");
        lblFullName.setForeground(COLOR_FG);
        lblFullName.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblFullName.setBounds(startX, 120, width, 20);
        formPanel.add(lblFullName);

        txtFullName = new JTextField(); 
        txtFullName.setBounds(startX, 145, width, inputHeight);
        styleTextField(txtFullName, "Masukkan nama lengkap");
        formPanel.add(txtFullName);

        // --- USERNAME ---
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setForeground(COLOR_FG);
        lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblUsername.setBounds(startX, 200, width, 20);
        formPanel.add(lblUsername);

        txtUsername = new JTextField(); 
        txtUsername.setBounds(startX, 225, width, inputHeight);
        styleTextField(txtUsername, "Pilih username");
        formPanel.add(txtUsername);

       // --- PASSWORD ---
        JLabel lblPasswordText = new JLabel("Password");
        lblPasswordText.setForeground(COLOR_FG);
        lblPasswordText.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblPasswordText.setBounds(startX, 280, width, 20);
        formPanel.add(lblPasswordText);

        // 1. Buat Textfield-nya terlebih dahulu
        txtPassword = new JPasswordField();
        txtPassword.setBounds(startX, 305, width, inputHeight); 
        stylePasswordField(txtPassword, "Buat password");
        
        // 2. Set layout textfield ke null agar kita bisa menaruh icon di dalamnya
        txtPassword.setLayout(null); 
        formPanel.add(txtPassword);

        // 3. Buat tombol mata dan atur posisinya RELATIF terhadap textfield
        btnToggleMataPassword = new JButton("\uD83D\uDC41"); 
        // Perhatikan X-nya sekarang adalah 'width - 40' (karena dihitung dari ujung textfield)
        btnToggleMataPassword.setBounds(width - 40, 0, 40, inputHeight); 
        styleToggleButton(btnToggleMataPassword);
        
        // 4. Tambahkan tombol mata KE DALAM textfield (Bukan ke formPanel)
        txtPassword.add(btnToggleMataPassword); 


        // --- CONFIRM PASSWORD ---
        JLabel lblConfirmPasswordText = new JLabel("Confirm Password");
        lblConfirmPasswordText.setForeground(COLOR_FG);
        lblConfirmPasswordText.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblConfirmPasswordText.setBounds(startX, 360, width, 20);
        formPanel.add(lblConfirmPasswordText);

        txtConfirmPassword = new JPasswordField();
        txtConfirmPassword.setBounds(startX, 385, width, inputHeight); 
        stylePasswordField(txtConfirmPassword, "Konfirmasi password");
        txtConfirmPassword.setLayout(null); // Sama seperti di atas
        formPanel.add(txtConfirmPassword);

        btnToggleMataConfirm = new JButton("\uD83D\uDC41"); 
        btnToggleMataConfirm.setBounds(width - 40, 0, 40, inputHeight);
        styleToggleButton(btnToggleMataConfirm);
        txtConfirmPassword.add(btnToggleMataConfirm); // Tambahkan ke txtConfirmPassword

        // --- SIGN UP BUTTON ---
        btnSignUp = new JButton("Sign Up") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getModel().isRollover() ? COLOR_BLUE_H : COLOR_BLUE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btnSignUp.setBounds(startX, 455, width, inputHeight);
        btnSignUp.setForeground(Color.WHITE);
        btnSignUp.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnSignUp.setBorderPainted(false);
        btnSignUp.setContentAreaFilled(false);
        btnSignUp.setFocusPainted(false);
        btnSignUp.setCursor(new Cursor(Cursor.HAND_CURSOR));
        formPanel.add(btnSignUp);

        // --- BACK TO LOGIN ---
        // Dibagi 2 bagian agar warna link berbeda sesuai desain
        Font fNormal = new Font("Segoe UI", Font.PLAIN, 13);
        Font fLink = new Font("Segoe UI", Font.BOLD, 13);
        
        String txtDont = "Sudah punya akun? ";
        String txtLogin = "Kembali ke Login";
        
        FontMetrics fmN = new JLabel().getFontMetrics(fNormal);
        FontMetrics fmL = new JLabel().getFontMetrics(fLink);
        int wDont = fmN.stringWidth(txtDont);
        int wLogin = fmL.stringWidth(txtLogin);
        int startFootX = (420 - wDont - wLogin) / 2;

        JLabel lblDontHave = new JLabel(txtDont);
        lblDontHave.setForeground(COLOR_MUTED);
        lblDontHave.setFont(fNormal);
        lblDontHave.setBounds(startFootX, 510, wDont + 5, 20);
        formPanel.add(lblDontHave);

        lblLogin = new JLabel(txtLogin);
        lblLogin.setForeground(COLOR_BLUE); 
        lblLogin.setFont(fLink);
        lblLogin.setBounds(startFootX + wDont, 510, wLogin + 10, 20);
        lblLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        formPanel.add(lblLogin);

        add(formPanel);
    }

    // ==========================================
    // METHOD HELPER STYLING UI
    // ==========================================
    private void styleTextField(JTextField textField, String placeholder) {
        textField.setBackground(BG_INPUT); 
        textField.setForeground(COLOR_MUTED);
        textField.setCaretColor(COLOR_FG); 
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        textField.setText(placeholder);

        // Membuat dua jenis border: Normal dan Saat Difokuskan (Aktif)
        javax.swing.border.Border borderNormal = BorderFactory.createCompoundBorder(
            new LineBorder(BORDER_INPUT, 1, true),
            new EmptyBorder(0, 12, 0, 12) 
        );
        javax.swing.border.Border borderFocus = BorderFactory.createCompoundBorder(
            new LineBorder(COLOR_BLUE, 2, true), // Border menjadi biru dan sedikit lebih tebal
            new EmptyBorder(0, 11, 0, 11) // Padding disesuaikan karena border menebal
        );

        textField.setBorder(borderNormal); // Set border awal

        // Logic efek fokus & Placeholder
        textField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                textField.setBorder(borderFocus); // Efek menyala saat diklik
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(COLOR_FG);
                }
            }
            public void focusLost(FocusEvent e) {
                textField.setBorder(borderNormal); // Kembali normal saat dilepas
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(COLOR_MUTED);
                }
            }
        });
    }
    private void stylePasswordField(JPasswordField passField, String placeholder) {
        passField.setBackground(BG_INPUT); 
        passField.setForeground(COLOR_MUTED);
        passField.setCaretColor(COLOR_FG); 
        passField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        // Membuat dua jenis border untuk Password (dengan padding kanan 40px untuk ikon mata)
        javax.swing.border.Border borderNormal = BorderFactory.createCompoundBorder(
            new LineBorder(BORDER_INPUT, 1, true),
            new EmptyBorder(0, 12, 0, 40) 
        );
        javax.swing.border.Border borderFocus = BorderFactory.createCompoundBorder(
            new LineBorder(COLOR_BLUE, 2, true), // Border menjadi biru
            new EmptyBorder(0, 11, 0, 39) 
        );

        passField.setBorder(borderNormal);
        passField.setEchoChar((char) 0);
        passField.setText(placeholder);

        passField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                passField.setBorder(borderFocus); // Efek menyala saat diklik
                String pwd = new String(passField.getPassword());
                if (pwd.equals(placeholder)) {
                    passField.setText("");
                    passField.setForeground(COLOR_FG);
                    if (!isPasswordTerlihat) passField.setEchoChar('●');
                }
            }
            public void focusLost(FocusEvent e) {
                passField.setBorder(borderNormal); // Kembali normal saat dilepas
                String pwd = new String(passField.getPassword());
                if (pwd.isEmpty()) {
                    passField.setEchoChar((char) 0);
                    passField.setText(placeholder);
                    passField.setForeground(COLOR_MUTED);
                }
            }
        });
    }

  private void styleToggleButton(JButton btn) {
        // Ganti Font-nya agar bentuk matanya lebih proporsional
        btn.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14)); 
        btn.setForeground(COLOR_MUTED);
        btn.setBorder(BorderFactory.createEmptyBorder());
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    // ==========================================
    // METHOD SESUAI PERMINTAAN KELAS (TIDAK DIUBAH)
    // ==========================================
    
    // Perbaikan sedikit di getter agar tidak mengirim placeholder sebagai value
    public String getFullName() { 
        String val = txtFullName.getText();
        return val.equals("Masukkan nama lengkap") ? "" : val; 
    }
    
    public String getUsername() { 
        String val = txtUsername.getText();
        return val.equals("Pilih username") ? "" : val;
    }
    
    public String getPassword() { 
        String val = new String(txtPassword.getPassword());
        return val.equals("Buat password") ? "" : val;
    }
    
    public String getConfirmPassword() { 
        String val = new String(txtConfirmPassword.getPassword());
        return val.equals("Konfirmasi password") ? "" : val;
    }

    public void toggleMataPassword() {
        isPasswordTerlihat = !isPasswordTerlihat;
        if (isPasswordTerlihat) {
            txtPassword.setEchoChar((char) 0);
        } else {
            // Cek agar placeholder tidak tersensor
            if (!getPassword().isEmpty()) txtPassword.setEchoChar('●');
        }
    }

    public void toggleMataConfirm() {
        isConfirmTerlihat = !isConfirmTerlihat;
        if (isConfirmTerlihat) {
            txtConfirmPassword.setEchoChar((char) 0);
        } else {
            if (!getConfirmPassword().isEmpty()) txtConfirmPassword.setEchoChar('●');
        }
    }

    public void tampilkanPesan(String pesan) {
        JOptionPane.showMessageDialog(this, pesan);
    }

    public void bersihkanInput() {
        txtFullName.setText("Masukkan nama lengkap");
        txtFullName.setForeground(COLOR_MUTED);
        
        txtUsername.setText("Pilih username");
        txtUsername.setForeground(COLOR_MUTED);
        
        txtPassword.setText("Buat password");
        txtPassword.setForeground(COLOR_MUTED);
        txtPassword.setEchoChar((char) 0);
        
        txtConfirmPassword.setText("Konfirmasi password");
        txtConfirmPassword.setForeground(COLOR_MUTED);
        txtConfirmPassword.setEchoChar((char) 0);
    }

    public void addSignUpListener(ActionListener listener) {
        btnSignUp.addActionListener(listener);
    }

    public void addBackToLoginListener(ActionListener listener) {
        // Kosong sesuai bawaan kode asli
    }

    // ==========================================
    // GETTER TAMBAHAN AGAR COCOK DGN REGISTERCONTROLLER
    // ==========================================
    public String getNamaLengkap() { return getFullName(); } // Disesuaikan ke getter utama yg sdh handle placeholder
    public JButton getBtnRegister() { return btnSignUp; } 
    public JButton getBtnTogglePassword() { return btnToggleMataPassword; }
    public JButton getBtnToggleConfirmPassword() { return btnToggleMataConfirm; }
    public JLabel getLblLogin() { return lblLogin; } 
    
    public JTextField getTxtNamaLengkap() { return txtFullName; }
    public JTextField getTxtUsername() { return txtUsername; }
    public JPasswordField getTxtPassword() { return txtPassword; }
    public JPasswordField getTxtConfirmPassword() { return txtConfirmPassword; }
}