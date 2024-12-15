package Model;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.query.Query;

import java.io.File;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class
Db4Obj {
    public static final String DBNAME = "QuanLyBanHang";
    private static final int OBJECT_GRAPH_DEPTH = 3;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static ObjectContainer db = null;
    private static final Map<String, Class<?>> typeMapping = new HashMap<>();

    static {
        // Đăng ký tất cả các kiểu dữ liệu
        typeMapping.put("ChiTietHoaDon", ChiTietHoaDon.class);
        typeMapping.put("ChiTietPhieuNhap", ChiTietPhieuNhap.class);
        typeMapping.put("ChucVu", ChucVu.class);
        typeMapping.put("HangSanXuat", HangSanXuat.class);
        typeMapping.put("HoaDon", HoaDon.class);
        typeMapping.put("KhachHang", KhachHang.class);
        typeMapping.put("LoaiKhachHang", LoaiKhachHang.class);
        typeMapping.put("LoaiSanPham", LoaiSanPham.class);
        typeMapping.put("NhanVien", NhanVien.class);
        typeMapping.put("NhaPhanPhoi", NhaPhanPhoi.class);
        typeMapping.put("PhieuNhap", PhieuNhap.class);
        typeMapping.put("SanPham", SanPham.class);
        typeMapping.put("Users", Users.class);
    }


    // Hàm khởi tạo Database
    public static void KhoiTaoDB() {
        try {
            File file = new File(DBNAME);
            //if (file.exists()) {
    		//	file.delete();
    		//}
            
            EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
            config.common().updateDepth(OBJECT_GRAPH_DEPTH);
            config.common().activationDepth(OBJECT_GRAPH_DEPTH);

            Boolean check = !file.exists();
            
            db = Db4oEmbedded.openFile(config, DBNAME);
            if (check) {
                KhoiTaoDuLieu();
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print the exception stack trace for debugging
        }
    }

    public static ObjectSet TruyVanSODA(String tenTruong, String giaTri, String type, String typeValue, String operator) {
        // Khởi tạo Query
        Query query = db.query();

        // Ép kiểu giá trị theo `typeValue`
        Object valueValidate;
        try {
            if (typeValue.equals("int")) {
                valueValidate = Integer.parseInt(giaTri);
            } else if (typeValue.equals("double")) {
                valueValidate = Double.parseDouble(giaTri);
            } else if (typeValue.equals("datetime")) {
                valueValidate = dateFormat.parse(giaTri);
            } else {
                valueValidate = giaTri;
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException("Định dạng datetime không hợp lệ. Vui lòng sử dụng: yyyy-MM-dd HH:mm:ss");
        }

        // Tìm lớp tương ứng từ `typeMapping`
        Class<?> clazz = typeMapping.get(type);
        if (clazz == null) {
            throw new IllegalArgumentException("Loại không hợp lệ: " + type);
        }

        // Ánh xạ lớp vào truy vấn
        query.constrain(clazz);

        // Thêm điều kiện lọc
        if ("=".equals(operator)) {
            query.descend(tenTruong).constrain(valueValidate);
        } else if ("!=".equals(operator)) {
            query.descend(tenTruong).constrain(valueValidate).not();
        } else if (">".equals(operator)) {
            query.descend(tenTruong).constrain(valueValidate).greater();
        } else if ("<".equals(operator)) {
            query.descend(tenTruong).constrain(valueValidate).smaller();
        } else {
            throw new IllegalArgumentException("Toán tử không hợp lệ: " + operator);
        }

        // Thực thi truy vấn
        return query.execute();
    }

    public static void KhoiTaoDuLieu() {
        try {

            ChiTietHoaDon chiTietHoaDon1 = new ChiTietHoaDon(1, 1001, 101, 5, 1500.0f, "Khuyến mãi 10%");
            ChiTietHoaDon chiTietHoaDon2 = new ChiTietHoaDon(2, 1002, 102, 3, 750.0f, "Mua 2 tặng 1");
            ChiTietHoaDon chiTietHoaDon3 = new ChiTietHoaDon(3, 1003, 103, 7, 2100.0f, "Hóa đơn lớn");
            ChiTietHoaDon chiTietHoaDon4 = new ChiTietHoaDon(4, 1004, 104, 1, 300.0f, "Khách VIP");
            ChiTietHoaDon chiTietHoaDon5 = new ChiTietHoaDon(5, 1005, 105, 10, 5000.0f, "Đơn hàng đặc biệt");


            ChiTietPhieuNhap chiTietPhieuNhap1 = new ChiTietPhieuNhap(1, 2001, 601, 50, 5000.0f, "Nhập hàng đợt 1");
            ChiTietPhieuNhap chiTietPhieuNhap2 = new ChiTietPhieuNhap(2, 2002, 602, 30, 3000.0f, "Nhập bổ sung");
            ChiTietPhieuNhap chiTietPhieuNhap3 = new ChiTietPhieuNhap(3, 2003, 603, 20, 2000.0f, "Hàng khuyến mãi");
            ChiTietPhieuNhap chiTietPhieuNhap4 = new ChiTietPhieuNhap(4, 2004, 604, 100, 10000.0f, "Nhập tồn kho");
            ChiTietPhieuNhap chiTietPhieuNhap5 = new ChiTietPhieuNhap(5, 2005, 605, 10, 1000.0f, "Hàng mẫu thử");

            ChucVu chucVu1 = new ChucVu(1, "Giám đốc", "Quản lý toàn bộ công ty");
            ChucVu chucVu2 = new ChucVu(2, "Trưởng phòng", "Quản lý các phòng ban");
            ChucVu chucVu3 = new ChucVu(3, "Nhân viên", "Thực hiện các công việc chuyên môn");
            ChucVu chucVu4 = new ChucVu(4, "Thực tập sinh", "Hỗ trợ các công việc đơn giản");
            ChucVu chucVu5 = new ChucVu(5, "Quản lý kho", "Phụ trách kho hàng và hàng tồn kho");

            HangSanXuat hangSanXuat1 = new HangSanXuat(1, "Apple");
            HangSanXuat hangSanXuat2 = new HangSanXuat(2, "Samsung");
            HangSanXuat hangSanXuat3 = new HangSanXuat(3, "Sony");
            HangSanXuat hangSanXuat4 = new HangSanXuat(4, "Xiaomi");
            HangSanXuat hangSanXuat5 = new HangSanXuat(5, "Dell");

            // Tạo đối tượng Calendar để thay đổi ngày lập hóa đơn
            Calendar cal = Calendar.getInstance();

            List<HoaDon> hoaDons = new ArrayList<>();
// Tháng 1
            cal.set(2024, Calendar.JANUARY, 15);
            hoaDons.add(new HoaDon(1001, 201, 301, cal.getTime(), 1500.0f, "Mua hàng đợt 1"));

// Tháng 2
            cal.set(2024, Calendar.FEBRUARY, 10);
            hoaDons.add(new HoaDon(1002, 202, 302, cal.getTime(), 2500.0f, "Khuyến mãi dịp lễ"));

// Tháng 3
            cal.set(2024, Calendar.MARCH, 20);
            hoaDons.add(new HoaDon(1003, 203, 303, cal.getTime(), 3500.0f, "Đơn hàng lớn"));

// Tháng 4
            cal.set(2024, Calendar.APRIL, 25);
            hoaDons.add(new HoaDon(1004, 204, 304, cal.getTime(), 4500.0f, "Khách hàng thân thiết"));

// Tháng 5
            cal.set(2024, Calendar.MAY, 5);
            hoaDons.add(new HoaDon(1005, 205, 305, cal.getTime(), 5500.0f, "Thanh toán trực tiếp"));

// Tháng 6
            cal.set(2024, Calendar.JUNE, 12);
            hoaDons.add(new HoaDon(1006, 206, 306, cal.getTime(), 2200.0f, "Khuyến mãi mùa hè"));

// Tháng 7
            cal.set(2024, Calendar.JULY, 18);
            hoaDons.add(new HoaDon(1007, 207, 307, cal.getTime(), 3300.0f, "Giảm giá giữa năm"));

// Tháng 8
            cal.set(2024, Calendar.AUGUST, 30);

            hoaDons.add(new HoaDon(1008, 208, 308, cal.getTime(), 4100.0f, "Đơn hàng VIP"));


            KhachHang khachHang1 = new KhachHang(001, "Nguyễn Văn A" , new Date(1990 - 1900, 5, 15), "Nam", "123 Đường ABC, Hà Nội", "0123456789", "Thường", "Khách hàng mới");
            KhachHang khachHang2 = new KhachHang(002, "Trần Thị B", new Date(1985 - 1900, 10, 20), "Nữ", "456 Đường DEF, TP.HCM", "0987654321", "VIP", "Thành viên lâu năm");
            KhachHang khachHang3 = new KhachHang(003, "Lê Văn C", new Date(1995 - 1900, 2, 28), "Nam", "789 Đường GHI, Đà Nẵng", "0345678912", "Tiềm năng", "Quan tâm chương trình khuyến mãi");
            KhachHang khachHang4 = new KhachHang(004, "Phạm Thị D", new Date(2000 - 1900, 7, 5), "Nữ", "101 Đường JKL, Cần Thơ", "0765432198", "Thường", "Khách hàng trung thành");
            KhachHang khachHang5 = new KhachHang(005, "Hoàng Văn E", new Date(1998 - 1900, 11, 30), "Nam", "202 Đường MNO, Hải Phòng", "0567891234", "VIP", "Khách hàng ưu tiên");

            LoaiKhachHang loaiKhachHang1 = new LoaiKhachHang("LKH01", "Thường");
            LoaiKhachHang loaiKhachHang2 = new LoaiKhachHang("LKH02", "VIP");
            LoaiKhachHang loaiKhachHang3 = new LoaiKhachHang("LKH03", "Tiềm năng");
            LoaiKhachHang loaiKhachHang4 = new LoaiKhachHang("LKH04", "Khách hàng mới");
            LoaiKhachHang loaiKhachHang5 = new LoaiKhachHang("LKH05", "Khách hàng lâu năm");

            LoaiSanPham loaiSanPham1 = new LoaiSanPham(1, "Điện thoại");
            LoaiSanPham loaiSanPham2 = new LoaiSanPham(2, "Laptop");
            LoaiSanPham loaiSanPham3 = new LoaiSanPham(3, "Máy tính bảng");
            LoaiSanPham loaiSanPham4 = new LoaiSanPham(4, "Tivi");
            LoaiSanPham loaiSanPham5 = new LoaiSanPham(5, "Phụ kiện");

            NhanVien nhanVien1 = new NhanVien(1, "Nguyễn Quang Thắng", "Nam", new Date(2020 - 1900, 5, 1), "Trưởng phòng IT", "123 Đường A, Hà Nội", "0123456789", "Hợp đồng vô thời hạn");
            NhanVien nhanVien2 = new NhanVien(2, "Trần Tuấn Hữu", "Nam", new Date(2019 - 1900, 8, 15), "Chuyên viên CNTT", "456 Đường B, TP.HCM", "0987654321", "Hợp đồng vô thời hạn");
            NhanVien nhanVien3 = new NhanVien(3, "Trần Ngọc Bích", "Nữ", new Date(2021 - 1900, 3, 10), "Nhân viên bán hàng" , "789 Đường C, Đà Nẵng", "0345678912", "Hợp đồng dịch vụ");
            NhanVien nhanVien4 = new NhanVien(4, "Nguyễn Hoàng Nam", "Nam", new Date(2018 - 1900, 9, 12), "Phó phòng" , "101 Đường D, Cần Thơ", "0765432198", "Hợp đồng vô thời hạn" );
            NhanVien nhanVien5 = new NhanVien(5, "Đỗ Đức Độ", "Nam", new Date(2022 - 1900, 7, 20), "Nhân viên kho" , "202 Đường E, Hải Phòng", "0567891234", "Hợp đồng 1 năm" );

            NhaPhanPhoi nhaPhanPhoi1 = new NhaPhanPhoi(1, "Công ty TNHH XYZ", "123 Đường A, Hà Nội", "0123456789", "xyz@gmail.com", "Nha phân phối chính thức");
            NhaPhanPhoi nhaPhanPhoi2 = new NhaPhanPhoi(2, "Công ty ABC", "456 Đường B, TP.HCM", "0987654321", "abc@outlook.com", "Đối tác lâu dài");
            NhaPhanPhoi nhaPhanPhoi3 = new NhaPhanPhoi(3, "Nhà phân phối 123", "789 Đường C, Đà Nẵng", "0345678912", "info@123.com", "Hợp tác mở rộng");
            NhaPhanPhoi nhaPhanPhoi4 = new NhaPhanPhoi(4, "Thương hiệu DEF", "101 Đường D, Cần Thơ", "0765432198", "def@company.com", "Chuyên cung cấp sản phẩm công nghệ");
            NhaPhanPhoi nhaPhanPhoi5 = new NhaPhanPhoi(5, "NPP GHI", "202 Đường E, Hải Phòng", "0567891234", "ghi@service.com", "Hợp tác chiến lược");

            PhieuNhap phieuNhap1 = new PhieuNhap(1, 301, 1, 5000.0f, new Date(2024 - 1900, 11, 1), "Nhập hàng từ NPP XYZ");
            PhieuNhap phieuNhap2 = new PhieuNhap(2, 302, 2, 3000.0f, new Date(2024 - 1900, 11, 5), "Nhập hàng bổ sung từ NPP ABC");
            PhieuNhap phieuNhap3 = new PhieuNhap(3, 303, 3, 4500.0f, new Date(2024 - 1900, 10, 20), "Nhập hàng tháng từ NPP 123");
            PhieuNhap phieuNhap4 = new PhieuNhap(4, 304, 4, 6000.0f, new Date(2024 - 1900, 9, 15), "Nhập hàng tồn kho từ NPP DEF");
            PhieuNhap phieuNhap5 = new PhieuNhap(5, 305, 5, 7000.0f, new Date(2024 - 1900, 8, 30), "Nhập hàng khuyến mãi từ NPP GHI");

            SanPham sanPham1 = new SanPham(101, "iPhone 15", 1, 1, 12000.0f, 15000.0f, 100, 1, "imageData1", "Điện thoại cao cấp");
            SanPham sanPham2 = new SanPham(102, "Galaxy S24", 1, 2, 10000.0f, 13000.0f, 150, 1, "imageData2", "Điện thoại Samsung");
            SanPham sanPham3 = new SanPham(103, "MacBook Pro 14", 2, 1, 25000.0f, 28000.0f, 50, 1, "imageData3", "Laptop Apple");
            SanPham sanPham4 = new SanPham(104, "Xiaomi Mi 11", 1, 3, 8000.0f, 9500.0f, 200, 1, "imageData4", "Điện thoại Xiaomi");
            SanPham sanPham5 = new SanPham(105, "Smart TV LG 55 inch", 4, 4, 12000.0f, 15000.0f, 30, 1, "imageData5", "Tivi LG");

            Users user1 = new Users(1, 301, "tung", "123", 1, "Quản trị viên");
            Users user2 = new Users(2, 302, "admin", "123456", 2, "Nhân viên bán hàng");
            Users user3 = new Users(3, 303, "hoang789", "password789", 3, "Nhân viên kho");
            Users user4 = new Users(4, 304, "anhduong101", "password101", 2, "Trưởng phòng IT");
            Users user5 = new Users(5, 305, "linh202", "password202", 3, "Nhân viên chăm sóc khách hàng");

// Lưu ChiTietHoaDon
            db.store(chiTietHoaDon1);
            db.store(chiTietHoaDon2);
            db.store(chiTietHoaDon3);
            db.store(chiTietHoaDon4);
            db.store(chiTietHoaDon5);

// Lưu HoaDon
            hoaDons.forEach(hoaDon -> db.store(hoaDon));

// Lưu KhachHang
            db.store(khachHang1);
            db.store(khachHang2);
            db.store(khachHang3);
            db.store(khachHang4);
            db.store(khachHang5);

// Lưu LoaiKhachHang
            db.store(loaiKhachHang1);
            db.store(loaiKhachHang2);
            db.store(loaiKhachHang3);
            db.store(loaiKhachHang4);
            db.store(loaiKhachHang5);

// Lưu LoaiSanPham
            db.store(loaiSanPham1);
            db.store(loaiSanPham2);
            db.store(loaiSanPham3);
            db.store(loaiSanPham4);
            db.store(loaiSanPham5);

// Lưu NhanVien
            db.store(nhanVien1);
            db.store(nhanVien2);
            db.store(nhanVien3);
            db.store(nhanVien4);
            db.store(nhanVien5);

// Lưu NhaPhanPhoi
            db.store(nhaPhanPhoi1);
            db.store(nhaPhanPhoi2);
            db.store(nhaPhanPhoi3);
            db.store(nhaPhanPhoi4);
            db.store(nhaPhanPhoi5);

// Lưu PhieuNhap
            db.store(phieuNhap1);
            db.store(phieuNhap2);
            db.store(phieuNhap3);
            db.store(phieuNhap4);
            db.store(phieuNhap5);

// Lưu SanPham
            db.store(sanPham1);
            db.store(sanPham2);
            db.store(sanPham3);
            db.store(sanPham4);
            db.store(sanPham5);

// Lưu User
            db.store(user1);
            db.store(user2);
            db.store(user3);
            db.store(user4);
            db.store(user5);

            // chuc vu
            db.store(chucVu1);
            db.store(chucVu2);
            db.store(chucVu3);
            db.store(chucVu4);
            db.store(chucVu5);

            // chi tiet phieu nhap
            db.store(chiTietPhieuNhap1);
            db.store(chiTietPhieuNhap2);
            db.store(chiTietPhieuNhap3);
            db.store(chiTietPhieuNhap4);
            db.store(chiTietPhieuNhap5);

            // hang san xuat
            db.store(hangSanXuat1);
            db.store(hangSanXuat2);
            db.store(hangSanXuat3);
            db.store(hangSanXuat4);
            db.store(hangSanXuat5);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Update Dữ liệu
    public static void UpdateRecord(Object newObj, String type, String tenTruong, int id) {
        try {
            ObjectSet os = TruyVanSODA(tenTruong, String.valueOf(id), type, "int", "=");

            if (os.size() > 0) {
                for (Object oldObj : os) {
                    Class<?> clazz = oldObj.getClass();
                    Field[] fields = clazz.getDeclaredFields();

                    for (Field field : fields) {
                        field.setAccessible(true);
                        Object newValue = field.get(newObj);
                        field.set(oldObj, newValue);
                    }

                    db.store(oldObj);
                }
            } else {
                System.out.println("Không tìm thấy bản ghi với id: " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Object> ListAllDuLieu(String type) {
        ArrayList<Object> result = new ArrayList<>();
        Class<?> clazz = typeMapping.get(type);

        if (clazz == null) {
            System.err.println("Loại không hợp lệ: " + type);
            return result;
        }

        try {
            ObjectSet<?> objects = db.query(clazz);
            while (objects.hasNext()) {
                result.add(objects.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    // Xóa bản ghi
    public static void DeleteRecord(String type, String tenTruong, int id) {
        ObjectSet<?> os = TruyVanSODA(tenTruong, String.valueOf(id), type, "int", "=");
        try {
            for (Object object : os) {
                db.delete(object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Thêm bản ghi mới
    public static void AddRecord(Object record, String type) {
        try {
            Class<?> expectedClass = typeMapping.get(type);
            if (expectedClass == null || !expectedClass.isInstance(record)) {
                throw new IllegalArgumentException("Loại không hợp lệ hoặc không khớp: " + type);
            }

            db.store(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
