package View;

import Model.PhieuNhap;
import utility.RecordHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import static Model.Db4Obj.*;

public class QuanLyPhieuNhap extends BaseManagementPanel {
    public QuanLyPhieuNhap() {
        // Gọi super() với tiêu đề và các cột
        super("Quản Lý Phiếu Nhập", new String[]{
                "Mã Phiếu Nhập",
                "Mã Nhân Viên",
                "Mã Nhà Phân Phối",
                "Tổng Tiền",
                "Ngày Nhập",
                "Chú Thích"
        });

        // Tải dữ liệu vào bảng
        loadData(6);
    }

    private void loadData(int columns) {
        ArrayList phieuNhaps = ListAllDuLieu("PhieuNhap");
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
        //==========================
        // NÚT THÊM
        //==========================
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddDialog();
            }
        });

        //==========================
        // NÚT SỬA
        //==========================
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showUpdateDialog();
            }
        });

        //==========================
        // NÚT XÓA
        //==========================
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletePhieuNhap();
            }
        });
    }

    /**
     * Hiển thị popup Thêm Phiếu Nhập
     */
    private void showAddDialog() {
        // Tạo panel nhập liệu (6 trường)
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 5, 5));

        JLabel lblMaPN = new JLabel("Mã Phiếu Nhập:");
        JTextField tfMaPN = new JTextField();

        JLabel lblMaNV = new JLabel("Mã Nhân Viên:");
        JTextField tfMaNV = new JTextField();

        JLabel lblMaNPP = new JLabel("Mã Nhà Phân Phối:");
        JTextField tfMaNPP = new JTextField();

        JLabel lblTongTien = new JLabel("Tổng Tiền:");
        JTextField tfTongTien = new JTextField();

        JLabel lblNgayNhap = new JLabel("Ngày Nhập:");
        JTextField tfNgayNhap = new JTextField();

        JLabel lblChuThich = new JLabel("Chú Thích:");
        JTextField tfChuThich = new JTextField();

        inputPanel.add(lblMaPN);
        inputPanel.add(tfMaPN);
        inputPanel.add(lblMaNV);
        inputPanel.add(tfMaNV);
        inputPanel.add(lblMaNPP);
        inputPanel.add(tfMaNPP);
        inputPanel.add(lblTongTien);
        inputPanel.add(tfTongTien);
        inputPanel.add(lblNgayNhap);
        inputPanel.add(tfNgayNhap);
        inputPanel.add(lblChuThich);
        inputPanel.add(tfChuThich);

        int result = JOptionPane.showConfirmDialog(
                null,
                inputPanel,
                "Thêm Phiếu Nhập",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            String maPN = tfMaPN.getText().trim();
            String maNV = tfMaNV.getText().trim();
            String maNPP = tfMaNPP.getText().trim();
            String tongTien = tfTongTien.getText().trim();
            String ngayNhap = tfNgayNhap.getText().trim();
            String chuThich = tfChuThich.getText().trim();

            if (!maPN.isEmpty() && !maNV.isEmpty() && !maNPP.isEmpty() && !tongTien.isEmpty() && !ngayNhap.isEmpty()) {
                // Thêm vào bảng
                Object[] rowData = {maPN, maNV, maNPP, tongTien, ngayNhap, chuThich};
                RecordHandler.addRecord(model, rowData);

                // Tạo đối tượng Phiếu Nhập để thêm DB
                PhieuNhap pn = new PhieuNhap(
                        Integer.parseInt(maPN),
                        Integer.parseInt(maNV),
                        Integer.parseInt(maNPP),
                        Float.parseFloat(tongTien),
                        new Date(ngayNhap), // Deprecated constructor, bạn nên dùng SimpleDateFormat
                        chuThich
                );
                AddRecord(pn, "PhieuNhap");
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
            }
        }
    }

    /**
     * Hiển thị popup Sửa Phiếu Nhập
     */
    private void showUpdateDialog() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Chọn một dòng để sửa!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                null,
                "Bạn có chắc chắn muốn sửa phiếu nhập đã chọn?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            // Lấy dữ liệu cũ
            String oldMaPN = safeGetValue(selectedRow, 0);
            String oldMaNV = safeGetValue(selectedRow, 1);
            String oldMaNPP = safeGetValue(selectedRow, 2);
            String oldTongTien = safeGetValue(selectedRow, 3);
            String oldNgayNhap = safeGetValue(selectedRow, 4);
            String oldChuThich = safeGetValue(selectedRow, 5);

            // Tạo panel nhập liệu
            JPanel inputPanel = new JPanel(new GridLayout(6, 2, 5, 5));

            JLabel lblMaPN = new JLabel("Mã Phiếu Nhập:");
            JTextField tfMaPN = new JTextField(oldMaPN);

            JLabel lblMaNV = new JLabel("Mã Nhân Viên:");
            JTextField tfMaNV = new JTextField(oldMaNV);

            JLabel lblMaNPP = new JLabel("Mã Nhà Phân Phối:");
            JTextField tfMaNPP = new JTextField(oldMaNPP);

            JLabel lblTongTien = new JLabel("Tổng Tiền:");
            JTextField tfTongTien = new JTextField(oldTongTien);

            JLabel lblNgayNhap = new JLabel("Ngày Nhập:");
            JTextField tfNgayNhap = new JTextField(oldNgayNhap);

            JLabel lblChuThich = new JLabel("Chú Thích:");
            JTextField tfChuThich = new JTextField(oldChuThich);

            inputPanel.add(lblMaPN);
            inputPanel.add(tfMaPN);
            inputPanel.add(lblMaNV);
            inputPanel.add(tfMaNV);
            inputPanel.add(lblMaNPP);
            inputPanel.add(tfMaNPP);
            inputPanel.add(lblTongTien);
            inputPanel.add(tfTongTien);
            inputPanel.add(lblNgayNhap);
            inputPanel.add(tfNgayNhap);
            inputPanel.add(lblChuThich);
            inputPanel.add(tfChuThich);

            int result = JOptionPane.showConfirmDialog(
                    null,
                    inputPanel,
                    "Sửa Phiếu Nhập",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );

            if (result == JOptionPane.OK_OPTION) {
                String newMaPN = tfMaPN.getText().trim();
                String newMaNV = tfMaNV.getText().trim();
                String newMaNPP = tfMaNPP.getText().trim();
                String newTongTien = tfTongTien.getText().trim();
                String newNgayNhap = tfNgayNhap.getText().trim();
                String newChuThich = tfChuThich.getText().trim();

                if (!newMaPN.isEmpty() && !newMaNV.isEmpty() && !newMaNPP.isEmpty() && !newTongTien.isEmpty() && !newNgayNhap.isEmpty()) {
                    // Cập nhật bảng
                    Object[] rowData = {newMaPN, newMaNV, newMaNPP, newTongTien, newNgayNhap, newChuThich};
                    RecordHandler.updateRecord(model, selectedRow, rowData);

                    // Cập nhật DB
                    PhieuNhap pn = new PhieuNhap(
                            Integer.parseInt(newMaPN),
                            Integer.parseInt(newMaNV),
                            Integer.parseInt(newMaNPP),
                            Float.parseFloat(newTongTien),
                            new Date(newNgayNhap), // Nên parse date bằng SimpleDateFormat
                            newChuThich
                    );
                    UpdateRecord(pn, "PhieuNhap", "MaPhieuNhap", Integer.parseInt(newMaPN));

                    JOptionPane.showMessageDialog(null, "Sửa thành công!");
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
                }
            }
        }
    }

    /**
     * Xóa phiếu nhập
     */
    private void deletePhieuNhap() {
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

    private String safeGetValue(int row, int column) {
        Object value = model.getValueAt(row, column);
        return (value != null) ? value.toString() : null;
    }
}