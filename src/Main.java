import com.formdev.flatlaf.FlatLightLaf;
import controller.DashboardController;

import controller.LoginController;
import controller.RegisterController;
import controller.TransaksiController;
import main.MainFrame;

import javax.swing.*;
import view.DashboardView;

import view.LoginView;
import view.RegisterView;
import view.TransaksiView;

public class Main {
    public static void main(String[] args) {
        // 1. Buat Jendela Utama (MainFrame) yang bertindak sebagai "Meja"
        MainFrame mainFrame = new MainFrame();
        LoginView loginView = new LoginView();
        RegisterView registerView = new RegisterView();
        DashboardView dashboardView = new DashboardView();
        // TransaksiView transaksiView = new TransaksiView();


        mainFrame.tambahHalaman(loginView, "HALAMAN_LOGIN");
        mainFrame.tambahHalaman(registerView, "HALAMAN_REGISTER");
        mainFrame.tambahHalaman(dashboardView, "HALAMAN_DASHBOARD");
        // mainFrame.tambahHalaman(transaksiView, "HALAMAN_TRANSAKSI");

        LoginController loginController = new LoginController(loginView, mainFrame);
        RegisterController registerController = new RegisterController(registerView, mainFrame);
        DashboardController dashboardController = new DashboardController(dashboardView, mainFrame);
        // TransaksiController transaksiController = new TransaksiController(transaksiView ,mainFrame);



        mainFrame.tampilkanHalaman("HALAMAN_DASHBOARD");    

        mainFrame.setVisible(true);
    }
}