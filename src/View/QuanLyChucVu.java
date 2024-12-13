package View;

import Model.ChucVu;
import utility.RecordHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static Model.Db4Obj.*;

public class QuanLyChucVu extends BaseManagementPanel {
    public QuanLyChucVu() {
        // Gọi super() với tiêu đề và các cột
        super("Quản Lý Chức Vụ", new String[]{"Mã Chức Vụ", "Tên Chức Vụ", "Ghi Chú"});

        // Tải dữ liệu vào bảng
        loadData(3);
    }

    private void loadData(int columns) {
        ArrayList chucVus = ListAllDuLieu("ChucVu");
        Object[][] data = new Object[chucVus.size()][columns];

        for (int i = 0; i < chucVus.size(); i++) {
            ChucVu cv = (ChucVu) chucVus.get(i);
            Object[] rowData = {
                    cv.getMaChucVu(),
                    cv.getTenChucVu(),
                    cv.getGhiChu()
            };
            RecordHandler.addRecord(model, rowData);
        }
    }

    @Override
    protected void initializeActions() {

        //==================
        // NÚT THÊM
        //==================
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tạo panel nhập dữ liệu (3 hàng, 2 cột)
                JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));

                JLabel lblMaChucVu = new JLabel("Mã Chức Vụ:");
                JTextField tfMaChucVu = new JTextField();

                JLabel lblTenChucVu = new JLabel("Tên Chức Vụ:");
                JTextField tfTenChucVu = new JTextField();

                JLabel lblGhiChu = new JLabel("Ghi Chú:");
                JTextField tfGhiChu = new JTextField();

                // Thêm các thành phần vào panel
                inputPanel.add(lblMaChucVu);
                inputPanel.add(tfMaChucVu);
                inputPanel.add(lblTenChucVu);
                inputPanel.add(tfTenChucVu);
                inputPanel.add(lblGhiChu);
                inputPanel.add(tfGhiChu);

                // Hiển thị panel trong 1 hộp thoại confirm
                int result = JOptionPane.showConfirmDialog(
                        null,
                        inputPanel,
                        "Nhập thông tin Chức Vụ",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

                if (result == JOptionPane.OK_OPTION) {
                    String maChucVu = tfMaChucVu.getText().trim();
                    String tenChucVu = tfTenChucVu.getText().trim();
                    String ghiChu = tfGhiChu.getText().trim();

                    // Kiểm tra dữ liệu
                    if (!maChucVu.isEmpty() && !tenChucVu.isEmpty()) {
                        // Thêm vào bảng
                        Object[] rowData = {maChucVu, tenChucVu, ghiChu};
                        RecordHandler.addRecord(model, rowData);

                        // Thêm vào DB
                        ChucVu cv = new ChucVu(
                                Integer.parseInt(maChucVu),
                                tenChucVu,
                                ghiChu
                        );
                        AddRecord(cv, "ChucVu");
                    } else {
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
                    }
                }
                // Nếu người dùng bấm Cancel hoặc đóng hộp thoại thì không làm gì
            }
        });

        //==================
        // NÚT SỬA
        //==================
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {

                    int confirm = JOptionPane.showConfirmDialog(
                            null,
                            "Bạn có chắc chắn muốn sửa chức vụ đã chọn?",
                            "Xác nhận",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirm == JOptionPane.YES_OPTION) {
                        // Lấy dữ liệu cũ
                        String oldMaChucVu = safeGetValue(selectedRow, 0);
                        String oldTenChucVu = safeGetValue(selectedRow, 1);
                        String oldGhiChu = safeGetValue(selectedRow, 2);

                        // Tạo panel để sửa dữ liệu
                        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));

                        JLabel lblMaChucVu = new JLabel("Mã Chức Vụ:");
                        JTextField tfMaChucVu = new JTextField(oldMaChucVu);

                        JLabel lblTenChucVu = new JLabel("Tên Chức Vụ:");
                        JTextField tfTenChucVu = new JTextField(oldTenChucVu);

                        JLabel lblGhiChu = new JLabel("Ghi Chú:");
                        JTextField tfGhiChu = new JTextField(oldGhiChu);

                        inputPanel.add(lblMaChucVu);
                        inputPanel.add(tfMaChucVu);
                        inputPanel.add(lblTenChucVu);
                        inputPanel.add(tfTenChucVu);
                        inputPanel.add(lblGhiChu);
                        inputPanel.add(tfGhiChu);

                        int result = JOptionPane.showConfirmDialog(
                                null,
                                inputPanel,
                                "Sửa thông tin Chức Vụ",
                                JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.PLAIN_MESSAGE
                        );

                        if (result == JOptionPane.OK_OPTION) {
                            String newMaChucVu = tfMaChucVu.getText().trim();
                            String newTenChucVu = tfTenChucVu.getText().trim();
                            String newGhiChu = tfGhiChu.getText().trim();

                            if (!newMaChucVu.isEmpty() && !newTenChucVu.isEmpty()) {
                                // Cập nhật bảng
                                Object[] rowData = {newMaChucVu, newTenChucVu, newGhiChu};
                                RecordHandler.updateRecord(model, selectedRow, rowData);

                                // Cập nhật DB
                                ChucVu cv = new ChucVu(
                                        Integer.parseInt(newMaChucVu),
                                        newTenChucVu,
                                        newGhiChu
                                );
                                UpdateRecord(cv, "ChucVu", "MaChucVu", Integer.parseInt(newMaChucVu));

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

        //==================
        // NÚT XÓA (giữ nguyên logic)
        //==================
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int confirm = JOptionPane.showConfirmDialog(
                            null,
                            "Bạn có chắc chắn muốn xoá chức vụ đã chọn?",
                            "Xác nhận",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirm == JOptionPane.YES_OPTION) {
                        String maChucVu = safeGetValue(selectedRow, 0);
                        if (maChucVu != null) {
                            RecordHandler.deleteRecord(model, selectedRow);
                            DeleteRecord("ChucVu", "MaChucVu", Integer.parseInt(maChucVu));
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