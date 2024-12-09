package View;

import Model.NhaPhanPhoi;
import Model.SanPham;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static Model.Db4Obj.*;

public class QuanLyNhaPhanPhoi extends javax.swing.JPanel {
    private JTable table;
    private JPanel panel1;
    private JLabel lb, lblID, lblName, lblLoaiSp, lblHang, lblGiaNhap, lblGiaBan, lblTonKho,
            lblTrangThai, lblBlob, lblChuThich;
    private JTextField tfID, tfName, tfLoaiSp, tfHang, tfGiaNhap, tfGiaBan, tfTonKho,
            tfTrangThai, tfBlob, tfChuThich;
    private JButton btnAdd, btnUpdate, btnDelete;
    private DefaultTableModel model;

    public QuanLyNhaPhanPhoi() {
        initComponents();
        lb.putClientProperty(FlatClientProperties.STYLE, "font:$h1.font");
    }

    private void initComponents() {
        // Tạo JLabel cho tiêu đề
        lb = new JLabel();
        lb.setHorizontalAlignment(SwingConstants.CENTER);
        lb.setText("QUẢN LÝ SẢN PHẨM");

        // Tạo các JLabel cho ID, Name, Age
        lblID = new JLabel("Mã sản phẩm:");
        lblName = new JLabel("Tên sản phẩm:");
        lblLoaiSp = new JLabel("Loại sản phẩm:");
        lblHang = new JLabel("Hãng sản xuất:");
        lblGiaNhap = new JLabel("Giá nhập:");
        lblGiaBan = new JLabel("Giá bán:");
        lblTonKho = new JLabel("Tồn kho:");
        lblTrangThai = new JLabel("Trạng thái:");
        lblBlob = new JLabel("Blob:");
        lblChuThich = new JLabel("Chú thích:");

        // Tạo các JTextField cho ID, Name, Age
        tfID = new JTextField(10);
        tfName = new JTextField(20);
        tfLoaiSp = new JTextField(10);
        tfHang = new JTextField(10);
        tfGiaNhap = new JTextField(10);
        tfGiaBan = new JTextField(10);
        tfTonKho = new JTextField(10);
        tfTrangThai = new JTextField(10);
        tfBlob = new JTextField(10);
        tfChuThich = new JTextField(10);

        // Tạo dữ liệu và cột cho JTable
        String[] columns = {"Mã nhà phân phối", "Tên nhà phân phối", "Địa chỉ", "Số điện thoại", "Email", "Chú thích"};
        ArrayList sanphams = ListAllDuLieu("NhaPhanPhoi");
        Object[][] data = new Object[sanphams.size()][columns.length];

        for (int i = 0; i < sanphams.size(); i++) {
            NhaPhanPhoi sp = (NhaPhanPhoi) sanphams.get(i);

            // Cập nhật thông tin của từng sản phẩm vào mảng 2 chiều data
            data[i][0] = sp.getMaNhaPhanPhoi();
            data[i][1] = sp.getTenNhaPhanPhoi();
            data[i][2] = sp.getDiaChi();
            data[i][3] = sp.getSDT();
            data[i][4] = sp.getEmail();
            data[i][5] = sp.getChuThich();
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
                addProduct();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateProduct();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteProduct();
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
                        tfLoaiSp.setText(model.getValueAt(selectedRow, 2).toString());
                        tfHang.setText(model.getValueAt(selectedRow, 3).toString());
                        tfGiaNhap.setText(model.getValueAt(selectedRow, 4).toString());
                        tfGiaBan.setText(model.getValueAt(selectedRow, 5).toString());
                        tfTonKho.setText(model.getValueAt(selectedRow, 6).toString());
                        tfTrangThai.setText(model.getValueAt(selectedRow, 7).toString());
                        tfBlob.setText(model.getValueAt(selectedRow, 8).toString());
                        tfChuThich.setText(model.getValueAt(selectedRow, 9).toString());
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
                                        .addGap(10)
                                        .addComponent(lblLoaiSp)
                                        .addGap(10)
                                        .addComponent(tfLoaiSp, 150, 150, 150)
                                        .addGap(20)
                                )
                                .addGroup(formLayout.createSequentialGroup()
                                        .addComponent(lblHang)
                                        .addGap(10)
                                        .addComponent(tfHang, 150, 150, 150)
                                        .addGap(20)
                                        .addComponent(lblGiaNhap)
                                        .addGap(10)
                                        .addComponent(tfGiaNhap, 150, 150, 150)
                                        .addGap(20)
                                        .addComponent(lblGiaBan)
                                        .addGap(10)
                                        .addComponent(tfGiaBan, 150, 150, 150)
                                )
                                .addGroup(formLayout.createSequentialGroup()
                                        .addComponent(lblTonKho)
                                        .addGap(10)
                                        .addComponent(tfTonKho, 150, 150, 150)
                                        .addGap(20)
                                        .addComponent(lblTrangThai)
                                        .addGap(10)
                                        .addComponent(tfTrangThai, 150, 150, 150)
                                        .addComponent(lblChuThich)
                                        .addGap(10)
                                        .addComponent(tfChuThich, 150, 150, 150)
                                        .addGap(20)
                                        .addComponent(lblBlob)
                                        .addGap(10)
                                        .addComponent(tfBlob, 150, 150, 150)
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
                                .addComponent(lblLoaiSp)
                                .addComponent(tfLoaiSp)
                        )
                        .addGap(10)
                        .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblHang)
                                .addComponent(tfHang)
                                .addComponent(lblGiaNhap)
                                .addComponent(tfGiaNhap)
                                .addComponent(lblGiaBan)
                                .addComponent(tfGiaBan)
                        )
                        .addGap(10)
                        .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblTonKho)
                                .addComponent(tfTonKho)
                                .addComponent(lblTrangThai)
                                .addComponent(tfTrangThai)
                                .addComponent(lblChuThich)
                                .addComponent(tfChuThich)
                                .addComponent(lblBlob)
                                .addComponent(tfBlob)
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


    private void addProduct() {
        int id = Integer.parseInt(tfID.getText());
        String name = tfName.getText();
        int loaiSp = Integer.parseInt(tfLoaiSp.getText());

        int hang = Integer.parseInt(tfHang.getText());
        Float giaNhap = Float.parseFloat(tfGiaNhap.getText());
        Float giaBan = Float.parseFloat(tfGiaBan.getText());
        int tonKho = Integer.parseInt(tfTonKho.getText());
        int trangThai = Integer.parseInt(tfTrangThai.getText());
        String blod = tfBlob.getText();
        String chuThich = tfChuThich.getText();

        if (!String.valueOf(id).isEmpty() && !name.isEmpty() && !String.valueOf(loaiSp).isEmpty()) {
            model.addRow(new Object[]{id, name, loaiSp});

            SanPham sanPham = new SanPham(id, name, loaiSp, hang, giaNhap, giaBan, tonKho, trangThai, blod, chuThich);

            AddRecord(sanPham, "SanPham");

            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
        }
    }

    private void updateProduct() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int id = Integer.parseInt(tfID.getText());
            String name = tfName.getText();
            int loaiSp = Integer.parseInt(tfLoaiSp.getText());

            int hang = Integer.parseInt(tfHang.getText());
            Float giaNhap = Float.parseFloat(tfGiaNhap.getText());
            Float giaBan = Float.parseFloat(tfGiaBan.getText());
            int tonKho = Integer.parseInt(tfTonKho.getText());
            int trangThai = Integer.parseInt(tfTrangThai.getText());
            String blod = tfBlob.getText();
            String chuThich = tfChuThich.getText();

            if (!String.valueOf(id).isEmpty() && !name.isEmpty() && !String.valueOf(loaiSp).isEmpty()) {

                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "Bạn có chắc chắn muốn sửa sản phẩm đã chọn?",
                        "Xác nhận",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    // Xử lý xóa sản phẩm tại đây
                    model.removeRow(selectedRow);
                    model.setValueAt(id, selectedRow, 0);
                    model.setValueAt(name, selectedRow, 1);
                    model.setValueAt(loaiSp, selectedRow, 2);

                    SanPham sanPham = new SanPham(id, name, loaiSp, hang, giaNhap, giaBan, tonKho, trangThai, blod, chuThich);

                    System.out.println(sanPham);

                    UpdateRecord(sanPham, "SanPham", "MaSanPham", id);
                    clearFields();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần sửa!");
        }
    }

    private void deleteProduct() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Bạn có chắc chắn muốn xoá sản phẩm đã chọn?",
                    "Xác nhận",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                // Xử lý xóa sản phẩm tại đây
                model.removeRow(selectedRow);
                String id = tfID.getText();

                DeleteRecord("SanPham", "MaSanPham", Integer.parseInt(id));
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần xoá!");
        }
    }

    private void clearFields() {
        tfID.setText("");
        tfName.setText("");
        tfLoaiSp.setText("");
    }
}
