package View;

import Model.SanPham;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.ArrayList;

import static Model.Db4Obj.ListAllDuLieu;

public class BaoCaoLoiNhuan extends javax.swing.JPanel {
    public BaoCaoLoiNhuan() {
        initComponents();
    }

    private void initComponents() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        ArrayList sanPhams = ListAllDuLieu("SanPham");

        for (int i = 0; i < sanPhams.size(); i++) {
            SanPham sanPham = (SanPham) sanPhams.get(i);
            String productName = sanPham.getTenSanPham(); // Tên sản phẩm
            float profit = sanPham.getGiaBan() - sanPham.getGiaNhap(); // Lợi nhuận mỗi sản phẩm
            dataset.addValue(profit, "Lợi nhuận", productName);
        }

// Tạo biểu đồ
        JFreeChart barChart = ChartFactory.createBarChart(
                "Lợi nhuận theo sản phẩm",
                "Sản phẩm",
                "Lợi nhuận (VND)",
                dataset
        );
        ChartPanel chartPanel = new ChartPanel(barChart);
        add(chartPanel);

    }
}
