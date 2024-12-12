package View;

import Model.HangSanXuat;
import utility.RecordHandler;

import javax.swing.*;
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
        Object[][] data = new Object[hangSanXuats.size()][columns];

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
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hiển thị hộp thoại nhập thông tin
                String maHangSanXuat = JOptionPane.showInputDialog("Nhập Mã Hãng Sản Xuất:");
                String tenHangSanXuat = JOptionPane.showInputDialog("Nhập Tên Hãng Sản Xuất:");

                if (maHangSanXuat != null && tenHangSanXuat != null) {
                    Object[] rowData = {maHangSanXuat, tenHangSanXuat};
                    RecordHandler.addRecord(model, rowData);

                    HangSanXuat hsx = new HangSanXuat(
                            Integer.parseInt(maHangSanXuat),
                            tenHangSanXuat
                    );
                    AddRecord(hsx, "HangSanXuat");
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
                            "Bạn có chắc chắn muốn sửa hãng sản xuất đã chọn?",
                            "Xác nhận",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirm == JOptionPane.YES_OPTION) {
                        String maHangSanXuat = safeGetValue(selectedRow, 0);
                        String tenHangSanXuat = safeGetValue(selectedRow, 1);

                        if (maHangSanXuat != null && tenHangSanXuat != null) {
                            Object[] rowData = {maHangSanXuat, tenHangSanXuat};
                            RecordHandler.updateRecord(model, selectedRow, rowData);

                            HangSanXuat hsx = new HangSanXuat(
                                    Integer.parseInt(maHangSanXuat),
                                    tenHangSanXuat
                            );
                            UpdateRecord(hsx, "HangSanXuat", "MaHangSanXuat", Integer.parseInt(maHangSanXuat));

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