package View;

import Model.HoaDon;
import utility.RecordHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import static Model.Db4Obj.*;

public class QuanLyHoaDon extends BaseManagementPanel {
    public QuanLyHoaDon() {
        // Gọi super() với tiêu đề và các cột
        super("Quản Lý Hóa Đơn", new String[]{"Mã Hóa Đơn", "Mã Khách Hàng", "Mã Nhân Viên", "Ngày Lập Hóa Đơn", "Tổng Tiền", "Ghi Chú"});

        // Tải dữ liệu vào bảng
        loadData(6);
    }

    private void loadData(int columns) {
        ArrayList hoaDons = ListAllDuLieu("HoaDon");
        Object[][] data = new Object[hoaDons.size()][columns];

        for (int i = 0; i < hoaDons.size(); i++) {
            HoaDon hd = (HoaDon) hoaDons.get(i);
            Object[] rowData = {
                    hd.getMaHoaDon(),
                    hd.getMaKhachHang(),
                    hd.getMaNhanVien(),
                    hd.getNgayLaphoaDon(),
                    hd.getTongTien(),
                    hd.getGhiChu()
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
                String maHoaDon = JOptionPane.showInputDialog("Nhập Mã Hóa Đơn:");
                String maKhachHang = JOptionPane.showInputDialog("Nhập Mã Khách Hàng:");
                String maNhanVien = JOptionPane.showInputDialog("Nhập Mã Nhân Viên:");
                String ngayLapHoaDon = JOptionPane.showInputDialog("Nhập Ngày Lập Hóa Đơn:");
                String tongTien = JOptionPane.showInputDialog("Nhập Tổng Tiền:");
                String ghiChu = JOptionPane.showInputDialog("Nhập Ghi Chú:");

                if (maHoaDon != null && maKhachHang != null && maNhanVien != null && ngayLapHoaDon != null && tongTien != null) {
                    Object[] rowData = {maHoaDon, maKhachHang, maNhanVien, ngayLapHoaDon, tongTien, ghiChu};
                    RecordHandler.addRecord(model, rowData);

                    HoaDon hd = new HoaDon(
                            Integer.parseInt(maHoaDon),
                            Integer.parseInt(maKhachHang),
                            Integer.parseInt(maNhanVien),
                            new Date(ngayLapHoaDon),
                            Float.parseFloat(tongTien),
                            ghiChu
                    );
                    AddRecord(hd, "HoaDon");
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
                            "Bạn có chắc chắn muốn sửa hóa đơn đã chọn?",
                            "Xác nhận",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirm == JOptionPane.YES_OPTION) {
                        String maHoaDon = safeGetValue(selectedRow, 0);
                        String maKhachHang = safeGetValue(selectedRow, 1);
                        String maNhanVien = safeGetValue(selectedRow, 2);
                        String ngayLapHoaDon = safeGetValue(selectedRow, 3);
                        String tongTien = safeGetValue(selectedRow, 4);
                        String ghiChu = safeGetValue(selectedRow, 5);

                        if (maHoaDon != null && maKhachHang != null && maNhanVien != null && ngayLapHoaDon != null && tongTien != null) {
                            Object[] rowData = {maHoaDon, maKhachHang, maNhanVien, ngayLapHoaDon, tongTien, ghiChu};
                            RecordHandler.updateRecord(model, selectedRow, rowData);

                            HoaDon hd = new HoaDon(
                                    Integer.parseInt(maHoaDon),
                                    Integer.parseInt(maKhachHang),
                                    Integer.parseInt(maNhanVien),
                                    new Date(ngayLapHoaDon),
                                    Float.parseFloat(tongTien),
                                    ghiChu
                            );
                            UpdateRecord(hd, "HoaDon", "MaHoaDon", Integer.parseInt(maHoaDon));

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
                            "Bạn có chắc chắn muốn xoá hóa đơn đã chọn?",
                            "Xác nhận",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirm == JOptionPane.YES_OPTION) {
                        String maHoaDon = safeGetValue(selectedRow, 0);
                        if (maHoaDon != null) {
                            RecordHandler.deleteRecord(model, selectedRow);
                            DeleteRecord("HoaDon", "MaHoaDon", Integer.parseInt(maHoaDon));
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