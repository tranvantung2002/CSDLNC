package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private final JTextField txtUsername;
    private final JPasswordField txtPassword;
    private final JButton btnLogin;
    private final JLabel lblMessage;

    public LoginView() {
        setTitle("Login");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel bên trái chứa ảnh
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        ImageIcon icon = new ImageIcon("Icons/sun.png");  // Đường dẫn đến ảnh

        JLabel lblImage = new JLabel(icon);
        lblImage.setHorizontalAlignment(SwingConstants.CENTER);
        leftPanel.add(lblImage, BorderLayout.CENTER);

        // Panel bên phải chứa form đăng nhập
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(255, 255, 255)); // Màu nền sáng
        rightPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblTitle = new JLabel("VUI LÒNG ĐĂNG NHẬP");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(new Color(60, 63, 65)); // Màu chữ đậm hơn

        JLabel lblUsername = new JLabel("TÀI KHOẢN:");
        lblUsername.setForeground(new Color(0, 0, 0));
        txtUsername = new JTextField(15);
        styleTextField(txtUsername);

        JLabel lblPassword = new JLabel("MẬT KHẨU:");
        lblPassword.setForeground(new Color(0, 0, 0));
        txtPassword = new JPasswordField(15);
        styleTextField(txtPassword);

        btnLogin = new JButton("ĐĂNG NHẬP");
//        btnLogin.setBackground(new Color(0, 0, 0));
        styleButton(btnLogin);

        lblMessage = new JLabel("");
        lblMessage.setForeground(Color.RED);

        // Sắp xếp các thành phần trong form đăng nhập
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        rightPanel.add(lblTitle, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy++;
        rightPanel.add(lblUsername, gbc);

        gbc.gridx = 1;
        rightPanel.add(txtUsername, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        rightPanel.add(lblPassword, gbc);

        gbc.gridx = 1;
        rightPanel.add(txtPassword, gbc);

        gbc.gridy++;
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        rightPanel.add(btnLogin, gbc);

        gbc.gridy++;
        rightPanel.add(lblMessage, gbc);

        // Sử dụng JSplitPane để chia thành 2 phần
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerLocation(300);  // Đặt kích thước phần ảnh bên trái
        splitPane.setEnabled(false);  // Không cho phép người dùng di chuyển thanh chia

        add(splitPane, BorderLayout.CENTER);
    }

    // Method để tạo style cho text field
    private void styleTextField(JTextField textField) {
//        textField.setBackground(new Color(230, 230, 230)); // Màu nền sáng cho ô nhập
        textField.setForeground(Color.BLACK); // Màu chữ đen
        textField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200))); // Viền sáng
        textField.setCaretColor(Color.BLACK);
    }

    // Method để tạo style cho nút đăng nhập
    private void styleButton(JButton button) {
//        button.setFocusPainted(false);
//        button.setBackground(new Color(98, 181, 67)); // Màu nền sáng cho nút
//        button.setForeground(Color.WHITE); // Màu chữ trắng
        button.setFont(new Font("Arial", Font.BOLD, 14));
//        button.setPreferredSize(new Dimension(100, 30));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        button.setBorder(BorderFactory.createEmptyBorder()); // Không có viền cho nút
    }

    // Method để thêm sự kiện cho nút đăng nhập
    public void addLoginListener(ActionListener listener) {
        btnLogin.addActionListener(listener);
        txtUsername.addActionListener(listener);
        txtPassword.addActionListener(listener);
    }

    // Các phương thức để lấy dữ liệu từ giao diện
    public String getUsername() {
        return txtUsername.getText();
    }

    public String getPassword() {
        return new String(txtPassword.getPassword());
    }

    // Phương thức hiển thị thông báo
    public void setMessage(String message) {
        lblMessage.setText(message);
    }
}
