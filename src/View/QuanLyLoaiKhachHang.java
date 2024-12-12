package View;

import Model.LoaiKhachHang;
import utility.RecordHandler;

import javax.swing.*;
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
        Object[][] data = new Object[loaiKhachHangs.size()][columns];

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
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hiển thị hộp thoại nhập thông tin
                String maLoaiKhachHang = JOptionPane.showInputDialog("Nhập Mã Loại Khách Hàng:");
                String tenLoaiKhachHang = JOptionPane.showInputDialog("Nhập Tên Loại Khách Hàng:");

                if (maLoaiKhachHang != null && tenLoaiKhachHang != null) {
                    Object[] rowData = {maLoaiKhachHang, tenLoaiKhachHang};
                    RecordHandler.addRecord(model, rowData);

                    LoaiKhachHang lkh = new LoaiKhachHang(
                            maLoaiKhachHang,
                            tenLoaiKhachHang
                    );
                    AddRecord(lkh, "LoaiKhachHang");
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
                            "Bạn có chắc chắn muốn sửa loại khách hàng đã chọn?",
                            "Xác nhận",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirm == JOptionPane.YES_OPTION) {
                        String maLoaiKhachHang = safeGetValue(selectedRow, 0);
                        String tenLoaiKhachHang = safeGetValue(selectedRow, 1);

                        if (maLoaiKhachHang != null && tenLoaiKhachHang != null) {
                            Object[] rowData = {maLoaiKhachHang, tenLoaiKhachHang};
                            RecordHandler.updateRecord(model, selectedRow, rowData);

                            LoaiKhachHang lkh = new LoaiKhachHang(
                                    maLoaiKhachHang,
                                    tenLoaiKhachHang
                            );
                            UpdateRecord(lkh, "LoaiKhachHang", "MaLoaiKhachHang", Integer.parseInt(maLoaiKhachHang));

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