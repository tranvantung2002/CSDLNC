package View;

import Model.HangSanXuat;
import utility.RecordHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static Model.Db4Obj.*;

public class QuanLyHangSanXuat extends BaseManagementPanel {
    public QuanLyHangSanXuat() {
        // Gọi super() với tiêu đề và các cột
        super("Quản Lý Hãng Sản Xuất", new String[]{"Mã Hãng Sản Xuất", "Tên Hãng Sản Xuất"});

        // Tải dữ liệu vào bảng
        loadData(2);
    }

    private void loadData(int columns) {
        ArrayList hangSanXuats = ListAllDuLieu("HangSanXuat");
        for (int i = 0; i < hangSanXuats.size(); i++) {
            HangSanXuat hsx = (HangSanXuat) hangSanXuats.get(i);
            Object[] rowData = {
                    hsx.getMaHangSanXuat(),
                    hsx.getTenHangSanXuat()
            };
            RecordHandler.addRecord(model, rowData);
        }
    }

    @Override
    protected void initializeActions() {
        //==========================
        // NÚT THÊM
        //==========================
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tạo panel nhập liệu (2 hàng, 2 cột)
                JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));

                JLabel lblMaHangSX = new JLabel("Mã Hãng Sản Xuất:");
                JTextField tfMaHangSX = new JTextField();

                JLabel lblTenHangSX = new JLabel("Tên Hãng Sản Xuất:");
                JTextField tfTenHangSX = new JTextField();

                // Thêm label + textfield vào panel
                inputPanel.add(lblMaHangSX);
                inputPanel.add(tfMaHangSX);
                inputPanel.add(lblTenHangSX);
                inputPanel.add(tfTenHangSX);

                // Hiển thị panel trong một hộp thoại
                int result = JOptionPane.showConfirmDialog(
                        null,
                        inputPanel,
                        "Nhập thông tin Hãng Sản Xuất",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

                if (result == JOptionPane.OK_OPTION) {
                    String maHangSanXuat = tfMaHangSX.getText().trim();
                    String tenHangSanXuat = tfTenHangSX.getText().trim();

                    // Kiểm tra dữ liệu
                    if (!maHangSanXuat.isEmpty() && !tenHangSanXuat.isEmpty()) {
                        // Thêm vào bảng
                        Object[] rowData = {maHangSanXuat, tenHangSanXuat};
                        RecordHandler.addRecord(model, rowData);

                        // Thêm vào DB
                        HangSanXuat hsx = new HangSanXuat(
                                Integer.parseInt(maHangSanXuat),
                                tenHangSanXuat
                        );
                        AddRecord(hsx, "HangSanXuat");
                    } else {
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
                    }
                }
            }
        });

        //==========================
        // NÚT SỬA
        //==========================
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int confirm = JOptionPane.showConfirmDialog(
                            null,
                            "Bạn có chắc chắn muốn sửa hãng sản xuất đã chọn?",
                            "Xác nhận",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirm == JOptionPane.YES_OPTION) {
                        // Lấy dữ liệu cũ
                        String oldMaHangSX = safeGetValue(selectedRow, 0);
                        String oldTenHangSX = safeGetValue(selectedRow, 1);

                        // Tạo panel nhập liệu
                        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));

                        JLabel lblMaHangSX = new JLabel("Mã Hãng Sản Xuất:");
                        JTextField tfMaHangSX = new JTextField(oldMaHangSX);

                        JLabel lblTenHangSX = new JLabel("Tên Hãng Sản Xuất:");
                        JTextField tfTenHangSX = new JTextField(oldTenHangSX);

                        inputPanel.add(lblMaHangSX);
                        inputPanel.add(tfMaHangSX);
                        inputPanel.add(lblTenHangSX);
                        inputPanel.add(tfTenHangSX);

                        int result = JOptionPane.showConfirmDialog(
                                null,
                                inputPanel,
                                "Sửa thông tin Hãng Sản Xuất",
                                JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.PLAIN_MESSAGE
                        );

                        if (result == JOptionPane.OK_OPTION) {
                            String newMaHangSX = tfMaHangSX.getText().trim();
                            String newTenHangSX = tfTenHangSX.getText().trim();

                            if (!newMaHangSX.isEmpty() && !newTenHangSX.isEmpty()) {
                                // Cập nhật bảng
                                Object[] rowData = {newMaHangSX, newTenHangSX};
                                RecordHandler.updateRecord(model, selectedRow, rowData);

                                // Ghi vào DB
                                HangSanXuat hsx = new HangSanXuat(
                                        Integer.parseInt(newMaHangSX),
                                        newTenHangSX
                                );
                                UpdateRecord(hsx, "HangSanXuat", "MaHangSanXuat", Integer.parseInt(newMaHangSX));

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

        //==========================
        // NÚT XÓA (giữ nguyên)
        //==========================
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int confirm = JOptionPane.showConfirmDialog(
                            null,
                            "Bạn có chắc chắn muốn xoá hãng sản xuất đã chọn?",
                            "Xác nhận",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirm == JOptionPane.YES_OPTION) {
                        String maHangSanXuat = safeGetValue(selectedRow, 0);
                        if (maHangSanXuat != null) {
                            RecordHandler.deleteRecord(model, selectedRow);
                            DeleteRecord("HangSanXuat", "MaHangSanXuat", Integer.parseInt(maHangSanXuat));
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