package View;

import static Model.Db4Obj.AddRecord;
import static Model.Db4Obj.DeleteRecord;
import static Model.Db4Obj.UpdateRecord;
import static Model.Db4Obj.ListAllDuLieu;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.StringContent;

import com.formdev.flatlaf.FlatClientProperties;

import Model.NhanVien;
import Model.SanPham;
public class QuanLyNhanVien extends javax.swing.JPanel {
	private JTable table;
    private JPanel panel1;
    private JLabel lb, lblID, lblName, lblGioiTinh,lblNgayVaoLam,lblChucVu,lblDiaChi,lblMobile,lblGhiChu;
    private JTextField tfID, tfName, tfGioiTinh,tfNgayVaoLam,tfChucVu,tfDiaChi,tfMobile,tfGhiChu ;
    private JButton btnAdd, btnUpdate, btnDelete;
    private DefaultTableModel model;
    private String pattern = "dd/MM/yyyy";
    private DateFormat df = new SimpleDateFormat(pattern);
    
    public QuanLyNhanVien() {
		// TODO Auto-generated constructor stub
    	initComponents();
        lb.putClientProperty(FlatClientProperties.STYLE, "font:$h1.font");
	}
    
    private void initComponents() {
        // Tạo JLabel cho tiêu đề
        lb = new JLabel();
        lb.setHorizontalAlignment(SwingConstants.CENTER);
        lb.setText("QUẢN LÝ NHÂN VIÊN");

        // Tạo các JLabel cho ID, Name, Age
        lblID = new JLabel("Mã nhân viên:");
        lblName = new JLabel("Họ và Tên:");
        lblGioiTinh = new JLabel("Giới Tính:");
        lblNgayVaoLam = new JLabel("Ngày vào làm:");
        lblChucVu = new JLabel("Chức vụ:");
        lblDiaChi = new JLabel("Địa chỉ:");
        lblMobile = new JLabel("Số điện thoại:");
        lblGhiChu = new JLabel("Ghi chú:");
        

        // Tạo các JTextField cho ID, Name, Age
        tfID = new JTextField(10);
        tfName = new JTextField(20);
        tfGioiTinh = new JTextField(10);
        tfNgayVaoLam = new JTextField(10);
        tfChucVu = new JTextField(10);
        tfDiaChi = new JTextField(10);
        tfMobile = new JTextField(10);
        tfGhiChu = new JTextField(10);
        
        
        // Tạo dữ liệu và cột cho JTable
        
        String[] columns = { "Mã nhân viên", "Họ và Tên", "Giới Tính", "Ngày vào làm", "Chức vụ", "Địa chỉ","Số điện thoại", "Ghi chú"};
        ArrayList nhanViens = ListAllDuLieu("NhanVien");
        Object[][] data = new Object[nhanViens.size()][columns.length];
        
        for (int i = 0; i < nhanViens.size(); i++) {
        	NhanVien sp = (NhanVien) nhanViens.get(i);
         // Cập nhật thông tin của từng sản phẩm vào mảng 2 chiều data
            data[i][0] = sp.getMaNhanVien();
            data[i][1] = sp.getHovaTen();
            data[i][2] = sp.getGioiTinh();
            data[i][3] = df.format(sp.getNgayVaoLam());
            data[i][4] = sp.getChucVu();
            data[i][5] = sp.getDiaChi();
            data[i][6] = sp.getSoDT();
            data[i][7] = sp.getGhiChu();
        }
        
     // Tạo DefaultTableModel và JTable
        model = new DefaultTableModel(data, columns);
        table = new JTable(model);
        
     // Bọc JTable trong JScrollPane để có thể cuộn
        JScrollPane scrollPane = new JScrollPane(table);

     // Tạo các nút chức năng
        btnAdd = new JButton("Thêm");
        btnUpdate = new JButton("Sửa");
        btnDelete = new JButton("Xoá");
        
     // Thiết lập hành động cho các nút
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNhanVien();
            }
        });
        
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateNhanVien();
            }
        });
        
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteNhanVien();
            }
        });
        
     // Lắng nghe sự kiện chọn hàng trong bảng
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        tfID.setText(model.getValueAt(selectedRow, 0).toString());
                        tfName.setText(model.getValueAt(selectedRow, 1).toString());
                        tfGioiTinh.setText(model.getValueAt(selectedRow, 2).toString());
                        tfNgayVaoLam.setText(model.getValueAt(selectedRow, 3).toString());
                        tfChucVu.setText(model.getValueAt(selectedRow, 4).toString());
                        tfDiaChi.setText(model.getValueAt(selectedRow, 5).toString());
                        tfMobile.setText(model.getValueAt(selectedRow, 6).toString());
                        tfGhiChu.setText(model.getValueAt(selectedRow, 7).toString());
                    }
                }
            }
        });

        
     // === Tổ chức bố cục ===
        JPanel formPanel = new JPanel();
        GroupLayout formLayout = new GroupLayout(formPanel);
        formPanel.setLayout(formLayout);

        formLayout.setHorizontalGroup(
                formLayout.createSequentialGroup()
                        .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(formLayout.createSequentialGroup()
                                        .addComponent(lblID)
                                        .addComponent(tfID, 150, 150, 150)
                                        .addGap(20)
                                        .addComponent(lblName)
                                        .addGap(10)
                                        .addComponent(tfName, 150, 150, 150)
                                        .addGap(10)
                                        .addComponent(lblGioiTinh)
                                        .addGap(10)
                                        .addComponent(tfGioiTinh, 150, 150, 150)
                                        .addGap(20)
                                )
                                .addGroup(formLayout.createSequentialGroup()
                                        .addComponent(lblNgayVaoLam)
                                        .addGap(10)
                                        .addComponent(tfNgayVaoLam, 150, 150, 150)
                                        .addGap(20)
                                        .addComponent(lblChucVu)
                                        .addGap(10)
                                        .addComponent(tfChucVu, 150, 150, 150)
                                        .addGap(20)
                                        .addComponent(lblDiaChi)
                                        .addGap(10)
                                        .addComponent(tfDiaChi, 150, 150, 150)
                                )
                                .addGroup(formLayout.createSequentialGroup()
                                        .addComponent(lblMobile)
                                        .addGap(10)
                                        .addComponent(tfMobile, 150, 150, 150)
                                        .addGap(20)
                                        .addComponent(lblGhiChu)
                                        .addGap(10)
                                        .addComponent(tfGhiChu, 150, 150, 150)
                                ).addGroup(formLayout.createSequentialGroup()
                                        .addComponent(btnAdd)
                                        .addGap(20)
                                        .addComponent(btnUpdate)
                                        .addGap(20)
                                        .addComponent(btnDelete))
                                
        ));

        formLayout.setVerticalGroup(
                formLayout.createSequentialGroup()
                        .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblID)
                                .addComponent(tfID)
                                .addComponent(lblName)
                                .addComponent(tfName)
                                .addComponent(lblGioiTinh)
                                .addComponent(tfGioiTinh)
                        )
                        .addGap(10)
                        .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblNgayVaoLam)
                                .addComponent(tfNgayVaoLam)
                                .addComponent(lblChucVu)
                                .addComponent(tfChucVu)
                                .addComponent(lblDiaChi)
                                .addComponent(tfDiaChi)
                        )
                        .addGap(10)
                        .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblMobile)
                                .addComponent(tfMobile)
                                .addComponent(lblGhiChu)
                                .addComponent(tfGhiChu)
                        )
                        .addGap(10)
                        .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(btnAdd)
                                .addComponent(btnUpdate)
                                .addComponent(btnDelete))
          
        );
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER); // JTable ở giữa
        add(formPanel, BorderLayout.SOUTH); // Form ở dưới

    }      
    
    private void addNhanVien() {
        String id = tfID.getText();
        String name = tfName.getText();
        String sex = tfGioiTinh.getText();
        String date = tfNgayVaoLam.getText();
        String title = tfChucVu.getText();
        String address = tfDiaChi.getText();
        int mobile = Integer.parseInt(tfMobile.getText().trim());
        String mobileNumber = tfMobile.getText().trim();
        String note = tfGhiChu.getText();
        
        java.util.Date dateOfWork = null;
        
        try {
        	dateOfWork = (java.util.Date) df.parse(date);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!id.isEmpty() && !name.isEmpty() && !sex.isEmpty() && !date.isEmpty() && !title.isEmpty()) {
            model.addRow(new Object[]{id, name, sex, date, title, address, mobile, note});
            
            NhanVien nhanVien = new NhanVien(Integer.parseInt(id), name, sex, dateOfWork, title, address, mobileNumber, note);
            AddRecord(nhanVien, "NhanVien");
            
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
        }
    }
    
    private void updateNhanVien() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String id = tfID.getText();
            String name = tfName.getText();
            String sex = tfGioiTinh.getText();
            String date = tfNgayVaoLam.getText();
            String title = tfChucVu.getText();
            String address = tfDiaChi.getText();
            int mobile = Integer.parseInt(tfMobile.getText().trim());
            String mobileNumber = tfMobile.getText().trim();
            String note = tfGhiChu.getText();
            
            DateFormat df = new SimpleDateFormat(pattern); 
            java.util.Date dateOfWork = null;
            
            try {
            	dateOfWork = (java.util.Date) df.parse(date);
                
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (!id.isEmpty() && !name.isEmpty() && !sex.isEmpty()) {
            	
            	int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "Bạn có chắc chắn muốn sửa nhân viên đã chọn?",
                        "Xác nhận",
                        JOptionPane.YES_NO_OPTION
                );
            	
            	if (confirm == JOptionPane.YES_OPTION) {
                    // Xử lý xóa sản phẩm tại đây
            		model.removeRow(selectedRow);
            		model.setValueAt(id, selectedRow, 0);
                    model.setValueAt(name, selectedRow, 1);
                    model.setValueAt(sex, selectedRow, 2);
                    model.setValueAt(df.format(dateOfWork), selectedRow, 3);
                    model.setValueAt(title, selectedRow, 4);
                    model.setValueAt(address, selectedRow, 5);
                    model.setValueAt(mobile, selectedRow, 6);
                    model.setValueAt(note, selectedRow, 7);
                    
                    NhanVien nhanVien = new NhanVien(Integer.parseInt(id), name, sex, dateOfWork, title, address, mobileNumber , note);

                    System.out.println(nhanVien);
                    UpdateRecord(nhanVien, "NhanVien", "MaNhanVien", Integer.parseInt(id));
                    
                    clearFields();

            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần sửa!");
        }
    }
   }
    
    private void deleteNhanVien() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
        	
        	int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Bạn có chắc chắn muốn xoá khách hàng đã chọn?",
                    "Xác nhận",
                    JOptionPane.YES_NO_OPTION
            );
        	
        	if (confirm == JOptionPane.YES_OPTION) {
                // Xử lý xóa sản phẩm tại đây
        	
            model.removeRow(selectedRow);
            String id = tfID.getText();

            DeleteRecord("NhanVien", "MaNhanVien", Integer.parseInt(id));
        }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần xoá!");
        }
    }
    
    private void clearFields() {
        tfID.setText("");
        tfName.setText("");
        tfGioiTinh.setText("");
        tfNgayVaoLam.setText("");
        tfChucVu.setText("");
        tfDiaChi.setText("");
        tfMobile.setText("");
        tfGhiChu.setText("");
    }

 }
