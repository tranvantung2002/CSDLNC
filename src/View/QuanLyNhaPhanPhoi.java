package View;

import Model.NhaPhanPhoi;
import utility.RecordHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static Model.Db4Obj.*;

public class QuanLyNhaPhanPhoi extends BaseManagementPanel {
    public QuanLyNhaPhanPhoi() {
        // Gọi super() với tiêu đề và các cột
        super("Quản Lý Nhà Phân Phối", new String[]{
                "Mã Nhà Phân Phối",
                "Tên Nhà Phân Phối",
                "Địa Chỉ",
                "Số Điện Thoại",
                "Email",
                "Chú Thích"
        });

        // Tải dữ liệu vào bảng
        loadData(6);
    }

    private void loadData(int columns) {
        ArrayList nhaPhanPhois = ListAllDuLieu("NhaPhanPhoi");
        for (int i = 0; i < nhaPhanPhois.size(); i++) {
            NhaPhanPhoi npp = (NhaPhanPhoi) nhaPhanPhois.get(i);
            Object[] rowData = {
                    npp.getMaNhaPhanPhoi(),
                    npp.getTenNhaPhanPhoi(),
                    npp.getDiaChi(),
                    npp.getSDT(),
                    npp.getEmail(),
                    npp.getChuThich()
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
                // Tạo panel nhập liệu (6 trường)
                JPanel inputPanel = new JPanel(new GridLayout(6, 2, 5, 5));

                JLabel lblMaNPP = new JLabel("Mã Nhà Phân Phối:");
                JTextField tfMaNPP = new JTextField();

                JLabel lblTenNPP = new JLabel("Tên Nhà Phân Phối:");
                JTextField tfTenNPP = new JTextField();

                JLabel lblDiaChi = new JLabel("Địa Chỉ:");
                JTextField tfDiaChi = new JTextField();

                JLabel lblSDT = new JLabel("Số Điện Thoại:");
                JTextField tfSDT = new JTextField();

                JLabel lblEmail = new JLabel("Email:");
                JTextField tfEmail = new JTextField();

                JLabel lblChuThich = new JLabel("Chú Thích:");
                JTextField tfChuThich = new JTextField();

                // Thêm các thành phần vào panel
                inputPanel.add(lblMaNPP);
                inputPanel.add(tfMaNPP);
                inputPanel.add(lblTenNPP);
                inputPanel.add(tfTenNPP);
                inputPanel.add(lblDiaChi);
                inputPanel.add(tfDiaChi);
                inputPanel.add(lblSDT);
                inputPanel.add(tfSDT);
                inputPanel.add(lblEmail);
                inputPanel.add(tfEmail);
                inputPanel.add(lblChuThich);
                inputPanel.add(tfChuThich);

                // Hiển thị panel trong 1 dialog xác nhận
                int result = JOptionPane.showConfirmDialog(
                        null,
                        inputPanel,
                        "Thêm Nhà Phân Phối",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

                if (result == JOptionPane.OK_OPTION) {
                    String maNhaPhanPhoi = tfMaNPP.getText().trim();
                    String tenNhaPhanPhoi = tfTenNPP.getText().trim();
                    String diaChi = tfDiaChi.getText().trim();
                    String sdt = tfSDT.getText().trim();
                    String email = tfEmail.getText().trim();
                    String chuThich = tfChuThich.getText().trim();

                    // Kiểm tra dữ liệu
                    if (!maNhaPhanPhoi.isEmpty() && !tenNhaPhanPhoi.isEmpty() &&
                            !diaChi.isEmpty() && !sdt.isEmpty() && !email.isEmpty()) {
                        // Thêm vào model (bảng)
                        Object[] rowData = {maNhaPhanPhoi, tenNhaPhanPhoi, diaChi, sdt, email, chuThich};
                        RecordHandler.addRecord(model, rowData);

                        // Thêm vào DB
                        NhaPhanPhoi npp = new NhaPhanPhoi(
                                Integer.parseInt(maNhaPhanPhoi),
                                tenNhaPhanPhoi,
                                diaChi,
                                sdt,
                                email,
                                chuThich
                        );
                        AddRecord(npp, "NhaPhanPhoi");
                    } else {
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
                    }
                }
            }
        });

        //============================
        // NÚT SỬA
        //============================
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int confirm = JOptionPane.showConfirmDialog(
                            null,
                            "Bạn có chắc chắn muốn sửa nhà phân phối đã chọn?",
                            "Xác nhận",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirm == JOptionPane.YES_OPTION) {
                        // Lấy dữ liệu cũ từ dòng được chọn
                        String oldMaNPP = safeGetValue(selectedRow, 0);
                        String oldTenNPP = safeGetValue(selectedRow, 1);
                        String oldDiaChi = safeGetValue(selectedRow, 2);
                        String oldSDT = safeGetValue(selectedRow, 3);
                        String oldEmail = safeGetValue(selectedRow, 4);
                        String oldChuThich = safeGetValue(selectedRow, 5);

                        // Tạo panel nhập liệu
                        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 5, 5));

