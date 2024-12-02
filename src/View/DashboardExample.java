package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardExample extends JFrame implements ActionListener {
    // Khai báo các thành phần giao diện
    private final JPanel menuPanel;
    private final JPanel contentPanel;
    private final CardLayout cardLayout;

    public DashboardExample() {
        // Thiết lập JFrame
        setTitle("Dashboard Example");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tạo panel menu cố định bên trái
        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(5, 1)); // Chia lưới các nút theo cột
        menuPanel.setPreferredSize(new Dimension(200, 0));

        // Tạo các nút trong menu và gắn sự kiện
        String[] menuItems = {"Home", "Profile", "Settings", "Help", "Logout"};
        for (String item : menuItems) {
            JButton button = new JButton(item);
            button.addActionListener(this);
            button.setActionCommand(item); // Đặt tên action cho nút
            menuPanel.add(button);
        }

        // Tạo content panel bên phải với CardLayout để thay đổi nội dung
        contentPanel = new JPanel();
        cardLayout = new CardLayout();
        contentPanel.setLayout(cardLayout);

        // Thêm các panel con vào contentPanel
        contentPanel.add(new JLabel("Welcome to Home"), "Home");
        contentPanel.add(new JLabel("User Profile Information"), "Profile");
        contentPanel.add(new JLabel("Application Settings"), "Settings");
        contentPanel.add(new JLabel("Help and Support"), "Help");
        contentPanel.add(new JLabel("Logout Screen"), "Logout");

        // Sắp xếp bố cục chính của JFrame với menu bên trái và content bên phải
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(menuPanel, BorderLayout.WEST);
        getContentPane().add(contentPanel, BorderLayout.CENTER);
    }

    // Chạy chương trình
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DashboardExample dashboard = new DashboardExample();
            dashboard.setVisible(true);
        });
    }

    // Sự kiện khi nhấn vào các nút menu
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        cardLayout.show(contentPanel, command); // Hiển thị panel tương ứng
    }
}
