package View;

import Model.HoaDon;
import utility.RecordHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import static Model.Db4Obj.*;

public class QuanLyHoaDon extends BaseManagementPanel {
    public QuanLyHoaDon() {
        // Gọi super() với tiêu đề và các cột
        super("Quản Lý Hóa Đơn", new String[]{
                "Mã Hóa Đơn",
                "Mã Khách Hàng",
                "Mã Nhân Viên",
                "Ngày Lập Hóa Đơn",
                "Tổng Tiền",
                "Ghi Chú"
        });

        // Tải dữ liệu vào bảng
        loadData(6);
    }

    private void loadData(int columns) {
        ArrayList hoaDons = ListAllDuLieu("HoaDon");
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
        //=================================
        // NÚT THÊM
        //=================================
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tạo panel nhập liệu (6 hàng, 2 cột)
                JPanel inputPanel = new JPanel(new GridLayout(6, 2, 5, 5));

                JLabel lblMaHoaDon = new JLabel("Mã Hóa Đơn:");
                JTextField tfMaHoaDon = new JTextField();

                JLabel lblMaKhachHang = new JLabel("Mã Khách Hàng:");
                JTextField tfMaKhachHang = new JTextField();

                JLabel lblMaNhanVien = new JLabel("Mã Nhân Viên:");
                JTextField tfMaNhanVien = new JTextField();

                JLabel lblNgayLap = new JLabel("Ngày Lập Hóa Đơn:");
                JTextField tfNgayLap = new JTextField();

                JLabel lblTongTien = new JLabel("Tổng Tiền:");
                JTextField tfTongTien = new JTextField();

                JLabel lblGhiChu = new JLabel("Ghi Chú:");
                JTextField tfGhiChu = new JTextField();

                // Thêm label và textfield vào panel
                inputPanel.add(lblMaHoaDon);
                inputPanel.add(tfMaHoaDon);
                inputPanel.add(lblMaKhachHang);
                inputPanel.add(tfMaKhachHang);
                inputPanel.add(lblMaNhanVien);
                inputPanel.add(tfMaNhanVien);
                inputPanel.add(lblNgayLap);
                inputPanel.add(tfNgayLap);
                inputPanel.add(lblTongTien);
                inputPanel.add(tfTongTien);
                inputPanel.add(lblGhiChu);
                inputPanel.add(tfGhiChu);

                int result = JOptionPane.showConfirmDialog(
                        null,
                        inputPanel,
                        "Nhập thông tin Hóa Đơn",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

                if (result == JOptionPane.OK_OPTION) {
                    String maHoaDon = tfMaHoaDon.getText().trim();
                    String maKhachHang = tfMaKhachHang.getText().trim();
                    String maNhanVien = tfMaNhanVien.getText().trim();
                    String ngayLapHD = tfNgayLap.getText().trim();
                    String tongTien = tfTongTien.getText().trim();
                    String ghiChu = tfGhiChu.getText().trim();

                    // Kiểm tra dữ liệu người dùng đã nhập
                    if (!maHoaDon.isEmpty() && !maKhachHang.isEmpty() &&
                            !maNhanVien.isEmpty() && !ngayLapHD.isEmpty() &&
                            !tongTien.isEmpty()) {
                        // Thêm vào bảng
                        Object[] rowData = {maHoaDon, maKhachHang, maNhanVien, ngayLapHD, tongTien, ghiChu};
                        RecordHandler.addRecord(model, rowData);

                        // Tạo đối tượng HoaDon và thêm vào DB
                        HoaDon hd = new HoaDon(
                                Integer.parseInt(maHoaDon),
                                Integer.parseInt(maKhachHang),
                                Integer.parseInt(maNhanVien),
                                new Date(ngayLapHD),  // Cách parse date này đã deprecated, khuyến cáo dùng SimpleDateFormat
                                Float.parseFloat(tongTien),
                                ghiChu
                        );
                        AddRecord(hd, "HoaDon");
                    } else {
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
                    }
                }
            }
        });

        //=================================
        // NÚT SỬA
        //=================================
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
                        // Lấy dữ liệu cũ từ bảng
                        String oldMaHoaDon = safeGetValue(selectedRow, 0);
                        String oldMaKhachHang = safeGetValue(selectedRow, 1);
                        String oldMaNhanVien = safeGetValue(selectedRow, 2);
                        String oldNgayLapHD = safeGetValue(selectedRow, 3);
                        String oldTongTien = safeGetValue(selectedRow, 4);
                        String oldGhiChu = safeGetValue(selectedRow, 5);

                        // Tạo panel để sửa dữ liệu
                        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 5, 5));

                        JLabel lblMaHoaDon = new JLabel("Mã Hóa Đơn:");
                        JTextField tfMaHoaDon = new JTextField(oldMaHoaDon);

                        JLabel lblMaKhachHang = new JLabel("Mã Khách Hàng:");
                        JTextField tfMaKhachHang = new JTextField(oldMaKhachHang);

                        JLabel lblMaNhanVien = new JLabel("Mã Nhân Viên:");
                        JTextField tfMaNhanVien = new JTextField(oldMaNhanVien);

                        JLabel lblNgayLap = new JLabel("Ngày Lập Hóa Đơn:");
                        JTextField tfNgayLap = new JTextField(oldNgayLapHD);

                        JLabel lblTongTien = new JLabel("Tổng Tiền:");
                        JTextField tfTongTien = new JTextField(oldTongTien);

                        JLabel lblGhiChu = new JLabel("Ghi Chú:");
                        JTextField tfGhiChu = new JTextField(oldGhiChu);

                        // Thêm các thành phần vào panel
                        inputPanel.add(lblMaHoaDon);
                        inputPanel.add(tfMaHoaDon);
                        inputPanel.add(lblMaKhachHang);
                        inputPanel.add(tfMaKhachHang);
                        inputPanel.add(lblMaNhanVien);
                        inputPanel.add(tfMaNhanVien);
                        inputPanel.add(lblNgayLap);
                        inputPanel.add(tfNgayLap);
                        inputPanel.add(lblTongTien);
                        inputPanel.add(tfTongTien);
                        inputPanel.add(lblGhiChu);
                        inputPanel.add(tfGhiChu);

                        int result = JOptionPane.showConfirmDialog(
                                null,
                                inputPanel,
                                "Sửa thông tin Hóa Đơn",
                                JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.PLAIN_MESSAGE
                        );

                        if (result == JOptionPane.OK_OPTION) {
                            String newMaHoaDon = tfMaHoaDon.getText().trim();
                            String newMaKhachHang = tfMaKhachHang.getText().trim();
                            String newMaNhanVien = tfMaNhanVien.getText().trim();
                            String newNgayLapHD = tfNgayLap.getText().trim();
                            String newTongTien = tfTongTien.getText().trim();
                            String newGhiChu = tfGhiChu.getText().trim();

                            if (!newMaHoaDon.isEmpty() && !newMaKhachHang.isEmpty() &&
                                    !newMaNhanVien.isEmpty() && !newNgayLapHD.isEmpty() &&
                                    !newTongTien.isEmpty()) {
                                // Cập nhật bảng
                                Object[] rowData = {
                                        newMaHoaDon, newMaKhachHang, newMaNhanVien,
                                        newNgayLapHD, newTongTien, newGhiChu
                                };
                                RecordHandler.updateRecord(model, selectedRow, rowData);

                                // Cập nhật DB
                                HoaDon hd = new HoaDon(
                                        Integer.parseInt(newMaHoaDon),
                                        Integer.parseInt(newMaKhachHang),
                                        Integer.parseInt(newMaNhanVien),
                                        new Date(newNgayLapHD),
                                        Float.parseFloat(newTongTien),
                                        newGhiChu
                                );
                                UpdateRecord(hd, "HoaDon", "MaHoaDon", Integer.parseInt(newMaHoaDon));

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

        //=================================
        // NÚT XÓA (giữ nguyên logic)
        //=================================
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