package View;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.ArrayList;

import static Model.Db4Obj.ListAllDuLieu;

public class BaoCaoNhapHang extends javax.swing.JPanel {
    public BaoCaoNhapHang() {
        initComponents();
    }

    private void initComponents() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        ArrayList chiTietPhieuNhaps = ListAllDuLieu("ChiTietPhieuNhap");

        for (int i = 0; i < chiTietPhieuNhaps.size(); i++) {
//            ChiTietPhieuNhap phieuNhap = (ChiTietPhieuNhap) chiTietPhieuNhaps.get(i);
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(phieuNhap.getNgayNhap());
//            int month = calendar.get(Calendar.MONTH) + 1; // Tháng
//            int year = calendar.get(Calendar.YEAR);
//
//            Calendar current = Calendar.getInstance();
//            int currentYear = current.get(Calendar.YEAR);
//
//            if (year == currentYear) {
//                String monthLabel = "Tháng " + month;
//                float cost = phieuNhap.getTongTien(); // Chi phí nhập hàng
//                dataset.addValue(cost, "Chi phí nhập hàng", monthLabel);
//            }
        }

// Tạo biểu đồ
        JFreeChart barChart = ChartFactory.createBarChart(
                "Chi phí nhập hàng theo tháng",
                "Tháng",
                "Chi phí (VND)",
                dataset
        );
        ChartPanel chartPanel = new ChartPanel(barChart);
        add(chartPanel);
    }
}
