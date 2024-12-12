package View;

import Model.ChucVu;
import utility.RecordHandler;

import javax.swing.*;
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
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hiển thị hộp thoại nhập thông tin
                String maChucVu = JOptionPane.showInputDialog("Nhập Mã Chức Vụ:");
                String tenChucVu = JOptionPane.showInputDialog("Nhập Tên Chức Vụ:");
                String ghiChu = JOptionPane.showInputDialog("Nhập Ghi Chú:");

                if (maChucVu != null && tenChucVu != null) {
                    Object[] rowData = {maChucVu, tenChucVu, ghiChu};
                    RecordHandler.addRecord(model, rowData);

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
        });

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
                        String maChucVu = safeGetValue(selectedRow, 0);
                        String tenChucVu = safeGetValue(selectedRow, 1);
                        String ghiChu = safeGetValue(selectedRow, 2);

                        if (maChucVu != null && tenChucVu != null) {
                            Object[] rowData = {maChucVu, tenChucVu, ghiChu};
                            RecordHandler.updateRecord(model, selectedRow, rowData);

                            ChucVu cv = new ChucVu(
                                    Integer.parseInt(maChucVu),
                                    tenChucVu,
                                    ghiChu
                            );
                            UpdateRecord(cv, "ChucVu", "MaChucVu", Integer.parseInt(maChucVu));

                            JOptionPane.showMessageDialog(null, "Sửa thành công!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
                        }
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Chọn một dòng để sửa!");
                }
            }
        });

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