package View;

import Model.Users;
import utility.RecordHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static Model.Db4Obj.*;

public class QuanLyNguoiDung extends BaseManagementPanel {

    public QuanLyNguoiDung() {
        // Gọi super() với tiêu đề và các cột
        super("Quản Lý Người Dùng", new String[]{"ID", "Mã Nhân Viên", "Tên Đăng Nhập", "Mật Khẩu", "Quyền", "Chú Thích"});

        // Tải dữ liệu vào bảng
        loadData(6);
    }

    private void loadData(int columns) {
        ArrayList users = ListAllDuLieu("Users");
        Object[][] data = new Object[users.size()][columns];

        for (int i = 0; i < users.size(); i++) {
            Users user = (Users) users.get(i);
            Object[] rowData = {
                    user.getID(),
                    user.getMaNhanVien(),
                    user.getTenDangNhap(),
                    user.getPassword(),
                    user.getQuyen(),
                    user.getChuThich()
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
                String id = JOptionPane.showInputDialog("Nhập ID:");
                String maNhanVien = JOptionPane.showInputDialog("Nhập Mã Nhân Viên:");
                String tenDangNhap = JOptionPane.showInputDialog("Nhập Tên Đăng Nhập:");
                String password = JOptionPane.showInputDialog("Nhập Mật Khẩu:");
                String quyen = JOptionPane.showInputDialog("Nhập Quyền:");
                String chuThich = JOptionPane.showInputDialog("Nhập Chú Thích:");

                if (id != null && maNhanVien != null && tenDangNhap != null && password != null && quyen != null) {
                    Object[] rowData = {id, maNhanVien, tenDangNhap, password, quyen, chuThich};
                    RecordHandler.addRecord(model, rowData);

                    Users user = new Users(
                            Integer.parseInt(id),
                            Integer.parseInt(maNhanVien),
                            tenDangNhap,
                            password,
                            Integer.parseInt(quyen),
                            chuThich
                    );
                    AddRecord(user, "Users");
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
                            "Bạn có chắc chắn muốn sửa người dùng đã chọn?",
                            "Xác nhận",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirm == JOptionPane.YES_OPTION) {
                        String id = safeGetValue(selectedRow, 0);
                        String maNhanVien = safeGetValue(selectedRow, 1);
                        String tenDangNhap = safeGetValue(selectedRow, 2);
                        String password = safeGetValue(selectedRow, 3);
                        String quyen = safeGetValue(selectedRow, 4);
                        String chuThich = safeGetValue(selectedRow, 5);

                        if (id != null && maNhanVien != null && tenDangNhap != null && password != null && quyen != null) {
                            Object[] rowData = {id, maNhanVien, tenDangNhap, password, quyen, chuThich};
                            RecordHandler.updateRecord(model, selectedRow, rowData);

                            Users user = new Users(
                                    Integer.parseInt(id),
                                    Integer.parseInt(maNhanVien),
                                    tenDangNhap,
                                    password,
                                    Integer.parseInt(quyen),
                                    chuThich
                            );
                            UpdateRecord(user, "Users", "ID", Integer.parseInt(id));

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
                            "Bạn có chắc chắn muốn xoá người dùng đã chọn?",
                            "Xác nhận",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirm == JOptionPane.YES_OPTION) {
                        String id = safeGetValue(selectedRow, 0);
                        if (id != null) {
                            RecordHandler.deleteRecord(model, selectedRow);
                            DeleteRecord("Users", "ID", Integer.parseInt(id));
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
