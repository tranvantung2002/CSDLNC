import Controller.LoginController;
import View.LoginFrame;
import View.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            new LoginController(loginFrame); // Khởi tạo Controller với View
        });
    }
}