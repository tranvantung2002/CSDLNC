package View;  //xuan

import Model.LoaiSanPham;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static Model.Db4Obj.ListAllDuLieu;

public class QuanLyLoaiSanPham extends javax.swing.JPanel {
    private JTable table;
    private JPanel panel1;
    private JLabel lb, lblID, lblName;
    private JTextField tfID, tfName;
    private JButton btnAdd, btnUpdate, btnDelete;
    private DefaultTableModel model;

    public QuanLyLoaiSanPham() {
        initComponents();
        lb.putClientProperty(FlatClientProperties.STYLE, "font:$h1.font");
    }

    private void initComponents() {
        // Tạo JLabel cho tiêu đề
        lb = new JLabel();
        lb.setHorizontalAlignment(SwingConstants.CENTER);
        lb.setText("QUẢN LÝ LOẠI SẢN PHẨM");

        // Tạo các JLabel cho ID, Name
        lblID = new JLabel("Mã loại sản phẩm:");
        lblName = new JLabel("Tên loại sản phẩm:");

        // Tạo các JTextField cho ID, Name
        tfID = new JTextField(10);
        tfName = new JTextField(20);

        // Tạo dữ liệu và cột cho JTable
        String[] columns = {"Mã Loại Sản Phẩm", "Tên Loại Sản Phẩm"};
        ArrayList loaisanphams = ListAllDuLieu("LoaiSanPham");
        Object[][] data = new Object[loaisanphams.size()][columns.length];

        for (int i = 0; i < loaisanphams.size(); i++) {
            LoaiSanPham lsp = (LoaiSanPham) loaisanphams.get(i);

            // Cập nhật thông tin của từng loại sản phẩm vào mảng 2 chiều data
            data[i][0] = lsp.getMaLoaiSanPham();
            data[i][1] = lsp.getTenLoaiSanPham();
        }

        // Tạo DefaultTableModel và JTable
        model = new DefaultTableModel(data, columns);
        table = new JTable(model);

        // Bọc JTable trong JScrollPane để có thể cuộn
        JScrollPane scrollPane = new JScrollPane(table);

        // Tạo các nút chức năng
        btnAdd = new JButton("Thêm");
        btnUpdate = new JButton("Sửa");
        btnDelete = new JButton("Xoá");

        // Thiết lập hành động cho các nút
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addLoaiSanPham();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateLoaiSanPham();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteLoaiSanPham();
            }
        });

        // Lắng nghe sự kiện chọn hàng trong bảng
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        tfID.setText(model.getValueAt(selectedRow, 0).toString());
                        tfName.setText(model.getValueAt(selectedRow, 1).toString());
                    }
                }
            }
        });

        // === Tổ chức bố cục ===
        JPanel formPanel = new JPanel();
        GroupLayout formLayout = new GroupLayout(formPanel);
        formPanel.setLayout(formLayout);

        formLayout.setHorizontalGroup(
                formLayout.createSequentialGroup()
                        .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(formLayout.createSequentialGroup()
                                        .addComponent(lblID)
                                        .addComponent(tfID, 150, 150, 150)
                                        .addGap(20)
                                        .addComponent(lblName)
                                        .addGap(10)
                                        .addComponent(tfName, 150, 150, 150)
                                        .addGap(20)
                                )
                                .addGroup(formLayout.createSequentialGroup()
                                        .addComponent(btnAdd)
                                        .addGap(20)
                                        .addComponent(btnUpdate)
                                        .addGap(20)
                                        .addComponent(btnDelete)))
        );

        formLayout.setVerticalGroup(
                formLayout.createSequentialGroup()
                        .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblID)
                                .addComponent(tfID)
                                .addComponent(lblName)
                                .addComponent(tfName)
                        )
                        .addGap(10)
                        .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(btnAdd)
                                .addComponent(btnUpdate)
                                .addComponent(btnDelete))
        );

        // Sử dụng BorderLayout để thêm bảng và form
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER); // JTable ở giữa
        add(formPanel, BorderLayout.SOUTH); // Form ở dưới
    }

    private void addLoaiSanPham() {
        String id = tfID.getText();
        String name = tfName.getText();

        if (!id.isEmpty() && !name.isEmpty()) {
            model.addRow(new Object[]{id, name});
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
        }
    }

    private void updateLoaiSanPham() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String id = tfID.getText();
            String name = tfName.getText();

            if (!id.isEmpty() && !name.isEmpty()) {
                model.setValueAt(id, selectedRow, 0);
                model.setValueAt(name, selectedRow, 1);
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn loại sản phẩm cần sửa!");
        }
    }

    private void deleteLoaiSanPham() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            model.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn loại sản phẩm cần xoá!");
        }
    }

    private void clearFields() {
        tfID.setText("");
        tfName.setText("");
    }
}
