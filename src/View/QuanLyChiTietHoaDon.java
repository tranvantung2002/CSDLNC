package View;

import Model.ChiTietHoaDon;
import utility.RecordHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static Model.Db4Obj.*;

public class QuanLyChiTietHoaDon extends BaseManagementPanel {
    public QuanLyChiTietHoaDon() {
        // Gọi super() với tiêu đề và các cột
        super("Quản Lý Chi Tiết Hóa Đơn", new String[]{"Mã CTHD", "Mã HĐ", "Mã SP", "Số Lượng", "Tổng Tiền", "Ghi Chú"});

        // Tải dữ liệu vào bảng
        loadData(6);
    }

    private void loadData(int columns) {
        ArrayList chiTietHoaDons = ListAllDuLieu("ChiTietHoaDon");
        Object[][] data = new Object[chiTietHoaDons.size()][columns];

        for (int i = 0; i < chiTietHoaDons.size(); i++) {
            ChiTietHoaDon cthd = (ChiTietHoaDon) chiTietHoaDons.get(i);
            Object[] rowData = {
                    cthd.getMaCTHD(),
                    cthd.getMaHoaDon(),
                    cthd.getMaSanPham(),
                    cthd.getSoLuong(),
                    cthd.getTongTien(),
                    cthd.getGhiChu()
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
                String maCTHD = JOptionPane.showInputDialog("Nhập Mã CTHD:");
                String maHD = JOptionPane.showInputDialog("Nhập Mã HĐ:");
                String maSP = JOptionPane.showInputDialog("Nhập Mã SP:");
                String soLuong = JOptionPane.showInputDialog("Nhập Số Lượng:");
                String tongTien = JOptionPane.showInputDialog("Nhập Tổng Tiền:");
                String ghiChu = JOptionPane.showInputDialog("Nhập Ghi Chú:");

                if (maCTHD != null && maHD != null && maSP != null && soLuong != null && tongTien != null) {
                    Object[] rowData = {maCTHD, maHD, maSP, soLuong, tongTien, ghiChu};
                    RecordHandler.addRecord(model, rowData);

                    ChiTietHoaDon cthd = new ChiTietHoaDon(
                            Integer.parseInt(maCTHD),
                            Integer.parseInt(maHD),
                            Integer.parseInt(maSP),
                            Integer.parseInt(soLuong),
                            Float.parseFloat(tongTien),
                            ghiChu
                    );
                    AddRecord(cthd, "ChiTietHoaDon");
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
                        String maCTHD = safeGetValue(selectedRow, 0);
                        String maHD = safeGetValue(selectedRow, 1);
                        String maSP = safeGetValue(selectedRow, 2);
                        String soLuong = safeGetValue(selectedRow, 3);
                        String tongTien = safeGetValue(selectedRow, 4);
                        String ghiChu = safeGetValue(selectedRow, 5);

                        if (maCTHD != null && maHD != null && maSP != null && soLuong != null && tongTien != null) {
                            Object[] rowData = {maCTHD, maHD, maSP, soLuong, tongTien, ghiChu};
                            RecordHandler.updateRecord(model, selectedRow, rowData);

                            ChiTietHoaDon cthd = new ChiTietHoaDon(
                                    Integer.parseInt(maCTHD),
                                    Integer.parseInt(maHD),
                                    Integer.parseInt(maSP),
                                    Integer.parseInt(soLuong),
                                    Float.parseFloat(tongTien),
                                    ghiChu
                            );
                            UpdateRecord(cthd, "ChiTietHoaDon", "MaCTHD", Integer.parseInt(maCTHD));

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
                        String maCTHD = safeGetValue(selectedRow, 0);
                        Object aa = model.getValueAt(selectedRow, 1);
                        System.out.println(aa);
                        System.out.println(maCTHD);
                        if (maCTHD != null) {
                            RecordHandler.deleteRecord(model, selectedRow);
                            DeleteRecord("ChiTietHoaDon", "MaCTHD", Integer.parseInt(maCTHD));
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