import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import model.login.AuthService;
import model.login.LoginView;
import model.login.LoginController;

public class Main {
    public static void main(String[] args) {
        try {
            FlatLightLaf.setup();
            System.out.println("FlatLaf berhasil dimuat!"); // ← di sini
        } catch (Exception e) {
            System.out.println("FlatLaf GAGAL: " + e.getMessage()); // ← di sini
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AuthService authService = new AuthService();
                LoginView view = new LoginView();
                new LoginController(view, authService);
                view.setVisible(true);
            }
        });
    }
}