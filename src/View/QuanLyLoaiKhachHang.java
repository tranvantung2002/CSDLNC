package View;

import Model.LoaiKhachHang;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static Model.Db4Obj.ListAllDuLieu;

public class QuanLyLoaiKhachHang extends javax.swing.JPanel {
    private JTable table;
    private JPanel panel1;
    private JLabel lb, lblID, lblName, lblGioiTinh, lblNgaySinh, lblType, lblDiaChi, lblMobile, lblGhiChu;
    private JTextField tfID, tfName, tfGioiTinh, tfNgaySinh, tfType, tfDiaChi, tfMobile, tfGhiChu;
    private JButton btnAdd, btnUpdate, btnDelete;
    private DefaultTableModel model;

    public QuanLyLoaiKhachHang() {
        // TODO Auto-generated constructor stub
        initComponents();
        lb.putClientProperty(FlatClientProperties.STYLE, "font:$h1.font");
    }

    private void initComponents() {
        // Tạo JLabel cho tiêu đề
        lb = new JLabel();
        lb.setHorizontalAlignment(SwingConstants.CENTER);
        lb.setText("QUẢN LÝ KHÁCH HÀNG");

        // Tạo các JLabel cho ID, Name, Age
        lblID = new JLabel("Mã khách hàng:");
        lblName = new JLabel("Tên khách hàng:");
        lblNgaySinh = new JLabel("Ngày sinh:");
        lblGioiTinh = new JLabel("Giới Tính:");
        lblDiaChi = new JLabel("Địa chỉ:");
        lblMobile = new JLabel("Số điện thoại:");
        lblType = new JLabel("Loại khách hàng:");
        lblGhiChu = new JLabel("Ghi chú:");


        // Tạo các JTextField cho ID, Name, Age
        tfID = new JTextField(10);
        tfName = new JTextField(20);
        tfNgaySinh = new JTextField(10);
        tfGioiTinh = new JTextField(10);
        tfDiaChi = new JTextField(10);
        tfMobile = new JTextField(10);
        tfType = new JTextField(10);
        tfGhiChu = new JTextField(10);


        // Tạo dữ liệu và cột cho JTable

        String[] columns = {"Mã loại khách hàng", "Tên loại khách hàng"};
        ArrayList khachHangs = ListAllDuLieu("LoaiKhachHang");
        Object[][] data = new Object[khachHangs.size()][columns.length];

        for (int i = 0; i < khachHangs.size(); i++) {
            LoaiKhachHang sp = (LoaiKhachHang) khachHangs.get(i);
            // Cập nhật thông tin của từng sản phẩm vào mảng 2 chiều data
            data[i][0] = sp.getMaLoaiKhachHang();
            data[i][1] = sp.getTenLoaiKhachHang();
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
                addKhachHang();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateKhachHang();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteKhachHang();
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
                                        .addGap(10)
                                        .addComponent(lblNgaySinh)
                                        .addGap(10)
                                        .addComponent(tfNgaySinh, 150, 150, 150)
                                        .addGap(20)
                                )
                                .addGroup(formLayout.createSequentialGroup()
                                        .addComponent(lblGioiTinh)
                                        .addGap(10)
                                        .addComponent(tfGioiTinh, 150, 150, 150)
                                        .addGap(20)
                                        .addComponent(lblDiaChi)
                                        .addGap(10)
                                        .addComponent(tfDiaChi, 150, 150, 150)
                                        .addGap(20)
                                        .addComponent(lblMobile)
                                        .addGap(10)
                                        .addComponent(tfMobile, 150, 150, 150)
                                )
                                .addGroup(formLayout.createSequentialGroup()
                                        .addComponent(lblType)
                                        .addGap(10)
                                        .addComponent(tfType, 150, 150, 150)
                                        .addGap(20)
                                        .addComponent(lblGhiChu)
                                        .addGap(10)
                                        .addComponent(tfGhiChu, 150, 150, 150)
                                ).addGroup(formLayout.createSequentialGroup()
                                        .addComponent(btnAdd)
                                        .addGap(20)
                                        .addComponent(btnUpdate)
                                        .addGap(20)
                                        .addComponent(btnDelete))

                        ));

        formLayout.setVerticalGroup(
                formLayout.createSequentialGroup()
                        .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblID)
                                .addComponent(tfID)
                                .addComponent(lblName)
                                .addComponent(tfName)
                                .addComponent(lblNgaySinh)
                                .addComponent(tfNgaySinh)
                        )
                        .addGap(10)
                        .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblGioiTinh)
                                .addComponent(tfGioiTinh)
                                .addComponent(lblDiaChi)
                                .addComponent(tfDiaChi)
                                .addComponent(lblMobile)
                                .addComponent(tfMobile)
                        )
                        .addGap(10)
                        .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblType)
                                .addComponent(tfType)
                                .addComponent(lblGhiChu)
                                .addComponent(tfGhiChu)
                        )
                        .addGap(10)
                        .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(btnAdd)
                                .addComponent(btnUpdate)
                                .addComponent(btnDelete))

        );
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER); // JTable ở giữa
        add(formPanel, BorderLayout.SOUTH); // Form ở dưới

    }

    private void addKhachHang() {
        String id = tfID.getText();
        String name = tfName.getText();
        String date = tfNgaySinh.getText();
        String sex = tfGioiTinh.getText();
        String address = tfDiaChi.getText();
        int mobile = Integer.parseInt(tfMobile.getText().trim());
        String type = tfType.getText();
        String note = tfGhiChu.getText();


        if (!id.isEmpty() && !name.isEmpty() && !sex.isEmpty() && !date.isEmpty() && !type.isEmpty()) {
            model.addRow(new Object[]{id, name, date, sex, address, mobile, type, note});
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
        }
    }

    private void updateKhachHang() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String id = tfID.getText();
            String name = tfName.getText();
            String date = tfNgaySinh.getText();
            String sex = tfGioiTinh.getText();
            String address = tfDiaChi.getText();
            int mobile = Integer.parseInt(tfMobile.getText().trim());
            String type = tfType.getText();
            String note = tfGhiChu.getText();

            if (!id.isEmpty() && !name.isEmpty() && !sex.isEmpty()) {
                model.setValueAt(id, selectedRow, 0);
                model.setValueAt(name, selectedRow, 1);
                model.setValueAt(date, selectedRow, 2);
                model.setValueAt(sex, selectedRow, 3);
                model.setValueAt(address, selectedRow, 4);
                model.setValueAt(mobile, selectedRow, 5);
                model.setValueAt(type, selectedRow, 6);
                model.setValueAt(note, selectedRow, 7);
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần sửa!");
        }
    }

    private void deleteKhachHang() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            model.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần xoá!");
        }
    }

    private void clearFields() {
        tfID.setText("");
        tfName.setText("");
        tfGioiTinh.setText("");
        tfNgaySinh.setText("");
        tfType.setText("");
        tfDiaChi.setText("");
        tfMobile.setText("");
        tfGhiChu.setText("");
    }
}
