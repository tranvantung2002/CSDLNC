package Model;

public class LoaiKhachHang {
    private String MaLoaiKhachHang;
    private String TenLoaiKhachHang;

    public String getMaLoaiKhachHang() {
        return MaLoaiKhachHang;
    }

    public void setMaLoaiKhachHang(String maLoaiKhachHang) {
        MaLoaiKhachHang = maLoaiKhachHang;
    }

    public String getTenLoaiKhachHang() {
        return TenLoaiKhachHang;
    }

    public void setTenLoaiKhachHang(String tenLoaiKhachHang) {
        TenLoaiKhachHang = tenLoaiKhachHang;
    }

    @Override
    public String toString() {
        return "LoaiKhachHang{" +
                "MaLoaiKhachHang='" + MaLoaiKhachHang + '\'' +
                ", TenLoaiKhachHang='" + TenLoaiKhachHang + '\'' +
                '}';
    }
}
