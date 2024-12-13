package View;

import Model.NhanVien;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static Model.Db4Obj.*;

public class QuanLyNhanVien extends javax.swing.JPanel {
    private JTable table;
    private JLabel lb;
    private DefaultTableModel model;

    private String pattern = "dd/MM/yyyy";
    private DateFormat df = new SimpleDateFormat(pattern);

    private JButton btnAdd, btnUpdate, btnDelete;

    public QuanLyNhanVien() {
        initComponents();
        lb.putClientProperty(FlatClientProperties.STYLE, "font:$h1.font");
    }

    private void initComponents() {
        // Tiêu đề
        lb = new JLabel("QUẢN LÝ NHÂN VIÊN", SwingConstants.CENTER);

        // Cột cho JTable
        String[] columns = {
                "Mã nhân viên",
                "Họ và Tên",
                "Giới Tính",
                "Ngày vào làm",
                "Chức vụ",
                "Địa chỉ",
                "Số điện thoại",
                "Ghi chú"
        };

        // Tải dữ liệu từ DB
        ArrayList nhanViens = ListAllDuLieu("NhanVien");
        Object[][] data = new Object[nhanViens.size()][columns.length];

        for (int i = 0; i < nhanViens.size(); i++) {
            NhanVien nv = (NhanVien) nhanViens.get(i);
            data[i][0] = nv.getMaNhanVien();
            data[i][1] = nv.getHovaTen();
            data[i][2] = nv.getGioiTinh();
            data[i][3] = df.format(nv.getNgayVaoLam());
            data[i][4] = nv.getChucVu();
            data[i][5] = nv.getDiaChi();
            data[i][6] = nv.getSoDT();
            data[i][7] = nv.getGhiChu();
        }

        // Tạo model và JTable
        model = new DefaultTableModel(data, columns);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Tạo nút
        btnAdd = new JButton("Thêm");
        btnUpdate = new JButton("Sửa");
        btnDelete = new JButton("Xoá");

        // Xử lý sự kiện nút Thêm
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddDialog();
            }
        });

        // Xử lý sự kiện nút Sửa
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showUpdateDialog();
            }
        });

        // Xử lý sự kiện nút Xoá
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteNhanVien();
            }
        });

        // Panel dưới chứa các nút chức năng
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);

        // Bố cục tổng thể
        setLayout(new BorderLayout());
        add(lb, BorderLayout.NORTH);         // Tiêu đề trên cùng
        add(scrollPane, BorderLayout.CENTER); // Bảng ở giữa
        add(buttonPanel, BorderLayout.SOUTH); // Nút ở dưới
    }

    //===================================================================================
    // Hàm hiển thị Popup để THÊM nhân viên
    //===================================================================================
    private void showAddDialog() {
        // Tạo panel nhập liệu (8 trường)
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new java.awt.GridLayout(8, 2, 5, 5));

        JLabel lblID = new JLabel("Mã nhân viên:");
        JTextField tfID = new JTextField();

        JLabel lblName = new JLabel("Họ và Tên:");
        JTextField tfName = new JTextField();

        JLabel lblGioiTinh = new JLabel("Giới Tính:");
        JTextField tfGioiTinh = new JTextField();

        JLabel lblNgayVL = new JLabel("Ngày vào làm:");
        JTextField tfNgayVL = new JTextField();

        JLabel lblChucVu = new JLabel("Chức vụ:");
        JTextField tfChucVu = new JTextField();

        JLabel lblDiaChi = new JLabel("Địa chỉ:");
        JTextField tfDiaChi = new JTextField();

        JLabel lblMobile = new JLabel("Số điện thoại:");
        JTextField tfMobile = new JTextField();

        JLabel lblGhiChu = new JLabel("Ghi chú:");
        JTextField tfGhiChu = new JTextField();

        // Thêm label + textfield
        inputPanel.add(lblID);
        inputPanel.add(tfID);
        inputPanel.add(lblName);
        inputPanel.add(tfName);
        inputPanel.add(lblGioiTinh);
        inputPanel.add(tfGioiTinh);
        inputPanel.add(lblNgayVL);
        inputPanel.add(tfNgayVL);
        inputPanel.add(lblChucVu);
        inputPanel.add(tfChucVu);
        inputPanel.add(lblDiaChi);
        inputPanel.add(tfDiaChi);
        inputPanel.add(lblMobile);
        inputPanel.add(tfMobile);
        inputPanel.add(lblGhiChu);
        inputPanel.add(tfGhiChu);

        int result = JOptionPane.showConfirmDialog(
                this,
                inputPanel,
                "Thêm Nhân Viên",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            String id = tfID.getText().trim();
            String name = tfName.getText().trim();
            String sex = tfGioiTinh.getText().trim();
            String dateStr = tfNgayVL.getText().trim();
            String chucVu = tfChucVu.getText().trim();
            String diaChi = tfDiaChi.getText().trim();
            String mobile = tfMobile.getText().trim();
            String ghiChu = tfGhiChu.getText().trim();

            // Kiểm tra hợp lệ
            if (!id.isEmpty() && !name.isEmpty() && !sex.isEmpty() && !dateStr.isEmpty() && !chucVu.isEmpty()) {
                try {
                    java.util.Date dateOfWork = df.parse(dateStr);

                    // Thêm vào model bảng
                    model.addRow(new Object[]{
                            id, name, sex, dateStr, chucVu, diaChi, mobile, ghiChu
                    });

                    // Tạo đối tượng NhanVien và thêm DB
                    NhanVien nv = new NhanVien(
                            Integer.parseInt(id),
                            name,
                            sex,
                            dateOfWork,
                            chucVu,
                            diaChi,
                            mobile,
                            ghiChu
                    );
                    AddRecord(nv, "NhanVien");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this,
                            "Định dạng ngày sai (dd/MM/yyyy) hoặc dữ liệu không hợp lệ!",
                            "Lỗi", JOptionPane.ERROR_MESSAGE
                    );
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            }
        }
    }

    //===================================================================================
    // Hàm hiển thị Popup để SỬA nhân viên
    //===================================================================================
    private void showUpdateDialog() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Chọn một dòng để sửa!");
            return;
        }

        // Lấy dữ liệu cũ
        String oldID = model.getValueAt(selectedRow, 0).toString();
        String oldName = model.getValueAt(selectedRow, 1).toString();
        String oldGioiTinh = model.getValueAt(selectedRow, 2).toString();
        String oldNgayVL = model.getValueAt(selectedRow, 3).toString();
        String oldChucVu = model.getValueAt(selectedRow, 4).toString();
        String oldDiaChi = model.getValueAt(selectedRow, 5).toString();
        String oldMobile = model.getValueAt(selectedRow, 6).toString();
        String oldGhiChu = model.getValueAt(selectedRow, 7).toString();

        // Tạo panel popup
        JPanel inputPanel = new JPanel(new java.awt.GridLayout(8, 2, 5, 5));

        JLabel lblID = new JLabel("Mã nhân viên:");
        JTextField tfID = new JTextField(oldID);

        JLabel lblName = new JLabel("Họ và Tên:");
        JTextField tfName = new JTextField(oldName);

        JLabel lblGioiTinh = new JLabel("Giới Tính:");
        JTextField tfGioiTinh = new JTextField(oldGioiTinh);

        JLabel lblNgayVL = new JLabel("Ngày vào làm:");
        JTextField tfNgayVL = new JTextField(oldNgayVL);

        JLabel lblChucVu = new JLabel("Chức vụ:");
        JTextField tfChucVu = new JTextField(oldChucVu);

        JLabel lblDiaChi = new JLabel("Địa chỉ:");
        JTextField tfDiaChi = new JTextField(oldDiaChi);

        JLabel lblMobile = new JLabel("Số điện thoại:");
        JTextField tfMobile = new JTextField(oldMobile);

        JLabel lblGhiChu = new JLabel("Ghi chú:");
        JTextField tfGhiChu = new JTextField(oldGhiChu);

        inputPanel.add(lblID);
        inputPanel.add(tfID);
        inputPanel.add(lblName);
        inputPanel.add(tfName);
        inputPanel.add(lblGioiTinh);
        inputPanel.add(tfGioiTinh);
        inputPanel.add(lblNgayVL);
        inputPanel.add(tfNgayVL);
        inputPanel.add(lblChucVu);
        inputPanel.add(tfChucVu);
        inputPanel.add(lblDiaChi);
        inputPanel.add(tfDiaChi);
        inputPanel.add(lblMobile);
        inputPanel.add(tfMobile);
        inputPanel.add(lblGhiChu);
        inputPanel.add(tfGhiChu);

        int result = JOptionPane.showConfirmDialog(
                this,
                inputPanel,
                "Sửa Nhân Viên",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            String newID = tfID.getText().trim();
            String newName = tfName.getText().trim();
            String newGioiTinh = tfGioiTinh.getText().trim();
            String newNgayVL = tfNgayVL.getText().trim();
            String newChucVu = tfChucVu.getText().trim();
            String newDiaChi = tfDiaChi.getText().trim();
            String newMobile = tfMobile.getText().trim();
            String newGhiChu = tfGhiChu.getText().trim();

            if (!newID.isEmpty() && !newName.isEmpty() && !newGioiTinh.isEmpty() && !newNgayVL.isEmpty() && !newChucVu.isEmpty()) {
                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "Bạn có chắc chắn muốn sửa nhân viên đã chọn?",
                        "Xác nhận",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    // Cập nhật model (bảng)
                    model.setValueAt(newID, selectedRow, 0);
                    model.setValueAt(newName, selectedRow, 1);
                    model.setValueAt(newGioiTinh, selectedRow, 2);
                    model.setValueAt(newNgayVL, selectedRow, 3);
                    model.setValueAt(newChucVu, selectedRow, 4);
                    model.setValueAt(newDiaChi, selectedRow, 5);
                    model.setValueAt(newMobile, selectedRow, 6);
                    model.setValueAt(newGhiChu, selectedRow, 7);

                    try {
                        java.util.Date dateOfWork = df.parse(newNgayVL);

                        // Tạo đối tượng NhanVien cập nhật DB
                        NhanVien nhanVien = new NhanVien(
                                Integer.parseInt(newID),
                                newName,
                                newGioiTinh,
                                dateOfWork,
                                newChucVu,
                                newDiaChi,
                                newMobile,
                                newGhiChu
                        );
                        UpdateRecord(nhanVien, "NhanVien", "MaNhanVien", Integer.parseInt(newID));
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this,
                                "Định dạng ngày sai (dd/MM/yyyy) hoặc dữ liệu không hợp lệ!",
                                "Lỗi", JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            }
        }
    }

    //===================================================================================
    // Xoá nhân viên
    //===================================================================================
    private void deleteNhanVien() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Bạn có chắc chắn muốn xoá nhân viên đã chọn?",
                    "Xác nhận",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                String id = model.getValueAt(selectedRow, 0).toString();
                // Xoá khỏi JTable
                model.removeRow(selectedRow);
                // Xoá DB
                DeleteRecord("NhanVien", "MaNhanVien", Integer.parseInt(id));
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần xoá!");
        }
    }
}