                        JLabel lblMaNPP = new JLabel("Mã Nhà Phân Phối:");
                        JTextField tfMaNPP = new JTextField(oldMaNPP);

                        JLabel lblTenNPP = new JLabel("Tên Nhà Phân Phối:");
                        JTextField tfTenNPP = new JTextField(oldTenNPP);

                        JLabel lblDiaChi = new JLabel("Địa Chỉ:");
                        JTextField tfDiaChi = new JTextField(oldDiaChi);

                        JLabel lblSDT = new JLabel("Số Điện Thoại:");
                        JTextField tfSDT = new JTextField(oldSDT);

                        JLabel lblEmail = new JLabel("Email:");
                        JTextField tfEmail = new JTextField(oldEmail);

                        JLabel lblChuThich = new JLabel("Chú Thích:");
                        JTextField tfChuThich = new JTextField(oldChuThich);

                        // Thêm thành phần vào panel
                        inputPanel.add(lblMaNPP);
                        inputPanel.add(tfMaNPP);
                        inputPanel.add(lblTenNPP);
                        inputPanel.add(tfTenNPP);
                        inputPanel.add(lblDiaChi);
                        inputPanel.add(tfDiaChi);
                        inputPanel.add(lblSDT);
                        inputPanel.add(tfSDT);
                        inputPanel.add(lblEmail);
                        inputPanel.add(tfEmail);
                        inputPanel.add(lblChuThich);
                        inputPanel.add(tfChuThich);

                        int result = JOptionPane.showConfirmDialog(
                                null,
                                inputPanel,
                                "Sửa Nhà Phân Phối",
                                JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.PLAIN_MESSAGE
                        );

                        if (result == JOptionPane.OK_OPTION) {
                            String newMaNPP = tfMaNPP.getText().trim();
                            String newTenNPP = tfTenNPP.getText().trim();
                            String newDiaChi = tfDiaChi.getText().trim();
                            String newSDT = tfSDT.getText().trim();
                            String newEmail = tfEmail.getText().trim();
                            String newChuThich = tfChuThich.getText().trim();

                            if (!newMaNPP.isEmpty() && !newTenNPP.isEmpty() &&
                                    !newDiaChi.isEmpty() && !newSDT.isEmpty() && !newEmail.isEmpty()) {
                                // Cập nhật bảng
                                Object[] rowData = {newMaNPP, newTenNPP, newDiaChi, newSDT, newEmail, newChuThich};
                                RecordHandler.updateRecord(model, selectedRow, rowData);

                                // Cập nhật DB
                                NhaPhanPhoi npp = new NhaPhanPhoi(
                                        Integer.parseInt(newMaNPP),
                                        newTenNPP,
                                        newDiaChi,
                                        newSDT,
                                        newEmail,
                                        newChuThich
                                );
                                UpdateRecord(npp, "NhaPhanPhoi", "MaNhaPhanPhoi", Integer.parseInt(newMaNPP));

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

        //============================
        // NÚT XÓA (giữ nguyên logic)
        //============================
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int confirm = JOptionPane.showConfirmDialog(
                            null,
                            "Bạn có chắc chắn muốn xoá nhà phân phối đã chọn?",
                            "Xác nhận",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirm == JOptionPane.YES_OPTION) {
                        String maNhaPhanPhoi = safeGetValue(selectedRow, 0);
                        if (maNhaPhanPhoi != null) {
                            RecordHandler.deleteRecord(model, selectedRow);
                            DeleteRecord("NhaPhanPhoi", "MaNhaPhanPhoi", Integer.parseInt(maNhaPhanPhoi));
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
        return (value != null) ? value.toString() : null;
    }
}