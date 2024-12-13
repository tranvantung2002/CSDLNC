package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public abstract class BaseManagementPanel extends JPanel {
    protected JTable table;
    protected DefaultTableModel model;
    protected JButton btnAdd, btnUpdate, btnDelete;

    public BaseManagementPanel(String title, String[] columns) {
        setLayout(new BorderLayout());

        // Tiêu đề
        JLabel lblTitle = new JLabel(title, SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblTitle, BorderLayout.NORTH);

        // Bảng
        model = createNonEditableModel(columns, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Các nút chức năng
        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnAdd = new JButton("Thêm");
        btnUpdate = new JButton("Sửa");
        btnDelete = new JButton("Xóa");
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        add(buttonPanel, BorderLayout.SOUTH);

        // Xử lý hành động nút
        initializeActions();
    }

    public static DefaultTableModel createNonEditableModel(Object[] columnNames, int rowCount) {
        return new DefaultTableModel(columnNames, rowCount) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Không cho phép chỉnh sửa bất kỳ cột nào
                return column != 0;
            }
        };
    }

    // Phương thức trừu tượng cho lớp con cài đặt
    protected abstract void initializeActions();
}
