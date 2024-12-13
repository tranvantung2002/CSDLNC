package View;

import Model.LoaiKhachHang;
import utility.RecordHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static Model.Db4Obj.*;

public class QuanLyLoaiKhachHang extends BaseManagementPanel {
    public QuanLyLoaiKhachHang() {
        // Gọi super() với tiêu đề và các cột
        super("Quản Lý Loại Khách Hàng", new String[]{"Mã Loại Khách Hàng", "Tên Loại Khách Hàng"});

        // Tải dữ liệu vào bảng
        loadData(2);
    }

    private void loadData(int columns) {
        ArrayList loaiKhachHangs = ListAllDuLieu("LoaiKhachHang");
        for (int i = 0; i < loaiKhachHangs.size(); i++) {
            LoaiKhachHang lkh = (LoaiKhachHang) loaiKhachHangs.get(i);
            Object[] rowData = {
                    lkh.getMaLoaiKhachHang(),
                    lkh.getTenLoaiKhachHang()
            };
            RecordHandler.addRecord(model, rowData);
        }
    }

    @Override
    protected void initializeActions() {

        //==================================
        // NÚT THÊM
        //==================================
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tạo panel nhập liệu (2 hàng, 2 cột)
                JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));

                JLabel lblMaLoaiKH = new JLabel("Mã Loại Khách Hàng:");
                JTextField tfMaLoaiKH = new JTextField();

                JLabel lblTenLoaiKH = new JLabel("Tên Loại Khách Hàng:");
                JTextField tfTenLoaiKH = new JTextField();

                // Thêm các thành phần vào panel
                inputPanel.add(lblMaLoaiKH);
                inputPanel.add(tfMaLoaiKH);
                inputPanel.add(lblTenLoaiKH);
                inputPanel.add(tfTenLoaiKH);

                int result = JOptionPane.showConfirmDialog(
                        null,
                        inputPanel,
                        "Nhập thông tin Loại Khách Hàng",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

                if (result == JOptionPane.OK_OPTION) {
                    String maLoaiKhachHang = tfMaLoaiKH.getText().trim();
                    String tenLoaiKhachHang = tfTenLoaiKH.getText().trim();

                    if (!maLoaiKhachHang.isEmpty() && !tenLoaiKhachHang.isEmpty()) {
                        // Thêm vào bảng
                        Object[] rowData = {maLoaiKhachHang, tenLoaiKhachHang};
                        RecordHandler.addRecord(model, rowData);

                        // Thêm vào DB
                        LoaiKhachHang lkh = new LoaiKhachHang(maLoaiKhachHang, tenLoaiKhachHang);
                        AddRecord(lkh, "LoaiKhachHang");
                    } else {
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
                    }
                }
            }
        });

        //==================================
        // NÚT SỬA
        //==================================
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int confirm = JOptionPane.showConfirmDialog(
                            null,
                            "Bạn có chắc chắn muốn sửa loại khách hàng đã chọn?",
                            "Xác nhận",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirm == JOptionPane.YES_OPTION) {
                        // Lấy dữ liệu cũ từ dòng được chọn
                        String oldMaLoaiKH = safeGetValue(selectedRow, 0);
                        String oldTenLoaiKH = safeGetValue(selectedRow, 1);

                        // Tạo panel để sửa dữ liệu
                        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));

                        JLabel lblMaLoaiKH = new JLabel("Mã Loại Khách Hàng:");
                        JTextField tfMaLoaiKH = new JTextField(oldMaLoaiKH);

                        JLabel lblTenLoaiKH = new JLabel("Tên Loại Khách Hàng:");
                        JTextField tfTenLoaiKH = new JTextField(oldTenLoaiKH);

                        inputPanel.add(lblMaLoaiKH);
                        inputPanel.add(tfMaLoaiKH);
                        inputPanel.add(lblTenLoaiKH);
                        inputPanel.add(tfTenLoaiKH);

                        int result = JOptionPane.showConfirmDialog(
                                null,
                                inputPanel,
                                "Sửa thông tin Loại Khách Hàng",
                                JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.PLAIN_MESSAGE
                        );

                        if (result == JOptionPane.OK_OPTION) {
                            String newMaLoaiKH = tfMaLoaiKH.getText().trim();
                            String newTenLoaiKH = tfTenLoaiKH.getText().trim();

                            if (!newMaLoaiKH.isEmpty() && !newTenLoaiKH.isEmpty()) {
                                // Cập nhật bảng
                                Object[] rowData = {newMaLoaiKH, newTenLoaiKH};
                                RecordHandler.updateRecord(model, selectedRow, rowData);

                                // Cập nhật DB
                                LoaiKhachHang lkh = new LoaiKhachHang(newMaLoaiKH, newTenLoaiKH);
                                // Ở đây code gốc parse MaLoaiKhachHang thành int, nên ta giữ nguyên
                                UpdateRecord(lkh, "LoaiKhachHang", "MaLoaiKhachHang", Integer.parseInt(newMaLoaiKH));

                                JOptionPane.showMessageDialog(null, "Sửa thành công!");
                            } else {
                                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Chọn một dòng để sửa!");
                }
            }
        });

        //==================================
        // NÚT XÓA (giữ nguyên)
        //==================================
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int confirm = JOptionPane.showConfirmDialog(
                            null,
                            "Bạn có chắc chắn muốn xoá loại khách hàng đã chọn?",
                            "Xác nhận",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirm == JOptionPane.YES_OPTION) {
                        String maLoaiKhachHang = safeGetValue(selectedRow, 0);
                        if (maLoaiKhachHang != null) {
                            RecordHandler.deleteRecord(model, selectedRow);
                            DeleteRecord("LoaiKhachHang", "MaLoaiKhachHang", Integer.parseInt(maLoaiKhachHang));
                        } else {
                            JOptionPane.showMessageDialog(null, "Dữ liệu không hợp lệ để xóa!");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Chọn một dòng để xóa!");
                }
            }
        });
    }

    private String safeGetValue(int row, int column) {
        Object value = model.getValueAt(row, column);
        return value != null ? value.toString() : null;
    }
}