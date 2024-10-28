import View.LoginDialog;
import View.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Hiển thị màn hình login
            LoginDialog loginDialog = new LoginDialog(null);

            // Kiểm tra trạng thái đăng nhập
            if (loginDialog.isLoggedIn()) {
                // Nếu đăng nhập thành công, mở màn hình chính
                new MainFrame();
            } else {
                // Nếu không, kết thúc ứng dụng
                System.exit(0);
            }
        });
//        MainFrame mainFrame = new MainFrame();
    }
}