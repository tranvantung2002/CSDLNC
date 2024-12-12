package View;

import Model.SanPham;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.ArrayList;

import static Model.Db4Obj.ListAllDuLieu;

public class BaoCaoTonKho extends javax.swing.JPanel {
    public BaoCaoTonKho() {
        initComponents();
    }

    private void initComponents() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        ArrayList sanPhams = ListAllDuLieu("SanPham");

        for (int i = 0; i < sanPhams.size(); i++) {
            SanPham sanPham = (SanPham) sanPhams.get(i);
            String productName = sanPham.getTenSanPham();
            int stock = sanPham.getTonKho(); // Số lượng tồn kho
            dataset.addValue(stock, "Tồn kho", productName);
        }

// Tạo biểu đồ
        JFreeChart barChart = ChartFactory.createBarChart(
                "Tồn kho sản phẩm",
                "Sản phẩm",
                "Số lượng tồn",
                dataset
        );
        ChartPanel chartPanel = new ChartPanel(barChart);
        add(chartPanel);

    }
}
