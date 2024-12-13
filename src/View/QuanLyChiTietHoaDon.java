package View;

import Model.ChiTietHoaDon;
import utility.RecordHandler;

import javax.swing.*;
import java.awt.*;
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
        //==========================
        // NÚT THÊM
        //==========================
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tạo panel để nhập liệu (6 dòng, 2 cột)
                JPanel inputPanel = new JPanel(new GridLayout(6, 2, 5, 5));

                JLabel lblMaCTHD = new JLabel("Mã CTHD:");
                JTextField tfMaCTHD = new JTextField();

                JLabel lblMaHD = new JLabel("Mã HĐ:");
                JTextField tfMaHD = new JTextField();

                JLabel lblMaSP = new JLabel("Mã SP:");
                JTextField tfMaSP = new JTextField();

                JLabel lblSoLuong = new JLabel("Số Lượng:");
                JTextField tfSoLuong = new JTextField();

                JLabel lblTongTien = new JLabel("Tổng Tiền:");
                JTextField tfTongTien = new JTextField();

                JLabel lblGhiChu = new JLabel("Ghi Chú:");
                JTextField tfGhiChu = new JTextField();

                // Thêm các thành phần vào panel
                inputPanel.add(lblMaCTHD);
                inputPanel.add(tfMaCTHD);
                inputPanel.add(lblMaHD);
                inputPanel.add(tfMaHD);
                inputPanel.add(lblMaSP);
                inputPanel.add(tfMaSP);
                inputPanel.add(lblSoLuong);
                inputPanel.add(tfSoLuong);
                inputPanel.add(lblTongTien);
                inputPanel.add(tfTongTien);
                inputPanel.add(lblGhiChu);
                inputPanel.add(tfGhiChu);

                // Hiển thị panel trong một hộp thoại
                int result = JOptionPane.showConfirmDialog(
                        null,
                        inputPanel,
                        "Nhập thông tin Chi Tiết Hóa Đơn",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

                if (result == JOptionPane.OK_OPTION) {
                    String maCTHD = tfMaCTHD.getText().trim();
                    String maHD = tfMaHD.getText().trim();
                    String maSP = tfMaSP.getText().trim();
                    String soLuong = tfSoLuong.getText().trim();
                    String tongTien = tfTongTien.getText().trim();
                    String ghiChu = tfGhiChu.getText().trim();

                    // Kiểm tra dữ liệu
                    if (!maCTHD.isEmpty() && !maHD.isEmpty() &&
                            !maSP.isEmpty() && !soLuong.isEmpty() &&
                            !tongTien.isEmpty()) {

                        // Thêm vào model (bảng)
                        Object[] rowData = {maCTHD, maHD, maSP, soLuong, tongTien, ghiChu};
                        RecordHandler.addRecord(model, rowData);

                        // Tạo đối tượng và thêm vào database
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
                // Người dùng Cancel hoặc đóng thì không làm gì
            }
        });

        //==========================
        // NÚT SỬA
        //==========================
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
                        // Lấy dữ liệu cũ từ bảng
                        String oldMaCTHD = safeGetValue(selectedRow, 0);
                        String oldMaHD = safeGetValue(selectedRow, 1);
                        String oldMaSP = safeGetValue(selectedRow, 2);
                        String oldSoLuong = safeGetValue(selectedRow, 3);
                        String oldTongTien = safeGetValue(selectedRow, 4);
                        String oldGhiChu = safeGetValue(selectedRow, 5);

                        // Tạo panel nhập liệu
                        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 5, 5));

                        JLabel lblMaCTHD = new JLabel("Mã CTHD:");
                        JTextField tfMaCTHD = new JTextField(oldMaCTHD);

                        JLabel lblMaHD = new JLabel("Mã HĐ:");
                        JTextField tfMaHD = new JTextField(oldMaHD);

                        JLabel lblMaSP = new JLabel("Mã SP:");
                        JTextField tfMaSP = new JTextField(oldMaSP);

                        JLabel lblSoLuong = new JLabel("Số Lượng:");
                        JTextField tfSoLuong = new JTextField(oldSoLuong);

                        JLabel lblTongTien = new JLabel("Tổng Tiền:");
                        JTextField tfTongTien = new JTextField(oldTongTien);

                        JLabel lblGhiChu = new JLabel("Ghi Chú:");
                        JTextField tfGhiChu = new JTextField(oldGhiChu);

                        // Thêm các thành phần vào panel
                        inputPanel.add(lblMaCTHD);
                        inputPanel.add(tfMaCTHD);
                        inputPanel.add(lblMaHD);
                        inputPanel.add(tfMaHD);
                        inputPanel.add(lblMaSP);
                        inputPanel.add(tfMaSP);
                        inputPanel.add(lblSoLuong);
                        inputPanel.add(tfSoLuong);
                        inputPanel.add(lblTongTien);
                        inputPanel.add(tfTongTien);
                        inputPanel.add(lblGhiChu);
                        inputPanel.add(tfGhiChu);

                        // Hiển thị panel trong một dialog
                        int result = JOptionPane.showConfirmDialog(
                                null,
                                inputPanel,
                                "Sửa Chi Tiết Hóa Đơn",
                                JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.PLAIN_MESSAGE
                        );

                        if (result == JOptionPane.OK_OPTION) {
                            String newMaCTHD = tfMaCTHD.getText().trim();
                            String newMaHD = tfMaHD.getText().trim();
                            String newMaSP = tfMaSP.getText().trim();
                            String newSoLuong = tfSoLuong.getText().trim();
                            String newTongTien = tfTongTien.getText().trim();
                            String newGhiChu = tfGhiChu.getText().trim();

                            if (!newMaCTHD.isEmpty() && !newMaHD.isEmpty() &&
                                    !newMaSP.isEmpty() && !newSoLuong.isEmpty() &&
                                    !newTongTien.isEmpty()) {

                                // Cập nhật bảng
                                Object[] rowData = {newMaCTHD, newMaHD, newMaSP, newSoLuong, newTongTien, newGhiChu};
                                RecordHandler.updateRecord(model, selectedRow, rowData);

                                // Cập nhật DB
                                ChiTietHoaDon cthd = new ChiTietHoaDon(
                                        Integer.parseInt(newMaCTHD),
                                        Integer.parseInt(newMaHD),
                                        Integer.parseInt(newMaSP),
                                        Integer.parseInt(newSoLuong),
                                        Float.parseFloat(newTongTien),
                                        newGhiChu
                                );
                                UpdateRecord(cthd, "ChiTietHoaDon", "MaCTHD", Integer.parseInt(newMaCTHD));

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

        //==========================
        // NÚT XÓA (giữ nguyên logic)
        //==========================
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