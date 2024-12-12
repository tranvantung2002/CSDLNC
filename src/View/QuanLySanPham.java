package View;

import Model.SanPham;
import utility.RecordHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static Model.Db4Obj.*;

public class QuanLySanPham extends BaseManagementPanel {
    public QuanLySanPham() {
        // Gọi super() với tiêu đề và các cột
        super("Quản Lý Sản Phẩm", new String[]{"Mã Sản Phẩm", "Tên Sản Phẩm", "Loại Sản Phẩm", "Hãng Sản Xuất", "Giá Nhập", "Giá Bán", "Tồn Kho", "Trạng Thái", "Blob", "Chú Thích"});

        // Tải dữ liệu vào bảng
        loadData(10);
    }

    private void loadData(int columns) {
        ArrayList sanPhams = ListAllDuLieu("SanPham");
        Object[][] data = new Object[sanPhams.size()][columns];

        for (int i = 0; i < sanPhams.size(); i++) {
            SanPham sp = (SanPham) sanPhams.get(i);
            Object[] rowData = {
                    sp.getMaSanPham(),
                    sp.getTenSanPham(),
                    sp.getLoaiSanPham(),
                    sp.getHangSanXuat(),
                    sp.getGiaNhap(),
                    sp.getGiaBan(),
                    sp.getTonKho(),
                    sp.getTrangThai(),
                    sp.getBlob(),
                    sp.getChuThich()
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
                String maSanPham = JOptionPane.showInputDialog("Nhập Mã Sản Phẩm:");
                String tenSanPham = JOptionPane.showInputDialog("Nhập Tên Sản Phẩm:");
                String loaiSanPham = JOptionPane.showInputDialog("Nhập Loại Sản Phẩm:");
                String hangSanXuat = JOptionPane.showInputDialog("Nhập Hãng Sản Xuất:");
                String giaNhap = JOptionPane.showInputDialog("Nhập Giá Nhập:");
                String giaBan = JOptionPane.showInputDialog("Nhập Giá Bán:");
                String tonKho = JOptionPane.showInputDialog("Nhập Tồn Kho:");
                String trangThai = JOptionPane.showInputDialog("Nhập Trạng Thái:");
                String blob = JOptionPane.showInputDialog("Nhập Blob:");
                String chuThich = JOptionPane.showInputDialog("Nhập Chú Thích:");

                if (maSanPham != null && tenSanPham != null && loaiSanPham != null && hangSanXuat != null && giaNhap != null && giaBan != null && tonKho != null && trangThai != null) {
                    Object[] rowData = {maSanPham, tenSanPham, loaiSanPham, hangSanXuat, giaNhap, giaBan, tonKho, trangThai, blob, chuThich};
                    RecordHandler.addRecord(model, rowData);

                    SanPham sp = new SanPham(
                            Integer.parseInt(maSanPham),
                            tenSanPham,
                            Integer.parseInt(loaiSanPham),
                            Integer.parseInt(hangSanXuat),
                            Float.parseFloat(giaNhap),
                            Float.parseFloat(giaBan),
                            Integer.parseInt(tonKho),
                            Integer.parseInt(trangThai),
                            blob,
                            chuThich
                    );
                    AddRecord(sp, "SanPham");
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
                            "Bạn có chắc chắn muốn sửa sản phẩm đã chọn?",
                            "Xác nhận",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirm == JOptionPane.YES_OPTION) {
                        String maSanPham = safeGetValue(selectedRow, 0);
                        String tenSanPham = safeGetValue(selectedRow, 1);
                        String loaiSanPham = safeGetValue(selectedRow, 2);
                        String hangSanXuat = safeGetValue(selectedRow, 3);
                        String giaNhap = safeGetValue(selectedRow, 4);
                        String giaBan = safeGetValue(selectedRow, 5);
                        String tonKho = safeGetValue(selectedRow, 6);
                        String trangThai = safeGetValue(selectedRow, 7);
                        String blob = safeGetValue(selectedRow, 8);
                        String chuThich = safeGetValue(selectedRow, 9);

                        if (maSanPham != null && tenSanPham != null && loaiSanPham != null && hangSanXuat != null && giaNhap != null && giaBan != null && tonKho != null && trangThai != null) {
                            Object[] rowData = {maSanPham, tenSanPham, loaiSanPham, hangSanXuat, giaNhap, giaBan, tonKho, trangThai, blob, chuThich};
                            RecordHandler.updateRecord(model, selectedRow, rowData);

                            SanPham sp = new SanPham(
                                    Integer.parseInt(maSanPham),
                                    tenSanPham,
                                    Integer.parseInt(loaiSanPham),
                                    Integer.parseInt(hangSanXuat),
                                    Float.parseFloat(giaNhap),
                                    Float.parseFloat(giaBan),
                                    Integer.parseInt(tonKho),
                                    Integer.parseInt(trangThai),
                                    blob,
                                    chuThich
                            );
                            UpdateRecord(sp, "SanPham", "MaSanPham", Integer.parseInt(maSanPham));

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
                            "Bạn có chắc chắn muốn xoá sản phẩm đã chọn?",
                            "Xác nhận",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirm == JOptionPane.YES_OPTION) {
                        String maSanPham = safeGetValue(selectedRow, 0);
                        if (maSanPham != null) {
                            RecordHandler.deleteRecord(model, selectedRow);
                            DeleteRecord("SanPham", "MaSanPham", Integer.parseInt(maSanPham));
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