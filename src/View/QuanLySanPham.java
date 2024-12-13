package View;

import Model.SanPham;
import utility.RecordHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static Model.Db4Obj.*;

public class QuanLySanPham extends BaseManagementPanel {
    public QuanLySanPham() {
        // Gọi super() với tiêu đề và các cột
        super("Quản Lý Sản Phẩm", new String[]{
                "Mã Sản Phẩm",
                "Tên Sản Phẩm",
                "Loại Sản Phẩm",
                "Hãng Sản Xuất",
                "Giá Nhập",
                "Giá Bán",
                "Tồn Kho",
                "Trạng Thái",
                "Blob",
                "Chú Thích"
        });

        // Tải dữ liệu vào bảng
        loadData(10);
    }

    private void loadData(int columns) {
        ArrayList sanPhams = ListAllDuLieu("SanPham");
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
        //============================
        // NÚT THÊM
        //============================
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddDialog();
            }
        });

        //============================
        // NÚT SỬA
        //============================
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showUpdateDialog();
            }
        });

        //============================
        // NÚT XÓA (giữ logic cũ)
        //============================
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSanPham();
            }
        });
    }

    /**
     * Hiển thị popup để THÊM sản phẩm
     */
    private void showAddDialog() {
        // Tạo panel nhập liệu (10 trường)
        JPanel inputPanel = new JPanel(new GridLayout(10, 2, 5, 5));

        JLabel lblMaSP = new JLabel("Mã Sản Phẩm:");
        JTextField tfMaSP = new JTextField();

        JLabel lblTenSP = new JLabel("Tên Sản Phẩm:");
        JTextField tfTenSP = new JTextField();

        JLabel lblLoaiSP = new JLabel("Loại Sản Phẩm:");
        JTextField tfLoaiSP = new JTextField();

        JLabel lblHangSX = new JLabel("Hãng Sản Xuất:");
        JTextField tfHangSX = new JTextField();

        JLabel lblGiaNhap = new JLabel("Giá Nhập:");
        JTextField tfGiaNhap = new JTextField();

        JLabel lblGiaBan = new JLabel("Giá Bán:");
        JTextField tfGiaBan = new JTextField();

        JLabel lblTonKho = new JLabel("Tồn Kho:");
        JTextField tfTonKho = new JTextField();

        JLabel lblTrangThai = new JLabel("Trạng Thái:");
        JTextField tfTrangThai = new JTextField();

        JLabel lblBlob = new JLabel("Blob:");
        JTextField tfBlob = new JTextField();

        JLabel lblChuThich = new JLabel("Chú Thích:");
        JTextField tfChuThich = new JTextField();

        // Thêm các thành phần vào inputPanel
        inputPanel.add(lblMaSP);
        inputPanel.add(tfMaSP);
        inputPanel.add(lblTenSP);
        inputPanel.add(tfTenSP);
        inputPanel.add(lblLoaiSP);
        inputPanel.add(tfLoaiSP);
        inputPanel.add(lblHangSX);
        inputPanel.add(tfHangSX);
        inputPanel.add(lblGiaNhap);
        inputPanel.add(tfGiaNhap);
        inputPanel.add(lblGiaBan);
        inputPanel.add(tfGiaBan);
        inputPanel.add(lblTonKho);
        inputPanel.add(tfTonKho);
        inputPanel.add(lblTrangThai);
        inputPanel.add(tfTrangThai);
        inputPanel.add(lblBlob);
        inputPanel.add(tfBlob);
        inputPanel.add(lblChuThich);
        inputPanel.add(tfChuThich);

        // Hiển thị dialog
        int result = JOptionPane.showConfirmDialog(
                null,
                inputPanel,
                "Thêm Sản Phẩm",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            String maSanPham = tfMaSP.getText().trim();
            String tenSanPham = tfTenSP.getText().trim();
            String loaiSanPham = tfLoaiSP.getText().trim();
            String hangSanXuat = tfHangSX.getText().trim();
            String giaNhap = tfGiaNhap.getText().trim();
            String giaBan = tfGiaBan.getText().trim();
            String tonKho = tfTonKho.getText().trim();
            String trangThai = tfTrangThai.getText().trim();
            String blob = tfBlob.getText().trim();
            String chuThich = tfChuThich.getText().trim();

            // Kiểm tra thông tin không rỗng
            if (!maSanPham.isEmpty() && !tenSanPham.isEmpty() && !loaiSanPham.isEmpty() &&
                    !hangSanXuat.isEmpty() && !giaNhap.isEmpty() && !giaBan.isEmpty() &&
                    !tonKho.isEmpty() && !trangThai.isEmpty()) {
                // Thêm vào bảng
                Object[] rowData = {maSanPham, tenSanPham, loaiSanPham, hangSanXuat, giaNhap, giaBan, tonKho, trangThai, blob, chuThich};
                RecordHandler.addRecord(model, rowData);

                // Thêm vào DB
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
    }

    /**
     * Hiển thị popup để SỬA sản phẩm
     */
    private void showUpdateDialog() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Chọn một dòng để sửa!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                null,
                "Bạn có chắc chắn muốn sửa sản phẩm đã chọn?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            // Lấy dữ liệu cũ từ bảng
            String oldMaSP = safeGetValue(selectedRow, 0);
            String oldTenSP = safeGetValue(selectedRow, 1);
            String oldLoaiSP = safeGetValue(selectedRow, 2);
            String oldHangSX = safeGetValue(selectedRow, 3);
            String oldGiaNhap = safeGetValue(selectedRow, 4);
            String oldGiaBan = safeGetValue(selectedRow, 5);
            String oldTonKho = safeGetValue(selectedRow, 6);
            String oldTrangThai = safeGetValue(selectedRow, 7);
            String oldBlob = safeGetValue(selectedRow, 8);
            String oldChuThich = safeGetValue(selectedRow, 9);

            // Tạo panel nhập liệu
            JPanel inputPanel = new JPanel(new GridLayout(10, 2, 5, 5));

            JLabel lblMaSP = new JLabel("Mã Sản Phẩm:");
            JTextField tfMaSP = new JTextField(oldMaSP);

            JLabel lblTenSP = new JLabel("Tên Sản Phẩm:");
            JTextField tfTenSP = new JTextField(oldTenSP);

            JLabel lblLoaiSP = new JLabel("Loại Sản Phẩm:");
            JTextField tfLoaiSP = new JTextField(oldLoaiSP);

            JLabel lblHangSX = new JLabel("Hãng Sản Xuất:");
            JTextField tfHangSX = new JTextField(oldHangSX);

            JLabel lblGiaNhap = new JLabel("Giá Nhập:");
            JTextField tfGiaNhap = new JTextField(oldGiaNhap);

            JLabel lblGiaBan = new JLabel("Giá Bán:");
            JTextField tfGiaBan = new JTextField(oldGiaBan);

            JLabel lblTonKho = new JLabel("Tồn Kho:");
            JTextField tfTonKho = new JTextField(oldTonKho);

            JLabel lblTrangThai = new JLabel("Trạng Thái:");
            JTextField tfTrangThai = new JTextField(oldTrangThai);

            JLabel lblBlob = new JLabel("Blob:");
            JTextField tfBlob = new JTextField(oldBlob);

            JLabel lblChuThich = new JLabel("Chú Thích:");
            JTextField tfChuThich = new JTextField(oldChuThich);

            // Thêm component vào panel
            inputPanel.add(lblMaSP);
            inputPanel.add(tfMaSP);
            inputPanel.add(lblTenSP);
            inputPanel.add(tfTenSP);
            inputPanel.add(lblLoaiSP);
            inputPanel.add(tfLoaiSP);
            inputPanel.add(lblHangSX);
            inputPanel.add(tfHangSX);
            inputPanel.add(lblGiaNhap);
            inputPanel.add(tfGiaNhap);
            inputPanel.add(lblGiaBan);
            inputPanel.add(tfGiaBan);
            inputPanel.add(lblTonKho);
            inputPanel.add(tfTonKho);
            inputPanel.add(lblTrangThai);
            inputPanel.add(tfTrangThai);
            inputPanel.add(lblBlob);
            inputPanel.add(tfBlob);
            inputPanel.add(lblChuThich);
            inputPanel.add(tfChuThich);

            int result = JOptionPane.showConfirmDialog(
                    null,
                    inputPanel,
                    "Sửa Sản Phẩm",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );

            if (result == JOptionPane.OK_OPTION) {
                String newMaSP = tfMaSP.getText().trim();
                String newTenSP = tfTenSP.getText().trim();
                String newLoaiSP = tfLoaiSP.getText().trim();
                String newHangSX = tfHangSX.getText().trim();
                String newGiaNhap = tfGiaNhap.getText().trim();
                String newGiaBan = tfGiaBan.getText().trim();
                String newTonKho = tfTonKho.getText().trim();
                String newTrangThai = tfTrangThai.getText().trim();
                String newBlob = tfBlob.getText().trim();
                String newChuThich = tfChuThich.getText().trim();

                if (!newMaSP.isEmpty() && !newTenSP.isEmpty() && !newLoaiSP.isEmpty() &&
                        !newHangSX.isEmpty() && !newGiaNhap.isEmpty() && !newGiaBan.isEmpty() &&
                        !newTonKho.isEmpty() && !newTrangThai.isEmpty()) {
                    // Cập nhật bảng
                    Object[] rowData = {newMaSP, newTenSP, newLoaiSP, newHangSX, newGiaNhap, newGiaBan, newTonKho, newTrangThai, newBlob, newChuThich};
                    RecordHandler.updateRecord(model, selectedRow, rowData);

                    // Cập nhật DB
                    SanPham sp = new SanPham(
                            Integer.parseInt(newMaSP),
                            newTenSP,
                            Integer.parseInt(newLoaiSP),
                            Integer.parseInt(newHangSX),
                            Float.parseFloat(newGiaNhap),
                            Float.parseFloat(newGiaBan),
                            Integer.parseInt(newTonKho),
                            Integer.parseInt(newTrangThai),
                            newBlob,
                            newChuThich
                    );
                    UpdateRecord(sp, "SanPham", "MaSanPham", Integer.parseInt(newMaSP));

                    JOptionPane.showMessageDialog(null, "Sửa thành công!");
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
                }
            }
        }
    }

    /**
     * Xoá sản phẩm
     */
    private void deleteSanPham() {
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

    private String safeGetValue(int row, int column) {
        Object value = model.getValueAt(row, column);
        return (value != null) ? value.toString() : null;
    }
}