package View;

import Model.PhieuNhap;
import utility.RecordHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import static Model.Db4Obj.*;

public class QuanLyPhieuNhap extends BaseManagementPanel {
    public QuanLyPhieuNhap() {
        // Gọi super() với tiêu đề và các cột
        super("Quản Lý Phiếu Nhập", new String[]{"Mã Phiếu Nhập", "Mã Nhân Viên", "Mã Nhà Phân Phối", "Tổng Tiền", "Ngày Nhập", "Chú Thích"});

        // Tải dữ liệu vào bảng
        loadData(6);
    }

    private void loadData(int columns) {
        ArrayList phieuNhaps = ListAllDuLieu("PhieuNhap");
        Object[][] data = new Object[phieuNhaps.size()][columns];

        for (int i = 0; i < phieuNhaps.size(); i++) {
            PhieuNhap pn = (PhieuNhap) phieuNhaps.get(i);
            Object[] rowData = {
                    pn.getMaPhieuNhap(),
                    pn.getMaNhanVien(),
                    pn.getMaNhaPhanPhoi(),
                    pn.getTongTien(),
                    pn.getNgayNhap(),
                    pn.getChuThich()
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
                String maPhieuNhap = JOptionPane.showInputDialog("Nhập Mã Phiếu Nhập:");
                String maNhanVien = JOptionPane.showInputDialog("Nhập Mã Nhân Viên:");
                String maNhaPhanPhoi = JOptionPane.showInputDialog("Nhập Mã Nhà Phân Phối:");
                String tongTien = JOptionPane.showInputDialog("Nhập Tổng Tiền:");
                String ngayNhap = JOptionPane.showInputDialog("Nhập Ngày Nhập:");
                String chuThich = JOptionPane.showInputDialog("Nhập Chú Thích:");

                if (maPhieuNhap != null && maNhanVien != null && maNhaPhanPhoi != null && tongTien != null && ngayNhap != null) {
                    Object[] rowData = {maPhieuNhap, maNhanVien, maNhaPhanPhoi, tongTien, ngayNhap, chuThich};
                    RecordHandler.addRecord(model, rowData);

                    PhieuNhap pn = new PhieuNhap(
                            Integer.parseInt(maPhieuNhap),
                            Integer.parseInt(maNhanVien),
                            Integer.parseInt(maNhaPhanPhoi),
                            Float.parseFloat(tongTien),
                            new Date(ngayNhap),
                            chuThich
                    );
                    AddRecord(pn, "PhieuNhap");
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
                            "Bạn có chắc chắn muốn sửa phiếu nhập đã chọn?",
                            "Xác nhận",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirm == JOptionPane.YES_OPTION) {
                        String maPhieuNhap = safeGetValue(selectedRow, 0);
                        String maNhanVien = safeGetValue(selectedRow, 1);
                        String maNhaPhanPhoi = safeGetValue(selectedRow, 2);
                        String tongTien = safeGetValue(selectedRow, 3);
                        String ngayNhap = safeGetValue(selectedRow, 4);
                        String chuThich = safeGetValue(selectedRow, 5);

                        if (maPhieuNhap != null && maNhanVien != null && maNhaPhanPhoi != null && tongTien != null && ngayNhap != null) {
                            Object[] rowData = {maPhieuNhap, maNhanVien, maNhaPhanPhoi, tongTien, ngayNhap, chuThich};
                            RecordHandler.updateRecord(model, selectedRow, rowData);

                            PhieuNhap pn = new PhieuNhap(
                                    Integer.parseInt(maPhieuNhap),
                                    Integer.parseInt(maNhanVien),
                                    Integer.parseInt(maNhaPhanPhoi),
                                    Float.parseFloat(tongTien),
                                    new Date(ngayNhap),
                                    chuThich
                            );
                            UpdateRecord(pn, "PhieuNhap", "MaPhieuNhap", Integer.parseInt(maPhieuNhap));

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
                            "Bạn có chắc chắn muốn xoá phiếu nhập đã chọn?",
                            "Xác nhận",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirm == JOptionPane.YES_OPTION) {
                        String maPhieuNhap = safeGetValue(selectedRow, 0);
                        if (maPhieuNhap != null) {
                            RecordHandler.deleteRecord(model, selectedRow);
                            DeleteRecord("PhieuNhap", "MaPhieuNhap", Integer.parseInt(maPhieuNhap));
                        } else {
                            JOptionPane.showMessageDialog(null, "Dữ liệu không hợp lệ để xoá!");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Chọn một dòng để xoá!");
                }
            }
        });
    }

    private String safeGetValue(int row, int column) {
        Object value = model.getValueAt(row, column);
        return value != null ? value.toString() : null;
    }
}