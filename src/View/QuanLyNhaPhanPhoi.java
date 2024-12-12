package View;

import Model.NhaPhanPhoi;
import utility.RecordHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static Model.Db4Obj.*;

public class QuanLyNhaPhanPhoi extends BaseManagementPanel {
    public QuanLyNhaPhanPhoi() {
        // Gọi super() với tiêu đề và các cột
        super("Quản Lý Nhà Phân Phối", new String[]{"Mã Nhà Phân Phối", "Tên Nhà Phân Phối", "Địa Chỉ", "Số Điện Thoại", "Email", "Chú Thích"});

        // Tải dữ liệu vào bảng
        loadData(6);
    }

    private void loadData(int columns) {
        ArrayList nhaPhanPhois = ListAllDuLieu("NhaPhanPhoi");
        Object[][] data = new Object[nhaPhanPhois.size()][columns];

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
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hiển thị hộp thoại nhập thông tin
                String maNhaPhanPhoi = JOptionPane.showInputDialog("Nhập Mã Nhà Phân Phối:");
                String tenNhaPhanPhoi = JOptionPane.showInputDialog("Nhập Tên Nhà Phân Phối:");
                String diaChi = JOptionPane.showInputDialog("Nhập Địa Chỉ:");
                String sdt = JOptionPane.showInputDialog("Nhập Số Điện Thoại:");
                String email = JOptionPane.showInputDialog("Nhập Email:");
                String chuThich = JOptionPane.showInputDialog("Nhập Chú Thích:");

                if (maNhaPhanPhoi != null && tenNhaPhanPhoi != null && diaChi != null && sdt != null && email != null) {
                    Object[] rowData = {maNhaPhanPhoi, tenNhaPhanPhoi, diaChi, sdt, email, chuThich};
                    RecordHandler.addRecord(model, rowData);

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
        });

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
                        String maNhaPhanPhoi = safeGetValue(selectedRow, 0);
                        String tenNhaPhanPhoi = safeGetValue(selectedRow, 1);
                        String diaChi = safeGetValue(selectedRow, 2);
                        String sdt = safeGetValue(selectedRow, 3);
                        String email = safeGetValue(selectedRow, 4);
                        String chuThich = safeGetValue(selectedRow, 5);

                        if (maNhaPhanPhoi != null && tenNhaPhanPhoi != null && diaChi != null && sdt != null && email != null) {
                            Object[] rowData = {maNhaPhanPhoi, tenNhaPhanPhoi, diaChi, sdt, email, chuThich};
                            RecordHandler.updateRecord(model, selectedRow, rowData);

                            NhaPhanPhoi npp = new NhaPhanPhoi(
                                    Integer.parseInt(maNhaPhanPhoi),
                                    tenNhaPhanPhoi,
                                    diaChi,
                                    sdt,
                                    email,
                                    chuThich
                            );
                            UpdateRecord(npp, "NhaPhanPhoi", "MaNhaPhanPhoi", Integer.parseInt(maNhaPhanPhoi));

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
        return value != null ? value.toString() : null;
    }
}