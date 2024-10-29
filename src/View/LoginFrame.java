package View;

import javax.swing.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {
    private JPanel contentPane;
    private JTextField userNameField;
    private JButton loginButton;
    private JPasswordField passwordField;

    private  boolean isLogin =false;
    public LoginFrame(){
        setContentPane(contentPane);
        setSize(700, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        // Thêm ActionListener cho nút
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });

        // Thêm KeyListener cho các trường nhập liệu
        userNameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    performLogin();
                }
            }
        });

        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    performLogin();
                }
            }
        });

        contentPane.addKeyListener(new KeyAdapter() {
        });
    }

    private void performLogin() {
        String userName = userNameField.getText();
        String password = new String(passwordField.getPassword());

        // Kiểm tra thông tin đăng nhập
        if (userName.equals("admin") && password.equals("123456")) {
            int result = JOptionPane.showConfirmDialog(LoginFrame.this,
                    "Đăng nhập thành công!", "Thông báo",
                    JOptionPane.DEFAULT_OPTION);

            // Chỉ đặt isLogin = true nếu người dùng nhấn OK
            if (result == JOptionPane.OK_OPTION) {
                isLogin = true;
                dispose(); // Đóng cửa sổ đăng nhập
            }
        } else {
            JOptionPane.showMessageDialog(LoginFrame.this,
                    "Sai tên đăng nhập hoặc mật khẩu!",
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }


    public boolean isLoggedIn(){
        return  isLogin;
    }
}
