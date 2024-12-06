package View;

import Model.LoaiSanPham;
import utility.RecordHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static Model.Db4Obj.*;

public class QuanLyLoaiSanPham extends BaseManagementPanel {

    public QuanLyLoaiSanPham() {
        // Gọi super() với tiêu đề và các cột
        super("Quản Lý Loại Sản Phẩm", new String[]{"Mã Loại Sản Phẩm", "Tên Loại Sản Phẩm"});

        // Tải dữ liệu vào bảng
        loadData(2);
    }

    private void loadData(int columns) {
        ArrayList loaisanphams = ListAllDuLieu("LoaiSanPham");
        Object[][] data = new Object[loaisanphams.size()][columns];

        for (int i = 0; i < loaisanphams.size(); i++) {
            LoaiSanPham lsp = (LoaiSanPham) loaisanphams.get(i);
            Object[] rowData = {
                    lsp.getMaLoaiSanPham(),
                    lsp.getTenLoaiSanPham()
            };
            RecordHandler.addRecord(model, rowData);
        }
    }

    @Override
    protected void initializeActions() {
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maLoaiSanPham = JOptionPane.showInputDialog("Nhập Mã Loại Sản Phẩm:");
                String tenLoaiSanPham = JOptionPane.showInputDialog("Nhập Tên Loại Sản Phẩm:");

                if (maLoaiSanPham != null && tenLoaiSanPham != null) {
                    Object[] rowData = {maLoaiSanPham, tenLoaiSanPham};
                    RecordHandler.addRecord(model, rowData);

                    LoaiSanPham lsp = new LoaiSanPham(Integer.parseInt(maLoaiSanPham), tenLoaiSanPham);
                    AddRecord(lsp, "LoaiSanPham");
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
                            "Bạn có chắc chắn muốn sửa loại sản phẩm đã chọn?",
                            "Xác nhận",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirm == JOptionPane.YES_OPTION) {
                        String maLoaiSanPham = safeGetValue(selectedRow, 0);
                        String tenLoaiSanPham = safeGetValue(selectedRow, 1);

                        if (maLoaiSanPham != null && tenLoaiSanPham != null) {
                            Object[] rowData = {maLoaiSanPham, tenLoaiSanPham};
                            RecordHandler.updateRecord(model, selectedRow, rowData);

                            LoaiSanPham lsp = new LoaiSanPham(Integer.parseInt(maLoaiSanPham), tenLoaiSanPham);
                            UpdateRecord(lsp, "LoaiSanPham", "MaLoaiSanPham", Integer.parseInt(maLoaiSanPham));

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
                            "Bạn có chắc chắn muốn xoá loại sản phẩm đã chọn?",
                            "Xác nhận",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirm == JOptionPane.YES_OPTION) {
                        String maLoaiSanPham = safeGetValue(selectedRow, 0);
                        if (maLoaiSanPham != null) {
                            RecordHandler.deleteRecord(model, selectedRow);
                            DeleteRecord("LoaiSanPham", "MaLoaiSanPham", Integer.parseInt(maLoaiSanPham));
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