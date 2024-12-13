package View;

import Model.KhachHang;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import java.util.ArrayList;

import static Model.Db4Obj.ListAllDuLieu;

public class BaoCaoKhachHangTheoLoai extends javax.swing.JPanel {
    public BaoCaoKhachHangTheoLoai() {
        initComponents();
    }

    private void initComponents() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        ArrayList khachHangs = ListAllDuLieu("KhachHang");

        for (int i = 0; i < khachHangs.size(); i++) {
            KhachHang khachHang = (KhachHang) khachHangs.get(i);
            String customerType = khachHang.getLoaiKhachHang();

            // Kiểm tra và thêm vào dataset
            if (dataset.getKeys().contains(customerType)) {
                Number currentValue = dataset.getValue(customerType);
                dataset.setValue(customerType, currentValue.intValue() + 1); // Tăng số lượng
            } else {
                dataset.setValue(customerType, 1); // Nếu chưa có, đặt giá trị là 1
            }
        }

// Tạo biểu đồ tròn
        JFreeChart pieChart = ChartFactory.createPieChart(
                "Phân loại khách hàng",
                dataset,
                true, // legend
                true, // tooltips
                false // URLs
        );

// Đóng gói biểu đồ vào ChartPanel
        ChartPanel chartPanel = new ChartPanel(pieChart);
        add(chartPanel);
    }
}
