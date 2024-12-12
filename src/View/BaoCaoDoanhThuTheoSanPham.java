package View;

import Model.ChiTietHoaDon;
import Model.SanPham;
import com.db4o.ObjectSet;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.ArrayList;

import static Model.Db4Obj.ListAllDuLieu;
import static Model.Db4Obj.TruyVanSODA;

public class BaoCaoDoanhThuTheoSanPham extends javax.swing.JPanel {
    public BaoCaoDoanhThuTheoSanPham() {
        initComponents();
    }

    private void initComponents() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        ArrayList chiTietHoaDons = ListAllDuLieu("ChiTietHoaDon");

        for (int i = 0; i < chiTietHoaDons.size(); i++) {
            ChiTietHoaDon chiTietHoaDon = (ChiTietHoaDon) chiTietHoaDons.get(i);
            ObjectSet<SanPham> sanPhams = TruyVanSODA("MaSanPham", String.valueOf(chiTietHoaDon.getMaSanPham()), "SanPham", "int", "=");
            String tenSanPham = sanPhams.get(0).getTenSanPham();
            String productName = String.valueOf(tenSanPham); // Tên sản phẩm (dùng mã sản phẩm làm tên)
            float revenue = chiTietHoaDon.getTongTien(); // Doanh thu từ sản phẩm

            // Kiểm tra và xử lý giá trị trong dataset
            try {
                Number currentValue = dataset.getValue("Doanh thu", productName);
                dataset.setValue(currentValue.floatValue() + revenue, "Doanh thu", productName); // Cộng dồn doanh thu
            } catch (org.jfree.data.UnknownKeyException e) {
                // Nếu khóa chưa tồn tại, thêm mới giá trị
                dataset.addValue(revenue, "Doanh thu", productName);
            }
        }

// Tạo biểu đồ
        JFreeChart barChart = ChartFactory.createBarChart(
                "Doanh thu theo sản phẩm",
                "Sản phẩm",
                "Doanh thu (VND)",
                dataset
        );
        ChartPanel chartPanel = new ChartPanel(barChart);
        add(chartPanel);

    }
}
