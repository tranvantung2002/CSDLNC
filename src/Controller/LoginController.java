package Controller;

import View.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private final LoginView view;

    public LoginController(LoginView view) {
        this.view = view;

        // Gắn sự kiện cho nút đăng nhập
        this.view.addLoginListener(new LoginActionListener());
    }

    // Lớp xử lý sự kiện đăng nhập
    class LoginActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = view.getUsername();
            String password = view.getPassword();

            // Logic kiểm tra tài khoản (ví dụ)
            if (username.equals("admin") && password.equals("password")) {
                view.setMessage("ĐĂNG NHẬP THÀNH CÔNG!");
                // Điều hướng đến màn hình chính hoặc các hành động khác
            } else {
                view.setMessage("ĐĂNG NHẬP THẤT BẠI");
            }
        }
    }
}
