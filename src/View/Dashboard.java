package View;

import Model.HoaDon;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.ArrayList;
import java.util.Calendar;

import static Model.Db4Obj.ListAllDuLieu;

public class Dashboard extends javax.swing.JPanel {
    public Dashboard() {
        initComponents();
    }

    private void initComponents() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        ArrayList hoaDons = ListAllDuLieu("HoaDon");

        for (int i = 0; i < hoaDons.size(); i++) {
            HoaDon hoaDon = (HoaDon) hoaDons.get(i);

            // Lấy tháng và năm từ hóa đơn
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(hoaDon.getNgayLaphoaDon());
            int month = calendar.get(Calendar.MONTH) + 1; // Tháng (0-11 nên cần +1)
            int year = calendar.get(Calendar.YEAR);       // Năm của hóa đơn

            // Lấy năm hiện tại
            Calendar current = Calendar.getInstance();
            int currentYear = current.get(Calendar.YEAR);

            // Chỉ thêm doanh thu nếu hóa đơn thuộc năm nay
            if (year == currentYear) {
                String monthLabel = "Tháng " + month; // Nhãn cho tháng
                float revenue = hoaDon.getTongTien(); // Tổng tiền từ hóa đơn
                dataset.addValue(revenue, "Doanh thu", monthLabel); // Thêm giá trị vào dataset
            }
        }


        // Tạo biểu đồ
        JFreeChart barChart = ChartFactory.createBarChart(
                "Doanh thu theo tháng",
                "Tháng",
                "Doanh thu (VND)",
                dataset
        );

        // Đóng gói biểu đồ vào ChartPanel
        ChartPanel chartPanel = new ChartPanel(barChart);

        // Hiển thị biểu đồ
        add(chartPanel);
        setSize(800, 600);
        setVisible(true);
    }
}
