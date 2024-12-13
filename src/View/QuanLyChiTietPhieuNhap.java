package View;

import Model.ChiTietPhieuNhap;
import utility.RecordHandler;

import javax.swing.*;
import java.awt.*;
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
        // Nút Thêm
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tạo panel chứa các label và textfield
                JPanel inputPanel = new JPanel(new GridLayout(6, 2, 5, 5));

                JLabel lblMaCTPN = new JLabel("Mã CTPN:");
                JTextField tfMaCTPN = new JTextField();

                JLabel lblMaPhieuNhap = new JLabel("Mã Phiếu Nhập:");
                JTextField tfMaPhieuNhap = new JTextField();

                JLabel lblMaSanPham = new JLabel("Mã Sản Phẩm:");
                JTextField tfMaSanPham = new JTextField();

                JLabel lblSoLuong = new JLabel("Số Lượng:");
                JTextField tfSoLuong = new JTextField();

                JLabel lblTongTien = new JLabel("Tổng Tiền:");
                JTextField tfTongTien = new JTextField();

                JLabel lblGhiChu = new JLabel("Chú Thích:");
                JTextField tfGhiChu = new JTextField();

                // Thêm label và textfield vào panel
                inputPanel.add(lblMaCTPN);
                inputPanel.add(tfMaCTPN);
                inputPanel.add(lblMaPhieuNhap);
                inputPanel.add(tfMaPhieuNhap);
                inputPanel.add(lblMaSanPham);
                inputPanel.add(tfMaSanPham);
                inputPanel.add(lblSoLuong);
                inputPanel.add(tfSoLuong);
                inputPanel.add(lblTongTien);
                inputPanel.add(tfTongTien);
                inputPanel.add(lblGhiChu);
                inputPanel.add(tfGhiChu);

                // Hiển thị panel trong 1 hộp thoại xác nhận
                int result = JOptionPane.showConfirmDialog(
                        null,
                        inputPanel,
                        "Nhập thông tin Chi Tiết Phiếu Nhập",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

                // Nếu người dùng bấm OK
                if (result == JOptionPane.OK_OPTION) {
                    String maCTPN = tfMaCTPN.getText().trim();
                    String maPhieuNhap = tfMaPhieuNhap.getText().trim();
                    String maSanPham = tfMaSanPham.getText().trim();
                    String soLuong = tfSoLuong.getText().trim();
                    String tongTien = tfTongTien.getText().trim();
                    String ghiChu = tfGhiChu.getText().trim();

                    // Kiểm tra xem người dùng đã nhập đủ chưa
                    if (!maCTPN.isEmpty() && !maPhieuNhap.isEmpty() &&
                            !maSanPham.isEmpty() && !soLuong.isEmpty() &&
                            !tongTien.isEmpty()) {

                        // Thêm vào bảng (model)
                        Object[] rowData = {maCTPN, maPhieuNhap, maSanPham, soLuong, tongTien, ghiChu};
                        RecordHandler.addRecord(model, rowData);

                        // Tạo đối tượng ChiTietPhieuNhap và lưu vào CSDL
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
                // Nếu người dùng bấm Cancel hoặc đóng hộp thoại -> không làm gì thêm
            }
        });

        // Nút Sửa
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
                        // Lấy dữ liệu cũ
                        String maCTPN = safeGetValue(selectedRow, 0);
                        String maPhieuNhap = safeGetValue(selectedRow, 1);
                        String maSanPham = safeGetValue(selectedRow, 2);
                        String soLuong = safeGetValue(selectedRow, 3);
                        String tongTien = safeGetValue(selectedRow, 4);
                        String ghiChu = safeGetValue(selectedRow, 5);

                        // Tạo panel để sửa dữ liệu
                        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 5, 5));

                        JLabel lblMaCTPN = new JLabel("Mã CTPN:");
                        JTextField tfMaCTPN = new JTextField(maCTPN);

                        JLabel lblMaPhieuNhap = new JLabel("Mã Phiếu Nhập:");
                        JTextField tfMaPhieuNhap = new JTextField(maPhieuNhap);

                        JLabel lblMaSanPham = new JLabel("Mã Sản Phẩm:");
                        JTextField tfMaSanPham = new JTextField(maSanPham);

                        JLabel lblSoLuong = new JLabel("Số Lượng:");
                        JTextField tfSoLuong = new JTextField(soLuong);

                        JLabel lblTongTien = new JLabel("Tổng Tiền:");
                        JTextField tfTongTien = new JTextField(tongTien);

                        JLabel lblGhiChu = new JLabel("Chú Thích:");
                        JTextField tfGhiChu = new JTextField(ghiChu);

                        inputPanel.add(lblMaCTPN);
                        inputPanel.add(tfMaCTPN);
                        inputPanel.add(lblMaPhieuNhap);
                        inputPanel.add(tfMaPhieuNhap);
                        inputPanel.add(lblMaSanPham);
                        inputPanel.add(tfMaSanPham);
                        inputPanel.add(lblSoLuong);
                        inputPanel.add(tfSoLuong);
                        inputPanel.add(lblTongTien);
                        inputPanel.add(tfTongTien);
                        inputPanel.add(lblGhiChu);
                        inputPanel.add(tfGhiChu);

                        // Hiển thị panel trong một dialog xác nhận
                        int result = JOptionPane.showConfirmDialog(
                                null,
                                inputPanel,
                                "Sửa Chi Tiết Phiếu Nhập",
                                JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.PLAIN_MESSAGE
                        );

                        if (result == JOptionPane.OK_OPTION) {
                            String newMaCTPN = tfMaCTPN.getText().trim();
                            String newMaPhieuNhap = tfMaPhieuNhap.getText().trim();
                            String newMaSanPham = tfMaSanPham.getText().trim();
                            String newSoLuong = tfSoLuong.getText().trim();
                            String newTongTien = tfTongTien.getText().trim();
                            String newGhiChu = tfGhiChu.getText().trim();

                            if (!newMaCTPN.isEmpty() && !newMaPhieuNhap.isEmpty() &&
                                    !newMaSanPham.isEmpty() && !newSoLuong.isEmpty() &&
                                    !newTongTien.isEmpty()) {

                                // Cập nhật bảng
                                Object[] rowData = {newMaCTPN, newMaPhieuNhap, newMaSanPham, newSoLuong, newTongTien, newGhiChu};
                                RecordHandler.updateRecord(model, selectedRow, rowData);

                                // Tạo đối tượng ChiTietPhieuNhap và lưu cập nhật vào CSDL
                                ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap(
                                        Integer.parseInt(newMaCTPN),
                                        Integer.parseInt(newMaPhieuNhap),
                                        Integer.parseInt(newMaSanPham),
                                        Integer.parseInt(newSoLuong),
                                        Float.parseFloat(newTongTien),
                                        newGhiChu
                                );
                                UpdateRecord(ctpn, "ChiTietPhieuNhap", "MaCTPN", Integer.parseInt(newMaCTPN));

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