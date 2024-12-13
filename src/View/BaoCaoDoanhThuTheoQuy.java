package View;

import Model.HoaDon;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.ArrayList;
import java.util.Calendar;

import static Model.Db4Obj.ListAllDuLieu;

public class BaoCaoDoanhThuTheoQuy extends javax.swing.JPanel {
    public BaoCaoDoanhThuTheoQuy() {
        initComponents();
    }

    private void initComponents() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        float[] quarterlyRevenue = new float[4]; // Lưu doanh thu cho 4 quý

        ArrayList hoaDons = ListAllDuLieu("HoaDon");

        for (int i = 0; i < hoaDons.size(); i++) {
            HoaDon hoaDon = (HoaDon) hoaDons.get(i);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(hoaDon.getNgayLaphoaDon());
            int month = calendar.get(Calendar.MONTH); // Tháng (0-11)
            int year = calendar.get(Calendar.YEAR);

            Calendar current = Calendar.getInstance();
            int currentYear = current.get(Calendar.YEAR);

            if (year == currentYear) {
                int quarter = month / 3; // Xác định quý: 0 (Q1), 1 (Q2), 2 (Q3), 3 (Q4)
                quarterlyRevenue[quarter] += hoaDon.getTongTien();
            }
        }

// Thêm dữ liệu vào dataset
        String[] quarters = {"Quý 1", "Quý 2", "Quý 3", "Quý 4"};
        for (int i = 0; i < quarterlyRevenue.length; i++) {
            dataset.addValue(quarterlyRevenue[i], "Doanh thu", quarters[i]);
        }

// Tạo biểu đồ
        JFreeChart barChart = ChartFactory.createBarChart(
                "Doanh thu theo quý",
                "Quý",
                "Doanh thu (VND)",
                dataset
        );
        ChartPanel chartPanel = new ChartPanel(barChart);
        add(chartPanel);

    }
}
