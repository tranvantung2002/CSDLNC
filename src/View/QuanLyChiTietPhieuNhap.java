package View;

import Model.ChiTietPhieuNhap;
import utility.RecordHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static Model.Db4Obj.*;

public class QuanLyChiTietPhieuNhap extends BaseManagementPanel {
    public QuanLyChiTietPhieuNhap() {
        // Gọi super() với tiêu đề và các cột
        super("Quản Lý Chi Tiết Phiếu Nhập", new String[]{"Mã CTPN", "Mã Phiếu Nhập", "Mã Sản Phẩm", "Số Lượng", "Tổng Tiền", "Chú Thích"});

        // Tải dữ liệu vào bảng
        loadData(6);
    }

    private void loadData(int columns) {
        ArrayList chiTietPhieuNhaps = ListAllDuLieu("ChiTietPhieuNhap");
        Object[][] data = new Object[chiTietPhieuNhaps.size()][columns];

        for (int i = 0; i < chiTietPhieuNhaps.size(); i++) {
            ChiTietPhieuNhap ctpn = (ChiTietPhieuNhap) chiTietPhieuNhaps.get(i);
            Object[] rowData = {
                    ctpn.getMaCTPN(),
                    ctpn.getMaPhieuNhap(),
                    ctpn.getMaSanPham(),
                    ctpn.getSoLuong(),
                    ctpn.getTongTien(),
                    ctpn.getGhiChu()
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
                String maCTPN = JOptionPane.showInputDialog("Nhập Mã CTPN:");
                String maPhieuNhap = JOptionPane.showInputDialog("Nhập Mã Phiếu Nhập:");
                String maSanPham = JOptionPane.showInputDialog("Nhập Mã Sản Phẩm:");
                String soLuong = JOptionPane.showInputDialog("Nhập Số Lượng:");
                String tongTien = JOptionPane.showInputDialog("Nhập Tổng Tiền:");
                String ghiChu = JOptionPane.showInputDialog("Nhập Chú Thích:");

                if (maCTPN != null && maPhieuNhap != null && maSanPham != null && soLuong != null && tongTien != null) {
                    Object[] rowData = {maCTPN, maPhieuNhap, maSanPham, soLuong, tongTien, ghiChu};
                    RecordHandler.addRecord(model, rowData);

                    ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap(
                            Integer.parseInt(maCTPN),
                            Integer.parseInt(maPhieuNhap),
                            Integer.parseInt(maSanPham),
                            Integer.parseInt(soLuong),
                            Float.parseFloat(tongTien),
                            ghiChu
                    );
                    AddRecord(ctpn, "ChiTietPhieuNhap");
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
                        String maCTPN = safeGetValue(selectedRow, 0);
                        String maPhieuNhap = safeGetValue(selectedRow, 1);
                        String maSanPham = safeGetValue(selectedRow, 2);
                        String soLuong = safeGetValue(selectedRow, 3);
                        String tongTien = safeGetValue(selectedRow, 4);
                        String ghiChu = safeGetValue(selectedRow, 5);

                        if (maCTPN != null && maPhieuNhap != null && maSanPham != null && soLuong != null && tongTien != null) {
                            Object[] rowData = {maCTPN, maPhieuNhap, maSanPham, soLuong, tongTien, ghiChu};
                            RecordHandler.updateRecord(model, selectedRow, rowData);

                            ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap(
                                    Integer.parseInt(maCTPN),
                                    Integer.parseInt(maPhieuNhap),
                                    Integer.parseInt(maSanPham),
                                    Integer.parseInt(soLuong),
                                    Float.parseFloat(tongTien),
                                    ghiChu
                            );
                            UpdateRecord(ctpn, "ChiTietPhieuNhap", "MaCTPN", Integer.parseInt(maCTPN));

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
                        String maCTPN = safeGetValue(selectedRow, 0);
                        if (maCTPN != null) {
                            RecordHandler.deleteRecord(model, selectedRow);
                            DeleteRecord("ChiTietPhieuNhap", "MaCTPN", Integer.parseInt(maCTPN));
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