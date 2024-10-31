package Controller;

import View.LoginFrame;
import View.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private LoginFrame loginFrame;

    public LoginController(LoginFrame loginFrame){
        this.loginFrame = loginFrame;

        loginFrame.addLoginButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });
    }
    private void performLogin() {
        String username = loginFrame.getUserName();
        String password = loginFrame.getPassword();

        // Kiểm tra thông tin đăng nhập
        if (username.equals("admin") && password.equals("123456")) {
            int result = JOptionPane.showConfirmDialog(loginFrame,
                    "Đăng nhập thành công!", "Thông báo",
                    JOptionPane.DEFAULT_OPTION);

            // Chỉ đặt isLogin = true nếu người dùng nhấn OK
            if (result == JOptionPane.OK_OPTION) {
                loginFrame.dispose(); // Đóng cửa sổ đăng nhập
                new MainFrame(); // Mở màn hình chính
            }
        } else {
            JOptionPane.showMessageDialog(loginFrame, "Sai tên đăng nhập hoặc mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

}
